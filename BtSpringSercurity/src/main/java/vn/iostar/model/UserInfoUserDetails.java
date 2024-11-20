package vn.iostar.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserInfoUserDetails implements UserDetails {

   
	private static final long serialVersionUID = 1L;
	private final String username;
    private final String password;
    private final List<GrantedAuthority> authorities;

    // Constructor để nhận thông tin từ UserInfo
    public UserInfoUserDetails(UserInfo userInfo) {
        this.username = userInfo.getName();
        this.password = userInfo.getPassword();
        // Chuyển roles từ UserInfo thành danh sách GrantedAuthority
        this.authorities = List.of(new SimpleGrantedAuthority(userInfo.getRoles()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
