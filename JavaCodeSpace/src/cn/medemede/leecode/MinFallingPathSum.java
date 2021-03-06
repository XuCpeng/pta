package cn.medemede.leecode;

public class MinFallingPathSum {

    /**
     * 下降路径最下和
     * <p>动态规划</p>
     *
     * @param matrix
     * @return
     */
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int tmp = matrix[i - 1][j];
                if (j - 1 >= 0) {
                    tmp = Math.min(tmp, matrix[i - 1][j - 1]);
                }
                if (j + 1 < n) {
                    tmp = Math.min(tmp, matrix[i - 1][j + 1]);
                }
                matrix[i][j] = matrix[i][j] + tmp;
            }
        }
        int res = matrix[n - 1][0];
        for (int x : matrix[n - 1]) {
            res = Math.min(res, x);
        }
        return res;
    }

}

