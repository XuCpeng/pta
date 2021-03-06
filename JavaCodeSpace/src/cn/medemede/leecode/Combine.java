package cn.medemede.leecode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Combine {
    List<List<Integer>> res;

    /**
     * [1..n]的所有k个数的组合
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        this.res = new ArrayList<>();
        getCombine(n, k, 1, new LinkedList<>());
        return res;
    }

    private void getCombine(int n, int k, int i, LinkedList<Integer> track) {
        if (track.size() == k) {
            res.add(new LinkedList<>(track));
            return;
        }
        if (i > n || track.size() + (n - i) + 1 < k) {
            return;
        }

        track.addLast(i);
        getCombine(n, k, i + 1, track);
        track.removeLast();
        getCombine(n, k, i + 1, track);
    }


}

