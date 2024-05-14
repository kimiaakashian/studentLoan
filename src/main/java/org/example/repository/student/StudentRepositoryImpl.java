package org.example.repository.student;

import org.example.base.repository.BaseRepositoryImpl;

import org.example.model.LoanRequest;
import org.example.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class StudentRepositoryImpl extends BaseRepositoryImpl<Student, Long> implements StudentRepository {
    public StudentRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Student> getEntityClass() {
        return Student.class;
    }

    public Student findByNationalCode(String nationalCode) {
        Student student = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            student = session.createQuery("FROM Student s WHERE s.nationalCode = :nationalCode", Student.class)
                    .setParameter("nationalCode", nationalCode)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return student;
    }

    public LoanRequest findSpouseInLoan(Student student) {
        LoanRequest loanRequest = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            loanRequest = session.createQuery("FROM LoanRequest lr WHERE lr.student.id = :studentId ", LoanRequest.class)
                    .setParameter("studentId", student.getId())
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return loanRequest;
    }

    @Override
    public Student findStudentByStudentId(Long studentId) {
        Student student = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            student = session.createQuery("FROM Student s WHERE s.id = :studentId", Student.class).setParameter("studentId", studentId).uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return student;
    }
}
