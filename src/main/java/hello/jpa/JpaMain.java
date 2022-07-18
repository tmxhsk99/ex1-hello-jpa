package hello.jpa;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import javax.persistence.PersistenceUtil;
import org.hibernate.Hibernate;

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

            Member refMember = em.getReference(Member.class, member.getId());
            System.out.println("refMember = " + refMember.getClass()); //Proxy
            //refMember.getUsername();
            //프록시 인스턴스의 초기화 여부 확인
            //System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(refMember));
            //강제 초기화
            Hibernate.initialize(refMember);
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
