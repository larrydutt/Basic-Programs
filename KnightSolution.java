import java.util.Scanner;

public class KnightSolution {
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);

        System.out.println("Enter size of board");
        int BoardSize = inp.nextInt();
        long start = System.nanoTime();
        KnightProblems kp = new KnightProblems(BoardSize);
        kp.solveProblem(1, 0, 0);
        long end = System.nanoTime();
        System.out.println("Total time taken: " + (end - start) + " nano seconds");
        System.out.println("Total number of Solutions: " + kp.solutions);
    }

    public static class KnightProblems {
        int solutions = 0;
        int BoardSize;
        int[] xMoves = {2, 1, -1, -2, -2, -1, 1, 2};
        int[] yMoves = {1, 2, 2, 1, -1, -2, -2, -1};
        int[][] visited;

        public KnightProblems(int chessBoardSize) {
            this.BoardSize = chessBoardSize;
            this.visited = new int[BoardSize][BoardSize];
            this.initializeBoard();
        }

        private void initializeBoard() {
            for (int i = 0; i < BoardSize; i++)
                for (int j = 0; j < BoardSize; j++)
                    this.visited[i][j] = Integer.MIN_VALUE;
        }


        public boolean solveProblem(int moveCount, int x, int y) {
            visited[x][y] = moveCount;
            if (moveCount >= BoardSize * BoardSize) {
                printSolution();
                solutions++;
                visited[x][y] = Integer.MIN_VALUE;
                return false;
            }

            for (int i = 0; i < xMoves.length; ++i) {
                int nextX = x + xMoves[i];
                int nextY = y + yMoves[i];

                // jo nayi position ayi hai vo thik hai ya nahi
                if (isValidMove(nextX, nextY) && visited[nextX][nextY] == Integer.MIN_VALUE) {

                    visited[nextX][nextY] = moveCount;
                    if (solveProblem(moveCount + 1, nextX, nextY)) {
                        return true;
                    }

                    // Agar yahan solution ni mila to backtrack
                    visited[nextX][nextY] = Integer.MIN_VALUE;
                }
            }
            return false;
        }

        //bas dekhna hai index -ve or out of bound exception na de
        public boolean isValidMove(int x, int y) {
            return x >= 0 && x < BoardSize && y >= 0 && y < BoardSize;
        }

        private void printSolution() {
            for (int i = 0; i < BoardSize; i++) {
                for (int j = 0; j < BoardSize; j++) {
                    System.out.print(visited[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("\n\n");
        }


    }
}
