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
package com.kinglcc.utester.db.listener;

import com.kinglcc.utester.db.annotation.JpaSupport;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

/**
 * Abstract implementation of the TestExecutionListener interface which provides JPA
 * 
 * @author kinglcc
 */
public class JpaTestExecutionListener extends AbstractTestExecutionListener {

    private JpaSupport jpaSupport;

    @Override
    public void beforeTestClass(TestContext testContext) throws Exception {
        jpaSupport = new JpaSupport();
        jpaSupport.jpaBinder(testContext.getTestClass(), testContext.getApplicationContext());
    }

    @Override
    public void afterTestClass(TestContext testContext) throws Exception {
        if (null != jpaSupport) {
            jpaSupport.rollback();
        }
    }

    @Override
    public int getOrder() {
        return 999;
    }

}
