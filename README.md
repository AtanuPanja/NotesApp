# Notes App
This web-based Notes application uses Java, JSP, Servlets, and MySQL. 
It also uses CSS and JS for the front end.
It implements CRUD functionality. 
The application is deployed on Apache Tomcat and uses MySQL as the backend database.


## Features
* CRUD operations: Get the list of notes, read a note, edit a note, delete a note
* Backend validations in the forms of add and edit pages
* JavaScript used for dynamic changes in form textarea and buttons.
* MVC architecture: Follows the MVC architecture for separation of concerns.

## Pre-requisites
* Java Development Kit (JDK): Version 17
* Apache Tomcat: Version 10.*
* Eclipse IDE for Java EE Developers: Version compatible with your setup.
* MySQL: Version 8.0.*
* MySQL Command Line Client: Installed and configured.

## Installation and Setup
### 1. Clone the repository
```bash
git clone https://github.com/AtanuPanja/NotesApp.git
```
### 2. MySQL setup
   1. #### Install MySQL:
      - Download and install MySQL from the [official website](https://dev.mysql.com/downloads/installer/)
      - Set up a new MySQL instance with a root user
     
   2. #### Create the Database:
      - Log in to the MySQL command line client:
        ```bash
        mysql -u root -p
        ```
      - Create the database for the application:
        ```sql
        CREATE DATABASE your_database_name;
        ```
        > You can choose your custom name for the database.
      - Use the your_database_name database:
        ```sql
        USE your_database_name;
        ```

   3. #### Run the SQL scripts:
      - While still logged in, run the command:
        ```sql
        SOURCE path/to/sql/schema.sql;
        ```
      - Alternatively, close the session, open the terminal from the project root, and run
      ```sql
      SOURCE sql/schema.sql;
      ```
      > This sets up the tables inside the database.
### 3. Eclipse Setup
#### Import the Project
  1. Open Eclipse and select `File > Import > Existing Maven Projects`.
  
  2. Browse to the location where you clone the repository and select it.
  
  3. Click `Finish`.
  
#### Install dependencies:
- Right-click on your project in the Project Explorer and go to `Run > Maven Install`.


#### Configure Tomcat in Eclipse

  1. #### Add Apache Tomcat to Eclipse:
     - Go to `Window > Preferences > Server > Runtime Environments`.
     - Click `Add`, choose `Apache Tomcat`, and select the version you installed.
     - Click `Next`, browse to your Tomcat installation directory, and click `Finish`.
     
  2. #### Configure the Project Facets:
     - Right-click on your project in the Project Explorer and select `Properties`.
     - Go to `Project Facets` and ensure the following facets are selected:
       - Dynamic Web Module: Version 6.0
       - Java: Version 17
       - Switch to the `Runtimes` tab and ensure the Apache server is selected.
     - Click `Apply and Close`.
  	 
  3. #### Add Tomcat Server:
     - Open the `Servers` tab at the bottom view of the Eclipse IDE, click `Create new server`.
     - Select `Apache > Tomcat (your installed version)` and click `Next`.
     - Click `Finish`.
     
### 4. Run the Application
  1. #### Initial Configuration:
     - Find the `config.properties.example` file and rename it `config.properties`.
     - Edit the file with your database name, MySQL username, and password.
        > <strong>Ensure the database name matches the one used in</strong> [Step 2 (Database Creation)](#2-mysql-setup) .

  1. Right-click on the project in the `Project Explorer`.
  1. Go to `Run > Run on Server`.
  1. Select `Choose an existing server`, pick the server under the localhost folder, and click `Next`.
  1. Add your project to the server and click `Finish`.

## Example Configuration
Here is an example `config.properties` file:

```properties
db.url=jdbc:mysql://localhost:3306/
db.name=your_database_name
db.username=your_mysql_username
db.password=your_mysql_password
db.driver=com.mysql.cj.jdbc.Driver
```
db.name, db.username, db.password values are customizable.

## Usage
- Access the application through [localhost:8080/NotesApp/](localhost:8080/NotesApp/).
- Add note using Add button as presented
- Edit and Delete have been displayed as icons to the bottom of each note.
  - Delete removes the note permanently.
  - Edit allows you to view and modify the full note content.
