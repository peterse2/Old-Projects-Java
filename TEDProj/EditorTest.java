package package1;

import static org.junit.Assert.*;
import org.junit.Test;

/***********************************************************************
 * Contains unit tests to test the Editor class.
 * @author Emily Peterson
 * @version 11/5/2014
 **********************************************************************/
public class EditorTest {

	@Test
	public void testInsertCommand() throws EditorException {
		Editor ed = new Editor();
		ed.processCommand("i Java is an OO language.");
		ed.processCommand("i Ruby is another OO language.");
		assertEquals("Ruby is another OO language.", ed.getCurrentLine());
		assertEquals("Java is an OO language.", ed.getLine(0));
	}

	@Test 
	public void testRemoveCommand()throws EditorException {
	   Editor ed = new Editor();
	   ed.processCommand("i Java is cool.");
	   ed.processCommand("i Python is cooler.");
	   ed.processCommand("i Ruby is hot.");
	   ed.processCommand("i COBOL is old.");
	   assertEquals(4, ed.nbrLines());
	   ed.processCommand("u 2");
	   ed.processCommand("r 2");
	   assertEquals(2, ed.nbrLines());
	   assertEquals("COBOL is old.", ed.getCurrentLine());
	}

	@Test 
	public void testReverseCommand()throws EditorException {
	   Editor ed = new Editor();
	   ed.processCommand("i Java is cool.");
	   ed.processCommand("i Python is cooler.");
	   ed.processCommand("i Ruby is hot.");
	   ed.processCommand("i COBOL is old.");
	   assertEquals(4, ed.nbrLines());
	   ed.processCommand("rev");
	   assertEquals(4, ed.nbrLines());
	   assertEquals("COBOL is old.", ed.getLine(0));
	   assertEquals("Ruby is hot.", ed.getLine(1));
	   assertEquals("Python is cooler.", ed.getLine(2));
	   assertEquals("Java is cool.", ed.getLine(3));
	}

	@Test(expected=EditorException.class)
	public void testRemoveException() throws EditorException {
	   Editor ed = new Editor();
	   ed.processCommand("i Java is cool.");
	   ed.processCommand("i Python is cooler.");
	   ed.processCommand("i Ruby is hot.");
	   assertEquals(3, ed.nbrLines());
	   ed.processCommand("u 1");
	   ed.processCommand("r 3");
	}

	@Test
	public void testBeforeCommand() throws EditorException {
		Editor ed = new Editor();
		ed.processCommand("b one");
		ed.processCommand("b two");
		ed.processCommand("b three");
		ed.processCommand("b four");
		ed.processCommand("b five");
		assertEquals("five", ed.getCurrentLine());
		assertEquals("one", ed.getLine(4));
	}
	
	@Test
	public void testBefore2Command() throws EditorException {
		Editor ed = new Editor();
		ed.processCommand("b one");
		assertEquals("one", ed.getCurrentLine());
	}
	
	@Test
	public void testBefore3Command() throws EditorException {
		Editor ed = new Editor();
		ed.processCommand("b");
		assertEquals("", ed.getCurrentLine());
	}
		
	@Test
	public void testInsert2Command() throws EditorException {
		Editor ed = new Editor();
		ed.processCommand("i one");
		ed.processCommand("i two");
		ed.processCommand("i three");
		ed.processCommand("i four");
		ed.processCommand("i five");
		assertEquals("five", ed.getCurrentLine());
		assertEquals("one", ed.getLine(0));
	}
	
	@Test
	public void testInsert3Command() throws EditorException {
		Editor ed = new Editor();
		ed.processCommand("i one");
		assertEquals("one", ed.getCurrentLine());
	}
	
	@Test(expected=EditorException.class)
	public void testMoveCommand() throws EditorException {
		Editor ed = new Editor();
		ed.processCommand("i one");
		ed.processCommand("i two");
		ed.processCommand("i three");
		ed.processCommand("i four");
		ed.processCommand("i five");
		ed.processCommand("m");
	}
	
	@Test
	public void testDownCommand() throws EditorException {
		Editor ed = new Editor();
		ed.processCommand("i one");
		ed.processCommand("i two");
		ed.processCommand("i three");
		ed.processCommand("i four");
		ed.processCommand("b five");
		ed.processCommand("m");
		assertEquals("four", ed.getCurrentLine());
		assertEquals("five", ed.getLine(3));
	}
	
	@Test (expected=EditorException.class)
	public void testDownCommand2() throws EditorException {
		Editor ed = new Editor();
		ed.processCommand("m");
	}
	
	@Test (expected=EditorException.class)
	public void testDownCommand3() throws EditorException {
		Editor ed = new Editor();
		ed.processCommand("i one");
		ed.processCommand("i two");
		ed.processCommand("i three");
		ed.processCommand("i four");
		ed.processCommand("b five");
		ed.processCommand("m 2");
	}
	
	@Test
	public void testDownCommand4() throws EditorException {
		Editor ed = new Editor();
		ed.processCommand("i one3");
		ed.processCommand("i two");
		ed.processCommand("i three");
		ed.processCommand("b four");
		ed.processCommand("b five");
		ed.processCommand("m 2");
		assertEquals("three", ed.getCurrentLine());
	}
	
	@Test
	public void testUpCommand() throws EditorException {
		Editor ed = new Editor();
		ed.processCommand("i one");
		ed.processCommand("i two");
		ed.processCommand("i three");
		ed.processCommand("i four");
		ed.processCommand("i five");
		ed.processCommand("u");
		assertEquals("four", ed.getCurrentLine());
	}
	
	@Test (expected=EditorException.class)
	public void testUpCommand2() throws EditorException {
		Editor ed = new Editor();
		ed.processCommand("i one");
		ed.processCommand("u");
	}
	
	@Test (expected=EditorException.class)
	public void testUpCommand3() throws EditorException {
		Editor ed = new Editor();
		ed.processCommand("i one");
		ed.processCommand("i two");
		ed.processCommand("i three");
		ed.processCommand("i four");
		ed.processCommand("i five");
		ed.processCommand("u 6");
	}
	
	@Test
	public void testUpCommand4() throws EditorException {
		Editor ed = new Editor();
		ed.processCommand("i one");
		ed.processCommand("i two");
		ed.processCommand("i three");
		ed.processCommand("i four");
		ed.processCommand("i five");
		ed.processCommand("u 3");
		assertEquals("two", ed.getCurrentLine());
	}
	
	@Test
	public void testRemoveEndCommand() throws EditorException {
		Editor ed = new Editor();
		ed.processCommand("i one");
		ed.processCommand("i two");
		ed.processCommand("i three");
		ed.processCommand("i four");
		ed.processCommand("i five");
		ed.processCommand("r");
		assertEquals("four", ed.getCurrentLine());
		assertEquals(4, ed.nbrLines());
	}
	
	@Test (expected=EditorException.class)
	public void testRemoveCommand2() throws EditorException {
		Editor ed = new Editor();
		ed.processCommand("r");
	}
	
	@Test
	public void testRemoveCommand3() throws EditorException {
		Editor ed = new Editor();
		ed.processCommand("i one");
		ed.processCommand("r");
		assertEquals(0, ed.nbrLines());
	}
	
	@Test
	public void testRemoveCommand4() throws EditorException {
		Editor ed = new Editor();
		ed.processCommand("i one");
		ed.processCommand("i two");
		ed.processCommand("i three");
		ed.processCommand("d");
		ed.processCommand("b four");
		ed.processCommand("b five");
		ed.processCommand("d");
		ed.processCommand("r 2");
		ed.processCommand("d");
		assertEquals("three", ed.getCurrentLine());
		assertEquals(3, ed.nbrLines());
	}
	
	@Test
	public void testCYesCommand() throws EditorException {
		Editor ed = new Editor();
		ed.processCommand("i one");
		ed.processCommand("i two");
		ed.processCommand("i three");
		ed.processCommand("i four");
		ed.processCommand("i five");
		ed.processCommand("c");
		assertEquals(0, ed.nbrLines()); // assumes yes was pushed
	}
	
	@Test
	public void testCNoCommand() throws EditorException {
		Editor ed = new Editor();
		ed.processCommand("i one");
		ed.processCommand("i two");
		ed.processCommand("i three");
		ed.processCommand("i four");
		ed.processCommand("i five");
		ed.processCommand("c");
		assertEquals(5, ed.nbrLines()); // assumes ok was pushed
	}
	
	@Test
	public void testCCancelCommand() throws EditorException {
		Editor ed = new Editor();
		ed.processCommand("i one");
		ed.processCommand("i two");
		ed.processCommand("i three");
		ed.processCommand("i four");
		ed.processCommand("i five");
		ed.processCommand("c");
		assertEquals(5, ed.nbrLines()); // assumes cancled was pushed
	}
	
	@Test
	public void testCCancelOK() throws EditorException {
		Editor ed = new Editor();
		ed.processCommand("c");
		assertEquals(0, ed.nbrLines()); // assumes ok was pushed
	}
	
	@Test
	public void testLoadSave() throws EditorException {
		Editor ed = new Editor();
		ed.processCommand("i one");
		ed.processCommand("i two");
		ed.processCommand("i three");
		ed.processCommand("i four");
		ed.processCommand("i five");
		assertEquals(5, ed.nbrLines());
		ed.processCommand("s hi");
		ed.processCommand("c");
		assertEquals(0, ed.nbrLines());
		ed.processCommand("l hi");
		assertEquals(5, ed.nbrLines()); // assumes ok was pushed
	}
	
	@Test
	public void testHelpCommand() throws EditorException {
		Editor ed = new Editor();
		ed.processCommand("h");
	}
	
	@Test
	public void testHelpCommand2() throws EditorException {
		Editor ed = new Editor();
		ed.processCommand("h 5");
	}
	
	@Test
	public void testXCommand() throws EditorException {
		Editor ed = new Editor();
		ed.processCommand("x");
	}
	
	@Test
	public void testXCommand2() throws EditorException {
		Editor ed = new Editor();
		ed.processCommand("x 4");
	}
	
	@Test
	public void testECommand() throws EditorException {
		Editor ed = new Editor();
		ed.processCommand("i one");
		ed.processCommand("i two");
		ed.processCommand("i three");
		ed.processCommand("i four");
		ed.processCommand("i five");
		ed.processCommand("e six");
		assertEquals("six", ed.getLine(5));
		assertEquals("six", ed.getCurrentLine());
		assertEquals(6, ed.nbrLines()); 
	}
	
	@Test
	public void testECommand2() throws EditorException {
		Editor ed = new Editor();
		ed.processCommand("i one");
		ed.processCommand("i two");
		ed.processCommand("i three");
		ed.processCommand("i four");
		ed.processCommand("b five");
		ed.processCommand("e six");
		assertEquals("six", ed.getLine(5));
		assertEquals("six", ed.getCurrentLine());
		assertEquals(6, ed.nbrLines()); 
	}
	
	@Test
	public void testECommand3() throws EditorException {
		Editor ed = new Editor();
		ed.processCommand("e six");
		assertEquals("six", ed.getLine(0));
		assertEquals("six", ed.getCurrentLine());
		assertEquals(1, ed.nbrLines()); 
	}
	
	@Test
	public void testCutCommand() throws EditorException {
		Editor ed = new Editor();
		ed.processCommand("i one");
		ed.processCommand("i two");
		ed.processCommand("i three");
		ed.processCommand("i four");
		ed.processCommand("i five");
		ed.processCommand("d");
		ed.processCommand("cut 2 3");
		ed.processCommand("d");
		assertEquals("five", ed.getCurrentLine());
		assertEquals(3, ed.nbrLines());
		assertEquals("four", ed.getLine(1));
	}
	
	@Test (expected=EditorException.class)
	public void testCutCommand2() throws EditorException {
		Editor ed = new Editor();
		ed.processCommand("cut 1 1");
	}
	
	@Test (expected=EditorException.class)
	public void testCutCommand3() throws EditorException {
		Editor ed = new Editor();
		ed.processCommand("i one");
		ed.processCommand("i two");
		ed.processCommand("i three");
		ed.processCommand("i four");
		ed.processCommand("i five");
		ed.processCommand("cut 2 10");
	}
	
	@Test
	public void testCutCommand4() throws EditorException {
		Editor ed = new Editor();
		ed.processCommand("i one");
		ed.processCommand("cut 1 1");
		assertEquals(0, ed.nbrLines());
	}
	
	@Test
	public void testPasteCommand() throws EditorException {
		Editor ed = new Editor();
		ed.processCommand("i one");
		ed.processCommand("i two");
		ed.processCommand("i three");
		ed.processCommand("i four");
		ed.processCommand("i five");
		ed.processCommand("cut 2 3");
		ed.processCommand("d");
		assertEquals("five", ed.getCurrentLine());
		assertEquals(3, ed.nbrLines()); 
		assertEquals("one", ed.getLine(0));
		ed.processCommand("pas");
		ed.processCommand("d");
		assertEquals("two", ed.getCurrentLine());
		assertEquals(5, ed.nbrLines());
	}
}