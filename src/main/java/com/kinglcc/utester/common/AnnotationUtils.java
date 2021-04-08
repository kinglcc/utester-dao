/*
 * ==========================================================================
 * utester-dao
 * ==========================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ==========================================================================
 */
package com.kinglcc.utester.common;

import java.lang.annotation.Annotation;
import java.lang.annotation.Inherited;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Retrieving and working with annotations.
 * 
 * @author kinglcc
 */
public class AnnotationUtils {

    /**
     * The attribute name for annotations with a single element
     */
    public static final String VALUE = "value";

    /**
     * Judge the annotation is inherited.
     * 
     * @param <A>
     * @param annotation Specifies the annotation class
     * @return True, if the annotation is marked with <code>@Inherited</code>
     */
    public static <A extends Annotation> boolean isInherited(Class<A> annotation) {
        return null != annotation && null != annotation.getAnnotation(Inherited.class);
    }

    /**
     * <pre>
     * Returns the given class's declared fields that are marked with the given annotation.
     * </pre>
     * 
     * @param clazz The class, not null
     * @param annotation The annotation, not null
     * @return A List containing fields annotated with the given annotation, empty list if none found
     */
    public static <A extends Annotation> Set<Field> getFieldsAnnotated(Class<? extends Object> clazz,
            Class<A> annotation) {
        if (Object.class.equals(clazz)) {
            return Collections.emptySet();
        }

        Set<Field> annotatedFields = new HashSet<Field>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (null != field.getAnnotation(annotation)) {
                annotatedFields.add(field);
            }
        }

        annotatedFields.addAll(getFieldsAnnotated(clazz.getSuperclass(), annotation));
        return annotatedFields;
    }

    /**
     * <pre>
     * Returns the given class's declared methods that are marked with the given annotation.
     * </pre>
     * 
     * @param clazz The class, not null
     * @param annotation The annotation, not null
     * @return A List containing methods annotated with the given annotation, empty list if none found
     */
    public static <A extends Annotation> Set<Method> getMethodsAnnotated(Class<?> clazz, Class<A> annotation) {
        if (Object.class.equals(clazz)) {
            return Collections.emptySet();
        }
        Set<Method> annotatedMethods = new HashSet<Method>();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            Object o = method.getAnnotation(annotation);
            if (o != null) {
                annotatedMethods.add(method);
            }
        }

        annotatedMethods.addAll(getMethodsAnnotated(clazz.getSuperclass(), annotation));
        return annotatedMethods;
    }

    public static <A extends Annotation> A getClassAnnotation(Class<?> clazz, Class<A> annotationType) {
        if (Object.class.equals(clazz)) {
            return null;
        }

        A annotation = (A) clazz.getAnnotation(annotationType);
        if (null != annotation || !isInherited(annotationType)) {
            return annotation;
        }
        return getClassAnnotation(clazz.getSuperclass(), annotationType);
    }

    @SuppressWarnings("unchecked")
    public static <A extends Annotation> A getAnnotation(Annotation ann, Class<A> annotationType) {
        if (annotationType.isInstance(ann)) {
            return (A) ann;
        }
        return ann.annotationType().getAnnotation(annotationType);
    }

    public static <A extends Annotation> A getAnnotation(AnnotatedElement ae, Class<A> annotationType) {
        A ann = ae.getAnnotation(annotationType);
        if (null == ann) {
            for (Annotation metaAnn : ae.getAnnotations()) {
                ann = getAnnotation(metaAnn, annotationType);
                if (ann != null) {
                    break;
                }
            }
        }
        return ann;
    }

}
