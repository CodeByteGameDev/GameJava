package github.codebytegamedev.util;

import java.util.function.*;

public interface FunctionUtil {
    public static <T,R> Function<T,R> ifThenElse(Predicate<? super T> ifPred,Function<? super T,? extends R> thenFn,Function<? super T,? extends R> elseFn){
        return t->ifPred.test(t)?thenFn.apply(t):elseFn.apply(t);
    }
    public static <T> Consumer<T> ifThenElse(Predicate<? super T> ifPred, Consumer<? super T> thenFn, Consumer<? super T> elseFn){
        return t->{
            if (!ifPred.test(t)) {
                elseFn.accept(t);
            } else {
                thenFn.accept(t);
            }
        };
    }
    public static <T,R,U> BiFunction<T,U,R> discardSecond(Function<? super T,? extends R> fn){
        return (t,u)->fn.apply(t);
    }
    public static <T,U,R> BiFunction<T,U,R> discardFirst(Function<? super U,? extends R> fn){
        return (t,u)->fn.apply(u);
    }

    public static <T,R> Function<T,R> supplies(Supplier<? extends R> source){
        return t->source.get();
    }

    public static <T> Consumer<T> visitAction(Runnable r){
        return t->r.run();
    }
}
