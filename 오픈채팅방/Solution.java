package KAKAOBLINDRECRUIMENT.오픈채팅방;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) {

        print(new Solution().solution(new String[]
                {
                    "Enter uid1234 Muzi",
                    "Enter uid4567 Prodo",
                    "Leave uid1234",
                    "Enter uid1234 Prodo",
                    "Change uid4567 Ryan"
        }));
    }

    public static void print(String[] answer) {

        for(String value : answer)
            System.out.print(value + " ");
        System.out.println();
    }

    public String[] getResult(HashMap<String, String> map, ArrayList<String> queries) {

        String[] ret = new String[queries.size()];
        StringTokenizer st;
        
        int idx = 0;
        
        for(String query : queries) {

            st = new StringTokenizer(query, "|");
            String command = st.nextToken();
            String uid = st.nextToken();

            if(command.equals("Enter"))
                ret[idx++] = map.get(uid) + "님이 들어왔습니다.";
            else
                ret[idx++] = map.get(uid) + "님이 나갔습니다.";
        }
        return ret;
    }

    public String[] solution(String[] record) {

        HashMap<String, String> map = new HashMap<>();
        ArrayList<String> answer = new ArrayList<>();

        StringTokenizer st;

        for(String query : record) {

            st = new StringTokenizer(query);

            String command = st.nextToken();
            String uid = st.nextToken();

            if(command.equals("Leave")) {
                answer.add(command + "|" + uid);
                continue;
            }
            String name = st.nextToken();

            map.put(uid, name);

            if(!command.equals("Change"))
                answer.add(command + "|" + uid);
        }
        return getResult(map, answer);
    }
}