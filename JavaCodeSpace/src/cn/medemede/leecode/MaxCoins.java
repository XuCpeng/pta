package cn.medemede.leecode;

import java.util.Arrays;

public class MaxCoins {

    /**
     * 戳气球
     * <p>递归，备忘录，两侧添加虚拟气球</p>
     */
    int[] nums;
    int[][] memo;

    public int maxCoins(int[] nums) {
        this.memo = new int[nums.length + 2][nums.length + 2];
        for (int[] x : memo) {
            Arrays.fill(x, -1);
        }
        this.nums = new int[nums.length + 2];
        this.nums[0] = 1;
        this.nums[nums.length + 1] = 1;
        System.arraycopy(nums, 0, this.nums, 1, nums.length);
        return getMaxCoins(0, nums.length + 1);
    }

    private int getMaxCoins(int i, int j) {
        int val = nums[i] * nums[j];
        if (i + 2 == j) {
            return val * nums[i + 1];
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        int maxVal = 0;
        // 最后戳破气球k时最大
        for (int k = i + 1; k < j; k++) {
            int tmpVal = getMaxCoins(i, k) + getMaxCoins(k, j) + val * nums[k];
            if (tmpVal > maxVal) {
                maxVal = tmpVal;
            }
        }
        memo[i][j] = maxVal;
        return maxVal;
    }

    /**
     * 戳气球
     * <p>非递归，dp数组，两侧添加虚拟气球，完全根据上面的递归方法改写</p>
     */
    public int maxCoins2(int[] nums) {
        int[][] dp = new int[nums.length + 2][nums.length + 2];
        int[] newNums = new int[nums.length + 2];
        newNums[0] = 1;
        newNums[nums.length + 1] = 1;
        for (int i = 0; i < nums.length; i++) {
            newNums[i + 1] = nums[i];
        }
        for (int i = nums.length; i >= 0; i--) {
            for (int j = i + 1; j <= nums.length + 1; j++) {
                for (int k = i + 1; k < j; k++) {
                    // i<k<j, 所以dp[i][k]在dp[i][j]同一行左侧，dp[k][j]在dp[i][j]同一列下侧
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + newNums[i] * newNums[k] * newNums[j]);
                }
            }
        }
        return dp[0][nums.length + 1];
    }

}

