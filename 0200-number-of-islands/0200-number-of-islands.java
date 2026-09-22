class Solution {
    public int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int count = 0;

        int [][] visited = new int[n][m];
        for(int i = 0; i<n; i++) {
            for(int j = 0; j<m; j++) {
                if(visited[i][j] == 0 && grid[i][j] == '1') {
                    count++;
                    dfs(i, j, visited, grid);
                }
            }
        }
        return count;
    }

    public void dfs(int i, int j, int[][] visited, char[][] grid) {
        visited[i][j] = 1;

        int n = grid.length;
        int m = grid[0].length;

        int[] delr = {-1, 1, 0, 0};
        int[] delc = {0, 0, -1, 1};

        for(int k = 0; k<4; k++) {
            int nrow = i + delr[k];
            int ncol = j + delc[k];

            if(nrow >=0 && nrow <= n-1 && ncol >= 0 && ncol <= m-1 && visited[nrow][ncol] == 0 && grid[nrow][ncol] == '1') {
                visited[nrow][ncol] = 1;

                dfs(nrow, ncol, visited, grid);
            }
        }
    }
}