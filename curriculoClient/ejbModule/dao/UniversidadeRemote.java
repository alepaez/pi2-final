package dao;

import java.util.List;

import models.Universidade;

public interface UniversidadeRemote {
	public void save(Universidade c);
	public void update(Universidade c);
	public void delete(Universidade c);
	public Universidade find(int id);
	public List<Universidade> list();
}
