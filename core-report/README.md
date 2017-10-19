# Core Report
Core libraries and dependencies for processing report configuration using JasperReport. You can add other report dependency to this library.

## How to use it
1. Add this dependency to your project.
2. You can used any of class that you need it. Main features of this libraries is:
   - `JasperReportService`: Generic Manager used to provide common report methods. You should need to extend `JasperReportServiceImpl` when your require custom report service.
3. Create a configuration file with path `config/report/core-report-config.properties` with values like the example below.
   ```Java
   core.report.executor.compile-path=/report/        # Location of .jasper file
   core.report.executor.output-path=/report/output/  # Location of output
   core.report.executor.report-path=/report/         # Location of .jrxml file
   ```
