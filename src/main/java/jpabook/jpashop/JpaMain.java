package jpabook.jpashop;

import jpabook.jpashop.domain.Member;

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
