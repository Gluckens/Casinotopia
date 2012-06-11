package ca.uqam.casinotopia.vue.roulette;

import javax.swing.AbstractListModel;

public class TableHeaderListModel extends AbstractListModel {
	
	private static final long serialVersionUID = 1L;
	String[] headers;
	
	public TableHeaderListModel() {
		this(null);
	}
	
	public TableHeaderListModel(String[] headers) {
		this.headers = headers;
	}
	
	public void setHeaders(String[] headers) {
		this.headers = headers;
	}
	
	@Override
	public Object getElementAt(int index) {
		if(this.headers != null) {
			return this.headers[index];
		}
		
		return null;
	}

	@Override
	public int getSize() {
		if(this.headers != null) {
			return this.headers.length;
		}
		
		return 0;
	}
}
