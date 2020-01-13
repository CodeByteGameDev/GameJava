package github.codebytegamedev.event;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class EventKey implements Comparable<EventKey> {
    private final String name;

    private EventKey(String name){
        this.name = name;
    }

    public static EventKey getKey(@NotNull String name){
        return new EventKey(Objects.requireNonNull(name));
    }

    @Override
    public int compareTo(@NotNull EventKey eventKey) {
        return name.compareTo(eventKey.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventKey eventKey = (EventKey) o;
        return name.equals(eventKey.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
