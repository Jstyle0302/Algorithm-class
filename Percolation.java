import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation{
    
    private boolean[][] opened;
    private int top = 0;
    private int bottom;
    private int size;
    private WeightedQuickUnionUF wqf;
    private int count;
    public Percolation(int n){                // create n-by-n grid, with all sites blocked
        size = n;
        count =0;
        bottom = size*size + 1;    //last one
        wqf = new WeightedQuickUnionUF(size*size +2);    //n*n grids and top and bottom
        opened = new boolean[size][size];     //grids
    }
    
    public    void open(int row, int col){    // open site (row, col) if it is not open already
        
        if (row <= 0 || row > size || col <= 0 || col > size) {
            throw new IllegalArgumentException();
        }
        
        if(opened[row-1][col-1]==false){
        opened[row-1][col-1]=true;
        count++; 
        if (row == 1){
            wqf.union(getQFIndex(row,col),top);
        }
        if(row == size){
            wqf.union(getQFIndex(row,col),bottom);
        }
        if(col >1 && isOpen(row,col-1)){
            wqf.union(getQFIndex(row,col),getQFIndex(row,col-1));
        }
        if(col <size && isOpen(row,col+1)){
            wqf.union(getQFIndex(row,col),getQFIndex(row,col+1));
        }
        if(row >1 && isOpen(row-1,col)){
            wqf.union(getQFIndex(row,col),getQFIndex(row-1,col));
        }
        if(row <size && isOpen(row+1,col)){
            wqf.union(getQFIndex(row,col),getQFIndex(row+1,col));
        }
        }
    }
    
    public boolean isOpen(int row, int col){  // is site (row, col) open?
        if(0<row && row <=size && 0<col && col<=size){
            return opened[row-1][col-1];
        }
        else{
            throw new IllegalArgumentException();
        }
    }
    
    public boolean isFull(int row, int col){  // is site (row, col) full?
        if(0<row && row <=size && 0<col && col<=size){
            if (opened[row-1][col-1]){
                return wqf.connected(top,getQFIndex(row,col));
            }
            else{
                return false;
            }
        }
        
        else{
            throw new IllegalArgumentException();
        }
    }
    
    public     int numberOfOpenSites(){       // number of open sites
        return count;
    }
    
    public boolean percolates(){              // does the system percolate?
        return wqf.connected(top,bottom);
    }
    
    private int getQFIndex(int i, int j){
        return size*(i-1)+j;
    }
        
    public static void main(String[] args){   // test client (optional)       
    }
}