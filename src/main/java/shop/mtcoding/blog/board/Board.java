package shop.mtcoding.blog.board;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Table(name="board_tb")
@Data
@Entity // 테이블 생성하기 위해 필요한 어노테이션
public class Board { // User 1 -> Board N
    @Id // PK 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment 전략
    private int id;
    private String title;
    private String content;

    @Column(name = "user_id") //데이터베이스 쿼리에 칼럼명가 다를 경우 이 어노테이션 사용해서 둘이 매핑 시킴
    private int userId; // 테이블에 만들어 질때 user_id

    private LocalDateTime createdAt;
}