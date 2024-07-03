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
package com.braintribe.model.artifact.processing.service.request;

import com.braintribe.model.artifact.processing.ArtifactResolution;
import com.braintribe.model.artifact.processing.HasArtifactIdentification;
import com.braintribe.model.artifact.processing.service.data.HasRepositoryConfigurationName;
import com.braintribe.model.artifact.processing.service.data.HasResolutionConfigurationName;
import com.braintribe.model.generic.eval.EvalContext;
import com.braintribe.model.generic.eval.Evaluator;
import com.braintribe.model.generic.reflection.EntityType;
import com.braintribe.model.generic.reflection.EntityTypes;
import com.braintribe.model.service.api.ServiceRequest;

/**
 * denotation type for the feature that resolves an artifact and attaches (recursively) the resolved dependencies 
 * @author pit
 *
 */
public interface ResolveArtifactDependencies extends ArtifactProcessingRequest, HasArtifactIdentification, HasRepositoryConfigurationName, HasResolutionConfigurationName {

	final EntityType<ResolveArtifactDependencies> T = EntityTypes.T(ResolveArtifactDependencies.class);

	@Override
	EvalContext<ArtifactResolution> eval(Evaluator<ServiceRequest> evaluator);
}
