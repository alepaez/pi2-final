package models;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Curriculo
 *
 */
@Entity
@Table(name="curriculos")
public class Curriculo implements Serializable {
	@Transient
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    int id;
    
    @Column(name = "objProf")
    String objProf;
    
    @Column(name = "idioma")
    String idioma;  
    
    @Column(name="universidadeId")
    int universidadeId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObjProf() {
        return objProf;
    }

    public void setObjProf(String objProf) {
        this.objProf = objProf;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Universidade getUniversidade() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa");
        EntityManager em = factory.createEntityManager();
        return (Universidade)em.find(Universidade.class, universidadeId);
    }

    public int getUniversidadeId() {
        return universidadeId;
    }

    public void setUniversidadeId(int universidadeId) {
        this.universidadeId = universidadeId;
    }
	
   
}
