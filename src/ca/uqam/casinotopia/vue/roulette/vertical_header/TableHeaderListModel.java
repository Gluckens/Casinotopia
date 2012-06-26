package ca.uqam.casinotopia.vue.roulette.vertical_header;

import javax.swing.AbstractListModel;

@SuppressWarnings("serial")
public class TableHeaderListModel extends AbstractListModel {
	
	String[] headers;
	
	public TableHeaderListModel() {
		this(null);
	}
	
	public TableHeaderListModel(String[] headers) {
		this.headers = headers;
	}
	
	public void clear() {
		this.headers = null;
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