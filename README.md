# Distance Calculator

The following is a spring-boot project which provides a user list which are within a distance of 100km from the destination location. The users are read from a file and then based on there co-ordinates distance is calculated.
The users are arranged in ascendng order of there id.
## Installation

1. Checkout the git project.
2. Install Java 8 and Maven for the project
2. Go into the distance-calculator directory
    cd distance-calculator
2. Run the command
    ```maven
    mvn clean
    ```   
   This will help to build the classes
2. Then Run the following command to build the jar file
    ```maven
    mvn install
     ```
 2. The following command will create a jar file after running the test cases.
   The jar file will be created inside the target folder as
    ```
    main-1.0-SNAPSHOT.jar
    ```
2. Run the jar file using the following command
    ```
    java -jar main-1.0-SNAPSHOT.jar
    ```   
2. Make sure the customers.txt file is in the same folder as the jar.
2. Once you run the jar the output can be accessed using the following local url:
    ```
    http://localhost:8080/v1/userDistances/
    ```   
2. The attained output for the customers.txt file in the repository can be seen in the Output.txt.   
