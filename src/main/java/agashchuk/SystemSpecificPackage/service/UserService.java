package agashchuk.SystemSpecificPackage.service;

import agashchuk.SystemSpecificPackage.model.RegistrationRequest;
import agashchuk.SystemSpecificPackage.model.User;
import agashchuk.SystemSpecificPackage.model.UserState;

import java.util.List;

public interface UserService {

    Boolean checkPassword(String rqPassword, String dbPassword);

    void authorize(User user);

    boolean isUserLogged();

    User getCurrentlyLoggedUser();

    User findUserByUsername(String username);

    User createUser(RegistrationRequest request);

    List<User> findUsersForActivation(UserState userState);

    User findUserByActivationCode(String activationCode);

    User save(User user);

    List<User> findAll();

}
