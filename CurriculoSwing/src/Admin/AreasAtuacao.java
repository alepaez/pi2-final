package Admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;

import models.AreaAtuacao;

import dao.AreaAtuacaoRemote;

public class AreasAtuacao extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public AreasAtuacao() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		Vector<Vector<String>> rowData = new Vector<Vector<String>>();
		Vector<String> columnNames = new Vector<String>();
		columnNames.addElement("ID");
		columnNames.addElement("Nome");
		
		try {
			InitialContext ic = new InitialContext();
			AreaAtuacaoRemote aaDao = (AreaAtuacaoRemote)ic.lookup("java:global/curriculoEAR/curriculo/AreaAtuacaoDao");
			List<AreaAtuacao> listaAreasAtuacao = aaDao.list();
			for(AreaAtuacao areaAtuacao : listaAreasAtuacao){
				Vector<String> row = new Vector<String>();
				row.addElement(String.valueOf(areaAtuacao.getId()));
				row.addElement(areaAtuacao.getNome());
				rowData.addElement(row);
			}
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		table = new JTable(rowData, columnNames);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					InitialContext ic = new InitialContext();
					AreaAtuacaoRemote aaDao = (AreaAtuacaoRemote)ic.lookup("java:global/curriculoEAR/curriculo/AreaAtuacaoDao");
					new EditarAreaAtuacao(aaDao.find(Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), 0)))).setVisible(true);
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		
		JButton btnNovaAreaDe = new JButton("Nova Area de Atua\u00E7\u00E3o");
		btnNovaAreaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new NovaAreaAtuacao().setVisible(true);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(20)
							.addComponent(btnNovaAreaDe)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnEditar)
							.addPreferredGap(ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
							.addComponent(btnSair)
							.addGap(36))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(table, GroupLayout.PREFERRED_SIZE, 419, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(15, Short.MAX_VALUE))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSair)
						.addComponent(btnNovaAreaDe)
						.addComponent(btnEditar))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(table, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(7, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
