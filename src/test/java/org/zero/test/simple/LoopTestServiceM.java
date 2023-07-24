package org.zero.test.simple;

public class LoopTestServiceM {
    public static void main(String[] args) {
        LoopTestServiceB b = new LoopTestServiceB();
        b.setName("b");
        LoopTestServiceA a = new LoopTestServiceA();
        a.setName("a");
        a.setServiceB(b);
        b.setServiceA(a);

        a.printName();
    }
}
