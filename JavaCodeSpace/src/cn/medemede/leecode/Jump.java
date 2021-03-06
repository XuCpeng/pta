package cn.medemede.leecode;

public class Jump {

    /**
     * 跳跃游戏 II
     * <p>贪心</p>
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        //可跳的最远位置
        int right = 0;
        //跳的步数
        int jumps = 0;
        //上一步可达的最远位置
        int end = 0;

        // 到达nums.length - 1就不再跳了，所以无需也不能计算nums.length - 1
        for (int i = 0; i < nums.length - 1; i++) {
            if (i + nums[i] > right) {
                right = i + nums[i];
            }
            if (i == end) {
                jumps++;
                end = right;
            }
        }
        return jumps;
    }

}

