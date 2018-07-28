/*
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
 */
package com.facebook.presto.execution;

import com.facebook.presto.Session;
import com.facebook.presto.metadata.Metadata;
import com.facebook.presto.security.AccessControl;
import com.facebook.presto.sql.analyzer.SemanticException;
import com.facebook.presto.sql.tree.CreateModel;
import com.facebook.presto.sql.tree.Expression;
import com.facebook.presto.sql.tree.QualifiedName;
import com.facebook.presto.transaction.TransactionManager;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

import static com.facebook.presto.sql.analyzer.SemanticErrorCode.INVALID_MODEL_NAME;
import static com.google.common.util.concurrent.Futures.immediateFuture;

public class CreateModelTask implements DataDefinitionTask<CreateModel> {

    @Override
    public String getName() {
        return "CREATE MODEL";
    }

    @Override
    public ListenableFuture<?> execute(CreateModel statement, TransactionManager transactionManager, Metadata metadata, AccessControl accessControl, QueryStateMachine stateMachine, List<Expression> parameters) {
        Session session = stateMachine.getSession();
        QualifiedName modelQName = statement.getModelName();
        if (modelQName.getParts().size() != 2) {
            throw new SemanticException(INVALID_MODEL_NAME, statement, "Too many parts in schema name: %s", modelQName);
        }
        String catalogName = modelQName.getParts().get(0);
        String modelName = modelQName.getSuffix();
        metadata.createModel(session, catalogName, modelName);
        return immediateFuture(null);
    }

    @Override
    public String explain(CreateModel statement, List<Expression> parameters) {
        return "CREATE MODEL " + statement.getModelName();
    }
}
