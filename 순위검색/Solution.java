package Problems.순위검색;

import java.util.*;

public class Solution {

    private final HashMap<String, ArrayList<Integer>> maps = new HashMap<>();

    private final String[][] items = {{"-", "cpp", "java", "python"}, {"-", "backend", "frontend"}, {"-", "junior", "senior"}, {"-", "chicken", "pizza"}};
    private final String[][] sample = {{"-", ""}, {"-", ""}, {"-", ""}, {"-", ""}};

    public int[] solution(String[] info, String[] query) {

        ArrayList<Integer> answer = new ArrayList<>();
        StringTokenizer st;
        createKeys(items, new StringBuilder(), 0, false, -1);

        for (String data : info) {

            st = new StringTokenizer(data);

            for (int i = 0; i < 4; i++)
                sample[i][1] = st.nextToken();
            createKeys(sample, new StringBuilder(), 0, true, Integer.parseInt(st.nextToken()));
        }

        for (String key : maps.keySet()) {

            Collections.sort(maps.get(key));
        }

        for (String q : query) {

            StringBuilder sb = new StringBuilder();
            st = new StringTokenizer(q);

            while (st.countTokens() > 1) {

                String token = st.nextToken();

                if (token.equals("and"))
                    continue;
                sb.append(token);
            }
            System.out.println("QUERY: " + q);
            answer.add(binarySearch(maps.get(sb.toString()), Integer.parseInt(st.nextToken())));
        }
        return answer.stream().mapToInt(i -> i).toArray();
    }

    private int binarySearch(ArrayList<Integer> list, int value) {

        if(list.isEmpty())
            return 0;

        int left = 0;
        int right = list.size() - 1;
        int mid = 0;

        while (left < right) {

            mid = (left + right) / 2;

            if (list.get(mid) < value)
                left = mid + 1;
            else
                right = mid;
        }
        return list.size() - (list.get(right) >= value ? right : right + 1);
    }

    private void createKeys(String[][] items, StringBuilder key, int idx, boolean isPush, int value) {

        if (idx >= 4) {

            if (!maps.containsKey(key.toString()))
                maps.put(key.toString(), new ArrayList<>());

            if (isPush) {

                ArrayList<Integer> list = maps.get(key.toString());
                list.add(value);
                maps.replace(key.toString(), list);
            }
            return;
        }
        for (int i = 0; i < items[idx].length; i++)
            createKeys(items, new StringBuilder(key.toString()).append(items[idx][i]), idx + 1, isPush, value);
    }

    public static void main(String[] args) {

        System.out.println(Arrays.toString(new Solution().solution(new String[]{
                        "java backend junior pizza 150",
                        "python frontend senior chicken 210",
                        "python frontend senior chicken 150",
                        "python frontend senior chicken 550",
                        "python frontend senior chicken 750",
                        "python frontend senior chicken 50",
                        "python frontend senior chicken 20",
                        "python frontend senior chicken 330",
                        "python frontend senior chicken 650",
                        "python frontend senior chicken 950",
                        "cpp backend senior pizza 260",
                        "cpp backend senior pizza 1260",
                        "cpp backend senior pizza 1360",
                        "java backend junior chicken 80",
                        "python backend senior chicken 50"
                },
                new String[]{
                        "cpp and backend and junior and pizza 100",
                        "java and backend and junior and pizza 100",
                        "python and frontend and senior and chicken 200",
                        "cpp and backend and senior and pizza 1050",
                        "cpp and - and senior and pizza 250",
                        "- and backend and senior and - 150",
                        "- and - and - and chicken 100",
                        "- and - and - and - 1000",
                        "cpp and backend and senior and pizza 2050"
                })))
        ;
//        System.out.println(Arrays.toString(new Solution().solution(new String[]{"java backend junior pizza 100000", "java backend junior pizza 10"},
//                new String[]{"java backend junior pizza 100000", "- and - and - and - 100000"})))
//        ;
//
//        String[] temp = new String[50000];
//        for (int i = 0; i < 50000; i++)
//            temp[i] = "java backend junior pizza 10";
//
//        System.out.println(Arrays.toString(new Solution().solution(temp,
//                new String[]{"java and backend and junior and pizza 0", "java and backend and junior and pizza 10"})))
        ;
    }
}
