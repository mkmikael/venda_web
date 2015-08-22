/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blacksoftware.webvenda.util;

import java.lang.reflect.Field;
import javax.persistence.Id;

/**
 *
 * @author Avell G1511
 */
public class ReflectionUtil {

    public static Object getIdFromAnnotation(Object o) {
        Field[] declaredFields = o.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (declaredField.isAnnotationPresent(Id.class)) {
                if (!declaredField.isAccessible()) {
                    declaredField.setAccessible(true);
                }
                try {
                    return declaredField.get(o);
                } catch (Exception e) {
                    return null;
                }
            }
        }
        return null;
    }

    public static Object getId(Object o) {
        try {
            Field declaredField = o.getClass().getDeclaredField("id");
            if (!declaredField.isAccessible()) {
                declaredField.setAccessible(true);
            }
            return declaredField.get(o);
        } catch (Exception e) {
            return null;
        }
    }
}
