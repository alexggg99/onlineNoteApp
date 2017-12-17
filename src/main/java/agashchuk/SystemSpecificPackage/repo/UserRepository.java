package agashchuk.SystemSpecificPackage.repo;

import agashchuk.SystemSpecificPackage.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findById(Long id);
    User findByUsername(String username);
}