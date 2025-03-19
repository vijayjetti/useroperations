# SonarQube Issues to Fix

1. Controller Issues:
   - Add proper @ResponseStatus annotations to all controller endpoints
   - Add input validation annotations (@Valid, @NotNull, etc.)
   - Add proper error handling with @ExceptionHandler

2. Service Layer Issues:
   - Fix unchecked Optional returns
   - Add proper null checks
   - Improve error handling with custom exceptions

3. General Issues:
   - Add proper logging
   - Improve error messages
   - Add request/response validation