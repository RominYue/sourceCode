package algorithms.week4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by DELL on 2016/9/27.
 */
public class Board {

    private int[][] blocks = null;
    private int n;
    private int[][] dir = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1}
    };
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks){
        this.blocks = deepCopy(blocks);
        n = blocks.length;
    }

    // board dimension n
    public int dimension(){
        return n;
    }

    // number of blocks out of place
    public int hamming(){
        int cnt = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if (blocks[i][j] != 0 && blocks[i][j] != getIdx(i,j))
                    cnt++;
            }
        }
        return cnt;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan(){
        int cnt = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(blocks[i][j]  == 0)continue;
                int[] res = getLoc(blocks[i][j]);
                cnt += Math.abs(res[0] - i) + Math.abs(res[1] - j);
            }
        }
        return cnt;
    }

    // is this board the goal board?
    public boolean isGoal(){
        return hamming() == 0;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin(){
        int[][] copyed = deepCopy(blocks);
        boolean exchange = false;
        int pre_i = -1, pre_j = -1;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(copyed[i][j] == 0)continue;
                if(pre_i == -1 && pre_j == -1)
                {
                    pre_i = i;
                    pre_j = j;
                }else{
                    int tmp = copyed[i][j];
                    copyed[i][j] = copyed[pre_i][pre_j];
                    copyed[pre_i][pre_j] = tmp;
                    exchange = true;
                    break;
                }
            }
            if (exchange)break;
        }

        return new Board(copyed);
    }

    // does this board equal y?
    public boolean equals(Object y){
        if (this == y) {
            return true;
        }
        if (y == null) {
            return false;
        }
        if (this.getClass() != y.getClass()) {
            return false;
        }
        Board that = (Board) y;
        if (this.n != that.n) {
            return false;
        }
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (this.blocks[row][col] != that.blocks[row][col]) {
                    return false;
                }
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors(){
        List<Board> list = new ArrayList<>();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(blocks[i][j] == 0){
                    for(int k = 0; k < 4; k++){
                        int x = i + dir[k][0];
                        int y = j + dir[k][1];
                        if (!isValid(x,y))continue;
                        //exchange
                        int[][] next = deepCopy(blocks);
                        next[i][j] = next[x][y];
                        next[x][y] = 0;
                        list.add(new Board(next));
                    }
                }
            }
        }
        return list;
    }

    // string representation of this board (in the output format specified below)
    public String toString(){
        StringBuilder sb = new StringBuilder(String.format("%d\n",n));
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(j == 0)sb.append(String.format("%2d",blocks[i][j]));
                else sb.append(String.format(" %2d",blocks[i][j]));
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    private int getIdx(int i, int j){
        return i*n + j + 1;
    }

    private int[] getLoc(int idx){
        int[] res = new int[2];
        res[0] = (idx-1)/n;
        res[1] = idx - res[0]*n - 1;
        return res;
    }

    private boolean isValid(int i, int j){
        if(i >= 0 && i < n && j >= 0 && j < n)return true;
        return false;
    }

    private int[][] deepCopy(int[][] blocks){
        int[][] ret = new int[blocks.length][];
        for(int i = 0; i < blocks.length; i++){
            ret[i] = Arrays.copyOf(blocks[i], blocks.length);
        }
        return ret;
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

        StdOut.println(initial);
        StdOut.println(initial.twin());
        StdOut.println(initial);
        StdOut.println(initial.hamming());
        StdOut.println(initial.manhattan());
        for (Board x: initial.neighbors()){
            StdOut.println(x);
            StdOut.println(x.isGoal());
        }
    }
}
