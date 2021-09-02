package at.technikumwien.rotter.esb.MQTT;

import at.technikumwien.rotter.esb.Kafka.KafkaProducer;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

public class MqttSender implements Callable<Void> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MqttSender.class);
    private static final String TOPIC = "benchmark";
    private IMqttClient client;

    public MqttSender(IMqttClient client) {
        this.client = client;
    }

    @Override
    public Void call() throws MqttException {
        if(!client.isConnected()) {
            LOGGER.error("[I31] Client not connected.");
            return null;
        }

        MqttMessage msg = new MqttMessage(new byte[100]);
        msg.setQos(0);
        msg.setRetained(true);
        client.publish(TOPIC,msg);

        return null;
    }
}
