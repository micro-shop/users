package cz.microshop.users.service;

import cz.microshop.users.model.PasswordResetToken;
import cz.microshop.users.model.User;
import cz.microshop.users.repository.IUsersRepository;
import cz.microshop.users.repository.IUsersResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

@Service
public class UsersService {

    @Autowired
    private IUsersRepository usersRepository;
    @Autowired
    private IUsersResetTokenRepository resetTokenRepository;

    public User save(User user) {
        if (user.getUserId() != null) {
            User dbUser = find(user.getUserId());
            if (dbUser != null) user.setId(dbUser.getId());
        }
        if (user.getUserId() == null) user.setUserId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);

        user.getUserRoles().forEach(userRole -> {
            if (userRole.getId() == null) {
                userRole.setRoleId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
            }
        });
        return usersRepository.save(user);
    }

    public PasswordResetToken save(PasswordResetToken passwordResetToken) {
        return resetTokenRepository.save(passwordResetToken);
    }

    public PasswordResetToken findToken(String token) {
        return resetTokenRepository.findByToken(token);
    }

    public void deleteAll() {
        usersRepository.deleteAll();
    }

    public User find(Long userId) {
        return usersRepository.findByUserId(userId);
    }

    public List<User> findAll() {
        return usersRepository.findAll();
    }

    public User findByUsername(String username) {
        return usersRepository.getUserByUsername(username);
    }

    public User findByEmail(String email) {
        return usersRepository.getUserByEmail(email);
    }

    public List<User> create(List<User> orderList) {
        /*orderList.forEach(user -> user.setId(UUID.randomUUID()));*/
        return usersRepository.saveAll(orderList);
    }

    public boolean authenticateCustomer(User user) {
        System.out.println("**In authenticate service class!");
        boolean isAuthenticated = false;
        if (user != null) {
            if (!StringUtils.isEmpty(user.getEmail())
                    && !StringUtils.isEmpty(user.getPassword())) {
                User dbUser = usersRepository.getUserByEmail(user.getEmail());
                if (dbUser != null) {
                    isAuthenticated = user.getPassword().equals(dbUser.getEmail());
                }
            }
        }

        return isAuthenticated;
    }
}
