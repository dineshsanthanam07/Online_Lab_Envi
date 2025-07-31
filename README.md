# Online Lab Environment Backend

## How to build in local

### Pre-requisites
1. [Node.js](https://nodejs.org/en) (We depend on [Swagger cli](https://www.npmjs.com/package/swagger-cli) to bundle multiple open api specifications)
2. Install [Swagger cli](https://www.npmjs.com/package/swagger-cli) node package
3. JDK 21 (Preferably [Bellsoft Libertica Native image kit](https://bell-sw.com/pages/downloads/native-image-kit/#nik-23-(jdk-21)))
4. gradle

### Setup in local
1. Clone the repo using ```git clone https://github.com/dineshsanthanam07/Online_Lab_Envi.git```
2. Switch to app directory ```cd Online_Lab_Envi```
3. Bundle open api specification by executing ```swagger-cli bundle -o src/main/resources/openapi/swagger-spec.yaml -t yaml src/main/resources/openapi/openapi-spec.yaml```
4. Build the application by executing ```gradle build```