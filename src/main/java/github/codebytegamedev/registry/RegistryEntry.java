package github.codebytegamedev.registry;

public interface RegistryEntry<E extends RegistryEntry<E>> {
    public Class<E> getType();
    public ResourceLocation getName();
}
