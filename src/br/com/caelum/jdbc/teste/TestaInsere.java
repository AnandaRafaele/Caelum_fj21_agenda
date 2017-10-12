package br.com.caelum.jdbc.teste;

import br.com.caelum.jdbc.dao.ContatoDao;
import br.com.caelum.jdbc.modelo.Contato;
import java.util.Calendar;

public class TestaInsere {
    
    public static void main(String[] args) {        
        
        Contato contato = new Contato();
        
        contato.setNome("Clarice Falcão");
        contato.setEmail("alanaline@caelum.com.br");
        contato.setEndereco("R. Farias Brito, 500");
        contato.setDataNascimento(Calendar.getInstance());
        
        ContatoDao dao = new ContatoDao();
        
        dao.adiciona(contato);
        
        System.out.println("Gravado!");
    }
    
}
