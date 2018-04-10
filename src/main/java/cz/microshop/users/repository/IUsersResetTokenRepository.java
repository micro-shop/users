package cz.microshop.users.repository;

import cz.microshop.users.model.PasswordResetToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public interface IUsersResetTokenRepository extends MongoRepository<PasswordResetToken, String> {

    PasswordResetToken findByToken(String token);
}
