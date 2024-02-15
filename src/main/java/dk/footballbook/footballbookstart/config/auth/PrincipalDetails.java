package dk.footballbook.footballbookstart.config.auth;

import dk.footballbook.footballbookstart.domain.user.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Data
public class PrincipalDetails implements UserDetails, OAuth2User {

    private final long serialVersionUID=1L;

    private User user;
    private Map<String, Object> attributes;

    public PrincipalDetails(User user) {
        this.user = user;
    }

    public PrincipalDetails(User user, Map<String,Object> attributes) {
        this.user = user;
    }
    @Override
    public Map<String, Object> getAttribute(String name) {
        return attributes; // {id: xxx, name:xxx, email:xxx}
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collector = new ArrayList<>();
        collector.add(() -> { return user.getRole();});
        return collector;
        //권한 가져오는 함수, 왜 컬렉션인가? 권한이 한개가 아닐수도 있기 때문
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; //만료 안되었는지 확인
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; //계정 잠긴지 확인
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; //비밀번호 만기 확인
    }

    @Override
    public boolean isEnabled() {
        return true; // 계정 활성화 확인
    }


}
