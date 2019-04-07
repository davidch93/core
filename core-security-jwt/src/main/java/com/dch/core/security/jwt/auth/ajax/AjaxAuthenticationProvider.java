package com.dch.core.security.jwt.auth.ajax;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Class provider that used to Verify user credentials against database, LDAP or
 * some other system which holds the user data, create UserContext and populate
 * it with user data you need, and upon successful authentication delegate
 * creation of JWT Token.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @see org.springframework.security.authentication.AuthenticationProvider
 * @since 1.0.0
 */
@Component
public class AjaxAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder passwordEncoder;

    public AjaxAuthenticationProvider(UserDetailsService userDetailsService, BCryptPasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails == null)
            throw new UsernameNotFoundException("User not found: " + username);

        if (!passwordEncoder.matches(password, userDetails.getPassword()))
            throw new BadCredentialsException("Authentication Failed. Username or Password not valid.");

        if (userDetails.getAuthorities() == null || userDetails.getAuthorities().isEmpty())
            throw new InsufficientAuthenticationException("User has no roles assigned");

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
