package cz.microshop.users.repository;

import cz.microshop.users.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public interface IUsersRepository extends MongoRepository<User, String> {

    public User getUserByEmail(String email);
    public User getUserByUsername(String username);
}
