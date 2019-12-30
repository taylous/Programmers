import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Solution {

    int[][] post;
    int[][] beam;
    
    public int[][] solution(int n, int[][] build_frame) {

        post = new int[n + 1][n + 1];
        beam = new int[n + 1][n + 1];

        for(int[] frame : build_frame) {

            // 0이면 기둥
            if(frame[2] == 0) {

                // 1이면 설치
                if(frame[3] == 1) {

                    // 높이가 1이 아니면 밑에 보 or 기둥이 있어야함
                    if (frame[1] != 1) {

                        // 밑에 보와 기둥 모두 없으면 못 만들기 때문에 무시한다.
                        if (frame[1] - 1 >= 0 && post[frame[1] - 1][frame[0]] == 0 && beam[frame[1]][frame[0]] == 0)
                            continue;
                        // 있다면 만들 수 있다.
                    }
                    post[frame[1]][frame[0]] = 1;
                }
                // 0이면 삭제
                else {

                    // 삭제할 기둥을 기준으로 beam[기둥y + 1][기둥x], beam[기둥y + 1][기둥x - 1] 확인한다.
                    if(beam[frame[1] + 1][frame[0]] == 1 && beam[frame[1] + 1][frame[0] - 1] == 1) {
                        // 둘 다 있다면?
                        // 하나로 연결되어 있다는 뜻이므로 삭제 가능
                        post[frame[1]][frame[0]] = 0;
                        continue;
                    }

                    // beam[기둥y + 1][기둥x]만 있다면?
                    if(beam[frame[1] + 1][frame[0]] == 1) {

                        // post[기둥y][기둥x + 1]에 기둥이 있는지 확인한다.
                        if(post[frame[1]][frame[0] + 1] == 1)
                            // 기둥이 있어서 삭제가 가능하다.
                            post[frame[1]][frame[0]] = 0;
                        // 없으면 조건에 위배 되므로 삭제가 불가능하다.
                    }
                    // beam[기둥y + 1][기둥x - 1]만 있다면?
                    else{
                        // post[기둥y][기둥x - 1]에 기둥이 있는지 확인한다.
                        if(post[frame[1]][frame[0] - 1] == 1)
                            // 기둥이 있어서 삭제가 가능하다.
                            post[frame[1]][frame[0]] = 0;
                        // 없으면 조건에 위배 되므로 삭제가 불가능하다.
                    }
                }
            }
            // 1이면 보
            else {

                if(frame[3] == 1) {

                    // post[보y - 1][보x] or post[보y - 1][보x + 1]에 기둥이 있으면 설치가능.
                    if(post[frame[1] - 1][frame[0]] == 1 || post[frame[1] - 1][frame[0] + 1] == 1) {

                        beam[frame[1]][frame[0]] = 1;
                        continue;
                    }
                    // 한쪽도 없다면, 양쪽에 보가 있는지 확인한다.
                    if(frame[0] - 1 >= 0 && frame[0] + 1 < n && beam[frame[1]][frame[0] - 1] == 1 && beam[frame[1]][frame[0] + 1] == 1) {
                        // 양쪽모두 보면 설치가능
                        beam[frame[1]][frame[0]] = 1;
                    }
                    //없다면 설치가 불가능하다.
                }
                else {

                    // 양쪽에 보가 있고 중간에 삭제하는 경우
                    if(frame[0] - 1 >= 0 && frame[0] + 1 < n && beam[frame[1]][frame[0] - 1] == 1 && beam[frame[1]][frame[0] + 1] == 1) {

                        if(post[frame[1] - 1][frame[0] - 1] == 1 && post[frame[1] - 1][frame[0] + 1] == 1) {

                            beam[frame[1]][frame[0]] = 0;
                        }
                    }
                    // 한쪽만 지우는 경우
                    else {

                        if(post[frame[1] - 1][frame[0]] == 1 || post[frame[1] - 1][frame[0] + 1] == 1)
                            beam[frame[1]][frame[0]] = 0;
                    }
                }
            }
        }
        ArrayList<PostAndBeam> list = new ArrayList<>();
        int[][] answer;

        for(int i = 0; i <= n; i++) {

            for(int j = 0; j <= n; j++)
                if(post[i][j] == 1)
                    list.add(new PostAndBeam(j, i, 0));
        }

        for(int i = 0; i <= n; i++) {

            for(int j = 0; j <= n; j++)
                if(beam[i][j] == 1)
                    list.add(new PostAndBeam(j, i, 1));
        }
        answer = new int[list.size()][3];
        Collections.sort(list);

        int idx = 0;
        for(PostAndBeam postAndBeam : list) {

            answer[idx][0] = postAndBeam.x;
            answer[idx][1] = postAndBeam.y;
            answer[idx++][2] = postAndBeam.type;
        }
        return answer;
    }
}

class PostAndBeam implements Comparable<PostAndBeam> {

    int x;
    int y;
    int type;

    public PostAndBeam(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    @Override
    public int compareTo(PostAndBeam other) {

        if(this.x < other.x)
            return -1;
        else if(this.x > other.x)
            return 1;
        else {

            if(this.y < other.y)
                return -1;
            else if(this.y > other.y)
                return 1;
            else {

                if(this.type == 0)
                    return -1;
            }
        }
        return 1;
    }
}