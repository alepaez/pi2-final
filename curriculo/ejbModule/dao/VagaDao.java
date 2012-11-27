package dao;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import models.Empresa;
import models.Vaga;

/**
 * Session Bean implementation class VagaDao
 */
@Stateless
@Remote(VagaRemote.class)
public class VagaDao implements VagaRemote {
	@PersistenceContext
	private EntityManager em;

	@Override
	public void save(Vaga c) {
		em.persist(c);
	}

	@Override
	public void update(Vaga c) {
		em.merge(c);
	}

	@Override
	public void delete(Vaga c) {
		em.remove(em.merge(c));
	}

	@Override
	public Vaga find(int id) {
		return em.find(Vaga.class, id);
	}

	@Override
	public List<Vaga> list() {
		TypedQuery<Vaga> query = em.createQuery("select cl from Vaga cl", Vaga.class); 
		return query.getResultList();
	}
	
	@Override
	public List<Vaga> list(Empresa empresa) {
		TypedQuery<Vaga> query = em.createQuery("select cl from Vaga cl where cl.empresa.id = '" + empresa.getId() + "'", Vaga.class); 
		return query.getResultList();
	}

}