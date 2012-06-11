package ca.uqam.casinotopia.vue.roulette;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class JoueursRouletteTableModel extends DefaultTableModel {
	//public Joueur
	
	public JoueursRouletteTableModel() {
		super();
	}
	
	public JoueursRouletteTableModel(int rowCount, int columnCount) {
		super(rowCount, columnCount);
	}
	
	public JoueursRouletteTableModel(ImageIcon[][] data, String[] headers) {
		super(data, headers);
	}

	@Override
	public Class<?> getColumnClass(int column) {
	    return Icon.class;
	}
}
