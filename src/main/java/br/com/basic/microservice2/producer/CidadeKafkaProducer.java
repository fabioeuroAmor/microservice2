package br.com.basic.microservice2.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CidadeKafkaProducer {

    @Value("${topic1.name.producer}")
    private String topicName;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String message){
        try {
            log.info("Payload enviado: {}", message);
            kafkaTemplate.send(topicName, message);
        }catch (Exception e){
            log.error("Erro ao produzir mensagem no topico : " + e.getMessage());
        }

    }

}