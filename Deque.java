import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    
   private int queueNum;
   private Node first, last;
   private class Node {
       private Item item;
       private Node front;
       private Node next;
   }
   public Deque() {                          // construct an empty deque
       queueNum = 0;
       first = null;
       last = null;
   }
   public boolean isEmpty() {                 // is the deque empty?
       return queueNum == 0;
   }
   public int size() {                        // return the number of items on the deque
       return queueNum;
   }
   public void addFirst(Item item) {          // add the item to the front
       if (item == null) {
           throw new java.lang.IllegalArgumentException();
       }
       
       Node oldFirst = first;
       first = new Node();
       first.item = item;
       first.front = null;
       first.next = oldFirst;
       
       if (isEmpty()) {
           last = first;
       }
       else {
           oldFirst.front = first;
       }
       queueNum++;
   }
   public void addLast(Item item) {           // add the item to the end
       if (item == null) {
           throw new java.lang.IllegalArgumentException();
       }
       
       Node oldLast = last;
       last = new Node();
       last.item = item;
       last.front = oldLast;
       last.next = null;
       if (isEmpty()) {
           first = last;
       }
       else {
           oldLast.next = last;
       }
       queueNum++;
   }
   public Item removeFirst() {                // remove and return the item from the front
       if (queueNum == 0) {
           throw new java.util.NoSuchElementException();
       }
       Item item = first.item;
       first = first.next;
       queueNum--;
       if (!isEmpty()) {
           first.front = null;
       }
       else {
           last = null;
       }
       return item;
   }
   public Item removeLast() {                 // remove and return the item from the end
       if (queueNum == 0) {
           throw new java.util.NoSuchElementException();
       }
       Item item = last.item;
       last = last.front;
       queueNum--;
       if (!isEmpty()) {
           last.next = null;
       }
       else {
           first = null;
       }
       return item;
   }
   private class ListIterator implements Iterator<Item>
   {
       private Node current = first;
       public boolean hasNext() {
           return (current != null);
       }
       public Item next() {
           if (!hasNext()) {
               throw new java.util.NoSuchElementException();
           }
           Item item = current.item;
           current = current.next;
           return item;
       }
       public void remove() {
           throw new java.lang.UnsupportedOperationException();
       }
   }
   public Iterator<Item> iterator() {         // return an iterator over items in order from front to end
       return new ListIterator();
   }
   public static void main(String[] args) {  // unit testing (optional)
       // UncommentedEmptyMethodBody
   }
}