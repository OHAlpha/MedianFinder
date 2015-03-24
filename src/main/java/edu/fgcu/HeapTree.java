package edu.fgcu;

import java.util.Comparator;

class HeapTree<T extends Comparable<T>>{
	private Comparator<T> comparator;
	private T [] buffer;
	private int size;

	public HeapTree(int capacity, Comparator<T> comparator){
		if(capacity<=0){
			throw new IllegalStateException(
					"Capacity cannot be less than zero or equal to zero.");
		}
		buffer = (T[])new Comparable[capacity];
		this.comparator = comparator;
		size = 0;
	}

	public boolean isFull(){
		return size==buffer.length;
	}

	public boolean isEmpty(){
		return size==0;
	}

	public void insert(T val){
		if(isFull()){
			throw new IllegalStateException(
					"HeapTree is full, no more insert.");
		}

		buffer[size] = val;

		int son = size, father = (son-1)/2;
		while(son>0 && comparator.compare(buffer[son], buffer[father])==1){
			T temp = buffer[son];
			buffer[son] = buffer[father];
			buffer[father] = temp;

			son = father;
			father = (son-1)/2;
		}

		size++;
	}

	public T delete(){
		if(isEmpty()){
			throw new IllegalStateException(
					"HeapTree is empty, cannot delete.");
		}
		T temp = buffer[0];
		buffer[0] = buffer[size-1];
		buffer[size-1] = temp;

		size--;

		int father = 0, left = 2*father+1, right = 2*father+2;
		int larger;
		if(left<size){
			if(right>=size){
				larger = left;
			}else{
				larger = comparator.compare(buffer[left], buffer[right])==1?left:right;
			}
			while(comparator.compare(buffer[larger], buffer[father])==1){
				temp = buffer[father];
				buffer[father] = buffer[larger];
				buffer[larger] = temp;

				father = larger; left = 2*father+1; right = 2*father+2;
				if(left>=size){
					break;
				}else if(right>=size){
					larger = left;
				}else{
					larger = comparator.compare(buffer[left], buffer[right])==1?left:right;
				}
			}
		}

		return buffer[size];
	}

	public int getSize(){
		return size;
	}

	public T peek(){
		if(size==0){
			throw new IllegalStateException(
								"HeapTree is full, nothing to peek.");

		}else{
			return buffer[0];
		}
	}
}
