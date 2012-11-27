package models;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="candidaturas")
public class Candidatura implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)        
    int id;
	
	@ManyToOne  
    @JoinColumn(name="candidatoId")
    Candidato candidato;
	
	@ManyToOne  
    @JoinColumn(name="vagaId")
    Vaga vaga;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Candidato getCandidato() {
		return candidato;
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}

	public Vaga getVaga() {
		return vaga;
	}

	public void setVaga(Vaga vaga) {
		this.vaga = vaga;
	}
	
	
}
