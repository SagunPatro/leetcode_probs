class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        int[] s = {0,0};
        int[] d = {n-1, n-1};

        if (grid[0][0] == 1 || grid[n-1][n-1] == 1) {
            return -1;
        }

        if(s[0] == d[0] && s[1] == d[1]) {
            return 1;
        }

        Queue<tuple> q = new LinkedList<>();
        int[][] dist = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], (int) 1e9);
        }

        dist[0][0] = 1;

        q.add(new tuple(1, 0, 0));

        int delr[] = {0, 0, -1, 1, 1, -1, -1, 1};
        int delc[] = {-1, 1, 0, 0, 1, 1, -1, -1};

        while(!q.isEmpty()) {
            tuple it = q.poll();

            if(it.second == n-1 && it.third == n-1) {
                return it.first;
            }

            for(int i = 0; i<8; i++) {
                int nrow = delr[i] + it.second;
                int ncol = delc[i] + it.third;

                if(nrow >= 0 && nrow < n && ncol >= 0 && ncol < n && grid[nrow][ncol] == 0 && dist[nrow][ncol] > it.first + 1) {
                    dist[nrow][ncol] = it.first + 1;
                    q.add(new tuple(dist[nrow][ncol], nrow, ncol));
                }
            }

        }
        return -1;
    }

}

class tuple {
    int first;
    int second;
    int third;

    tuple(int first, int second, int third){
        this.first = first;
        this.second = second;
        this.third = third;
    }
}