package com.dch.core.security.jwt.auth;

import com.dch.core.security.jwt.model.token.RawAccessJwtToken;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * An {@link org.springframework.security.core.Authentication} implementation
 * that is designed for simple presentation of JwtToken.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @see org.springframework.security.authentication.AbstractAuthenticationToken
 * @since 1.0.0
 */
public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private RawAccessJwtToken rawAccessToken;
    private UserDetails userDetails;

    public JwtAuthenticationToken(RawAccessJwtToken unsafeToken) {
        super(null);
        this.rawAccessToken = unsafeToken;
        this.setAuthenticated(false);
    }

    public JwtAuthenticationToken(UserDetails userDetails, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.eraseCredentials();
        this.userDetails = userDetails;
        super.setAuthenticated(true);
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        if (authenticated)
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");

        super.setAuthenticated(false);
    }

    @Override
    public Object getCredentials() {
        return rawAccessToken;
    }

    @Override
    public Object getPrincipal() {
        return userDetails;
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        rawAccessToken = null;
    }
}
