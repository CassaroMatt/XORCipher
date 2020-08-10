import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CodeBreaker {
	Encryption e = new Encryption();
	
	/**
	 * Goes through auto generated three character password and performs the XOR of that to an integer array,
	 * It will continuously do this until it finds the highest score containing the most english characters or numbers.
	 * @return - the sum of the ascii values in the original text
	 * @throws IOException
	 */
	public String decryptWithoutPassword(String[] euler) throws IOException {

	System.out.print("Input array: ");
	for(int i=0; i<euler.length; i++) {
		System.out.print(euler[i]+" ");
	}
	
	char[] bestKey = null;
	char[] bestDecrypted = null;
	double bestScore = Double.NaN;
	
	//loop through all combinations of the alphabet (a-z)^3,
	//and check that key against possible decrypted outcomes to determine the most likely one.
		for (char x = 'a'; x <= 'z'; x++) {
			for (char y = 'a'; y <= 'z'; y++) {
				for (char z = 'a'; z <= 'z'; z++) {					
					char[] key = {x, y, z};
					char[] decrypted = e.decryptStringArray(euler, key);
					//append the best decrypted result to the highest score
					double score = validKey(decrypted);
					if (bestKey == null || score > bestScore) {		
						bestKey = key;					
						bestDecrypted = decrypted;
						bestScore = score;

					}
				}
			}
			
		}
		System.out.println();
		System.out.print("Decrypted message: ");
		System.out.println(bestDecrypted);
		System.out.print("Key found: ");
		System.out.println(bestKey);
		int sum = 0;
		for (int i = 0; i < bestDecrypted.length; i++) {
			sum += bestDecrypted[i];		
		}
		System.out.println("Sum of original values: " +sum);
		return Integer.toString(sum);
	}
	
	/**
	 * Checks for printable ascii characters and prioritizes those characters from letters, to digits, to other symbols
	 * returns the highest sum containing those printable characters
	 * @param b
	 * @return
	 */
	private int validKey(char[] b) {
		int sum = 0;
		for (int i = 0; i < b.length; i++) {
			char c = (char)b[i];

			//if character is printable
			if(c >= 32 || c <= 127) {
				sum++;
				//if we come across letters
				if(c >= 'A' && c <= 'Z' || c >= 'a' && c <='z') {
					sum+=5;
				}
			}
			//otherwise character is nonprintable
			else {
				sum--;
			}
		}
		
		return sum;
	}
}
