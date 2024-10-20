# Notes App
This web-based Notes application uses Java, JSP, Servlets, and MySQL. 
It also uses CSS and JS for the front end.
It implements CRUD functionality. 
The application is deployed on Apache Tomcat and uses MySQL as the backend database.


## Features
* CRUD operations: Get the list of notes, read a note, edit a note, move note to bin, restore note, delete permanently, empty bin, mark/unmark as favorite
* Backend validations in all the route controllers, and forms in add and edit pages
* JavaScript used for dynamic changes in form textarea and buttons.
* JavaScript used for navigation
* MVC architecture: Follows the MVC architecture for separation of concerns.
* DAO pattern: Uses DAO pattern to separate data access logic from the controllers.

## Pre-requisites
* Java Development Kit (JDK): Latest LTS version (install and add to JAVA_HOME path variable)
* [Apache Tomcat](https://tomcat.apache.org/download-10.cgi): Version 10.*
* [Apache Maven](https://maven.apache.org/download.cgi): Any version (install and add to system PATH) 
      
  [Maven Install instructions](https://maven.apache.org/install)

  (Check the installation using mvn -v)

* MySQL: Version 8.0.*

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
      - While still logged in, run the commands:
        ```sql
        SOURCE path/to/sql/schema.sql;
        SOURCE path/to/sql/sample_data.sql;
        ```
      - Alternatively, close the session, open the terminal from the project root, and run the commands:
        ```sql
        SOURCE sql/schema.sql;
        SOURCE sql/sample_data.sql;
        ```
      > This sets up the tables, with some initial data inside the database.
      
### 3. Run the application

#### Initial Configuration:
  1. Find the `config.properties.example` file and rename it to `config.properties`.
  
  2.  Edit the file with your database name, MySQL username, and password.
        > **Ensure the database name matches the one used in** [Step 2 (Database Creation)](#2-mysql-setup) .

Here is a sample `config.properties` file:

```properties
db.url=jdbc:mysql://localhost:3306/
db.name=your_database_name
db.username=your_mysql_username
db.password=your_mysql_password
db.driver=com.mysql.cj.jdbc.Driver
```
db.name, db.username, db.password values are customizable.

#### Package the application to .war
  1. Open the project root directory in the terminal.
  2. Run `mvn install`.
  3. This creates a file with extension `.war` in the `target` subdirectory.
  4. Copy the `.war` file for the next step.

#### Running on the server
  1. Navigate to `path\to\tomcat\webapps`.
  2. Paste the `.war` file to this directory.
  3. Navigate to `path\to\tomcat\bin`.
  4. Open terminal in this directory and run the command:
  
  On Windows: `.\startup.bat`.
  
  On Linux: `./startup.sh`.
  
  5. Another terminal window opens and starts executing the server.

## Usage
- Access the application through [localhost:8080/NotesApp/](localhost:8080/NotesApp/).
- Navigate between Home and Bin pages using the navbar
- **Add**: Add note using Add button as presented.
- **View/Edit**: 
  - Click on a note to open view/edit page.
  - Only when you input some data into the textareas, the `Save Changes` button appears.
- **Delete**: 
  - In home page, *delete temporarily* and move to bin by clicking on delete button.
  -  In trash page:
	  - Click on `trash-icon with cross symbol`, to *delete permanently*.
	  - Click on `trash-icon with arrow symbol`, to *restore* the note back to home page.
	  - Click on `Empty Bin` at the top, to *delete all trashed notes permanently*.
      - `Empty Bin` button is now visible only when there are notes in the trash page.
      - It is hidden when no notes are in trash.
- **Favorite**: Click on `star-icon` associated with any note to mark it as favorite. Toggle favorite by clicking again.
- **Dark Mode**: Enable/Disable dark mode: In the navbar `Menu > Enable Dark Theme`.
