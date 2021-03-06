package cn.medemede.leecode;

public class CalculateMinimumHP {

    int[][] memo;

    /**
     * 地下城游戏
     * <p>动态规划，右下角倒序</p>
     *
     * @param dungeon
     * @return
     */
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        int[][] dp = new int[m][n];
        if (dungeon[m - 1][n - 1] < 0) {
            dp[m - 1][n - 1] = 1 - dungeon[m - 1][n - 1];
        } else {
            dp[m - 1][n - 1] = 1;
        }
        for (int i = m - 2; i >= 0; i--) {
            int need = dungeon[i][n - 1] - dp[i + 1][n - 1];
            if (need < 0) {
                dp[i][n - 1] = -need;
            } else {
                dp[i][n - 1] = 1;
            }
        }
        for (int i = n - 2; i >= 0; i--) {
            int need = dungeon[m - 1][i] - dp[m - 1][i + 1];
            if (need < 0) {
                dp[m - 1][i] = -need;
            } else {
                dp[m - 1][i] = 1;
            }
        }
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                int need = dungeon[i][j] - Math.min(dp[i + 1][j], dp[i][j + 1]);
                if (need < 0) {
                    dp[i][j] = -need;
                } else {
                    dp[i][j] = 1;
                }
            }
        }
        return dp[0][0];
    }


    /**
     * 地下城游戏
     * <p>动态规划，递归，备忘录</p>
     *
     * @param dungeon
     * @return
     */
    public int calculateMinimumHP2(int[][] dungeon) {
        this.memo = new int[dungeon.length][dungeon[0].length];
        return getCalculateMinimumHP2(dungeon, 0, 0);
    }

    private int getCalculateMinimumHP2(int[][] dungeon, int i, int j) {
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        int next = 1;
        if (i != dungeon.length - 1 && j != dungeon[0].length - 1) {
            next = Math.min(getCalculateMinimumHP2(dungeon, i + 1, j), getCalculateMinimumHP2(dungeon, i, j + 1));
        } else if (i != dungeon.length - 1) {
            next = getCalculateMinimumHP2(dungeon, i + 1, j);
        } else if (j != dungeon[0].length - 1) {
            next = getCalculateMinimumHP2(dungeon, i, j + 1);
        }
        int need = dungeon[i][j] - next;
        if (need < 0) {
            memo[i][j] = -need;
        } else {
            memo[i][j] = 1;
        }
        return memo[i][j];
    }

}

