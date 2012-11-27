package dao;

import java.util.List;


import models.Curriculo;

public interface CurriculoRemote {
	public void save(Curriculo c);
	public void update(Curriculo c);
	public void delete(Curriculo c);
	public Curriculo find(int id);
	public List<Curriculo> list();
}
