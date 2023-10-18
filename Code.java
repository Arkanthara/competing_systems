public class MyCode {
    public static void main(String args[]) {
        Queue q = new Queue();

        MyThread_enq enq_1 = new MyThread_enq(q);
        MyThread_deq deq_1 = new MyThread_deq(q);
        MyThread_enq enq_2 = new MyThread_enq(q);
        MyThread_deq deq_2 = new MyThread_deq(q);

        deq_1.start();
        deq_2.start();
        enq_1.start();
        enq_2.start();
    }
    
}
public class Queue {
    private int head = 0, tail = 0;
    final int QSIZE=1;
    private int[] items;

    public Queue() {
        this.items = new int[QSIZE];
    }

    public synchronized void enq(int x) {
        while (this.tail - this.head == QSIZE) {
            try {
                this.wait();
            } catch (Exception e) {}
        }
        this.items[this.tail++%QSIZE] = x;
        System.out.println("Successfully enqueue !");
        this.notifyAll();
    }
    public synchronized int deq() {
        while (this.tail - this.head == 0) {
            try {
                this.wait();
            } catch (Exception e) {}
        }
        int inter = this.items[this.head++%QSIZE];
        System.out.println("successfully dequeue!");
        this.notifyAll();
        return inter;
    }
}

public class MyThread_enq extends Thread {
    private volatile Queue q;

    public MyThread_enq(Queue q) {
        this.q = q;
    }
    
    public void run() {
        this.q.enq(1);
    }
} 

public class MyThread_deq extends Thread {
    private volatile Queue q;

    public MyThread_deq(Queue q) {
        this.q = q;
    }
    
    public void run() {
        this.q.deq();
    }
}
