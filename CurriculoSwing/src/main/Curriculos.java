package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;
import java.util.Vector;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import models.Candidato;
import models.Curriculo;
import models.Universidade;

import dao.CandidatoRemote;
import dao.CurriculoRemote;
import dao.UniversidadeRemote;
import dao.VagaRemote;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Curriculos extends JFrame {

	private JPanel contentPane;
	private JTable table;
	/**
	 * Create the frame.
	 */
	public Curriculos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		Vector<Vector<String>> rowData = new Vector<Vector<String>>();
		Vector<String> columnNames = new Vector<String>();
		columnNames.addElement("Nome");
		columnNames.addElement("Objetivo Profissional");
		columnNames.addElement("Universidade");
		columnNames.addElement("Idioma");
		try {
			InitialContext ic = new InitialContext();
			CandidatoRemote candidatoDao = (CandidatoRemote)ic.lookup("java:global/curriculoEAR/curriculo/CandidatoDao");
			List<Candidato> listaCandidatos = candidatoDao.list();
			UniversidadeRemote universidadeDao = (UniversidadeRemote)ic.lookup("java:global/curriculoEAR/curriculo/UniversidadeDao");
			for(Candidato candidato : listaCandidatos){
				Vector<String> row = new Vector<String>();
				Curriculo curriculo = candidato.getCurriculo();
				Universidade universidade = universidadeDao.find(curriculo.getUniversidadeId());
				row.addElement(candidato.getNome());
				row.addElement(curriculo.getObjProf());
				if(universidade == null){
					row.addElement("");
				}else {
					row.addElement(universidade.getNome());
				}
				row.addElement(curriculo.getIdioma());
				rowData.addElement(row);
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		table = new JTable(rowData, columnNames);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(298, Short.MAX_VALUE)
					.addComponent(btnVoltar)
					.addGap(25))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(table, GroupLayout.PREFERRED_SIZE, 423, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(11, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnVoltar)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(table, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
					.addGap(17))
		);
		contentPane.setLayout(gl_contentPane);
	}

}
