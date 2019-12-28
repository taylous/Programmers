import java.util.Arrays;
import java.util.LinkedList;

class Solution {

    LinkedList<LinkedList<int[]>> sideList = new LinkedList<>();

    static int[] loX = { 0, 1, 0, -1 };
    static int[] loY = { 1, 0, -1, 0 };

    static int baseSize;

    static int keySize;
    static int lockSize;

    public static void main(String[] arsg) {

        System.out.println(new Solution().solution(new int[][]{
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 1}
        }, new int[][] {
                {1, 1, 1, 1},
                {1, 1, 0, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1}
        }));
    }

    public boolean solution(int[][] key, int[][] lock) {

        int rotate = 3;

        keySize = key.length;
        lockSize = lock.length;
        baseSize = keySize - 1;

        boolean answer = unlock(key, lock);
        initiate(key);

        while(rotate-- > 0 && !answer) {

            rotateKey(key);

            for(int[] temp : key) {

                for(int value : temp)
                    System.out.print(value);
                System.out.println();
            }
            System.out.println();

            answer = unlock(key, lock);
        }
        return answer;
    }

    private void initiate(int[][] key) {

        for(int i = 0; i <= (keySize / 2) - 1; i++)
            sideList.add(new LinkedList<>());

        int[] temp;
        int x, y, side = 0, index;
        int offset = keySize;

        for(int i = 0; i <= (keySize / 2) - 1; i++) {

            x = y = i;

            while(side < 4) {

                temp = new int[keySize - (i * 2)];
                index = 0;

                do {

                    temp[index++] = key[x][y];
                    x += loX[side];
                    y += loY[side];

                } while(x >= i && x < offset && y >= i && y < offset);

                sideList.get(i).add(temp);
                x -= loX[side];
                y -= loY[side++];
            }
            side = 0;
            offset--;
        }
    }

    private void rotateKey(int[][] key) {

        LinkedList<int[]> side;

        int totalSize = sideList.size();
        int x, y, index;

        for(int i = 0; i < totalSize; i++) {

            side = sideList.get(i);
            side.addFirst(side.removeLast());
            index = 0;
            x = y = i;

            for(int[] element : side) {

                for(int value : element) {

                    key[x][y] = value;
                    x += loX[index];
                    y += loY[index];
                }
                x -= loX[index];
                y -= loY[index++];
            }
        }
    }

    private boolean unlock(int[][] key, int[][] lock) {

        System.out.println("UNLOCK");
        int[][] compare = new int[((keySize - 1) * 2) + lockSize][((keySize - 1) * 2) + lockSize];
        int size = compare.length;
        boolean flag;

        for(int i = 0; i <= size - keySize; i++) {

            for(int j = 0; j <= size - keySize; j++) {

                for(int row = 0; row < keySize; row++)
                    System.arraycopy(key[row], 0, compare[i + row], j, keySize);

                for(int[] t : compare) {

                    for(int v : t)
                        System.out.print(v);
                    System.out.println();
                }
                System.out.println();

                flag = false;

                for(int row = 0; row < lockSize; row++) {

                    for(int col = 0; col < lockSize; col++) {

                        if(compare[baseSize + row][baseSize + col] + lock[row][col] != 1) {

                            flag = true;
                            break;
                        }
                    }
                    if(flag)
                        break;
                }
                if(!flag)
                    return true;
                for (int[] ints : compare) Arrays.fill(ints, 0);
            }
        }
        System.out.println();
        return false;
    }
}