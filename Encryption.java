import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Encryption {
	
	GetFileContents g = new GetFileContents();
	
	/**
	 * performs the XOR operation on two different ASCII characters and returns the
	 * result of the XOR operation
	 * @param a
	 * @param b
	 * @return
	 */
	public int performXOR(char a, char b) {
		int ascii1 = a;
		int ascii2 = b;

		return ascii1 ^ ascii2;
			
	}
	
	/**
	 * Performs the XOR operation on two different strings that are valid ASCII inputs
	 * and returns the result of the operation
	 * @param a - this is the plain text
	 * @param b - this is the key
	 * @return
	 */	
	public String performXORString(String a, String b) {
		
		try {
			if(a.length() > b.length()) {
				b = checkStringLength(a, b);
			}else if(b.length() > a.length()) {
				b = trimKeyLength(b, a);
			}
		}
		catch(NullPointerException ex){			
			System.out.println("File read no data, make sure file contents contains a plaintext message");
			System.exit(0);
		}
		
		
		char[] stringA = a.toCharArray();
		char[] stringB = b.toCharArray();
		
		int[] asciiA = new int[stringA.length];
		
		for(int i = 0; i < stringA.length; i++) {
			asciiA[i] = stringA[i];
		}
		
		int[] asciiB = new int[stringA.length];
		
		for(int i = 0; i < stringB.length; i++) {
			asciiB[i] = stringB[i];
		}
		
		int[] result = new int[stringA.length];
		for(int i = 0; i < result.length; i++) {
			result[i] = asciiA[i] ^ asciiB[i];
		}
		
		char[] resultChar = new char[result.length];
		for(int i=0; i<resultChar.length; i++) {
			resultChar[i] = (char) result[i];
			//System.out.println(result[i]);
		}
		
		String resultString = "";
		for(int i=0; i<resultChar.length; i++) {
			resultString += resultChar[i];
		}
		
		
		return resultString;
	}
	
	
	/**
	 * performs the XOR operation on a String array character key array
	 * @param ciphertext
	 * @param key
	 * @return - the XOR result of text and key
	 */
	char[] decryptStringArray(String[] ciphertext, char[] key) {
		char[] plaintext = new char[ciphertext.length];
		int[] euler = new int[ciphertext.length];
		for(int i=0; i<euler.length; i++) {
			euler[i] = Integer.parseInt(ciphertext[i]);
		}
		for (int i = 0; i < euler.length; i++) {
			plaintext[i] = (char) (euler[i] ^ key[i % key.length]);
		}
		return plaintext;
	} 
	
	
	
	/**
	 * Checks the string lengths of the two inputs and will repeat either
	 * string the exact amount of times to match the lengths. 
	 * @param a
	 * @param b
	 * @return
	 */
	public String checkStringLength(String a, String b) {
		
		int repeat = 0;
		int leftover = 0;
		String s = "";
		
		if(a.length() < b.length()) {
			if(!(b.length() % a.length() == 0)) {
				repeat = b.length() / a.length() + 1;			
				for(int i=0; i<repeat; i++) {
					s += a;
				}
				leftover = s.length() % b.length();
				s = s.substring(0, s.length()-(leftover));
			}
			else if(b.length() % a.length() == 0) {
				repeat = b.length()/a.length();
				for(int i = 0; i < repeat; i++) {
					s += a;
				}
			}
		}else if(b.length() < a.length()) {
			if(!(a.length() % b.length() == 0)) {
				repeat = a.length() / b.length() + 1;				
				for(int i=0; i<repeat; i++) {
					s += b;
				}
				leftover = s.length() % a.length();
				s = s.substring(0, s.length()-(leftover));
			}
			else if(a.length() % b.length() == 0) {
				repeat = a.length()/b.length();
				for(int i = 0; i < repeat; i++) {
					s += b;
				}
			}
		}
		return s;
	}
	
	/**
	 * Trims the length of the key to match the length of the plaintext message
	 * @param a - Plaintext message
	 * @param b - Key
	 * @return
	 */
	public String trimKeyLength(String a, String b) {
		String result = "";
		
		if(a.length() > b.length()) {
			result = a.substring(0, b.length());
		}
		else if(b.length() > a.length()) {
			result = b.substring(0, a.length());
		}
		System.out.println("Key has been trimmed to: "+result);
		return result;
	}

}
