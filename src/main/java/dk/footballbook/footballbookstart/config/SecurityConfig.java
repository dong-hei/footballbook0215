package dk.footballbook.footballbookstart.config;

import dk.footballbook.footballbookstart.config.oauth.OAuth2DetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
@EnableWebSecurity //해당파일로 시큐리티를 활성화 시킨다.
@Configuration //IoC
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final OAuth2DetailsService oAuth2DetailsService;
    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http); // 얘를 지우면 기존 시큐리티가 가지고 있는 기능이 비활성화 된다.
        http.csrf().disable();     // CSRF 토큰을 비활성화 시켜서 사용하지 않을것이다.
        http.authorizeRequests()
                .antMatchers("/", "/user/**", "/image/**", "/subscribe/**", "/comment/**", "/api/**")
                .authenticated() // 해당 주소는 무조건 인증이 필요하고
                .anyRequest().permitAll() // 나머지는 인증 안해도 된다.
                .and()
                .formLogin()//form 태그가 있고 input 태그가 있는곳으로 로그인
                .loginPage("/auth/signin") //인증이 필요한 페이지는 여기로 이동, GET방식
                .loginProcessingUrl("/auth/signin") //누군가가 POST방식으로 로그인 요청 -> 스프링 시큐가 로그인 프로세스 진행
                .defaultSuccessUrl("/")// 성공하면 / 경로로 이동
                .and()
                .oauth2Login() // form 로그인도 하지만, oauth2로그인도 받는다.
                .userInfoEndpoint() // oauth2로그인을 하면 최종 응답을 회원정보로 바로 받을 수 있다.
                .userService(oAuth2DetailsService); // DefaultOAuth2UserService 상속하면 형이 맞지않아서 나는 오류는 없다.
    }
}
