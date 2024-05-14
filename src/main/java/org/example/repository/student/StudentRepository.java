package org.example.repository.student;

import org.example.base.repository.BaseRepository;
import org.example.model.LoanRequest;
import org.example.model.Student;

public interface StudentRepository extends BaseRepository<Student,Long> {
     Student findByNationalCode(String nationalCode);
      LoanRequest findSpouseInLoan(Student student);
    Student findStudentByStudentId(Long studentId);
}
