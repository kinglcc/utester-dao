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

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;

import com.kinglcc.utester.common.AnnotationUtils;

/**
 * The implement of the annotation Jpa
 * 
 * @author kinglcc
 */
public class JpaSupport {

    private SchemaExport schemaExport;

    /**
     * Bind the annotation <code>@Jpa</code>
     * 
     * @param clazz The test class
     * @param applicationContext The spring application context
     * @return JpaSupport
     * @throws IOException I/O exception
     */
    public JpaSupport jpaBinder(Class<?> clazz, ApplicationContext applicationContext) throws IOException {

        Jpa jpa = AnnotationUtils.getClassAnnotation(clazz, Jpa.class);
        if (null != jpa) {
            LocalSessionFactoryBuilder sfb =
                    new LocalSessionFactoryBuilder(applicationContext.getBean(DataSource.class), applicationContext);

            sfb.addAnnotatedClasses(jpa.value());
            sfb.scanPackages(jpa.packageToScan());

            Resource resource = applicationContext.getResource(jpa.jpaConfig());
            Properties props = PropertiesLoaderUtils.loadProperties(resource);
            sfb.addProperties(props);
            sfb.buildMappings();

            schemaExport = new SchemaExport(sfb);
            schemaExport.create(false, true);
        }
        return this;
    }

    /**
     * Rollback the database
     */
    public void rollback() {
        if (null != schemaExport) {
            schemaExport.drop(false, true);
        }
    }
}
