package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.JButton;

import models.Candidatura;
import models.Empresa;
import models.Vaga;
import dao.CandidaturaRemote;
import dao.VagaRemote;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Vector;

public class Candidaturas extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	/**
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Candidaturas frame = new Candidaturas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
	/**
	 * Create the frame.
	 */
	public Candidaturas(final Empresa empresa) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		Vector<Vector<String>> rowData = new Vector<Vector<String>>();
		Vector<String> columnNames = new Vector<String>();
		columnNames.addElement("ID");
		columnNames.addElement("Cargo");
		columnNames.addElement("Area de Atua��o");
		columnNames.addElement("Nome");
		try {
			InitialContext ic = new InitialContext();
			CandidaturaRemote candidaturaDao = (CandidaturaRemote)ic.lookup("java:global/curriculoEAR/curriculo/CandidaturaDao");
			List<Candidatura> listaCandidaturas = candidaturaDao.list(empresa);
			for(Candidatura candidatura : listaCandidaturas){
				Vector<String> row = new Vector<String>();
				row.addElement(String.valueOf(candidatura.getId()));
				row.addElement(candidatura.getVaga().getCargo());
				row.addElement(candidatura.getVaga().getAreaAtuacao().getNome());
				row.addElement(candidatura.getCandidato().getNome());
				rowData.addElement(row);
			}
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		table = new JTable(rowData, columnNames);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(15)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnVoltar)
						.addComponent(table, GroupLayout.PREFERRED_SIZE, 409, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(16, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(16, Short.MAX_VALUE)
					.addComponent(btnVoltar)
					.addGap(18)
					.addComponent(table, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)
					.addGap(17))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
