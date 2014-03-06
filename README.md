# Testing CDI Event behavior with different implementation
 
 With included code, you'll be able to test how CDI 1.0 and 1.1 resolves event observers.
 Thanks to Arquillian you can launch these test against :
 
 * Apache OpenWebBean 1.1.7 (version included in TomEE 1.5.1)
 * JBoss Weld 1.1.5 (version included in JBoss AS 7.1.1)
 * JBoss Weld 2.1.2 (version included in WildFly 8.0.0.Final)
 
## Requirements

You'll need JDK 1.7 and Maven 3.x to launch these tests

## Launching test
 
You'll only need to choose of tof the provided maven profiles for test. Profiles are :

 * `owb`for OpenWebBeans tests
 * `weld-1.x` for Weld 1.x tests
 * `weld-2.x` for Weld 2.x tests
  
Just launch `mvn clean test -P<profile>` to launch test (for instance `mvn clean test -Powb` to test against OpenWebBeans).
Tests won't be launched without one of these profiles.
 
 
 
 
