# SafetyNet Alerts
The "PAY" application allows clients to easily transfer money to manage their finances or pay their friends. It allows them to make payments, withdrawals and especially transfers to "friends".

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install the software and how to install them

- Java 1.8
- Oracle 12.2.X 
- Maven 3.6.3
- Spring 5.2.5


### Installing

A step by step series of examples that tell you how to get a development env running:

1.Install Java:

https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html

2.Install Maven:

https://maven.apache.org/install.html

3.Spring

https://spring.io

4. Install Oracle (Oracle XE https://www.oracle.com/fr/database/technologies/appdev/xe.html) and create a database "XE" and a schema (User "OCR_P6"), then run the SQL script data_schema.sql to create the tables.
You can populate the database with the script data_init.sql.

### Documentation : UML Diagram - conceptual data model

![UML diagram](/img/P6_UML_Diagram.jpg)

### Testing

The app has unit tests and integration tests written. The existing tests need to be triggered from maven-surefire plugin while we try to generate the final executable jar file.

To run the tests from maven, go to the folder that contains the pom.xml file and execute the below command.

`mvn test`

### Other consideration
JAVADOC has been initialized and needs to be completed.
