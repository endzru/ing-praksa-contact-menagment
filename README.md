# ing-praksa-contact-menagment
##** Installation guide **
## Getting Started
### Requirments
* Java 18
* Maven
* PostgreSQL (version 14)
* Intellij IDEA (version = 2022.1.3)
* Git for cloning the project.
* Postman (Optional)

### Running the API
**Clone this repository** 
```bash
git clone https://github.com/TheSpax/contact-management.git
```
* When opening the project, open the contact-menagment folder with your IDE
* Open the project folder `contact-menagment` and in comand prompt type `mvn clean install`  (this will install all dependencies)
* Run the program.

**when the program runs you can access `http://localhost:8080/contact-manager/swagger-ui.html` for the documented API.**
* there you can see all the enpoints.

##Run with docker
* If running with docker, docker is the only thing you need installed
Enter the root folder of the project, in the cmd type `docker-compose build` and `docker-compose up`, this will build the images and run them.
