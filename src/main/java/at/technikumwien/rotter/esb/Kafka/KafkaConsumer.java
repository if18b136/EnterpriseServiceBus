package at.technikumwien.rotter.esb.Kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);
    private List<Long[]> timestamps = new ArrayList<>();

    public KafkaConsumer(){}

    @KafkaListener(topics = "part1")
    public void consumeSaveTimestamp1(ConsumerRecord<?,?> consumerRecord){
        //LOGGER.info("Received payload, time needed='{}'", System.currentTimeMillis()-consumerRecord.timestamp());
        timestamps.add(new Long[]{consumerRecord.timestamp(),System.currentTimeMillis()});
    }

    @KafkaListener(topics = "part4")
    public void consumeSaveTimestamp4(ConsumerRecord<?,?> consumerRecord){
        //LOGGER.info("Received payload, time needed='{}'", System.currentTimeMillis()-consumerRecord.timestamp());
        timestamps.add(new Long[]{consumerRecord.timestamp(),System.currentTimeMillis()});
    }

    @KafkaListener(topics = "part16")
    public void consumeSaveTimestamp16(ConsumerRecord<?,?> consumerRecord){
        //LOGGER.info("Received payload, time needed='{}'", System.currentTimeMillis()-consumerRecord.timestamp());
        timestamps.add(new Long[]{consumerRecord.timestamp(),System.currentTimeMillis()});
    }
    
    public long getTime() {
        long time = 0;
        try{Thread.sleep(100);} catch (Exception e) { e.printStackTrace();}
        if(!timestamps.isEmpty()) {
            System.out.println("Number of messages received: " + timestamps.size());
            time = timestamps.get(timestamps.size()-1)[1] - timestamps.get(0)[0];
            timestamps.clear();
        }
        return time;
    }

    public long getTimestampsSize() { return timestamps.size(); }

}
