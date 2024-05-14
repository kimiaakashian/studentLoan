package org.example.utility;

import org.example.model.BankAccount;
import org.example.model.LoanInstallment;
import org.example.model.Student;
import org.example.model.enums.InstallmentStatus;
import org.example.model.date.DateConvertorNew;
import org.example.service.bankAccount.BankAccountService;
import org.example.service.loanInstallment.LoanInstallmentService;
import org.example.service.student.StudentService;

import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    LoanMenu loanMenu = new LoanMenu();
    StudentMenu studentMenu = new StudentMenu();
    BankAccountService bankAccountService = ApplicationContext.getBankAccountService();
    LoanInstallmentService loanInstallmentService = ApplicationContext.getLoanInstallmentService();
    StudentService studentService = ApplicationContext.getStudentService();

    public void mainMenu() {
        int number = 0;
        Long studentId = 0L;
        while (number != 5) {
            System.out.println("****Welcome****");
            System.out.println("\n 1.ثبت نام\n 2.دریافت وام\n 3.یازپرداخت\n 4.خروج");
            System.out.println("یکی از موارد را انتخاب کنید");
            number = scanner.nextInt();
            scanner.nextLine();
            switch (number) {
                case 1 -> studentMenu.StudentRegister();
                case 2 -> {
                    studentId = signIn();
                    loanMenu.loanRequest(studentId);
                }
                case 3 -> {
                    while (number != 4) {
                        studentId = signIn();
                        System.out.println("\n 1. اقساط پرداخت شده\n 2. اقساط پرداخت نشده\n 3.پرداخت قسط\n 4.بازگشت به متو اصلی");
                        number = scanner.nextInt();
                        switch (number) {
                            case 1 -> loanMenu.displayAllPaidInstallment(studentId);
                            case 2 -> {
                                loanMenu.displayAllUnPaidInstallment(studentId);
                            }
                            case 3 -> {
                                payment(studentId);
                            }
                            case 4 -> mainMenu();

                        }

                    }

                }
                case 4 -> {
                    break;
                }

            }
        }
    }

    public static Long signIn() {
        Scanner scanner = new Scanner(System.in);
        StudentService studentService = ApplicationContext.getStudentService();
        while (true) {

            System.out.println("Please enter your username: ");
            String nationalCode = scanner.nextLine();

            System.out.println("Please enter your password: ");
            String password = scanner.nextLine();


            Student student = studentService.findByNationalCode(nationalCode);

            if (student != null && student.getPassword().equals(password)) {
                return student.getId();
            } else if (student == null) {
                System.out.println("User does not exist");

            } else if (!password.equals(student.getPassword())) {
                System.out.println(("Incorrect password."));
            }
        }
    }


    public void payment(long studentId) {
        boolean isGraduate = studentService.IsGraduated(studentId);
        if (!isGraduate) {
            System.out.println("شما فارق التحصیل نشده اید");
        } else {

            while (true) {
                System.out.println("مایل به پرداخت کدام قسط میباشید؟");

                loanMenu.displayAllLoanRequestForStudent(studentId);
                Long loanRequestId = scanner.nextLong();
                scanner.nextLine();
                LoanInstallment loanInstallment = loanInstallmentService.findFirstLoanInstallment(loanRequestId);

                System.out.println(" شماره حساب:  ");
                String accountNumber = scanner.nextLine();

                System.out.println("لطفا cvv2 را وارد کنید: ");
                String cvv2 = scanner.nextLine();

                System.out.println("تاریخ انقصا کارت: ");
                String expireDate = scanner.nextLine();

                if (!bankAccountService.isValidExpirationDate(expireDate)) {
                    System.out.println("کارت منقضی شده است");

                } else {
                    BankAccount bankAccount = bankAccountService.findByAccountNumber(accountNumber);
                    if (bankAccount != null && bankAccount.getAccountNumber().equals(accountNumber) && bankAccount.getCvv2().equals(cvv2)) {
                        loanInstallment.setInstallmentStatus(InstallmentStatus.پرداخت_شده);
                        loanInstallment.setPayDate(DateConvertorNew.todayDate());
                        loanInstallment.setPayAmount(loanInstallment.getInstallmentAmount());
                        loanInstallmentService.saveOrUpdate(loanInstallment);
                        System.out.println("قسط شماره" + loanInstallment.getId() + "به تاریخ" + loanInstallment.getPayDate() + "و مبلغ" + loanInstallment.getPayAmount() + "با موفقیت انجام شد.");
                    }

                }
            }
        }
    }


}
