package controller;
/**
 * @author wat wattanagaroon
 * @version 2014/11/04
 */
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
	
	/**
	 * This connect with soap webservice
	 * @param country 
	 */
	public void connect(String country){
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
	/**
	 * change list of table in xml to array
	 */
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
