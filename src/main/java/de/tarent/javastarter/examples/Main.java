package de.tarent.javastarter.examples;

public class Main {

    public static void main(String[] args) {
        
        Class c = new Class(3, "hallo");
        Object o = new Class(4, "Welt");


        IntGetter intGetter = new Class(34, "Hallo Welt");

        System.out.println(Class.getCounter());


    }
}
