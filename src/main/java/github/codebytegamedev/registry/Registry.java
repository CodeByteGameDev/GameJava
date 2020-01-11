package github.codebytegamedev.registry;

import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

public final class Registry<E extends RegistryEntry<E>> implements RegistryEntry<Registry<?>> {
    private final Map<ResourceLocation,E> backing;
    private final ResourceLocation name;
    private Registry(ResourceLocation name){
        this.backing = new TreeMap<>();
        this.name = name;
    }

    public static Registry<Registry<?>> registryRegistry = new Registry<Registry<?>>(ResourceLocation.getResourceLocation("codebyte","registries"));

    public static <E extends RegistryEntry<E>> Registry<E> newRegistry(ResourceLocation loc){
        Registry<E> reg = new Registry<>(loc);
        registryRegistry.register(reg);
        return reg;
    }

    public void register(E object){
        if(backing.putIfAbsent(object.getName(),object)!=null)
            throw new IllegalArgumentException("Object with given name already exists");
    }

    public Stream<E> entries(){
        return backing.values().stream();
    }

    @Override
    public Class<Registry<?>> getType() {
        //noinspection unchecked
        return (Class<Registry<?>>)(Class<? extends Registry>)Registry.class;
    }

    @Override
    public ResourceLocation getName() {
        return name;
    }
}
