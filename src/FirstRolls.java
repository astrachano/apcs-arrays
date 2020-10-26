public class FirstRolls {

    public static void main(String[] args) {
        int rolls = 1000;
        int twos = 0;
        int threes = 0;
        int fours = 0;
        for(int k=0; k < rolls; k++) {
            int s1 = (int) Math.floor(Math.random()*2 + 1);
            int s2 = (int) Math.floor(Math.random()*2 + 1);
            int roll = s1 + s2;
            if (roll == 2) twos += 1;
            if (roll == 3) threes += 1;
            if (roll == 4) fours += 1;
        }
        System.out.println("2's = "+twos);
        System.out.println("3's = "+threes);
        System.out.println("4's = "+fours);
    }
}
