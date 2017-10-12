package br.com.caelum.jdbc.teste;

import br.com.caelum.jdbc.dao.ContatoDao;
import br.com.caelum.jdbc.modelo.Contato;
import java.util.Calendar;

public class TestaAlterar {
    
    public static void main(String[] args) {
        
        Contato contato = new Contato();
        
        contato.setNome("Anandaaaaaaaaa");
        contato.setEmail("ananda_rafaele@hotmail.com");
        contato.setEndereco("R. Farias Brito, nº 500");
        contato.setDataNascimento(Calendar.getInstance());
        contato.setId(5l);
        
        ContatoDao dao = new ContatoDao();
        
        dao.altera(contato);
        
        System.out.println("Alterado!");
        
    }
    
}
