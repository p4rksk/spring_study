package shop.mtcoding.blog.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;

@RequiredArgsConstructor //final이 붙은 애들에 대한 생성자를 만들어준다.
@Controller
public class UserController {
    private final UserRepository userRepository;
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