# Receipts API Exercise

Hello, thank you for taking the time to review my application!  I have built this application in Java/Spring Boot with an embedded H2 db.

## Instructions
To run the application, please run:  
  `./gradlew build`

To build the image:  
  On Windows:  
    `docker build --build-arg JAR_FILE=build/libs/*.jar -t receipts-api .`  
  On Mac and presumably Linus:  
    `docker build --build-arg JAR_FILE=build/libs/\*.jar -t receipts-api .`  
To run the application on localhost:  
  `docker run -d -p 8080:8080 receipts-api`  
  
