# Core Rest API
Core libraries and dependencies for common function API using `spring-boot-starter-web`.

## How to use it
1. Add this dependency to your project. This project include following dependencies:
   - `spring-boot-starter-actuator`
   - `micrometer-registry-prometheus`
   - `springfox-swagger-ui`
2. To get web mvc configuration only need to add **WebMvcConfigurerSupport** class.
   ```Java
   @Configuration
   @EnableWebMvc
   public class SpringWebMvcConfig extends WebMvcConfigurerSupport {
       //TODO You can override everything you need
   }
   ```
3. You can used any of class that you need it. Main features of this libraries is:
   - `BaseEndpoint`: define common function for all controller. You can add others common function in this class.
