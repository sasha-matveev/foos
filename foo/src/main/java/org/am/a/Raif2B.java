package org.am.a;

public class Raif2B {
    public static void main(String[] args) {
        MyCustomConsumer<String> a = str -> System.out.println("First " + str);
        a.then(s -> System.out.println("Second " + s))
                .then(s -> System.out.println("Third " + s))
                .then(s -> System.out.println("Fourth " + s))
                .consume("Hello");

    }

    @FunctionalInterface
    interface MyCustomConsumer<T> {
        void consume(T s);

        default MyCustomConsumer<T> then(MyCustomConsumer<T> consumer) {
            return obj -> {
                this.consume(obj);
                consumer.consume(obj);
            };
        }
    }
}
