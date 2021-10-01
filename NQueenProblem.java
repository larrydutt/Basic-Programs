import java.util.Arrays;
import java.util.Scanner;

public class NQueenProblem
{
    public static int N;
    public static int sol=0;
    private static boolean isSafe(char mat[][], int r, int c)
    {

        for (int i = 0; i < r; i++)
        {
            if (mat[i][c] == 'Q') {
                return false;
            }
        }

        for (int i = r, j = c; i >= 0 && j >= 0; i--, j--)
        {
            if (mat[i][j] == 'Q') {
                return false;
            }
        }

        for (int i = r, j = c; i >= 0 && j < N; i--, j++)
        {
            if (mat[i][j] == 'Q') {
                return false;
            }
        }

        return true;
    }

    private static void printSolution(char mat[][])
    {
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(mat[i]).replaceAll(",", ""));
        }
        System.out.println();
    }

    private static void nQueen(char mat[][], int r)
    {
        if (r == N)
        {
            sol++;
            printSolution(mat);
            return;
        }

        for (int i = 0; i < N; i++)
        {

            if (isSafe(mat, r, i))
            {
                mat[r][i] = 'Q';
                nQueen(mat, r + 1);
                mat[r][i] = '–';
            }
        }
    }

    public static void main(String[] args)
    {
        System.out.println("Enter N ");
        Scanner inp=new Scanner(System.in);
        N=inp.nextInt();
        char[][] mat = new char[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(mat[i], '–');
        }

        long start = System.nanoTime();
        nQueen(mat, 0);
        long end = System.nanoTime();
        long elapsedTime = end - start;
        System.out.format("Number of possible solutions are: %d\n",sol);
        System.out.print("Time taken by the program: " + elapsedTime + " nano seconds");
    }
}