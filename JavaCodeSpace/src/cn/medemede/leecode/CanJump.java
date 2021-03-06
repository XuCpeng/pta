package cn.medemede.leecode;

public class CanJump {

    /**
     * 跳跃游戏
     * <p>经典贪心算法！！！记录可到达的最远位置即可</p>
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int n = nums.length - 1;
        int right = 0;
        // 此处条件，right>=n时可直接返回ture，i<=right才可到达
        for (int i = 0; right < n && i <= right; i++) {
            right = Math.max(right, nums[i] + i);
        }
        return right >= n;
    }

}

