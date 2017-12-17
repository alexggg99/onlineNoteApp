package agashchuk.SystemSpecificPackage.service;

import agashchuk.SystemSpecificPackage.model.User;

public interface UserService {

    Boolean checkPassword(String rqPassword, String dbPassword);

    void authorize(User user);

    User getCurrentLoginUser();

}
