package com.mageddo;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {
        final Class<?> clazz = Class.forName("sun.nio.ch.SelectorImpl");
        System.out.println(clazz);
        System.out.println(getUnsafe().objectFieldOffset(clazz.getDeclaredField("selectedKeys")));
        System.out.println(getUnsafe().objectFieldOffset(clazz.getDeclaredField("publicSelectedKeys")));
        System.out.println("end");
    }

    public static Unsafe getUnsafe(){
        return (Unsafe) AccessController.doPrivileged((PrivilegedAction<Object>) () -> {
            try {
                final Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
                // We always want to try using Unsafe as the access still works on java9 as well and
                // we need it for out native-transports and many optimizations.
                unsafeField.setAccessible(true);
                // the unsafe instance
                return unsafeField.get(null);
            } catch (NoSuchFieldException e) {
                return e;
            } catch (SecurityException e) {
                return e;
            } catch (IllegalAccessException e) {
                return e;
            } catch (NoClassDefFoundError e) {
                // Also catch NoClassDefFoundError in case someone uses for example OSGI and it made
                // Unsafe unloadable.
                return e;
            }
        });
    }
}
