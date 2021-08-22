package at.technikumwien.rotter.esb.Tests;

import at.technikumwien.rotter.esb.Kafka.Consumer;
import at.technikumwien.rotter.esb.Kafka.Producer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class KafkaTest {
    @Autowired
    private Producer producer;

    @Autowired
    private Consumer consumer;

    private final byte[] msg_10 = new byte[10];
    private final byte[] msg_100 = new byte[100];
    private final byte[] msg_1000 = new byte[1000];

    /* ----------- Single Message Sending Test Single partition ----------- */
//    @Test
//    public void singlePartitionSingleMessage10Byte() {
//        System.out.println("Messages existing at start: " + consumer.getTimestampsSize());
//        producer.send("part1",msg_10);
//        System.out.println("Time passed between first message send and last message received: " + consumer.getTime() + "ms");
//    }
//
//    @Test
//    public void singlePartitionMultipleMessage10Byte() {
//        System.out.println("Messages existing at start: " + consumer.getTimestampsSize());
//        for(int i=0; i<1_000;i++)
//            producer.send("part1",msg_10);
//        System.out.println("Time passed between first message send and last message received: " + consumer.getTime() + "ms");
//    }

    /* ----------- Multiple Message Sending Test Single partition 100 runs for averaging ----------- */
    @Test
    public void singlePartitionMultipleMessage10ByteAveragingTimeOver100Runs() {
        List<Long> starts = new ArrayList<>();
        List<Long> times = new ArrayList<>();
        for(int i=0; i<100;i++) {
            starts.add(consumer.getTimestampsSize());
            for(int j=0; j<5_00;j++)
                producer.send("part1",msg_10);
            times.add(consumer.getTime());
        }
        for(Long start : starts)
            Assertions.assertEquals(start,0L);

        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println("Average time over 1000 iterations: " + avg + "ms");
    }

    @Test
    public void singlePartitionMultipleMessage100ByteAveragingTimeOver100Runs() {
        List<Long> starts = new ArrayList<>();
        List<Long> times = new ArrayList<>();
        for(int i=0; i<100;i++) {
            starts.add(consumer.getTimestampsSize());
            for(int j=0; j<5_00;j++)
                producer.send("part1",msg_100);
            times.add(consumer.getTime());
        }
        for(Long start : starts)
            Assertions.assertEquals(start,0L);

        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println("Average time over 1000 iterations: " + avg + "ms");
    }

    @Test
    public void singlePartitionMultipleMessage1000ByteAveragingTimeOver100Runs() {
        List<Long> starts = new ArrayList<>();
        List<Long> times = new ArrayList<>();
        for(int i=0; i<100;i++) {
            starts.add(consumer.getTimestampsSize());
            for(int j=0; j<5_00;j++)
                producer.send("part1",msg_1000);
            times.add(consumer.getTime());
        }
        for(Long start : starts)
            Assertions.assertEquals(start,0L);

        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println("Average time over 1000 iterations: " + avg + "ms");
    }

    /* ----------- Multiple Message Sending Test 4 partitions 100 runs for averaging ----------- */
    @Test
    public void fourPartitionMultipleMessage10ByteAveragingTimeOver100Runs() {
        List<Long> starts = new ArrayList<>();
        List<Long> times = new ArrayList<>();
        for(int i=0; i<100;i++) {
            starts.add(consumer.getTimestampsSize());
            for(int j=0; j<5_00;j++)
                producer.send("part4",msg_10);
            times.add(consumer.getTime());
        }
        for(Long start : starts)
            Assertions.assertEquals(start,0L);

        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println("Average time over 1000 iterations: " + avg + "ms");
    }

    @Test
    public void fourPartitionMultipleMessage100ByteAveragingTimeOver100Runs() {
        List<Long> starts = new ArrayList<>();
        List<Long> times = new ArrayList<>();
        for(int i=0; i<100;i++) {
            starts.add(consumer.getTimestampsSize());
            for(int j=0; j<5_00;j++)
                producer.send("part4",msg_100);
            times.add(consumer.getTime());
        }
        for(Long start : starts)
            Assertions.assertEquals(start,0L);

        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println("Average time over 1000 iterations: " + avg + "ms");
    }

    @Test
    public void fourPartitionMultipleMessage1000ByteAveragingTimeOver100Runs() {
        List<Long> starts = new ArrayList<>();
        List<Long> times = new ArrayList<>();
        for(int i=0; i<100;i++) {
            starts.add(consumer.getTimestampsSize());
            for(int j=0; j<5_00;j++)
                producer.send("part4",msg_1000);
            times.add(consumer.getTime());
        }
        for(Long start : starts)
            Assertions.assertEquals(start,0L);

        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println("Average time over 1000 iterations: " + avg + "ms");
    }

    /* ----------- Multiple Message Sending Test 16 partitions 100 runs for averaging ----------- */
    @Test
    public void sixteenPartitionMultipleMessage10ByteAveragingTimeOver100Runs() {
        List<Long> starts = new ArrayList<>();
        List<Long> times = new ArrayList<>();
        for(int i=0; i<100;i++) {
            starts.add(consumer.getTimestampsSize());
            for(int j=0; j<5_00;j++)
                producer.send("part16",msg_10);
            times.add(consumer.getTime());
        }
        for(Long start : starts)
            Assertions.assertEquals(start,0L);

        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println("Average time over 1000 iterations: " + avg + "ms");
    }

    @Test
    public void sixteenPartitionMultipleMessage100ByteAveragingTimeOver100Runs() {
        List<Long> starts = new ArrayList<>();
        List<Long> times = new ArrayList<>();
        for(int i=0; i<100;i++) {
            starts.add(consumer.getTimestampsSize());
            for(int j=0; j<5_00;j++)
                producer.send("part16",msg_100);
            times.add(consumer.getTime());
        }
        for(Long start : starts)
            Assertions.assertEquals(start,0L);

        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println("Average time over 1000 iterations: " + avg + "ms");
    }

    @Test
    public void sixteenPartitionMultipleMessage1000ByteAveragingTimeOver100Runs() {
        List<Long> starts = new ArrayList<>();
        List<Long> times = new ArrayList<>();
        for(int i=0; i<100;i++) {
            starts.add(consumer.getTimestampsSize());
            for(int j=0; j<5_00;j++)
                producer.send("part16",msg_1000);
            times.add(consumer.getTime());
        }
        for(Long start : starts)
            Assertions.assertEquals(start,0L);

        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println("Average time over 1000 iterations: " + avg + "ms");
    }





}
