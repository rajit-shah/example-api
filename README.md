# Rest API to demonstrate async rest api call 
The implementation is in java using popular spring boot framework and open api (swagger).

## Project Structure

* ```rs.example.api.entry.AsyncApiStarter``` is an entry class that boots up the app.  
* ```rs.example.api.resources.UserPostResource``` is a controller class that implements the rest api with swagger definitions.  
* ```rs.example.api.repositories.PostRepository``` and ```rs.example.api.repositories.UserRepository``` are repository classes that perform async call to 3rd party api. The url is supplied as an application property.
    The api url ```https://jsonplaceholder.typicode.com``` is part of the application and is hard coded into ```resources/application.yaml``` file. The log out put shows how two urls are called asynchnously simutaneously. 
    The sample log looks like:
```
2020-02-12 10:45:40.717  INFO 7988 --- [cTaskExecutor-1] r.e.api.repositories.UserRepository      : Success! - SimpleAsyncTaskExecutor-1
2020-02-12 10:45:40.720  INFO 7988 --- [cTaskExecutor-2] r.e.api.repositories.PostRepository      : Success! - SimpleAsyncTaskExecutor-2      
```

* ```rs.example.api.resources.UserPostResourceTest``` does the simple unit test with mocked repository objects. 
* ```rs.example.api.resources.UserPostResourceIntegrationTest``` does the Integration test which does the real api call to 3rd party
endpoint and does the response check as well as response entity check. 
 
## Build and run
You will need maven to build this application.

* run ```mvn clean install``` to build a runnable jar. Run ```java -jar <jar file>``` to start the app directly from jar file.
* build docker image running ``` docker build -t razzsh/api-example:1.0```
* run docker container using ``` docker run -p 8080:8080 razzsh/api-example:1.0```
* you can open swagger ui using ```http://localhost:8080/swagger-ui.html```

 