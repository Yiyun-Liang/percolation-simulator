import edu.princeton.cs.algs4.*;

/**
 * Created by isa on 2017-02-04.
 */
public class Percolation {
    private int n;
    private int numOpenSites;
    private int totalSites;
    private boolean[] isOpenArr;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF adjustor;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n){
        if(n <= 0){
            throw new IllegalArgumentException();
        }

        totalSites = n*n;

        // this is an array
        uf = new WeightedQuickUnionUF(totalSites+2);
        adjustor = new WeightedQuickUnionUF(totalSites+1);
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
        if(row < 1 || row > n || col < 1 || col > n){
            throw new IndexOutOfBoundsException();
        }

        int index = (row-1) * n + col;

        if(isOpen(row, col)){
            return;
        }

        isOpenArr[index] = true;
        numOpenSites++;

        // connect with neighbors if any neighbors are open
        // left and right
        if(col != 1 && isOpen(row, col-1)){
            uf.union(index-1, index);
            adjustor.union(index-1, index);
        }
        if(col != n && isOpen(row, col+1)){
            uf.union(index+1, index);
            adjustor.union(index+1, index);
        }
        // top and bottom;
        if(row != 1 && isOpen(row-1, col)){
            uf.union(index-n, index);
            adjustor.union(index-n, index);
        }
        if(row != n && isOpen(row+1, col)){
            uf.union(index+n, index);
            adjustor.union(index+n, index);
        }

        // connect to top or bottom virtual node if any top row nodes or bottom row nodes are open now
        if(index <= n){
            uf.union(0, index);
            adjustor.union(0, index);
        }
        if(index > totalSites - n){
            uf.union(totalSites+1, index);
        }
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col){
        if(row < 1 || row > n || col < 1 || col > n){
            throw new IndexOutOfBoundsException();
        }

        int index = (row-1) * n + col;

        return isOpenArr[index];
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col){
        if(row < 1 || row > n || col < 1 || col > n){
            throw new IndexOutOfBoundsException();
        }

        int index = (row-1) * n + col;

        return uf.connected(0, index) && adjustor.connected(0, index);
    }

    // number of open sites
    public int numOfOpenSites(){
        return numOpenSites;
    }

    // does the system percolate?
    public boolean percolates(){
        return uf.connected(0, totalSites+1);
    }

}
