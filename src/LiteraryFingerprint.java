import java.util.*;
import java.io.*;

public class LiteraryFingerprint {
    private static int MAX_LENGTH = 20;

    public String[] readFile(String fname)
            throws FileNotFoundException {
        Scanner scan = new Scanner(new File(fname));
        scan.useDelimiter("\\Z");
        String s = scan.next();
        s = s.replaceAll("\\s+"," ");
        s = s.replaceAll("\\p{Punct}", "");
        return s.toLowerCase().split(" ");
    }

    public int[] getLengths(String[] words) {
        int[] counts = new int[MAX_LENGTH];
        for(String s : words) {
            int len = s.length();
            if (len < MAX_LENGTH) {
                counts[len] += 1;
            }
        }
        return counts;
    }

    public double[] getStats(int[] data) {
        int totalData = 0;
        for(int k=1; k < data.length; k++) {
            totalData += data[k];
        }

        int sum = 0;
        int median = -1;
        for (int k = 1; k < data.length; k++) {
            sum += k * data[k];
            if (sum >= totalData && median == -1) {
                median = k;
            }
        }
        double mean = 1.0 * sum / totalData;

        int devSum = 0;
        for(int k=1; k < data.length; k++) {
            devSum += data[k]*(k-mean)*(k-mean);
        }
        double stdev = Math.sqrt(devSum/(totalData - 1));

        return new double[] {mean,median,stdev};

    }

    public static void main(String[] args) throws FileNotFoundException {
        String[] plays = {
                "allswell.txt", "romeo.txt",
                "macbeth.txt", "hamlet.txt", "caesar.txt"
        };
        LiteraryFingerprint lf = new LiteraryFingerprint();
        for(String title : plays) {
            String[] words = lf.readFile("data/"+title);
            int[] counts = lf.getLengths(words);
            double[] stats = lf.getStats(counts);
            System.out.print(title+"\t");
            System.out.printf("%2.3g,%2.3g,%2.3g\n",stats[0],stats[1],stats[2]);
        }
        String[] works = {"hawthorne.txt", "mobydick.txt", "littlewomen.txt"};
        for(String title : works) {
            String[] words = lf.readFile("data/"+title);
            int[] counts = lf.getLengths(words);
            double[] stats = lf.getStats(counts);
            System.out.print(title+"\t");
            System.out.printf("%2.3g,%2.3g,%2.3g\n",stats[0],stats[1],stats[2]);
        }
    }
}
