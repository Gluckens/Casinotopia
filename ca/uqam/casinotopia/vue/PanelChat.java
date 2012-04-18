package ca.uqam.casinotopia.vue;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JButton;

import ca.uqam.casinotopia.controleur.client.ControleurClientPrincipal;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class PanelChat extends JPanel {
	
	private ControleurClientPrincipal controleur;
	
	public JTextArea txtChat;
	public JTextField txtMessage;
	public JList lstConnecte;
	public JButton btnSeConnecter;
	public JScrollPane scrollPane;
	public JTextField txtSeConnecterA;
	public JLabel lblTitre;

	public PanelChat(ControleurClientPrincipal ctrl){
		this();
		this.controleur = ctrl;
		btnSeConnecter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controleur.seConnecterAuChat();
			}
		});
		
		txtMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controleur.envoyerMessageChat(txtMessage.getText());
			}
		});
	}
	
	/**
	 * Create the panel.
	 */
	private PanelChat() {
		setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(160, 11, 280, 247);
		scrollPane.setBorder(new LineBorder(Color.LIGHT_GRAY));
		add(scrollPane);
		
		txtChat = new JTextArea();
		txtChat.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		scrollPane.setViewportView(txtChat);
		txtChat.setBackground(Color.WHITE);
		txtChat.setEditable(false);
		txtChat.setForeground(new Color(0, 0, 0));
		
		txtMessage = new JTextField();
		txtMessage.setToolTipText("Entr\u00E9e pour envoyer");
		txtMessage.setBounds(160, 269, 280, 20);
		add(txtMessage);
		txtMessage.setColumns(10);
		
		lblTitre = new JLabel("Les autres gens");
		lblTitre.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		lblTitre.setBounds(10, 11, 142, 15);
		add(lblTitre);
		
		DefaultListModel model = new DefaultListModel();
		lstConnecte = new JList(model);
		lstConnecte.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lstConnecte.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		lstConnecte.setBounds(10, 30, 142, 228);
		add(lstConnecte);
		
		JLabel lblToi = new JLabel("Toi");
		lblToi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblToi.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		lblToi.setBounds(10, 269, 140, 20);
		add(lblToi);
		
		btnSeConnecter = new JButton("Se connecter au chat");
		btnSeConnecter.setBounds(263, 295, 177, 23);
		add(btnSeConnecter);
		
		txtSeConnecterA = new JTextField();
		txtSeConnecterA.setText("salle1");
		txtSeConnecterA.setBounds(10, 296, 243, 22);
		add(txtSeConnecterA);
		txtSeConnecterA.setColumns(10);

	}
}
