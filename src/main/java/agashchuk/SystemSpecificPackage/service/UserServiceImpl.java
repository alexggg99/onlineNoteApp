package agashchuk.SystemSpecificPackage.service;

import agashchuk.SystemSpecificPackage.model.User;
import agashchuk.SystemSpecificPackage.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public User getCurrentLoginUser() {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User) {
                return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            }
        }

        return null;
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
