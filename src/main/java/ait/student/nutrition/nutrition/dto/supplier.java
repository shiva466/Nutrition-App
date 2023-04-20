package ait.student.nutrition.nutrition.dto;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity


public class supplier {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private long Id;
	private String supplier_name;
	private String item;
	private String item_code;
	private String manufacting_cost;
	private String selling_cost;
	private String profit_gained;
	public supplier()
	{
		
	}
	@JsonCreator
	public supplier(@JsonProperty("Id") long Id,@JsonProperty("supplier_name") String supplier_name,@JsonProperty("item") String item,@JsonProperty("item_code") String item_code,@JsonProperty("manufacting_cost") String manufacting_cost,@JsonProperty("selling_cost") String selling_cost,@JsonProperty("profit_gained") String profit_gained) {
		this.Id= Id; 
		this.supplier_name = supplier_name; 
		this.item = item; 
		this.item_code =item_code; 
		this.manufacting_cost = manufacting_cost; 
		this.selling_cost = selling_cost;
		this.profit_gained = profit_gained;
	}

	public Long getId() 
	  {
		  return Id;
	  } 
	  public void setId(Long Id) 
	  { 
		  this.Id=Id;
	  }
	 
	public String getSupplier_name() {
		return supplier_name;
	}
	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getItem_code() {
		return item_code;
	}
	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}
	public String getManufacting_cost() {
		return manufacting_cost;
	}
	public void setManufacting_cost(String manufacting_cost) {
		this.manufacting_cost = manufacting_cost;
	}
	public String getSelling_cost() {
		return selling_cost;
	}
	public void setSelling_cost(String selling_cost) {
		this.selling_cost = selling_cost;
	}
	public String getProfit_gained() {
		return profit_gained;
	}
	public void setProfit_gained(String profit_gained) {
		this.profit_gained = profit_gained;
	}
	
}
