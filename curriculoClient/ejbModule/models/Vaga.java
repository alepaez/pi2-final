package models;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Vaga
 *
 */
@Entity
@Table(name="vagas")
public class Vaga implements Serializable {
	@Transient
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)  
    int id;
    
    @Column(name="cargo")
    String cargo;
    
    @ManyToOne
    @JoinColumn(name="empresaId")
    Empresa empresa;
    
    @ManyToOne
    @JoinColumn(name="areaAtuacaoId")
    AreaAtuacao areaAtuacao;

    public AreaAtuacao getAreaAtuacao() {
        return areaAtuacao;
    }

    public void setAreaAtuacao(AreaAtuacao areaAtuacao) {
        this.areaAtuacao = areaAtuacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
	 
   
}
