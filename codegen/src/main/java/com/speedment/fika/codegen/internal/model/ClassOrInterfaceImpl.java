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
import com.speedment.fika.codegen.model.ClassOrInterface;
import com.speedment.fika.codegen.model.Field;
import com.speedment.fika.codegen.model.Generic;
import com.speedment.fika.codegen.model.Initializer;
import com.speedment.fika.codegen.model.Javadoc;
import com.speedment.fika.codegen.model.Method;
import com.speedment.fika.codegen.model.Type;
import com.speedment.fika.codegen.model.modifier.Modifier;
import com.speedment.fika.codegen.internal.util.Copier;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import static java.util.Objects.requireNonNull;
import java.util.Optional;
import java.util.Set;

/**
 * This is the abstract base implementation used by the classes {@link ClassImpl},
 * {@link EnumImpl} and {@link InterfaceImpl} to share functionality.
 * 
 * @author Emil Forslund
 * @param <T> The extending type
 */
public abstract class ClassOrInterfaceImpl<T extends ClassOrInterface<T>> 
    implements ClassOrInterface<T> {
	
	private String name;
	private Javadoc javadoc;
	private final List<AnnotationUsage> annotations;
	private final List<Generic> generics;
	private final List<Type> interfaces;
	private final List<Field> fields;
	private final List<Method> methods;
    private final List<Initializer> initalizers;
	private final List<ClassOrInterface<?>> classes;
	private final Set<Modifier> modifiers;
    
    /**
     * Initializes this model.
     * 
     * @param name  the name
     */
	public ClassOrInterfaceImpl(String name) {
		this.name		 = requireNonNull(name);
		this.javadoc	 = null;
		this.annotations = new ArrayList<>();
		this.generics	 = new ArrayList<>();
		this.interfaces	 = new ArrayList<>();
		this.fields		 = new ArrayList<>();
		this.methods	 = new ArrayList<>();
        this.initalizers = new ArrayList<>();
		this.classes	 = new ArrayList<>();
		this.modifiers	 = EnumSet.noneOf(Modifier.class);
	}
	
    /**
     * Copy constructor.
     * 
     * @param prototype  the prototype
     */
	protected ClassOrInterfaceImpl(ClassOrInterface<T> prototype) {
		name		= requireNonNull(prototype).getName();
		javadoc		= prototype.getJavadoc().map(Copier::copy).orElse(null);
		annotations	= Copier.copy(prototype.getAnnotations());
		generics	= Copier.copy(prototype.getGenerics());
		interfaces	= Copier.copy(prototype.getInterfaces());
		fields		= Copier.copy(prototype.getFields());
		methods		= Copier.copy(prototype.getMethods());
        initalizers = Copier.copy(prototype.getInitalizers());
		classes		= Copier.copy(prototype.getClasses(), c -> c.copy());
		modifiers	= Copier.copy(prototype.getModifiers(), c -> c.copy(), EnumSet.noneOf(Modifier.class));
	}

    /**
     * {@inheritDoc}
     */
	@Override
    @SuppressWarnings("unchecked")
	public T setName(String name) {
		this.name = requireNonNull(name);
		return (T) this;
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
    @SuppressWarnings("unchecked")
	public T set(Javadoc doc) {
		javadoc = doc;
		return (T) this;
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public Optional<Javadoc> getJavadoc() {
		return Optional.ofNullable(javadoc);
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public List<Method> getMethods() {
		return methods;
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public List<Field> getFields() {
		return fields;
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public List<Type> getInterfaces() {
		return interfaces;
	}
	
    /**
     * {@inheritDoc}
     */
	@Override
	public Set<Modifier> getModifiers() {
		return modifiers;
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public List<Generic> getGenerics() {
		return generics;
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public List<AnnotationUsage> getAnnotations() {
		return annotations;
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public List<ClassOrInterface<?>> getClasses() {
		return classes;
	}

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Initializer> getInitalizers() {
        return initalizers;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + Objects.hashCode(this.javadoc);
        hash = 37 * hash + Objects.hashCode(this.annotations);
        hash = 37 * hash + Objects.hashCode(this.generics);
        hash = 37 * hash + Objects.hashCode(this.interfaces);
        hash = 37 * hash + Objects.hashCode(this.fields);
        hash = 37 * hash + Objects.hashCode(this.methods);
        hash = 37 * hash + Objects.hashCode(this.initalizers);
        hash = 37 * hash + Objects.hashCode(this.classes);
        hash = 37 * hash + Objects.hashCode(this.modifiers);
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
        final ClassOrInterfaceImpl<?> other = (ClassOrInterfaceImpl<?>) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.javadoc, other.javadoc)) {
            return false;
        }
        if (!Objects.equals(this.annotations, other.annotations)) {
            return false;
        }
        if (!Objects.equals(this.generics, other.generics)) {
            return false;
        }
        if (!Objects.equals(this.interfaces, other.interfaces)) {
            return false;
        }
        if (!Objects.equals(this.fields, other.fields)) {
            return false;
        }
        if (!Objects.equals(this.methods, other.methods)) {
            return false;
        }
        if (!Objects.equals(this.initalizers, other.initalizers)) {
            return false;
        }
        if (!Objects.equals(this.classes, other.classes)) {
            return false;
        }
        if (!Objects.equals(this.modifiers, other.modifiers)) {
            return false;
        }
        return true;
    }

  
}