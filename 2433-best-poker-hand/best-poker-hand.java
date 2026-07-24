class Solution {
    public String bestHand(int[] ranks, char[] suits) {
       
        boolean flush = true;

        for (int i = 1; i < 5; i++) {
            if (suits[i] != suits[0]) {
                flush = false;
                break;
            }
        }

        if (flush)
            return "Flush";

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int rank : ranks) {
            map.put(rank, map.getOrDefault(rank, 0) + 1);

            if (map.get(rank) == 3)
                return "Three of a Kind";
        }

        for (int count : map.values()) {
            if (count == 2)
                return "Pair";
        }

        return "High Card";
    }
}