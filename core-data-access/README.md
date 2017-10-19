# Core Data Access
Core libraries and dependencies for all data access and represents with abstract class.

## How to use it
1. Add this dependency to your project.
2. You can used any of class that you need it. Main features of this libraries is:
   - `BaseEntity` and others defined common field that exist in every model and mapped as superclass.
   - Audit Trail using **HibernateEnvers**.
3. To get auditing configuration only need to extends **AuditConfigurerSupport** class.
   ```Java
   @Configuration
   @EnableJpaAuditing
   @EntityScan(basePackages = {"com.dch.core.dataaccess.audit.envers" ,"your entitiy base package"})
   public class SpringAsyncConfig extends AsyncConfigurerSupport {

   }
   ```
