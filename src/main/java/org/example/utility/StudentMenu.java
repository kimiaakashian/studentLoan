package org.example.utility;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.example.model.*;
import org.example.model.enums.MaritalType;
import org.example.service.bank.BankService;
import org.example.service.bankAccount.BankAccountService;
import org.example.service.educationGrade.EducationGradeService;
import org.example.service.student.StudentService;
import org.example.service.university.UniversityService;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class StudentMenu {
    Scanner scanner = new Scanner(System.in);
    StudentService studentService = ApplicationContext.getStudentService();
    UniversityService universityService = ApplicationContext.getUniversityService();
    EducationGradeService educationGradeService = ApplicationContext.getEducationGradeService();
    BankAccountService bankAccountService = ApplicationContext.getBankAccountService();
    BankService bankService = ApplicationContext.getBankService();

    public void StudentRegister() {
        System.out.println("کد ملی: ");
        String nationalCode = scanner.nextLine();
        System.out.println("رمز عبور: ");
        String password = scanner.nextLine();
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Student studentValid = new Student(nationalCode, password);
        Set<ConstraintViolation<Student>> violations = validator.validate(studentValid);

        if (!violations.isEmpty()) {
            for (ConstraintViolation<Student> violation : violations) {
                System.out.println(violation.getMessage());
                StudentRegister();
                break;
            }
        } else {

            System.out.println(" نام: ");
            String firstName = scanner.nextLine();
            System.out.println("نام خانوادگی: ");
            String lastName = scanner.nextLine();
            System.out.println("نام مادر: ");
            String motherName = scanner.nextLine();
            System.out.println("نام پدر: ");
            String fatherName = scanner.nextLine();
            System.out.println("تاریخ تولد: ");
            String birthDate = scanner.nextLine();
            System.out.println("کد دانشجویی: ");
            Long studentCode = scanner.nextLong();
            System.out.println("کد دانشگاه: ");
            System.out.println("لیست دانشگاه ها :");
            displayAllUniversities();
            Long universityId = scanner.nextLong();
            System.out.println("ایا خوابگاه دارید ؟ ۱.بله ۲.خیر");
            int dormitory = scanner.nextInt();
            System.out.println("سال ورود به دانشگاه:");
            int entryYear = scanner.nextInt();
            System.out.println("کد مقطع تحصیلی: ");
            System.out.println("مقاطع :");
            displayAllEducationGrades();
            Long educationGradeId = scanner.nextLong();
            String typeInput = "0";
            while (!(typeInput.equals("1") || typeInput.equals("2"))) {
                System.out.println("وضعیت تاهل: ۱.متاهل ۲.مجرد");
                typeInput = scanner.nextLine();
            }
            MaritalType maritalTypeInput;
            String spouseNationalCode = "";
            if (typeInput.equals("1")) {
                maritalTypeInput = MaritalType.MARRIED;
                System.out.println("کد ملی همسر:");
                 spouseNationalCode = scanner.nextLine();

            } else

                maritalTypeInput = MaritalType.BACHELOR;


            System.out.println("ادرس خود را وارد کنید :");
            String address = scanner.nextLine();

            System.out.println("****اطلاعات بانگی****");
            System.out.println("یکی از بانگ های زیر را انتخاب کنید :");
            displayAllBanks();
            Long bankId = scanner.nextLong();
            scanner.nextLine();
            System.out.println("شماره حساب :");
            String accountNumber = scanner.nextLine();
            System.out.println("cvv2 :");
            String cvv2 = scanner.nextLine();
            System.out.println("تاریخ انقضا :");
            String expireDateّ = scanner.nextLine();

            Bank bank = bankService.findById(bankId);
            BankAccount bankAccount = new BankAccount(accountNumber, cvv2, expireDateّ, bank);
            bankAccountService.saveOrUpdate(bankAccount);

            Student studentSpouse = studentService.findByNationalCode(spouseNationalCode);
            EducationGrade educationGrade = educationGradeService.findById(educationGradeId);
            University university = universityService.findById(universityId);
            Student student = new Student(firstName, lastName, fatherName, motherName, nationalCode, birthDate, studentCode, university, dormitory, maritalTypeInput, entryYear, educationGrade, password, studentSpouse, bankAccount, address);
            studentService.saveOrUpdate(student);
        }
    }

    public void displayAllUniversities() {
        List<University> universities = universityService.findAllUniversity();
        for (University u : universities
        ) {
            System.out.println((u.getId()) + ". " + u.getName() + ". " + u.getUniversityType());
        }
    }

    public void displayAllEducationGrades() {
        List<EducationGrade> educationGrades = educationGradeService.findAllEducationGrades();
        for (EducationGrade e : educationGrades
        ) {
            System.out.println((e.getId()) + ". " + e.getGradeName());
        }
    }

    public void displayAllBanks() {
        List<Bank> banks = bankService.findAllBanks();
        for (Bank b : banks
        ) {
            System.out.println((b.getId()) + ". " + b.getName());
        }
    }

}

