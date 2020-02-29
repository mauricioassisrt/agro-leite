/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import banco.Banco;
import banco.DAOGenerico;
import entidades.Animal;
import entidades.Origem;
import entidades.Pessoa;
import entidades.Raca;
import entidades.Remedios;
//import entidades.Tipo;
import entidades.Vacinacao;
import util.EnviaEmail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

@ManagedBean
@ViewScoped
public class RecuperaSenha {
  
	private DAOGenerico dao = new DAOGenerico();
	private String emailFormulario;
	private String mensagem ="Recuperação de senha sua nova senha é :123", enviado;
	
	public void RecuperaSenha(){
		enviado = "";
	}
	
	public void recuperar() throws EmailException{
		List<Pessoa> lista = new ArrayList<>();
	    Pessoa objetPess = new Pessoa();
		lista = dao.lista(Pessoa.class);
		Random gerador = new Random();
		
		for(Pessoa objet:lista){
			if(objet.getEmail().equals(emailFormulario)){
			 objetPess.setId(objet.getId());
			 objetPess.setEmail(objet.getEmail());
			 objetPess.setNome(objet.getNome());
			 objetPess.setPermissao(objet.getPermissao());
			 objetPess.setTelefone(objet.getTelefone());
			 int senhaNova=gerador.nextInt();
			 objetPess.setSenha(senhaNova);
			 System.out.println(objetPess.getEmail());
			 
			String assunto = Integer.toString(senhaNova);
			 
			sendEmail(objetPess.getEmail().toString(), objetPess.getNome().toString(), "Nova senha : " + assunto, mensagem);
			enviado = "Enviado com sucesso, abra seu email";
			System.out.println(objetPess.getEmail()+objetPess.getId()+objetPess.getNome());
			
			dao.salvar(objetPess);
			
			emailFormulario = "";
			 break;
			}
		}
		
		if(objetPess.getEmail() ==null){
			enviado = "Erro Usuario não cadastrado";
			enviado = "";
			}
		
		
	}
	
	public void sendEmail(String emailDestino, String nome, String mensagem, String assunto) throws EmailException {
	    
		   SimpleEmail email = new SimpleEmail();
		   //Utilize o hostname do seu provedor de email
		   //System.out.println("alterando hostname...");
		   email.setHostName("smtp.gmail.com");
		   //Quando a porta utilizada n�o � a padr�o (gmail = 465)
		   email.setSmtpPort(465);
		   //Adicione os destinat�rios
		   email.addTo(emailDestino, nome);
		   //Configure o seu email do qual enviar�
		   email.setFrom("evento.tads@gmail.com", "Agro leite ");
		   //Adicione um assunto
		   email.setSubject(assunto);
		   //Adicione a mensagem do email
		   email.setMsg(mensagem);
		   //Para autenticar no servidor � necess�rio chamar os dois m�todos abaixo
		   //System.out.println("autenticando...");
		   email.setSSL(true);
		   email.setAuthentication("evento.tads@gmail.com", "tads2016");
		   //System.out.println("enviando...");
		   email.send();
		   //System.out.println("Email enviado!");
		}



	public String getEmailFormulario() {
		return emailFormulario;
	}



	public void setEmailFormulario(String emailFormulario) {
		this.emailFormulario = emailFormulario;
	}

	public String getEnviado() {
		return enviado;
	}

	public void setEnviado(String enviado) {
		this.enviado = enviado;
	}
	
	
}
