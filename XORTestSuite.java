import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class XORTestSuite {

	private static final short String = 0;
	Encryption e;
	CodeBreaker b;
	
	@BeforeEach
	void before() throws Exception {
		e = new Encryption();
		b = new CodeBreaker();
	}
	
	
	@Test 
	void testXORResultOfTwoCharacters(){
		
		assertEquals('&', e.performXOR('a', 'G'));
	}
	
	@Test 
	void testXORValueResultOfTwoCharacters(){
		
		assertEquals(38, e.performXOR('a', 'G'));
	}
	
	@Test
	void testXORSameCharacterInput() {
		assertEquals(0, e.performXOR('a','a'));
	}
	
	@Test 
	void testXORResultToNewString() {				
		assertEquals('G', e.performXOR('&', 'a'));
	}
	
	@Test
	void testXORCharInvalidOutput() {
		assertEquals(7, e.performXOR('6', '1'));	
	}
	
	@Test
	void testXOREmptyInput() {
		assertEquals('g', e.performXOR('G', ' '));
	}
	
	@Test
	void testXORBothSpaceInput() {
		assertEquals(0, e.performXOR(' ', ' '));
	}
	
	
	@Test
	void testXORResultOfStringsOfSameLength() {		
		assertEquals("'%'%?", e.performXORString("APRIL", "fuuls"));
	}
	
	@Test
	void testXORStringSpaceInput() {
		assertEquals("HI", e.performXORString("hi", "  "));
	}
	
	@Test
	void testXORStringBothEmptyInput() {
		assertEquals("", e.performXORString("", ""));
	}
	
	@Test
	void testXORSameStringInput() {
		char[] chars = {0,0,0,0,0};
		String result = new String(chars);
		assertEquals(result, e.performXORString("hello", "hello"));
	}
	
	@Test
	void testXORStringsResultNumberOutput() {
		assertNotEquals(23, e.performXORString("H1", "#d"));
	}
	
	@Test
	void testXORInvalidStringOutputSameLengthInput() {
		char[] chars = {15, 1, 0};
		String result = new String(chars);
		assertEquals(result, e.performXORString("944", "654"));
	}
	
	@Test
	void testXORInvalidStringOutputDiffLengthInput() {
		char[] chars = {15, 1, 0, 1, 13, 7};
		String result = new String(chars);
		assertEquals(result, e.performXORString(e.checkStringLength("944783", "654"), "944783"));
	}
	
	@Test
	void testResultStringLengthMultiple() {
		
		assertEquals("n5n5n5", e.checkStringLength("n5", "absegs"));		
		
	}
	
	
	//Trim one letter off
	@Test
	void testStringDiffLengthPlusOne() {		
		assertEquals("dadadadad", e.checkStringLength("da", "sdhfksade"));		
	}
		
	
	//Trim two letters off
	@Test
	void testStringDiffLengthPlusTwo() {
		assertEquals("dasdasd", e.checkStringLength("das", "getadss"));
	}
	

	@Test
	void testStringOfMultiple() {
		assertEquals("dadadada", e.checkStringLength("da", "asdhrezd"));
	}
	

	@Test
	void testStringOutputLargerThanInput() {		
		assertNotEquals("12345678910", e.checkStringLength("123456789", "da"));		
	}
	
	@Test
	void testStringOutputShorterThanInput() {		
		assertNotEquals("12345678", e.checkStringLength("da", "1234567"));		
	}
	
	@Test 
	void testXORStringInputLargerThanOutput() {
		assertNotEquals("QRUPWTWP", e.performXORString(e.checkStringLength("123465789", "da"), "53113531"));		
	}
	
	@Test
	void testXORStringInputShorterThanOutput() {
		try {
			assertNotEquals("QRUPWTWP", e.performXORString(e.checkStringLength("da", "123465"), "53113531"));	
		}
		catch (Exception ex) {
			assertEquals(ArrayIndexOutOfBoundsException.class, ex.getClass());
		}
	}

	
	
	@Test
	void testThreeCharacterKey() {
		assertEquals("PT PT PT PT PT",  e.performXORString(e.checkStringLength("16C", "abcabcabcabcab"), "abcabcabcabcab"));
	}
	
	
	@Test 
	void testDecryptionOfKeyAndMessage() {
		assertEquals("abcabcabcabcab",  e.performXORString(e.checkStringLength("16C", "              "), "PT PT PT PT PT"));
	}
	
	@Test
	void testKeyLongerThanPlaintextAndNotMultiple() {
		assertEquals("P@W",  e.performXORString(e.trimKeyLength("asdhd23yhskjd", "133"), "133"));
	}
	
	
	@Test 
	void testKeyLongerThanPlaintextAndMultiple() {
		assertEquals("%;V",  e.performXORString(e.trimKeyLength("asdhd23yhskj", "DH2"), "DH2"));
	}
	
	
	@Test
	void testResultDecryptionOfEncryption() {
		assertEquals("DH2", e.performXORString("asd", "%;V"));
	}
	
	
	
	@Test
	void testPlaintextFileEncryption() throws IOException { 
		/**
		 * Enter the file path to read the plaintext message
		 */
		File file = new File("D:\\JCUCS\\CS270\\plaintext_message.txt"); 

		assertEquals("PT PT PT PT PT",  e.performXORString(e.checkStringLength("16C", e.g.getContentsTXT(file.toString())), e.g.getContentsTXT(file.toString())));
		
	}
	
	@Test
	void testFileContentsNotEqualToOutput() throws IOException {
		File file = new File("D:\\JCUCS\\CS270\\plaintext_message.txt"); 

		assertNotEquals("PT PT PT PT",  e.performXORString(e.checkStringLength("16C", e.g.getContentsTXT(file.toString())), e.g.getContentsTXT(file.toString())));
	}
	
	
	//test file with nothing in it
	@Test
	void testEmptyFileContentTXT() throws IOException {
		
		try {
			new File("D:\\JCUCS\\CS270\\empty_plaintext.txt"); 
		}
		catch(Exception ex) {
			assertEquals(NullPointerException.class, ex.getClass());
		}
	}
	
	@Test
	void testNullFileInputTXT() throws IOException {		
		try {
			new File(""); 
		}
		catch (Exception ex) {
			assertEquals(FileNotFoundException.class, ex.getClass());
		}
	}
	
	
	@AfterEach
	void after() throws Exception {
		e = null;
		b = null;
	}
	

}
