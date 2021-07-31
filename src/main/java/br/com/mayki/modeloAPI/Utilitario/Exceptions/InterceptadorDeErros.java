package br.com.mayki.modeloAPI.Utilitario.Exceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.mayki.modeloAPI.Views.Dto.ErroDto;
import br.com.mayki.modeloAPI.Views.Dto.ErroParamDto;

@RestControllerAdvice
public class InterceptadorDeErros {
	
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(value = NoSuchElementException.class)
	public ResponseEntity<?> trataNoSuchElementException(NoSuchElementException e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@ExceptionHandler(value = ExceptionMayki.class)
	public ResponseEntity<?> trataExceptionMayki(ExceptionMayki e){
		Map<String, String> erros = Map.of("erro", e.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(erros);
	}
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<List<ErroDto>> trataMethodArgumentNotValidException(MethodArgumentNotValidException e){
		List<ErroDto> listaErro = new ArrayList<ErroDto>();
		
		List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
		
		fieldErrors.forEach(itemErro -> {
			String mensagemErro = messageSource.getMessage(itemErro, LocaleContextHolder.getLocale());
			listaErro.add(new ErroDto(itemErro.getField(), mensagemErro));
		});
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(listaErro);
	}
	
	@ExceptionHandler(value = MissingServletRequestParameterException.class)
	public ResponseEntity<ErroDto> trataMissingServletRequestParameterException(MissingServletRequestParameterException e){
		ErroDto erro = new ErroDto(e.getParameterName(), e.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}


}
