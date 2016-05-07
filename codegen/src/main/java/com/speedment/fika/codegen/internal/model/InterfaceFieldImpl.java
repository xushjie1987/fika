/**
 *
 * Copyright (c) 2006-2016, Speedment, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); You may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.speedment.fika.codegen.internal.model;

import com.speedment.fika.codegen.model.AnnotationUsage;
import com.speedment.fika.codegen.model.Field;
import com.speedment.fika.codegen.model.InterfaceField;
import com.speedment.fika.codegen.model.Javadoc;
import com.speedment.fika.codegen.model.Type;
import com.speedment.fika.codegen.model.Value;
import com.speedment.fika.codegen.model.modifier.Modifier;
import java.util.List;
import java.util.Objects;
import static java.util.Objects.requireNonNull;
import java.util.Optional;
import java.util.Set;

/**
 * The default implementation of the wrapper for the {@link Field} interface.
 * 
 * @author Emil Forslund
 */
public final class InterfaceFieldImpl implements InterfaceField {
    
	private final Field f;
	
    /**
     * Wraps the specified field.
     * 
     * @param wrapped  the inner field
     */
	public InterfaceFieldImpl(Field wrapped) {
		f = requireNonNull(wrapped);
	}

    /**
     * {@inheritDoc}
     */
    @Override
	public String getName() {
		return f.getName();
	}
	
    /**
     * {@inheritDoc}
     */
    @Override
	public Type getType() {
		return f.getType();
	}

    /**
     * {@inheritDoc}
     */
    @Override
	public Set<Modifier> getModifiers() {
		return f.getModifiers();
	}

    /**
     * {@inheritDoc}
     */
    @Override
	public Optional<Javadoc> getJavadoc() {
		return f.getJavadoc();
	}

    /**
     * {@inheritDoc}
     */
    @Override
	public Optional<Value<?>> getValue() {
		return f.getValue();
	}

    /**
     * {@inheritDoc}
     */
    @Override
	public List<AnnotationUsage> getAnnotations() {
		return f.getAnnotations();
	}

    /**
     * {@inheritDoc}
     */
    @Override
    public InterfaceField setName(String name) {
        f.setName(name);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InterfaceField set(Type type) {
        f.set(type);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InterfaceField set(Javadoc doc) {
        f.set(doc);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InterfaceField set(Value<?> val) {
        f.set(val);
        return this;
    }
    
    /**
     * {@inheritDoc}
     */
	@Override
	public InterfaceFieldImpl copy() {
		return new InterfaceFieldImpl(f.copy());
	}

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.f);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final InterfaceFieldImpl other = (InterfaceFieldImpl) obj;
        if (!Objects.equals(this.f, other.f)) {
            return false;
        }
        return true;
    }


}