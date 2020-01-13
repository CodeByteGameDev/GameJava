package github.codebytegamedev.event;

public interface EventBus {
    public void post(EventKey key,Object... args);
}
