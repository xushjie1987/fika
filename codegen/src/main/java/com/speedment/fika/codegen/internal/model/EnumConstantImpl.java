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

import com.speedment.fika.codegen.model.EnumConstant;
import com.speedment.fika.codegen.model.Value;
import com.speedment.fika.codegen.internal.util.Copier;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import static java.util.Objects.requireNonNull;

/**
 * This is the default implementation of the {@link EnumConstant} interface.
 * This class should not be instantiated directly. Instead you should call the
 * {@link EnumConstant#of(java.lang.String)} method to get an instance. In that way, 
 * you can layer change the implementing class without modifying the using code.
 * 
 * @author Emil Forslund
 * @see    EnumConstant
 */
public final class EnumConstantImpl implements EnumConstant {
	
	private String name;
	private final List<Value<?>> values;

    /**
     * Initializes this enum constant using a name.
     * <p>
     * <b>Warning!</b> This class should not be instantiated directly but using 
     * the {@link EnumConstant#of(java.lang.String)} method!
     * 
     * @param name  the name
     */
	public EnumConstantImpl(String name) {
		this.name	= requireNonNull(name);
		this.values = new ArrayList<>();
	}
	
    /**
     * Copy constructor.
     * 
     * @param prototype  the prototype 
     */
	protected EnumConstantImpl(EnumConstant prototype) {
		name	= requireNonNull(prototype).getName();
		values	= Copier.copy(prototype.getValues(), v -> v.copy());
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public EnumConstant setName(String name) {
		this.name = requireNonNull(name);
		return this;
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public String getName() {
		return name;
	}
	
    /**
     * {@inheritDoc}
     */
    @Override
	public List<Value<?>> getValues() {
		return values;
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public EnumConstantImpl copy() {
		return new EnumConstantImpl(this);
	}

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + Objects.hashCode(this.values);
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
        final EnumConstantImpl other = (EnumConstantImpl) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.values, other.values)) {
            return false;
        }
        return true;
    }


}