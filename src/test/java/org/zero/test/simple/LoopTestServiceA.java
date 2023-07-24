package org.zero.test.simple;

public class LoopTestServiceA implements Naming {
    private String name;
    private LoopTestServiceB serviceB;

    @Override
    public void printName() {
        serviceB.printName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LoopTestServiceB getServiceB() {
        return serviceB;
    }

    public void setServiceB(LoopTestServiceB serviceB) {
        this.serviceB = serviceB;
    }
}
