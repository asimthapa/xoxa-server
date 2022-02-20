# xoxa-server
Backend for xoxa (Zulu for message)

# Steps to run the app

### `./mvnw spring-boot:run`
Start the application

### Run the xoxa-client

## Run without xoxa-client (not functional)
localhost:8080 also serves up a page from src/main/resources/static/app.js which can be used to just check the app.

## ISSUES 

- Messages are not being saved to database when being sent from the React UI. But if the messages are sent from src/main/resources/static/app.js, the messages are saved.
