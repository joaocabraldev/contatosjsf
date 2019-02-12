package br.com.joaocabral.contatosjsf.util;

import javax.activity.InvalidActivityException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class GeradorMensagem {
	
	private GeradorMensagem() throws InvalidActivityException {
		throw new InvalidActivityException("Classe Utilitária");
	}
	
	public static void mostrarMensagemInformacao(String titulo, String mensagem) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, mensagem);
        FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public static void mostrarMensagemAlerta(String titulo, String mensagem) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, titulo, mensagem);
        FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public static void mostrarMensagemErro(String titulo, String mensagem) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, mensagem);
        FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
}
