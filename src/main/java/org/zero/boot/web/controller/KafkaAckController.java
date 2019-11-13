package org.zero.boot.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zero.boot.domain.mq.kafka.KafkaProducer;
import org.zero.boot.web.controller.base.BaseController;

@RestController
public class KafkaAckController extends BaseController {

    @Autowired
    private KafkaProducer kafkaProducer;

    @RequestMapping("/kafka/send")
    public ResponseEntity<?> sendMsg(@RequestParam("topic") String topic,
                                     @RequestParam("msg") String msg) {
        boolean flag = kafkaProducer.sendMsg(topic, msg);
        return ResponseEntity.ok(flag);
    }
}
