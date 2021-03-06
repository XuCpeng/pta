package cn.medemede.leecode;

import java.util.Arrays;
import java.util.Comparator;

public class EraseOverlapIntervals {

    /**
     * 无重叠区间，返回需要删除的最小区间数量
     * <p>排序,贪心</p>
     *
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));
        int res = 0;
        int right = intervals[0][0];
        for (int[] x : intervals) {
            if (x[0] >= right) {
                right = x[1];
            } else {
                res++;
            }
        }
        return res;
    }

}

