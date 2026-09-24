class Solution {
    public int numEnclaves(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        int[][] visited = new int[n][m];

        // Queue stores {row, col}
        Queue<int[]> q = new LinkedList<>();

        // Put all boundary land cells into the queue
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if ((i == 0 || j == 0 || i == n - 1 || j == m - 1)
                        && grid[i][j] == 1) {

                    visited[i][j] = 1;
                    q.add(new int[]{i, j});
                }
            }
        }

        int[] delrow = {-1, 1, 0, 0};
        int[] delcol = {0, 0, -1, 1};

        // BFS
        while (!q.isEmpty()) {

            int[] current = q.poll();

            int row = current[0];
            int col = current[1];

            for (int i = 0; i < 4; i++) {

                int nrow = row + delrow[i];
                int ncol = col + delcol[i];

                if (nrow >= 0 && nrow < n &&
                    ncol >= 0 && ncol < m &&
                    visited[nrow][ncol] == 0 &&
                    grid[nrow][ncol] == 1) {

                    visited[nrow][ncol] = 1;

                    q.add(new int[]{nrow, ncol});
                }
            }
        }

        // Count land cells that were NOT reachable from boundary
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (grid[i][j] == 1 && visited[i][j] == 0) {
                    count++;
                }
            }
        }

        return count;
    }
}

