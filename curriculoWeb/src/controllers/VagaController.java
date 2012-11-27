package controllers;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import models.Candidatura;
import models.Vaga;

import dao.CandidatoRemote;
import dao.CandidaturaRemote;
import dao.VagaRemote;

@ManagedBean
@SessionScoped
public class VagaController {
	@EJB
	public VagaRemote vagaDao;
	
	@EJB
	public CandidaturaRemote candidaturaDao;
	
	@EJB
	public CandidatoRemote candidatoDao;
	
	
	public DataModel<Vaga> getListaVagas(){
		return new ListDataModel<Vaga>(vagaDao.list());
	}
	
	public String vagas(){
		return "vagas"; 
	}
	
	public void candidatar(){
		int vagaId = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("vagaId"));
		int candidatoId = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("candidatoId"));
		Candidatura candidatura = new Candidatura();
		candidatura.setCandidato(candidatoDao.find(candidatoId));
		candidatura.setVaga(vagaDao.find(vagaId));
		candidaturaDao.save(candidatura);
	}

}
