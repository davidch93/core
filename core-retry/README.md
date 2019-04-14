# Core Retry
Core libraries and dependencies for spring retry configuration.

## How to use it
1. Add this dependency to your project.
2. To get retry configuration only need to add **RetryConfigurerSupport** class.
   ```Java
   @Configuration
   @EnableRetry
   public class SpringRetryConfig extends RetryConfigurerSupport {

   }
   ```
   If you want to change the retry configuration, then you just need to override the methods in the **RetryConfigurerSupport** class.
3. Create a configuration file with path `config/retry/core-retry-config.properties` with values like the example below.
   ```properties
   core.retry.identity-prefix=RETRY
   core.retry.back-of-period=1000
   core.retry.max-attempts=10
   ```
