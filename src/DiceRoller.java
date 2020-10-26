public class DiceRoller {

    public static int rollDice(int sides) {
        int s1 = (int) Math.floor(Math.random()*sides + 1);
        int s2 = (int) Math.floor(Math.random()*sides + 1);
        return s1 + s2;
    }

    public static void main(String[] args) {
        int sides = 6;
        int[] counts = new int[2*sides + 1];
        int rolls = 1000;
        for(int k=0; k < rolls; k++) {
            int roll = rollDice(sides);
            counts[roll] += 1;
        }
        for(int k=2; k <= 2*sides; k++) {
            System.out.println(k+"\t"+counts[k]);
        }
    }
}
