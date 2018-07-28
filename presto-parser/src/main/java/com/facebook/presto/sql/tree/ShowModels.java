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

public class ShowModels extends Statement {

    private final Optional<Identifier> catalog;

    public ShowModels(NodeLocation location, Optional<Identifier> catalog) {
        this(Optional.of(location), catalog);
    }

    private ShowModels(Optional<NodeLocation> location, Optional<Identifier> catalog) {
        super(location);
        this.catalog = catalog;
    }

    @Override
    public List<? extends Node> getChildren() {
        return ImmutableList.of();
    }

    @Override
    public <R, C> R accept(AstVisitor<R, C> visitor, C context) {
        return visitor.visitShowModels(this, context);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShowModels)) return false;
        ShowModels that = (ShowModels) o;
        return Objects.equals(catalog, that.catalog);
    }

    @Override
    public int hashCode() {
        return Objects.hash(catalog);
    }

    @Override
    public String toString() {
        return "ShowModels{" +
                "catalog=" + catalog +
                '}';
    }

    public Optional<Identifier> getCatalog() {
        return catalog;
    }
}
