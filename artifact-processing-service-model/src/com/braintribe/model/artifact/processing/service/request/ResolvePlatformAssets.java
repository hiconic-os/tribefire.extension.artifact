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

import java.util.List;

import com.braintribe.model.artifact.processing.ArtifactIdentification;
import com.braintribe.model.artifact.processing.service.data.HasAssetContext;
import com.braintribe.model.artifact.processing.service.data.HasRepositoryConfigurationName;
import com.braintribe.model.artifact.processing.service.data.ResolvedPlatformAssets;
import com.braintribe.model.generic.eval.EvalContext;
import com.braintribe.model.generic.eval.Evaluator;
import com.braintribe.model.generic.reflection.EntityType;
import com.braintribe.model.generic.reflection.EntityTypes;
import com.braintribe.model.service.api.ServiceRequest;

/**
 * Request that resolves a number of assets together
 * @author Dirk Scheffler
 *
 */
public interface ResolvePlatformAssets extends ArtifactProcessingRequest, HasRepositoryConfigurationName, HasAssetContext {
	
	final EntityType<ResolvePlatformAssets> T = EntityTypes.T(ResolvePlatformAssets.class);
	
	public List<ArtifactIdentification> getAssets();
	public void setAssets(List<ArtifactIdentification> assets);
	
	@Override
	EvalContext<ResolvedPlatformAssets> eval(Evaluator<ServiceRequest> evaluator);
}
