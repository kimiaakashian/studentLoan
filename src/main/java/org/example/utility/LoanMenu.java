package org.example.utility;

import org.example.model.*;
import org.example.model.date.DateConvertorNew;
import org.example.service.loanInstallment.LoanInstallmentService;
import org.example.service.loanRequest.LoanRequestService;
import org.example.service.loanType.LoanTypeService;
import org.example.service.student.StudentService;

import java.util.List;
import java.util.Scanner;

public class LoanMenu {
    Scanner scanner = new Scanner(System.in);
    LoanTypeService loanTypeService = ApplicationContext.getLoanTypeService();
    StudentService studentService = ApplicationContext.getStudentService();
    LoanRequestService loanRequestService = ApplicationContext.getLoanRequestService();
    LoanInstallmentService loanInstallmentService = ApplicationContext.getLoanInstallmentService();

    public void loanRequest(long studentId) {
        boolean isGraduate = studentService.IsGraduated(studentId);
        if (isGraduate) {
            System.out.println("شما فارق التحصیل شده اید و قادر به دریافت وام نمیباشید");
        } else if (!isAllowedTimeToRegisterLoan()) {
            System.out.println("بازه زمانی غیر مجاز است");
        } else {
            System.out.println("کد وام را وارد کنید ");
            displayAllLoanTypes();
            Long loanTypeId = scanner.nextLong();
            System.out.println("مبلغ وام درخواستی را وارد کنید ");
            Double loanAmount = scanner.nextDouble();
            Student student = studentService.findById(studentId);
            LoanType loanType = loanTypeService.findById(loanTypeId);
            LoanRequest loanRequest = new LoanRequest(student, loanType, loanAmount, DateConvertorNew.todayDate());
            String message = loanRequestService.saveLoanWithInstallments(loanRequest);
            if (message.equalsIgnoreCase("OK"))
                System.out.println("درخواست وام شما با موفقیت ثبت گردید.");
            else
                System.out.println(message);
        }
    }

    public void displayAllLoanTypes() {
        List<LoanType> loanTypes = loanTypeService.findAllLoanTypes();
        for (LoanType l : loanTypes
        ) {
            System.out.println((l.getId()) + ". " + l.getLoanName() + ". " + l.getYear() + ". " + l.getLoanStatus());
        }
    }

    public void displayAllLoanRequestForStudent(Long studentId) {
        List<LoanRequest> loanRequests = loanRequestService.findAllLoanRequestForStudent(studentId);
        for (LoanRequest l : loanRequests
        ) {
            System.out.println((l.getId()) + ". " + l.getLoanAmount() + ". " + l.getLoanType() + ". " + l.getLoanDate());
        }
    }

    public void displayAllPaidInstallment(Long studentId) {
        List<LoanInstallment> loanInstallments = loanInstallmentService.findPaidInstallment(studentId);
        for (LoanInstallment l : loanInstallments
        ) {
            System.out.println((l.getId()) + ". " + l.getInstallmentAmount() + ". " + l.getInstallmentDate() + ". " + l.getInstallmentStatus());
        }
    }

    public void displayAllUnPaidInstallment(Long studentId) {
        List<LoanInstallment> loanInstallments = loanInstallmentService.findUnPaidInstallment(studentId);
        for (LoanInstallment l : loanInstallments
        ) {
            System.out.println((l.getId()) + ". " + l.getInstallmentAmount() + ". " + l.getInstallmentDate() + ". " + l.getInstallmentStatus());
        }
    }


    public boolean isAllowedTimeToRegisterLoan() {
        String[] todayDateArr = DateConvertorNew.todayDate().split("/");
        int month = Integer.parseInt(todayDateArr[1]);
        int day = Integer.parseInt(todayDateArr[2]);

        if ((month == 8 && day >= 1 && day <= 8) || (month == 2 && day >= 25 && day <= 30)) {
            return true;
        }
        return false;
    }


}


