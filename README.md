# LearnSpringBoot
Project for testing spring boot features

## Lessons learned
### Async jobs
* Add @EnableAsync to the configuration class
* Add @Async to the asynchronous method
### Switch to JSR 330 (@Inject)
* Add javax.inject version as pom dependency
### Externalizing configuration
* Configuration variables come from:
    * Application.properties
    * App variables
    * Java variables (-D)
    * Env variables (export var=x)
### Profiles
* In spring boot you can load specific properties files based on the profile name (application-dev.properties)
* https://www.baeldung.com/spring-profiles
* Java param: -Dspring.profiles.active=dev or any env variable  (export spring.profiles.active=dev)
* You can switch the profile from the maven profile
### Transaction management
* @EnableTransactionManagement has to be set on some config class (it seems to be by default in spring boot)
* @Transactional has to be set on the class/method where the transaction starts
* There is no transaction by default when @transactional is not set. JPA starts its own transaction in this case.
* logging.level.org.springframework.transaction.interceptor=TRACE (to log transaction details)
### Logging -> how to change logging levels and destinations
* Logback is the default spring boot implementation
* Use logback-spring.xml to configure logback
* Useful properties in app properties
    * Logging.file
    * Logging.path
    * logging.level.root=WARN
    * logging.level.org.springframework.web=DEBUG
    * logging.level.org.hibernate=ERROR
* You can define logging groups (more packages together)
* Predefined groups in spring boot: 
    * Web
    * Sql
* Spring boot includes some extensions to logback
    * Profile specific configurations
    * Use properties from app properties file
* You can add a configuration @Bean that initializes the Logger and inject it everywhere
### JAX-RS (with jersey)
* To use jersey add spring-boot-starter-jersey as dependency
* Create new jersey config class extending ResourceConfig with @ApplicationPath
* Register the endpoints in the jersey resource config
* To change date format use: ‘spring.jackson.date-format=yyyy-MM-dd HH mm’ in application.properties
### JAX-RS (with and Apache-cx)
* It is more complicated to configure that it is for jersey
* Add starter in pom.xml
~~~
    <dependency>
        <groupId>org.apache.cxf</groupId>
        <artifactId>cxf-spring-boot-starter-jaxrs</artifactId>
        <version>3.1.12</version>
    </dependency>
~~~
* Add jackson json provider (because cxf doesn't come with a json provider... wtf)
~~~
    <dependency>
        <groupId>com.fasterxml.jackson.jaxrs</groupId>
        <artifactId>jackson-jaxrs-json-provider</artifactId>
    </dependency>
~~~
* Configure stuff in application.properties (note the pakage where the json provider is)
~~~
cxf.path=/cxf
servlet.init.service-list-path=/info
cxf.jaxrs.component-scan=true
cxf.jaxrs.component-scan-packages=com.raz.crud.cxf,org.apache.cxf.jaxrs.swagger,org.apache.cxf.metrics,org.glassfish.jersey.jackson.internal.jackson.jaxrs.json
~~~
* Provide JacksonJsonProvider as bean in the configuration class
* Configure date format in the json provider (configuration class)
### Security
* https://www.baeldung.com/spring-boot-security-autoconfiguration
### JPA and JDBC check
### Check mongoDB connection
### Caching
### Messaging
### Calling rest service
### Validation