package cn.medemede.leecode;

import java.util.ArrayList;
import java.util.List;

public class SolveNQueens {

    /**
     * N 皇后
     *
     * @param n
     * @return
     */
    List<List<String>> queens;
    int n;
    boolean[] flag;

    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        this.queens = new ArrayList<>();
        this.flag = new boolean[n];
        getSolveNQueens(new ArrayList<>());
        return queens;
    }

    private void getSolveNQueens(ArrayList<String> queen) {
        if (queen.size() == n) {
            queens.add(new ArrayList<>(queen));
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append('.');
        }
        for (int i = 0; i < n; i++) {
            if (canSetQueen(i, queen)) {
                sb.setCharAt(i, 'Q');
                flag[i] = true;
                queen.add(sb.toString());
                getSolveNQueens(queen);
                sb.setCharAt(i, '.');
                flag[i] = false;
                queen.remove(queen.size() - 1);
            }
        }
    }

    private boolean canSetQueen(int i, ArrayList<String> queen) {
        if (flag[i]) {
            return false;
        }
        int p = queen.size() - 1;
        int q = i + 1;
        while (p >= 0 && q < n) {
            if (queen.get(p).charAt(q) == 'Q') {
                return false;
            }
            p--;
            q++;
        }

        p = queen.size() - 1;
        q = i - 1;
        while (p >= 0 && q >= 0) {
            if (queen.get(p).charAt(q) == 'Q') {
                return false;
            }
            p--;
            q--;
        }
        return true;
    }

}

