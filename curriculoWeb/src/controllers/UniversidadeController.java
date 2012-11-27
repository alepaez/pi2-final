package controllers;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import models.Universidade;

import dao.UniversidadeRemote;

@ManagedBean
@SessionScoped
public class UniversidadeController {
	@EJB
	public UniversidadeRemote universidadeDao; 
	
	public DataModel<Universidade> getListaUniversidades(){
		return new ListDataModel<Universidade>(universidadeDao.list());
	}
}
