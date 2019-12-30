package com.dch.core.security.oauth2.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Class that contains parameter setting of authorization server configuration.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = "core.security.oauth2.authorization")
public class AuthorizationSetting {

    private String identityPrefix = "SECURITY-AUTH";
    private KeyStoreSetting keyStore;

    /**
     * Get identity prefix.
     *
     * @return the identity prefix
     */
    public String getIdentityPrefix() {
        return identityPrefix;
    }

    /**
     * Set identity prefix.
     *
     * @param identityPrefix the identity prefix
     */
    public void setIdentityPrefix(String identityPrefix) {
        this.identityPrefix = identityPrefix;
    }

    /**
     * Get key store.
     *
     * @return the key store
     */
    public KeyStoreSetting getKeyStore() {
        return keyStore;
    }

    /**
     * Set key store.
     *
     * @param keyStore the key store
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
         * Get file name.
         *
         * @return the file name
         */
        public String getFileName() {
            return fileName;
        }

        /**
         * Set file name.
         *
         * @param fileName the file name
         */
        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        /**
         * Get password.
         *
         * @return the password
         */
        public String getPassword() {
            return password;
        }

        /**
         * Set password.
         *
         * @param password the password
         */
        public void setPassword(String password) {
            this.password = password;
        }

        /**
         * Get key pair.
         *
         * @return the key pair
         */
        public String getKeyPair() {
            return keyPair;
        }

        /**
         * Set key pair.
         *
         * @param keyPair the key pair
         */
        public void setKeyPair(String keyPair) {
            this.keyPair = keyPair;
        }
    }
}