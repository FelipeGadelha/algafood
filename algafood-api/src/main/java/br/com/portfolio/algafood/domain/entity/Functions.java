package br.com.portfolio.algafood.domain.entity;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public interface Functions<T> {

    default <R> R map(Function<T, R> func) {
        return func.apply((T) this);
    }
    default void also(Consumer<T> consumer) {
        consumer.accept((T) this);
    }
    default void run(Supplier<T> supplier) {
        supplier.get();
    }
    default T let(UnaryOperator<T> op) {
        return op.apply((T) this);
    }
}
