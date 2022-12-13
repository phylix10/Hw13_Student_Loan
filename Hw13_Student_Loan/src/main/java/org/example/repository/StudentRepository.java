package org.example.repository;

import org.example.configuration.SessionFactorySingleton;
import org.example.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class StudentRepository extends BaseRepository<Student> {

    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    public StudentRepository() {
        super("Student", Student.class);
    }

    public Student findByNationalCode(String nationalCode) {
        Session session = sessionFactory.openSession();
        Query<Student> query = session.createQuery("select s from Student s where s.nationalCode=:nationalCode", Student.class)
                .setParameter("nationalCode", nationalCode);
        List<Student> list = query.list();
        session.close();
        if (list.isEmpty())
            return null;

        return list.get(0);
    }
}
