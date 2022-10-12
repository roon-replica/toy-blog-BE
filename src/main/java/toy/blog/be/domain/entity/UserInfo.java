package toy.blog.be.domain.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toy.blog.be.domain.value.Role;
import toy.blog.be.infra.IdGenerator;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class UserInfo implements UserDetails {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "auth")
    private Role auth;

    @Builder
    public UserInfo(String email, String password, Role auth) {
        this.id = IdGenerator.newId();
        this.email = email;
        this.password = password;
        this.auth = auth;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        //사용자 권한을 콜렉션 형태로 반환
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(auth.name()));

        return roles;
    }

    @Override
    public String getPassword() {
        // 사용자 password 반환
        return password;
    }

    @Override
    public String getUsername() {
        // 사용자 id를 반환
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        // 계정 만료 여부 반환
        return true; // true -> 만료되지 않음
    }

    @Override
    public boolean isAccountNonLocked() {
        // 계정 잠금 여부 반환
        return true; // true -> 잠금되지 않음
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // password 만료 여부 반환
        return true; // true -> 만료되지 않음
    }

    @Override
    public boolean isEnabled() {
        // 계정 사용 가능 여부 반환
        return true; // true -> 계정 사용 가능
    }

}
