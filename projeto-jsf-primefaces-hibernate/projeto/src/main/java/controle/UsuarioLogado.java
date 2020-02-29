package controle;


import org.springframework.security.core.context.SecurityContextHolder;

import banco.DAOGenerico;
import entidades.Pessoa;

public class UsuarioLogado {

	public static Pessoa retornaUsuarioLogado(){
		try {
			Pessoa p = new Pessoa();
			p = (Pessoa) new DAOGenerico().listaCondicao(Pessoa.class, "email = '"+SecurityContextHolder.getContext().getAuthentication().getName()+"'").get(0);
			return p;			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
