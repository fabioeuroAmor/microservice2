package br.com.basic.microservice2.service;

import br.com.basic.microservice2.dto.CidadeDto;
import br.com.basic.microservice2.exception.NegocioException;
import br.com.basic.microservice2.producer.CidadeKafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CidadeService {

    @Autowired
    private CidadeKafkaProducer cidadeKafkaProducer;

    public void send(String mensagem) throws NegocioException{
        try {
            cidadeKafkaProducer.send(mensagem);
        }catch (Exception e) {
            log.error("Erro na camda de servico ao produzir a mensagem no topico: " + e.getMessage());
            throw new NegocioException(e.getMessage());
        }

    }

    public  CidadeDto inserirCidade(CidadeDto cidadeDto){

        return null;
    }
}
