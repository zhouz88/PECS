import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

class Solution {
    public static void main(String[] args) {
        List<Employee> list = new ArrayList<>();
        list.add(new Boss());
        list.add(new Manager());
        list.add(new Manager());
        write(list);



        List<Boss> employess = new ArrayList<>();
        employess.add(new Boss());
        employess.add(new Boss());
        read(employess);
    }

    private static void writeBoss(List<? super Boss> list) {
        //list.add(new Manager());
        list.add(new Boss());
        list.add(new Boss());
    }

    private static void writeManager(List<? super Manager> list) {
        //list.add(new Employee());
        list.add(new Manager());
        list.add(new Boss());
        list.add(new Boss());
    }

    private static void read(List<? extends Employee> list) {
        System.out.println(list.get(0));
        System.out.println(list.get(1));
    }

    public static class Employee{}

    public static class Manager extends Employee{}

    public static class Boss extends Manager{}

    interface SB {
        void dosth();
    }

    static class SBimpl implements SB {

        @Override
        public void dosth() {
            System.out.println("hello");
        }
    }

    static class SBimp implements SB {

        @Override
        public void dosth() {
            System.out.println("hello");
        }
    }

    static public class SNK implements InvocationHandler {
        private Object o;
        public SNK(Object o) {
            this.o = o;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("Intercept first");
            method.invoke(o, args);
            return null;
        }
    }
}