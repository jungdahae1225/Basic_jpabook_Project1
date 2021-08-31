package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        /**JPA는 하나의 DB당 하나의 EntityManagerFactory를 만들어야 한다.**/
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");

        /**고객의 요청이 올 때마다 EntityManager를 통해서 작업을 해야한다.**/
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        //code

        /**==jpa을 통한 모든 작업은 꼭 트랜젝션 단위로 해당 트랜젝션 안에서 작업해야 하기 때문에 작업을 하나의 트랜젝션으로 감싸주어야 한다.
         * 즉, JPA의 모든 데이터 변경은 트랜젝션 안에서 실행해야한다.==**/
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin(); //트랜젝션 시작

        try {
            //팀 저장
            Team team = new Team();
            team.setName("TeamA");
            entityManager.persist(team);
            //회원 저장
            Member member = new Member();
            member.setName("member1");
            member.setTeam(team); //단방향 연관관계 설정, 참조 저장 (객체를 테이블에 맞춰 모델링 했던 코드와 달리 이렇게 바로 객체를 넣어 연결가능하다.)
            entityManager.persist(member);

            //조회
            Member findMember = entityManager.find(Member.class, member.getId());
            //참조를 사용해서 연관관계 조회
            Team findTeam = findMember.getTeam(); //객체를 테이블에 맞춰 모델링 했던 코드와 달리 이렇게 바로 해당 member의 소속 팀을 찾을 수 있다.

            transaction.commit();
        }catch (Exception e){
            //문제가 발생 하면 catch문에서 예외처리로 트랜젝션 롤백해주기.
            transaction.rollback();
        }finally {
            //다 쓰면 닫아줘야 함.
            entityManager.close();
        }
        //전체가 끝나면 다 쓰면 entityManagerFactory 닫아줘야 함.
        entityManagerFactory.close(); //커넥션 풀링을 위해.

    }
}
