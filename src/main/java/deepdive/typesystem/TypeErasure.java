package deepdive.typesystem;

import java.util.ArrayList;
import java.util.List;

public class TypeErasure {
    public static void main(String[] args) {
        new TypeErasure().typeErasure();
    }


    // tag::type_erasure[]
    void typeErasure() {
        List<String> strList = new ArrayList<>();
        List<Integer> intList = new ArrayList<>();
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
        String value2 = list.get(1).toLowerCase();
    }
    // end::type_erasure_pitfall[]


}
