package Problems.풍선터트리기;

public class Solution {
    public static void main(String[] args) {

        System.out.println(new Solution().solution(new int[]{9, -1, -5}));
        System.out.println(new Solution().solution(new int[]{-16, 27, 65, -2, 58, -92, -71, -68, -61, -33}));
    }

    public int solution(int[] a) {

        int n = a.length;

        if(n <= 2)
            return n;

        SegmentTree tree = new SegmentTree(n);
        tree.init(a);

        int answer = 2;

        for (int start = 1; start < n - 1; start++) {

            int leftMin = tree.travel(0, start);
            int rightMin = tree.travel(start + 1, n);

            if(a[start] < leftMin || a[start] < rightMin)
                answer += 1;
        }
        return answer;
    }
}

class SegmentTree {

    private final int[] segmentArr;
    private final int n;

    private int a;
    private int b;

    public SegmentTree(int n) {

        this.n = n;
        int x = (int) Math.ceil(Math.log(n) / Math.log(2));
        int segmentSize = (int) Math.pow(2, x) * 2 - 1;

        segmentArr = new int[segmentSize];
    }

    public void init(int[] sequence) {

        init(sequence, 0, sequence.length - 1, 1);
        init(sequence, 0, sequence.length - 1, 1);
    }

    public int travel(int a, int b) {

        this.a = a;
        this.b = b - 1;

        return travel(0, n - 1, 1);
    }

    private int travel(int low, int high, int node) {

        if (a > high || b < low)
            return Integer.MAX_VALUE;

        if (a <= low && high <= b)
            return segmentArr[node];

        int mid = (low + high) / 2;
        return Math.min(travel(low, mid, node * 2), travel(mid + 1, high, (node * 2) + 1));
    }

    private int init(int[] sequence, int low, int high, int node) {

        if (low == high)
            return segmentArr[node] = sequence[low];

        int mid = (low + high) / 2;
        return segmentArr[node] = Math.min(init(sequence, low, mid, node * 2), init(sequence, mid + 1, high, (node * 2) + 1));
    }
}