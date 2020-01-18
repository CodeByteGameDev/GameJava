package github.codebytegamedev.tile;

import github.codebytegamedev.event.EmptyData;
import github.codebytegamedev.event.EventBus;
import github.codebytegamedev.event.NullEventBus;
import github.codebytegamedev.registry.Registry;
import github.codebytegamedev.registry.RegistryEntry;
import github.codebytegamedev.registry.ResourceLocation;

import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class Tile implements RegistryEntry<Tile> {
    private final ResourceLocation name;
    private final float mu;
    private final Predicate<Direction> collidesInDirection;
    private final boolean updates;
    private final EventBus bus;
    // TODO add image repr

    public static final Predicate<Direction> COLLIDES_ALWAYS =  d->true;
    public static final Predicate<Direction> COLLIDES_NEVER = d->false;

    private static final Registry<Tile> tileRegistry = Registry.newRegistry("codebyte:tiles");

    public static class Builder{
        private ResourceLocation name;
        private float mu;
        private Predicate<Direction> collidesInDirection;
        private boolean updates;
        private EventBus bus;
        private Builder(ResourceLocation name){
            this.name = name;
            this.mu = 0.7f;
            this.collidesInDirection = COLLIDES_ALWAYS;
            this.updates = false;
            this.bus = NullEventBus.nullBus;
        }
        public Builder withFrictionCoefficient(float mu){
            if(mu<=0||mu>1)
                throw new IllegalArgumentException("The coefficient of friction must be in (0,1]");
            this.mu = mu;
            return this;
        }
        public Builder withCollisionChecker(Predicate<Direction> collisionCheck){
            this.collidesInDirection = Objects.requireNonNull(collisionCheck);
            return this;
        }

        public Builder withEventBus(EventBus bus){
            this.bus = bus;
            return this;
        }
        public Builder requestingContinuousUpdates(){
            this.updates = true;
            return this;
        }
        public Tile build(){
            return new Tile(name,mu,collidesInDirection,updates,bus);
        }
    }

    public static Builder newBuilder(ResourceLocation loc){
        return new Builder(loc);
    }
    public static Builder newBuilder(String loc){
        return new Builder(ResourceLocation.getResourceLocation(loc));
    }

    public double getFrictionForce(double normal){
        return normal*mu;
    }

    private Tile(ResourceLocation name, float mu, Predicate<Direction> collidesInDirection, boolean updates, EventBus bus) {
        this.name = name;
        this.mu = mu;
        this.collidesInDirection = collidesInDirection;
        this.updates = updates;
        this.bus = bus;
        tileRegistry.register(this);
    }


    @Override
    public Class<Tile> getType() {
        return Tile.class;
    }

    @Override
    public ResourceLocation getName() {
        return name;
    }

    public boolean checkCollision(Direction from){
        return this.collidesInDirection.test(from);
    }

    public void publishUpdate(boolean periodic){
        if(periodic||updates)
            bus.post(TileEventKeys.Update,this,new EmptyData());
    }
}
