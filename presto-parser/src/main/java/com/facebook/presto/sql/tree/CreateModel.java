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
package com.facebook.presto.sql.tree;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CreateModel extends Statement {

    private QualifiedName modelName;

    public CreateModel(NodeLocation location, QualifiedName modelName) {
        this(Optional.of(location), modelName);
    }

    private CreateModel(Optional<NodeLocation> location, QualifiedName modelName) {
        super(location);
        this.modelName = modelName;
    }

    @Override
    public List<? extends Node> getChildren() {
        return ImmutableList.of();
    }

    public QualifiedName getModelName() {
        return modelName;
    }

    @Override
    public <R, C> R accept(AstVisitor<R, C> visitor, C context) {
        return visitor.visitCreateModel(this, context);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateModel)) return false;
        CreateModel that = (CreateModel) o;
        return Objects.equals(modelName, that.modelName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modelName);
    }

    @Override
    public String toString() {
        return "CreateModel{" +
                "modelName=" + modelName +
                '}';
    }
}
