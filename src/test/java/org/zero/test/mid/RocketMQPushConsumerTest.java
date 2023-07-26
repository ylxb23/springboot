package org.zero.test.mid;

import org.apache.rocketmq.client.apis.ClientConfiguration;
import org.apache.rocketmq.client.apis.ClientConfigurationBuilder;
import org.apache.rocketmq.client.apis.ClientException;
import org.apache.rocketmq.client.apis.ClientServiceProvider;
import org.apache.rocketmq.client.apis.StaticSessionCredentialsProvider;
import org.apache.rocketmq.client.apis.consumer.ConsumeResult;
import org.apache.rocketmq.client.apis.consumer.FilterExpression;
import org.apache.rocketmq.client.apis.consumer.PushConsumer;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class RocketMQPushConsumerTest {
    static final CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) throws ClientException, InterruptedException, IOException {
        String endpoint = "127.0.0.1:8081";
        ClientServiceProvider provider = ClientServiceProvider.loadService();
        ClientConfigurationBuilder builder = ClientConfiguration.newBuilder()
                .setEndpoints(endpoint)
                .setCredentialProvider(new StaticSessionCredentialsProvider("", ""))
                .setRequestTimeout(Duration.ofSeconds(5L));
        ClientConfiguration configuration = builder.build();
        Map<String, FilterExpression> subscriptionExpressions = new HashMap<>();
        subscriptionExpressions.put("TEST001", new FilterExpression("*"));
        PushConsumer consumer = provider.newPushConsumerBuilder()
                .setConsumerGroup("pushCG001")
                .setClientConfiguration(configuration)
                .setSubscriptionExpressions(subscriptionExpressions)
                .setMessageListener(msg -> {
                    System.out.println("consume message[topic:" + msg.getTopic() + ", tags:" + msg.getTag().orElse("null") + "], id: " + msg.getMessageId() + ", properties: " + msg.getProperties() + ", body: " + msg.getBody());
                    return ConsumeResult.SUCCESS;
                })
                .build();
        latch.await();
        consumer.close();
    }
}
