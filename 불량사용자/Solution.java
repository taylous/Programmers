package KAKAO.불량사용자;

import java.util.HashSet;
import java.util.LinkedHashSet;

public class Solution {

    private HashSet<HashSet<String>> answer;

    public static void main(String[] args) {
        System.out.println(new Solution().solution(
                new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"},
                new String[]{"fr*d*", "abc1**"}));

        System.out.println(new Solution().solution(
                new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"},
                new String[]{"*rodo", "*rodo", "******"}
        ));

        System.out.println(new Solution().solution(
                new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"},
                new String[]{"fr*d*", "*rodo", "******", "******"}
        ));
    }

    public boolean cmpStr(String target, String comp) {

        if (target.length() != comp.length())
            return false;

        for (int i = 0; i < target.length(); i++) {

            if (target.charAt(i) == '*')
                continue;

            if (target.charAt(i) != comp.charAt(i))
                return false;
        }
        return true;
    }

    public void combinations(String[] user_id, String[] banned_id, HashSet<String> set) {

        if(set.size() == banned_id.length) {

            int i = 0;
            for(String user : set) {

                if(cmpStr(banned_id[i++], user))
                    continue;
                return;
            }
            answer.add(new HashSet<>(set));
            return;
        }

        for(String user : user_id) {

            if(!set.contains(user)) {

                set.add(user);
                combinations(user_id, banned_id, set);
                set.remove(user);
            }
        }
    }

    public int solution(String[] user_id, String[] banned_id) {

        answer = new HashSet<>();
        combinations(user_id, banned_id, new LinkedHashSet<>());
        return answer.size();
    }
}
