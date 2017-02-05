import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.IllegalFormatCodePointException;

/**
 * Created by isa on 2017-02-04.
 */
public class PercolationStats {
    private int n;
    private double[] nums;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials){
        if(n <= 0 || trials <= 0){
            throw new IllegalArgumentException();
        }
        this.n = n;
        int t = trials;
        nums = new double[trials];

        for(int i = 0; i < t; i++){
            Percolation pc = new Percolation(n);
            int count = 0;
            while(!pc.percolates()){
                int row = StdRandom.uniform(1, n+1);
                int col = StdRandom.uniform(1, n+1);
                if(!pc.isOpen(row, col)){
                    pc.open(row, col);
                }
                count++;
            }
            nums[i] = (double)count/n*n;
        }
    }

    // sample mean of percolation threshold
    public double mean(){
        return StdStats.mean(nums);
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        return StdStats.stddev(nums);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo(){
        return mean() - 1.96*stddev()/Math.sqrt(nums.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        return mean() + 1.96*stddev()/Math.sqrt(nums.length);
    }

    // test client
    // takes two commandline arguments, n and T
    // performs T independent computational experiments on an n-by-n grid
    public static void main(String[] args){

    }
}
