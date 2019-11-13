package org.zero.boot.domain.mq.kafka;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;

@Component
public class KafkaProducer {
    private final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public boolean sendMsg(String topic, String msg) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, msg);
        try {
            SendResult<String, String> result = future.get();
            if(logger.isDebugEnabled()) {
                logger.debug("SendKafkaMsg success, topic:{}, msg:{}, result:{}", topic, msg, result.toString());
            }
            return true;
        } catch (InterruptedException | ExecutionException e) {
            logger.error("SendKafkaMsg error, topic:{}, msg:{}", topic, msg, e);
            return false;
        }
    }
}
