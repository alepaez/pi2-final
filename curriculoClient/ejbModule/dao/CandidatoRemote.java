package dao;

import java.util.List;
import models.Candidato;


public interface CandidatoRemote {
	public void save(Candidato c);
	public void update(Candidato c);
	public void delete(Candidato c);
	public Candidato find(int id);
	public Candidato find(String login);
	public List<Candidato> list();
}
