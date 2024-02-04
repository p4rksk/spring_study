package shop.mtcoding.blog.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserRepository {
    // DB에 접근할 수 있는 매니저 객체
    // 스프링이 만들어서 IoC에 넣어둔다.
    // DI에서 꺼내 쓰기만 하면된다.
    private EntityManager em;

    // 생성자 주입 (DI 코드)
    public UserRepository(EntityManager em) {
        this.em = em;
    }
    @Transactional // db에 write 할때는 필수 값을 넣어주는 역할을 하는데 항상 초기화 되도록한다.
    public void save(UserRequest.JoinDto requestDTO){
        Query query = em.createNativeQuery("insert into user_tb(username,password,email, created_at)values (?,?,?, now())");
        query.setParameter(1,requestDTO.getUsername());
        query.setParameter(2,requestDTO.getPassword());
        query.setParameter(3,requestDTO.getEmail());
        query.executeUpdate();// DB로 전송
    }
}
