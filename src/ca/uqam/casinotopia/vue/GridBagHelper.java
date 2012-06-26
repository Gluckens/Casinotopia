package ca.uqam.casinotopia.vue;

import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * Classe permettant de simplifier la paramétrisation du positionement des components dans un GridBagLayout
 */
@SuppressWarnings("serial")
public class GridBagHelper extends GridBagConstraints {
	private GridBagConstraints gbc = new GridBagConstraints();

	public GridBagHelper setXY(int gridx, int gridy) {
		this.gbc.gridx = gridx;
		this.gbc.gridy = gridy;
		return this;
	}

	public GridBagHelper setWH(int gridwidth, int gridheight) {
		this.gbc.gridwidth = gridwidth;
		this.gbc.gridheight = gridheight;
		return this;
	}

	public GridBagHelper setFill(int fill) {
		this.gbc.fill = fill;
		return this;
	}

	public GridBagHelper setAnchor(int anchor) {
		this.gbc.anchor = anchor;
		return this;
	}

	public GridBagHelper setPad(int ipadx, int ipady) {
		this.gbc.ipadx = ipadx;
		this.gbc.ipady = ipady;
		return this;
	}

	public GridBagHelper setInsets(Insets insets) {
		this.gbc.insets = insets;
		return this;
	}

	public GridBagHelper setWeight(int weightx, int weighty) {
		this.gbc.weightx = weightx;
		this.gbc.weighty = weighty;
		return this;
	}

	public GridBagConstraints end() {
		return this.gbc;
	}
}
