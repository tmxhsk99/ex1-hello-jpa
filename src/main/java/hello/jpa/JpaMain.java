package hello.jpa;

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
            //저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            //1차캐시에 Team만 등록됨 | 1L | TeamA |

            Member member = new Member();
            member.setName("member1");
            //연관관계 편의 메서드 적용시
            member.changeTeam(team);
            em.persist(member);


            //1차 캐시에서 가져오기 때문에 영속성 콘텍스트를 초기야 해야 쿼리를 볼수 있다.
//            em.flush();
//            em.clear();

            Team findTeam = em.find(Team.class,team.getId());
            List<Member> members = findTeam.getMembers();
            System.out.println("==========================");
            for (Member m : members) {
                System.out.println("m = " + m.getName());
            }
            System.out.println("==========================");


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
