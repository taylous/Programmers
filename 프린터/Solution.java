package Problems.프린터;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

class Document {

    int priority;
    boolean isLocation;

    public Document(int priority, boolean isLocation) {
        this.priority = priority;
        this.isLocation = isLocation;
    }
}

public class Solution {
    public int solution(int[] priorities, int location) {

        ArrayList<Integer> priorityList = new ArrayList<>();
        LinkedList<Document> documents = new LinkedList<>();
        int order = 1;

        for(int priority : priorities) {

            documents.add(new Document(priority, false));
            priorityList.add(priority);
        }
        priorityList.sort(Collections.reverseOrder());
        documents.get(location).isLocation = true;

        while(!documents.isEmpty()) {

            Document document = documents.removeFirst();

            if(document.priority == priorityList.get(0)) {

                if(document.isLocation)
                    break;
                order += 1;
                priorityList.remove(0);
                continue;
            }
            documents.addLast(document);
        }
        return order;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution(new int[]{2, 1, 3, 2}, 2));
        System.out.println(new Solution().solution(new int[]{1, 1, 9, 1, 1, 1}, 0));
    }
}
