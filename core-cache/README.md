# Core Cache
Core libraries and dependencies for all process caching.

## How to use it
1. Add this dependency to your project.
2. To get asynchronous configuration only need to add **CachingConfigurerSupport** class.
   ```Java
   @Configuration
   @EnableCaching
   public class SpringCachingConfig extends CachingConfigurerSupport {
   
       @Bean
       @Override
       public CacheManager cacheManager() {
           final SimpleCacheManager cacheManager = new SimpleCacheManager();
           cacheManager.setCaches(Arrays.asList(
               new ConcurrentMapCache("country")
           ));
           return cacheManager;
       }
   }
   ```
   If you don't want to change default configuration, then you just need to override the method cacheManager. If you want to change the cache configuration, then you just need to override the methods in the **CachingConfigurerSupport** class.
