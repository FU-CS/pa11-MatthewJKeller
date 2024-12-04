package pa11;

import java.util.LinkedList;

public class HashMap {


    int size = 0;
    int capacity = 17;
    LinkedList<Pair>[] data;
    /**
     *  Constructor for the map
     */
    
    @SuppressWarnings("unchecked")
    public HashMap() {
        System.out.println("HashMap");
        this.data = new LinkedList[this.capacity];
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
     *  Size of the map
     *  @return the number of elements in the map
     */
    public int size() {
        System.out.println("Size");
        return this.size;
    }

    /**
     *  Check if the map is empty
     *  @return a boolean indicating whether the map is empty
     */
    public boolean isEmpty() {
        System.out.println("IsEmpty");
        return this.size == 0;
    }

    /**
     *  Get the value associated with the key
     *  @param key the key to get the value for
     *  @return the value associated with the key, or null if no such entry exists
     */
    public String get(String key) {
        System.out.println("Get " + key);

        int index = this.hashingAlgorithm(key);
        LinkedList<Pair> curr = this.data[index];

        if (curr == null) {
            return null;
        }

        for(int i=0; i<curr.size(); i++){
            if(curr.get(i).getKey().equals(key)){
                return curr.get(i).getValue();
            }
        }
        return null;
    }

    /**
     *  Add an entry to the map
     *  @param key the key to add
     *  @param value the value to add
     *  @return the old value associated with the key, or null if no such entry exists
     */
    public String put(String key, String value) {
        System.out.println("Put " + key + " " + value);

        int index = this.hashingAlgorithm(key);
        
        if (this.data[index] == null) {
            this.data[index] = new LinkedList<>();
        }

        for(int i=0; i<this.data[index].size(); i++){
            if(this.data[index].get(i).getKey().equals(key)){
                String oldVal = this.data[index].get(i).getValue();
                this.data[index].get(i).setVal(value);
                return oldVal;
            }
        }

        this.data[index].add(new Pair(key, value));
        this.size++;
        return null;
    }

    /**
     *  Remove an entry from the map
     *  @param key the key to remove
     *  @return the value associated with the key, or null if no such entry exists
     */
    public String remove(String key) {
        System.out.println("Remove " + key);

        int index = this.hashingAlgorithm(key);
        
        if (this.data[index] == null) {
            return null;
        }

        for(int i=0; i<this.data[index].size(); i++){
            if(this.data[index].get(i).getKey().equals(key)){
                String oldValue = this.data[index].get(i).getValue();
                this.data[index].remove(i);
                this.size--;
                return oldValue;
            }
        }

        return null;
    }

    /**
     *  Get all the keys in the map
     *  @return all the keys stored in the map
     */
    public String[] keySet() {
        System.out.println("KeySet");
        String[] keys = new String[this.size];
        int curr = 0;
        for (int i = 0; i < this.capacity; i++) {
            if (this.data[i] != null) {
                for(int j=0; j<this.data[i].size(); j++){
                    keys[curr] = this.data[i].get(j).getKey();
                    curr++;
                }
            }
        }
        return keys;
    }

    /**
     *  Get all the values in the map
     *  @return all the values stored in the map
     */
    public String[] values() {
        System.out.println("Values");
        String[] values = new String[this.size];
        int curr = 0;
        for (int i = 0; i < this.capacity; i++) {
            if (this.data[i] != null) {
                for (int j = 0; j < this.data[i].size(); j++) {
                    values[curr] = this.data[i].get(j).getValue();
                    curr++;
                }
            }
        }
        return values;
    }
}
