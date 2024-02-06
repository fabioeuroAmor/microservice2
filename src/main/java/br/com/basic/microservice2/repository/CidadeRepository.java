package br.com.basic.microservice2.repository;


import br.com.basic.microservice2.domain.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer>{
	
	@Query("FROM Cidade c WHERE LOWER(c.dcNome) like %:searchTerm% ")
	List<Cidade> search(@Param("searchTerm") String searchTerm);
	

}