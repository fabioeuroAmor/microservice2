package br.com.basic.microservice2.domain;

import java.io.Serializable;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="TBL_CIDADE")
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@SequenceGenerator(name="tbl_cidade_id_cidade_seq", sequenceName="tbl_cidade_id_cidade_seq",initialValue=1, allocationSize=1)
public class Cidade implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "tbl_cidade_id_cidade_seq")
	@Column(name="id_cidade")
	Integer idCidade;
	
	@Column(name="dc_temperatura")
	Double dcTemperatura;
	
	@Column(name="estado")
	String estado;
	
	@Column(name="dc_nome")
	String dcNome;
}
