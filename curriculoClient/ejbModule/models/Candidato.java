 package models;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Candidato
 *
 */
@Entity
@Table(name="candidatos")
public class Candidato implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)        
    int id;
  
    @OneToOne  
    @JoinColumn(name="curriculoId")
    Curriculo curriculo;
    
    @Column(name = "nome")
    String nome;
    
    @Column(name = "endereco")
    String endereco;
    
    @Column(name = "login")
    String login;
    
    @Column(name = "senha")
    String senha;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Curriculo getCurriculo() {
        return curriculo;
    }

    public void setCurriculo(Curriculo curriculo) {
        this.curriculo = curriculo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
   
}
