import edu.princeton.cs.algs4.*;

/**
 * Created by isa on 2017-02-04.
 */
public class Percolation {
    private int n;
    private int numOpenSites;
    private int totalSites;
    private boolean[] isOpenArr;
    private WeightedQuickUnionUF grid;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n){
        if(n <= 0){
            throw new IllegalArgumentException();
        }

        totalSites = n*n;

        // this is an array
        grid = new WeightedQuickUnionUF(totalSites+2);
        this.n = n;
        numOpenSites = 0;

        // all sites are closed at the beginning, virtual top and bottom are open
        isOpenArr = new boolean[totalSites+2];
        for(int i = 1; i <= totalSites; i++){
            isOpenArr[i] = false;
        }
        isOpenArr[0] = true;
        isOpenArr[totalSites+1] = true;
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col){
        if(row < 1 || row > n || col < 1 || row > n){
            throw new IndexOutOfBoundsException();
        }

        int index = (row-1) * n + col;

        if(!isOpen(row, col)){
            isOpenArr[index] = true;
            numOpenSites++;
        }

        // connect with neighbors if any neighbors are open
        // left and right
        if(isOpen(row, col-1)){
            grid.union(index-1, index);
        }
        if(isOpen(row, col+1)){
            grid.union(index+1, index);
        }
        // top and bottom;
        if(isOpen(row-1, col)){
            grid.union(index-n, index);
        }
        if(isOpen(row+1, col)){
            grid.union(index+n, index);
        }

        // connect to top or bottom virtual node if any top row nodes or bottom row nodes are open now
        if(index <= n){
            grid.union(0, index);
        }
        if(index >= totalSites - 2){
            grid.union(totalSites+1, index);
        }
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col){
        if(row < 1 || row > n || col < 1 || row > n){
            throw new IndexOutOfBoundsException();
        }

        int index = (row-1) * n + col;

        return isOpenArr[index];
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col){
        if(row < 1 || row > n || col < 1 || row > n){
            throw new IndexOutOfBoundsException();
        }

        int index = (row-1) * n + col;

        return grid.connected(0, index);
    }

    // number of open sites
    public int numOfOpenSites(){
        return numOpenSites;
    }

    // does the system percolate?
    public boolean percolates(){
        return grid.connected(0, totalSites+1);
    }

}
