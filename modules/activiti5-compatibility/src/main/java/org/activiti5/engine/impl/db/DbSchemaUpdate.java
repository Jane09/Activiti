/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.activiti5.engine.impl.db;

import org.activiti5.engine.ProcessEngines;
import org.activiti5.engine.impl.ProcessEngineImpl;
import org.activiti5.engine.impl.interceptor.Command;
import org.activiti5.engine.impl.interceptor.CommandConfig;
import org.activiti5.engine.impl.interceptor.CommandContext;
import org.activiti5.engine.impl.interceptor.CommandExecutor;

/**
 * @author Tom Baeyens
 */
public class DbSchemaUpdate {

  public static void main(String[] args) {
    ProcessEngineImpl processEngine = (ProcessEngineImpl) ProcessEngines.getDefaultProcessEngine();
    CommandExecutor commandExecutor = processEngine.getProcessEngineConfiguration().getCommandExecutor();
    CommandConfig config = new CommandConfig().transactionNotSupported();
    commandExecutor.execute(config, new Command<Object>() {
      public Object execute(CommandContext commandContext) {
        commandContext.getSession(DbSqlSession.class).dbSchemaUpdate();
        return null;
      }
    });
  }

}
