package github.codebytegamedev.event;

import static github.codebytegamedev.util.FunctionUtil.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;

public interface EventBus {
    public <T extends EventData> void post(EventKey<T> key,Object receiver,T args);

    public static final class Builder{
        private Map<EventKey, BiConsumer<Object,? extends EventData>> handlers;
        private Builder(){
            this.handlers = new HashMap<>();
        }
        public <T extends EventData>  Builder handles(EventKey<T> key,BiConsumer<Object,T> handler){
            handlers.compute(key, discardFirst(ifThenElse(Objects::isNull,supplies(()->handler),fn->((BiConsumer<Object,T>)fn).andThen(handler))));
            return this;
        }
        private <T extends EventData> void handleEvent(EventKey<T> key,Object receiver,T args){
            if(handlers.containsKey(key))
                ((BiConsumer<Object,T>)handlers.get(key)).accept(receiver,args);
        }
        public EventBus build(){
            return this::handleEvent;
        }
    }
    public static Builder newBuilder(){
        return new Builder();
    }
}
