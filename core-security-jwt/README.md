# Core Security JWT
Core libraries and dependencies for HTTP security using JSON Web Token.

## How to use it
1. Add this dependency to your project.
2. To get security configuration only need to add **WebSecurityConfigurerSupport** class.
   ```Java
   @Configuration
   @EnableWebSecurity
   public class SpringAsyncConfig extends WebSecurityConfigurerSupport {
       
       @Override
       protected List<String> requestAntPathsToPermitAll() {
           // TODO your URL to permit
           return ...
       }
       
       @Override
       protected ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry configureAuthorization(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests) throws Exception {
           // TODO your custom HTTPSecurity
           return authorizeRequests.
               .antMatchers("/transaction").hasAuthority("ADMIN");
       }
   }
   ```
   If you want to change the security configuration, then you just need to override the methods in the **WebSecurityConfigurerSupport** class.
