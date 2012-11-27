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

import dao.CandidaturaRemote;
import dao.VagaRemote;

import models.Candidatura;
import models.Empresa;
import models.Vaga;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Vector;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Vagas extends JFrame {

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
					Vagas frame = new Vagas();
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
	public Vagas(final Empresa empresa) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		Vector<Vector<String>> rowData = new Vector<Vector<String>>();
		Vector<String> columnNames = new Vector<String>();
		columnNames.addElement("ID");
		columnNames.addElement("Cargo");
		columnNames.addElement("Area de Atuação");
		try {
			InitialContext ic = new InitialContext();
			VagaRemote vagaDao = (VagaRemote)ic.lookup("java:global/curriculoEAR/curriculo/VagaDao");
			List<Vaga> listaVagas = vagaDao.list(empresa);
			for(Vaga vaga : listaVagas){
				Vector<String> row = new Vector<String>();
				row.addElement(String.valueOf(vaga.getId()));
				row.addElement(vaga.getCargo());
				row.addElement(vaga.getAreaAtuacao().getNome());
				rowData.addElement(row);
			}
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		table = new JTable(rowData, columnNames);
		
		JButton btnCadastrarNovaVaga = new JButton("Cadastrar nova Vaga");
		btnCadastrarNovaVaga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new NovaVaga(empresa).setVisible(true);
			}
		});
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JButton btnApagar = new JButton("Apagar");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					InitialContext ic = new InitialContext();
					VagaRemote vagaDao = (VagaRemote)ic.lookup("java:global/curriculoEAR/curriculo/VagaDao");
					CandidaturaRemote candidaturaDao = (CandidaturaRemote)ic.lookup("java:global/curriculoEAR/curriculo/CandidaturaDao");
					Vaga vaga = vagaDao.find(Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), 0)));
					List<Candidatura> listaCandidaturas = candidaturaDao.list(vaga);
					for(Candidatura candidatura : listaCandidaturas){
						candidaturaDao.delete(candidatura);
					}
					vagaDao.delete(vaga);
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
				new Vagas(empresa).setVisible(true);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(table, GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnCadastrarNovaVaga)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnApagar)
							.addPreferredGap(ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
							.addComponent(btnSair)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCadastrarNovaVaga)
						.addComponent(btnSair)
						.addComponent(btnApagar))
					.addGap(18)
					.addComponent(table, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
