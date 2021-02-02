package Problems.신규아이디추천;

import java.util.List;
import java.util.stream.Collectors;

public class Solution {
    public String solution(String new_id) {

//      1단계 new_id의 모든 대문자를 대응되는 소문자로 치환합니다.
        String id = new_id.toLowerCase();

        System.out.println("1단계: " + id);

//      2단계 new_id에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.
        List<Character> ids = id.chars().mapToObj(ch -> (char) ch).collect(Collectors.toList());
        deleteChar(ids);

        System.out.println("2단계: " + ids);

//      3단계 new_id에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
        deleteCommas(ids);
        System.out.println("3단계: " + ids);


//      4단계 new_id에서 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
        deleteHeadAndTail(ids);
        System.out.println("4단계: " + ids);

//      5단계 new_id가 빈 문자열이라면, new_id에 "a"를 대입합니다.
        if (ids.isEmpty())
            ids.add('a');
        System.out.println("5단계: " + ids);

//      6단계 new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다.
//              만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.
        if (ids.size() >= 16) {
            ids.subList(15, ids.size()).clear();
            if (ids.get(ids.size() - 1) == '.')
                ids.remove(ids.size() - 1);
        }
        System.out.println("6단계: " + ids);


//      7단계 new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.
        if (ids.size() <= 2) {

            char ch = ids.get(ids.size() - 1);

            while (ids.size() < 3)
                ids.add(ch);
        }
        System.out.println("7단계: " + ids);

        return ids.stream().map(String::valueOf).collect(Collectors.joining());
    }

    private void deleteChar(List<Character> id) {

        int size = id.size();

        for (int idx = 0; idx < size; idx++) {

            char ch = id.get(idx);

            if (!(ch >= 'a' && ch <= 'z') && !(ch >= '0' && ch <= '9') && ch != '-' && ch != '_' && ch != '.') {
                id.remove(idx);
                size -= 1;
                idx -= 1;
            }
        }
    }

    private void deleteCommas(List<Character> id) {

        int size = id.size();
        int start, end;

        for (int i = 0; i < size; i++) {

            char ch = id.get(i);

            if (ch == '.') {

                start = end = i;

                for (int j = i + 1; j < size; j++) {

                    ch = id.get(j);

                    if (ch != '.')
                        break;
                    end = j;
                }

                if (end - start >= 1) {
                    id.subList(start, end + 1).clear();
                    id.add(start, '.');
                    size -= (end - start);
                }
            }
        }
    }

    private void deleteHeadAndTail(List<Character> id) {

        int size = id.size();

        if (!id.isEmpty() && id.get(0) == '.') {
            id.remove(0);
            size -= 1;
        }
        if (size - 1 >= 0 && id.get(size - 1) == '.')
            id.remove(size - 1);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution("q"));
        System.out.println(new Solution().solution(".$."));
        System.out.println(new Solution().solution("....."));
        System.out.println(new Solution().solution("..bat.....y.ab..cdefghi..."));
        System.out.println(new Solution().solution("...!@BaT#*..y.abcdefghijklm"));
        System.out.println(new Solution().solution("z-+.^."));
        System.out.println(new Solution().solution("=.="));
        System.out.println(new Solution().solution("123_.def"));
        System.out.println(new Solution().solution("abcdefghijklmn.p"));
    }
}
