package agashchuk.SystemSpecificPackage.repo;

import agashchuk.SystemSpecificPackage.model.User;
import agashchuk.SystemSpecificPackage.model.UserState;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    User findById(Long id);
    User findByUsername(String username);
    List<User> findByActivationCodeNotNullAndStateEquals(UserState userState);
    User findByActivationCode(String activationCode);
}