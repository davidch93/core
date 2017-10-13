# Core Async
Core libraries and dependencies for spring asynchronous configuration.

## How to use it
1. Add this dependency to your project.
2. To get asynchronous configuration only need to add **AsyncConfigurerSupport** class.
   ```Java
   @Configuration
   @EnableAsync
   public class SpringAsyncConfig extends AsyncConfigurerSupport {

   }
   ```
   If you want to change the asynchronous configuration, then you just need to override the methods in the **AsyncConfigurerSupport** class.
3. Create a configuration file with path `config/async/core-async-config.properties` with values like the example below.
   ```Java
   core.async.executor.core-pool-size=1
   core.async.executor.keep-alive-seconds=60
   core.async.executor.max-pool-size=500
   core.async.executor.queue-capacity=500
   core.async.executor.thread-name-prefix=ASYNC-EXECUTOR
   ```
