package ca.uqam.casinotopia;

import java.util.Map;

import javax.swing.table.AbstractTableModel;

public class TableJeuTableModel extends AbstractTableModel {

	private String[] columnNames;
	private Object[][] data;

	// List<>
	private Map<Case, Map<Integer, Integer>> dataCases;

	public TableJeuTableModel(Map<Case, Map<Integer, Integer>> cases, Object[] columnNames) {
		// TODO Auto-generated constructor stub

		this.setData(cases, columnNames);
	}

	public void setData(Map<Case, Map<Integer, Integer>> cases, Object[] columnNames) {
		this.data = new Object[6][];

		String nomCase;
		Integer idJoueur, mise;
		// String
		int k = 0;
		for (Map.Entry<Case, Map<Integer, Integer>> m1 : cases.entrySet()) {
			nomCase = m1.getKey().toString();
			for (Map.Entry<Integer, Integer> m2 : m1.getValue().entrySet()) {
				idJoueur = m2.getKey();
				mise = m2.getValue();

				for (int i = 0; i < columnNames.length; i++) {
					if (columnNames[i].equals(idJoueur)) {
						// this.data[k] =
					}
				}

				// this.data[] = {
			}

			k++;
		}
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getValueAt(int row, int col) {
		// this.dataCases.

		return null;
	}

}
