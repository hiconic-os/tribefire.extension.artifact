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
package com.braintribe.model.artifact.processing.cfg.repository;

import com.braintribe.model.generic.annotation.meta.Mandatory;
import com.braintribe.model.generic.reflection.EntityType;
import com.braintribe.model.generic.reflection.EntityTypes;
import com.braintribe.model.resource.Resource;

/**
 * a Maven style configuration, supplied via an resource containing a XML declaration (as in settings.xml)
 * 
 * @author pit
 *
 */
public interface MavenRepositoryConfiguration extends RepositoryConfiguration {
	
	final EntityType<MavenRepositoryConfiguration> T = EntityTypes.T(MavenRepositoryConfiguration.class);

	/**
	 * @return - the {@link Resource} that contains the XML declaration 
	 */
	@Mandatory
	Resource getSettingsAsResource();
	/**
	 * @param settings - the {@link Resource} that contains the XML declaration
	 */
	void setSettingsAsResource( Resource settings);
	

}
