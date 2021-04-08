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
package com.kinglcc.utester.db.testng;

import com.kinglcc.utester.db.listener.JpaTestExecutionListener;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.kinglcc.utester.core.Utester;

/**
 * The base dao test for TestNG
 * 
 * author liaochaochao
 */
@ActiveProfiles("dao")
@TestExecutionListeners({ JpaTestExecutionListener.class })
@TransactionConfiguration(defaultRollback = true)
public class TestNGUtester extends AbstractTransactionalTestNGSpringContextTests implements Utester {

}
