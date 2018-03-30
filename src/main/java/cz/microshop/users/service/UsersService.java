package cz.microshop.users.service;

import cz.microshop.users.model.User;
import cz.microshop.users.repository.IUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UsersService {

    @Autowired
    private IUsersRepository usersRepository;

    public void save(User user) {
        usersRepository.save(user);
    }

    public void deleteAll() {
        usersRepository.deleteAll();
    }

    public List<User> findAll() {
        return usersRepository.findAll();
    }

    public User findByUsername(String username) {
        return usersRepository.getUserByUsername(username);
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
