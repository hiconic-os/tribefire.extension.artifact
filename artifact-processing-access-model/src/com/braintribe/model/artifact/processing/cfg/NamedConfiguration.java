// ============================================================================
// Copyright BRAINTRIBE TECHNOLOGY GMBH, Austria, 2002-2022
// 
// This library is free software; you can redistribute it and/or modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either version 3 of the License, or (at your option) any later version.
// 
// This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
// 
// You should have received a copy of the GNU Lesser General Public License along with this library; See http://www.gnu.org/licenses/.
// ============================================================================
package com.braintribe.model.artifact.processing.cfg;

import com.braintribe.model.generic.GenericEntity;
import com.braintribe.model.generic.annotation.Abstract;
import com.braintribe.model.generic.annotation.meta.Mandatory;
import com.braintribe.model.generic.reflection.EntityType;
import com.braintribe.model.generic.reflection.EntityTypes;

/**
 * basic named configuration
 * 
 * @author pit
 *
 */
@Abstract
public interface NamedConfiguration extends GenericEntity {
	
	final EntityType<NamedConfiguration> T = EntityTypes.T(NamedConfiguration.class);

	/**
	 * @return - the name (or key) of the {@link NamedConfiguration}
	 */
	@Mandatory
	String getName();
	/**
	 * @param name - the name (or key) of the {@link NamedConfiguration}
	 */
	void setName( String name);
	
	/**
	 * @return - the description of the {@link NamedConfiguration} as a {@link String} - if any
	 */
	String getDescription();
	/**
	 * @param description - the description of the {@link NamedConfiguration} as a {@link String}
	 */
	void setDescription( String description);

}
