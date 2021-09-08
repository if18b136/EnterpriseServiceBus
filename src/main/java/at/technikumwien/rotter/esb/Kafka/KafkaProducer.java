package at.technikumwien.rotter.esb.Kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Service
public class KafkaProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);
    private final KafkaTemplate<String, byte[]> kafkaTemplate;
    private static byte[] msg =  null;

    private final byte[] msg_10 = new byte[10];
    private final byte[] msg_100 = new byte[100];
    private final byte[] msg_1000 = new byte[1000];
    private final byte[] msg_10000 = new byte[10000];

    private final String str_10 = Arrays.toString(msg_10).replaceAll("[ ,\\[\\]]","");
    private final String str_100 = Arrays.toString(msg_100).replaceAll("[ ,\\[\\]]","");
    private final String str_1000 = Arrays.toString(msg_1000).replaceAll("[ ,\\[\\]]","");
    private final String str_10000 = Arrays.toString(msg_10000).replaceAll("[ ,\\[\\]]","");


    public KafkaProducer(KafkaTemplate<String, byte[]> template) { this.kafkaTemplate = template; }

    public void setMsg(int num) {
        switch (num) {
            case 10:
                msg = str_10.getBytes(StandardCharsets.UTF_8);
                break;
            case 100:
                msg = str_100.getBytes(StandardCharsets.UTF_8);
                break;
            case 1000:
                msg = str_1000.getBytes(StandardCharsets.UTF_8);
                break;
            case 10000:
                msg = str_10000.getBytes(StandardCharsets.UTF_8);
                break;
        }
    }

    public void send(String topic, byte[] msg) {
        kafkaTemplate.send(topic,msg);
    }

    public void send(String topic) {    // sending with predefined message for more tests
        kafkaTemplate.send(topic,msg);
    }


}
