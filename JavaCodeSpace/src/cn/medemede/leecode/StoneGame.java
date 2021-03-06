package cn.medemede.leecode;

import java.util.Arrays;

public class StoneGame {

    /**
     * 石子游戏
     * <p>自己想的方法：递归，每次统计两个人的选择结果，返回先手优势最大的分数</p>
     *
     * @param piles
     * @return
     */
    int[][] memo;

    public boolean stoneGame(int[] piles) {
        this.memo = new int[piles.length][piles.length];
        for (int[] x : memo) {
            Arrays.fill(x, Integer.MAX_VALUE);
        }
        return getStoneGame(piles, 0, piles.length - 1) > 0;
    }

    private int getStoneGame(int[] piles, int i, int j) {
        if (i > j) {
            return 0;
        }
        if (memo[i][j] != Integer.MAX_VALUE) {
            return memo[i][j];
        }
        int a = Math.abs(piles[i] - piles[j]) + getStoneGame(piles, i + 1, j - 1);
        int b = piles[i] - piles[i + 1] + getStoneGame(piles, i + 2, j);
        int c = piles[j] - piles[j - 1] + getStoneGame(piles, i, j - 2);
        memo[i][j] = Math.max(a, Math.max(b, c));
        return memo[i][j];
    }

    /**
     * 石子游戏
     * <p>自己想的方法：dp数组，每次统计两个人的选择结果，返回先手优势最大的分数。完全根据上面的递归修改</p>
     *
     * @param piles
     * @return
     */
    public boolean stoneGame2(int[] piles) {
        int[][] dp = new int[piles.length][piles.length];

        for (int i = piles.length - 3; i >= 0; i--) {
            for (int j = 2; j < piles.length; j++) {
                int a = Math.abs(piles[i] - piles[j]) + dp[i + 1][j - 1];
                int b = piles[i] - piles[i + 1] + dp[i + 2][j];
                int c = piles[j] - piles[j - 1] + dp[i][j - 2];
                dp[i][j] = Math.max(a, Math.max(b, c));
            }
        }
        return dp[0][piles.length - 1] > 0;
    }

    /**
     * 石子游戏
     * <p>官方解法：dp数组，每次统计一个人的选择结果，返回石子数量之差。还可继续压缩状态</p>
     *
     * @param piles
     * @return
     */
    public boolean stoneGame3(int[] piles) {
        int length = piles.length;
        int[][] dp = new int[length][length];
        for (int i = 0; i < length; i++) {
            dp[i][i] = piles[i];
        }
        for (int i = length - 2; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]);
            }
        }
        return dp[0][length - 1] > 0;
    }

}

