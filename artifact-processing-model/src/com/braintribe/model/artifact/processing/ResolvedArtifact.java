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
package com.braintribe.model.artifact.processing;

import java.util.List;

import com.braintribe.model.artifact.info.HasRepositoryOrigins;
import com.braintribe.model.generic.reflection.EntityType;
import com.braintribe.model.generic.reflection.EntityTypes;

/**
 * a representation of an artifact
 * @author pit
 *
 */
public interface ResolvedArtifact extends ArtifactIdentification, HasRepositoryOrigins, HasParts {
	
	final EntityType<ResolvedArtifact> T = EntityTypes.T(ResolvedArtifact.class);
	
	
	/**
	 * @return - the DIRECT dependencies as {@link ResolvedArtifact}
	 */
	List<ResolvedArtifact> getDependencies();
	/**
	 * @param dependencies - the DIRECT dependencies as {@link ResolvedArtifact}
	 */
	void setDependencies( List<ResolvedArtifact> dependencies);
	
}
