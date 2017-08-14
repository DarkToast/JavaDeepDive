package deepdive.typesystem;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Generics {

    // tag::old_lists[]
    String oldLists() {
        List myList = new ArrayList();

        myList.add("Hallo Welt");
        myList.add(42);
        myList.add(new Object());

        return (String) myList.get(0);  // <-- is it a String???
    }
    // end::old_lists[]

    /**
     * The use of argument types in other classes.
     * As an example, the List types.
     */
    void typeArgumentedTypes() {
        // tag::generics_in_use[]
        List<String> stringList = new ArrayList<>();
        List<Integer> intList = new ArrayList<>();

        // stringList = intList;        <-- not possible
        // stringList.addAll(intList);  <-- also not possible
        // end::generics_in_use[]


        // tag::generic_type_not_polymorphic[]

        List<Object> objectList = new ArrayList<>();
        // objectList = stringList;     <-- not possible

        // end::generic_type_not_polymorphic[]
    }

    /**
     * We can also use type arguments in an extend statement.
     * Here, we define a static typed integer list.
     */
    // tag::typed_class[]
    static class IntegerList extends ArrayList<Integer> {
    }

    void useOfIntegerList() {
        IntegerList intList = new IntegerList();
        intList.add(1);
        // intList.add("hallo");    <-- Won't work
    }
    // end::typed_class[]


    /**
     * To make a generic class, we can define type arguments to the class definition.
     * The `E` is just a placeholder. It can be every string like every other variable.
     * By convention the type parameters are written in upper case letters.
     *
     * @param <E>
     */
    // tag::generic_class[]
    static class ElementAdder<E> {
        List<E> addElement(List<E> list, E element) {
            list.add(element);
            return list;
        }
    }


    void useOfElementAdder() {
        ElementAdder<String> stringAdder = new ElementAdder<>();
        List<String> strList = stringAdder.addElement(new ArrayList<>(), "hallo Welt");

        ElementAdder<Double> doubleAdder = new ElementAdder<>();
        List<Double> doubleList = doubleAdder.addElement(new ArrayList<>(), 42d);
    }
    // end::generic_class[]


    // tag::typed_methods[]
    /**
     * We can also define typed methods. So the type argument is not related to the class scope, but only to
     * the method scope.
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

        // Type parameter on method invocation.
        final List<Integer> integers = typedMethods.<IntegerList>create(IntegerList.class);

        // Usage of typed methods, with interfered type information.
        List<Integer> intList = new ArrayList<>();
        List<String> stringList = new ArrayList<>();


        intList = typedMethods.addElement(intList, 42);
        int i = typedMethods.getFirstElement(intList);


        stringList = typedMethods.addElement(stringList, "Hallo Welt");
        String s = typedMethods.getFirstElement(stringList);
    }
    // end::typed_methods[]
}
