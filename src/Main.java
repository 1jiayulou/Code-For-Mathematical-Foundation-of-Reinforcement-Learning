//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        int[][] map = new int[][]{
                {0, -1, 0, -1, 0, -1},
                {0, -1, 0, -1, 0, 0},
                {0, -1, 0, 0, -1, 0},
                {0, -1, 0, -1, 0, 0},
                {-1, 0, -1, 0, 0, -1},
                {0, 0, 0, -1, 0, 3},
        };

        Bellman bellman = new Bellman(map, -1, 0.9);

        bellman.valueIteration();
    }
}