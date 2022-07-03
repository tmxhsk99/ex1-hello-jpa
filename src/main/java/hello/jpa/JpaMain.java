package hello.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {

            //membmer insert

/*
            Member member = new Member();
            member.setId(2L);
            member.setName("Hello B");
            em.persist(member);
            tx.commit();
*/


            //member find
/*
            Member findMember = em.find(Member.class, 2L);
            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.name = " + findMember.getName());
            tx.commit();
 */



            //memeber remove
            /*
            Member findMember = em.find(Member.class, 2L);
            em.remove(findMember);
            tx.commit();*/

            //member update
            /*
            Member findMember = em.find(Member.class, 1L);
            findMember.setName("HelloJPA");
            tx.commit();
            */

            //select ALL
            /*
            List<Member> result = em.createQuery("select m from Member as m", Member.class).setFirstResult(1).setMaxResults(10).getResultList();
            for (Member member : result) {
                System.out.println("member.getName = " + member.getName());
            }*/

        } catch (Exception e) {
            tx.rollback();
        } finally {
            //close
            em.close();
            emf.close();

        }
    }
}
