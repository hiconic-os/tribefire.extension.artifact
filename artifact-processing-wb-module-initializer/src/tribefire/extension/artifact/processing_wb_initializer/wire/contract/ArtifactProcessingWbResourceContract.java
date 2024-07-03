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
package tribefire.extension.artifact.processing_wb_initializer.wire.contract;


import com.braintribe.model.resource.Resource;
import com.braintribe.wire.api.space.WireSpace;

import tribefire.cortex.initializer.support.impl.lookup.GlobalId;
import tribefire.cortex.initializer.support.impl.lookup.InstanceLookup;

@InstanceLookup(lookupOnly = true, globalIdPrefix = ArtifactProcessingWbResourceContract.GLOBAL_ID_PREFIX)
public interface ArtifactProcessingWbResourceContract extends WireSpace {

	String RESOURCE_ASSET_NAME = "tribefire.extension.artifact:artifact-processing-access-wb-resources";
	String GLOBAL_ID_PREFIX = "asset-resource://" + RESOURCE_ASSET_NAME + "/";
	
	@GlobalId("config_24x24.png")
	Resource config24Png();
	
	@GlobalId("config_32x32.png")
	Resource config32Png();
	
	@GlobalId("config_6x64.png")
	Resource config64Png();
	
}
