package at.technikumwien.rotter.esb.MQTT;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.Callable;

public class MqttSender implements Callable<Void> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MqttSender.class);
    private static final String TOPIC = "benchmark";
    private IMqttClient client;

    private final byte[] msg_10 = new byte[10];
    private final byte[] msg_100 = new byte[100];
    private final byte[] msg_1000 = new byte[1000];
    private final byte[] msg_10000 = new byte[10000];

    private final String str_10 = Arrays.toString(msg_10).replaceAll("[ ,\\[\\]]","");
    private final String str_100 = Arrays.toString(msg_100).replaceAll("[ ,\\[\\]]","");
    private final String str_1000 = Arrays.toString(msg_1000).replaceAll("[ ,\\[\\]]","");
    private final String str_10000 = Arrays.toString(msg_10000).replaceAll("[ ,\\[\\]]","");

    public static byte[] msg = null;

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

    public MqttSender(IMqttClient client) {
        this.client = client;
    }

    @Override
    public Void call() throws MqttException {
        if(!client.isConnected()) {
            LOGGER.error("Client not connected.");
            return null;
        }

        MqttMessage message = new MqttMessage(msg); // setMsg needs to be called before using call()
        message.setQos(0);
        //message.setRetained(true);
        client.publish(TOPIC,message);

        return null;
    }

}
