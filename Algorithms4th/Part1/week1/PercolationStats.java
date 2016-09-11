
import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
/**
 * Created by Ming on 16/9/10.
 */
public class PercolationStats {
    private int n;
    private int trials;
    private double[] ret;

    public PercolationStats(int n, int trials) {
        if(n <= 0 || trials <= 0)throw new IllegalArgumentException();
        this.n = n;
        this.trials = trials;
        ret = new double[trials];
        doExperiment();
    }

    public double mean() {
        return StdStats.mean(ret);
    }

    public double stddev() {
        return StdStats.stddev(ret);
    }

    public double confidenceLo() {
        double mean = mean();
        double stdVar = stddev();
        return mean - 1.96*stdVar/Math.sqrt(trials);
    }

    public double confidenceHi() {
        double mean = mean();
        double stdVar = stddev();
        return mean + 1.96*stdVar/Math.sqrt(1.0*trials);
    }

    private void doExperiment() {
        for (int k = 0; k < trials; k++){
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()){
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                percolation.open(row, col);
            }

            int cnt = 0;
            for (int i = 1; i <= n; i++){
                for (int j = 1; j <= n; j++)
                {
                    if(percolation.isOpen(i, j)){
                        cnt++;
                    }
                }
            }
            ret[k] = cnt*1.0/(n*n);
        }
    }


    public static void main(String[] args){
        if(args.length != 2) throw new IllegalArgumentException();
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats  percolationStats = new PercolationStats(n, trials);
        System.out.printf("mean                    = %f\n", percolationStats.mean());
        System.out.printf("stddev                  = %f\n", percolationStats.stddev());
        System.out.printf("95%% confidence interval = %f, %f\n",
                percolationStats.confidenceLo(), percolationStats.confidenceHi());
    }
}
