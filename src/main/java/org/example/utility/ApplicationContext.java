package org.example.utility;

import org.example.conncetion.SessionFactorySingleton;

import org.example.repository.EducationGrade.EducationGradeRepository;
import org.example.repository.EducationGrade.EducationGradeRepositoryImpl;
import org.example.repository.LoanLimit.LoanLimitRepository;
import org.example.repository.LoanLimit.LoanLimitRepositoryImpl;
import org.example.repository.bank.BankRepository;
import org.example.repository.bank.BankRepositoryImpl;
import org.example.repository.bankAccount.BankAccountRepository;
import org.example.repository.bankAccount.BankAccountRepositoryImpl;
import org.example.repository.city.CityRepository;
import org.example.repository.city.CityRepositoryImpl;
import org.example.repository.loanInstallment.LoanInstallmentRepository;
import org.example.repository.loanInstallment.LoanInstallmentRepositoryImpl;
import org.example.repository.loanRequest.LoanRequestRepository;
import org.example.repository.loanRequest.LoanRequestRepositoryImpl;
import org.example.repository.loanType.LoanTypeRepository;
import org.example.repository.loanType.LoanTypeRepositoryImpl;
import org.example.repository.student.StudentRepository;
import org.example.repository.student.StudentRepositoryImpl;
import org.example.repository.university.UniversityRepository;
import org.example.repository.university.UniversityRepositoryImpl;
import org.example.service.bank.BankService;
import org.example.service.bank.BankServiceImpl;
import org.example.service.bankAccount.BankAccountService;
import org.example.service.bankAccount.BankAccountServiceImpl;
import org.example.service.city.CityService;
import org.example.service.city.CityServiceImpl;
import org.example.service.educationGrade.EducationGradeService;
import org.example.service.educationGrade.EducationGradeServiceImpl;
import org.example.service.loanInstallment.LoanInstallmentService;
import org.example.service.loanInstallment.LoanInstallmentServiceImpl;
import org.example.service.loanLimit.LoanLimitService;
import org.example.service.loanLimit.LoanLimitServiceImpl;
import org.example.service.loanRequest.LoanRequestService;
import org.example.service.loanRequest.LoanRequestServiceImpl;
import org.example.service.loanType.LoanTypeService;
import org.example.service.loanType.LoanTypeServiceImpl;
import org.example.service.student.StudentService;
import org.example.service.student.StudentServiceImpl;
import org.example.service.university.UniversityService;
import org.example.service.university.UniversityServiceImpl;
import org.hibernate.SessionFactory;

public class ApplicationContext {
    static final SessionFactory SESSION_FACTORY;
    static final BankRepository BANK_REPOSITORY;
    static final BankService BANK_SERVICE;
    static final BankAccountRepository BANK_ACCOUNT_REPOSITORY;
    static final BankAccountService BANK_ACCOUNT_SERVICE;
    static final CityRepository CITY_REPOSITORY;
    static final CityService CITY_SERVICE;
    static final EducationGradeRepository EDUCATION_GRADE_REPOSITORY;
    static final EducationGradeService EDUCATION_GRADE_SERVICE;
    static final LoanLimitRepository LOAN_LIMIT_REPOSITORY;
    static final LoanLimitService LOAN_LIMIT_SERVICE;
    static final LoanRequestRepository LOAN_REQUEST_REPOSITORY;
    static final LoanRequestService LOAN_REQUEST_SERVICE;

    static final LoanTypeRepository LOAN_TYPE_REPOSITORY;
    static final LoanTypeService LOAN_TYPE_SERVICE;

    static final UniversityRepository UNIVERSITY_REPOSITORY;
    static final UniversityService UNIVERSITY_SERVICE;
    static final StudentRepository STUDENT_REPOSITORY;
    static final StudentService STUDENT_SERVICE;
    static final LoanInstallmentRepository LOAN_INSTALLMENT_REPOSITORY;
    static final LoanInstallmentService LOAN_INSTALLMENT_SERVICE;

    static {
        SESSION_FACTORY = SessionFactorySingleton.getInstance();
        STUDENT_REPOSITORY = new StudentRepositoryImpl(SESSION_FACTORY);
        STUDENT_SERVICE = new StudentServiceImpl(STUDENT_REPOSITORY, SESSION_FACTORY);
        BANK_REPOSITORY = new BankRepositoryImpl(SESSION_FACTORY);
        BANK_SERVICE = new BankServiceImpl(BANK_REPOSITORY, SESSION_FACTORY);
        BANK_ACCOUNT_REPOSITORY = new BankAccountRepositoryImpl(SESSION_FACTORY);
        BANK_ACCOUNT_SERVICE = new BankAccountServiceImpl(BANK_ACCOUNT_REPOSITORY, SESSION_FACTORY);
        CITY_REPOSITORY = new CityRepositoryImpl(SESSION_FACTORY);
        CITY_SERVICE = new CityServiceImpl(CITY_REPOSITORY, SESSION_FACTORY);
        EDUCATION_GRADE_REPOSITORY = new EducationGradeRepositoryImpl(SESSION_FACTORY);
        EDUCATION_GRADE_SERVICE = new EducationGradeServiceImpl(EDUCATION_GRADE_REPOSITORY, SESSION_FACTORY);
        LOAN_INSTALLMENT_REPOSITORY = new LoanInstallmentRepositoryImpl(SESSION_FACTORY);
        LOAN_INSTALLMENT_SERVICE = new LoanInstallmentServiceImpl(LOAN_INSTALLMENT_REPOSITORY, SESSION_FACTORY);
        LOAN_LIMIT_REPOSITORY = new LoanLimitRepositoryImpl(SESSION_FACTORY);
        LOAN_LIMIT_SERVICE = new LoanLimitServiceImpl(LOAN_LIMIT_REPOSITORY, SESSION_FACTORY);
        LOAN_REQUEST_REPOSITORY = new LoanRequestRepositoryImpl(SESSION_FACTORY);
        LOAN_REQUEST_SERVICE = new LoanRequestServiceImpl(LOAN_REQUEST_REPOSITORY, SESSION_FACTORY);
        LOAN_TYPE_REPOSITORY = new LoanTypeRepositoryImpl(SESSION_FACTORY);
        LOAN_TYPE_SERVICE = new LoanTypeServiceImpl(LOAN_TYPE_REPOSITORY, SESSION_FACTORY);
        UNIVERSITY_REPOSITORY = new UniversityRepositoryImpl(SESSION_FACTORY);
        UNIVERSITY_SERVICE = new UniversityServiceImpl(UNIVERSITY_REPOSITORY, SESSION_FACTORY);


    }

    public static StudentService getStudentService() {
        return STUDENT_SERVICE;
    }

    public static BankService getBankService() {
        return BANK_SERVICE;
    }

    public static BankAccountService getBankAccountService() {
        return BANK_ACCOUNT_SERVICE;
    }

    public static UniversityService getUniversityService() {
        return UNIVERSITY_SERVICE;

    }

    public static EducationGradeService getEducationGradeService() {
        return EDUCATION_GRADE_SERVICE;
    }


    public static LoanInstallmentService getLoanInstallmentService() {
        return LOAN_INSTALLMENT_SERVICE;
    }

    public static LoanTypeService getLoanTypeService() {
        return LOAN_TYPE_SERVICE;

    }
    public static LoanRequestService getLoanRequestService(){
        return LOAN_REQUEST_SERVICE;
    }


}
