package dao;

import java.util.List;

import models.Empresa;
import models.Vaga;

public interface VagaRemote {
	public void save(Vaga c);
	public void update(Vaga c);
	public void delete(Vaga c);
	public Vaga find(int id);
	public List<Vaga> list();
	public List<Vaga> list(Empresa empresa);
}
