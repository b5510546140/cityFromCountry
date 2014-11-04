import java.io.ByteArrayInputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import view.Gui;
import controller.Controller;
import net.webservicex.GlobalWeather;
import net.webservicex.GlobalWeatherSoap;

public class Main {
//	private static NewDataSet newDataSet;
	public static void main(String[] args) {
//		GlobalWeather factory = new GlobalWeather();
//		GlobalWeatherSoap proxy = factory.getGlobalWeatherSoap();
//		String response = proxy.getCitiesByCountry("Thailand");
//		System.out.println(response);
//		
//		JAXBContext ctx = JAXBContext.newInstance( NewDataSet.class );
//		Unmarshaller unmarshaller = ctx.createUnmarshaller();
//		Object obj = unmarshaller.unmarshal(new ByteArrayInputStream(response.getBytes()));
//		newDataSet = (NewDataSet) obj;
		
		Gui gui = new Gui();
		gui.run();
		
	}

}
