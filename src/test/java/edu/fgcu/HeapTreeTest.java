package edu.fgcu;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Random;
import java.util.TreeSet;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HeapTreeTest {
	private HeapTree<Integer> tree;
	private static final int CAPACITY=100;
	private Random rand;
	@Before
	public void setup(){
		rand = new Random();
		tree = new HeapTree<Integer>(CAPACITY,
				new Comparator<Integer>(){
					@Override
					public int compare(Integer arg0, Integer arg1) {
						return arg0.compareTo(arg1);
					}

		});
	}

	@Test
	public void testIsEmpty(){
		assertTrue("Tree's initial size", tree.isEmpty());
	}

	@Test
	public void testIsFull(){
		for(int i=0;i<CAPACITY;i++){
			tree.insert(i);
		}
		assertTrue("After "+CAPACITY+" inserts, tree", tree.isFull());
	}

	@Test
	public void testInsert(){
		TreeSet<Integer> set = new TreeSet<Integer>();
		for(int i=0;i<CAPACITY;i++){
			int r = rand.nextInt();
			tree.insert(r);
			set.add(r);
			assertEquals("The root of the tree", (int)set.last(), (int)tree.peek());
		}
	}

	@Test
	public void testGetSize(){
		for(int i=0;i<CAPACITY;i++){
			tree.insert(i);
			assertEquals("The size of the tree", i+1, tree.getSize());
		}
	}

	@Test
	public void testDelete(){
		TreeSet<Integer> set = new TreeSet<Integer>();
		for(int i=0;i<CAPACITY;i++){
			int r = rand.nextInt();
			tree.insert(r);
			set.add(r);
		}

		for(int i=0;i<CAPACITY;i++){
			int d = tree.delete();
			assertEquals("The deleted element", (int)set.last(), d);
			set.remove(d);
		}
	}
}
