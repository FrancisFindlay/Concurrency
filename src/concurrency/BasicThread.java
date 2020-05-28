package concurrency;

public class BasicThread {
    public static void main(String[] args) {
        for(int i=0;i<5;i++){
            new Thread(new LiftOff()).start();
        }
    }

}
