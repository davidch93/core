package com.dch.core.security.oauth2.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Class that contains parameter setting of authorization server configuration.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
@Configuration
@PropertySource("classpath:config/security/core-oauth2-config.properties")
@ConfigurationProperties(prefix = "core.security.oauth2.authorization")
public class AuthorizationSetting {

    private String identityPrefix = "SECURITY-AUTH";
    private KeyStoreSetting keyStore;

    /**
     * @return the identityPrefix
     */
    public String getIdentityPrefix() {
        return identityPrefix;
    }

    /**
     * @param identityPrefix the identityPrefix to set
     */
    public void setIdentityPrefix(String identityPrefix) {
        this.identityPrefix = identityPrefix;
    }

    /**
     * @return the keyStore
     */
    public KeyStoreSetting getKeyStore() {
        return keyStore;
    }

    /**
     * @param keyStore the keyStore to set
     */
    public void setKeyStore(KeyStoreSetting keyStore) {
        this.keyStore = keyStore;
    }

    /**
     * Class that contains parameter KeyStore.
     *
     * @author David.Christianto
     * @version 2.0.0
     * @since 1.0.0
     */
    public static class KeyStoreSetting {

        private String fileName;
        private String password;
        private String keyPair;

        /**
         * @return the fileName
         */
        public String getFileName() {
            return fileName;
        }

        /**
         * @param fileName the fileName to set
         */
        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        /**
         * @return the password
         */
        public String getPassword() {
            return password;
        }

        /**
         * @param password the password to set
         */
        public void setPassword(String password) {
            this.password = password;
        }

        /**
         * @return the keyPair
         */
        public String getKeyPair() {
            return keyPair;
        }

        /**
         * @param keyPair the keyPair to set
         */
        public void setKeyPair(String keyPair) {
            this.keyPair = keyPair;
        }
    }
}