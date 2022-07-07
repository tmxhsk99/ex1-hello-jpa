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
            //저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            //1차캐시에 Team만 등록됨 | 1L | TeamA |

            Member member = new Member();
            member.setName("member1");
            member.setTeam(team);
            em.persist(member);

            //1차캐시에서 Member 등록함 | 1L | member1 | TeamA |

            //1차 캐시에서 가져오기 때문에 영속성 콘텍스트를 초기야 해야 쿼리를 볼수 있다.
//            em.flush();
//            em.clear();

            Team findTeam = em.find(Team.class,team.getId()); //1차 캐시에 있는 Team 객체를 가져옴 , 그러나 아직 쿼리가 안날라갔기때문에 Members에 객체가 셋팅이 안되어있는 상태
            List<Member> members = findTeam.getMembers();
            System.out.println("==========================");
            //쿼리문으로 연관관계가 맵핑되지 않은 상태인 순수 객체이므로 멤버가 나오지 않는다.
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
