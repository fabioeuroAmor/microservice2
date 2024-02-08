package br.com.basic.microservice2.service;

import br.com.basic.microservice2.domain.Cidade;
import br.com.basic.microservice2.dto.CidadeDto;
import br.com.basic.microservice2.dto.PaginatedResponseDto;
import br.com.basic.microservice2.exception.BDException;
import br.com.basic.microservice2.exception.NegocioException;
import br.com.basic.microservice2.producer.CidadeKafkaProducer;
import br.com.basic.microservice2.repository.CidadeRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class CidadeService {

    @Autowired
    private CidadeKafkaProducer cidadeKafkaProducer;
    @Autowired
    private CidadeRepository  cidadeRepository;

    public void send(String mensagem) throws NegocioException{
        try {
            cidadeKafkaProducer.send(mensagem);
        }catch (Exception e) {
            log.error("Erro na camda de servico ao produzir a mensagem no topico: " + e.getMessage());
            throw new NegocioException(e.getMessage());
        }

    }

    public  CidadeDto inserirCidade(CidadeDto cidadeDto)throws BDException{
        CidadeDto cidadePers = null;
        try {
            ModelMapper modelMapper = new ModelMapper();
            Cidade cidade =  modelMapper.map(cidadeDto, Cidade.class);
            cidade =  cidadeRepository.save(cidade);
            cidadePers = modelMapper.map(cidade, CidadeDto.class);
        }catch (Exception e) {
            log.error("Erro na camda de servico ao realizar a insercao no banco de dados: " + e.getMessage());
            throw new BDException(e.getMessage());
        }

        return cidadePers;
    }


    public ArrayList<CidadeDto> buscarTodas(){
        ArrayList<Cidade> arrayCidades = new ArrayList<>();
        ArrayList<CidadeDto> arrayCidadesDestDto = new ArrayList<>();

        try {
            ModelMapper modelMapper = new ModelMapper();
            arrayCidades = (ArrayList<Cidade>) cidadeRepository.findAll();

            // Defina o tipo de destino usando TypeToken
            Type destinationListType = new TypeToken<List<CidadeDto>>() {}.getType();

            arrayCidadesDestDto  = modelMapper.map(arrayCidades, destinationListType);

        }catch (Exception e) {
            log.error("Erro na camda de servico ao realizar a insercao no banco de dados: " + e.getMessage());
            throw new BDException(e.getMessage());
        }
        return arrayCidadesDestDto;
    }

    public CidadeDto buscarPorNome(String dcNome){
        CidadeDto cidadePers = null;
        try {
            ModelMapper modelMapper = new ModelMapper();
            Cidade cidade =  cidadeRepository.buscarPorNome(dcNome);
            cidadePers = modelMapper.map(cidade, CidadeDto.class);
        }catch (Exception e) {
            log.error("Erro na camda de servico ao realizar a buscaPornome no banco de dados: " + e.getMessage());
            throw new BDException(e.getMessage());
        }

        return cidadePers;

    }

    public CidadeDto buscarPorNomeQueryNativa(String dcNome){
        CidadeDto cidadePers = null;
        try {
            ModelMapper modelMapper = new ModelMapper();
            Cidade cidade =  cidadeRepository.buscarPorNomeQueryNativa(dcNome);
            cidadePers = modelMapper.map(cidade, CidadeDto.class);
        }catch (Exception e) {
            log.error("Erro na camda de servico ao realizar a buscaPornome no banco de dados: " + e.getMessage());
            throw new BDException(e.getMessage());
        }

        return cidadePers;
    }



    public PaginatedResponseDto findAllPag(int page, int size) {

        PaginatedResponseDto paginatedResponseDto = new PaginatedResponseDto();
        ArrayList<CidadeDto> arrayCidadesDto = new ArrayList<>();
        List<Cidade> arrayCidadePag = new ArrayList<>();

       try{
           arrayCidadePag = cidadeRepository.findAll(PageRequest.of(page,size)).getContent();

           ModelMapper modelMapper = new ModelMapper();
           // Defina o tipo de destino usando TypeToken
           Type destinationListType = new TypeToken<List<CidadeDto>>() {}.getType();

           arrayCidadesDto  = modelMapper.map(arrayCidadePag, destinationListType);

           paginatedResponseDto.setData(arrayCidadesDto);
           paginatedResponseDto.setPage(page);
           paginatedResponseDto.setSize(size);
           paginatedResponseDto.setTotalElements(arrayCidadePag.size());
           paginatedResponseDto.setTotalElementsNoBanco(cidadeRepository.findAll().size());

       }catch (Exception e){
           log.error("Erro na camda de servico ao realizar searchPag no banco de dados: " + e.getMessage());
           throw new BDException(e.getMessage());
       }
        return paginatedResponseDto;
    }


    public PaginatedResponseDto searchPag(String searchTerm, int page, int size) {

        PaginatedResponseDto paginatedResponseDto = new PaginatedResponseDto();
        List<Cidade> arrayCidadePag = new ArrayList<>();
        ArrayList<CidadeDto> arrayCidadesDto = new ArrayList<>();
        try{
            arrayCidadePag = cidadeRepository.searchPag(searchTerm ,PageRequest.of(page,size)).getContent();

            ModelMapper modelMapper = new ModelMapper();
            // Defina o tipo de destino usando TypeToken
            Type destinationListType = new TypeToken<List<CidadeDto>>() {}.getType();

            arrayCidadesDto  = modelMapper.map(arrayCidadePag, destinationListType);

            paginatedResponseDto.setData(arrayCidadesDto);
            paginatedResponseDto.setPage(page);
            paginatedResponseDto.setSize(size);
            paginatedResponseDto.setTotalElements(arrayCidadePag.size());
            paginatedResponseDto.setTotalElementsNoBanco(cidadeRepository.search(searchTerm).size());

        }catch (Exception e){
            log.error("Erro na camda de servico ao realizar searchPag no banco de dados: " + e.getMessage());
            throw new BDException(e.getMessage());
        }
        return paginatedResponseDto;

    }

    public  void delete(Integer id)throws BDException{
        try {
            cidadeRepository.deleteById(id);
        }catch (Exception e) {
            log.error("Erro na camda de servico ao deleta a cidade: " + e.getMessage());
            throw new NegocioException(e.getMessage());
        }

    }

    public CidadeDto atualizar(CidadeDto cidade){
        CidadeDto cidadeDto = new CidadeDto();
        Cidade cidadeAtuPers = new Cidade();

        try {

            if(Objects.nonNull(buscaPorId(cidade.getIdCidade()))){
                ModelMapper modelMapper = new ModelMapper();
                Cidade cidadeAtu =   modelMapper.map(cidade, Cidade.class);
                cidadeAtuPers = cidadeRepository.saveAndFlush(cidadeAtu);
                cidadeDto = modelMapper.map(cidadeAtuPers, CidadeDto.class);
            }

        }catch (Exception e) {
            log.error("Erro na camda de servico ao deleta a cidade: " + e.getMessage());
            throw new NegocioException(e.getMessage());
        }
        return cidadeDto;
    }

     public CidadeDto buscaPorId(Integer id){
         CidadeDto cidadeDto = new CidadeDto();
         try {
           Optional<Cidade> cidade =   cidadeRepository.findById(id);
           ModelMapper modelMapper = new ModelMapper();
           cidadeDto = modelMapper.map(cidade.get(), CidadeDto.class);
         }catch (Exception e) {
             log.error("Erro na camda de servico ao buscaPorId a cidade: " + e.getMessage());
             throw new NegocioException(e.getMessage());
         }
         return cidadeDto;
    }

}