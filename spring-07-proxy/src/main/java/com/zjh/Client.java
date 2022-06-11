package com.zjh;

public class Client {
    public static void main(String[] args) {
        Host host = new Host();

        ProxyInvocationHandler pih = new ProxyInvocationHandler();
        pih.setTarget(host);
        Rent rent = (Rent) pih.getProxy();

        rent.rent();
    }
}
