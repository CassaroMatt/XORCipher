import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GetFileContents implements RetrieveableFIleContents {

	/**
	 * Returns the file contents of a .txt file and converts it to a string
	 */
	public String getContentsTXT(String file) throws IOException {

		  String st = ""; 
		  String output="";
		  try {			  
			  BufferedReader br = new BufferedReader(new FileReader(file)); 
			  if(file.contains(".txt")) {
				  while ((st = br.readLine()) != null) {
					  System.out.println("Contents: "+st);	
					  output+=st;
				  } 
			  }else if(!file.contains(".txt")) {
				  System.out.println("Please enter a .txt file");
				  System.exit(0);
			  }
		  }		  
		  catch(Exception ex) {
			  System.out.println("Please enter a valid file path.");			  
			  System.exit(0);

		  }	  
		  
		return output;	
	}

	
	/**
	 * Returns the contents of a .csv file and converts it to a sting array
	 */
	public String[] getContentsCSV(String file) throws FileNotFoundException, IOException {
		String[] values = new String[5000];
		String COMMA_DELIMITER =",";
		ArrayList<String[]> records = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			if(file.contains(".csv")) {
			    String line;
			    while ((line = br.readLine()) != null) {
			        values = line.split(COMMA_DELIMITER);
			        records.add(values);		        
			    }
			}else if(!file.contains(".csv")) {
				System.out.println("Please enter a .csv file");
				System.exit(0);
			}
		}catch(Exception ex) {
			  System.out.println("Please enter a valid file path.");			  
			  System.exit(0);  
	    }	  
		return values;
	}

}
