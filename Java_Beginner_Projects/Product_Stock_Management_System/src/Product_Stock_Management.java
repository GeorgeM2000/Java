import java.io.*;
import java.util.*;


public class Product_Stock_Management {
	
	
	// Read Method ----------------------------------------
	public static void readData(File f) {
		
		if(f.length() != 0) {
			try {
	            FileInputStream inputStream = new FileInputStream(f);
	            ObjectInputStream dataInputStream = new ObjectInputStream(inputStream);

	            Product product = null;
	            while(inputStream.available() != 0) {	 
	            	product = (Product) dataInputStream.readObject();
	            	product.print();
	            }

	            dataInputStream.close();
	            inputStream.close();
	            
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        } catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
        
    }
	
	// Write Method --------------------------------------------
	public static void writeData(File f, ArrayList<Product> p, boolean mode) {
		if(mode) {
			try {
	            FileOutputStream outputStream = new FileOutputStream(f, true);
	            
	            if(f.length() == 0) {
	            	ObjectOutputStream dataOutputStream = new ObjectOutputStream(outputStream);
	            	
	            	for(int i=0; i<p.size(); i++) {
	            		dataOutputStream.writeObject(p.get(i));
	            	}
	            	dataOutputStream.close();
	            } else {
	            	MyObjectOutputStream dataOutputStream = new MyObjectOutputStream(outputStream);
	            	
	            	for(int i=0; i<p.size(); i++) {
	            		dataOutputStream.writeObject(p.get(i));
	            	}
	            	dataOutputStream.close();
	            }
	            

	            outputStream.close();
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
		} else {
			
			try {
				FileOutputStream outputStream = new FileOutputStream(f);
				ObjectOutputStream dataOutputStream = new ObjectOutputStream(outputStream);
	        	
	        	for(int i=0; i<p.size(); i++) {
	        		dataOutputStream.writeObject(p.get(i));
	        	}
	        	dataOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	
	// Copy Method ------------------------------------------------
	public static void copy(File f1, int id, int quantity) {
		ArrayList<Product> products = new ArrayList<>();
		
		try {
			FileInputStream inputStream = new FileInputStream(f1);
            ObjectInputStream dataInputStream = new ObjectInputStream(inputStream);
                                   
            Product product = null;
            while(inputStream.available() != 0) {
            	product = (Product) dataInputStream.readObject();
            	if(product.product_id == id) {
            		product.product_quantity = quantity;
            	}
            	products.add(product);
            }
            
            
            dataInputStream.close();
            inputStream.close();
            
		} catch(IOException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		
		writeData(f1, products, false);		
	}
	
	// Set Method -----------------------------------------
	public static void set(File f, int id, int quantity) {
		boolean id_found = false;
		
		
		try {
			if(f.exists() && f.length() != 0) {
				FileInputStream inputStream = new FileInputStream(f);
	            ObjectInputStream dataInputStream = new ObjectInputStream(inputStream);

	            Product product = null;
	            while(inputStream.available() != 0) {
	            	product = (Product) dataInputStream.readObject();
	            	if(product.product_id == id) {
	            		id_found = true;
	            	}
	            }
	            
	            dataInputStream.close();
	            inputStream.close();
			}

        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
		
		if(id_found) {
			System.out.println("ID Exists.");
			copy(f, id, quantity);
		} else {
			ArrayList<Product> products = new ArrayList<>();
			products.add(new Product(id, quantity));
			writeData(f, products, true);
		}
		
	}
	
	// List Method -----------------------------------
	public static void list(File f, int id) {
		boolean id_found = false;
		
		try {
            FileInputStream inputStream = new FileInputStream(f);
            ObjectInputStream dataInputStream = new ObjectInputStream(inputStream);

            Product product = null;
            while(inputStream.available() != 0) {
            	product = (Product) dataInputStream.readObject();
            	if(product.product_id == id) {
            		id_found = true;
            		System.out.println("Product found. Quantity : " + product.product_quantity);
            		break;
            	}
            }
            
            dataInputStream.close();
            inputStream.close();

        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
		
		if(!id_found) {
			System.out.println("Product not found.");
		}
	}
	
	
	// Export Method --------------------------------------------
	public static void export(File f1, File f2) {
		
		ArrayList<Product> products = new ArrayList<>();
		
		try {
			FileInputStream inputStream = new FileInputStream(f1);
            ObjectInputStream dataInputStream = new ObjectInputStream(inputStream);
                                   
            while(inputStream.available() != 0) {
            	products.add((Product) dataInputStream.readObject());
            }
            
            dataInputStream.close();
            inputStream.close();
            
		} catch(IOException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		
		writeData(f2, products, false);
	}
	
	
	// Import Method ---------------------------------------
	public static void importFile(File f) {
		readData(f);
	}
	


	// Main method -----------------------------------------------
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("Product_Stock.txt");
		Scanner input = new Scanner(System.in);
		
		try {
			if (file.createNewFile()) {
			    System.out.println("File created: " + file.getName());
			} 
			if (!file.createNewFile()){
				
				
				while(true) {
					
					System.out.println("\nFor import press 1 : ");
					System.out.println("For export press 2 : ");
					System.out.println("For set    press 3 : ");
					System.out.println("For list   press 4 : ");
					System.out.println("For exit   press 5 : \n");
					
					String c = input.nextLine();
					
					switch(c) {
						// Import Functionality
						case "1":
							// Import prompt
							System.out.println("Enter the file path : (C:\\path\\to\\file) ");
							
							// Import input
							String file_path = input.nextLine();
							
							// File
							File import_file = new File(file_path);
							
							if(import_file.exists() && import_file.length() != 0) {
								importFile(import_file);
							} else {
								System.out.println("File doesn't exist or is empty.");
							}
							
							
							break;
						// Export Functionality
						case "2":
							// Export prompt
							System.out.println("Enter the first file path  : (C:\\path\\to\\file) ");
							System.out.println("Enter the second file path : (C:\\path\\to\\file) ");
							
							// Export input
							String first_filepath = input.nextLine();
							File first_file = new File(first_filepath);
							
							String second_filepath = input.nextLine();
							File second_file = new File(second_filepath);
							
							// Check if file exists and is not empty
							if(first_file.exists() && first_file.length() != 0) {
								export(first_file, second_file);
							} else {
								System.out.println("Something went wrong. Please try again.");
							}
							
							break;
						// Set Functionality
						case "3":
							// Set prompt
							System.out.println("Enter the product id 	   : ");
							System.out.println("Enter the product quantity : ");
							System.out.println("Enter the file path : (C:\\path\\to\\file) ");
							
							// Set input
							int set_id = Integer.parseInt(input.nextLine());
							int quantity = Integer.parseInt(input.nextLine());
							String set_file_path = input.nextLine();
							
							// File 
							File set_file = new File(set_file_path);
							
							
							set(set_file, set_id, quantity);
							break;
						// List Functionality
						case "4":
							// List prompt
							System.out.println("Enter the product id : ");
							System.out.println("Enter the file path : (C:\\path\\to\\file) ");
							
							// List input
							int list_id = Integer.parseInt(input.nextLine());
							String list_file_path = input.nextLine();
							
							// File 
							File list_file = new File(list_file_path);
							
							// Check if file 
							if(list_file.exists() && list_file.length() != 0) {
								list(list_file, list_id);
							} else {
								System.out.println("File is empty.");
							}
							
							break;
						// Exit Functionality
						case "5":
							System.out.println("Exit.");
							System.exit(1);
					}
				}
			}
			input.close();
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
