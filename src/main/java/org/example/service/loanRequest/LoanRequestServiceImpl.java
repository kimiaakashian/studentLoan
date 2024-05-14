package org.example.service.loanRequest;

import org.example.base.service.BaseServiceImpl;
import org.example.model.LoanInstallment;
import org.example.model.LoanLimit;
import org.example.model.LoanRequest;
import org.example.model.Student;
import org.example.model.enums.InstallmentStatus;
import org.example.model.enums.UniversityType;
import org.example.model.date.DateConvertorNew;
import org.example.repository.LoanLimit.LoanLimitRepository;
import org.example.repository.LoanLimit.LoanLimitRepositoryImpl;
import org.example.repository.loanInstallment.LoanInstallmentRepository;
import org.example.repository.loanInstallment.LoanInstallmentRepositoryImpl;
import org.example.repository.loanRequest.LoanRequestRepository;
import org.example.service.student.StudentService;
import org.example.utility.ApplicationContext;
import org.hibernate.SessionFactory;

import java.util.List;

public class LoanRequestServiceImpl extends BaseServiceImpl<LoanRequest, Long, LoanRequestRepository> implements LoanRequestService {
    public LoanRequestServiceImpl(LoanRequestRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }

    int installmentCount = 60;
    double profit = 0.04;
    LoanInstallmentRepository loanInstallmentRepository = new LoanInstallmentRepositoryImpl(sessionFactory);
    LoanLimitRepository loanLimitRepository = new LoanLimitRepositoryImpl(sessionFactory);
    StudentService studentService = ApplicationContext.getStudentService();

    @Override
    public String saveLoanWithInstallments(LoanRequest loanRequest) {
        String validationMessage = validateLoanRequest(loanRequest);

        if (validationMessage.equalsIgnoreCase("OK")) {
            saveOrUpdate(loanRequest);
            String today = DateConvertorNew.todayDate();
            for (int i = 0; i < installmentCount; i++) {
                String installmentDate = DateConvertorNew.dateAdd(today, DateConvertorNew.IntervalTypes.MONTH, i);
                double installmentAmount = calculateInstallmentAmount(i, loanRequest.getLoanAmount(), profit);
                LoanInstallment loanInstallment = new LoanInstallment(loanRequest, i, installmentAmount, installmentDate, 0D, null, InstallmentStatus.پرداخت_نشده);
                loanInstallmentRepository.saveOrUpdate(loanInstallment);
            }
        }
        return validationMessage;
    }

    private double calculateInstallmentAmount(int installmentNumber, double loanAmount, double profit) {
        Double installmentAmount = 0D;
        int installmentCount = 12 + 24 + 48 + 96 ;
        int yearNum = (installmentNumber / 12) + 1;
        double loanAmountWithProfit = loanAmount * (1 + profit);
        Double minInstallmentAmount = loanAmountWithProfit / installmentCount;
        installmentAmount = minInstallmentAmount * yearNum;
        return installmentAmount;
    }

    private String validateLoanRequest(LoanRequest loanRequest) {
        String validationMessage = "OK";
        validationMessage = checkLoanLimitation(loanRequest);
        if (validationMessage.equalsIgnoreCase("OK")) {

            if (StudentTakeEducationLoanBefore(loanRequest) || StudentTakeTuitionLoanBefore(loanRequest)) {
                validationMessage = "شما قبلا این وام را دریافت نموده اید";
            } else if (loanRequest.getLoanType().getId().intValue() == 3) {
                if (StudentHaveDormitory(loanRequest)) {
                    validationMessage = "شما خوابگاه دارید و نمی توانید این وام را اخذ نمایید";
                } else if (StudentTakenHousingLoanBefore(loanRequest)) {
                    validationMessage = "شما قبلا این وام را دریافت نموده اید";

                } else if (SpouseTakenHousingLoanBefore(loanRequest)) {
                    validationMessage = "همسر شما این وام را اخذ نموده است!";
                }
            }
        }
        return validationMessage;
    }

    private boolean SpouseTakenHousingLoanBefore(LoanRequest loanRequest) {
        boolean validation = false;
        if (loanRequest.getStudent().getSpouseInfo() != null) {
            Student spouseStudent = studentService.findByNationalCode(loanRequest.getStudent().getSpouseInfo().getNationalCode());
            if (spouseStudent != null) {
                LoanRequest loanRequestHousing = repository.findHousingLoanRequestForStudent(spouseStudent.getId());
                if (loanRequestHousing != null)
                    validation = true;
            }
        }
        return validation;
    }
    private boolean StudentTakenHousingLoanBefore(LoanRequest loanRequest) {
        boolean validation = false;
        if (loanRequest.getStudent()!= null) {
            Student student = studentService.findByNationalCode(loanRequest.getStudent().getNationalCode());
            if (student != null) {
                LoanRequest loanRequestHousing = repository.findHousingLoanRequestForStudent(student.getId());
                if (loanRequestHousing != null)
                    validation = true;
            }
        }
        return validation;
    }


    private boolean StudentHaveDormitory(LoanRequest loanRequest) {
        boolean validation = false;
        if (loanRequest.getStudent().getDormitory()==1){
            validation=true;
        }
        return validation;
    }

    private boolean StudentTakeEducationLoanBefore(LoanRequest loanRequest) {
        boolean validation = false;
        if (loanRequest.getStudent() != null) {
            Student studentEducationLoan = studentService.findByNationalCode(loanRequest.getStudent().getNationalCode());
            if (studentEducationLoan != null) {
                LoanRequest educationLoanRequest = repository.findEducationLoanRequestForStudent(studentEducationLoan.getId());
                if (educationLoanRequest != null)
                    validation = true;
            }
        }
        return validation;
    }
    private boolean StudentTakeTuitionLoanBefore(LoanRequest loanRequest) {
        boolean validation = false;
        if (loanRequest.getStudent() != null) {
            Student studentEducationLoan = studentService.findByNationalCode(loanRequest.getStudent().getNationalCode());
            if (studentEducationLoan != null) {
                LoanRequest tuitionLoanRequest = repository.findTuitionLoanRequestForStudent(studentEducationLoan.getId());
                if (tuitionLoanRequest != null)
                    validation = true;
            }
        }
        return validation;
    }

    private String checkLoanLimitation(LoanRequest loanRequest) {
        String validationMessage = "OK";
        LoanLimit loanLimit = null;
        if (loanRequest.getLoanType().getId() == 3) {
            loanLimit = loanLimitRepository.findByLoanTypeAndCityType(loanRequest.getLoanType().getId()
                    , loanRequest.getStudent().getUniversity().getCity().getCityType());
        }else if (loanRequest.getLoanType().getId() == 1 ) {
            if (loanRequest.getStudent().getUniversity().getUniversityType().equals(UniversityType.دولتی_روزانه)){
                validationMessage= "شما در دانشگاه  دولتی در حال تحصیل میباشید و امکان دریافت این وام وجود ندارد";

            }
            loanLimit = loanLimitRepository.findByLoanTypeAndEducationGrade(loanRequest.getLoanType().getId()
                    , loanRequest.getStudent().getEducationGrade().getId());
        }else if (loanRequest.getLoanType().getId() == 2) {
                loanLimit = loanLimitRepository.findByLoanTypeAndEducationGrade(loanRequest.getLoanType().getId()
                        , loanRequest.getStudent().getEducationGrade().getId());
            }


            if (loanLimit != null && loanRequest.getLoanAmount() > loanLimit.getLoanAmount()) {
                validationMessage = "مبلغ درخواستی بیشتر از سقف تسهیلات می باشد.";
            } else if (loanLimit == null) {
                validationMessage = "وام درخواستی پیدا نشد";
            }
            return validationMessage;
        }
    public List<LoanRequest> findAllLoanRequestForStudent(Long studentId) {
        return repository.findAllLoanRequestForStudent(studentId);}
    }




