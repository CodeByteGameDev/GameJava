package github.codebytegamedev.event;

/**
 * Represents an EventBus that ignores all events it receives.
 */
public final class NullEventBus implements EventBus {
    public static final NullEventBus nullBus = new NullEventBus();
    private NullEventBus(){}
    @Override
    public void post(EventKey key,Object receiver, EventData args) {}
}
