package br.com.basic.microservice2.json;

import java.io.Serializable;

/**
 * Interface de marcacao que representa o design pattern  Data Transfer Object.
 * 
 * @author Fabio Amorim
 *
 */
public class Response implements Serializable {
	

	private static final long serialVersionUID = 1L;

	private Object modeloRetorno;
	private String mensagensRetorno;
	
	public Object getModeloRetorno() {
		return modeloRetorno;
	}
	public void setModeloRetorno(Object modeloRetorno) {
		this.modeloRetorno = modeloRetorno;
	}
	public String getMensagensRetorno() {
		return mensagensRetorno;
	}
	public void setMensagensRetorno(String mensagensRetorno) {
		this.mensagensRetorno = mensagensRetorno;
	}

}
