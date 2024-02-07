package br.com.basic.microservice2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaginatedResponseDto {
	
	private ArrayList<CidadeDto> data;

    private int page;

    private int pageSize;

    private long totalElements;

}
