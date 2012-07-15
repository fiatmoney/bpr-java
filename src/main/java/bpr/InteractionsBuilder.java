package bpr;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;

/**A factory for SamplableInteractionsLists.  This version does not support
 * concurrent inserts.*/
public class InteractionsBuilder implements Serializable {
	
	private final HashMap<Integer,HashSet<Integer>> personToItemSet = 
			new HashMap<Integer,HashSet<Integer>>();
	
	/**@return a PersonSamplableInteractions based on the current state of the
	 * InteractionsBuilder.*/
	public PersonSamplableInteractions makePersonSamplableInteractions(){
		return new PersonSamplableInteractions(personToItemSet);
	}
	
	/**@return an ItemSamplableInteractions based on the current state of the
	 * InteractionsBuilder.*/
	public ItemSamplableInteractions makeItemSamplableInteractions(){
		return new ItemSamplableInteractions(personToItemSet);
	}
	
	/**Adds the person-item combination to the state of the factory.*/
	public void addInteraction(int personID, int itemID){
		if(!personToItemSet.containsKey(personID)){
			personToItemSet.put(personID, new HashSet<Integer>());
		}
		personToItemSet.get(personID).add(itemID);
	}
	
	/**Remove all records for the person. O(1) operation.*/
	public void removePerson(int personID){
		personToItemSet.remove(personID);
	}
	
	/**Remove all records for the item.  O(n) operation, as it traverses the
	 * entire map.*/
	public void removeItem(int itemID){
		for(HashSet<Integer> items:personToItemSet.values()){
			items.remove(itemID);
		}
	}
}
