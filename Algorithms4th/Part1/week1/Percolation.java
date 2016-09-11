
/**
 * Created by Ming on 16/9/10.
 */
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Percolation {

    private boolean[] opened;
    private int n;
    private int[][] dir = {
        {1, 0}, {-1, 0}, {0, 1}, {0, -1}
    };
    private WeightedQuickUnionUF uf, uf_backwash;

    public Percolation(int n) {
        if(n <= 0) throw new IllegalArgumentException();
        this.n = n;
        opened = new boolean[n*n + 2];

        for (int i = 0; i <= n*n + 1; i++) {
            opened[i] = false;
        }
        opened[0] = true;
        opened[n*n+1] = true;

        uf = new WeightedQuickUnionUF(n*n + 2);
        uf_backwash = new WeightedQuickUnionUF(n*n + 2);
    }

    public void open(int i, int j) {
        if (!isValid(i, j)) throw  new IndexOutOfBoundsException();
        if (isOpen(i, j)) return;
        opened[getIdx(i, j)] = true;
        for (int k = 0; k < 4; k++)
        {
            int next_i = i + dir[k][0];
            int next_j = j + dir[k][1];
            if(isValid(next_i, next_j) && opened[getIdx(next_i, next_j)]){
                uf.union(getIdx(i, j), getIdx(next_i, next_j));
                uf_backwash.union(getIdx(i, j), getIdx(next_i, next_j));
            }
        }
        if (i == 1){
            uf.union(getIdx(i, j), 0);
            uf_backwash.union(getIdx(i, j), 0);
        }
        if(i == n) {
            uf.union(getIdx(i, j), n*n+1);
        }
    }

    public boolean isOpen(int i, int j) {
        if (!isValid(i, j)) throw  new IndexOutOfBoundsException();
        return opened[getIdx(i, j)];
    }

    public boolean isFull(int i, int j) {
        if (!isValid(i, j)) throw  new IndexOutOfBoundsException();
        return uf_backwash.connected(0, getIdx(i, j));
    }

    public boolean percolates() {
        return uf.connected(0, n*n+1);
    }

    private int getIdx(int i, int j) {
        return (i - 1) * n + j;
    }

    private boolean isValid(int i, int j) {
        if (i >= 1 && i <= n && j >= 1 && j <=n)return true;
        return false;
    }

    public static void main(String[] args){
        System.out.println("hello world");
    }
}
