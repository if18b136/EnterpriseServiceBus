package at.technikumwien.rotter.esb.Tests;

import at.technikumwien.rotter.esb.Kafka.KafkaConsumer;
import at.technikumwien.rotter.esb.Kafka.KafkaProducer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
public class KafkaTest {
    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private KafkaConsumer kafkaConsumer;

    private static final Map<String, Long> results = new TreeMap<>();
    private static final long runs = 100;

    @AfterAll
    private static void printResults() {
        results.forEach((str,l) -> System.out.println(str + " - " + l + " ms"));
    }

    private long sendMessage(int messages, int size, String partition) {
        List<Long> starts = new ArrayList<>();
        List<Long> times = new ArrayList<>();

        kafkaProducer.setMsg(size);

        starts.add(kafkaConsumer.getTimestampsSize());
        for(int j=0; j<messages;j++)
            kafkaProducer.send(partition);
        times.add(kafkaConsumer.getTime(messages));

        for(Long start : starts)
            Assertions.assertEquals(start,0L);

        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        return avg;
    }

    /* ----------- Multiple Message Sending Test - 10 Byte Message Size, 1 Partition - 100 runs for averaging ----------- */
    @Test
    public void send_100Msg_10B_1Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(100,10, "part1"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("01: 100Msg 10B 1Part", avg);
    }

    @Test
    public void send_500Msg_10B_1Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(500,10, "part1"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("02: 500Msg 10B 1Part", avg);
    }

    @Test
    public void send_1000Msg_10B_1Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(1000,10, "part1"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("03: 1.000Msg 10B 1Part", avg);
    }

    @Test
    public void send_1500Msg_10B_1Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(1500,10, "part1"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("04: 1.500Msg 10B 1Part", avg);
    }

    @Test
    public void send_2000Msg_10B_1Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(2000,10, "part1"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("05: 2.000Msg 10B 1Part", avg);
    }

    /* ----------- Multiple Message Sending Test - 100 Byte Message Size, 1 Partition - 100 runs for averaging ----------- */
    @Test
    public void send_100Msg_100B_1Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(100,100, "part1"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("06: 100Msg 100B 1Part", avg);
    }

    @Test
    public void send_500Msg_100B_1Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(500,100, "part1"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("07: 500Msg 100B 1Part", avg);
    }

    @Test
    public void send_1000Msg_100B_1Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(1000,100, "part1"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("08: 1.000Msg 100B 1Part", avg);
    }

    @Test
    public void send_1500Msg_100B_1Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(1500,100, "part1"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("09: 1.500Msg 100B 1Part", avg);
    }

    @Test
    public void send_2000Msg_100B_1Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(2000,100, "part1"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("10: 2.000Msg 100B 1Part", avg);
    }

    /* ----------- Multiple Message Sending Test - 1000 Byte Message Size, 1 Partition - 100 runs for averaging ----------- */
    @Test
    public void send_100Msg_1000B_1Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(100,1000, "part1"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("11: 100Msg 1.000B 1Part", avg);
    }

    @Test
    public void send_500Msg_1000B_1Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(500,1000, "part1"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("12: 500Msg 1.000B 1Part", avg);
    }

    @Test
    public void send_1000Msg_1000B_1Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(1000,1000, "part1"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("13: 1.000Msg 1.000B 1Part", avg);
    }

    @Test
    public void send_1500Msg_1000B_1Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(1500,1000, "part1"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("14: 1.500Msg 1.000B 1Part", avg);
    }

    @Test
    public void send_2000Msg_1000B_1Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(2000,1000, "part1"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("15: 2.000Msg 1.000B 1Part", avg);
    }

    /* ----------- Multiple Message Sending Test - 10.000 Byte Message Size, 1 Partition - 100 runs for averaging ----------- */
    @Test
    public void send_100Msg_10000B_1Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(100,10000, "part1"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("16: 100Msg 10.000B 1Part", avg);
    }

    @Test
    public void send_500Msg_10000B_1Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(500,10000, "part1"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("17: 500Msg 10.000B 1Part", avg);
    }

    @Test
    public void send_1000Msg_10000B_1Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(1000,10000, "part1"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("18: 1.000Msg 10.000B 1Part", avg);
    }

    @Test
    public void send_1500Msg_10000B_1Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(1500,10000, "part1"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("19: 1.500Msg 10.000B 1Part", avg);
    }

    @Test
    public void send_2000Msg_10000B_1Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(2000,10000, "part1"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("20: 2.000Msg 10.000B 1Part", avg);
    }

// 4 PARTITIONS
    /* ----------- Multiple Message Sending Test - 10 Byte Message Size, 4 Partition - 100 runs for averaging ----------- */
    @Test
    public void send_100Msg_10B_4Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(100,10, "part4"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("21: 100Msg 10B 4Part", avg);
    }

    @Test
    public void send_500Msg_10B_4Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(500,10, "part4"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("22: 500Msg 10B 4Part", avg);
    }

    @Test
    public void send_1000Msg_10B_4Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(1000,10, "part4"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("23: 1.000Msg 10B 4Part", avg);
    }

    @Test
    public void send_1500Msg_10B_4Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(1500,10, "part4"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("24: 1.500Msg 10B 4Part", avg);
    }

    @Test
    public void send_2000Msg_10B_4Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(2000,10, "part4"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("25: 2.000Msg 10B 4Part", avg);
    }

    /* ----------- Multiple Message Sending Test - 100 Byte Message Size, 4 Partition - 100 runs for averaging ----------- */
    @Test
    public void send_100Msg_100B_4Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(100,100, "part4"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("26: 100Msg 100B 4Part", avg);
    }

    @Test
    public void send_500Msg_100B_4Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(500,100, "part4"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("27: 500Msg 100B 4Part", avg);
    }

    @Test
    public void send_1000Msg_100B_4Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(1000,100, "part4"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("28: 1.000Msg 100B 4Part", avg);
    }

    @Test
    public void send_1500Msg_100B_4Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(1500,100, "part4"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("29: 1.500Msg 100B 4Part", avg);
    }

    @Test
    public void send_2000Msg_100B_4Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(2000,100, "part4"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("30: 2.000Msg 100B 4Part", avg);
    }

    /* ----------- Multiple Message Sending Test - 1000 Byte Message Size, 4 Partition - 100 runs for averaging ----------- */
    @Test
    public void send_100Msg_1000B_4Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(100,1000, "part4"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("31: 100Msg 1.000B 4Part", avg);
    }

    @Test
    public void send_500Msg_1000B_4Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(500,1000, "part4"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("32: 500Msg 1.000B 4Part", avg);
    }

    @Test
    public void send_1000Msg_1000B_4Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(1000,1000, "part4"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("33: 1.000Msg 1.000B 4Part", avg);
    }

    @Test
    public void send_1500Msg_1000B_4Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(1500,1000, "part4"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("34: 1.500Msg 1.000B 4Part", avg);
    }

    @Test
    public void send_2000Msg_1000B_4Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(2000,1000, "part4"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("35: 2.000Msg 1.000B 4Part", avg);
    }

    /* ----------- Multiple Message Sending Test - 10.000 Byte Message Size, 4 Partition - 100 runs for averaging ----------- */
    @Test
    public void send_100Msg_10000B_4Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(100,10000, "part4"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("36: 100Msg 10.000B 4Part", avg);
    }

    @Test
    public void send_500Msg_10000B_4Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(500,10000, "part4"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("37: 500Msg 10.000B 4Part", avg);
    }

    @Test
    public void send_1000Msg_10000B_4Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(1000,10000, "part4"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("38: 1.000Msg 10.000B 4Part", avg);
    }

    @Test
    public void send_1500Msg_10000B_4Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(1500,10000, "part4"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("39: 1.500Msg 10.000B Part", avg);
    }

    @Test
    public void send_2000Msg_10000B_4Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(2000,10000, "part4"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("40: 2.000Msg 10.000B 4Part", avg);
    }

// 16 PARTITIONS
    /* ----------- Multiple Message Sending Test - 10 Byte Message Size, 16 Partition - 100 runs for averaging ----------- */
    @Test
    public void send_100Msg_10B_16Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(100,10, "part16"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("41: 100Msg 10B 16Part", avg);
    }

    @Test
    public void send_500Msg_10B_16Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(500,10, "part16"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("42: 500Msg 10B 16Part", avg);
    }

    @Test
    public void send_1000Msg_10B_16Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(1000,10, "part16"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("43: 1.000Msg 10B 16Part", avg);
    }

    @Test
    public void send_1500Msg_10B_16Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(1500,10, "part16"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("44: 1.500Msg 10B 16Part", avg);
    }

    @Test
    public void send_2000Msg_10B_16Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(2000,10, "part16"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("45: 2.000Msg 10B 16Part", avg);
    }

    /* ----------- Multiple Message Sending Test - 100 Byte Message Size, 16 Partition - 100 runs for averaging ----------- */
    @Test
    public void send_100Msg_100B_16Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(100,100, "part16"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("46: 100Msg 100B 16Part", avg);
    }

    @Test
    public void send_500Msg_100B_16Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(500,100, "part16"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("47: 500Msg 100B 16Part", avg);
    }

    @Test
    public void send_1000Msg_100B_16Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(1000,100, "part16"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("48: 1.000Msg 100B 16Part", avg);
    }

    @Test
    public void send_1500Msg_100B_16Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(1500,100, "part16"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("49: 1.500Msg 100B 16Part", avg);
    }

    @Test
    public void send_2000Msg_100B_16Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(2000,100, "part16"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("50: 2.000Msg 100B 16Part", avg);
    }

    /* ----------- Multiple Message Sending Test - 1000 Byte Message Size, 16 Partition - 100 runs for averaging ----------- */
    @Test
    public void send_100Msg_1000B_16Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(100,1000, "part16"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("51: 100Msg 1.000B 16Part", avg);
    }

    @Test
    public void send_500Msg_1000B_16Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(500,1000, "part16"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("52: 500Msg 1.000B 16Part", avg);
    }

    @Test
    public void send_1000Msg_1000B_16Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(1000,1000, "part16"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("53: 1.000Msg 1.000B 16Part", avg);
    }

    @Test
    public void send_1500Msg_1000B_16Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(1500,1000, "part16"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("54: 1.500Msg 1.000B 16Part", avg);
    }

    @Test
    public void send_2000Msg_1000B_16Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(2000,1000, "part16"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("55: 2.000Msg 1.000B 16Part", avg);
    }

    /* ----------- Multiple Message Sending Test - 10.000 Byte Message Size, 16 Partition - 100 runs for averaging ----------- */
    @Test
    public void send_100Msg_10000B_16Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(100,10000, "part16"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("56: 100Msg 10.000B 16Part", avg);
    }

    @Test
    public void send_500Msg_10000B_16Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(500,10000, "part16"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("57: 500Msg 10.000B 16Part", avg);
    }

    @Test
    public void send_1000Msg_10000B_16Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(1000,10000, "part16"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("58: 1.000Msg 10.000B 16Part", avg);
    }

    @Test
    public void send_1500Msg_10000B_16Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(1500,10000, "part16"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("59: 1.500Msg 10.000B 16Part", avg);
    }

    @Test
    public void send_2000Msg_10000B_16Part() {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(2000,10000, "part16"));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println(avg);
        results.put("60: 2.000Msg 10.000B 16Part", avg);
    }

    // Old tests
//    @Test
//    public void singlePartitionMultipleMessage100ByteAveragingTimeOver100Runs() {
//        List<Long> starts = new ArrayList<>();
//        List<Long> times = new ArrayList<>();
//        for(int i=0; i<runs;i++) {
//            starts.add(kafkaConsumer.getTimestampsSize());
//            for(int j=0; j<5_00;j++)
//                kafkaProducer.send("part1",msg_100);
//            times.add(kafkaConsumer.getTime());
//        }
//        for(Long start : starts)
//            Assertions.assertEquals(start,0L);
//
//        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
//        System.out.println("Average time over 100 iterations: " + avg + "ms");
//    }
//
//    @Test
//    public void singlePartitionMultipleMessage1000ByteAveragingTimeOver100Runs() {
//        List<Long> starts = new ArrayList<>();
//        List<Long> times = new ArrayList<>();
//        for(int i=0; i<runs;i++) {
//            starts.add(kafkaConsumer.getTimestampsSize());
//            for(int j=0; j<5_00;j++)
//                kafkaProducer.send("part1",msg_1000);
//            times.add(kafkaConsumer.getTime());
//        }
//        for(Long start : starts)
//            Assertions.assertEquals(start,0L);
//
//        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
//        System.out.println("Average time over 100 iterations: " + avg + "ms");
//    }
//
//    /* ----------- Multiple Message Sending Test 4 partitions 100 runs for averaging ----------- */
//    @Test
//    public void fourPartitionMultipleMessage10ByteAveragingTimeOver100Runs() {
//        List<Long> starts = new ArrayList<>();
//        List<Long> times = new ArrayList<>();
//        for(int i=0; i<runs;i++) {
//            starts.add(kafkaConsumer.getTimestampsSize());
//            for(int j=0; j<5_00;j++)
//                kafkaProducer.send("part4",msg_10);
//            times.add(kafkaConsumer.getTime());
//        }
//        for(Long start : starts)
//            Assertions.assertEquals(start,0L);
//
//        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
//        System.out.println("Average time over 100 iterations: " + avg + "ms");
//    }
//
//    @Test
//    public void fourPartitionMultipleMessage100ByteAveragingTimeOver100Runs() {
//        List<Long> starts = new ArrayList<>();
//        List<Long> times = new ArrayList<>();
//        for(int i=0; i<runs;i++) {
//            starts.add(kafkaConsumer.getTimestampsSize());
//            for(int j=0; j<5_00;j++)
//                kafkaProducer.send("part4",msg_100);
//            times.add(kafkaConsumer.getTime());
//        }
//        for(Long start : starts)
//            Assertions.assertEquals(start,0L);
//
//        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
//        System.out.println("Average time over 100 iterations: " + avg + "ms");
//    }
//
//    @Test
//    public void fourPartitionMultipleMessage1000ByteAveragingTimeOver100Runs() {
//        List<Long> starts = new ArrayList<>();
//        List<Long> times = new ArrayList<>();
//        for(int i=0; i<runs;i++) {
//            starts.add(kafkaConsumer.getTimestampsSize());
//            for(int j=0; j<5_00;j++)
//                kafkaProducer.send("part4",msg_1000);
//            times.add(kafkaConsumer.getTime());
//        }
//        for(Long start : starts)
//            Assertions.assertEquals(start,0L);
//
//        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
//        System.out.println("Average time over 100 iterations: " + avg + "ms");
//    }
//
//    /* ----------- Multiple Message Sending Test 16 partitions 100 runs for averaging ----------- */
//    @Test
//    public void sixteenPartitionMultipleMessage10ByteAveragingTimeOver100Runs() {
//        List<Long> starts = new ArrayList<>();
//        List<Long> times = new ArrayList<>();
//        for(int i=0; i<runs;i++) {
//            starts.add(kafkaConsumer.getTimestampsSize());
//            for(int j=0; j<5_00;j++)
//                kafkaProducer.send("part16",msg_10);
//            times.add(kafkaConsumer.getTime());
//        }
//        for(Long start : starts)
//            Assertions.assertEquals(start,0L);
//
//        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
//        System.out.println("Average time over 100 iterations: " + avg + "ms");
//    }
//
//    @Test
//    public void sixteenPartitionMultipleMessage100ByteAveragingTimeOver100Runs() {
//        List<Long> starts = new ArrayList<>();
//        List<Long> times = new ArrayList<>();
//        for(int i=0; i<runs;i++) {
//            starts.add(kafkaConsumer.getTimestampsSize());
//            for(int j=0; j<5_00;j++)
//                kafkaProducer.send("part16",msg_100);
//            times.add(kafkaConsumer.getTime());
//        }
//        for(Long start : starts)
//            Assertions.assertEquals(start,0L);
//
//        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
//        System.out.println("Average time over 100 iterations: " + avg + "ms");
//    }
//
//    @Test
//    public void sixteenPartitionMultipleMessage1000ByteAveragingTimeOver100Runs() {
//        List<Long> starts = new ArrayList<>();
//        List<Long> times = new ArrayList<>();
//        for(int i=0; i<runs;i++) {
//            starts.add(kafkaConsumer.getTimestampsSize());
//            for(int j=0; j<5_00;j++)
//                kafkaProducer.send("part16",msg_1000);
//            times.add(kafkaConsumer.getTime());
//        }
//        for(Long start : starts)
//            Assertions.assertEquals(start,0L);
//
//        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
//        System.out.println("Average time over 100 iterations: " + avg + "ms");
//    }

}
