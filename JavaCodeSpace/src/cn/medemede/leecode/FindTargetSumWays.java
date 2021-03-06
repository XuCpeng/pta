package cn.medemede.leecode;

public class FindTargetSumWays {

    /**
     * 目标和
     * <p>动态规划,递推公式</p>
     *
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays(int[] nums, int target) {
        int minvalue = 1000;
        int[] dp = new int[2001];
        dp[nums[0] + minvalue] = 1;
        dp[-nums[0] + minvalue] += 1;
        for (int i = 1; i < nums.length; i++) {
            int[] tmp = new int[2001];
            for (int j = -1000; j < 1001; j++) {
                if (dp[j + minvalue] > 0) {
                    tmp[j - nums[i] + minvalue] += dp[j + minvalue];
                    tmp[j + nums[i] + minvalue] += dp[j + minvalue];
                }
            }
            dp = tmp;
        }
        return dp[target + minvalue];
    }


    /**
     * 目标和
     * <p>动态规划，背包问题，反向dp数组</p>
     * <p>动态规划问题还是要分清“选择”</p>
     *
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays2(int[] nums, int target) {
        int sum = 0;
        for (int x : nums) {
            sum += x;
        }
        if ((sum + target) % 2 != 0) {
            return 0;
        }
        target = (sum + target) / 2;

        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= nums.length; i++) {
            // 非常重要：此处需要倒序遍历，j>=0
            for (int j = target; j >= 0; j--) {
                if (j >= nums[i - 1]) {
                    dp[j] += dp[j - nums[i - 1]];
                }
            }
        }
        return dp[target];
    }

}

