package github.codebytegamedev.registry;


import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.regex.qual.Regex;

import java.util.Objects;


public final class ResourceLocation implements Comparable<ResourceLocation> {
    private final String domain;
    private final String path;
    private static final @Regex String domainPattern = "[a-z_][a-z0-9_]*";
    private static final @Regex String pathPattern = "[a-z_][a-z0-9_]*([a-z_][a-z0-9_]*)*";
    private static final @Regex String pattern = "[a-z_][a-z0-9_]*:[a-z_][a-z0-9_]*([a-z_][a-z0-9_]*)*";
    private ResourceLocation(String domain,String path){
        this.domain = domain;
        this.path = path;
    }
    private ResourceLocation(String[] parts){
        this(parts[0],parts[1]);
    }
    public static ResourceLocation getResourceLocation(@NonNull String location){
        if(!location.matches(pattern))
            throw new IllegalArgumentException("Resource Location must match [a-z_][a-z0-9_]*:[a-z_][a-z0-9_]*([a-z_][a-z0-9_]*)*");
        return new ResourceLocation(location.split(":"));
    }
    public static ResourceLocation getResourceLocation(@NonNull String domain,@NonNull String path){
        if(!domain.matches(domainPattern)||!path.matches(pathPattern))
            throw new IllegalArgumentException("Resource Location must match [a-z_][a-z0-9_]*:[a-z_][a-z0-9_]*([a-z_][a-z0-9_]*)*");
        return new ResourceLocation(domain,path);
    }
    @Override
    public int compareTo(@NonNull ResourceLocation other) {
        int cmp;
        if((cmp=domain.compareTo(other.domain))!=0)
            return cmp;
        else
            return path.compareTo(other.path);
    }

    public @NonNull String getDomain(){
        return domain;
    }
    public @NonNull String getPath(){
        return path;
    }

    public @NonNull String toString(){
        return domain+":"+path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResourceLocation that = (ResourceLocation) o;
        return domain.equals(that.domain) &&
                path.equals(that.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(domain, path);
    }
}
