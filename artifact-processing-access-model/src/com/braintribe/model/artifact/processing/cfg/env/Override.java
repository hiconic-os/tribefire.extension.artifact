// ============================================================================
// Copyright BRAINTRIBE TECHNOLOGY GMBH, Austria, 2002-2022
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
// ============================================================================
package com.braintribe.model.artifact.processing.cfg.env;

import com.braintribe.model.generic.GenericEntity;
import com.braintribe.model.generic.annotation.Abstract;
import com.braintribe.model.generic.annotation.meta.Mandatory;
import com.braintribe.model.generic.reflection.EntityType;
import com.braintribe.model.generic.reflection.EntityTypes;

/**
 * base for both {@link OverridingEnvironmentVariable} and {@link OverridingSystemProperty}<br/>
 * defines a name value pair to override a system property or an environment
 * @author pit
 *
 */
@Abstract
public interface Override extends GenericEntity{
	
	final EntityType<Override> T = EntityTypes.T(Override.class);

	/**
	 * @param name - the name of the {@link Override}, i.e. environment-variable or system-property name
	 */
	@Mandatory
	void setName(String name);
	/**
	 * @return - the name of the {@link Override}, i.e. environment-variable or system-property name
	 */
	String getName();
	
	/**
	 * @param value - the {@link String} value of the {@link Override}
	 */
	void setValue(String value);
	/**
	 * @return - the {@link String} value of the {@link Override}
	 */
	String getValue();
	
	
}
