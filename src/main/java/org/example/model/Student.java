package org.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.base.entity.BaseEntity;
import org.example.model.enums.MaritalType;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student extends BaseEntity<Long> {
    private String firstName;
    private String lastName;
    private String fatherName;
    private String motherName;
    @NotBlank(message = "National code cannot be empty or null.")
    @Size(min = 10, max = 10, message = "National code must be 10 characters long.")
    private String nationalCode;
    private String birthDate;
    private Long studentCode;
    @ManyToOne
    private University university;
    //۱.خوابگاه دارم ۲.خوابگاه ندارم
    private int dormitory;
    @Enumerated(EnumType.STRING)
    private MaritalType maritalType;
    private int entryYear;
    @ManyToOne
    private EducationGrade educationGrade;
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
            message = "Password must contain at least one digit, one lowercase, one uppercase, one special character and no whitespaces")
    private String password;
    @ManyToOne
    private Student spouseInfo;

    @ManyToOne
    private BankAccount bankAccount;
    private String address;

    public Student(String nationalCode, String password) {
        this.nationalCode = nationalCode;
        this.password = password;
    }

    public Student(String firstName, String lastName, String fatherName, String motherName, String birthDate, Long studentCode, University university, int dormitory, MaritalType maritalType, int entryYear, EducationGrade educationGrade, Student spouseInfo, BankAccount bankAccount, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.birthDate = birthDate;
        this.studentCode = studentCode;
        this.university = university;
        this.dormitory = dormitory;
        this.maritalType = maritalType;
        this.entryYear = entryYear;
        this.educationGrade = educationGrade;
        this.spouseInfo = spouseInfo;
        this.bankAccount = bankAccount;
        this.address = address;
    }
}
