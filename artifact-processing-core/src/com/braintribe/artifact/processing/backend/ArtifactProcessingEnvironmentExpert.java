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
package com.braintribe.artifact.processing.backend;

import java.util.List;

import com.braintribe.build.artifact.representations.artifact.maven.settings.OverrideableVirtualEnvironment;
import com.braintribe.logging.Logger;
import com.braintribe.model.artifact.processing.cfg.env.Override;
import com.braintribe.model.artifact.processing.cfg.env.OverridingEnvironmentVariable;
import com.braintribe.model.artifact.processing.cfg.env.OverridingSystemProperty;
import com.braintribe.model.artifact.processing.cfg.repository.RepositoryConfiguration;
import com.braintribe.ve.api.VirtualEnvironment;

public class ArtifactProcessingEnvironmentExpert {
	private static Logger log = Logger.getLogger(ArtifactProcessingEnvironmentExpert.class);
	
	public VirtualEnvironment acquireVirtualEnvironment( RepositoryConfiguration scopeConfiguration) {
		OverrideableVirtualEnvironment ove = new OverrideableVirtualEnvironment();

		if (scopeConfiguration == null) {
			return ove;
		}
		
		List<Override> overrides = scopeConfiguration.getEnvironmentOverrides();		
		//  
		if (overrides != null && overrides.size() > 0) {
			// add the overrides 
			overrides.stream().forEach( o -> {
				if ( o instanceof OverridingEnvironmentVariable) {					
					ove.addEnvironmentOverride( o.getName(), o.getValue());
				}
				else if (o instanceof OverridingSystemProperty) { 				
					ove.addPropertyOverride( o.getName(), o.getValue());
				}
				else {
					log.warn( "unknow override found [" + o.getClass().getName() + "], ignoring");
				}
			});
		}
		return ove;		
	}

}
