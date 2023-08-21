package onlinelibrary.user.service;

import lombok.RequiredArgsConstructor;
import onlinelibrary.user.domain.LocalUser;
import onlinelibrary.user.repo.UserRepo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepo dao;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        LocalUser localUser = dao.findByUsername(userName);
        if (localUser == null) {
            throw new UsernameNotFoundException("Unknown user: "+ userName);
        }
        return User.builder()
                .username(localUser.getUsername())
                .password(localUser.getPassword())
                .roles(localUser.getRole())
                .build();
    }
}
