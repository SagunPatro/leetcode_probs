class Solution {
public:
    int minMoves(vector<string>& classroom, int energy) {

        int m = classroom.size();
        int n = classroom[0].size();

        // Find starting position and number of litter cells
        int sr = 0, sc = 0;
        int litterCount = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (classroom[i][j] == 'S') {
                    sr = i;
                    sc = j;
                }

                if (classroom[i][j] == 'L') {
                    litterCount++;
                }
            }
        }

        // No litter to clean
        if (litterCount == 0)
            return 0;

        // Give each litter cell a unique bit number
        vector<vector<int>> id(m, vector<int>(n, -1));

        int cnt = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (classroom[i][j] == 'L') {
                    id[i][j] = cnt++;
                }
            }
        }

        // All litter collected
        int fullMask = (1 << litterCount) - 1;

        /*
            State:

            r     = row
            c     = column
            e     = current energy
            mask  = which litter has been collected
        */

        struct State {
            int r, c, e, mask;
        };

        queue<State> q;

        /*
            visited[r][c][e][mask]

            true = we have already reached this state
        */
        vector<vector<vector<vector<bool>>>> visited(
            m,
            vector<vector<vector<bool>>>(
                n,
                vector<vector<bool>>(
                    energy + 1,
                    vector<bool>(1 << litterCount, false)
                )
            )
        );

        // Starting state
        q.push({sr, sc, energy, 0});
        visited[sr][sc][energy][0] = true;

        int dr[] = {-1, 1, 0, 0};
        int dc[] = {0, 0, -1, 1};

        int moves = 0;

        while (!q.empty()) {

            int size = q.size();

            // Every state in this level requires 'moves' moves
            while (size--) {

                State cur = q.front();
                q.pop();

                int r = cur.r;
                int c = cur.c;
                int e = cur.e;
                int mask = cur.mask;

                // If all litter is collected
                if (mask == fullMask) {
                    return moves;
                }

                // Cannot move without energy
                if (e == 0)
                    continue;

                // Try all 4 directions
                for (int d = 0; d < 4; d++) {

                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    // Outside grid
                    if (nr < 0 || nr >= m ||
                        nc < 0 || nc >= n) {
                        continue;
                    }

                    // Cannot pass through X
                    if (classroom[nr][nc] == 'X')
                        continue;

                    // Moving costs 1 energy
                    int newEnergy = e - 1;

                    // Copy current mask
                    int newMask = mask;

                    // If we step on litter
                    if (classroom[nr][nc] == 'L') {

                        int bit = id[nr][nc];

                        newMask = newMask | (1 << bit);
                    }

                    // If we step on reset area
                    if (classroom[nr][nc] == 'R') {

                        newEnergy = energy;
                    }

                    // If this state hasn't been visited
                    if (!visited[nr][nc][newEnergy][newMask]) {

                        visited[nr][nc][newEnergy][newMask] = true;

                        q.push({
                            nr,
                            nc,
                            newEnergy,
                            newMask
                        });
                    }
                }
            }

            // Go to next BFS level
            moves++;
        }

        // Impossible to collect all litter
        return -1;
    }
};