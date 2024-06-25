# spring-boot-kafka-monitoring-example

Example of how to monitor producers and consumers running in Spring Boot Kafka.


## Target State

- Ability to produce messages using REST API
- Consume messages from the same topic that is produced to
- Ability to return metrics readable by Prometheus using REST API

# Install

## Installation and Setup

### Prerequisites

- Java JDK 17 or later
- Maven 4.0 or later
- Access to a Kafka cluster

### Steps to Install and Run

1. Clone the repository:


1. Create `src/main/resources/application.properties`


```sh
spring.application.name=kafka-client

spring.kafka.consumer.group-id=my-group-id

spring.kafka.bootstrap-servers=pkc-abcdef.confluent.cloud:9092
spring.kafka.properties.security.protocol=SASL_SSL
spring.kafka.properties.sasl.mechanism=PLAIN
spring.kafka.properties.sasl.jaas.config=org.apache.kafka.common.security.plain.PlainLoginModule required username="username" password="password";

# Actuator configuration
management.endpoints.web.exposure.include=*
management.endpoints.web.base-path=/actuator
management.endpoint.prometheus.enabled=true

management.metrics.enable.kafka=true

server.port=8081
```

3. Create a build

```
mvn clean package
```

4. Run the jar file

```
java -jar target/kafka-client-0.0.1-SNAPSHOT.jar  
```

5. Produce to Kafka

```
curl -X POST http://localhost:8081/send\?topic\=test-topic-1\&message\=HelloKafka

# notice the logs

Received message: HelloKafka
```

6. Get metrics

```
curl "http://localhost:8081/actuator/prometheus" |  grep kafka
```