# buoy

Web application in which users can simultaneously edit code snippets and otherwise make posts in an imageboard format. 

The intention of this project is to provide students with a platform to collaborate with each other to learn programming
as well as a general resource for sharing information and asking questions.

This is project is not feature complete.

Features that are currently at some level of functionality:
* Collaborative editor
* Text posts
* Comments

Planned features:
* Accounts
* Live chat
* Syntax highlighting

### Installing

Clone the repo into the directory of your choice
```
git clone https://github.com/dolmengate/buoy
```

Ensure Maven is installed
```
mvn -v
```

Then install dependencies and build the project (this might take a few minutes)
```
mvn clean install
```

### Running
Change directory into `buoy/server`
```
cd ./server
```

Start the web server
```
mvn spring-boot:run
```

There will be a lot of logging to the shell. Once you see something like
```
s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8080 (http)
info.sroman.Application                  : Started Application in 15.348 seconds (JVM running for 19.647)
```
The server is up and running!

Open your favorite web browser and check out `localhost:8080`.

## Built With

* [Spring](url) - MVC framework, JPA implementation and REST API
* [Angular2](https://angular.io/) - Front-end
* [Maven](https://maven.apache.org/) - Dependency Management
* [Stomp.js](https://github.com/jmesnil/stomp-websocket) - WebSocket message protocol
* [ng2-stompjs](https://github.com/stomp-js/ng2-stompjs) - Angular2 Stomp service over WebSockets
