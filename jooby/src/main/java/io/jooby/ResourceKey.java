/**
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
 *
 * Copyright 2014 Edgar Espina
 */
package io.jooby;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

/**
 * Utility class which creates a String key from type and optionally a name.
 *
 * @param <T>
 */
public final class ResourceKey<T> {
  private final Class<T> type;

  private final int hashCode;

  private final String name;

  private ResourceKey(Class<T> type, String name) {
    this.type = type;
    this.name = name;
    this.hashCode = Objects.hash(type, name);
  }

  public @Nonnull Class<T> getType() {
    return type;
  }

  public @Nullable String getName() {
    return name;
  }

  @Override public boolean equals(Object obj) {
    if (obj instanceof ResourceKey) {
      ResourceKey that = (ResourceKey) obj;
      return this.type == that.type && Objects.equals(this.name, that.name);
    }
    return false;
  }

  @Override public int hashCode() {
    return hashCode;
  }

  @Override public String toString() {
    if (name == null) {
      return type.getName();
    }
    return type.getName() + "(" + name + ")";
  }

  public static <T> ResourceKey<T> key(Class<T> type) {
    return new ResourceKey<>(type, null);
  }

  public static <T> ResourceKey<T> key(Class<T> type, String name) {
    return new ResourceKey<>(type, name);
  }
}