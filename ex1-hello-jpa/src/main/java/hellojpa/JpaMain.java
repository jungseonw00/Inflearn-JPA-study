package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        // EntityManagerFactory는 하나만 생성해서 애플리케이션 전체에서 공유
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // EntityManager는 쓰레드간 공유X (사용하고 버려야 한다.)
        // JPA의 모든 데이터 변경은 트랜잭션 안에서 실행
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // JPA를 통해 Entity를 가져오면 JPA가 관리를 한다.
            // 커밋하는 시점에 값이 바뀐 걸 체크하고 바꼈으면 update 쿼리를 날린다.

            Member member = new Member();
            member.setUsername("C");

            System.out.println("====================");
            em.persist(member);

            System.out.println("member.getId() = " + member.getId());
            System.out.println("====================");
            
            tx.commit();
        } catch (Exception E) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}