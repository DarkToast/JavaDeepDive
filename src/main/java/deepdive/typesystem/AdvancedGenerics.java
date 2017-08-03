package deepdive.typesystem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AdvancedGenerics {

    public static void main(String[] args) {

        List<String> strList = new ArrayList<>();
//        List<Object> objList = strList;

        // List<String> is not a subtype of List<Object>, even
        // String is a subtype of Object.

    }


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


    /**
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
}
