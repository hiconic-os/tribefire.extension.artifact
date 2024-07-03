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
package com.braintribe.model.artifact.processing.service.data;

import java.util.List;

import com.braintribe.model.artifact.processing.ResolvedPlatformAsset;
import com.braintribe.model.generic.GenericEntity;
import com.braintribe.model.generic.reflection.EntityType;
import com.braintribe.model.generic.reflection.EntityTypes;

public interface ResolvedPlatformAssets extends GenericEntity {
		
	EntityType<ResolvedPlatformAssets> T = EntityTypes.T(ResolvedPlatformAssets.class);

	/**
	 * The list of the terminal assets for which the resolution was made. They are repeated in order in {@link #getResolvedAssets()} 
	 */
	List<ResolvedPlatformAsset> getTerminalAssets();
	void setTerminalAssets(List<ResolvedPlatformAsset> terminalAssets);
	
	/**
	 * The list of all transitive resolved assets in the order they would have to be processed including the terminals from {@link #getTerminalAssets()}
	 */
	List<ResolvedPlatformAsset> getResolvedAssets();
	void setResolvedAssets(List<ResolvedPlatformAsset> resolvedAssets);
}
