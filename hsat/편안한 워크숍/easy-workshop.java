import java.util.*;

public class Main {
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                grid[i][j] = scanner.nextInt();
        
        long[] order = new long[n*n];
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                order[i*n + j] = (long) grid[i][j] * 10000 + (i*n + j);
            }
        }
        Arrays.sort(order);

        // dp[i][j] i칸에서 끝나는 길이 j (j는 K이상이어야함) 중 최솟값
        int[][] dp = new int[n*n][k+1];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        for (int id=0; id<n*n; id++) {
            dp[id][1] = 0;
        }

        for (long v : order) {
            int id = (int) (v%10000);
            int r = id/n, c = id%n;
            for (int l=1; l<=k; l++) {
                if (dp[id][l] == Integer.MAX_VALUE) continue;
                for (int dir=0; dir<4; dir++) {
                    int nr = r + dr[dir], nc = c + dc[dir];
                    if (nr < 0 || nc < 0 || nr >= n || nc >= n) continue;

                    int diff = grid[nr][nc] - grid[r][c];
                    if (diff <= 0) continue;

                    int nid = nr * n + nc;
                    int nl = Math.min(l+1, k);
                    int cand = Math.max(dp[id][l], diff);
                    if (cand < dp[nid][nl]) {
                        dp[nid][nl] = cand;
                    }
                }
            }
        }

        int res = Integer.MAX_VALUE;
        for (int i=0; i<n*n; i++) {
            res = Math.min(res, dp[i][k]);
        }
        System.out.println(res == Integer.MAX_VALUE ? -1 : res);
    }
}