package cz.microshop.users.repository;

import cz.microshop.users.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public interface UsersRepository extends MongoRepository<User, String> {

    User getUserByEmail(String email);
    User getUserByUsername(String username);
    User findByUserId(Long userId);
}
