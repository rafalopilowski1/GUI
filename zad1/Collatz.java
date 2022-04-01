import java.util.Iterator;

public class Collatz {
    public static void main(String[] args) {
        int init = 77031, count = -1, maxel = 0;
        Hailstone hailstone = new Hailstone(init);
        for (int h : hailstone) {
            if (h > maxel)
                maxel = h;
            ++count;
        }
        System.out.println(init + " " + count + " " + maxel);
    }
}

class Hailstone implements Iterable<Integer> {
    protected int num;

    Hailstone(int begin_num) {
        this.num = begin_num;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new HailstoneIterator(this);
    }
}

class HailstoneIterator implements Iterator<Integer> {
    Hailstone data;
    boolean init_sent;

    HailstoneIterator(Hailstone data) {
        this.data = data;
        init_sent = false;
    }

    @Override
    public boolean hasNext() {
        return data.num > 1;
    }

    @Override
    public Integer next() {
        if (init_sent)
            switch (data.num % 2) {
                case 0 -> {
                    data.num = data.num / 2;
                }
                case 1 -> {
                    data.num = (3 * data.num) + 1;
                }
            }
        init_sent = true;
        return data.num;
    }
}
