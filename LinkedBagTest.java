
public class LinkedBagTest {
	public static void main(String[] args) {
		//Add chars to bag 1
		String[] bag1Array = {"a", "b", "c"};
		BagInterface<String> bag1 = new LinkedBag<>();
	
		for (int i = 0; i < bag1Array.length; i++) 
			bag1.add(bag1Array[i]);
	
		//Add chars to bag 2
		String[] bag2Array = {"b", "b"};
		BagInterface<String> bag2 = new LinkedBag<>();
	
		for (int i = 0; i < bag2Array.length; i++) 
			bag2.add(bag2Array[i]);
	
		//Call the union, intersection, and difference methods
		BagInterface<String> everything = bag1.union(bag2);
		Object[] everythingArray = everything.toArray();
		
		BagInterface<String> commonItems = bag1.intersection(bag2);
		Object[] commonItemsArray = commonItems.toArray();
		
		BagInterface<String> leftOver1 = bag1.difference(bag2);
		Object[] leftOver1Array = leftOver1.toArray();
		
		BagInterface<String> leftOver2 = bag2.intersection(bag1);
		Object[] leftOver2Array = leftOver2.toArray();
	
		//Print the chars in the union
		System.out.print("Union: ");
		for (int i = 0; i < everythingArray.length; i++) {
			System.out.print(everythingArray[i]+" ");
		}
	
		//Print the chars in the intersection
		System.out.print("\nIntersection: ");
		for (int i = 0; i < commonItemsArray.length; i++) {
			System.out.print(commonItemsArray[i]+" ");
		}
	
		//Print the chars in the difference
		System.out.print("\nLeftovers from bag 1 to bag 2: ");
		for (int i = 0; i < leftOver1Array.length; i++) {
			System.out.print(leftOver1Array[i]+" ");
		}
		System.out.print("\nLeftovers from bag 2 to bag 1: ");
		for (int i = 0; i < leftOver2Array.length; i++) {
			System.out.print(leftOver2Array[i]+" ");
		}
	}
}
