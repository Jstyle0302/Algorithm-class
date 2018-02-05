import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        int k = Integer.parseInt(args[0]);
        int n = 0;
        while (!StdIn.isEmpty()) {
            rq.enqueue(StdIn.readString());
            n++;
        }
        if (k > n) {
            throw new java.lang.ArrayIndexOutOfBoundsException();
        }
        while (k > 0) {
            System.out.println(rq.dequeue());
            k--;
        }
    }
}