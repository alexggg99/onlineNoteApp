package agashchuk.SystemSpecificPackage.service;

import agashchuk.SystemSpecificPackage.model.RegistrationRequest;
import agashchuk.SystemSpecificPackage.model.Role;
import agashchuk.SystemSpecificPackage.model.User;
import agashchuk.SystemSpecificPackage.model.UserState;
import agashchuk.SystemSpecificPackage.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Boolean checkPassword(String rqPassword, String dbPassword) {
        return rqPassword.equals(dbPassword);
    }

    @Override
    public void authorize(User user) {
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().name().toUpperCase()));

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, "N/A", grantedAuthorityList);
        SecurityContextHolder.getContext().setAuthentication(token);
    }

    @Override
    public boolean isUserLogged() {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            if (SecurityContextHolder.getContext().getAuthentication().getPrincipal()
                    instanceof org.springframework.security.core.userdetails.User) {
                return SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null;
            }
        }

        return false;
    }

    @Override
    public User getCurrentlyLoggedUser() {
        if (isUserLogged()) {
            org.springframework.security.core.userdetails.User user =
                    (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return userRepository.findByUsername(user.getUsername());
        }
        return null;
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User createUser(RegistrationRequest request) {
        User user = new User(request.getUsername(), request.getUsername(), request.getPassword(), request.getEmail());
        user.setRole(Role.User);
        user.setState(UserState.Blocked);
        String code = UUID.randomUUID().toString();
        user.setActivationCode(code);
        return userRepository.save(user);
    }

    @Override
    public List<User> findUsersForActivation(UserState userState) {
        return userRepository.findByActivationCodeNotNullAndStateEquals(userState);
    }

    @Override
    public User findUserByActivationCode(String activationCode) {
        return userRepository.findByActivationCode(activationCode);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAllOrOrderByUsername();
    }
}
