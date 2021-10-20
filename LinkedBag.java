
public class LinkedBag<T> implements BagInterface<T> {
	private Node firstNode;
	private int numOfEntries;
	
	public LinkedBag() {
		firstNode = null;
		numOfEntries = 0;
	}
	/** Adds a new entry to this bag.
	 * @paramnewEntryThe object to be added as a new entry
	 * @returnTrue if the addition is successful, or false if not. */
	public boolean add(T newEntry) {
		// Add to beginning of chain:
		Node newNode = new Node(newEntry);
		newNode.next = firstNode; 	// Make new node reference rest of chain
									// (firstNodeis null if chain is empty)        
		firstNode = newNode; 		// New node is at beginning of chain
		numOfEntries++;
		
		return true;
	} // end add
	
	public boolean remove(T anEntry){
		boolean result= false;
		Node nodeN= getReferenceTo(anEntry);
		if(nodeN!= null){
			// Replace located entry with entry in first node
			nodeN.setData(firstNode.getData()); // Remove first node
			firstNode= firstNode.getNextNode(); 
			numOfEntries--;
			result= true;
			} // end if
		return result;
	} // end remove

	private Node getReferenceTo(T anEntry){
		boolean found= false;
		Node currentNode= firstNode;
		while(!found&& (currentNode!= null)){
			if(anEntry.equals(currentNode.getData()))
				found= true;
			else
				currentNode= currentNode.getNextNode();
			} // end while
		return currentNode;
	} // end getReferenceTo
	
	/** Removes one unspecified entry from this bag, if possible.
	 * @returnEither the removed entry, if the removal was successful, or null. */
	public T remove(){
		T result= null;
		if(firstNode != null){
			result = firstNode.getData();
			firstNode= firstNode.getNextNode(); // Remove first node from chain
			numOfEntries--;} // end if
		
		return result;
	} // end remove

	public boolean isEmpty() {
		return numOfEntries == 0;
	} // end isEmpty
	
	public int getCurrentSize() {
		return numOfEntries;
	} // end getCurrentSize
	
	public void clear() {
		while (!isEmpty())
			remove();
	} // end clear
	
	public int getFrequencyOf (T anEntry) {
		int frequency = 0;
		
		int counter = 0;
		Node currentNode = firstNode;
		while ((counter < numOfEntries) && (currentNode != null)) {
			if (anEntry.equals(currentNode.getData()))
				frequency++;
			counter++; // increase counter
			currentNode = currentNode.getNextNode();
		} // while
		return frequency;
	} // end getFrequencyOf
	
	public boolean contains (T anEntry) {
		boolean found = false;
		Node currentNode = firstNode;
		
		while (!found && (currentNode != null)) {
			if (anEntry.equals(currentNode.getData()))
				found = true;
			else
				currentNode = currentNode.getNextNode();
		} // end while
		return found;
	} //end contains
	
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] result = (T[])new Object[numOfEntries];
		
		int index = 0;
		Node currentNode = firstNode;
		while ((index < numOfEntries) && (currentNode != null)) {
			result[index] = currentNode.getData(); 
			index++;
			currentNode = currentNode.getNextNode();
		} // end while
		return result;
	} //end toArray
	
	public BagInterface<T> union (BagInterface<T> otherBag) {
		BagInterface<T> everything = new LinkedBag<>(); // Create everything to hold the union
		T[] firstBag = this.toArray(); // Take first bag as an array
		T[] secondBag = otherBag.toArray(); // Take second bag as an array
		
		for (int i = 0; i < secondBag.length; i++) {
			everything.add(secondBag[i]); // Place all of the second bag contents into everything
		}
		for (int i = 0; i < firstBag.length; i++) {
			everything.add(firstBag[i]); // Place all of the first bag contents into everything
		}
		return everything; // Return the union of the two bags
	} // end union
	
	public BagInterface<T> intersection (BagInterface<T> otherBag) {
		BagInterface<T> commonItems = new LinkedBag<>(); // Create commonItems to hold the intersection
		T[] firstBag = this.toArray(); // Take first bag as an array
		T[] secondBag = otherBag.toArray(); // Take second bag as an array
		BagInterface<T> copyOfBag = new LinkedBag<>(); // Create copy of the linked bag to hold contents without altering actual bag
		
		for (int i = 0; i < firstBag.length; i++) {
			copyOfBag.add(firstBag[i]); // Make a copy of the first bag
		}
		
		for (int i = 0; i < secondBag.length; i++) {
			if (copyOfBag.contains(secondBag[i])) {
				commonItems.add(secondBag[i]); // If first and second bag have an item in common, add to commonItems
				copyOfBag.remove(secondBag[i]); // Remove the common item from the copied bag
			}
		}
		return commonItems; // Return the intersection of the two bags
	} // end intersection
	
	public BagInterface<T> difference(BagInterface<T> otherBag) {
		BagInterface<T> leftOver = new LinkedBag<>(); // Create leftOver to hold the difference
		T[] firstBag = this.toArray(); // Take first bag as an array
		T[] secondBag = otherBag.toArray(); // Take second bag as an array
		
		for (int i = 0; i < firstBag.length; i++) {
			leftOver.add(firstBag[i]); // Add first bag to the leftovers
		}
		
		for (int i = 0; i < secondBag.length; i++) {
			if (leftOver.contains(secondBag[i])) {
				leftOver.remove(secondBag[i]); // If first bag contains anything from second bag, take it out
			}
		}
		return leftOver; // Return the difference of the two bags
	} // end difference
	
	private class Node {
		private T data;
		private Node next;
		
		private Node (T dataPortion) {
			this(dataPortion, null);
		}
		
		private Node(T dataPortion, Node nextNode) {
			data = dataPortion;
			next = nextNode;
		}
		
		private T getData() {
			return data;
		}
		
		private void setData(T newData) {
			data = newData;
		}
		
		private Node getNextNode() {
			return next;
		}
		
	}
	
	
}
