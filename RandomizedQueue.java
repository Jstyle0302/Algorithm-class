import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
   
   private int initSize = 1;
   private int queueSize = 0;
   private Item[] queue;
   
   public RandomizedQueue() {                 // construct an empty randomized queue
       queue = (Item[]) new Object[initSize];
   }
   public boolean isEmpty() {                 // is the randomized queue empty?
       return queueSize == 0;
   }
   public int size() {                        // return the number of items on the randomized queue
       return queueSize;
   }
   private void resize(int size) {
       Item[] temp = (Item[]) new Object[size];
       for (int i = 0; i < queueSize; i++)
           temp[i] = queue[i];
       queue = temp;
   }
   public void enqueue(Item item) {           // add the item
       if (item == null) {
           throw new java.lang.IllegalArgumentException();
       }
       if (queueSize == queue.length) {
           resize(2*queue.length);
       }
       queue[queueSize++] = item;
   }
   public Item dequeue() {                    // remove and return a random item
       if (isEmpty()) {
           throw new java.util.NoSuchElementException();
       }
       int randomIndex = StdRandom.uniform(queueSize);
       Item item = queue[randomIndex];
       
       if (randomIndex != queueSize) {
           queue[randomIndex] = queue[queueSize-1];
       }
       queue[queueSize-1] = null;
       queueSize--;
       
       if (queueSize < queue.length/4) {
           resize(queue.length/2);
       }
       return item;
   }
   public Item sample() {                     // return a random item (but do not remove it)
       if (isEmpty()) {
           throw new java.util.NoSuchElementException();
       }
       int randomIndex = StdRandom.uniform(queueSize);
       return queue[randomIndex];
       
   }
   private class ArrayIterator implements Iterator<Item> {
       private int index = 0;
       private int[] randomInt;
       
       public ArrayIterator() {
           randomInt = new int[queueSize];
           for (int i = 0; i < randomInt.length; i++)
               randomInt[i] = i;
           StdRandom.shuffle(randomInt);
       }
       
       public boolean hasNext() {
           return (index < queue.length);
       }
       public Item next() {
           if (!hasNext()) {
               throw new java.util.NoSuchElementException();
           }
           int randomIndex = randomInt[index];
           index++;
           return queue[randomIndex];
       }
       public void remove() {
           throw new java.lang.UnsupportedOperationException();
       }
   }
   public Iterator<Item> iterator() {         // return an independent iterator over items in random order
       return new ArrayIterator();
   }
   public static void main(String[] args) {   // unit testing (optional)
   }
}