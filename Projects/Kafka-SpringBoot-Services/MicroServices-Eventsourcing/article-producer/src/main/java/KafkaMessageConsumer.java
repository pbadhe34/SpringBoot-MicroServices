

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.apache.kafka.clients.consumer.ConsumerConfig.*;
import static org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG;

 
 public class KafkaMessageConsumer   {

    public static Logger logger = LoggerFactory.getLogger(KafkaMessageConsumer.class);

	 

    private static Gson gson = new Gson();

    public static  Map<String, Object> config() {

        Map<String, Object> props = new HashMap<>();
        props.put(BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(GROUP_ID_CONFIG, "historie-4");
        props.put(ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.put(SESSION_TIMEOUT_MS_CONFIG, "30000");
        props.put(KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

        return props;
    }

    public static void main(String args[]) {

    	System.out.println("The KafkaMessageConsumer is starting");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(config());

        consumer.subscribe(Arrays.asList("articles"), new OffsetBeginningRebalanceListener(consumer, "articles"));

        JsonParser parser = new JsonParser();

        try {

            while (true) {

                ConsumerRecords<String, String> records = consumer.poll(1000);

                if (records.isEmpty())
                    continue;

                for (ConsumerRecord<String, String> cr : records) {
                   

                    //  @Consumer(topic="articles")

                    JsonObject json = parser.parse(cr.value()).getAsJsonObject();

                    String action = json.getAsJsonPrimitive("action").getAsString();

                    JsonObject object = json.getAsJsonObject("object");

                    System.out.println("----------------------------------------------------------------------------------");
                    System.out.println("Service-App1 KafkaListener  Reading from Articles Topic");
                    
                    System.out.println("Offset: " + cr.offset());
                    System.out.println("Key: "+ cr.key());
                    System.out.println("Action: " + action);
                    System.out.println("Object: " + object);

                    Article article = gson.fromJson(object, Article.class);

                    switch (action) {
                        case "update":
                        	  System.out.println("Update action in KafkaMessageConsumer"); 
                        	  break;
                        case "create":
                        	System.out.println("Create action in KafkaMessageConsumer"); 
                            break;
                        case "delete":
                        	System.out.println("Delete action in KafkaMessageConsumer"); 
                            break;

                    }


                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }
    }
}



