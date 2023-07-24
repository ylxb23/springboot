package org.zero.test.mid;

import org.apache.rocketmq.client.apis.ClientConfiguration;
import org.apache.rocketmq.client.apis.ClientConfigurationBuilder;
import org.apache.rocketmq.client.apis.ClientException;
import org.apache.rocketmq.client.apis.ClientServiceProvider;
import org.apache.rocketmq.client.apis.StaticSessionCredentialsProvider;
import org.apache.rocketmq.client.apis.message.Message;
import org.apache.rocketmq.client.apis.message.MessageView;
import org.apache.rocketmq.client.apis.producer.Producer;
import org.apache.rocketmq.client.apis.producer.SendReceipt;
import org.apache.rocketmq.client.apis.producer.Transaction;
import org.apache.rocketmq.client.apis.producer.TransactionChecker;
import org.apache.rocketmq.client.apis.producer.TransactionResolution;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicLong;

public class RocketMQTransactionalMsgTest {

    public static void main(String[] args) throws ClientException, IOException {
        String endpoint = "127.0.0.1:8081";
        // $ mqadmin.cmd updateTopic -n 127.0.0.1:9876 -t TEST_TX_001 -c DefaultCluster -a +message.type=TRANSACTION
        String topic = "TEST_TX_001";   // topic需设置类型为事务消息队列，即在创建topic的时候需要加上 "-a +message.type=TRANSACTION "

        ClientServiceProvider provider = ClientServiceProvider.loadService();
        ClientConfigurationBuilder builder = ClientConfiguration.newBuilder()
                .setEndpoints(endpoint)
                .setRequestTimeout(Duration.ofSeconds(5L));
        ClientConfiguration configuration = builder.build();

        Producer producer = provider.newProducerBuilder()
                .setTransactionChecker(new TransactionCheckerImpl())
                .setTopics(topic)
                .setClientConfiguration(configuration)
                .build();

        Message message = provider.newMessageBuilder()
                .setTopic(topic)
                .setKeys("keysHere")
                .setTag("tag01")
                .setBody("This_is_body_0001".getBytes())
                .build();
        Transaction transaction = producer.beginTransaction();
        SendReceipt sendReceipt = producer.send(message, transaction);
        System.out.println("send transaction message success...");
        System.out.println("receipt is " + sendReceipt);
        try {
            Thread.sleep(90000L);   // 90s后commit该消息
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        transaction.commit();
        System.out.println("commit transaction message success...");
        producer.close();
        System.out.println("receipt is " + sendReceipt);
    }

    public static class TransactionCheckerImpl implements TransactionChecker {
        static AtomicLong counter = new AtomicLong();
        @Override
        public TransactionResolution check(MessageView messageView) {
            long cu = counter.incrementAndGet();
            System.out.println("[" + cu + "]check message view: " + messageView);
            if(cu % 3 == 0) {
                return TransactionResolution.COMMIT;
            }
            return TransactionResolution.UNKNOWN;
        }
    }
}
