package algorithms.week4;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
/**
 * Created by DELL on 2016/9/27.
 */
public class Solver {
    private int step = -1;
    private Stack<Board> boardStack = null;
    private boolean isSolved = false;
    private MinPQ<State> pq;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial){
        if (initial == null) throw new NullPointerException();

        boardStack = new Stack<>();
        pq = new MinPQ<>();
        pq.insert(new State(null, 0, initial, false));
        pq.insert(new State(null, 0, initial.twin(), true));
        solve();
    }

    // is the initial board solvable?
    public boolean isSolvable(){
        return isSolved;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves(){
        if (isSolvable())return step;
        return -1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution(){
        if (boardStack.isEmpty())return null;
        return boardStack;
    }

    private void solve(){
        State t = null;
        while(!pq.isEmpty()){
            t = pq.delMin();
            if(t.cur.isGoal())break;

            for(Board next: t.cur.neighbors()){
                if(t.cur.equals(next))continue;
                if(t.parent != null && t.parent.cur.equals(next))continue;
                pq.insert(new State(t, t.step + 1, next, t.isTwin));
            }
        }

        if (t.isTwin == true){
            step = -1;
            isSolved = false;
        }else {
            step = t.step;
            isSolved = true;
            boardStack.push(t.cur);
            while (t.parent != null){
                boardStack.push(t.parent.cur);
                t = t.parent;
            }
        }
    }

    private class State implements Comparable<State>{
        private State parent;
        private int step;
        private Board cur;
        private boolean isTwin;
        private int priority;

        public State(){}
        public State(State parent, int step, Board cur, boolean isTwin){
            this.parent = parent;
            this.step = step;
            this.cur = cur;
            this.isTwin = isTwin;
            this.priority = step + cur.manhattan();
        }

        @Override
        public int compareTo(State that) {
            if (priority < that.priority)return -1;
            else if(priority == that.priority)return 0;
            return 1;
        }
    }

    public static void main(String[] args){
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
