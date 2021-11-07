package composition;

import java.util.ArrayList;

public class CompositionExample {

	public static void main(String[] args) {
		Store store1 = new Store("Τσιμισκή 156", 10, "Θεσσαλονίκη");
		Store store2 = new Store("Ερμού 18", 15, "Αθήνα");
		Store store3 = new Store("Ελ. Βενίζου 27", 5, "Γιαννιτσά");
		ArrayList<Store> al = new ArrayList<Store>();
		al.add(store1);
		al.add(store2);
		al.add(store3);
		Company myCompany = new Company("Η εταιρία μου", 423991123, al);
		
		System.out.println("Όνομα εταιρίας="+myCompany.getName());
		System.out.println("ΑΦΜ εταιρίας="+myCompany.getAfm());
		System.out.println("Καταστήματα εταιρίας="+myCompany.getStores());
	}

}
