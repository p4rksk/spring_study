package shop.mtcoding.blog.user;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Table(name="user_tb")
@Data
@Entity
public class User {
    @Id //pk 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY)//auto_incremnet
    private int id;

    private String username;
    private String password;
    private String email;

    //카멜 표기법으로 만들면 DB에서 자동으로 create_at으로 인식한다.(언더 스코업 기법)
    private LocalDateTime createdAt;
}
