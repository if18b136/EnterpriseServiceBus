package at.technikumwien.rotter.esb.Kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;

@Service
public class Producer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);
    private final KafkaTemplate<String, byte[]> kafkaTemplate;
    private final byte[] msg =  new byte[1000];

    public Producer(KafkaTemplate<String, byte[]> template) { this.kafkaTemplate = template; }

    public void send(String topic) {
//        try{
            //LOGGER.info("Timestamp='{}' - sending payload='{}' to topic='{}'", kafkaTemplate.send(TOPIC, msg).get().getRecordMetadata().timestamp(), msg, TOPIC);
            kafkaTemplate.send(topic,msg);
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }
    }

    public void send(String topic, byte[] msg) {
        kafkaTemplate.send(topic,msg);
    }

}
