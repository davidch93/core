# Core Service JPA
Core libraries and dependencies for generic service using JPA.

## How to use it
1. Add this dependency to your project.
2. You can used any of class that you need it. Main features of this libraries is:
   - `GenericService`: Generic Manager that talks to GenericDao to CRUD POJOs. Extend this interface if you want typesafe (no casting necessary) managers for your domain objects.
   - `GenericTreeService`: `GenericService` with some tree service.
