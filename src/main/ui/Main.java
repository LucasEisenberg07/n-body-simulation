package ui;



public class Main {
    public static void main(String[] args) throws Exception {
        int a = 1;
        int b = 2;
        
        do {
            if (a < 3) {
                a += b;
            } else {
                a += 2 * b;
            }
            b++;
        
        } while (b < 5);
        System.out.println(a);
    }
}
