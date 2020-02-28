package ru.mano.aviasales.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.mano.aviasales.entity.User;
import ru.mano.aviasales.service.UserManagementService;

import java.util.Collection;

@Component
public class AuthProvider implements AuthenticationProvider {

    @Autowired
    private UserManagementService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //перехватывает POST со страницы login
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        User user = null;
        try {
            user = userService.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
        }

        if (user != null && (user.getUsername().equals(username))) {
            if (!passwordEncoder.matches(password, user.getPassword())) {
                throw new BadCredentialsException("Wrong password");
            }
            /*if (!user.getPassword().equals(password)) {
                throw new BadCredentialsException("Wrong password");
            }*/

            Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

            return new UsernamePasswordAuthenticationToken(user, password, authorities);
        } else
            throw new BadCredentialsException("Username not found");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
