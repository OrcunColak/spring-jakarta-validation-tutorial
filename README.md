# Read me

Validation failure can be received by

1. BindingResult as controller parameter
2. @RestControllerAdvice + @ExceptionHandler(MethodArgumentNotValidException.class)

# 1. Binding Result Controller

The original idea is from  
https://medium.com/@himani.prasad016/validations-in-spring-boot-e9948aa6286b

# Swagger

http://localhost:8080/swagger-ui/index.html

# File encoding

Change file encoding of properties file to UTF-8
https://youtrack.jetbrains.com/issue/IDEA-217124/Resource-bundle-properties-files-are-locked-to-UTF-8-when-in-reality-they-should-be-ISO-8859-1