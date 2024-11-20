package vn.iostar.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import vn.iostar.model.UserInfo;
import vn.iostar.model.UserInfoRepository;



@Service
public class UserService {
    private final UserInfoRepository repository;
    private final PasswordEncoder passwordEncoder;

    // Constructor Injection
    public UserService(UserInfoRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    // Thêm user vào database
    public String addUser(UserInfo userInfo) {
        // Mã hóa mật khẩu trước khi lưu
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        repository.save(userInfo); // Lưu user vào database
        return "Thêm user thành công!";
    }
}
