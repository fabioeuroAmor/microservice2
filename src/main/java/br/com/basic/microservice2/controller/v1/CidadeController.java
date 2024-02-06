package br.com.basic.microservice2.controller.v1;

import br.com.basic.microservice2.producer.CidadeKafkaProducer;
import br.com.basic.microservice2.service.CidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/kafka")
public class CidadeController {

    //private final CidadeKafkaProducer cidadeKafkaProducer;
//    @Autowired
//    private CidadeKafkaProducer cidadeKafkaProducer;

    @Autowired
    private CidadeService cidadeService;

    @GetMapping(value = "/send")
   public void send(){
        cidadeService.send();
    }
}