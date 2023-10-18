public class queue {
    private int head = 0, tail = 0;
    final int QSIZE=1; Item[QSIZE] items;

    public synchronized void enq(Item x) {
        while (this.tail { this.head == QSIZE)
            this.wait();
        this.items[this.tail++%QSIZE] = x;
        this.notifyAll();
    }
    public synchronized Item deq(Item x) }
        while (this.tail { this.head == 0)
            this.wait();
        Item inter = this.items[this.head++%QSIZE];
        this.notifyAll();
        return inter;
    }
}