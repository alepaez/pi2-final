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
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JComboBox;

import dao.AreaAtuacaoRemote;
import dao.EmpresaRemote;
import dao.VagaRemote;

import models.AreaAtuacao;
import models.Empresa;
import models.Vaga;

public class NovaVaga extends JFrame {

	private JPanel contentPane;
	private JTextField cargo;
	private JComboBox areaAtuacao;

	/**
	 * Launch the application.
	 */
	/**
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NovaVaga frame = new NovaVaga();
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
	public NovaVaga(final Empresa empresa){
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 315, 263);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblCargo = new JLabel("Cargo");
		
		JLabel lblAreaAtuao = new JLabel("Area Atua\u00E7\u00E3o");
		
		cargo = new JTextField();
		cargo.setColumns(10);
		
		JButton btnCadastrarVaga = new JButton("Cadastrar Vaga");
		btnCadastrarVaga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InitialContext ic;
				try {
					ic = new InitialContext();
					VagaRemote vagaDao = (VagaRemote)ic.lookup("java:global/curriculoEAR/curriculo/VagaDao");
					Vaga vaga = new Vaga();
					vaga.setEmpresa(empresa);
					vaga.setAreaAtuacao((AreaAtuacao)areaAtuacao.getSelectedItem());
					vaga.setCargo(cargo.getText());
					vagaDao.save(vaga);
				} catch (NamingException e1){
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
				new Vagas(empresa).setVisible(true);
			}
		});
		
		
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Vagas(empresa).setVisible(true);
			}
		});
		
		areaAtuacao = new JComboBox();
		InitialContext ic;
		try {
			ic = new InitialContext();
			AreaAtuacaoRemote aaDao = (AreaAtuacaoRemote)ic.lookup("java:global/curriculoEAR/curriculo/AreaAtuacaoDao");
			List<AreaAtuacao> listaAreas = aaDao.list();
			for (AreaAtuacao a : listaAreas ){
				areaAtuacao.addItem(a);
			}
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(38)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblAreaAtuao)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(areaAtuacao, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblCargo)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(cargo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(25)
							.addComponent(btnCadastrarVaga)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnCancelar)))
					.addContainerGap(32, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(81)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(cargo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCargo))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAreaAtuao)
						.addComponent(areaAtuacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCadastrarVaga)
						.addComponent(btnCancelar))
					.addContainerGap(35, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
