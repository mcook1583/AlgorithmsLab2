import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularArrayRing<T> extends AbstractCollection<T> implements Ring<T> {

	int capacity;
	int head;
	int noItems;
	T[] array;
	
	@SuppressWarnings("unchecked")
	public CircularArrayRing(){
		this.capacity = 10;
		this.noItems=0;
		this.array = (T[]) new Object[capacity];
	}
	
	@SuppressWarnings("unchecked")
	public CircularArrayRing(int capacity){
		this.capacity = capacity;
		this.noItems=0;
		this.array = (T[]) new Object[capacity];
	}

	public boolean add(T e) {
		head=(head+1|0);
		//reset the index of the head to 0 if the capacity is exceeded
		if(head==capacity){
			head=0;
		}
		array[head] = e;
		//once the ring is filled keep the number of items at full capcity
		if(noItems<capacity){
			noItems++;
		}
		return true;
	}


	public T get(int index) throws IndexOutOfBoundsException {
		if(index<0|| index>noItems || index>capacity){
			throw new IndexOutOfBoundsException();
		}else{
			//make sure the index is non-negative for the current capacity
			int tempIndex = (head-index+capacity) % capacity;
			return array[tempIndex];
		}
	}

	public Iterator<T> iterator() {
		Iterator<T> ringIterator = new Iterator<T>() {

            int currentIndex = head;

            public boolean hasNext() {
                return currentIndex==0;
            }

            public T next() {
            	if(this.hasNext()){
            		return array[currentIndex--];
            	}else{
            		throw new NoSuchElementException();
            	}
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return ringIterator;
	}

	public int size() {
		return noItems;
	}

}
