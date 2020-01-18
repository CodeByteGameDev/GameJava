package github.codebytegamedev.tile;

import github.codebytegamedev.registry.RegistryEntry;
import github.codebytegamedev.registry.ResourceLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TileBank implements RegistryEntry<TileBank> {

    private final ResourceLocation name;
    private final List<Tile> tiles;

    private TileBank(ResourceLocation name, List<Tile> tiles) {
        this.name = name;
        this.tiles = tiles;
    }

    public static class Builder{
        private Builder(ResourceLocation loc){
            this.name = loc;
            this.tiles = new ArrayList<>();
        }
        private final ResourceLocation name;
        private List<Tile> tiles;
        public Builder addTile(Tile t){
            this.tiles.add(t);
            return this;
        }
        public TileBank build(){
            List<Tile> copied = tiles;
            tiles = null;
            return new TileBank(name,copied);
        }
    }

    @Override
    public Class<TileBank> getType() {
        return TileBank.class;
    }

    @Override
    public ResourceLocation getName() {
        return name;
    }

    public Optional<Tile> getTileById(int id){
        if(id<tiles.size())
            return Optional.of(tiles.get(id));
        else
            return Optional.empty();
    }
}
