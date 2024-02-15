package dk.footballbook.footballbookstart.web;

import dk.footballbook.footballbookstart.domain.user.User;
import dk.footballbook.footballbookstart.svc.AuthService;
import dk.footballbook.footballbookstart.web.dto.auth.SignupDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor // final 필드 DI
public class AuthController {

    @Autowired
    private final AuthService authService;

    @GetMapping("/auth/signin")
    public String signinForm() {
        return "auth/signin";
    }

    @GetMapping("/auth/signup")
    public String signupForm() {
        return "auth/signup";
    }

    //시큐리티가 POST를 막아서 진행되지 않음 (CSRF 토큰을 시큐리티가 검사해봤더니 없어서.)
    @PostMapping("/auth/signup")
    public String signup(@Valid SignupDto signupDto, BindingResult bindingResult) {

            // user -> signupDto
            User user = signupDto.toEntity();
            User userEntity = authService.signin(user);
//            System.out.println("userEntity = " + userEntity);
//            log.info(user.toString());
            return "auth/signin";
        }
        //회원가입 validation 전처리
}
