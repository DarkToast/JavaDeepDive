package deepdive.typesystem;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AdvancedGenerics {

    // tag::substitutability[]
    /**
     * The Liskov substitution principle
     */
    static class A {
        List invariant(List l) {
            return l;
        }

        List covariant() {
            return new ArrayList();
        }

        void contravariant(LinkedList l) {
        }
    }

    static class B extends A {
        @Override
        List invariant(List l) {
            return l;
        }

        @Override
        LinkedList covariant() {
            return new LinkedList();
        }

        void contravariant(List l) {
        }
    }
    // end::substitutability[]


    // tag::bounded_types[]
    /**
     * Covariant bounded type
     * @param <T>
     */
    static class C <T extends List<String>> {

        List invariant(T list) {
            return list.subList(0, 1);
        }

        T covariant(T list) {
            list.add("hallo");
            return list;
        }

        static void usage() {
            C<LinkedList<String>> c = new C<>();
            c.covariant(new LinkedList<>());
            // c.covariant(new ArrayList<>());  <-- compile error
        }
    }
    // end::bounded_types[]


    // tag::bounded_wildcards[]
    /**
     * B --> A --> Object
     *
     * Covariant lower bounded wildcards in methods.
     */
    abstract static class D {

        void covariant(List<? extends A> l) {
            A a = l.get(0);
        }

        void usage() {
            covariant(new ArrayList<A>());
            covariant(new ArrayList<B>());
            // covariant(new ArrayList<Object>()); <-- compile error
        }
    }

    /**
     * Contravariant upper bounded wildcards in methods.
     */
    abstract static class E {
        void contravariant(List<? super B> l) {
            l.add(new B());
            // l.add(new A());  <-- compile error
        }

        void usage() {
            contravariant(new ArrayList<A>());
            contravariant(new ArrayList<B>());
            // contravariant(new ArrayList<Object>()); <-- compile error
        }
    }

    /**
     * unbounded type with wildcards
     */
    abstract static class F {
        abstract void unbounded(List<?> l);

        void usage() {
            unbounded(new ArrayList<Object>());
            unbounded(new ArrayList<A>());
            unbounded(new ArrayList<String>());
        }
    }
    // end::bounded_wildcards[]


    // tag::recursive_types_simple[]
    /**
     * Recursive generic types. A simple example without recursion.
     *
     * @param <T>
     */
    interface FromJsonSimple<T> {
        default T fromJson(Class<T> clazz, final String json) {
            try {
                return new ObjectMapper().reader().forType(clazz).readValue(json);
            } catch (IOException e) {
                return null;
            }
        }
    }

    static class MyJsonType implements FromJsonSimple<List> {
        List usage() {
            return this.fromJson(List.class, "[]");    // Why should FromJsonSimple allow to extended by the MyJsonType type as List-typed?
        }
    }
    // end::recursive_types_simple[]


    // tag::recursive_types[]
    /**
     * Recursive generic types. A recursive typed interface, which only allows to be typed with the implementing type.
     *
     * @param <T>
     */
    interface FromJson<T extends FromJson<T>> {
        default T fromJson(Class<T> clazz, final String json) {
            try {
                return new ObjectMapper().reader().forType(clazz).readValue(json);
            } catch (IOException e) {
                return null;
            }
        }
    }

    static class MyNewJsonType implements FromJson<MyNewJsonType> { // <-- Only MyNewJsonType is allowed here.
        MyNewJsonType usage() {
            return this.fromJson(MyNewJsonType.class, "[]");    // Why should FromJsonSimple allow to extended by the MyNewJsonType type as List-typed?
        }
    }
    // tag::recursive_types[]

}
