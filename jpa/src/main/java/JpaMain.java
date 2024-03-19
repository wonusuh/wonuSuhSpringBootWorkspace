import entity.Customer;
import entity.Locker;
import entity.Major;
import entity.Student;
import javassist.LoaderClassPath;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void init(EntityManager em){
        Student stu1 = new Student("김씨", "1학년");
        Student stu2 = new Student("이씨", "2학년");
        Student stu3 = new Student("박씨", "3학년");
        Major m1 = new Major("컴싸", "소프트엔지니어링");
        em.persist(m1);

//        stu1.setMajorId(m1.getMajorId());
//        stu2.setMajorId(m1.getMajorId());
//        stu3.setMajorId(m1.getMajorId());
        stu1.setMajor(m1);
        stu2.setMajor(m1);
        stu3.setMajor(m1);

        em.persist(stu1);
        em.persist(stu2);
        em.persist(stu3);
    }
    public static void main(String[] args) {
        // 객체 sessionFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("customer-ex");
        EntityManager em = emf.createEntityManager(); // session 객체
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {
//            init(em);
            Student stu = em.find(Student.class, 1L);
            Student stu2 = em.find(Student.class, 2L);
            Locker locker = em.find(Locker.class, 5L);
            stu.setLocker(locker);
            em.persist(locker);

            System.out.println("=====================");
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
