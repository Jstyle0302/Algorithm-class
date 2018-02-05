import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
   
   private int experimentsCount;
   private Percolation pc;
   private double[] fractions;
   private static double CONFIDENCE_95 = 1.96;    //CONFIDENCE for 95% interval
   
   public PercolationStats(int n, int trials){    // perform trials independent experiments on an n-by-n grid
       if(n<=0 || trials<=0){
           throw new IllegalArgumentException();
       }
       experimentsCount = trials;
       fractions = new double[experimentsCount];
       
       for(int i=0;i<experimentsCount;i++){
           pc = new Percolation(n);
           int openedNum = 0;
           while(!pc.percolates()){
               int row = StdRandom.uniform(1,n+1);
               int col = StdRandom.uniform(1,n+1);
               if(!pc.isOpen(row,col)){
                   pc.open(row,col);
                   openedNum++;
               }
           double fraction = (double) openedNum/(n*n);
           fractions[i]= fraction;
           }
       }
   }
   
   public double mean(){                          // sample mean of percolation threshold
       return StdStats.mean(fractions);
   }
   
   public double stddev(){                        // sample standard deviation of percolation threshold
       return StdStats.stddev(fractions);
   }
   
   public double confidenceLo(){                  // low  endpoint of 95% confidence interval
       return mean()-((CONFIDENCE_95 *stddev())/Math.sqrt(experimentsCount));
   }
   
   public double confidenceHi(){                  // high endpoint of 95% confidence interval
       return mean()+((CONFIDENCE_95 *stddev())/Math.sqrt(experimentsCount));
   }

   public static void main(String[] args){        // test client (described below)
       int n = Integer.parseInt(args[1]);
       int trials = Integer.parseInt(args[2]);
       PercolationStats pcs = new PercolationStats(n,trials);
       
       String confidence = pcs.confidenceLo() + ", " + pcs.confidenceHi();
       System.out.println("mean                    = " + pcs.mean());
       System.out.println("stddev                  = " + pcs.stddev());
       System.out.println("95% confidence interval = [" + confidence+"]");
   }
}