# CustomerAPI
CRUD operations REST API implemented in Spring Boot performed using Oracle Database - 18.3.0.0.0 

Implementation steps-

1. Create a maven project and add below parent pom dependency in the pom.xml file to make our project as a child of it. This dependency includes the default configuration and provides plugin management for applications built with Maven.

           <parent>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-parent</artifactId>
			<version>1.4.2.RELEASE</version>
           </parent>
	   
2. Add below properties (it is optional) in pom.xml to support java version 8.

           <properties>
			<java.version>1.8</java.version>
           </properties>
	    
3. We want to create a REST API and this needs below dependency to be added in the pom.xml file. This dependency will inject all the jars required to use the annotations for creating a REST API. For example, @RestController, @RequestMapping etc.

		<dependencies>
			<dependency>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-starter-web</artifactId>
			</dependency>
		</dependencies>

4. Add below dependencies to make oracle db connection using JDBC.

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>

		<dependency>
			<groupId>com.oracle</groupId>
			<artifactId>oracle</artifactId>
			<version>18.3.0.0.0</version>
		</dependency>
		
You might get an error "Missing artifact com.oracle:oracle:jar:18.3.0.0.0"

Download ojdbc8.jar explicitly and place it in a folder, run below command in the folder containing the jar and then you should get a BUILD SUCCESS message.
> mvn install:install-file -Dfile=ojdbc8.jar -DgroupId=com.oracle -DartifactId=oracle -Dversion=18.3.0.0.0 -Dpackaging=jar -DgeneratePom=true

This will inject oracle-18.3.0.0.0.jar in the project's classpath required to make oracle database connection. This step is to be done because the jar is not yet available on maven central repository. 

Update the maven project once and you will see the error is gone and oracle-18.3.0.0.0.jar will be added to the Maven dependencies section.

5. Create a new configuration file application.yml in src/main/resources folder and add below properties.

		#tomcat server port
		server:
		  port: 9090

		---

		#database config
		spring: 
		     datasource:
			url: jdbc:oracle:thin:@localhost:1521/orclpdb
			username: <<your username>>
			password: <<your password>>
			driver-class-name: oracle.jdbc.OracleDriver
			
6. Login to your oracle db schema using sqlplus or any oracle IDE like SQlDeveloper. Create customers table and customer_seq as below.
customers table is used for storing customer data and customer_seq is used for 

		--Customers table
	        CREATE TABLE CUSTOMERS(
		   id INTEGER NOT NULL PRIMARY KEY,
		   firstname VARCHAR2(20) NOT NULL,
		   lastname VARCHAR2(20) NOT NULL,
		   age INTEGER NOT NULL,
		   emailid VARCHAR2(20) NOT NULL
		);

		--customer sequence
		CREATE SEQUENCE customer_seq
		MINVALUE 1
		MAXVALUE 99999999
		START WITH 1
		INCREMENT BY 1
		CACHE 10;
		
7. REST API endpoints for customer -

	a. Adding a new customer - 

		@RequestMapping(value = "/customers/add", method = RequestMethod.POST)

	b. Retrieving a customer based on its id -

		@RequestMapping(value = "/customers/{id}", method = RequestMethod.GET)

	c. Deleting a customer -

	    @RequestMapping(value = "/customers/{id}", method = RequestMethod.DELETE)

	d. Updating existing customer details - 

	    @RequestMapping(value = "/customers/{id}", method = RequestMethod.PUT)


