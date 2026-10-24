import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String S = scanner.next();
        int K = scanner.nextInt();
        int M = scanner.nextInt();
        
        int len = S.length();
        long mask = (1L << K) - 1;
        HashMap<Long, Integer> count = new HashMap<>();
        long window = 0;
        int res = 0;

        for (int i=0; i<len; i++) {
            window = ((window << 1) | (S.charAt(i) - '0')) & mask;
            if (i>= K-1) {
                int c = count.merge(window, 1, Integer::sum);
                if (c >= M) {
                    res = 1;
                    break;
                }
            }
        }
        System.out.println(res);
    }
}