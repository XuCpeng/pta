package cn.medemede.leecode;

import java.util.HashMap;

public class IsPossible {

    /**
     * 分割数组为连续子序列
     *
     * @param nums
     * @return
     */
    public boolean isPossible(int[] nums) {
        HashMap<Integer, Integer> need = new HashMap<>();
        HashMap<Integer, Integer> freq = new HashMap<>();
        for (int x : nums) {
            freq.put(x, freq.getOrDefault(x, 0) + 1);
        }

        for (int x : nums) {
            if (freq.getOrDefault(x, -1) == 0) {
                continue;
            }
            if (need.getOrDefault(x, -1) > 0) {
                need.put(x, need.get(x) - 1);
                freq.put(x, freq.get(x) - 1);
                need.put(x + 1, need.getOrDefault(x + 1, 0) + 1);
            } else if (freq.getOrDefault(x + 1, -1) > 0 && freq.getOrDefault(x + 2, -1) > 0) {
                freq.put(x, freq.get(x) - 1);
                freq.put(x + 1, freq.get(x + 1) - 1);
                freq.put(x + 1, freq.get(x + 1) - 1);
                need.put(x + 3, need.getOrDefault(x + 3, 0) + 1);
            } else {
                return false;
            }
        }
        return true;
    }


}

