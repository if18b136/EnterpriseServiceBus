package at.technikumwien.rotter.esb;

import at.technikumwien.rotter.esb.Kafka.KafkaConsumer;
import at.technikumwien.rotter.esb.Kafka.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cmd")
public class Controller {
    private final KafkaProducer kafkaProducer;
    private final KafkaConsumer kafkaConsumer;
    private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);

    @Autowired
    public Controller(KafkaProducer kafkaProducer, KafkaConsumer kafkaConsumer) {
        this.kafkaProducer = kafkaProducer;
        this.kafkaConsumer = kafkaConsumer;
    }

    @GetMapping("/single")
    public void sendSingleMessage() /*throws ExecutionException, InterruptedException*/ {

    }

    @GetMapping("/multiple/1")
    public void sendMultipleMessages1() {
        long start = System.currentTimeMillis();
        sendMultipleMessages("part1");
        LOGGER.info("Producer sending time with 1 Partition: {}", System.currentTimeMillis()-start);
        LOGGER.info("Time according to timestamps with 1 Partition: {}", kafkaConsumer.getTime());
    }

    @GetMapping("/multiple/4")
    public void sendMultipleMessages4() {
        long start = System.currentTimeMillis();
        sendMultipleMessages("part4");
        LOGGER.info("Producer sending time with 4 Partitions: {}", System.currentTimeMillis()-start);
        LOGGER.info("Time according to timestamps with 4 Partitions: {}", kafkaConsumer.getTime());
    }

    @GetMapping("/multiple/16")
    public void sendMultipleMessages16() {
        long start = System.currentTimeMillis();
        sendMultipleMessages("part16");
        LOGGER.info("Producer sending time with 16 partitions: {}", System.currentTimeMillis()-start);
        LOGGER.info("Time according to timestamps with 16 partitions: {}", kafkaConsumer.getTime());
    }

    private void sendMultipleMessages(String topic) {
        for(int i = 0; i < 1_000_000; i++) {
            kafkaProducer.send(topic);
        }
    }
}
