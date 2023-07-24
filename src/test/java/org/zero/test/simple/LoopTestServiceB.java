package org.zero.test.simple;

public class LoopTestServiceB implements Naming {
    private String name;
    private LoopTestServiceA serviceA;

    @Override
    public void printName() {
        serviceA.printName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LoopTestServiceA getServiceA() {
        return serviceA;
    }

    public void setServiceA(LoopTestServiceA serviceA) {
        this.serviceA = serviceA;
    }
}
