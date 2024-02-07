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

    @GetMapping()
    public ResponseEntity<Response> buscarTodasCidades() {
        Response response = new Response();

        try {
            response.setModeloRetorno(cidadeService.buscarTodas());
            response.setMensagensRetorno("Lista de todas as cidades!" );

        }catch (Exception e){
            log.error("Erro ao consultar todas as cidades: " + e.getMessage());
            response.setMensagensRetorno(e.getMessage());
            return  (ResponseEntity<Response>) ResponseEntity.status(500);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{dcNome}")
    public ResponseEntity<Response> buscaCidadesPorNome(@PathVariable("dcNome") String dcNome){

        Response response = new Response();

        try {
            response.setModeloRetorno(cidadeService.buscarPorNome(dcNome));
            response.setMensagensRetorno("Cidade encontrada na consulta por nome!" );

        }catch (Exception e){
            log.error("Erro ao consultar a cidade pelo nome: " + e.getMessage());
            response.setMensagensRetorno(e.getMessage());
            return  (ResponseEntity<Response>) ResponseEntity.status(500);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/query/nativa/{dcNome}")
    public ResponseEntity<Response> buscarPorNomeQueryNativa(@PathVariable("dcNome") String dcNome){

        Response response = new Response();

        try {
            response.setModeloRetorno(cidadeService.buscarPorNomeQueryNativa(dcNome));
            response.setMensagensRetorno("Cidade encontrada na consulta por nome de uma query nativa!" );

        }catch (Exception e){
            log.error("Erro ao consultar a cidade pelo nome: " + e.getMessage());
            response.setMensagensRetorno(e.getMessage());
            return  (ResponseEntity<Response>) ResponseEntity.status(500);
        }
        return ResponseEntity.ok(response);
    }
    @GetMapping(value = "/searchPag")
    public ResponseEntity<Response> searchPag(@RequestParam(value = "searchTerm", required = true) String searchTerm, @RequestParam(value = "page", required = true) Integer page, @RequestParam(value = "size", required = true) Integer size){

        Response response = new Response();

        try {
            response.setModeloRetorno(cidadeService.searchPag( searchTerm,  page.intValue(),  size.intValue()));
            response.setMensagensRetorno("Consulta por dcNome ou estado com resposta pagisnada!" );
        }catch (Exception e){
            log.error("Erro ao consultar a cidade pelo nome: " + e.getMessage());
            response.setMensagensRetorno(e.getMessage());
            return  (ResponseEntity<Response>) ResponseEntity.status(500);
        }
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Response> findAllPag(){
        return null;
    }


}