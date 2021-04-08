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
package com.kinglcc.utester.db.junit;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.kinglcc.utester.core.Utester;
import com.kinglcc.utester.db.listener.JpaTestExecutionListener;

/**
 * The base dao test for junit
 * 
 * @author kinglcc
 */
@ActiveProfiles("dao")
@TestExecutionListeners({ JpaTestExecutionListener.class })
@TransactionConfiguration(defaultRollback = true)
public class JUnitUtester extends AbstractTransactionalJUnit4SpringContextTests implements Utester {

}
