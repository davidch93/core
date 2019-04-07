package com.dch.core.security.oauth2;

import com.dch.core.security.oauth2.service.SecurityDetailsService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.AbstractOAuth2SecurityExceptionHandler;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * This entry point is called once the request missing their authentication.
 * <p>
 * For more detail look at {@link OAuth2AuthenticationEntryPoint}.
 * </p>
 *
 * @author David.Christianto
 * @version 2.0.0
 * @see org.springframework.security.oauth2.provider.error.AbstractOAuth2SecurityExceptionHandler
 * @see org.springframework.security.web.AuthenticationEntryPoint
 * @since 1.0.0
 */
public class RestAuthenticationEntryPoint extends AbstractOAuth2SecurityExceptionHandler
        implements AuthenticationEntryPoint {

    private String typeName = OAuth2AccessToken.BEARER_TYPE;
    private String realmName = "oauth";
    private final SecurityDetailsService securityDetailsService;

    public RestAuthenticationEntryPoint(SecurityDetailsService securityDetailsService) {
        this.securityDetailsService = securityDetailsService;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        doHandle(request, response, authException);
    }

    @Override
    protected ResponseEntity<?> enhanceResponse(ResponseEntity<?> response, Exception exception) {
        HttpHeaders headers = response.getHeaders();
        String existing = null;
        if (headers.containsKey("WWW-Authenticate")) {
            existing = this.extractTypePrefix(Objects.requireNonNull(headers.getFirst("WWW-Authenticate")));
        }

        String value = String.format("%s realm=\"%s\"", typeName, realmName);
        if (existing != null) {
            value += String.format(", %s", existing);
        }

        HttpHeaders update = new HttpHeaders();
        update.putAll(response.getHeaders());
        update.set("WWW-Authenticate", value);

        return new ResponseEntity<>(
                securityDetailsService.getSecurityResponseBuilder((OAuth2Exception) response.getBody()).build(), update,
                response.getStatusCode());
    }

    /**
     * Method used to extract type prefix header.
     *
     * @param header {@link String}
     * @return Type prefix.
     */
    private String extractTypePrefix(String header) {
        String existing = header;
        String[] tokens = header.split(" +");
        if (tokens.length > 1 && !tokens[0].endsWith(",")) {
            existing = StringUtils.arrayToDelimitedString(tokens, " ").substring(header.indexOf(" ") + 1);
        }
        return existing;
    }

    public void setRealmName(String realmName) {
        this.realmName = realmName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
