package br.com.mayki.modeloAPI.Utilitario.Exceptions;

public class ExceptionMayki extends Throwable {

	/**
	 * minha propria classe de erro em tempo de execução
	 */
	private static final long serialVersionUID = 1L;

	public ExceptionMayki() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExceptionMayki(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public ExceptionMayki(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ExceptionMayki(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ExceptionMayki(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
}
