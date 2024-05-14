package org.example.service.student;

import org.example.base.service.BaseService;
import org.example.model.EducationGrade;
import org.example.model.LoanRequest;
import org.example.model.Student;

public interface StudentService extends BaseService<Student,Long> {
    Student findByNationalCode(String nationalCode);
    LoanRequest findSpouseInLoan(Student student);
    void signUpUsers(Student student);
     int calculateGraduationYear(int entryYear, EducationGrade educationGrade);
    boolean IsGraduated(Long studentId);

}
