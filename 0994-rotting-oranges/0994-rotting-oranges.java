class Solution {

    public int orangesRotting(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        int fresh = 0;
        int tm = 0;

        // Queue stores: {row, column, time}
        Queue<int[]> q = new LinkedList<>();

        // visited[i][j] = 2 means this orange has already been
        // processed / will become rotten
        int[][] visited = new int[n][m];

        // Put all initially rotten oranges into the queue
        // and count the fresh oranges.
        for(int i = 0; i < n; i++) {

            for(int j = 0; j < m; j++) {

                if(grid[i][j] == 2) {

                    // Store row, column and time
                    q.add(new int[]{i, j, 0});

                    visited[i][j] = 2;
                }

                else if(grid[i][j] == 1) {

                    fresh++;
                }

                else {

                    visited[i][j] = 0;
                }
            }
        }


        // Four possible directions
        int[] drow = {-1, 1, 0, 0};
        int[] dcol = {0, 0, -1, 1};

        // Number of fresh oranges that became rotten
        int count = 0;


        // BFS
        while(!q.isEmpty()) {

            // Get current orange
            int[] curr = q.poll();

            int r = curr[0];
            int c = curr[1];
            int t = curr[2];

            // Keep track of maximum time
            tm = Math.max(tm, t);


            // Check 4 neighbours
            for(int i = 0; i < 4; i++) {

                int nrow = r + drow[i];
                int ncol = c + dcol[i];


                // Check if neighbour is inside the grid
                // and is a fresh orange that hasn't been visited
                if(nrow >= 0 && nrow < n &&
                   ncol >= 0 && ncol < m &&
                   visited[nrow][ncol] == 0 &&
                   grid[nrow][ncol] == 1) {

                    // This fresh orange becomes rotten
                    visited[nrow][ncol] = 2;

                    // It becomes rotten after t + 1 minutes
                    q.add(new int[]{nrow, ncol, t + 1});

                    count++;
                }
            }
        }


        // If some fresh oranges were never rotten,
        // then it is impossible to rot all oranges.
        if(count != fresh) {
            return -1;
        }

        return tm;
    }
}