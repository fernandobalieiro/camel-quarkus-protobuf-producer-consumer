# Camel Quarkus Protobuf Producer and Consumer Example

Producer and Consumer examples using Quarkus, Apache Camel, RabbitMQ and Protobuf. 

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run the apps in dev mode that enables live coding.

First, install the project's dependencies:
```shell script
./mvnw clean install -Dquickly
```

Run in dev mode:

### Producer:
```shell script
./mvnw quarkus:dev -pl camel-quarkus-protobuf-producer
```

### Consumer:
For the Consumer, it's necessary to specify the Debug Port, as port 5005 (default) will be used by the Producer.  

```shell script
./mvnw quarkus:dev -pl camel-quarkus-protobuf-consumer -Ddebug=5006
```

### RabbitMQ and MongoDB:
```shell script
docker volume prune -f; docker-compose down; docker-compose run -d -p 27017:27017 mongo; docker-compose run -d -p 5672:5672 -p 15672:15672 rabbitmq;
```


## Packaging and running the application

### Packaging
The application can be packaged using:
```shell script
./mvnw package -Dquickly
```
It produces the `camel-quarkus-protobuf-producer-1.0.0-SNAPSHOT-runner.jar` and `camel-quarkus-protobuf-consumer-1.0.0-SNAPSHOT-runner.jar` under their respective `/target` directories.
Be aware that these are not _über-jars_ as the dependencies are copied into the `target/lib` directory.

If you want to build _über-jars_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar -Dquickly
```

The application is now runnable using `java -jar target/camel-quarkus-protobuf-<producer_or_consumer>-1.0.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/camel-quarkus-protobuf-producer-consumer-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.html.

# Config example

<p>This example displays mach speed in your favourite unit, depending on the specified Quarkus configuration.</p>
<p>The Quarkus configuration is located in: <code>src/main/resources/application.yml</code></p>
<p><b>Supersonic!</b></p>
Guide: https://quarkus.io/guides/config#yaml
