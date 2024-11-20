package vn.iostar.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import vn.iostar.model.UserInfo;
import vn.iostar.model.UserInfoRepository;

@Service
public class UserInfoDetailService implements UserDetailsService {

    private final UserInfoRepository userInfoRepository;

    public UserInfoDetailService(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfoOptional = userInfoRepository.findByName(username);

        if (userInfoOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        UserInfo userInfo = userInfoOptional.get();
        return User.builder()
                .username(userInfo.getName())
                .password(userInfo.getPassword()) // Đảm bảo mật khẩu đã được mã hóa
                .roles(userInfo.getRoles().split(",")) // Nếu lưu vai trò dưới dạng chuỗi phân tách
                .build();
    }
}
