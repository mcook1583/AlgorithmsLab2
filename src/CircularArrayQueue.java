import java.util.NoSuchElementException;

public class CircularArrayQueue implements MyQueue{

	int capacity = 10;
	int head = 0;
	int tail = 0;
	int noItems = 0;
	int[] array = new int[capacity];
	
	public void enqueue(int in) {
		//checks if the array isn't full, resizes if it is
		if(noItems>=capacity){
			resizeQueue();
		}
		//works out what index the head is at even if it has exceeded the capacity
		head = (tail+noItems) % capacity;
		array[head] = in;
		noItems++;
	}

	public int dequeue() throws NoSuchElementException {
		if(noItems>0){
			//set the value as a temporary variable
			int item = array[tail];
			//set the value at the tail to some irrelevant value
			array[tail]=0;
			tail++;
			noItems--;
			//if the tail goes over the capacity reset it to an index of 0
			if(tail==capacity){
				tail=0;
			}
			return item;
		}else{
			throw new NoSuchElementException();
		}
	}

	public int noItems() {
		return noItems;
	}
	
	public int getCapacityLeft() {
		return capacity - noItems;
	}

	public boolean isEmpty() {
		if(noItems==0){
			return true;
		}else{
			return false;
		}
	}

	private void resizeQueue() {
		//create a temporary array with double capacity
		int[] tempArray = new int[capacity*2];
		//loop through all values and place them in the new array
		for(int i=0;i<capacity;i++){
			tempArray[i] = array[i];
		}
		//set the array to the temporary array and update the capacity value
		array = tempArray;
		capacity = capacity*2;
	}
}
