package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import models.Empresa;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/**
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
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
	public Principal(final Empresa empresa) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 425, 121);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnDeslogar = new JButton("Deslogar");
		btnDeslogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new Main().setVisible(true);
			}
		});
		
		JButton btnPerfil = new JButton("Perfil");
		
		JButton btnCurriculos = new JButton("Curriculos");
		btnCurriculos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Curriculos().setVisible(true);
			}
		});
		
		JButton btnVagas = new JButton("Vagas");
		btnVagas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Vagas(empresa).setVisible(true);
			}
		});
		
		JButton btnCandidaturas = new JButton("Candidaturas");
		btnCandidaturas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Candidaturas(empresa).setVisible(true);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnDeslogar)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnPerfil)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnVagas)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCurriculos)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCandidaturas)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnDeslogar)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnPerfil)
						.addComponent(btnVagas)
						.addComponent(btnCurriculos)
						.addComponent(btnCandidaturas))
					.addContainerGap(19, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
