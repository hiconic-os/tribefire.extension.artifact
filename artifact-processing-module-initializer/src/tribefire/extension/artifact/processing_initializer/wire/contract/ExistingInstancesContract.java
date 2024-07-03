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
package tribefire.extension.artifact.processing_initializer.wire.contract;


import com.braintribe.model.deployment.Module;
import com.braintribe.model.meta.GmMetaModel;
import com.braintribe.wire.api.space.WireSpace;

import tribefire.cortex.initializer.support.impl.lookup.GlobalId;
import tribefire.cortex.initializer.support.impl.lookup.InstanceLookup;

@InstanceLookup(lookupOnly=true)
public interface ExistingInstancesContract extends WireSpace {
	
	String GRPID ="tribefire.extension.artifact";
	String GLOBALID_PREFIX = "model:" + GRPID + ":";
	
	
	@GlobalId( GLOBALID_PREFIX + "artifact-processing-deployment-model")
	GmMetaModel artifactProcessingDeploymentModel();
	
	@GlobalId( GLOBALID_PREFIX + "artifact-processing-service-model")
	GmMetaModel artifactProcessingServiceModel();
	
	@GlobalId( GLOBALID_PREFIX + "artifact-processing-access-model")
	GmMetaModel artifactProcessingAccessModel();
	
	@GlobalId( "module://" + GRPID + ":artifact-processing-module")
	Module artifactProcessingModule();
	
	
}
