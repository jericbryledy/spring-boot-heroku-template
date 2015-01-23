Spring Boot for Heroku Template
==

This project template is ready to be pushed to Heroku and will work properly.

Technology Stack
--
1. Build Tool
    1. [Maven](http://maven.apache.org/)
1. Database
    1. [Liquibase](https://github.com/liquibase/liquibase)
    1. [HikariCP](https://github.com/brettwooldridge/HikariCP)
    1. [Hibernate](http://hibernate.org/)
    1. [PostgreSQL](http://www.postgresql.org/)
1. Web
    1. [Spring Boot](http://projects.spring.io/spring-boot/)
    1. JSP
    1. [JSTL](https://jstl.java.net/)
1. Test
    1. [TestNG](http://testng.org/doc/index.html)

Local Usage
--

Since Heroku uses the environment variable <code>DATABASE_URL</code> for specifying its database connection, you need to specify it on your local machine yourself.

Format is:

```
postgres://<username>:<password>@<host>:<port>/<database name>
```

Example (linux):
```
export DATABASE_URL=postgres://jeric:123@localhost:5432/sample_db
```

Running
--

**The Spring Boot way:**
```
mvn clean install spring-boot:run
```
or if you don't want to leave traces in your .m2 repo:
```
mvn clean test spring-boot:run
```
you should be able to access http://localhost:8080/ afterwards


**The Heroku way:**
```
mvn clean install && foreman start
```
or if you don't want to leave traces in your .m2 repo:
```
mvn clean verify && foreman start
```
you should be able to access http://localhost:5000/ afterwards

