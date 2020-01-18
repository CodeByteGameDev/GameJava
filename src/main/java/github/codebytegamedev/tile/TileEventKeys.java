package github.codebytegamedev.tile;

import github.codebytegamedev.event.EmptyData;
import github.codebytegamedev.event.EventKey;

public interface TileEventKeys {
    public static EventKey<EmptyData> Update = EventKey.create();
}
