package org.example.service;

import org.example.configuration.SessionFactorySingleton;
import org.example.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentServiceTest {
    private static SessionFactory sessionFactory;
    private final Student student = new Student("alireza", "rastegar", "5180182832", false);
    private final StudentService studentService = new StudentService();
    private Session session;

    @BeforeAll
    public static void setup() {
        sessionFactory = SessionFactorySingleton.getInstance();
        System.out.println("SessionFactory created");
    }

    @AfterAll
    public static void tearDown() {
        if (sessionFactory != null) sessionFactory.close();
        System.out.println("SessionFactory destroyed");
    }

    @Test
    @Order(1)
    void add() {
        System.out.println("Running testCreate...");
        session.beginTransaction();

        studentService.add(student);

        Student newStudent = session.get(Student.class, 1L);
        assertEquals(student.getFirstName(), newStudent.getFirstName());
        session.getTransaction().commit();
    }

    @Test
    @Order(2)
    void findByNationalCode() {
        System.out.println("Running testCreate...");
        Student newStudent = studentService.findByNationalCode("5180182832");

        assertNotNull(newStudent);
    }

    @BeforeEach
    public void openSession() {
        session = sessionFactory.openSession();
        System.out.println("Session created");
    }

    @AfterEach
    public void closeSession() {
        if (session != null) session.close();
        System.out.println("Session closed\n");
    }
}