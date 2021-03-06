package cn.medemede.leecode;

public class MinInsertions {

    /**
     * 让字符串成为回文串的最少插入次数
     * <p>递归，备忘录</p>
     */
    char[] ss;
    int[][] memo;

    public int minInsertions(String s) {
        this.ss = s.toCharArray();
        this.memo = new int[ss.length][ss.length];
        return getMinInsertions(0, ss.length - 1);
    }

    private int getMinInsertions(int i, int j) {
        if (i >= j) {
            return 0;
        }
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        if (ss[i] == ss[j]) {
            memo[i][j] = getMinInsertions(i + 1, j - 1);
        } else {
            memo[i][j] = Math.min(getMinInsertions(i + 1, j), getMinInsertions(i, j - 1)) + 1;
        }

        return memo[i][j];
    }

    /**
     * 让字符串成为回文串的最少插入次数
     * <p>dp数组</p>
     */
    public int minInsertions2(String s) {
        char[] ss = s.toCharArray();
        int[][] dp = new int[ss.length][ss.length];
        for (int i = ss.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < ss.length; j++) {
                if (ss[i] == ss[j]) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        return dp[0][ss.length - 1];
    }


}

