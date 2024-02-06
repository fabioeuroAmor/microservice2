package br.com.basic.microservice2.service;

import br.com.basic.microservice2.producer.CidadeKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CidadeService {

    @Autowired
    private CidadeKafkaProducer cidadeKafkaProducer;

    public void send(){
        cidadeKafkaProducer.send("Mensagem de teste enviada ao tópico");
    }
}
