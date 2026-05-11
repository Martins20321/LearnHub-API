package com.martinsdev.learnhub.api.config;

import com.estudosjavaspring.springcourse.model.*;
import com.martinsdev.learnhub.api.model.*;
import com.martinsdev.learnhub.api.model.enums.CourseLevel;
import com.martinsdev.learnhub.api.model.enums.EnrollmentStatus;
import com.martinsdev.learnhub.api.model.enums.UserRole;
import com.estudosjavaspring.springcourse.repositories.*;
import com.martinsdev.learnhub.api.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public void run(String... args) throws Exception {

        // 1. Instanciar Usuários
        User us1 = new User(null, "Pablo Marcelo", "pabloa@gmail.com", "6199843123", "1234", UserRole.ADMIN);
        User us2 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456", UserRole.STUDENT);
        User us3 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456", UserRole.INSTRUCTOR);

        // 2. Instanciar Categorias
        Category cat1 = new Category(null, "Full Stack");
        Category cat2 = new Category(null, "Carreira Python");
        Category cat3 = new Category(null, "Marketing Digital");

        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
        userRepository.saveAll(Arrays.asList(us1, us2, us3));

        // 3. Instanciar Cursos (dependem de Categoria)
        Course c1 = new Course(null, "Desenvolvimento Web Full Stack", "Aprenda a criar aplicações web completas...", 997.50, CourseLevel.BEGINNER, null, cat1);
        Course c2 = new Course(null, "Análise de Dados com Python e Pandas", "Domine as bibliotecas Python...", 450.00, CourseLevel.INTERMEDIATE, null, cat2);
        Course c3 = new Course(null, "Estratégias Avançadas de Marketing Digital", "Crie campanhas eficazes...", 399.90, CourseLevel.ADVANCED, null, cat3);

        courseRepository.saveAll(Arrays.asList(c1, c2, c3));

        // 4. Instanciar Lições (dependem de Curso)
        Lesson l1 = new Lesson(null, "Criação de API", 2.00, "video1.mp4", c1);
        Lesson l2 = new Lesson(null, "Aprendendo Spring Boot", 3.00, "video2.mp4", c2);
        Lesson l3 = new Lesson(null, "Certificação AWS", 3.30, "video3.mp4", c3);

        lessonRepository.saveAll(Arrays.asList(l1, l2, l3));

        // 5. Instanciar Matrículas (dependem de User e Course)
        Enrollment e1 = new Enrollment(c1, us2, Instant.parse("2024-03-10T10:00:00Z"), EnrollmentStatus.ACTIVE);
        Enrollment e2 = new Enrollment(c2, us1, Instant.parse("2024-03-11T12:00:00Z"), EnrollmentStatus.CANCELED);
        Enrollment e3 = new Enrollment(c3, us3, Instant.parse("2024-03-12T15:00:00Z"), EnrollmentStatus.COMPLETED);

        enrollmentRepository.saveAll(Arrays.asList(e1, e2, e3));

        // 6. Instanciar Pagamentos (dependem de Enrollment)
        Payment p1 = new Payment(null, Instant.now(), c1.getPrice(), e1);
        Payment p2 = new Payment(null, Instant.now(), 0.00, e2);
        Payment p3 = new Payment(null, Instant.now(), c3.getPrice(), e3);

        e1.setPayment(p1);
        e2.setPayment(p2);
        e3.setPayment(p3);

        paymentRepository.saveAll(Arrays.asList(p1, p2, p3));
    }
}