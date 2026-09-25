class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        int [][] visited = new int[n][m];
        int [][] distance = new int[n][m];

        Queue<int []> q = new LinkedList<>();
        for(int i = 0; i<n; i++) {
            for(int j = 0; j<m; j++) {
                if(mat[i][j] == 0) {
                    q.add(new int[]{i, j, 0});
                    visited[i][j] = 1;
                }
                else {
                    visited[i][j] = 0;
                }
            }
        }

        int delrow[] = {-1, 1, 0, 0};
        int delcol[] = {0, 0, -1, 1};

        while(!q.isEmpty()) {
            int[] curr = q.poll();
            int row = curr[0];
            int col = curr[1];
            int steps = curr[2];

            distance[row][col] = steps;

            for(int i = 0; i<4; i++) {
                int nrow = row + delrow[i];
                int ncol = col + delcol[i];

                if(nrow >= 0 && nrow < n && ncol >= 0 && ncol < m && visited[nrow][ncol] == 0) {
                    visited[nrow][ncol] = 1;
                    q.add(new int[]{nrow, ncol, steps+1});
                }
            }
        }
        return distance;

    }
}