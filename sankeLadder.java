import java.util.HashMap;
import java.util.Scanner;

class SnakeLadder {
    static HashMap<Integer, Integer> snakes = new HashMap<>();
    static HashMap<Integer, Integer> ladders = new HashMap<>();
    static HashMap<String, Integer> players = new HashMap<>();

    public static void main(String[] args) {

        takeInput(snakes);
        takeInput(ladders);
        takePlayersInput(players);

        playGame();
    }

    public static void takeInput(HashMap<Integer, Integer> map) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();

        for (int i = 0; i < n; i++) {
            map.put(s.nextInt(), s.nextInt());
        }

    }

    public static void takePlayersInput(HashMap<String, Integer> map) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();

        for (int i = 0; i < n; i++) {
            map.put(s.next(), 0);
        }
    }

    public static int rollDice() {
        return (int) (Math.random() * 5 + 1);
    }

    public static String checkWinner() {
        for (String s : players.keySet())
            if (players.get(s) == 100)
                return s;
        return "";
    }

    public static int checkSnakeLadder(int n) {
        if (snakes.containsKey(n))
            return snakes.get(n);
        if (ladders.containsKey(n))
            return ladders.get(n);
        return n;
    }

    public static int findNext(int prev, int dice) {
        int sum = prev + dice;

        if (sum > 100)
            return prev;

        return checkSnakeLadder(sum);
    }

    public static void playGame() {
        while (checkWinner() == "") {
            for (String key : players.keySet()) {
                int dice = rollDice();
                int prev = players.get(key);
                int next = findNext(prev, dice);
                players.put(key, next);
                System.out.println(key + " rolled a " + dice + " and moved from " + prev + " to " + next);
                if (next == 100){
                    System.out.println(key + " wins the game");
                    return;
                };
            }
            System.out.println();
        }
    }
}