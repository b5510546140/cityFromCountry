package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
/**
 * 
 * @author wat wattanagaroon
 * @version 2014/9/10
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Table {
	
	@XmlElement(name="Country")
	private String country;
	@XmlElement(name="City")
	private String city;
	
	public Table() {}
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
}
