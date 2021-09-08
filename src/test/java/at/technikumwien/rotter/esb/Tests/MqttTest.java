package at.technikumwien.rotter.esb.Tests;

import at.technikumwien.rotter.esb.MQTT.MqttSender;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.Callable;

public class MqttTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(MqttTest.class);

    private static final Map<String, Long> results = new TreeMap<>();
    private static final long runs = 100;

    private static String publisherId, subscriberId;
    private static MqttClient publisher, subscriber;
    private static MqttConnectOptions options;

    private final String TOPIC = "benchmark";

    @BeforeAll
    private static void init() {
        options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);  // cleans cache after client disconnect
        options.setConnectionTimeout(10);
        options.setKeepAliveInterval(60);
        options.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1);
    }

    @BeforeEach
    private void reset() throws MqttException {
        publisherId = UUID.randomUUID().toString();
        subscriberId = UUID.randomUUID().toString();

        MemoryPersistence persistence = new MemoryPersistence();

        publisher = new MqttClient("tcp://localhost:1883", publisherId, persistence);
        subscriber = new MqttClient("tcp://localhost:1883", subscriberId, persistence);

        publisher.connect(options);
        subscriber.connect(options);
    }

    @AfterEach
    private void cleanup() throws MqttException {
        publisher.disconnect();
        publisher.close();

        subscriber.disconnect();
        subscriber.close();
    }

    @AfterAll
    private static void printResults() {
        results.forEach((str,l) -> System.out.println(str + " - " + l + " ms"));
    }

//    @Test
    public void sendSingleMessage() throws Exception {
        String publisherId = UUID.randomUUID().toString();
        MqttClient publisher = new MqttClient("tcp://localhost:1883", publisherId);

        String subscriberId = UUID.randomUUID().toString();
        MqttClient subscriber = new MqttClient("tcp://localhost:1883",subscriberId);

        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);

        subscriber.connect(options);
        publisher.connect(options);

        subscriber.subscribe(TOPIC, (topic, msg) -> {
            byte[] payload = msg.getPayload();
            LOGGER.info("[I46] Message received: topic={}, payload={}", topic, new String(payload));
        });

        Callable<Void> target = new MqttSender(publisher);
        LOGGER.info("Before call.");
        target.call();
        LOGGER.info("After call.");
    }

    private long sendMessage(int messages, int size)  throws MqttException, InterruptedException {
        List<Long> times = new ArrayList<>();

        subscriber.subscribe(TOPIC, (topic, msg) -> times.add(System.currentTimeMillis()));
        MqttSender target = new MqttSender(publisher);
        target.setMsg(size);

        times.add(System.currentTimeMillis());
        for (int j = 0; j<messages; j++) {
            target.call();
        }

        while (times.size() < messages) {
            Thread.sleep(10);
        }
        return (times.get(times.size()-1) - times.get(0));
    }

    /* ----------- Multiple Message Sending Test - 10 Byte Message Size - 100 runs for averaging ----------- */
    @Test
    public void send100Msg_10Byte() throws MqttException, InterruptedException {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(100,10));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        results.put("01: 100Msg 10B", avg);
    }

    @Test
    public void send500Msg_10Byte() throws MqttException, InterruptedException {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(500,10));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        results.put("02: 500Msg 10B", avg);
    }

    @Test
    public void send1000Msg_10Byte() throws MqttException, InterruptedException {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(1000,10));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        results.put("03: 1.000Msg 10B", avg);
    }

    @Test
    public void send1500Msg_10Byte() throws MqttException, InterruptedException {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(1500,10));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        results.put("04: 1.500Msg 10B", avg);
    }

    @Test
    public void send2000Msg_10Byte() throws MqttException, InterruptedException {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(2000,10));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        results.put("05: 2.000Msg 10B", avg);
    }

    /* ----------- Multiple Message Sending Test - 100 Byte Message Size - 100 runs for averaging ----------- */
    @Test
    public void send100Msg_100Byte() throws MqttException, InterruptedException {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(100,100));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        results.put("06: 100Msg 100B", avg);
    }

    @Test
    public void send500Msg_100Byte() throws MqttException, InterruptedException {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(500,100));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        results.put("07: 500Msg 100B", avg);
    }

    @Test
    public void send1000Msg_100Byte() throws MqttException, InterruptedException {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(1000,100));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        results.put("08: 1.000Msg 100B", avg);
    }

    @Test
    public void send1500Msg_100Byte() throws MqttException, InterruptedException {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(1500,100));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        results.put("09: 1.500Msg 100B", avg);
    }

    @Test
    public void send2000Msg_100Byte() throws MqttException, InterruptedException {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(2000,100));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        results.put("10: 2.000Msg 100B", avg);
    }

    /* ----------- Multiple Message Sending Test - 1000 Byte Message Size - 100 runs for averaging ----------- */
    @Test
    public void send100Msg_1000Byte() throws MqttException, InterruptedException {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(100,1000));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        results.put("11: 100Msg 1.000B", avg);
    }

    @Test
    public void send500Msg_1000Byte() throws MqttException, InterruptedException {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(500,1000));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        results.put("12: 500Msg 1.000B", avg);
    }

    @Test
    public void send1000Msg_1000Byte() throws MqttException, InterruptedException {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(1000,1000));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        results.put("13: 1.000Msg 1.000B", avg);
    }

    @Test
    public void send1500Msg_1000Byte() throws MqttException, InterruptedException {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(1500,1000));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        results.put("14: 1.500Msg 1.000B", avg);
    }

    @Test
    public void send2000Msg_1000Byte() throws MqttException, InterruptedException {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(2000,1000));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        results.put("15: 2.000Msg 1.000B", avg);
    }

    /* ----------- Multiple Message Sending Test - 10.000 Byte Message Size - 100 runs for averaging ----------- */
    @Test
    public void send100Msg_10000Byte() throws MqttException, InterruptedException {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(100,10000));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        results.put("16: 100Msg 10.000B", avg);
    }

    @Test
    public void send500Msg_10000Byte() throws MqttException, InterruptedException {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(500,10000));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        results.put("17: 500Msg 10.000B", avg);
    }

    @Test
    public void send1000Msg_10000Byte() throws MqttException, InterruptedException {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(1000,10000));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        results.put("18: 1.000Msg 10.000B", avg);
    }

    @Test
    public void send1500Msg_10000Byte() throws MqttException, InterruptedException {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(1500,10000));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        results.put("19: 1.500Msg 10.000B", avg);
    }

    @Test
    public void send2000Msg_10000Byte() throws MqttException, InterruptedException {
        List<Long> times = new ArrayList<>();
        for (int i=0; i<runs;i++)
            times.add(sendMessage(2000,10000));
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        results.put("20: 2.000Msg 10.000B", avg);
    }

//    //@Test
//    public void send100Msg_10ByteSIMPLE() throws MqttException, InterruptedException {
//        List<Long> times = new ArrayList<>();
//
//        subscriber.subscribe(TOPIC, (topic, msg) -> {
//            byte[] payload = msg.getPayload();
//            LOGGER.info("Message received: topic={}, payload={}", topic, new String(payload));
//            times.add(System.currentTimeMillis());
//        });
//        MqttMessage message = new MqttMessage(str_10.getBytes(StandardCharsets.UTF_8));
//        message.setQos(0);
//
//        times.add(System.currentTimeMillis());
//        for (int i = 0; i<100; i++) {
//           publisher.publish(TOPIC,message);
//        }
//        Thread.sleep(2000);
//        LOGGER.info("Messages received={}, time needed={}[ms]", times.size()-1, (times.get(times.size()-1) - times.get(0)));
//    }
//
//    //@Test
//    public void sendMultipleMessages() throws Exception {
//        List<Long> times = new ArrayList<>();
//
//        String publisherId = UUID.randomUUID().toString();
//        MqttClient publisher = new MqttClient("tcp://localhost:1883",publisherId);
//
//        String subscriberId = UUID.randomUUID().toString();
//        MqttClient subscriber = new MqttClient("tcp://localhost:1883",subscriberId);
//
//        MqttConnectOptions options = new MqttConnectOptions();
//        options.setAutomaticReconnect(true);
//        options.setCleanSession(true);
//        options.setConnectionTimeout(10);
//
//
//        publisher.connect(options);
//        subscriber.connect(options);
//
//        subscriber.subscribe(TOPIC, (topic, msg) -> {
//            byte[] payload = msg.getPayload();
//            LOGGER.info("[I82] Message received: topic={}, payload={}", topic, new String(payload));
//            times.add(System.currentTimeMillis());
//        });
//
//        Callable<Void> target = new MqttSender(publisher);
//        LOGGER.info("Before call.");
//        times.add(System.currentTimeMillis());
//        for (int i = 0; i<100; i++) {
//            target.call();
//        }
//        LOGGER.info("After call.");
//        Thread.sleep(2000);
//        LOGGER.info("Messages received={}, time needed={}[ms]", times.size()-1, (times.get(times.size()-1) - times.get(0)));
//    }
//
//    //@Test
//    public void MosquittoTest() {
//        int qos             =  0;
//        String broker       = "tcp://localhost:1883";
//        String PubId        = "127.0.0.1";
//        byte[] msg = new byte[100];
//        MemoryPersistence persistence = new MemoryPersistence();
//        try {
//            MqttClient sampleClient = new MqttClient(broker, PubId, persistence);
//            MqttConnectOptions connOpts = new MqttConnectOptions();
//            connOpts.setCleanSession(true);
//            connOpts.setConnectionTimeout(60);
//            connOpts.setKeepAliveInterval(60);
//            connOpts.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1);
//            System.out.println("Connecting to broker: "+ broker);
//            sampleClient.connect(connOpts);
//            System.out.println("Connected");
//            System.out.println("Publishing message: "+ Arrays.toString(msg));
//            MqttMessage message = new MqttMessage(msg);
//            message.setQos(qos);
//            sampleClient.publish(TOPIC,message);
//            System.out.println("Message published");
//        } catch(MqttException me) {
//            System.out.println("Reason :"+ me.getReasonCode());
//            System.out.println("Message :"+ me.getMessage());
//            System.out.println("Local :"+ me.getLocalizedMessage());
//            System.out.println("Cause :"+ me.getCause());
//            System.out.println("Exception :"+ me);
//            me.printStackTrace();
//        }
//    }
}
