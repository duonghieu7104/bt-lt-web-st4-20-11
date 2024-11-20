package vn.iostar.services;

import org.springframework.stereotype.Service;
import vn.iotstar.repository.UserRepository;
import vn.iotstar.entity.User;
import java.util.List;
import java.util.ArrayList;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }
}
