package model;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="NewDataSet")
public class NewDataSet {
	@XmlElement(name="Table")
	private List<Table> table;
	private NewDataSet NewDataSet;

	public NewDataSet getNewDataSet() {
		return NewDataSet;
	}

	public void setNewDataSet(NewDataSet newDataSet) {
		this.NewDataSet = newDataSet;
	}

	public List<Table> getTable() {
		return table;
	}

}
