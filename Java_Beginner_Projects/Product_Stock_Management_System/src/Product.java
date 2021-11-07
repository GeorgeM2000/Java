import java.io.*;


public class Product implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int product_id;
	int product_quantity;
	
	Product(int p_id,int p_qty){
		product_id = p_id;
		product_quantity = p_qty;
	}
	
	public void print() {
        System.out.println("ID " + this.product_id + " Quantity " + this.product_quantity);
    }
}
