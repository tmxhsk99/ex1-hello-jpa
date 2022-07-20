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
            member.setUsername("member1");
            member.setHomeAddress(new Address("homeAddress", "street", "10000"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");

            member.getAddressHistory().add(new Address("old1", "street", "10000"));
            member.getAddressHistory().add(new Address("old2", "street", "10000"));

            em.persist(member);
            em.flush();
            em.clear();
            System.out.println("=================== START ==================");
            Member findMember = em.find(Member.class, member.getId());
            //값타입 수정
            //homeCity -> newCity;
            //안 좋은 예 : 사이드 이펙트가 발생할 수 있음
            //findMember.getHomeAddress().setCity("newCity");
            //좋은 예
            Address address = findMember.getHomeAddress();
            findMember.setHomeAddress(new Address("newCity",address.getStreet(),address.getZipcode()));

            //치킨 -> 한식
            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("한식");

            //Address의 Equals 를 제대로 구현해야한다.
            findMember.getAddressHistory().remove(new Address("old1","street","10000"));
            findMember.getAddressHistory().add(new Address("newCity","street","10000"));

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            //close
            em.close();
            emf.close();
        }
    }
}
