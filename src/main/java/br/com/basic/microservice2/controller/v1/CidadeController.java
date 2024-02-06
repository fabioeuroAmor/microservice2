package br.com.basic.microservice2.controller.v1;


import br.com.basic.microservice2.service.CidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/cidade")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @PostMapping(value = "/kafka/send")
   public void send(@RequestParam(value = "mensagem", required = true) String mensagem){
        cidadeService.send(mensagem);
    }


}