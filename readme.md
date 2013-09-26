##Restaraunt Project
It's a learning project which I've done at Bionic University Java SE & EE study. 
###Task
Restaraunt system. Client makes his order from Menu. Administrator approves the order and sends it to the kitchen. Administrator bills the client. Client foots the bill.
### Realization 
The task is done using Java EE 6. For presentation layer JSF 2 is used with Richfaces 2.2.3 library. For security matters JAAS framework is used. Communication with database is done via JPA.
DAO is made with the use of EJB 3.1.

Some of the pages can be seen on the screenshots.  
All tables are done with pagination (only the number of database records displayed is fetched from database).  
User can change his info.  
Pages that admin and client can both see are differed depending on role.  
MySQL database is used.