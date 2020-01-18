package github.codebytegamedev.event;

public final class EventKey<T extends EventData> {

    private EventKey(){}
    public static <T extends EventData> EventKey<T> create(){
        return new EventKey<>();
    }
}
