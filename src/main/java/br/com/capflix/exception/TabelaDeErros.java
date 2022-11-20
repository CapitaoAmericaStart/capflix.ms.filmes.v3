package br.com.capflix.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TabelaDeErros {

	VALIDACAO(HttpStatus.BAD_REQUEST, "1001-1000", "dados de requisição inválidos"),
	FILME_NAO_ENCONTRADO(HttpStatus.NOT_FOUND, "1001-2000", "filme não encontrado"),	
	SISTEMA(HttpStatus.INTERNAL_SERVER_ERROR, "1001-5000", "sitema indisponível");
	
	private final HttpStatus codigoHttp;
	private final String codigoDeErro;
	private final String mensagem;
}
