package agashchuk.SystemSpecificPackage.service;

import agashchuk.SystemSpecificPackage.model.User;
import agashchuk.SystemSpecificPackage.model.UserState;
import agashchuk.SystemSpecificPackage.repo.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.*;

public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user =userRepository.findByUsername(s);
        if (user == null || user.getState() == UserState.Blocked) {
            throw new UsernameNotFoundException("user " + s + "  not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), getAuthority(user));
    }

    private Collection<GrantedAuthority> getAuthority(User user) {
        return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_" + user.getRole().name().toUpperCase());
    }
}
