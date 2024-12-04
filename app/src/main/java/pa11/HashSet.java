package pa11;
import java.util.LinkedList;


public class HashSet {  

    int size = 0;
    int capacity = 17;
    LinkedList<String>[] data;
    /**
     * Constructor for the set
     */
    @SuppressWarnings("unchecked")
    public HashSet() {
        System.out.println("HashSet");
        this.data = new LinkedList[this.capacity];
    }

    /**
     * Size of the set
     * @return the number of elements in the set
     */
    public int size() {
        System.out.println("Size");
        return this.size;
    }

    /** 
     * Check if the set is empty
     * @return a boolean indicating whether the set is empty
     */
    public boolean isEmpty() {
        return this.size == 0;
    }
    private int hashingAlgorithm(String s){
        int xcode = 0;
        for(int i=0; i<s.length(); i++){
            int ascii = (int) s.charAt(i);
            xcode += ascii;
        }
        return xcode % this.capacity;
    }
    /**
     * Add an element to the set
     * @param s the element to add
     * @return the old value associated with the key, or null if no such entry exists
     */
    public void add(String s) {
        System.out.println("Adding " + s);
        int xcode = this.hashingAlgorithm(s);

        if (this.data[xcode] == null) {
            this.data[xcode] = new LinkedList<>();
        }
        
        if(!this.contains(s)){
            this.data[xcode].add(s);
            this.size++;
        }
    }

    /** 
     * Remove an element from the set
     * @param s the element to remove
     * @return the value associated with the key, or null if no such entry exists
     */
    public void remove(String s) {
        System.out.println("Removing " + s);

        int xcode = this.hashingAlgorithm(s);

        if (this.data[xcode] == null) {
            return;
        }

        int index = this.data[xcode].indexOf(s);

        if(index != -1){
            this.data[xcode].remove(index);
            this.size--;
        }
    }
    
    /** 
     * Check if the set contains an element
     * @param s the element to check for
     * @return a boolean indicating whether the set contains the element
     */
    public boolean contains(String s) {
        System.out.println("Contains " + s);
        int xcode = this.hashingAlgorithm(s);

        if (this.data[xcode] == null) {
            return false;
        }

        int index = this.data[xcode].indexOf(s);

        return index != -1;
    }

    /** 
     * Clears the set
     */
    public void clear() {
        System.out.println("Clear");
        for (int i = 0; i < this.capacity; i++) {
            if (this.data[i] != null) {
                this.data[i].clear();
            }
        }
        this.size = 0;
    }

    /** 
     * Convert the set to an array
     * @return an array containing all the elements in the set
     */
    public String[] toArray() {
        String[] arr = new String[this.size];
        int curr = 0;
        for (int i = 0; i < this.capacity; i++) {
            if (this.data[i] != null) {
                for (int j = 0; j < this.data[i].size(); j++) {
                    arr[curr] = this.data[i].get(j);
                    curr++;
                }
            }
        }
        return arr;
    }

    /** 
     * Takes the intersection of the current set and the `other` set
     * @param other the set to intersect with
     * @return a new `HashSet` containing the intersection of the current set and the `other` set
     */
    public HashSet intersection(HashSet other) {
        String[] arr = this.toArray();
        HashSet result = new HashSet();
        for (String str : arr) {
            if (other.contains(str)) {
                result.add(str);
            }
        }
        return result;
    }

    /** 
     * Convert the set to a string
     * @param other the set to union with
     * @return a new `HashSet` containing the union of the current set and the `other` set
     */
    public HashSet union(HashSet other) {
        String[] arr = this.toArray();
        String[] arr2 = other.toArray();

        HashSet result = new HashSet();
        for (String str : arr) {
            result.add(str);
        }

        for (String str : arr2) {
            result.add(str);
        }

        return result;
    }

    /** 
     * Takes the difference of the current set and the `other` set 
     * @param other the set to take the difference with
     * @return a new `HashSet` containing the difference of the current set and the `other` set
     */
    public HashSet difference(HashSet other) {
        String[] arr = this.toArray();
        String[] arr2 = other.toArray();

        HashSet result = new HashSet();
        for (String str : arr) {
            result.add(str);
        }
        
        for (String str : arr2) {
            result.remove(str);
        }

        return result;
    }

    /** 
     * Check if the current set is a subset of the `other` set
     * @param other the set to check for a subset
     * @return a boolean indicating whether the current set is a subset of the `other` set
     */
    public boolean subset(HashSet otherSet) {
        for (int i = 0; i < this.capacity; i++) {
            if (this.data[i] != null) {
                for (String element : this.data[i]) {
                    if (!otherSet.contains(element)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
  
}
