# Core Data Transfer Object
Core libraries and dependencies for all data transfer object.

## How to use it
1. Add this dependency to your project.
2. You can used any of class that you need it. Main features of this libraries is:
   - `BaseDTO`: define common field that exist in every data transfer object.
   - `ResponseDTO`: that defined common response message from server.
   - Any common validator using **JSR-303**.
   - `ValidatorService`: Service used to validate object. Extends this interface if you want to create custom validator services.
   - `KV`: An immutable key/value pair with compatibility to use collection sort.
