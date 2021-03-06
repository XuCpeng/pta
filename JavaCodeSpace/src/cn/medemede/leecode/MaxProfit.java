package cn.medemede.leecode;

public class MaxProfit {

    /**
     * 买卖股票的最佳时机，一次
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int minPrice = prices[0];
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] - minPrice > max) {
                max = prices[i] - minPrice;
            } else if (prices[i] < minPrice) {
                minPrice = prices[i];
            }
        }
        return max;
    }

    /**
     * 买卖股票的最佳时机，无限次
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        int profit = 0;
        int i = 0;
        while (i < prices.length) {
            while (i + 1 < prices.length && prices[i + 1] <= prices[i]) {
                i++;
            }
            int j = i + 1;
            while (j + 1 < prices.length && prices[j + 1] >= prices[j]) {
                j++;
            }
            if (i < prices.length && j < prices.length) {
                profit += prices[j] - prices[i];
            }
            i = j + 1;
        }
        return profit;
    }

    /**
     * 买卖股票的最佳时机，K次
     * <p>
     * 状态转移方程：
     * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
     * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
     *
     * @param prices
     * @return
     */
    public int maxProfit4(int k, int[] prices) {
        if (prices.length < 2 || k < 1) {
            return 0;
        }
        int[][][] dp = new int[prices.length][k][2];

        // base case: i==0时（即第一天时），若持有股票只能是-prices[0]
        for (int j = 0; j < k; j++) {
            dp[0][j][1] = -prices[0];
        }
        for (int i = 1; i < prices.length; i++) {
            for (int j = 0; j < k; j++) {
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                if (j == 0) {
                    // base case: dp[i>0][0][1]时(非第一天，第一次买卖，且持有)
                    dp[i][0][1] = Math.max(dp[i - 1][0][1], -prices[i]);
                } else {
                    dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
                }
            }
        }
        return dp[prices.length - 1][k - 1][0];
    }

    /**
     * 买卖股票的最佳时机+冷冻期，无限次
     *
     * @param prices
     * @return
     */
    public int maxProfit5(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int dpi0 = Math.max(0, prices[1] - prices[0]);
        int dpi1 = Math.max(-prices[0], -prices[1]);
        int dpi20 = 0;
        for (int i = 2; i < prices.length; i++) {
            int tmp = dpi0;
            dpi0 = Math.max(dpi0, dpi1 + prices[i]);

            // 即dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i]);
            dpi1 = Math.max(dpi1, dpi20 - prices[i]);

            dpi20 = tmp;
        }
        return dpi0;
    }

    /**
     * 买卖股票的最佳时机+手续费，无限次
     *
     * @param prices
     * @return
     */
    public int maxProfit6(int[] prices, int fee) {
        if (prices.length < 2) {
            return 0;
        }

        int dpi0 = 0;
        int dpi1 = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            dpi0 = Math.max(dpi0, dpi1 + prices[i] - fee);
            dpi1 = Math.max(dpi1, dpi0 - prices[i]);
        }
        return dpi0;
    }

    /**
     * 买卖股票的最佳时机，两次
     * <p>
     * base case：
     * dp[-1][k][0] = dp[i][0][0] = 0
     * dp[-1][k][1] = dp[i][0][1] = -infinity
     * <p>
     * 状态转移方程：
     * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
     * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
     *
     * @param prices
     * @return
     */

    public int maxProfit30(int[] prices) {
        int[][][] dp = new int[prices.length][3][2];
        dp[0][1][1] = -prices[0];
        dp[0][2][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][1][0] = Math.max(dp[i - 1][1][0], dp[i - 1][1][1] + prices[i]);
            dp[i][1][1] = Math.max(dp[i - 1][1][1], -prices[i]);

            dp[i][2][0] = Math.max(dp[i - 1][2][0], dp[i - 1][2][1] + prices[i]);
            dp[i][2][1] = Math.max(dp[i - 1][2][1], dp[i - 1][1][0] - prices[i]);
        }
        return dp[prices.length - 1][2][0];
    }

    public int maxProfit31(int[] prices) {
        int dpi10 = 0;
        int dpi11 = -prices[0];
        int dpi20 = 0;
        int dpi21 = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            int tmp = dpi10;
            dpi10 = Math.max(dpi10, dpi11 + prices[i]);
            dpi11 = Math.max(dpi11, -prices[i]);
            dpi20 = Math.max(dpi20, dpi21 + prices[i]);
            dpi21 = Math.max(dpi21, tmp - prices[i]);
        }
        return dpi20;
    }

}

