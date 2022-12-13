package org.example.service;

import org.example.configuration.SessionFactorySingleton;
import org.example.entity.Student;
import org.example.repository.StudentRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class StudentService {

    private final StudentRepository studentRepository = new StudentRepository();
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    public void add(Student student) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            try {
                transaction.begin();
                studentRepository.add(student);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());
            }
            finally {
                session.close();
            }
        }
    }

    public Student findByNationalCode(String nationalCode) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            try {
                return studentRepository.findByNationalCode(nationalCode);
            } catch (Exception e) {
                return null;
            }
            finally {
                session.close();
            }
        }
    }
}
