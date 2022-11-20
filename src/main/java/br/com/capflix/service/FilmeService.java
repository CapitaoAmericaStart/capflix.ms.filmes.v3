package br.com.capflix.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.capflix.exception.NegocioException;
import br.com.capflix.exception.TabelaDeErros;
import br.com.capflix.model.Filme;
import br.com.capflix.model.dto.FilmeEntradaDto;
import br.com.capflix.model.dto.FilmeSaidaDto;
import br.com.capflix.repository.FilmeRepository;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class FilmeService {

	@Autowired
	private FilmeRepository repository;
	
	@Autowired
	private ModelMapper mapper;
	
	public FilmeSaidaDto criar(FilmeEntradaDto entradaDto) {
		try {
			Filme filme = mapper.map(entradaDto, Filme.class);
			
			Filme filmeBanco = repository.save(filme);
			
			return mapper.map(filmeBanco, FilmeSaidaDto.class);
		} catch (Exception e) {
			log.error("erro genérico: ", e);
			
			throw new NegocioException(TabelaDeErros.SISTEMA);
		} 
	}

	public void alterar(Long id, FilmeEntradaDto entradaDto) {
		try {
			Optional<Filme> optional = repository.findById(id);
			
			if(!optional.isPresent()) {
				throw new NegocioException(TabelaDeErros.FILME_NAO_ENCONTRADO);
			}
			
			Filme filmeBanco = optional.get();
			
			mapper.map(entradaDto, filmeBanco);
	
			repository.save(filmeBanco);
		} catch (NegocioException e) {
			throw e;
		} catch (Exception e) {
			log.error("erro genérico: ", e);
			
			throw new NegocioException(TabelaDeErros.SISTEMA);
		}
	}

	public FilmeSaidaDto pagarUm(Long id) {
		try {
			Optional<Filme> optional = repository.findById(id);
			
			if(!optional.isPresent()) {
				throw new NegocioException(TabelaDeErros.FILME_NAO_ENCONTRADO);
			}
			
			Filme filmeBanco = optional.get();
			
			return mapper.map(filmeBanco, FilmeSaidaDto.class);
		} catch (NegocioException e) {
			log.error("erro de negócio: codigoDeErro={}, mensagem={}", e.getCodigoDeErro(), e.getMensagem());
			throw e;
		} catch (Exception e) {
			log.error("erro genérico: ", e);
			
			throw new NegocioException(TabelaDeErros.SISTEMA);
		}		
	}

	public void excluir(Long id) {
		try {
			if(!repository.existsById(id)) {
				throw new NegocioException(TabelaDeErros.FILME_NAO_ENCONTRADO);
			}
			
			repository.deleteById(id);
		} catch (NegocioException e) {
			log.error("erro de negócio: codigoDeErro={}, mensagem={}", e.getCodigoDeErro(), e.getMensagem());
			throw e;
		} catch (Exception e) {
			log.error("erro genérico: ", e);
			
			throw new NegocioException(TabelaDeErros.SISTEMA);
		}		
	}

	public List<FilmeSaidaDto> listar() {
		try {
			List<Filme> lista = repository.findAll();
			
			return mapper.map(lista, new TypeToken<List<FilmeSaidaDto>>() {}.getType());
		} catch (Exception e) {
			log.error("erro genérico: ", e);
			
			throw new NegocioException(TabelaDeErros.SISTEMA);
		}
	}
}
