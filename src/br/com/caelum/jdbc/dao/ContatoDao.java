package br.com.caelum.jdbc.dao;

import br.com.caelum.jdbc.ConnectionFactory;        
import br.com.caelum.jdbc.modelo.Contato;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ContatoDao {
        
    private Connection connection;

    public ContatoDao() {
        this.connection = new ConnectionFactory().getConnection();
    }
    
    
    public void adiciona(Contato contato){
        
        String sql = "INSERT INTO contato (nome,email,endereco,dataNascimento) VALUES (?,?,?,?)";
        
        try {
            
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getEmail());
            stmt.setString(3, contato.getEndereco());
            stmt.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));
            
            stmt.execute();
            stmt.close();
            
        } catch (SQLException e) {
        	e.printStackTrace();
            throw new RuntimeException(e);        
        }
        
    }
    
    public List<Contato> getLista(){
        
        try {
            List<Contato> contatos = new ArrayList<>();
            PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM contato");
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                //Criando o objeto contato
                Contato contato = new Contato();
                contato.setId(rs.getLong("id"));
                contato.setNome(rs.getString("nome"));
                contato.setEmail(rs.getString("email"));
                contato.setEndereco(rs.getString("endereco"));
                
                //Montando a data através do Calendar
                Calendar data = Calendar.getInstance();
                data.setTime(rs.getDate("dataNascimento"));
                contato.setDataNascimento(data);
                
                //Adicionando o objeto a Lista
                contatos.add(contato);
            }
            
            rs.close();
            stmt.close();
            
            return contatos;
            
        } catch (SQLException e) {
            throw new RuntimeException (e);
        }
    }
    
    public void altera(Contato contato){
        String sql = "UPDATE contato SET nome = ?, email = ?, endereco = ? dataNascimento = ? where id = ?";
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getEmail());
            stmt.setString(3, contato.getEndereco());
            stmt.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));
            stmt.setLong(5, contato.getId());
            
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void remove(Contato contato){
        
        try {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM contatos WHERE id=?");
            stmt.setLong(1, contato.getId());
            stmt.execute();
            stmt.close();
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }    
    
}
