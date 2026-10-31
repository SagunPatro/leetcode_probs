
class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int MOD = 1_000_000_007;

        // right[i][j]: ways to enter (i,j) while moving right
        // down[i][j]: ways to enter (i,j) while moving down
        long[][] right = new long[m][n];
        long[][] down = new long[m][n];

        // Robot starts at (0,0)
        long[][] dp = new long[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                long ways;

                if (i == 0 && j == 0) {
                    ways = 1;
                } else {
                    ways = (right[i][j] + down[i][j]) % MOD;
                }

                // Destination reached
                if (i == m - 1 && j == n - 1) {
                    dp[i][j] = ways;
                    continue;
                }

                if (grid[i][j] == 1) {

                    // Mirror:
                    // Entered from left -> reflected down
                    if (i + 1 < m) {
                        down[i + 1][j] =
                            (down[i + 1][j] + right[i][j]) % MOD;
                    }

                    // Entered from above -> reflected right
                    if (j + 1 < n) {
                        right[i][j + 1] =
                            (right[i][j + 1] + down[i][j]) % MOD;
                    }

                } else {

                    // Empty cell: can move either right or down

                    if (j + 1 < n) {
                        right[i][j + 1] =
                            (right[i][j + 1] + ways) % MOD;
                    }

                    if (i + 1 < m) {
                        down[i + 1][j] =
                            (down[i + 1][j] + ways) % MOD;
                    }
                }
            }
        }

        return (int) dp[m - 1][n - 1];
    }
}
