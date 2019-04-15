# Core Batch
Core libraries and dependencies for spring batch configuration.

## How to use it
1. Add this dependency to your project.
2. To get batch configuration only need to add **@EnableBatchProcessing** annotation.
   ```Java
   @Configuration
   @EnableBatchProcessing
   public class SpringBatchConfig extends BatchConfigurerSupport {

   }
   ```
3. Create a configuration file with path `config/batch/core-batch-config.properties` with values like the example below.
   ```properties
   core.batch.chunk-size=10
   ```
4. Extends abstract class **BaseBatchServiceImpl** and override other abstract methods.
   ```Java
   @Service
   public class BatchServiceImpl extends BaseBatchServiceImpl {
   
      @Override
      public JobExecutionListenerSupport getListener(JobParameters jobParameters) {
          //TODO Create your implementation of JobExecutionListenerSupport
          return null;
      }
      
      @Override
      public ItemReader<?> getReader(JobParameters jobParameters) {
          //TODO Create your implementation of ItemReader
          return null;
      }
      
      @Override
      public ItemProcessor<?, ?> getProcessor(JobParameters jobParameters) {
          //TODO Create your implementation of ItemProcessor
          return null;
      }
      
      @Override
      public ItemWriter<?> getWriter(JobParameters jobParameters) {
          //TODO Create your implementation of ItemWriter
          return null;
      }
   }
   ```
   If you want to execute batch process, then you only need to call **execute** method only.
