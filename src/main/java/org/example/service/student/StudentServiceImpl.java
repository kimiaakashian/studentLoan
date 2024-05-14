package org.example.service.student;

import org.example.base.service.BaseServiceImpl;
import org.example.model.EducationGrade;
import org.example.model.LoanRequest;
import org.example.model.Student;
import org.example.model.date.DateConvertorNew;
import org.example.repository.student.StudentRepository;
import org.hibernate.SessionFactory;

public class StudentServiceImpl extends BaseServiceImpl<Student, Long, StudentRepository> implements StudentService {
    public StudentServiceImpl(StudentRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }

    @Override
    public Student findByNationalCode(String nationalCode) {
        return repository.findByNationalCode(nationalCode);
    }

    @Override
    public LoanRequest findSpouseInLoan(Student student) {
        return repository.findSpouseInLoan(student);
    }

    @Override
    public void signUpUsers(Student student) {

        repository.saveOrUpdate(student);

    }


    public int calculateGraduationYear(int entryYear, EducationGrade educationGrade) {
        int graduationYear = 0;

        if (educationGrade.getId() == 1 || educationGrade.getId() == 4) {
            graduationYear = entryYear + 2;
        } else if (educationGrade.getId() == 2 || educationGrade.getId() == 3) {
            graduationYear = entryYear + 4;
        } else if (educationGrade.getId() == 5) {
            graduationYear = entryYear + 6;
        } else if (educationGrade.getId() == 6 || educationGrade.getId() == 7 || educationGrade.getId() == 8) {
            graduationYear = entryYear + 5;
        } else {
            throw new UnsupportedOperationException("Unsupported education grade");
        }

        return graduationYear;
    }

    public boolean IsGraduated(Long studentId) {
        Student student = repository.findStudentByStudentId(studentId);
        int graduationYear = calculateGraduationYear(student.getEntryYear(), student.getEducationGrade());
        boolean isGraduated = graduationYear <= Integer.valueOf(DateConvertorNew.todayDate().split("/")[0]);

        return isGraduated;

    }
}
