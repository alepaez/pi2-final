package dao;

import java.util.List;

import models.AreaAtuacao;

public interface AreaAtuacaoRemote {
	public void save(AreaAtuacao c);
	public void update(AreaAtuacao c);
	public void delete(AreaAtuacao c);
	public AreaAtuacao find(int id);
	public List<AreaAtuacao> list();
}
