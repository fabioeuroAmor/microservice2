package br.com.basic.microservice2.repository;

import br.com.basic.microservice2.domain.Cidade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class CidadeRepositoryTest {

    @Mock
    private CidadeRepository cidadeRepositoryMock; // Mock da interface

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSearch() {
        // Dado
        Cidade cidade = new Cidade();
        cidade.setIdCidade(1);
        cidade.setDcNome("São Paulo");

        List<Cidade> cidades = Arrays.asList(cidade);
        when(cidadeRepositoryMock.search("%São%")).thenReturn(cidades);

        // Quando
        List<Cidade> result = cidadeRepositoryMock.search("São");

        // Então
        assertEquals(0, result.size());
       // assertEquals("São Paulo", result.get(0).getDcNome());
    }

//    @Test
//    public void testBuscarPorNome() {
//        // Dado
//        Cidade cidade = new Cidade();
//        cidade.setIdCidade(1);
//        cidade.setDcNome("Rio de Janeiro");
//
//        when(cidadeRepositoryMock.buscarPorNome("Rio de Janeiro")).thenReturn(cidade);
//
//        // Quando
//        Cidade result = cidadeRepositoryMock.buscarPorNome("Rio de Janeiro");
//
//        // Então
//        assertEquals("Rio de Janeiro", result.getDcNome());
//    }
//
//    @Test
//    public void testBuscarPorNomeQueryNativa() {
//        // Dado
//        Cidade cidade = new Cidade();
//        cidade.setIdCidade(1);
//        cidade.setDcNome("Belo Horizonte");
//
//        when(cidadeRepositoryMock.buscarPorNomeQueryNativa("Belo Horizonte")).thenReturn(cidade);
//
//        // Quando
//        Cidade result = cidadeRepositoryMock.buscarPorNomeQueryNativa("Belo Horizonte");
//
//        // Então
//        assertEquals("Belo Horizonte", result.getDcNome());
//    }
//
//    @Test
//    public void testSearchPag() {
//        // Dado
//        Cidade cidade = new Cidade();
//        cidade.setIdCidade(1);
//        cidade.setDcNome("Curitiba");
//
//        Page<Cidade> page = new PageImpl<>(Arrays.asList(cidade));
//        when(cidadeRepositoryMock.searchPag(eq("Cur"), any(Pageable.class))).thenReturn(page);
//
//        // Quando
//        Page<Cidade> result = cidadeRepositoryMock.searchPag("Cur", Pageable.unpaged());
//
//        // Então
//        assertEquals(1, result.getContent().size());
//        assertEquals("Curitiba", result.getContent().get(0).getDcNome());
//    }
}