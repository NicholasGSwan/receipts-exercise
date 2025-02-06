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
#### EDIT:  
I don't know if the above instructions work without java or gradle, so I've pushed an image to dockerhub.  
If the above did not work, please pull the image:  
`docker pull uglygrayduck/receipts-api:1.0`

This would require you to change `receipts-api` to `uglygrayduck/receipts-api:1.0` in the following command.

To run the application on localhost:  
  `docker run -d -p 8080:8080 receipts-api`  
  
