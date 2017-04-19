/**
 * 
 */
package cn.zhang.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * @author hiynn 
 * @Date 2016年6月22日上午10:36:42
 */
public class City{

	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="CITY_SEQ")
	@SequenceGenerator(name="CITY_SEQ",sequenceName="CITY_SEQ")
	private Integer id;
	
	private String name; 
	 
	  
	private String state; 
	  

	public City() {
		// TODO Auto-generated constructor stub
	}
	
	public City(String name,String state){
		
		this.name=name;
		
		this.state=state;
		
	}
	public String getName() { 
		  
	     return name; 
	     
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public void setName(String name) {
		this.name = name;
	} 
	 
	  
  

}
