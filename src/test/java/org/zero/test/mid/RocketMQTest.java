package org.zero.test.mid;

import org.apache.rocketmq.client.apis.*;
import org.apache.rocketmq.client.apis.message.Message;
import org.apache.rocketmq.client.apis.producer.Producer;
import org.apache.rocketmq.client.apis.producer.SendReceipt;

import java.io.IOException;
import java.time.Duration;

public class RocketMQTest {
    public static void main(String[] args) throws ClientException, IOException {
        String endpoint = "127.0.0.1:8081";
        String topic = "zero01";
        ClientServiceProvider provider = ClientServiceProvider.loadService();
        ClientConfigurationBuilder builder = ClientConfiguration.newBuilder()
                .setEndpoints(endpoint)
                .setCredentialProvider(new StaticSessionCredentialsProvider("", ""))
                .setRequestTimeout(Duration.ofSeconds(5L));
        ClientConfiguration configuration = builder.build();
        Producer producer = provider.newProducerBuilder()
                .setTopics(topic)
                .setClientConfiguration(configuration)
                .build();
        Message message = provider.newMessageBuilder()
                .setTopic(topic)
                .setKeys("this_is_keys")
                .setTag("this_is_tag")
                .setBody("This_is_body_0001".getBytes())
                .build();
        SendReceipt sendReceipt = producer.send(message);
        producer.close();
        System.out.println("receipt is " + sendReceipt);
    }
}
