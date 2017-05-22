## Baseball API REST Client

This repo contains a Java based HTTP client baked for a Baseball REST API that's implemented as a suite of microservices and also serves as the entry-point for a demo.

### Big Picture

The purpose of this demo is to highlight Context Based Routing and Blue Green deployment with a basic Eureka and Zuul setup.  Eureka provides service discovery and Zuul provides support for Context Based Routing.  The demo contains 2 Spring Boot based microservices each exposing REST endpoints and 1 client that's used to call the API.

### Big Deal

Context Based Routing is kinda a Big deal in Microservice Architectures because it allows aggregating seperate microservices under one API and thus each "context" becomes independently deployable and scaleable.

### The Parts

1. The Baseball Client

The Baseball Client is a simple Spring Boot app that calls into the API via the Baseball API Gateway and echos the response to the console.  A single thread runs every 5 seconds by default and each time it calls a random endpoint exposed by one of the microservices.  If you want concurrency start multiple instances of the Baseball Client.

Each Baseball Client needs to know a little info so it can call the API, first piece is the API root (i.e. reference to the API gateway root).  This is set in application.yml or can be set on command line when the Baseball Client boots.

2. The Players REST Service

3. The Batting REST Service

4. The Baseball API Gateway

5. The Eureka Service


### Setting up the demo

1. clone this repo

``git clone https://github.com/corbtastik/baseball-client.git``

2. clone Eureka Server repo

``git clone https://github.com/corbtastik/eureka-server.git``

3. clone Baseball API Gateway repo (Zuul based)

``git clone https://github.com/corbtastik/baseball-api.git``

4. clone Players REST API repo

``git clone https://github.com/corbtastik/players.git``

5. clone Batting REST API repo

``git clone https://github.com/corbtastik/batting.git``




