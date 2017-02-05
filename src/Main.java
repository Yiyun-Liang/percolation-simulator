import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by isa on 2017-02-05.
 */
public class Main {

    // test client (optional)
    public static void main(String[] args){
        // first input tells how n
        In in = new In(args[0]);
        int n = in.readInt();

        Percolation pc = new Percolation(n);

        while(!in.isEmpty()){
            int p = in.readInt();
            int q = in.readInt();

            pc.open(p,q);
        }
    }

}
