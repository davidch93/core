# Core Security Oauth2 Resource
Core libraries and dependencies for HTTP security using Oauth2. This library is only used as resource server configuration.

## How to use it
1. Add this dependency to your project.
2. To get resource server configuration only need to add **ResourceServerConfigurerSupport** class.
   ```Java
   @Configuration
   @EnableResourceServer
   public class SpringResourceServerConfig extends ResourceServerConfigurerSupport {
       
       @Override
       protected ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry configureAuthorization(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests) throws Exception {
           // TODO your custom HTTPSecurity
           return authorizeRequests.antMatchers("/transaction").hasAuthority("ADMIN");
       }
   }
   ```
   If you want to change or use **JwtTokenStore** from the resource server configuration, then you just need to override the methods in the **ResourceServerConfigurerSupport** class. You can look at this example for using **JwtTokenStore**.
   ```Java
   @Configuration
   @EnableResourceServer
   public class SpringResourceServerConfig extends ResourceServerConfigurerSupport {
       
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
           try {
               JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
               Resource resource = new ClassPathResource("public.txt");
               String publicKey = IOUtils.toString(resource.getInputStream());
               converter.setVerifierKey(publicKey);
               return converter;
           } catch (final IOException e) {
               throw new RuntimeException(e);
           }
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
           return defaultTokenServices;
       }
       
       @Override
       public void configure(ResourceServerSecurityConfigurer resources) {
           super.configure(resources);
           resources.tokenServices(tokenServices());
       }
       
       @Override
       protected ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry configureAuthorization(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests) throws Exception {
           // TODO your custom HTTPSecurity
           return authorizeRequests.antMatchers("/transaction").hasAuthority("ADMIN");
       }
   }
   ```
3. Create a configuration file with path `config/security/core-security-config.properties` with values like the example below.
   ```properties
   core.security.oauth2.authorization.key-store.file-name=/keystore/mytest.jks # If you are using JwtTokenStore
   core.security.oauth2.authorization.key-store.password=mypass                # If you are using JwtTokenStore
   core.security.oauth2.authorization.key-store.key-pair=mytest                # If you are using JwtTokenStore
   core.security.oauth2.resource.resource-id=myresource
   ```
