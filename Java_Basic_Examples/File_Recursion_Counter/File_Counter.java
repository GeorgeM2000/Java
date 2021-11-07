import java.io.File;
import java.util.Scanner;

public class File_Counter {
	
	// Count Files 
	public static int countFiles(String file_path) {
		File directory = new File(file_path);
		File[] files = directory.listFiles();
		int count = 0;
		for(File filename: files) {
			if(filename.isDirectory()) {
				count += countFiles(filename.getAbsolutePath());
			} else {
				count += 1;
			}
		}
		return count;
	}

	// Main method
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the file path : (C:\\path\\to\\file) ");
		String path = input.nextLine();
		input.close();
		System.out.println("Files : " + countFiles(path));
		
	}

}
