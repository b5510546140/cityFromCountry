package controller;


import java.io.ByteArrayInputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import model.NewDataSet;
import model.Table;
import net.webservicex.GlobalWeather;
import net.webservicex.GlobalWeatherSoap;

public class Controller {
	
	private NewDataSet newDataSet;
	private Table[] itemArray;
	private GlobalWeatherSoap proxy;
	
	public Controller(){
		GlobalWeather factory = new GlobalWeather();
		proxy = factory.getGlobalWeatherSoap();

		
	}
	
	
	public void Connect(String country){
			itemArray = null;
			String response = proxy.getCitiesByCountry(country);
			JAXBContext ctx = null;
			try {
				ctx = JAXBContext.newInstance( NewDataSet.class );
			Unmarshaller unmarshaller = null;
			unmarshaller = ctx.createUnmarshaller();
			Object obj = null;
			obj = unmarshaller.unmarshal(new ByteArrayInputStream(response.getBytes()));
			newDataSet = (NewDataSet) obj;
			} catch (JAXBException e) {
				e.printStackTrace();
			}
			setListToArray();
	}
	
	public void setListToArray(){
		List<Table> tableList = newDataSet.getTable();

		if(tableList != null)
			itemArray = tableList.toArray(new Table[tableList.size()]);
		else
			itemArray = null;
	}
	
	public Table[] getList(){
		return itemArray;
	}
}
