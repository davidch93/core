package com.dch.core.security.oauth2;

import com.dch.core.datastatic.response.GenericResponse;
import com.dch.core.security.oauth2.service.SecurityDetailsService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This entry point is called once the request missing their authentication.
 * This class extends {@link AbstractRestSecurityExceptionHandler} and
 * implements {@link AuthenticationEntryPoint}.
 * <p>
 * For more detail look at {@link OAuth2AuthenticationEntryPoint}.
 * </p>
 *
 * @author David.Christianto
 * @version 1.0.0
 * @updated Jun 11, 2017
 * @since 1.0.0-SNAPSHOT
 */
public class RestAuthenticationEntryPoint extends AbstractRestSecurityExceptionHandler
        implements AuthenticationEntryPoint {

    private String typeName = OAuth2AccessToken.BEARER_TYPE;
    private String realmName = "oauth";
    private SecurityDetailsService securityDetailsService;

    public RestAuthenticationEntryPoint(SecurityDetailsService securityDetailsService) {
        this.securityDetailsService = securityDetailsService;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        doHandle(request, response, authException);
    }

    @Override
    protected ResponseEntity<GenericResponse> enhanceResponse(ResponseEntity<OAuth2Exception> response,
                                                              Exception exception) {

        HttpHeaders headers = response.getHeaders();
        String existing = null;
        if (headers.containsKey("WWW-Authenticate")) {
            existing = extractTypePrefix(headers.getFirst("WWW-Authenticate"));
        }
        StringBuilder builder = new StringBuilder();
        builder.append(typeName + " ");
        builder.append("realm=\"" + realmName + "\"");
        if (existing != null) {
            builder.append(", " + existing);
        }
        HttpHeaders update = new HttpHeaders();
        update.putAll(response.getHeaders());
        update.set("WWW-Authenticate", builder.toString());

        return new ResponseEntity<GenericResponse>(
                securityDetailsService.getSecurityResponseBuilder(response.getBody()).build(), update,
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
        String[] tokens = existing.split(" +");
        if (tokens.length > 1 && !tokens[0].endsWith(",")) {
            existing = StringUtils.arrayToDelimitedString(tokens, " ").substring(existing.indexOf(" ") + 1);
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
