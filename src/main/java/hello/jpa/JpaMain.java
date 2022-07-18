package hello.jpa;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Member member = new Member();
            member.setUsername("hello");
            em.persist(member);
            em.flush();
            em.clear();
            //Member findMember = em.find(Member.class, member.getId());
            Member findMember = em.getReference(Member.class, member.getId());
            System.out.println("findMember.getClass() = " + findMember.getClass());
            System.out.println("findMember = " + findMember.getId());
            //System.out.println("findMember = " + findMember.getUsername());
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            //close
            em.close();
            emf.close();
        }
    }
}
