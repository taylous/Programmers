import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class Solution {

    public static void main(String[] args) {

        int[] answer = new Solution().solution(
                new String[]
                        {"frodo", "front", "frost", "frozen", "frame", "kakao"},
                new String[]
                        {"fro??", "????o", "fr???", "fro???", "pro?"});

        for(int value : answer)
            System.out.print(value);
        System.out.println();
    }

    public int[] solution(String[] words, String[] queries) {

        HashMap<String, Integer> map = new HashMap<>();
        HashSet<String> set = new HashSet<>();

        Trie trie = new Trie();
        Trie reverseTrie = new Trie();

        String key;

        int[] answer = new int[queries.length];
        int idx = 0, result;

        for(String word : words)
            set.add(word);

        Iterator it = set.iterator();
        while(it.hasNext()) {

            key = (String) it.next();
            trie.put(key);
            reverseTrie.put(reverseStr(key));
        }

        for(String query : queries) {

            if(map.containsKey(query)) {
                result = map.get(query);
            }
            else {

                if(query.charAt(0) == '?')
                    result = reverseTrie.search(new StringBuffer(query).reverse().toString());
                else
                    result = trie.search(query);
                map.put(query, result);
            }
            answer[idx++] = result;
        }
        return answer;
    }

    private String reverseStr(String str) {
        return new StringBuffer(str).reverse().toString();
    }
}

class Trie {

    private class Node {

        Node[] alphabet;
        char ch;
        int child;
        boolean terminal;

        Node(char ch) {
            this.alphabet = new Node[26];
            this.ch = ch;
            this.child = 0;
            this.terminal = false;
        }
    }
    private Node root;

    Trie() {

        this.root = new Node('\u0000');
    }

    public void put(String str) {

        Node node = this.root;

        int until = str.length(), idx = 0;
        char ch;

        while(true) {

            if(idx == until) {

                if(node == null)
                    node = new Node(str.charAt(idx));
                node.terminal = true;
                break;
            }
            ch = str.charAt(idx++);

            if(node.alphabet[ch - 'a'] == null) {
                node.child++;
                node.alphabet[ch - 'a'] = new Node(ch);
            }
            node = node.alphabet[ch - 'a'];
        }
    }

    public int search(String str) {

        return search(this.root, str, 0);
    }

    private int search(Node node, String str, int idx) {

        if(idx == str.length()) {

            if(str.charAt(idx - 1) != '?') {
                if (node.ch != str.charAt(idx - 1))
                    return 0;
            }
            return node.terminal ? 1 : 0;
        }
        if(node.child == 0)
            return 0;

        char ch = str.charAt(idx);
        int ret = 0;

        if(ch == '?') {

            for(int i = 0; i < 26; i++) {

                if(node.alphabet[i] != null)
                    ret += search(node.alphabet[i], str, idx + 1);
            }
        }
        else {
            if (node.alphabet[ch - 'a'] != null)
                ret += search(node.alphabet[ch - 'a'], str, idx + 1);
        }
        return ret;
    }
}
