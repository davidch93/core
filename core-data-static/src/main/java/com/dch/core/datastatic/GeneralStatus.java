package com.dch.core.datastatic;

/**
 * List of custom HTTP response status code in application. For more information:<br>
 * 1. Successful API Responses = SAR<br>
 * 2. Common API Error Responses = CAER<br>
 * 3. Security API Error Responses = SAER
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
public enum GeneralStatus {

    SUCCESS("SAR01", "SUCCESS", "msg.httpstatus.success"),
    TOKEN_CREATED("SAR02", "TOKEN_CREATED", "msg.httpstatus.tokencreated"),
    TOKEN_REVOKED("SAR03", "TOKEN_REVOKED", "msg.httpstatus.tokenrevoked"),

    ARGUMENT_NOT_VALID("CAER01", "ARGUMENT_NOT_VALID", "msg.httpstatus.argumentnotvalid"),
    BAD_REQUEST("CAER02", "BAD_REQUEST", "msg.httpstatus.badrequest"),
    INTERNAL_SERVER_ERROR("CAER03", "INTERNAL_SERVER_ERROR", "msg.httpstatus.internalservererror"),
    MESSAGE_NOT_READABLE("CAER04", "MESSAGE_NOT_READABLE", "msg.httpstatus.messagenotreadable"),
    METHOD_NOT_ALLOWED("CAER05", "METHOD_NOT_ALLOWED", "msg.httpstatus.methodnotallowed"),
    NOT_FOUND("CAER06", "NOT_FOUND", "msg.httpstatus.notfound"),
    UNSUPPORTED_MEDIA_TYPE("CAER07", "UNSUPPORTED_MEDIA_TYPE", "msg.httpstatus.unsupportedmediatype"),
    NOT_ACCEPTABLE("CAER08", "NOT_ACCEPTABLE", "msg.httpstatus.notacceptable"),

    AUTHENTICATION_FAILED("SAER01", "AUTHENTICATION_FAILED", "msg.httpstatus.authenticationfailed"),
    AUTHENTICATION_METHOD_NOT_SUPPORTED("SAER02", "AUTHENTICATION_METHOD_NOT_SUPPORTED", "msg.httpstatus" +
            ".authenticationmethodnotsupported"),
    FORBIDDEN("SAER03", "FORBIDDEN", "msg.httpstatus.forbidden"),
    INVALID_AUTHENTICATION("SAER04", "INVALID_AUTHENTICATION", "msg.httpstatus.invalidauthentication"),
    INVALID_CLIENT("SAER05", "INVALID_CLIENT", "msg.httpstatus.invalidclient"),
    INVALID_GRANT("SAER06", "INVALID_GRANT", "msg.httpstatus.invalidgrant"),
    INVALID_REQUEST("SAER07", "INVALID_REQUEST", "msg.httpstatus.invalidrequest"),
    INVALID_SCOPE("SAER08", "INVALID_SCOPE", "msg.httpstatus.invalidscope"),
    INVALID_TOKEN("SAER09", "INVALID_TOKEN", "msg.httpstatus.invalidtoken"),
    INVALID_USERNAME_PASSWORD("SAER10", "INVALID_USERNAME_PASSWORD", "msg.httpstatus.invalidusernamepassword"),
    REDIRECT_URI_MISMATCH("SAER11", "REDIRECT_URI_MISMATCH", "msg.httpstatus.redirectmismatch"),
    TOKEN_EXPIRED("SAER12", "TOKEN_EXPIRED", "msg.httpstatus.tokenexpired"),
    UNAUTHORIZED_CLIENT("SAER13", "UNAUTHORIZED_CLIENT", "msg.httpstatus.unauthorizedclient"),
    UNSUPPORTED_GRANT_TYPE("SAER14", "UNSUPPORTED_GRANT_TYPE", "msg.httpstatus.unsupportedgranttype"),
    UNSUPPORTED_RESPONSE_TYPE("SAER15", "UNSUPPORTED_RESPONSE_TYPE", "msg.httpstatus.unsupportedresponsetype");

    private final String code;
    private final String parameter;
    private final String value;

    GeneralStatus(String code, String parameter, String value) {
        this.code = code;
        this.parameter = parameter;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public String getParameter() {
        return parameter;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "GeneralStatus{" +
                "code='" + code + '\'' +
                ", parameter='" + parameter + '\'' +
                ", value='" + value + '\'' +
                "} " + super.toString();
    }}
