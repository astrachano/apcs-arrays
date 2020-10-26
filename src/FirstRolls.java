public class FirstRolls {
    public static void trackRolls(){
        int twos = 0;
        int threes = 0;
        int fours = 0;
        for(int k=0; k < 1000; k++) {
            int roll = rollDice(2);
            if (roll == 2) twos += 1;
            if (roll == 3) threes += 1;
            if (roll == 4) fours += 1;
        }
        System.out.printf("%d,%d,%d\n",twos,threes,fours);
    }

    public static int rollDice(int sides) {
        int s1 = (int) Math.floor(Math.random()*sides + 1);
        int s2 = (int) Math.floor(Math.random()*sides + 1);
        return s1 + s2;
    }
    public static void main(String[] args) {
        trackRolls();
    }
}
