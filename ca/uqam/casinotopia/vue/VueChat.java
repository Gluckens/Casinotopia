package ca.uqam.casinotopia.vue;

import javax.swing.DefaultListModel;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JButton;

import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.controleur.client.ControleurChatClient;
import ca.uqam.casinotopia.modele.client.ModeleChatClient;
import ca.uqam.casinotopia.observateur.Observable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
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
	public JScrollPane scrollPane;
	public JTextField txtSeConnecterA;
	public JLabel lblTitre;
	
	public VueChat(ControleurClient controleur) {
		this.controleur = (ControleurChatClient) controleur;
		
		this.setPanelOptions();
		this.addComponents();
		//this.createComponentsMap();
	}

	/*public VueChat(ControleurChatClient ctrl, FrameApplication frame) {
		this();
		this.controleur = ctrl;
		btnSeConnecter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtSeConnecterA.getText().isEmpty()) {
					txtSeConnecterA.setText("entrez un nom de salle ici");
				}
				else {
					controleur.cmdSeConnecterAuChat(txtSeConnecterA.getText());
				}
			}
		});
		
		
		txtSeConnecterA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				envoyerMessageChat();
			}
		});
		txtMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				envoyerMessageChat();
			}
		});
	}*/
	
	/**
	 * Create the panel.
	 * @return 
	 */
	@Override
	protected void addComponents() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{142, 93, 177, 0};
		gridBagLayout.rowHeights = new int[]{15, 228, 20, 23, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		lblTitre = new JLabel("Les autres gens");
		lblTitre.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		GridBagConstraints gbc_lblTitre = new GridBagConstraints();
		gbc_lblTitre.fill = GridBagConstraints.BOTH;
		gbc_lblTitre.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitre.gridx = 0;
		gbc_lblTitre.gridy = 0;
		add(lblTitre, gbc_lblTitre);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(Color.LIGHT_GRAY));
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.anchor = GridBagConstraints.WEST;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 0;
		add(scrollPane, gbc_scrollPane);
		
		txtChat = new JTextArea();
		txtChat.setColumns(10);
		txtChat.setLineWrap(true);
		txtChat.setEditable(false);
		txtChat.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		scrollPane.setViewportView(txtChat);
		txtChat.setBackground(Color.WHITE);
		txtChat.setForeground(new Color(0, 0, 0));
		//lstConnecte = new JList(model);
		lstConnecte.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lstConnecte.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		GridBagConstraints gbc_lstConnecte = new GridBagConstraints();
		gbc_lstConnecte.fill = GridBagConstraints.BOTH;
		gbc_lstConnecte.insets = new Insets(0, 0, 5, 5);
		gbc_lstConnecte.gridx = 0;
		gbc_lstConnecte.gridy = 1;
		add(lstConnecte, gbc_lstConnecte);
		
		JLabel lblToi = new JLabel("Toi");
		lblToi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblToi.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		GridBagConstraints gbc_lblToi = new GridBagConstraints();
		gbc_lblToi.fill = GridBagConstraints.BOTH;
		gbc_lblToi.insets = new Insets(0, 0, 5, 5);
		gbc_lblToi.gridx = 0;
		gbc_lblToi.gridy = 2;
		add(lblToi, gbc_lblToi);
		
		txtMessage = new JTextField();
		txtMessage.setToolTipText("Entr\u00E9e pour envoyer");
		GridBagConstraints gbc_txtMessage = new GridBagConstraints();
		gbc_txtMessage.anchor = GridBagConstraints.NORTH;
		gbc_txtMessage.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMessage.insets = new Insets(0, 0, 5, 0);
		gbc_txtMessage.gridwidth = 2;
		gbc_txtMessage.gridx = 1;
		gbc_txtMessage.gridy = 2;
		add(txtMessage, gbc_txtMessage);
		txtMessage.setColumns(10);
		txtMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				envoyerMessageChat();
			}
		});
		
		txtSeConnecterA = new JTextField();
		txtSeConnecterA.setText("salle1");
		GridBagConstraints gbc_txtSeConnecterA = new GridBagConstraints();
		gbc_txtSeConnecterA.fill = GridBagConstraints.BOTH;
		gbc_txtSeConnecterA.insets = new Insets(0, 0, 0, 5);
		gbc_txtSeConnecterA.gridwidth = 2;
		gbc_txtSeConnecterA.gridx = 0;
		gbc_txtSeConnecterA.gridy = 3;
		add(txtSeConnecterA, gbc_txtSeConnecterA);
		txtSeConnecterA.setColumns(10);
		
		
		txtSeConnecterA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				envoyerMessageChat();
			}
		});
		
		btnSeConnecter = new JButton("Se connecter au chat");
		GridBagConstraints gbc_btnSeConnecter = new GridBagConstraints();
		gbc_btnSeConnecter.anchor = GridBagConstraints.NORTH;
		gbc_btnSeConnecter.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSeConnecter.gridx = 2;
		gbc_btnSeConnecter.gridy = 3;
		add(btnSeConnecter, gbc_btnSeConnecter);
		
		btnSeConnecter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtSeConnecterA.getText().isEmpty()) {
					txtSeConnecterA.setText("entrez un nom de salle ici");
				}
				else {
					controleur.cmdSeConnecterAuChat(txtSeConnecterA.getText());
				}
			}
		});
		
		DefaultListModel model = new DefaultListModel();
		
		this.setSize(new Dimension(400, 400));

	}
	
	public void envoyerMessageChat() {
		if(!txtMessage.getText().isEmpty()) {
			this.controleur.cmdEnvoyerMessageChat(txtMessage.getText());
			txtMessage.setText("");
			txtMessage.setFocusable(true);
		}
	}

	@Override
	public void update(Observable observable) {
		if(observable instanceof ModeleChatClient) {
			ModeleChatClient modele = (ModeleChatClient) observable;
			
			//TODO voir un méchanisme si on veut faire une chose en particulier (récupérer dernier message)
			//On pourrait potentiellement avec des enum de modif dans les modeles, et des méthodes get/set TypeModif.
			//Lors de la mise a jour du modele par le ctrl, on set le type de modif si c'est une modif précise (sinon on changerait le modele au complet)
			//Dans l'observateur (vue), on peux faire un switch sur le type et faire des actions précises, et éviter de refresher toujours tout si pas besoin.
			//EX : String message = modele.getLastMessage();
			String message = "Nouveau message";
			
			txtChat.setText(txtChat.getText() + "\n" + message);
			txtChat.setCaretPosition(txtChat.getText().length());
			JScrollBar jsb = scrollPane.getVerticalScrollBar();
			jsb.setValue(jsb.getMaximum());
		}
	}
}
