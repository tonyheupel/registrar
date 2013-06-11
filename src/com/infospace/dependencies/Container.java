package com.infospace.dependencies;

import java.util.concurrent.ConcurrentHashMap;

public class Container {
    private static ConcurrentHashMap<Class<?>, Object> map = new ConcurrentHashMap<Class<?>, Object>();

    public static class TypeNotRegisteredException extends RuntimeException {
        public TypeNotRegisteredException() {
            super();
        }

        public TypeNotRegisteredException(String message) {
            super(message);
        }
    }

    public static <T extends Object> void register(Class<T> type, T instance) {
        map.put(type, instance);
    }

    public static <T extends Object> void register(Class<T> type, Class<?> concreteType) {
        map.put(type, concreteType);
    }

    public static <T extends Object> T resolve(Class<T> type) throws TypeNotRegisteredException {
        if (!map.containsKey(type))
            throw new TypeNotRegisteredException(String.format("%s is not registered.", type.getSimpleName()));

        return type.cast(map.get(type));
    }

    public static <T extends Object> void remove(Class<T> type) {
        map.remove(type);
    }
}
