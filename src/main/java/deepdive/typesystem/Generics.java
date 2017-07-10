package deepdive.typesystem;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Generics {

    /**
     * The use of argument types in other classes.
     * As an example, the List types.
     */
    void typeArgumentedTypes() {
        List<String> stringList = new ArrayList<>();
        List<Integer> intList = new ArrayList<>();

        // stringList = intList;        <-- not possible
        // stringList.addAll(intList);  <-- also not possible

        List<Object> objectList = new ArrayList<>();
        // objectList = stringList;     <-- not possible

        objectList.addAll(stringList);
        objectList.addAll(intList);
    }


    /**
     * We can also use type arguments in an extend statement.
     * Here, we define a static typed integer list.
     */
    static class IntegerList extends ArrayList<Integer> {
    }

    void useOfIntegerList() {
        IntegerList intList = new IntegerList();
        intList.add(1);
        // intList.add("hallo");    <-- Won't work
    }


    /**
     * To make a generic class, we can define type arguments to the class definition.
     * The `E` is just a placeholder. It can be every string like every other variable.
     * By convention the type parameters are written in upper case letters.
     *
     * @param <E>
     */
    static class ElementAdder<E> {

        List<E> addElement(List<E> list, E element) {
            list.add(element);
            return list;
        }

    }

    void useOfElementAdder() {
        ElementAdder<String> stringAdder = new ElementAdder<>();
        stringAdder.addElement(new ArrayList<>(), "hallo Welt");

        ElementAdder<Double> doubleAdder = new ElementAdder<>();
        doubleAdder.addElement(new ArrayList<>(), 42d);
    }


    /**
     * We can also define typed methods. So the type argument is not related to the class scope, but only to
     * the method scope. The type parameter
     */
    static class TypedMethods {

        <E> List<E> addElement(List<E> list, E element) {
            list.add(element);
            return list;
        }

        <F> F getFirstElement(List<F> list) {
            return list.get(0);
        }

        @SuppressWarnings("unchecked")
        <G> G create(Class clazz) {
            try {
                Constructor<?> ctor = clazz.getConstructor();
                return (G) ctor.newInstance();
            } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    void useTypedMethods() {
        TypedMethods typedMethods = new TypedMethods();

        List<Integer> intList = new ArrayList<>();
        List<String> stringList = new ArrayList<>();


        intList = typedMethods.addElement(intList, 42);
        int i = typedMethods.getFirstElement(intList);


        stringList = typedMethods.addElement(stringList, "Hallo Welt");
        String s = typedMethods.getFirstElement(stringList);

        // Type parameter on method invocation.
        final List<Integer> integers = typedMethods.<IntegerList>create(IntegerList.class);
    }
}
