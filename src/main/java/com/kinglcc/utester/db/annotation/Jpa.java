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
package com.kinglcc.utester.db.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is applied to the database test class. Specifies that the test class need use jpa.
 * 
 * @author kinglcc
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Jpa {

    /**
     * Annotationed entity classes.<br>
     * Specifies that the classes will be mapped.
     * 
     * @return the annotation class
     */
    Class<?>[] value();

    /**
     * Specifies that the classes in packages will be mapped.
     * 
     * @return the packages need mapped
     */
    String[] packageToScan() default {};

    /**
     * Specifies that the jpa configure properties file location.<br>
     * default configure is
     * 
     * <pre>
     * classpath:test-jpa.properties
     * </pre>
     * 
     * @return the configuration for jpa
     */
    String jpaConfig() default "classpath:utester.properties";
}
