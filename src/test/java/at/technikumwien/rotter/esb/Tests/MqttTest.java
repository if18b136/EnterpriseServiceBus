package at.technikumwien.rotter.esb.Tests;

import at.technikumwien.rotter.esb.MQTT.MqttSender;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;

public class MqttTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(MqttTest.class);

    private final String TOPIC = "benchmark";

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

    @Test
    public void sendMultipleMessages() throws Exception {
        List<Long> times = new ArrayList<>();

        String publisherId = UUID.randomUUID().toString();
        MqttClient publisher = new MqttClient("tcp://localhost:1883",publisherId);

        String subscriberId = UUID.randomUUID().toString();
        MqttClient subscriber = new MqttClient("tcp://localhost:1883",subscriberId);

        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);


        publisher.connect(options);
        subscriber.connect(options);

        subscriber.subscribe(TOPIC, (topic, msg) -> {
            byte[] payload = msg.getPayload();
            LOGGER.info("[I82] Message received: topic={}, payload={}", topic, new String(payload));
            times.add(System.currentTimeMillis());
        });

        Callable<Void> target = new MqttSender(publisher);
        LOGGER.info("Before call.");
        times.add(System.currentTimeMillis());
        for (int i = 0; i<100; i++) {
            target.call();
        }
        LOGGER.info("After call.");
        Thread.sleep(2000);
        LOGGER.info("Messages received={}, time needed={}[ms]", times.size()-1, (times.get(times.size()-1) - times.get(0)));
    }

//    @Test
    public void MosquittoTest() {
        int qos             =  0;
        String broker       = "tcp://localhost:1883";
        String PubId        = "127.0.0.1";
        byte[] msg = new byte[100];
        MemoryPersistence persistence = new MemoryPersistence();
        try {
            MqttClient sampleClient = new MqttClient(broker, PubId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            connOpts.setConnectionTimeout(60);
            connOpts.setKeepAliveInterval(60);
            connOpts.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1);
            System.out.println("Connecting to broker: "+ broker);
            sampleClient.connect(connOpts);
            System.out.println("Connected");
            System.out.println("Publishing message: "+ Arrays.toString(msg));
            MqttMessage message = new MqttMessage(msg);
            message.setQos(qos);
            sampleClient.publish(TOPIC,message);
            System.out.println("Message published");
        } catch(MqttException me) {
            System.out.println("Reason :"+ me.getReasonCode());
            System.out.println("Message :"+ me.getMessage());
            System.out.println("Local :"+ me.getLocalizedMessage());
            System.out.println("Cause :"+ me.getCause());
            System.out.println("Exception :"+ me);
            me.printStackTrace();
        }
    }
}
