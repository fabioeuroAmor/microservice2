package br.com.basic.microservice2.service;

import br.com.basic.microservice2.domain.Cidade;
import br.com.basic.microservice2.dto.CidadeDto;
import br.com.basic.microservice2.dto.PaginatedResponseDto;
import br.com.basic.microservice2.exception.BDException;
import br.com.basic.microservice2.exception.NegocioException;
import br.com.basic.microservice2.producer.CidadeKafkaProducer;
import br.com.basic.microservice2.repository.CidadeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

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

    public PaginatedResponseDto searchPag(String searchTerm, int page, int size){
        PaginatedResponseDto paginatedResponseDto = new PaginatedResponseDto();
        List<Cidade> cidadesPers = null;
        ArrayList<CidadeDto> arrayCidadestDto = new ArrayList<>();

        //Objeto com informacao da pagisnacao
        Page<Cidade> arrayCidadePag = null;

        try {
            //Objeto com filtro da pagisnacao
            PageRequest pageRequest = PageRequest.of(
                    page,
                    size,
                    Sort.Direction.ASC,"dcNome");
            //Objeto que contem informacoes da pagisnacao
            arrayCidadePag =  cidadeRepository.searchPag(searchTerm.toLowerCase(),pageRequest);

            cidadesPers = cidadeRepository.search(searchTerm);

            ModelMapper modelMapper = new ModelMapper();
            // Defina o tipo de destino usando TypeToken
            Type destinationListType = new TypeToken<List<CidadeDto>>() {}.getType();

            arrayCidadestDto  = modelMapper.map(cidadesPers, destinationListType);

            paginatedResponseDto.setData(arrayCidadestDto);
            paginatedResponseDto.setPage(arrayCidadePag.getPageable().getPageNumber());
            paginatedResponseDto.setTotalElements(cidadesPers.size());
            paginatedResponseDto.setPageSize(arrayCidadePag.getPageable().getPageSize());


        }catch (Exception e){
            log.error("Erro na camda de servico ao realizar searchPag no banco de dados: " + e.getMessage());
            throw new BDException(e.getMessage());
        }
        return paginatedResponseDto;
    }

    public Page<Cidade> findAllPag() {
        Page<Cidade> arrayCidadePag = null;
       try{
           int page = 0;
           int size = 10;
           PageRequest pageRequest = PageRequest.of(
                   page,
                   size,
                   Sort.Direction.ASC,
                   "dcNome");
           arrayCidadePag =  new PageImpl<>(
                   cidadeRepository.findAll(),
                   pageRequest, size);
       }catch (Exception e){
           log.error("Erro na camda de servico ao realizar searchPag no banco de dados: " + e.getMessage());
           throw new BDException(e.getMessage());
       }
        return arrayCidadePag;
    }


}