
public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().solution("(()())()"));
        System.out.println(new Solution().solution(")("));
        System.out.println(new Solution().solution("()))((()"));
    }

    public boolean check(String p) {

        int openBracketCnt = 0;

        for(char bracket : p.toCharArray()) {

            if(bracket == ')') {

                if(openBracketCnt == 0)
                    return false;
                openBracketCnt--;
                continue;
            }
            openBracketCnt++;
        }
        return true;
    }

    public String[] disunite(String p) {

        String[] ret = new String[2];

        int[] bracketCount = new int[2];
        int idx = 0;

        for(char bracket : p.toCharArray()) {

            if(bracket == '(')
                bracketCount[0]++;
            else
                bracketCount[1]++;
            idx++;

            if(bracketCount[0] == bracketCount[1])
                break;
        }
        ret[0] = p.substring(0, idx);
        ret[1] = p.substring(idx);
        return ret;
    }

    public String convert(String u) {

        StringBuffer sb = new StringBuffer();

        for(char bracket : u.toCharArray())
            sb.append(bracket == '(' ? ')' : '(');
        return sb.toString();
    }

    public String solution(String p) {

        if(p.equals(""))
            return "";
        if(check(p))
            return p;

        StringBuffer sb = new StringBuffer();
        String[] brackets = disunite(p);

        if(check(brackets[0])) {

            sb.append(brackets[0]);
            return sb.append(solution(brackets[1])).toString();
        }
        sb.append("(");
        sb.append(solution(brackets[1]));
        sb.append(")");

        brackets[0] = brackets[0].substring(1);
        brackets[0] = brackets[0].substring(0, brackets[0].length() - 1);
        return sb.append(convert(brackets[0])).toString();
    }
}
