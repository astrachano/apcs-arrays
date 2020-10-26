public class MetaRoller {
    public static void createCounters(int sides){
        for(int d=2; d <= 2*sides; d+=1) {
            System.out.println("int c"+d+" = 0;");
        }
    }

    public static void createIfs(int sides) {
        for(int d=2; d <= 2*sides; d+=1) {
            System.out.println("if (roll == "+d+") c"+d+" += 1;");
        }
    }

    public static void createRoll(int sides) {
        System.out.println("int s1 = (int) Math.floor(Math.random()*"+sides+"+ 1);");
        System.out.println("int s2 = (int) Math.floor(Math.random()*"+sides+"+ 1);");
        System.out.println("int roll = s1+s2;");
    }

    public static void createPrints(int sides) {
        for(int d=2; d <= 2*sides; d+=1) {
            System.out.print("System.out.println(");
            System.out.println(d+"+\"\\t"+ "\"+c"+d+");");
        }
    }
    public static void createLoop(int rolls) {
        System.out.println("for(int k=0; k < "+rolls+"; k+= 1){");
    }
    public static void createHeader(int sides){
        System.out.println("public class Roll"+sides+"{");
        System.out.println("public static void main(String[] args) {");
    }

    public static void main(String[] args) {
        int sides = 2;
        createHeader(sides);
        createCounters(sides);
        createLoop(1000);
        createRoll(sides);
        createIfs(sides);
        System.out.println("}");
        createPrints(sides);
        System.out.println("}");
        System.out.println("}");
    }
}
