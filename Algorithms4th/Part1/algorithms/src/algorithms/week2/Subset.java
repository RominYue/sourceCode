package algorithms.week2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by DELL on 2016/9/17.
 */
public class Subset {
    public static void main(String[] args){
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> queue = new RandomizedQueue<>();

        int cnt = 0;
        while (!StdIn.isEmpty()) {
            String x = StdIn.readString();
            if(cnt >= k)
            {
                int num = StdRandom.uniform(cnt + 1);
                if(num < k)
                {
                    queue.dequeue();
                    queue.enqueue(x);
                }
            }else {
                queue.enqueue(x);
            }
            cnt += 1;
        }

        for(String x: queue){
            System.out.println(x);
        }
    }
}
