class Solution {
    public int numIslands(char[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        int count = 0;

        int[][] visited = new int[n][m];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {

                if(visited[i][j] == 0 && grid[i][j] == '1') {
                    count++;
                    bfs(i, j, visited, grid);
                }
            }
        }

        return count;
    }

    public void bfs(int i, int j, int[][] visited, char[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        Queue<int[]> q = new LinkedList<>();

        q.add(new int[]{i, j});
        visited[i][j] = 1;

        // 4 directions: up, down, left, right
        int[] delr = {-1, 1, 0, 0};
        int[] delc = {0, 0, -1, 1};

        while(!q.isEmpty()) {

            int[] curr = q.poll();

            int row = curr[0];
            int col = curr[1];

            for(int k = 0; k < 4; k++) {

                int nrow = row + delr[k];
                int ncol = col + delc[k];

                // Check boundaries
                if(nrow >= 0 && nrow < n &&
                   ncol >= 0 && ncol < m &&
                   grid[nrow][ncol] == '1' &&
                   visited[nrow][ncol] == 0) {

                    visited[nrow][ncol] = 1;

                    q.add(new int[]{nrow, ncol});
                }
            }
        }
    }
}