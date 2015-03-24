package edu.fgcu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class MedianFinder <T extends Comparable<T>>{

	private HeapTree<T> minHeap; // stores the greater half
	private HeapTree<T> maxHeap; // stores the less half

	public MedianFinder(int capacity, HeapTree<T> minHeap, HeapTree<T> maxHeap){
		if(capacity<=0){
			throw new IllegalStateException(
					"Capacity cannot be less than zero or equal to zero.");
		}
		this.minHeap = minHeap;
		this.maxHeap = maxHeap;
	}

	public void add(T val){
		if(maxHeap.getSize()==0 && minHeap.getSize()==0){
			minHeap.insert(val);
			return;
		}

		T min = minHeap.peek();

		if(val.compareTo(min)==1){
			minHeap.insert(val);
			if(minHeap.getSize()==maxHeap.getSize()+2){
				maxHeap.insert(minHeap.delete());
			}
		}else{
			maxHeap.insert(val);
			if(maxHeap.getSize()==minHeap.getSize()+2){
				minHeap.insert(maxHeap.delete());
			}
		}
	}

	public List<T> median(){
		List<T> result = new ArrayList<T>();
		if(minHeap.getSize()+maxHeap.getSize()==0){
			return result;
		}
		if(minHeap.getSize()>maxHeap.getSize()){
			result.add(minHeap.peek());
		}else if(minHeap.getSize()<maxHeap.getSize()){
			result.add(maxHeap.peek());
		}else{
			result.add(minHeap.peek());
			result.add(maxHeap.peek());
		}
		return result;
	}
}
