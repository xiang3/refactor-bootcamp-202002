package cc.xpbootcamp.warmup.fibonacci;

public class Fibonacci {

    public Fibonacci() {
    }

    public int get(int position) {
        if(position == 1 || position == 2 ) {
            return 1;
        }
        return get(position-1)+get(position-2);
    }
}
