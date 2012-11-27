package controllers;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dao.CandidatoRemote;
import dao.CurriculoRemote;
import models.Candidato;
import models.Curriculo;

@ManagedBean
@SessionScoped
public class CandidatoController {
	@EJB
	public CandidatoRemote candidatoDao;
	
	@EJB
	public CurriculoRemote curriculoDao;
	
	public Candidato candidato;
	public Curriculo curriculo;
	public String login;
	public String senha;
	
	public String newCandidato(){
		candidato = new Candidato();
		return "newCandidato";
	}
	
	public String createCandidato(){
		candidatoDao.save(candidato);
		return "index";
	}
	
	public void saveCandidato(){
		candidatoDao.update(candidato);
	}
	public void saveCurriculo(){
		curriculoDao.update(curriculo);
	}
	
	public String editCandidato(){
		return "gerCandidato";
	}
	
	public String editCurriculo(){
		if(candidato.getCurriculo() == null){
			curriculo = new Curriculo();
			curriculoDao.save(curriculo);
			candidato.setCurriculo(curriculo);
			saveCandidato();
		}else{
			curriculo = candidato.getCurriculo();
		}
		return "gerCurriculo";
	}
	
	public String login(){
		candidato = candidatoDao.find(login);
		if(candidato != null){
			if(!senha.equals(candidato.getSenha())){
				candidato = null;
				login = null;
				senha = null;
			}
		}
		return "index";
	}
	
	public String logout(){
		candidato = null;
		login = null;
		senha = null;
		return "index";
	}

	public Candidato getCandidato() {
		return candidato;
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
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

	public Curriculo getCurriculo() {
		return curriculo;
	}

	public void setCurriculo(Curriculo curriculo) {
		this.curriculo = curriculo;
	}

	

}
