package br.com.basic.microservice2.controller.v1;


import br.com.basic.microservice2.dto.CidadeDto;
import br.com.basic.microservice2.json.Response;
import br.com.basic.microservice2.service.CidadeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/cidades")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @PostMapping(value = "/kafka/send")
   public void send(@RequestParam(value = "mensagem", required = true) String mensagem){
        cidadeService.send(mensagem);
    }
    @PostMapping()
    public ResponseEntity<Response> inserirCidade(@RequestParam(value = "dcTemperatura", required = true) Double dcTemperatura, @RequestParam(value = "estado", required = true) String estado,
                                                  @RequestParam(value = "dcNome", required = true) String dcNome) {
        Response response = new Response();
        CidadeDto cidadeDto= null;
        try {
            cidadeDto = new CidadeDto();

            cidadeDto.setDcTemperatura(dcTemperatura);
            cidadeDto.setEstado(estado);
            cidadeDto.setDcNome(dcNome);

            response.setModeloRetorno(cidadeService.inserirCidade(cidadeDto));
            response.setMensagensRetorno("Insercao da cidade realizada com sucesso!");

        }catch (Exception e){
          log.error("Erro ao cosumir o servico: " + e.getMessage());
          response.setMensagensRetorno(e.getMessage());
          return  (ResponseEntity<Response>) ResponseEntity.status(500);
        }

        return ResponseEntity.ok(response);

    }


}