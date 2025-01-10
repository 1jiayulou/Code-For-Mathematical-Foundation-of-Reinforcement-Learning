//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        int[][] map = new int[][]{
                {0, 0,  0, 0, 0},
                {0, -10, -10, 0, 0},
                {0, 0, -10, 0, 0},
                {0, -10, 1, -10, 0},
                {0, -10, 0, 0, 0}
        };

        Bellman bellman = new Bellman(map, -1, 0.9);

        bellman.policyIteration();
    }
}