package michael.linker.gestureid.config;

public abstract class ConfigurationTypedBean<T, V extends Enum<V>> extends ConfigurationBean<T> {
    private V implementationType;

    public ConfigurationTypedBean(T defaultImplementation) {
        super(defaultImplementation);
    }

    /**
     * Implement with type.
     *
     * @param newImplementationType V
     * @return new implementation.
     */
    public abstract T implement(V newImplementationType);

    protected void setImplementationType(V newImplementationType) {
        implementationType = newImplementationType;
    }

    public V getImplementationType() {
        return implementationType;
    }
}
