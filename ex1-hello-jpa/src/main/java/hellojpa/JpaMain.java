package hellojpa;

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

            /*
            저장 Before
            Member member = new Member();
            member.setUsername("member1");
            em.persist(member);

            Team team = new Team();
            team.setName("TeamA");
            team.getMembers().add(member);
            em.persist(team);

            return member.team_id = null
            연관관계 주인은 member의 team이기에 member.team에 값을 넣어주어야 한다.
            */

            // 저장 After
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team);
            em.persist(member);

            team.addMember(member);

//            em.flush(); // 영속성 컨텍스트에 있는 SQL을 DB에 전달.
//            em.clear(); // 1차 캐시등의 데이터는 entityManager 소멸시점이랑 같지만, 명령어로 미리 지운다.

            Team findTeam = em.find(Team.class, team.getId()); // 1차 캐시를 조회한다.
            List<Member> members = findTeam.getMembers();
            System.out.println("============");
            System.out.println("members= " + findTeam);
            // 무한루프로 StackOverflowError 발생
            // entity는 controller에서 사용하면 X, toString(), JSON 생성 라이브러리로 무한루프 생성 가능성이 높다. 그래서 DTO를 사용해야 한다.
            // 단방향 매핑을 잘 하고 양방향은 필요할 때 추가해도 됨 (테이블에 영향을 주지 않는다.)
            System.out.println("============");

            tx.commit();
        } catch (Exception E) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}