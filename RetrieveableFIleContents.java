import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public interface RetrieveableFIleContents {
	/**
	 * Reads the contents of a .txt file inputed and returns it as a string
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public String getContentsTXT(String file) throws FileNotFoundException, IOException;
	
	/**
	 * Gets the contents of a .csv file and returns it as a String array
	 * @param file
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public String[] getContentsCSV(String file) throws FileNotFoundException, IOException;
}
