package Grupo11_ISCTE.Code_Smells_Detective;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class TestListUtilities {

	@Test
	void testUnion() {
	
		Integer one = 1;
		Integer two = 2;
		Integer three = 3;
		Integer four = 4;
		Integer five = 5;
		
		
		ArrayList<Integer> conjunto1 = new ArrayList<Integer>();
		
		conjunto1.add(one);
		conjunto1.add(two);
		conjunto1.add(three);
		
		ArrayList<Integer> conjunto2 = new ArrayList<Integer>();
		conjunto2.add(three);
		conjunto2.add(four);
		conjunto2.add(five);
		
		
		ArrayList<Integer> output = (ArrayList<Integer>) ListUtilities.union(conjunto1, conjunto2);
		
		assertEquals(1, output.get(0));
		assertEquals(2, output.get(1));
		assertEquals(3, output.get(2));
		assertEquals(4, output.get(3));
		assertEquals(5, output.get(4));	
	}
	
	
	@Test
	void testIntersection() {
		
		Integer one = 1;
		Integer two = 2;
		Integer three = 3;
		Integer four = 4;
		Integer five = 5;
		
		
		ArrayList<Integer> conjunto1 = new ArrayList<Integer>();
		conjunto1.add(one);
		conjunto1.add(two);
		conjunto1.add(three);
		
		ArrayList<Integer> conjunto2 = new ArrayList<Integer>();
		conjunto2.add(three);
		conjunto2.add(four);
		conjunto2.add(five);
		
		
		ArrayList<Integer> output = (ArrayList<Integer>) ListUtilities.intersection(conjunto1, conjunto2);
		
		assertEquals(3, output.get(0));
		
	}

}
