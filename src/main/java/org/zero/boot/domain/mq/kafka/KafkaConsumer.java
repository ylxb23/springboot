package org.zero.boot.domain.mq.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @KafkaListener(topics = {"zero", "zero01", "zero02", "zero03"})
    public void listen(ConsumerRecord<String, String> consumerRecord) {
        logger.info("GetKafkaMsg, topic:{}, offset:{}, msg:{}", consumerRecord.topic(), consumerRecord.offset(), consumerRecord.value());
    }

}
