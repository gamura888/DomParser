package com.highpots.DomParser.util;


import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * this is a helper class that converts one ojbects values to another object using Reflection
 *
 * @param <M> Model
 * @param <E> Entity
 */

public class ClassUtil<M, E> {
    private static Logger log = Logger.getLogger(ClassUtil.class.getName());


    public M toModel(M m, E e) {
        if (m != null && e != null)
            convert(e, m);
        return m;
    }

    public E toEntity(M m, E e) {
        if (m != null && e != null)
            convert(m, e);
        return e;
    }


    /**
     * convert source object values to destination object
     *
     * @param source
     * @param dest
     */
    public static void convert(Object source, Object dest) {
        try {
            Class<?> destClass = dest.getClass();
            Class<?> sourceClass = source.getClass();
            for (Field sf : sourceClass.getDeclaredFields()) {
                sf.setAccessible(true);
                Field df = null;

                try {
                    df = destClass.getDeclaredField(sf.getName());
                } catch (NoSuchFieldException ex) {
                    // Ignore
                }

                if (df != null) {
                    df.setAccessible(true);
                    Object value = sf.get(source);
                    df.set(dest, value);
                }
            }
        } catch (Exception ex) {
            log.log(Level.ALL, ex.getMessage(), ex);
        }
    }

}
