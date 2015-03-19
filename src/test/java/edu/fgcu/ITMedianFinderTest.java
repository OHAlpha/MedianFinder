package edu.fgcu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ITMedianFinderTest {
	private HeapTree<Integer> minHeap;
	private HeapTree<Integer> maxHeap;
	private static final int CAPACITY = 10000;
	private MedianFinder<Integer> finder;
	private List<Integer> nums;
	private Random rand;
	@Before
	public void setup(){
		minHeap = new HeapTree<Integer>(CAPACITY/2+1, new Comparator<Integer>(){
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2.compareTo(o1);
			}
		});

		maxHeap = new HeapTree<Integer>(CAPACITY/2+1, new Comparator<Integer>(){
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);
			}
		});

		finder = new MedianFinder<Integer>(CAPACITY, minHeap, maxHeap);
		rand = new Random();

		nums = new ArrayList<Integer>();
	}

	@Test(timeout=1000)
	public void testAdd(){
		for(int i=0;i<CAPACITY;i++){
			finder.add(rand.nextInt());
		}
	}

	@Test(timeout=2000)
	public void testMedianWithTimeout(){
		for(int i=0;i<CAPACITY;i++){
			finder.add(rand.nextInt());
			finder.median();
		}
	}

	@Test
	public void testMedian(){
		for(int i=0;i<CAPACITY;i++){
			int r = rand.nextInt();
			nums.add(r);
			finder.add(r);
			Collections.sort(nums);
			if((i+1)%2==0){
				checkEvenNumberOfInputs(finder, i);
			}else{
				checkOddNumberOfInputs(finder, i);
			}
		}

	}

	private void checkOddNumberOfInputs(MedianFinder<Integer> finder, int length) {
		List<Integer> median = finder.median();
		assertEquals("Number of medians", 1, median.size());
		assertEquals("Median", nums.get(length/2), median.get(0));
	}

	private void checkEvenNumberOfInputs(MedianFinder<Integer> finder, int length) {
		List<Integer> median = finder.median();
		assertEquals("Number of medians", 2, median.size());
		assertEquals("Median 1", nums.get(length/2), median.get(1));
		assertEquals("Median 2", nums.get(length/2+1), median.get(0));
	}
}
