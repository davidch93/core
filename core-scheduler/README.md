# Core Scheduler
Core libraries and dependencies for spring scheduler configuration.

## How to use it
1. Add this dependency to your project.
2. To get scheduler configuration only need to add **SchedulerConfigurerSupport** class.
   ```Java
   @Configuration
   @EnableScheduling
   public class SpringSchedulerConfig extends SchedulerConfigurerSupport {

   }
   ```
   If you want to change the scheduler configuration, then you just need to override the methods in the **SchedulerConfigurerSupport** class.
3. Create a configuration file with path `config/scheduler/core-scheduler-config.properties` with values like the example below.
   ```properties
   core.scheduler.executor.pool-size=10
   core.scheduler.executor.thread-name-prefix=SCHEDULER-EXECUTOR
   ```
4. Then you can use this base service:
   - `BaseJobService`: Service used to do the job scheduler. Extends this interface if you want to create job for scheduler.
     ```Java
     @Service("transactionSchedulerService")
     public class TransactionSchedulerServiceImpl implements BaseJobService {
         
         @Override
         public void run(SchedulerItem<?> schedulerItem) throws Exception {
             // TODO running the job
         }
     }
     ```
     **Important**
     - You must define this class as a `@Service`, because this configuration using that service for processes schedule.
     - You must store that **service name** into your SchedulerEntity (as a field in your table). So you can set it into `SchedulerItem`.
   - `BaseSchedulerService`: Generic Manager used to provide common scheduler methods. Extend this interface if you want typesafe (no casting necessary) managers for your scheduler.
     ```Java
     @Service("schedulerService")
     public class SchedulerServiceImpl extends BaseSchedulerServiceImpl {
         
         @Override
         public void beforeSchedule(SchedulerItem<?> schedulerItem) {
             // TODO process you want to execute before start schedule for each {@link BaseJobService}
         }
         
         @Override
         public void afterSchedule(SchedulerItem<?> schedulerItem) {
             // TODO process you want to execute after schedule completed for each {@link BaseJobService}
         }
         
         @Override
         public void afterSchedule(SchedulerItem<?> schedulerItem, String errorMessage) {
             // TODO process you want to execute after schedule completed because scheduler have an error. 
         }
         
         @Override
         public Trigger getTrigger(SchedulerItem<?> schedulerItem) {
             // TODO create your custom trigger for each {@link SchedulerItem}
         }
     }
     ```
