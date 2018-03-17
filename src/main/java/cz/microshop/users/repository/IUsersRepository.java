package cz.microshop.users.repository;

import cz.microshop.users.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface IUsersRepository extends MongoRepository<User, UUID> {

    public User getUserByEmail(String email);
}
