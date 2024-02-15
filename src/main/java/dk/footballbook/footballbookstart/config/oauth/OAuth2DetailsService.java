package dk.footballbook.footballbookstart.config.oauth;

import dk.footballbook.footballbookstart.config.auth.PrincipalDetails;
import dk.footballbook.footballbookstart.domain.user.User;
import dk.footballbook.footballbookstart.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

/**
 * 페이스북 -> ID,PW -> 페이스북 -> 응답 -> 이 응답을 처리하는곳이 OAuth2DetailsService
 * 즉 회원정보를 이곳에서 받는다.
 */
@RequiredArgsConstructor
@Service
public class OAuth2DetailsService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User Oauth2user = super.loadUser(userRequest);
//        System.out.println("Oauth2user.getAttribute() 값 = " + Oauth2user.getAttributes());

        Map<String, Object> userInfo = Oauth2user.getAttributes();
        String username = "facebook_"+(String) userInfo.get("id");
        String password = new BCryptPasswordEncoder().encode(UUID.randomUUID().toString());
        String name = (String) userInfo.get("name");
        String email = (String) userInfo.get("email");

        //유저 정보가 있는지 없는지 확인할 필요가 있다. 확인하지 않으면 user를 계속 생성한다.
        User userEntity = userRepository.findByUsername(username);

        if(userEntity == null){
            //페북 최초 로그인
            User user = User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .name(name)
                    .role("ROLE_USER")
                    .build();

            return new PrincipalDetails(userRepository.save(user)); // 유저 정보 저장

        } else{
            // 이미 회원가입 되어있다.
            return new PrincipalDetails(userEntity); // 형변환을 PrincipalDetails implements 를 통해 해결했다.
        }

    } // 응답을 처리할때 이 함수를 탐
}
