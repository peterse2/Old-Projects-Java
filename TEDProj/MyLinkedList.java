package package1;


/***********************************************************************
 * Implements a simple linked list.
 * @author Emily Peterson
 * @version 11/5/2014
 **********************************************************************/
public class MyLinkedList implements ILinkedList {

	/** First Line */
	private Node front;

	/** Last Line */
	private Node rear;

	/** Number of lines */
	private int size;


	/*******************************************************************
	 * Initializes a newly constructed MyLinkedList object.
	 ******************************************************************/
	public MyLinkedList(){
		front = null;
		rear = null;
		size = 0;
	}


	/*******************************************************************
	 * Adds the element to the end of the list
	 * @param element a string of text
	 * @throws NullPointerException if the specified element is null
	 ******************************************************************/
	public void add(String element) {
		//Node temp = front;

		if (element == null){
			throw new NullPointerException();
		}if(front == null){
			front = new Node(element);
			rear = front;
			size ++;
			return;
		}
		
		rear.setNext(new Node(element));
		rear = rear.getNext();

		size++;

	}


	/*******************************************************************
	 * Inserts the element at the specified position in the list
	 * @param index position number in the list
	 * @param element a string of text
	 * @throws IndexOutOfBoundsException if the index is out of range 
	 * (index < 0 || index > size())
	 * @throws NullPointerException if the specified element is null
	 ******************************************************************/
	public void add(int index, String element) {
		Node before = front;
		Node after = rear;
		Node temp = new Node(element);
		
		if(element == null){
			throw new NullPointerException();
		}
		
		temp = new Node(element);
		

		//what is domain of index? does it start at 0?
		//index-1? index =0?
		if (index > size || index < 0 ){
			throw new IndexOutOfBoundsException();
		}
		
		//At Begining
		if(index == 0){
			if(index == size)
				rear = temp;
			else
				temp.setNext(front);
			front = temp;
			size++;
			return;
		}
		
		//Finds the node before where the insertion is happening
		while (index > 1){
			before =  before.getNext();
			index --;
		}
		
		after = before.getNext();
		before.setNext(temp);
		temp.setNext(after);
		size++;
		
	}

	/*******************************************************************
	 * Removes and returns the element at the specified position in the 
	 * list
	 * @param index position number in the list
	 * @return the element previously at the specified position
	 * @throws IndexOutOfBoundsException if the index is out of range 
	 * (index < 0 || index >= size())
	 ******************************************************************/
	public String remove(int index) {
		Node before = front;
		Node after = rear;
		Node temp = front;

		if (index >= size || index < 0){
			throw new IndexOutOfBoundsException();
		}
		
		//one one element
		if(size == 1){
			temp = new Node(front.getData());
			clear();
			return temp.getData();
		}
		
		//At begining 
		if(index == 0){
			temp = new Node(front.getData());
			front = front.getNext();
			size --;
			return temp.getData();
		}
		
		//Not at begining
		while (index > 1){
			before =  before.getNext();
			temp = before.getNext();
			index --;
		}
		
		if(before == rear){
			temp = new Node(rear.getData());
			rear = before;
			size --;
			return temp.getData();
		}
		
		after = before.getNext().getNext();
		before.setNext(after);
		size--;
		return temp.getData();
		
	}


	/*******************************************************************
	 * Returns the element at the specified position in the list
	 * @param index position number in the list
	 * @return str string at index 
	 * @throws IndexOutOfBoundsException if index is invalid or list is 
	 * empty (index < 0 || index >= size())
	 ******************************************************************/
	public String get(int index) {
		Node temp = front;
		
		if(index < 0 || index > size-1){
			throw new IndexOutOfBoundsException();
		}
		
		while (index > 0){
			temp =  temp.getNext();
			index --;
		}
		
		return temp.getData();
	}
	
	
	/*******************************************************************
	 * Returns the Node at the specified position in the list
	 * @param index position number in the list
	 * @return str string at index 
	 * @throws IndexOutOfBoundsException if index is invalid or list is 
	 * empty (index < 0 || index >= size())
	 ******************************************************************/
	public Node getNode(int index) {
		Node temp = front;
		
		if(index < 0 || index > size-1){
			throw new IndexOutOfBoundsException();
		}
		
		while (index > 0){
			temp =  temp.getNext();
			index --;
		}
		
		return temp;
	}


	/*******************************************************************
	 * Determines if the list is comtains any elements
	 * @return true if the list is empty
	 * @return flase if the list conatins an element
	 ******************************************************************/
	public boolean isEmpty() {
		return size == 0;
	}


	/*******************************************************************
	 * Returns the number of elements in the list
	 * @return size amount of elements in the list
	 ******************************************************************/
	public int size() {
		return size;
	}


	/*******************************************************************
	 * Removes all the elements from the list.
	 * The list will be empty after this call returns.
	 ******************************************************************/
	public void clear() {
		front = null;
		rear = null;
		size = 0;
	}


	/*******************************************************************
	 * Displays all the node's data in the list 
	 ******************************************************************/
	public void display () {
		Node temp = front;

		while (temp != null) {
			System.out.println(temp.getData());
			temp = temp.getNext();
		}
		System.out.println();
	}
	
	
	/*******************************************************************
	 * Displays the node's data in the indicated range inclusively with 
	 * line numbers
	 * @param index1 begining index
	 * @param index2 end index
	 ******************************************************************/
	public void display(int index1, int index2) {
		Node temp = front;
		int lineNum = 1;

		while (temp.getData() != get(index1)) {
			temp = temp.getNext();
		}
		while(index1 <= index2){
			temp = temp.getNext();
			index1++;
			lineNum++;
			System.out.println("" + lineNum + temp.getData());
		}
		System.out.println();
	}


	/*******************************************************************
	 * Returns the first node of the list
	 * @return front the first node of the list
	 ******************************************************************/
	public Node getFront() {
		return front;
	}


	/*******************************************************************
	 * Returns the last node of the list
	 * @return rear the last node of the list
	 ******************************************************************/
	public Node getRear() {
		return rear;
	}
	
	
	/*******************************************************************
	 * Sets the first node to given node; replaces the first node with 
	 * the one given
	 * @param front the first node of the list
	 ******************************************************************/
	public void setFront(Node front) {
		this.front = front;
	}


	/*******************************************************************
	 * Sets the last node to given node; replaces the last node with 
	 * the one given
	 * @param rear the last node of the list
	 ******************************************************************/
	public void setRear(Node rear) {
		this.rear = rear;
	}
}
