# SurveyBack

## Overview
This is the backend of a survey app. It's purpose is to allow team members to rate surveys. 
I've added the admin interface in order to allow creating new survey and checking the results of the team member ratings. 

You can run this app locally. But first, you'll have to do the essential configuration for your postgres database and update the application.properties file accordingly. 

I tried to make it simple to test this application and deployed it. 

[Link of the backend](https://surveyback-vb8s.onrender.com)
[Link of the frontend application](https://survey-neon.vercel.app/)

### API Endpoints
The endpoints are documented here : 
[Link to swagger](https://surveyback-vb8s.onrender.com/swagger-ui/index.html#/)

<img width="1485" alt="Screenshot 2024-12-15 at 16 38 13" src="https://github.com/user-attachments/assets/f99edfdd-0376-4408-821b-bec09d22947b" />

PS: The queries can take some time to be excecuted due to the database host response time which can take around 4 to 5 seconds and sometimes more. ( Free hosting has its limits :) )


## Prerequisites ( If you decided to run the app locally)

Before running this project, ensure you have the following installed:

- Java 17 or higher
- Maven 3.8.1 or higher
- Spring Boot 2.x or higher

### Clone the Repository

```bash
git clone https://github.com/GhadaRV/SurveyBack.git
cd SurveyBack
```

### Build the Project

```bash
mvn clean install
```
### Run the Application

```bash
mvn spring-boot:run
```




