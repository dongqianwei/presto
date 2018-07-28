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
package com.facebook.presto.util;

import com.facebook.presto.spi.resourceGroups.QueryType;
import com.facebook.presto.sql.tree.*;
import com.google.common.collect.ImmutableMap;

import java.util.Map;
import java.util.Optional;

public class StatementUtils
{
    private StatementUtils() {}

    private static final Map<Class<? extends Statement>, QueryType> STATEMENT_QUERY_TYPES;

    static {
        ImmutableMap.Builder<Class<? extends Statement>, QueryType> builder = ImmutableMap.builder();
        builder.put(Query.class, QueryType.SELECT);

        builder.put(Explain.class, QueryType.EXPLAIN);

        builder.put(CreateTableAsSelect.class, QueryType.INSERT);
        builder.put(Insert.class, QueryType.INSERT);

        builder.put(Delete.class, QueryType.DELETE);

        builder.put(ShowCatalogs.class, QueryType.DESCRIBE);
        builder.put(ShowModels.class, QueryType.DESCRIBE);
        builder.put(ShowCreate.class, QueryType.DESCRIBE);
        builder.put(ShowFunctions.class, QueryType.DESCRIBE);
        builder.put(ShowGrants.class, QueryType.DESCRIBE);
        builder.put(ShowPartitions.class, QueryType.DESCRIBE);
        builder.put(ShowSchemas.class, QueryType.DESCRIBE);
        builder.put(ShowSession.class, QueryType.DESCRIBE);
        builder.put(ShowStats.class, QueryType.DESCRIBE);
        builder.put(ShowTables.class, QueryType.DESCRIBE);
        builder.put(ShowColumns.class, QueryType.DESCRIBE);
        builder.put(DescribeInput.class, QueryType.DESCRIBE);
        builder.put(DescribeOutput.class, QueryType.DESCRIBE);

        builder.put(CreateSchema.class, QueryType.DATA_DEFINITION);
        builder.put(DropSchema.class, QueryType.DATA_DEFINITION);
        builder.put(RenameSchema.class, QueryType.DATA_DEFINITION);
        builder.put(AddColumn.class, QueryType.DATA_DEFINITION);
        builder.put(CreateTable.class, QueryType.DATA_DEFINITION);
        builder.put(RenameTable.class, QueryType.DATA_DEFINITION);
        builder.put(CreateModel.class, QueryType.DATA_DEFINITION);
        builder.put(DeleteModel.class, QueryType.DATA_DEFINITION);
        builder.put(RenameColumn.class, QueryType.DATA_DEFINITION);
        builder.put(DropColumn.class, QueryType.DATA_DEFINITION);
        builder.put(DropTable.class, QueryType.DATA_DEFINITION);
        builder.put(CreateView.class, QueryType.DATA_DEFINITION);
        builder.put(DropView.class, QueryType.DATA_DEFINITION);
        builder.put(Use.class, QueryType.DATA_DEFINITION);
        builder.put(SetSession.class, QueryType.DATA_DEFINITION);
        builder.put(ResetSession.class, QueryType.DATA_DEFINITION);
        builder.put(StartTransaction.class, QueryType.DATA_DEFINITION);
        builder.put(Commit.class, QueryType.DATA_DEFINITION);
        builder.put(Rollback.class, QueryType.DATA_DEFINITION);
        builder.put(Call.class, QueryType.DATA_DEFINITION);
        builder.put(Grant.class, QueryType.DATA_DEFINITION);
        builder.put(Revoke.class, QueryType.DATA_DEFINITION);
        builder.put(Prepare.class, QueryType.DATA_DEFINITION);
        builder.put(Deallocate.class, QueryType.DATA_DEFINITION);
        builder.put(SetPath.class, QueryType.DATA_DEFINITION);
        STATEMENT_QUERY_TYPES = builder.build();
    }

    public static Map<Class<? extends Statement>, QueryType> getAllQueryTypes()
    {
        return STATEMENT_QUERY_TYPES;
    }

    public static Optional<QueryType> getQueryType(Class<? extends Statement> statement)
    {
        return Optional.ofNullable(STATEMENT_QUERY_TYPES.get(statement));
    }
}
