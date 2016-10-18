Spring Recipes 3rd Edition Sources
===

These are the sources belonging to Spring Recipes 3rd Edition. Each chapter has its own sources and each chapter can contain multiple source snippets

TOC
---

1. Spring Development Tools
2. Spring Core Tasks
3. Spring Annotation Driven Core Tasks
4. Spring @MVC
5. Spring REST
6. Spring Deployment to the Cloud
7. Spring Social
8. Spring Security
9. Spring Mobile
10. Spring with other Web Frameworks
11. Data Access
12. Spring Transaction Management
13. Spring Batch
14. Spring NoSQL and Big Data 
15. Spring Java Enterprise Services and Remoting Technologies
16. Spring Messaging
17. Spring Integration
18. Spring Testing
19. Spring Caching
20. Grails


Building
---
The projects can be build with either gradle or the gradle wrapper (the latter is recommended). 

    ./gradlew clean build

This should download gradle and start building all the projects. The executables can be found in the `build/libs` directory of each project. 

In general if it is a .jar file it should be executable and can be run with

    java -jar [name-of-jar].jar 
    
If it is a .war file deploy to a server like Apache Tomcat.
