# Core Security Oauth2 Authorization Server
Core libraries and dependencies for HTTP security using Oauth2. This library includes configuration for authorization server.

## How to use it
1. Add this dependency to your project.
2. To get authorization server configuration only need to add **AuthorizationServerConfigurerSupport** class.
   ```Java
   @Configuration
   @EnableAuthorizationServer
   public class SpringAuthorizationServerConfig extends AuthorizationServerConfigurerSupport {
       
       @Override
       public TokenStore tokenStore() {
           // TODO you can choose for using {@link JwtTokenStore} or {@link JdbcTokenStore} but default configuration using {@link JdbcTokenStore}
           return ...
       }
   }
   ```
   If you want to change or use {@link JwtTokenStore} from the authorization server configuration, then you just need to override the methods in the **AuthorizationServerConfigurerSupport** class. You can look at this example for using {@link JwtTokenStore}.
   ```Java
   @Configuration
   @EnableAuthorizationServer
   public class SpringAuthorizationServerConfig extends AuthorizationServerConfigurerSupport {
       
       @Override
       public TokenStore tokenStore() {
           return new JwtTokenStore(accessTokenConverter());
       }
       
       /**
        * Bean of JWT Access Token Converter.
        * 
        * @return {@link JwtAccessTokenConverter}
        */
       @Bean
       public JwtAccessTokenConverter accessTokenConverter() {
           KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(
               new ClassPathResource(authorizationSetting.getKeyStore().getFileName()),
               authorizationSetting.getKeyStore().getPassword().toCharArray());

           JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
           converter.setKeyPair(keyStoreKeyFactory.getKeyPair(authorizationSetting.getKeyStore().getKeyPair()));
           return converter;
       }
       
       /**
        * Bean of Token Services. This bean annotated with <code>@Primary</code> so
        * only one bean with the primary attribute is found, then it will be
        * Autowired.
        * 
        * @return {@link DefaultTokenServices}
        */
       @Bean
       @Primary
       public DefaultTokenServices tokenServices() {
           DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
           defaultTokenServices.setTokenStore(tokenStore());
           defaultTokenServices.setSupportRefreshToken(true);
           defaultTokenServices.setClientDetailsService(clientDetailsService());
           defaultTokenServices.setAuthenticationManager(authenticationManager);
           return defaultTokenServices;
       }
       
       /**
        * {@inheritDoc} This implementation using custom access token converter.
        */
       @Override
       protected TokenEnhancerChain tokenEnhancerChain() {
           final TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
           tokenEnhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter()));
           return tokenEnhancerChain;
       }
   }
   ```
3. Create a configuration file with path `config/security/core-security-config.properties` with values like the example below.
   ```Java
   core.security.oauth2.authorization.key-store.file-name=/keystore/mytest.jks # If you are using JwtTokenStore
   core.security.oauth2.authorization.key-store.password=mypass                # If you are using JwtTokenStore
   core.security.oauth2.authorization.key-store.key-pair=mytest                # If you are using JwtTokenStore
   core.security.oauth2.resource.resource-id=myresource
   ```
4. To create file .jks you can following this step:
   - Ex 1: `keytool -genkeypair -alias myauthserver -keyalg RSA -dname "CN=Web Server,OU=Development,O=Me,L=Jakarta,S=DKI,C=ID" -keypass keypassword -keystore my-auth-server.jks -storepass storepassword`
   - Ex 2: `keytool -genkeypair -alias mytest -keyalg RSA -dname "CN=Web Server,OU=Development,O=Me,L=Jakarta,S=DKI,C=ID" -keypass mypass -keystore mytest.jks -storepass mypass`
   - Export public key certificate file: `keytool -export -keystore my-auth-server.jks -alias myauthserver -file my-auth-server.cer`
   - Show Public Key: `keytool -list -rfc --keystore my-auth-server.jks | openssl x509 -inform pem -pubkey`
