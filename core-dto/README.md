# Core Data Transfer Object
Core libraries and dependencies for all data transfer object.

## How to use it
1. Add this dependency to your project.
2. You can used any of class that you need it. Main features of this libraries is:
   - `BaseDTO`: define common field that exist in every data transfer object.
   - `ResponseDTO`: that defined common response message from server.
   - `ValidatorService`: Service to validate object used by validator constraint. Extends this interface if you want to create custom implementation validator services.
   - Any common validator using **JSR-303**.
