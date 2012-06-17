package ca.uqam.casinotopia.vue.chat;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
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

import ca.uqam.casinotopia.controleur.client.ControleurChatClient;
import ca.uqam.casinotopia.modele.client.ModeleChatClient;
import ca.uqam.casinotopia.observateur.Observable;
import ca.uqam.casinotopia.vue.Vue;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

@SuppressWarnings("serial")
public class VueChat extends Vue {

	private ControleurChatClient controleur;

	public JTextArea txtChat;
	public JTextField txtMessage;
	public JList lstConnecte;
	public JButton btnSeConnecter;
	public JTextField txtSeConnecterA;
	public JLabel lblTitre;
	private JPanel pnlChat;
	private JPanel pnlListeUtilisateur;
	private JScrollPane scrollPane;
	private JButton btnQuitter;

	public VueChat(ControleurChatClient ctrl){
		this.addComponents();
		this.controleur = ctrl;
		
		btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controleur.cmdQuitterPartie();
			}
		});
		
		GridBagConstraints gbc_btnQuitter = new GridBagConstraints();
		gbc_btnQuitter.gridx = 2;
		gbc_btnQuitter.gridy = 3;
		add(btnQuitter, gbc_btnQuitter);
		btnSeConnecter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				seConnecterAuChat();
			}
		});
		
		txtSeConnecterA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				seConnecterAuChat();
			}
		});

		txtMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				envoyerMessageChat();
			}
		});
		
		this.setPanelOptions();
	}

	protected void envoyerMessageChat() {
		if(!txtMessage.getText().isEmpty()){
			controleur.cmdEnvoyerMessageChat();
		}

	}

	protected void seConnecterAuChat() {
		if(txtSeConnecterA.getText().isEmpty()){
			txtSeConnecterA.setText("entrez un nom de salle ici");
		}else{
			controleur.cmdSeConnecterAuChat(txtSeConnecterA.getText());
		}
	}

	@Override
	protected void addComponents() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{100, 64, 158, 0};
		gridBagLayout.rowHeights = new int[]{15, 20, 23, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		pnlListeUtilisateur = new JPanel();
		GridBagConstraints gbc_pnlListeUtilisateur = new GridBagConstraints();
		gbc_pnlListeUtilisateur.fill = GridBagConstraints.BOTH;
		gbc_pnlListeUtilisateur.insets = new Insets(0, 0, 5, 5);
		gbc_pnlListeUtilisateur.gridx = 0;
		gbc_pnlListeUtilisateur.gridy = 0;
		add(pnlListeUtilisateur, gbc_pnlListeUtilisateur);
		GridBagLayout gbl_pnlListeUtilisateur = new GridBagLayout();
		gbl_pnlListeUtilisateur.columnWidths = new int[]{100, 0};
		gbl_pnlListeUtilisateur.rowHeights = new int[]{15, 228, 0};
		gbl_pnlListeUtilisateur.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_pnlListeUtilisateur.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		pnlListeUtilisateur.setLayout(gbl_pnlListeUtilisateur);

		lblTitre = new JLabel("Les autres gens");
		GridBagConstraints gbc_lblTitre = new GridBagConstraints();
		gbc_lblTitre.fill = GridBagConstraints.BOTH;
		gbc_lblTitre.insets = new Insets(0, 0, 5, 0);
		gbc_lblTitre.gridx = 0;
		gbc_lblTitre.gridy = 0;
		pnlListeUtilisateur.add(lblTitre, gbc_lblTitre);
		lblTitre.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		lstConnecte = new JList();
		GridBagConstraints gbc_lstConnecte = new GridBagConstraints();
		gbc_lstConnecte.fill = GridBagConstraints.BOTH;
		gbc_lstConnecte.gridx = 0;
		gbc_lstConnecte.gridy = 1;
		pnlListeUtilisateur.add(lstConnecte, gbc_lstConnecte);
		lstConnecte.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lstConnecte.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		
		pnlChat = new JPanel();
		GridBagConstraints gbc_pnlChat = new GridBagConstraints();
		gbc_pnlChat.fill = GridBagConstraints.BOTH;
		gbc_pnlChat.gridheight = 2;
		gbc_pnlChat.gridwidth = 2;
		gbc_pnlChat.insets = new Insets(0, 0, 5, 0);
		gbc_pnlChat.gridx = 1;
		gbc_pnlChat.gridy = 0;
		add(pnlChat, gbc_pnlChat);
		GridBagLayout gbl_pnlChat = new GridBagLayout();
		gbl_pnlChat.columnWidths = new int[]{212, 0};
		gbl_pnlChat.rowHeights = new int[]{255, 20, 0};
		gbl_pnlChat.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_pnlChat.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		pnlChat.setLayout(gbl_pnlChat);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		pnlChat.add(scrollPane, gbc_scrollPane);

		this.txtChat = new JTextArea();
		scrollPane.setViewportView(txtChat);
		txtChat.setLineWrap(true);
		txtChat.setEditable(false);
		txtChat.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		txtChat.setBackground(Color.WHITE);
		txtChat.setForeground(new Color(0, 0, 0));
		
		this.txtMessage = new JTextField();
		GridBagConstraints gbc_txtMessage = new GridBagConstraints();
		gbc_txtMessage.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMessage.gridx = 0;
		gbc_txtMessage.gridy = 1;
		pnlChat.add(txtMessage, gbc_txtMessage);
		txtMessage.setToolTipText("Entr\u00E9e pour envoyer");
		txtMessage.setColumns(10);

		txtMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				envoyerMessageChat();
			}
		});

		JLabel lblToi = new JLabel("Toi");
		lblToi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblToi.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		GridBagConstraints gbc_lblToi = new GridBagConstraints();
		gbc_lblToi.fill = GridBagConstraints.BOTH;
		gbc_lblToi.insets = new Insets(0, 0, 5, 5);
		gbc_lblToi.gridx = 0;
		gbc_lblToi.gridy = 1;
		this.add(lblToi, gbc_lblToi);

		txtSeConnecterA = new JTextField();
		txtSeConnecterA.setText("salle1");
		GridBagConstraints gbc_txtSeConnecterA = new GridBagConstraints();
		gbc_txtSeConnecterA.fill = GridBagConstraints.BOTH;
		gbc_txtSeConnecterA.insets = new Insets(0, 0, 5, 5);
		gbc_txtSeConnecterA.gridwidth = 2;
		gbc_txtSeConnecterA.gridx = 0;
		gbc_txtSeConnecterA.gridy = 2;
		add(txtSeConnecterA, gbc_txtSeConnecterA);
		txtSeConnecterA.setColumns(10);


		txtSeConnecterA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				envoyerMessageChat();
			}
		});

		btnSeConnecter = new JButton("Se connecter au chat");
		GridBagConstraints gbc_btnSeConnecter = new GridBagConstraints();
		gbc_btnSeConnecter.insets = new Insets(0, 0, 5, 0);
		gbc_btnSeConnecter.anchor = GridBagConstraints.NORTH;
		gbc_btnSeConnecter.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSeConnecter.gridx = 2;
		gbc_btnSeConnecter.gridy = 2;
		this.add(btnSeConnecter, gbc_btnSeConnecter);

		btnSeConnecter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				seConnecterAuChat();
			}
		});

		this.setSize(new Dimension(322, 335));
	}

	public void setLstConnecteModel(DefaultListModel model){
		lstConnecte.setModel(model);
	}

	@Override
	public void update(Observable observable) {
		if (observable instanceof ModeleChatClient) {
			ModeleChatClient modele = (ModeleChatClient) observable;

			this.lblTitre.setText(modele.getSalle());
			// (récupérer dernier message)
			//On pourrait potentiellement avec des enum de modif dans les modeles, et des méthodes get/set TypeModif.
			// modeles, et des méthodes get/set TypeModif.
			// Lors de la mise a jour du modele par le ctrl, on set le type de
			//Lors de la mise a jour du modele par le ctrl, on set le type de modif si c'est une modif précise (sinon on changerait le modele au complet)
			// au complet)
			// Dans l'observateur (vue), on peux faire un switch sur le type et
			//Dans l'observateur (vue), on peux faire un switch sur le type et faire des actions précises, et éviter de refresher toujours tout si pas besoin.
			// si pas besoin.
			//EX : String message = modele.getLastMessage();
			
			this.txtChat.setText(modele.getMessages());

			this.txtChat.setCaretPosition(this.txtChat.getText().length());
			JScrollBar jsb = this.scrollPane.getVerticalScrollBar();
			jsb.setValue(jsb.getMaximum());
		}
	}
	
	/**
	 * cache le bouton quitter, le bouton de connxion a une salle et le textbox
	 */
	public void cacherSalle(){
		this.btnQuitter.setVisible(false);
		this.btnSeConnecter.setVisible(false);
		this.txtSeConnecterA.setVisible(false);
	}
	
	
}
