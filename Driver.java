import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Driver {

	public static void main(String[] args) throws IOException {
		Encryption e = new Encryption();
		CodeBreaker b = new CodeBreaker();
		
		
		
		Scanner s = new Scanner(System.in);
		String response = "";
		String plainText ="";
		String key = "";
		String result="";
		String fileInput = "";
		String clipboard="";
		
		
	
		
		while(true) {
			System.out.println(">Action?");	
			System.out.println("1: Encrypt");
			System.out.println("2: Decrypt");
			System.out.println("3: Exit");

			response = s.nextLine();
			
			if(response.equals("Encrypt") || response.equals("encrypt") || response.equals("1")) {
				System.out.println("Do you want to enter a 1: plaintext or 2: file input?");
				
				response = s.nextLine();
			
				if(response.equals("plaintext") || response.equals("1")) {
					System.out.println("Enter plaintext");
				
					plainText = s.nextLine();

					System.out.println("Enter a key");
					
					try {
						key = s.nextLine();												
						result = e.performXORString(plainText, key);
						System.out.println("Here is the encrptyed result: "+result);				
					}catch(Exception ex) {
						System.out.println("Invalid Key: "+ex.getMessage()+". please enter a valid key.");

					}
					
				}
				
				else if(response.equals("file input") || response.equals("2")) {
					System.out.println("Would you like to enter a 1: .txt file or 2: .csv file and have us decrypt it?");
					response = s.nextLine();
					if(response.equals("1") || response.equals(".txt file")) {
						
						System.out.println("Enter .txt file path");					
						
						fileInput = s.nextLine();				
						File file = new File(fileInput);
						
						plainText = e.g.getContentsTXT(file.toString());
						
						System.out.println("Enter a key");				
						key = s.nextLine();					
						result = e.performXORString(plainText, key);
						System.out.println("Here is the encrptyed result: "+result);
						
					}
					else if(response.equals("2") || response.equals(".csv file")) {
						
						System.out.println("Enter .csv file path");					
						fileInput = s.nextLine();	
						String[] euler = e.g.getContentsCSV(fileInput); 
						b.decryptWithoutPassword(euler);
						
					}				
				}
			
			}
			
			else if(response.equals("Decrypt") || response.equals("decrypt") || response.equals("2")) {
				System.out.println(">Use clipboard string?");
				
				clipboard = s.nextLine();
				if(clipboard.equals("y") || clipboard.equals("yes") || clipboard.equals("Yes")) {
					System.out.println("Enter a key");
					try {
						key = s.nextLine();				
						result = e.performXORString(result, key);
						System.out.println("Here is the decrypted result: "+result);
					}catch(Exception ex) {
						System.out.println("Invalid Key: "+ex.getMessage()+". please enter a valid key.");

					}
				}
				
				
				else if (response.equals("n") || clipboard.equals("no") || clipboard.equals("No")) {
					System.out.println("Enter plaintext");
					
					plainText = s.nextLine();
					System.out.println("Enter a key");
					try {
						key = s.nextLine();				
						result = e.performXORString(plainText, key);
						System.out.println("Here is the encrptyed result: " +result);
					}catch(Exception ex) {
						System.out.println("Invalid Key: "+ex.getMessage()+". please enter a valid key.");
					}
				}	
				
			}
			
			
			
			else if(response.equals("q") || response.equals("e") || response.equals("3")) {
				System.out.println("Exiting...");
				break;
			}
		}
		
	}

}
