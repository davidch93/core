# Core Batch
Core libraries and dependencies for spring batch configuration.

## How to use it
1. Add this dependency to your project.
2. To get batch configuration only need to add **@EnableBatchProcessing** annotation.
   ```Java
   @Configuration
   @EnableBatchProcessing
   public class SpringBatchConfig {

   }
   ```
3. Create a configuration file with path `config/batch/core-batch-config.properties` with values like the example below.
   ```Java
   core.batch.chunk-size=10
   ```
4. Extends abstract class **BaseBatchServiceImpl** and override other abstract methods.
   ```Java
   @Service
   public class BatchServiceImpl extends BaseBatchServiceImpl {
   
      @Override
      public JobExecutionListenerSupport getListener(JobParameters jobParameters) {
          return ...
      }
      
      @Override
      public ItemReader<?> getReader(JobParameters jobParameters) {
          return ...
      }
      
      @Override
      public ItemProcessor<?, ?> getProcessor(JobParameters jobParameters) {
          return ...
      }
      
      @Override
      public ItemWriter<?> getWriter(JobParameters jobParameters) {
          return ...
      }
   }
   ```
   If you want to execute batch process, then you only need to call **execute** method only.
