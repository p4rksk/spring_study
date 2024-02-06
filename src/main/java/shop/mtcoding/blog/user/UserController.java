package shop.mtcoding.blog.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;


/*책임
    1, 요청처리
    2. 데이터 유효성 검사
 */
@RequiredArgsConstructor //final이 붙은 애들에 대한 생성자를 만들어준다.
@Controller
public class UserController {//생성자가 만들어져 있는 상태임
    private final UserRepository userRepository;
    private final HttpSession session;
    @PostMapping("/login")
    public String login(UserRequest.LoginDTO requestDto) {
        System.out.println(requestDto);

        if (requestDto.getUsername().length() < 3) {//유효성 검사
            return "error/404"; //viewResolver 설정이 돼있음.
        }

        User user = userRepository.findByUsernameAndPassword(requestDto);
        if (user == null) {// 조회 안됨 (401)
            return "error/401";
        } else { // 조회 됐음 (인증됨)
            session.setAttribute("sessionUser", user);//락카에 담음
        }
        return "redirect:/"; //컨트롤러가 존재하면 무조건 redirect 외우기
    }
    @PostMapping("/join")
    public String join(UserRequest.JoinDto requestDto) {
        System.out.println(requestDto);

        userRepository.save(requestDto); //모델에 위임
        return "redirect:/loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    @GetMapping("/user/updateForm")
    public String updateForm() {
        return "user/updateForm";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }
}
