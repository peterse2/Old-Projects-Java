package package1;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Emily Peterson
 *@version 9/08/2014
 */	
public class TestStopWatch {


	@Test

	public void testConstructor() {
		StopWatch s = new StopWatch (5,10,300);
		assertEquals(s.toString(),"5:10:300");

		s = new StopWatch("20:10:8");
		assertEquals(s.toString(),"20:10:008");

		s = new StopWatch("20:8");
		assertEquals(s.toString(),"0:20:008");

		s = new StopWatch("8");
		assertEquals(s.toString(),"0:00:008");

	}

	@Test
	public void testAddMethod () {
		StopWatch s1 = new StopWatch (5,59,300);
		s1.add(2000);
		assertEquals (s1.toString(),"6:01:300");

		s1 = new StopWatch (5,59,300);
		StopWatch s2 = new StopWatch (2,2,300);
		s1.add(s2);
		System.out.println (s1);
		assertEquals (s1.toString(),"8:01:600");

		for (int i = 0; i < 15000; i++)
			s1.inc();
		System.out.println (s1);
		assertEquals (s1.toString(),"8:16:600");
	}


	@Test (expected = IllegalArgumentException.class)
	public void testContuctor() {
		new StopWatch("2,-3,-3");
	}

	@Test 
	public void testEqual () {
		StopWatch s1 = new StopWatch (5,59,300);
		StopWatch s2 = new StopWatch (6,01,200);
		StopWatch s3 = new StopWatch (5,50,200);
		StopWatch s4 = new StopWatch (5,59,300);

		assertFalse(s1.equals(s2));
		assertTrue (s1.equals(s4));

		assertTrue (s2.compareTo(s1) > 0);
		assertTrue (s3.compareTo(s1) < 0);
		assertTrue (s1.compareTo(s4) == 0);

	}
	@Test 
	public void testCompareTo () {
		StopWatch s1 = new StopWatch (5,59,300);
		StopWatch s2 = new StopWatch (6,01,200);
		StopWatch s3 = new StopWatch (5,50,200);
		StopWatch s4 = new StopWatch (5,59,300);

		assertFalse(s1.equals(s2));
		assertTrue (s1.equals(s4));

		assertTrue (s2.compareTo(s1) > 0);
		assertTrue (s3.compareTo(s1) < 0);
		assertTrue (s1.compareTo(s4) == 0);

	}

	@Test 
	public void testLoadSave () {
		StopWatch s1 = new StopWatch (5,59,300);

		s1.save("file1");
		s1 = new StopWatch ();

	}
	
	@Test 
	public void testLoadSave2 () {
		StopWatch s1 = new StopWatch (5,59,300);

		s1.save("file1");
		s1 = new StopWatch ();
		s1.load("file1");
	}

	@Test 
	public void testMutate () {
		StopWatch s1 = new StopWatch (5,59,300);
		StopWatch s2 = new StopWatch (5,59,300);

		StopWatch.setMutate(false);
		s1.add(1000);
		assertTrue (s1.equals(s2));	
	}





	//Tests the Default Constructor
	@Test
	public void testDCMin () {
		StopWatch s = new StopWatch();
		assertEquals(s.toString(),"0:00:000");
	}


	@Test
	public void testDCNegMin () {
		StopWatch s = new StopWatch();
		assertFalse(s.getM() == -1);
	}@Test
	public void testDCNegSec () {
		StopWatch s = new StopWatch();
		assertFalse(s.getS() == -1);
	}@Test
	public void testDCNegMill () {
		StopWatch s = new StopWatch();
		assertFalse(s.getMl() == -1);
	}


	//Testing the three parameter constructor
	@Test
	public void testThreeCMin () {
		StopWatch s = new StopWatch(0, 00, 000);
		assertEquals(s.toString(),"0:00:000");
	}


	@Test 
	public void testThreeCZeros (){
		StopWatch s = new StopWatch(0, 00, 000);
		StopWatch s1 = new StopWatch("0:00:000");
		assertTrue(s.equals(s1));	
	}@Test 
	public void testThreeCZeros2 (){
		StopWatch s = new StopWatch(00, 00, 000);
		StopWatch s1 = new StopWatch("0:00:000");
		assertTrue(s.equals(s1));
	}@Test 
	public void testThreeCZeros3 (){
		StopWatch s = new StopWatch(0, 0, 000);
		StopWatch s1 = new StopWatch("0:00:000");
		assertTrue(s.equals(s1));
	}@Test 
	public void testThreeCZeros4 (){
		StopWatch s = new StopWatch(0, 00, 00);
		StopWatch s1 = new StopWatch("0:00:000");
		assertTrue(s.equals(s1));
	}@Test 
	public void testThreeCZeros5 (){
		StopWatch s = new StopWatch(0, 00, 0);
		StopWatch s1 = new StopWatch("0:00:000");
		assertTrue(s.equals(s1));
	}


	@Test
	public void testThreeCPlacementMin (){
		StopWatch s = new StopWatch(5, 00, 000);
		StopWatch s1 = new StopWatch("5:00:000");
		assertTrue(s.equals(s1));
	}@Test 
	public void testThreeCPlacementSec (){
		StopWatch s = new StopWatch(0, 5, 000);
		StopWatch s1 = new StopWatch("0:05:000");
		assertTrue(s.equals(s1));
	}@Test 
	public void testStringPlacement (){
		StopWatch s = new StopWatch(0, 50, 000);
		StopWatch s1 = new StopWatch("0:50:000");
		assertTrue(s.equals(s1));	
	}@Test 
	public void testThreeCPlacementMill (){
		StopWatch s = new StopWatch(0, 0, 5);
		StopWatch s1 = new StopWatch("0:00:005");
		assertTrue(s.equals(s1));
	}@Test 
	public void testStringPlacement2 (){
		StopWatch s = new StopWatch(0, 0, 50);
		StopWatch s1 = new StopWatch("0:00:050");
		assertTrue(s.equals(s1));
	}@Test 
	public void testStringPlacement3 (){
		StopWatch s = new StopWatch(0, 0, 500);
		StopWatch s1 = new StopWatch("0:00:500");
		assertTrue(s.equals(s1));
	}


	@Test 
	public void testThreeCBorder1 (){
		StopWatch s = new StopWatch(0, 00, 999);
		StopWatch s1 = new StopWatch("0:00:999");
		assertTrue(s.equals(s1));
	}@Test 
	public void testThreeCBorder2 (){
		StopWatch s = new StopWatch(0, 59, 000);
		StopWatch s1 = new StopWatch("0:59:000");
		assertTrue(s.equals(s1));
	}@Test (expected = IndexOutOfBoundsException.class) 
	public void testThreeCBorder3 (){
		new StopWatch(0, 0, 1000);
	}@Test (expected = IndexOutOfBoundsException.class)
	public void testThreeCBorder4 (){
		new StopWatch(0, 60, 000);
	}


	@Test (expected = IndexOutOfBoundsException.class)
	public void testThreeCNeg () {
		new StopWatch(0,00,-2);
	}@Test (expected = IndexOutOfBoundsException.class)
	public void testThreeCNeg2 () {
		new StopWatch(0,00,-20);
	}@Test (expected = IndexOutOfBoundsException.class)
	public void testThreeCNeg3 () {
		new StopWatch(0,00,-200);
	}@Test (expected = IndexOutOfBoundsException.class)
	public void testThreeCNeg4 () {
		new StopWatch(0,-5,000);
	}@Test (expected = IndexOutOfBoundsException.class)
	public void testThreeCNeg5 () {
		new StopWatch(0,-50,000);
	}@Test (expected = IndexOutOfBoundsException.class)
	public void testThreeCNeg6 () {
		new StopWatch(-5,00,000);
	}@Test (expected = IndexOutOfBoundsException.class)
	public void testThreeCNeg7 () {
		new StopWatch(-50,00,000);
	}@Test (expected = IndexOutOfBoundsException.class)
	public void testThreeCNeg8 () {
		new StopWatch(0,-59,000);
	}@Test (expected = IndexOutOfBoundsException.class)
	public void testThreeCNeg9 () {
		new StopWatch(0,-60,000);
	}@Test (expected = IndexOutOfBoundsException.class)
	public void testThreeCNeg10 () {
		new StopWatch(0,00,-999);
	}@Test (expected = IndexOutOfBoundsException.class)
	public void testThreeCNeg11 () {
		new StopWatch(0,00,-1000);
	}


	//Testing the two parameter constructor
	@Test
	public void testTwo () {
		StopWatch s = new StopWatch(00, 000);
		assertTrue(s.getS() == 0);
		assertTrue(s.getMl() == 0);
	}


	@Test 
	public void testTwoCZeros (){
		StopWatch s = new StopWatch(0, 000);
		StopWatch s1 = new StopWatch("0:00:000");
		assertTrue(s.equals(s1));
	}@Test 
	public void testTwoCZeros2 (){
		StopWatch s = new StopWatch(00, 000);
		StopWatch s1 = new StopWatch("0:00:000");
		assertTrue(s.equals(s1));
	}@Test 
	public void testTwoCZeros3 (){
		StopWatch s = new StopWatch(00, 00);
		StopWatch s1 = new StopWatch("0:00:000");
		assertTrue(s.equals(s1));
	}@Test 
	public void testTwoCZeros4 (){
		StopWatch s = new StopWatch(00, 0);
		StopWatch s1 = new StopWatch("0:00:000");
		assertTrue(s.equals(s1));
	}


	@Test 
	public void testTwoCPlacementSec (){
		StopWatch s = new StopWatch(0, 5, 000);
		StopWatch s1 = new StopWatch("0:05:000");
		assertTrue(s.equals(s1));
	}@Test 
	public void testStringPlacement4 (){
		StopWatch s = new StopWatch(50, 000);
		StopWatch s1 = new StopWatch("0:50:000");
		assertTrue(s.equals(s1));	
	}@Test 
	public void testTwoCPlacementMill (){
		StopWatch s = new StopWatch(0, 5);
		StopWatch s1 = new StopWatch("0:00:005");
		assertTrue(s.equals(s1));
	}@Test 
	public void testStringPlacement5 (){
		StopWatch s = new StopWatch(0, 50);
		StopWatch s1 = new StopWatch("0:00:050");
		assertTrue(s.equals(s1));
	}@Test 
	public void testStringPlacement6 (){
		StopWatch s = new StopWatch(0, 500);
		StopWatch s1 = new StopWatch("0:00:500");
		assertTrue(s.equals(s1));
	}


	@Test 
	public void testTwoCBorder1 (){
		StopWatch s = new StopWatch(00, 999);
		StopWatch s1 = new StopWatch("0:00:999");
		assertTrue(s.equals(s1));
	}@Test 
	public void testTwoCBorder2 (){
		StopWatch s = new StopWatch(59, 000);
		StopWatch s1 = new StopWatch("0:59:000");
		assertTrue(s.equals(s1));
	}@Test (expected = IndexOutOfBoundsException.class) 
	public void testTwoCBorder3 (){
		new StopWatch(0, 1000);
	}@Test (expected = IndexOutOfBoundsException.class)
	public void testTwoCBorder4 (){
		new StopWatch(60, 000);
	}

	@Test (expected = IndexOutOfBoundsException.class)
	public void testTwoCNeg () {
		new StopWatch(00,-2);
	}@Test (expected = IndexOutOfBoundsException.class)
	public void testTwoCNeg2 () {
		new StopWatch(00,-20);
	}@Test (expected = IndexOutOfBoundsException.class)
	public void testTwoCNeg3 () {
		new StopWatch(00,-200);
	}@Test (expected = IndexOutOfBoundsException.class)
	public void testTwoCNeg4 () {
		new StopWatch(-5,000);
	}@Test (expected = IndexOutOfBoundsException.class)
	public void testTwoCNeg5 () {
		new StopWatch(-50,000);
	}@Test (expected = IndexOutOfBoundsException.class)
	public void testTwoCNeg6 () {
		new StopWatch(-59,000);
	}@Test (expected = IndexOutOfBoundsException.class)
	public void testTwoCNeg7 () {
		new StopWatch(-60,000);
	}@Test (expected = IndexOutOfBoundsException.class)
	public void testTwoCNeg8 () {
		new StopWatch(00,-999);
	}@Test (expected = IndexOutOfBoundsException.class)
	public void testTwoCNeg9 () {
		new StopWatch(00,-1000);
	}


	//Testing the One parameter constructor
	@Test 
	public void testOneMill () {
		StopWatch s = new StopWatch(000);
		assertTrue(s.getMl() == 0);
	}

	@Test 
	public void testOneCZeros (){
		StopWatch s = new StopWatch(000);
		StopWatch s1 = new StopWatch("0:00:000");
		assertTrue(s.equals(s1));
	}@Test 
	public void testOneCZeros2 (){
		StopWatch s = new StopWatch(00, 00);
		StopWatch s1 = new StopWatch("0:00:000");
		assertTrue(s.equals(s1));
	}@Test 
	public void testOneCZeros3 (){
		StopWatch s = new StopWatch(00, 0);
		StopWatch s1 = new StopWatch("0:00:000");
		assertTrue(s.equals(s1));
	}


	@Test 
	public void testOneCPlacementMill (){
		StopWatch s = new StopWatch(5);
		StopWatch s1 = new StopWatch("0:00:005");
		assertTrue(s.equals(s1));
	}@Test 
	public void testStringPlacement8 (){
		StopWatch s = new StopWatch(50);
		StopWatch s1 = new StopWatch("0:00:050");
		assertTrue(s.equals(s1));
	}@Test 
	public void testStringPlacement9 (){
		StopWatch s = new StopWatch(500);
		StopWatch s1 = new StopWatch("0:00:500");
		assertTrue(s.equals(s1));
	}


	@Test 
	public void testOneCBorder (){
		StopWatch s = new StopWatch(999);
		StopWatch s1 = new StopWatch("0:00:999");
		assertTrue(s.equals(s1));
	}@Test (expected = IndexOutOfBoundsException.class) 
	public void testOneCBorder2 (){
		new StopWatch(1000);
	}


	@Test (expected = IndexOutOfBoundsException.class)
	public void testOneCNeg () {
		new StopWatch(-2);
	}@Test (expected = IndexOutOfBoundsException.class)
	public void testOneCNeg2 () {
		new StopWatch(-20);
	}@Test (expected = IndexOutOfBoundsException.class)
	public void testOneCNeg3 () {
		new StopWatch(-200);
	}@Test (expected = IndexOutOfBoundsException.class)
	public void testOneCNeg4 () {
		new StopWatch(-999);
	}@Test (expected = IndexOutOfBoundsException.class)
	public void testOneCNeg5 () {
		new StopWatch(-1000);
	}
	//	}@Test (expected = IndexOutOfBoundsException.class)
	//	public void testOneCNeg6 () {
	//		new StopWatch(-0);
	//	}


	//Testing the String constructor
	@Test 
	public void testStringC (){
		StopWatch s = new StopWatch("0:00:000");
		assertEquals(s.toString(),"0:00:000");
	}@Test
	public void testStringC2 (){
		StopWatch s = new StopWatch("00:000");
		assertEquals(s.toString(),"0:00:000");
	}@Test
	public void testStringC3 (){
		StopWatch s = new StopWatch("000");
		assertEquals(s.toString(),"0:00:000");
	}@Test 
	public void testStringC4 (){
		StopWatch s = new StopWatch("5:13:121");
		assertEquals(s.toString(),"5:13:121");
	}@Test 
	public void testStringC5 (){
		StopWatch s = new StopWatch("13:121");
		assertEquals(s.toString(),"0:13:121");
	}@Test 
	public void testStringC6 (){
		StopWatch s = new StopWatch("121");
		assertEquals(s.toString(),"0:00:121");
	}


	@Test(expected = NumberFormatException.class) 
	public void testStringCSplit (){
		new StopWatch("0,00,000");
	}


	@Test 
	public void testStringCPlacement (){
		StopWatch s = new StopWatch("5:00:000");
		assertEquals(s.toString(),"5:00:000");
	}@Test 
	public void testStringCPlacement2 (){
		StopWatch s = new StopWatch("0:50:000");
		assertEquals(s.toString(),"0:50:000");
	}@Test 
	public void testStringCPlacement3 (){
		StopWatch s = new StopWatch("0:00:500");
		assertEquals(s.toString(),"0:00:500");
	}@Test 
	public void testStringCPlacement4 (){
		StopWatch s = new StopWatch("50:000");
		assertEquals(s.toString(),"0:50:000");
	}@Test 
	public void testStringCPlacement5 (){
		StopWatch s = new StopWatch("00:500");
		assertEquals(s.toString(),"0:00:500");
	}@Test 
	public void testStringCPlacement6 (){
		StopWatch s = new StopWatch("500");
		assertEquals(s.toString(),"0:00:500");
	}


	@Test 
	public void testStringCZeros (){
		StopWatch s = new StopWatch("0:0:000");
		assertEquals(s.toString(),"0:00:000");
	}@Test 
	public void testStringCZeros2 (){
		StopWatch s = new StopWatch("0:00:00");
		assertEquals(s.toString(),"0:00:000");
	}@Test 
	public void testStringCZeros3 (){
		StopWatch s = new StopWatch("0:00:0");
		assertEquals(s.toString(),"0:00:000");
	}@Test 
	public void testStringCZeros4 (){
		StopWatch s = new StopWatch("0:000");
		assertEquals(s.toString(),"0:00:000");
	}@Test 
	public void testStringCZeros5 (){
		StopWatch s = new StopWatch("00:00");
		assertEquals(s.toString(),"0:00:000");
	}@Test 
	public void testStringCZeros6 (){
		StopWatch s = new StopWatch("00:0");
		assertEquals(s.toString(),"0:00:000");
	}@Test 
	public void testStringCZeros7 (){
		StopWatch s = new StopWatch("00");
		assertEquals(s.toString(),"0:00:000");
	}@Test 
	public void testStringCZeros8 (){
		StopWatch s = new StopWatch("0");
		assertEquals(s.toString(),"0:00:000");
	}@Test 
	public void testStringCZeros9 (){
		StopWatch s = new StopWatch("5:00");
		assertEquals(s.toString(),"0:05:000");
	}@Test 
	public void testStringCZeros10 (){
		StopWatch s = new StopWatch("00:5");
		assertEquals(s.toString(),"0:00:005");
	}@Test 
	public void testStringCZeros11 (){
		StopWatch s = new StopWatch("00:50");
		assertEquals(s.toString(),"0:00:050");
	}@Test 
	public void testStringCZeros12 (){
		StopWatch s = new StopWatch("00:500");
		assertEquals(s.toString(),"0:00:500");
	}@Test 
	public void testStringCZeros13 (){
		StopWatch s = new StopWatch("5:00");
		assertEquals(s.toString(),"0:05:000");
	}@Test 
	public void testStringCZeros14 (){
		StopWatch s = new StopWatch("00:5");
		assertEquals(s.toString(),"0:00:005");
	}@Test 
	public void testStringCZeros15 (){
		StopWatch s = new StopWatch("00:50");
		assertEquals(s.toString(),"0:00:050");
	}@Test 
	public void testStringCZeros16 (){
		StopWatch s = new StopWatch("00:500");
		assertEquals(s.toString(),"0:00:500");
	}@Test 
	public void testStringCZeros17 (){
		StopWatch s = new StopWatch("5");
		assertEquals(s.toString(),"0:00:005");
	}@Test 
	public void testStringCZeros18 (){
		StopWatch s = new StopWatch("50");
		assertEquals(s.toString(),"0:00:050");
	}@Test 
	public void testStringCZeros19 (){
		StopWatch s = new StopWatch("500");
		assertEquals(s.toString(),"0:00:500");
	}

	//	
	//	@Test (expected = IllegalArgumentException.class)
	//	public void testStringCNeg (){
	//		new StopWatch("-1:00:000");
	//	}@Test (expected = IndexOutOfBoundsException.class)
	//	public void testStringCNeg2 (){
	//		new StopWatch("0:-10:000");
	//	}@Test (expected = IndexOutOfBoundsException.class)
	//	public void testStringCNeg3 (){
	//		new StopWatch("0:00:-100");
	//	}
	//	

	@Test(expected = NumberFormatException.class) 
	public void testStringCSpaces(){
		new StopWatch(" 0 : 0 0 : 0 0 0 ");
	}


	@Test(expected = NumberFormatException.class)
	public void testStringCNull(){
		new StopWatch("");
	}


	//Tests Getter Methods
	@Test 
	public void testGetter(){
		StopWatch s = new StopWatch(3,55,600);
		assertEquals(s.getM(),3);
		assertEquals(s.getS(),55);
		assertEquals(s.getMl(),600);
	}


	//Tests Equals StopWatch method
	@Test
	public void testEqualsSW(){
		StopWatch s = new StopWatch(3,4,5);
		StopWatch s1 = new StopWatch(3,4,5);
		assertTrue(s.equals(s1));
	}@Test
	public void testEqualsSW2(){
		StopWatch s = new StopWatch(4,5);
		StopWatch s1 = new StopWatch(4,5);
		assertTrue(s.equals(s1));
	}@Test
	public void testEqualsSW3(){
		StopWatch s = new StopWatch(5);
		StopWatch s1 = new StopWatch(5);
		assertTrue(s.equals(s1));
	}


	@Test
	public void testEqualsSWZeros(){
		StopWatch s = new StopWatch(0,0,0);
		StopWatch s1 = new StopWatch(0,0,0);
		assertTrue(s.equals(s1));
	}@Test
	public void testEqualsSWZeros2(){
		StopWatch s = new StopWatch(0,0);
		StopWatch s1 = new StopWatch(0,0);
		assertTrue(s.equals(s1));
	}@Test
	public void testEqualsSWZeros3(){
		StopWatch s = new StopWatch(0);
		StopWatch s1 = new StopWatch(0);
		assertTrue(s.equals(s1));
	}


	@Test
	public void testEqualsSWBorders(){
		StopWatch s = new StopWatch(0,59,0);
		StopWatch s1 = new StopWatch(0,59,0);
		assertTrue(s.equals(s1));
	}@Test
	public void testEqualsSWBorders2(){
		StopWatch s = new StopWatch(59,0);
		StopWatch s1 = new StopWatch(59,0);
		assertTrue(s.equals(s1));
	}@Test
	public void testEqualsSWBorders3(){
		StopWatch s = new StopWatch(0,999);
		StopWatch s1 = new StopWatch(0,999);
		assertTrue(s.equals(s1));
	}@Test
	public void testEqualsSWBorders4(){
		StopWatch s = new StopWatch(999);
		StopWatch s1 = new StopWatch(999);
		assertTrue(s.equals(s1));
	}


	//Tests Equals Object method
	@Test
	public void testEqualsObj(){
		Object s = new StopWatch(1,2,3);
		assertTrue(s.equals(s));
	}
	
	@Test
	public void testEqualsObj2(){
		Object s = new StopWatch(4,5);
		assertTrue(s.equals(s));
	}@Test
	public void testEqualsObj3(){
		Object s = new StopWatch(5);
		assertTrue(s.equals(s));
	}
	

	//Testing Two StopWatch equals
	@Test
	public void testTwoSWEquals(){
		StopWatch s = new StopWatch(3,4,5);
		StopWatch s1 = new StopWatch(3,4,5);
		assertTrue(StopWatch.equals(s, s1));
	}@Test
	public void testTwoSWEquals2(){
		StopWatch s = new StopWatch(4,5);
		StopWatch s1 = new StopWatch(4,5);
		assertTrue(StopWatch.equals(s, s1));
	}@Test
	public void testTwoSWEquals3(){
		StopWatch s = new StopWatch(5);
		StopWatch s1 = new StopWatch(5);
		assertTrue(StopWatch.equals(s, s1));
	}


	@Test
	public void testTwoSWEqualsZeros(){
		StopWatch s = new StopWatch(0,0,0);
		StopWatch s1 = new StopWatch(0,0,0);
		assertTrue(StopWatch.equals(s, s1));
	}@Test
	public void testTwoSWEqualsZeros2(){
		StopWatch s = new StopWatch(0,0);
		StopWatch s1 = new StopWatch(0,0);
		assertTrue(StopWatch.equals(s, s1));
	}@Test
	public void testTwoSWEqualsZeros3(){
		StopWatch s = new StopWatch(0);
		StopWatch s1 = new StopWatch(0);
		assertTrue(StopWatch.equals(s, s1));
	}


	@Test
	public void testTwoSWEqualsBorders(){
		StopWatch s = new StopWatch(0,59,0);
		StopWatch s1 = new StopWatch(0,59,0);
		assertTrue(StopWatch.equals(s, s1));
	}@Test
	public void testTwoSWEqualsBorders2(){
		StopWatch s = new StopWatch(59,0);
		StopWatch s1 = new StopWatch(59,0);
		assertTrue(StopWatch.equals(s, s1));
	}@Test
	public void testTwoSWEqualsBorders3(){
		StopWatch s = new StopWatch(0,999);
		StopWatch s1 = new StopWatch(0,999);
		assertTrue(StopWatch.equals(s, s1));
	}@Test
	public void testTwoSWEqualsBorders4(){
		StopWatch s = new StopWatch(999);
		StopWatch s1 = new StopWatch(999);
		assertTrue(StopWatch.equals(s, s1));
	}


	//Testing Compare to method
	@Test
	public void testCompareTo1(){
		StopWatch s = new StopWatch(3,4,5);
		StopWatch s1 = new StopWatch(3,4,5);
		assertTrue(s.compareTo(s1) == 0);
	}@Test
	public void testCompareTo2(){
		StopWatch s = new StopWatch(4,5);
		StopWatch s1 = new StopWatch(4,5);
		assertTrue(s.compareTo(s1) == 0);
	}@Test
	public void testCompareTo3(){
		StopWatch s = new StopWatch(5);
		StopWatch s1 = new StopWatch(5);
		assertTrue(s.compareTo(s1) == 0);
	}


	@Test
	public void testCompareToZeros(){
		StopWatch s = new StopWatch(0,0,0);
		StopWatch s1 = new StopWatch(0,0,0);
		assertTrue(s.compareTo(s1) == 0);
	}@Test
	public void testCompareToZeros2(){
		StopWatch s = new StopWatch(0,0);
		StopWatch s1 = new StopWatch(0,0);
		assertTrue(s.compareTo(s1) == 0);
	}@Test
	public void testCompareToZeros3(){
		StopWatch s = new StopWatch(0);
		StopWatch s1 = new StopWatch(0);
		assertTrue(s.compareTo(s1) == 0);
	}


	@Test
	public void testCompareToBorders(){
		StopWatch s = new StopWatch(0,59,0);
		StopWatch s1 = new StopWatch(0,59,0);
		assertTrue(s.compareTo(s1) == 0);
	}@Test
	public void testCompareToBorders2(){
		StopWatch s = new StopWatch(59,0);
		StopWatch s1 = new StopWatch(59,0);
		assertTrue(s.compareTo(s1) == 0);
	}@Test
	public void testCompareToBorders3(){
		StopWatch s = new StopWatch(0,999);
		StopWatch s1 = new StopWatch(0,999);
		assertTrue(s.compareTo(s1) == 0);
	}@Test
	public void testCompareToBorders4(){
		StopWatch s = new StopWatch(999);
		StopWatch s1 = new StopWatch(999);
		assertTrue(s.compareTo(s1) == 0);
	}


	@Test
	public void testCompareToMore(){
		StopWatch s = new StopWatch(3,40,978);
		StopWatch s1 = new StopWatch(3,40,979);
		assertFalse(s.compareTo(s1) == 1);
	}@Test
	public void testCompareToMore2(){
		StopWatch s = new StopWatch(3,40,978);
		StopWatch s1 = new StopWatch(3,41,978);
		assertFalse(s.compareTo(s1) == 1);
	}@Test
	public void testCompareToMore3(){
		StopWatch s = new StopWatch(3,40,978);
		StopWatch s1 = new StopWatch(4,40,978);
		assertFalse(s.compareTo(s1) == 1);
	}@Test
	public void testCompareToMore4(){
		StopWatch s = new StopWatch(3,40,978);
		StopWatch s1 = new StopWatch(3,40,979);
		assertTrue(s1.compareTo(s) == 1);
	}@Test
	public void testCompareToMore5(){
		StopWatch s = new StopWatch(3,40,978);
		StopWatch s1 = new StopWatch(3,41,978);
		assertTrue(s1.compareTo(s) == 1);
	}@Test
	public void testCompareToMore6(){
		StopWatch s = new StopWatch(3,40,978);
		StopWatch s1 = new StopWatch(4,40,978);
		assertTrue(s1.compareTo(s) == 1);
	}


	@Test
	public void testCompareToLess(){
		StopWatch s = new StopWatch(3,40,978);
		StopWatch s1 = new StopWatch(3,40,979);
		assertTrue(s.compareTo(s1) == -1);
	}@Test
	public void testCompareToLess2(){
		StopWatch s = new StopWatch(3,40,978);
		StopWatch s1 = new StopWatch(3,41,978);
		assertTrue(s.compareTo(s1) == -1);
	}@Test
	public void testCompareToLess3(){
		StopWatch s = new StopWatch(3,40,978);
		StopWatch s1 = new StopWatch(4,40,978);
		assertTrue(s.compareTo(s1) == -1);
	}@Test
	public void testCompareToLess4(){
		StopWatch s = new StopWatch(3,40,978);
		StopWatch s1 = new StopWatch(3,40,979);
		assertFalse(s1.compareTo(s) == -1);
	}@Test
	public void testCompareToLess5(){
		StopWatch s = new StopWatch(3,40,978);
		StopWatch s1 = new StopWatch(3,41,978);
		assertFalse(s1.compareTo(s) == -1);
	}@Test
	public void testCompareToLess6(){
		StopWatch s = new StopWatch(3,40,978);
		StopWatch s1 = new StopWatch(4,40,978);
		assertFalse(s1.compareTo(s) == -1);
	}


	//Tests add integer
	@Test 
	public void testAddInt () {
		StopWatch s = new StopWatch(0,0,0);
		StopWatch.setMutate(true);
		s.add(3);
		assertEquals(s.toString(), "0:00:003");
	}@Test 
	public void testAddInt2 () {
		StopWatch s = new StopWatch(2,30,980);
		s.add(20);
		assertEquals(s.toString(), "2:31:000");
	}@Test
	public void testAddInt3 () {
		StopWatch s = new StopWatch(1,59,999);
		s.add(1);
		assertEquals(s.toString(), "2:00:000");
	}@Test
	public void testAddInt4 () {
		StopWatch s = new StopWatch(2,59,999);
		s.add(70031);
		assertEquals(s.toString(), "4:10:030");
	}


	@Test 
	public void testAddIntBorders () {
		StopWatch.setMutate(true);
		StopWatch s = new StopWatch(0,0,0);
		s.add(999);
		assertEquals(s.toString(), "0:00:999");
	}@Test 
	public void testAddIntBorders2 () {
		StopWatch s = new StopWatch(0,0,0);
		s.add(1000);
		assertEquals(s.toString(), "0:01:000");
	}@Test 
	public void testAddIntBorders3 () {
		StopWatch s = new StopWatch(0,0,0);
		s.add(59000);
		assertEquals(s.toString(), "0:59:000");
	}@Test 
	public void testAddIntBorders4 () {
		StopWatch s = new StopWatch(0,0,0);
		s.add(60000);
		assertEquals(s.toString(), "1:00:000");
	}


	@Test (expected = IndexOutOfBoundsException.class)
	public void testAddIntNeg () {
		StopWatch s = new StopWatch(0,0,0);
		s.add(-20);
	}


	@Test
	public void testAddIntMutateFalse () {
		StopWatch s = new StopWatch(0,0,0);
		StopWatch.setMutate(false);
		s.add(20);
		assertEquals(s.toString(), "0:00:000");
	}


	//Tests add StopWatch Method
	@Test
	public void testAddSW (){
		StopWatch.setMutate(true);
		StopWatch s = new StopWatch(3,43,12);
		StopWatch s1 = new StopWatch(0,0,0);
		s1.add(s);
		assertEquals(s1.toString(), "3:43:012");
	}@Test
	public void testAddSW2 (){
		StopWatch s = new StopWatch(3,43,12);
		StopWatch s1 = new StopWatch(6,02,300);
		s1.add(s);
		assertEquals(s1.toString(), "9:45:312");
	}

	@Test
	public void testAddSWBorders (){
		StopWatch s = new StopWatch(1,59,111);
		StopWatch s1 = new StopWatch(0,01,0);
		s1.add(s);
		assertEquals(s1.toString(), "2:00:111");
	}@Test
	public void testAddSWBorders2 (){
		StopWatch.setMutate(true);
		StopWatch s = new StopWatch(1,11,999);
		StopWatch s1 = new StopWatch(0,0,1);
		s1.add(s);
		assertEquals(s1.toString(), "1:12:000");
	}@Test
	public void testAddSWBorders3 (){
		StopWatch s = new StopWatch(1,59,999);
		StopWatch s1 = new StopWatch(0,0,1);
		s1.add(s);
		assertEquals(s1.toString(), "2:00:000");
	}@Test
	public void testAddSWBorders4 (){
		StopWatch s = new StopWatch(1,59,999);
		StopWatch s1 = new StopWatch(0,1,2);
		s1.add(s);
		assertEquals(s1.toString(), "2:01:001");
	}


	@Test
	public void testAddSWMutateFalse (){
		StopWatch s = new StopWatch(1,59,999);
		StopWatch s1 = new StopWatch(0,1,2);
		StopWatch.setMutate(false);
		s1.add(s);
		assertEquals(s1.toString(), "0:01:002");
	}


	//Tests increment method
	@Test
	public void testInc (){
		StopWatch s = new StopWatch(0,0,0);
		s.inc();
		assertEquals(s.toString(), "0:00:001");
	}@Test
	public void testInc2 (){
		StopWatch s = new StopWatch(1,59,999);
		s.inc();
		assertEquals(s.toString(), "2:00:000");
	}@Test
	public void testInc3 (){
		StopWatch s = new StopWatch(1,59,999);
		StopWatch.setMutate(false);
		s.inc();
		assertEquals(s.toString(), "1:59:999");
	}


	//Tests toString method
	@Test
	public void testToString(){
		StopWatch s = new StopWatch(0,00,000);
		assertEquals(s.toString(), "0:00:000");
	}


	@Test
	public void testToStringPlacement(){
		StopWatch s = new StopWatch(5,00,000);
		assertEquals(s.toString(), "5:00:000");
	}@Test
	public void testToStringPlacement1(){
		StopWatch s = new StopWatch(0,50,000);
		assertEquals(s.toString(), "0:50:000");
	}@Test
	public void testToStringPlacement2(){
		StopWatch s = new StopWatch(0,05,000);
		assertEquals(s.toString(), "0:05:000");
	}@Test
	public void testToStringPlacement3(){
		StopWatch s = new StopWatch(0,00,500);
		assertEquals(s.toString(), "0:00:500");
	}@Test
	public void testToStringPlacement4(){
		StopWatch s = new StopWatch(0,00,50);
		assertEquals(s.toString(), "0:00:050");
	}@Test
	public void testToStringPlacement5(){
		StopWatch s = new StopWatch(0,00,005);
		assertEquals(s.toString(), "0:00:005");
	}@Test
	public void testToStringPlacement6(){
		StopWatch s = new StopWatch(5,31,345);
		assertEquals(s.toString(), "5:31:345");
	}


	@Test
	public void testToStringBorders(){
		StopWatch s = new StopWatch(0,9,0);
		assertEquals(s.toString(), "0:09:000");
	}@Test
	public void testToStringBorders2(){
		StopWatch s = new StopWatch(0,10,0);
		assertEquals(s.toString(), "0:10:000");
	}@Test
	public void testToStringBorders3(){
		StopWatch s = new StopWatch(0,59,0);
		assertEquals(s.toString(), "0:59:000");
	}@Test
	public void testToStringBorders4(){
		StopWatch s = new StopWatch(0,0,9);
		assertEquals(s.toString(), "0:00:009");
	}@Test
	public void testToStringBorders5(){
		StopWatch s = new StopWatch(0,0,10);
		assertEquals(s.toString(), "0:00:010");
	}@Test
	public void testToStringBorders6(){
		StopWatch s = new StopWatch(0,0,999);
		assertEquals(s.toString(), "0:00:999");
	}		


	//Tests the setMutate method
	@Test
	public void testSetMutate(){
		StopWatch s = new StopWatch(3,40,500);
		StopWatch s1 = new StopWatch(3,40,500);
		StopWatch.setMutate(false);
		s1.inc();
		assertTrue(s.equals(s1));
	}@Test
	public void testSetMutate2(){
		StopWatch s = new StopWatch(3,40,500);
		StopWatch s1 = new StopWatch(3,40,500);
		StopWatch.setMutate(true);
		s1.inc();
		assertFalse(s.equals(s1));
	}@Test
	public void testSetMutate3(){
		StopWatch s = new StopWatch(3,40,501);
		StopWatch s1 = new StopWatch(3,40,500);
		StopWatch.setMutate(true);
		s1.inc();
		StopWatch.setMutate(false);
		s1.inc();
		assertTrue(s.equals(s1));
	}


	//Test Load and Save method
	@Test 
	public void testSave () {
		StopWatch s1 = new StopWatch (5,59,300);
		s1.save("test");
		s1 = new StopWatch ();
	}@Test 
	public void testSave2 () {
		StopWatch s1 = new StopWatch (5,59,300);
		s1.save("////");
		s1 = new StopWatch ();
	}@Test 
	public void testSave3 () {
		StopWatch s1 = new StopWatch (5,59,300);
		s1.save("000000");
		s1 = new StopWatch ();
	}
}
