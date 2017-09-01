package deepdive.typesystem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TypeErasure {
    public static void main(String[] args) {
        new TypeErasure().typeErasureStatic();
    }

    void dynamicVsStatic() {
    // tag::dynamic_static_types[]
        // --------------
        // static types:
        List aList = new ArrayList();

        // still List
        aList      = new LinkedList();


        // --------------
        // dynamic types - on initialization
        List bList = null;

        // now `bList` is also ArrayList.
        bList      = new ArrayList();
    // end::dynamic_static_types[]
    }


    // tag::type_erasure[]
    void typeErasureStatic() {
        // static types:
        List<String> strList = new ArrayList<>();
        List<Integer> intList = new ArrayList<>();
    }

    void typeErasureDynamic() {
        // dynamic types
        ArrayList<Object> intList = new ArrayList<>();
        ArrayList<Object> strList = new ArrayList<>();

    }
    // end::type_erasure[]



    // tag::type_erasure_pitfall[]
    void doSomethingWithAListAndAnObject(List list, Object element) {
        list.add(element);
    }


    void typeErasurePitfall() {
        List<String> list = new ArrayList<>();
        list.add("Hallo Welt");

        doSomethingWithAListAndAnObject(list, new Object());

        String value1 = list.get(0).toLowerCase();
        String value2 = list.get(1).toLowerCase();  // Will crash with a TypeCastException
    }
    // end::type_erasure_pitfall[]


}
