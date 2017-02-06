import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.awt.Font;

/**
 * Created by isa on 2017-02-05.
 */
public class Main {

    private static final int SPEED = 100;

    // http://introcs.cs.princeton.edu/java/stdlib/javadoc/StdDraw.html
    public static void draw(Percolation pc, int n){
        StdDraw.clear();

        // initialize a black canvas
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setXscale(0, n);
        StdDraw.setYscale(0, n+1);

        // Draws a filled square of the specified size, centered at (x, y).
        StdDraw.filledSquare(n/2.0, n/2.0, n/2.0);

        // draw n-by-n grid
        int opened = 0;
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= n; col++) {
                if (pc.isFull(row, col)) {
                    StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
                    opened++;
                }else if (pc.isOpen(row, col)) {
                    StdDraw.setPenColor(StdDraw.WHITE);
                    opened++;
                }else{
                    StdDraw.setPenColor(StdDraw.BLACK);
                }

                // Draws a filled square of the specified size, centered at (x, y).
                StdDraw.filledSquare(col - 0.5, n - row + 0.5, 0.45);
            }
        }

        // Write result
        StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 12));
        StdDraw.setPenColor(StdDraw.BLACK);
        // Write the given text string in the current font, centered at (x, y).
        //StdDraw.text(0.25*n, -n*0.025, opened + " open sites");
        StdDraw.textLeft(0.5, n+0.7, opened + "/" + n*n);
        if (pc.percolates()){
            StdDraw.textLeft(0.5, n+0.3, "percolates");
        }
        else{
            StdDraw.textLeft(0.5, n+0.3, "does not percolate");
        }
    }

    // test client (optional)
    public static void main(String[] args){
        // first input tells how n
        In in = new In(args[0]);
        int n = in.readInt();

        // turn on animation mode
        StdDraw.show(0);

        Percolation pc = new Percolation(n);
        draw(pc, n);
        StdDraw.show(SPEED);

        while(!in.isEmpty()){
            int p = in.readInt();
            int q = in.readInt();

            pc.open(p,q);
            draw(pc, n);
            StdDraw.show(SPEED);
        }

    }

}
