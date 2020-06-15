### Relevant articles

- [Intro to Apache Kafka with Spring](http://www.baeldung.com/spring-kafka)



# Spring Kafka Messaging

This is a simple Spring Boot app to demonstrate sending and receiving of messages in Kafka using spring-kafka.

 Srart the Spring Boot Application

  mvn spring-boot:run

When the application runs successfully, following output is logged on to console (along with spring logs):

 

   kafka-console-consumer --bootstrap-server localhost:9092 --topic TestTopic  --from-beginning

 listeners with groups foo and bar
>Received Message in group 'foo': Hello, World!<br>
Received Message in group 'bar': Hello, World!



#### Message received from the TestTopic  topic, with the partition info
>Received Message: Hello, World! from partition: 0

#### Message received from the 'partitionedTopic' topic, only from specific partitions

kafka-console-consumer --bootstrap-server localhost:9092 --topic partitionedTopic --from-beginning
 
>Received Message: Hello To Partioned Topic! from partition: 0<br>
Received Message: Hello To Partioned Topic! from partition: 3

#### Message received from the 'filteredTopic' topic after filtering

kafka-console-consumer --bootstrap-server localhost:9092 --topic filteredTopic  --from-beginning
 


>Received Message in filtered listener: Hello Topic!

#### Message (Serialized Java Object) received from the 'greetingTopic' topic
kafka-console-consumer --bootstrap-server localhost:9092 --topic greetingTopic  --from-beginning

>Received greeting message: Greetings, World!!
