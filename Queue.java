
public class Queue<T> {
	private LinkedList<T> list;
	int cursor;
	//create a queue
	public Queue(){
		list= new LinkedList<T>();
		cursor=0;
	}
	
	//add an item to
	public void enqueue(T item){
		list.addToEnd(item);
	}
	
	//remove the item which was first added to the queue
	public T dequeue(){
		if(list.isEmpty())
			return null;
		T result = list.getAt(0);
		list.removeAt(0);
		return result;
	}
	//return the number of elements of an array
	public int size(){
		return list.size();
	}
	
	//return if the queue is empty
	public boolean isEmpty(){
		return list.isEmpty();
	}
	
	//remove all the elements in the list
	public void clear(){
		list.clear();
	}
	
	//get the element at the top of the queue
	public T peak(){
		if(list.isEmpty())
			return null;
		return list.getAt(0);
	}
	
	//return the position of a given item
	public int positionOf(T item){
		return list.indexOf(item);
	}
	
	//remove the first occurance of a given item
	public void remove(T item){
		list.remove(item);
	}
	
	//get the first thing in the Q
	public T first(){
		if(list.isEmpty())
			return null;
		cursor=0;
		return list.getAt(cursor);
	}
	
	//get the next thing using the cursor in the Q
	public T next(){
		if(list.isEmpty() || cursor > list.size()-2)
			return null;
		cursor++;
		return list.getAt(cursor);
	}
	
	//get the element at a given index in the Q
	public T getAt(int index)
	{
		return list.getAt(index);
	}



}
