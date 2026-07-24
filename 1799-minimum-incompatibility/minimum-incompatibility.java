class Solution {
    public int minimumIncompatibility(int[] nums, int k) {
        int n = nums.length;
        int size = n / k;
        int total = 1 << n;

        int[] cost = new int[total];
        Arrays.fill(cost, -1);

        for (int mask = 0; mask < total; mask++) {
            if (Integer.bitCount(mask) != size)
                continue;

            boolean[] seen = new boolean[n + 1];
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            boolean valid = true;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    if (seen[nums[i]]) {
                        valid = false;
                        break;
                    }

                    seen[nums[i]] = true;
                    min = Math.min(min, nums[i]);
                    max = Math.max(max, nums[i]);
                }
            }

            if (valid)
                cost[mask] = max - min;
        }

        int[] dp = new int[total];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int mask = 0; mask < total; mask++) {
            if (dp[mask] == Integer.MAX_VALUE)
                continue;

            int remain = (total - 1) ^ mask;
            int first = remain & -remain;

            for (int sub = remain; sub > 0; sub = (sub - 1) & remain) {
                if ((sub & first) == 0)
                    continue;

                if (cost[sub] != -1) {
                    dp[mask | sub] = Math.min(dp[mask | sub],
                            dp[mask] + cost[sub]);
                }
            }
        }

        return dp[total - 1] == Integer.MAX_VALUE ? -1 : dp[total - 1];
    }
}