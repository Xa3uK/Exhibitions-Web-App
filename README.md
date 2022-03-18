# Exhibitions Web App
Epam winter camp 2022 final project. 
<br>
There are roles: user, authorized user, administrator.
The administrator makes a list of expositions (theme, hall, period and time of work, ticket price), and can also cancel expositions, view statistics of visits. The exposition can occupy one or more halls.
The user can view the exhibits by topic, ticket price, and filter by date.
An authorized user can buy a ticket to the selected exhibition.
<br>
## Installation

1. Use SQL scripts from src/main to create tables in PostgreSQL database<br>
2. Create exh_app.properties file and put it in System Environment "APP_DIR" with data: <br>
db.url = jdbc:postgresql://localhost:5432/your_database_name<br>
db.user = your_login<br>
db.password = your_password<br>
db.driver = org.postgresql.Driver<br>
3. Run app from Intelij Idea with your Tomcat configuration or put .war archive of project to Apache Tomcat directory, then localhost:8080/app_name
