package shop.mtcoding.blog.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository // IoC에 new할 수 있고, 데이터 베이스 연동
public class UserRepository {

    // DB에 접근할 수 있는 매니저 객체
    // 스프링이 만들어서 IoC에 넣어둔다.
    // DI에서 꺼내 쓰기만 하면된다.
    private EntityManager em;

    // 생성자 주입 (DI 코드)
    //DI:의존성 주입
    public UserRepository(EntityManager em) {
        this.em = em;
    }

    @Transactional // 값을 넣어주는 역할을 하는데 항상 초기화 되도록한다.
    public void save(UserRequest.JoinDto requestDTO) { // Query를 직접 짬
        Query query = em.createNativeQuery("insert into user_tb (username, password, email) values (?, ?, ?)");
        query.setParameter(1, requestDTO.getUsername());
        query.setParameter(2, requestDTO.getPassword());
        query.setParameter(3, requestDTO.getEmail());

        query.executeUpdate(); // DB로 전송
    }

    @Transactional
    public User findByUsernameAndPassword(UserRequest.LoginDTO requestDTO){ // 모델 조회 요청
        Query query = em.createNativeQuery("select * from user_tb where username=? and password=?", User.class);
        query.setParameter(1, requestDTO.getUsername());
        query.setParameter(2, requestDTO.getPassword());

        User user =(User) query.getSingleResult(); //DB 조회 결과를 Entity로 받음
        return user;

    }

}

