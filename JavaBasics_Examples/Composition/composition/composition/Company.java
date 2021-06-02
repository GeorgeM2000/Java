package composition;

import java.util.ArrayList;

public class Company {
	private String name;
	private long afm;
	private ArrayList<Store> stores;
	
	public Company(String name, long afm, ArrayList<Store> stores) {
		super();
		this.name = name;
		this.afm = afm;
		this.stores = stores;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public long getAfm() {
		return afm;
	}
	
	public void setAfm(long afm) {
		this.afm = afm;
	}
	
	public ArrayList<Store> getStores() {
		return stores;
	}
	
	public void setStores(ArrayList<Store> stores) {
		this.stores = stores;
	}
	
}
