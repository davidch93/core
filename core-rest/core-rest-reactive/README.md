# Core Rest Reactive
Core libraries and dependencies for common function API using `spring-boot-starter-webflux`.
By default this library use [Prometheus](https://prometheus.io/) to publish **JMX** into metrics and can be configured use `management.endpoints.web.*`
For more information you can follow this [link](https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-endpoints.html).
This project include following dependencies:
- `spring-boot-starter-actuator`
- `micrometer-registry-prometheus`
- `springfox-swagger-ui`

## How to use it
1. Add this dependency to your project.
2. To get web mvc configuration only need to add **WebFluxConfigurerSupport** class.
   ```Java
   @Configuration
   @EnableWebFlux
   public class SpringWebFluxConfig extends WebFluxConfigurerSupport {
       //TODO You can override everything you need
   }
   ```
3. You can used any of class that you need it. Main features of this libraries is:
   - `BaseEndpoint`: define common function for all controller. You can add others common function in this class.
