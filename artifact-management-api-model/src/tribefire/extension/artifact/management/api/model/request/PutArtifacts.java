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
package tribefire.extension.artifact.management.api.model.request;

import java.util.List;

import com.braintribe.model.artifact.consumable.Artifact;
import com.braintribe.model.artifact.consumable.ArtifactResolution;
import com.braintribe.model.generic.annotation.meta.Alias;
import com.braintribe.model.generic.annotation.meta.Description;
import com.braintribe.model.generic.eval.EvalContext;
import com.braintribe.model.generic.eval.Evaluator;
import com.braintribe.model.generic.reflection.EntityType;
import com.braintribe.model.generic.reflection.EntityTypes;
import com.braintribe.model.service.api.ServiceRequest;

@Description("Uploads artifacts from a given set of artifacts to the target repo of the current configuration.")
public interface PutArtifacts extends ArtifactManagementRequest { 

	EntityType<PutArtifacts> T = EntityTypes.T(PutArtifacts.class);
	
	String artifacts = "artifacts";
	String update = "update";

	List<Artifact> getArtifacts();
	void setArtifacts(List<Artifact> artifacts); 
	
	@Alias("u")
	@Description("If set, already existing artifacts in the repository will be updated. New parts are added and already existing parts "
			+ "are updated if their hash differs.")
	boolean getUpdate();
	void setUpdate(boolean value);
	
	@Override
	EvalContext<ArtifactResolution> eval(Evaluator<ServiceRequest> evaluator);
}
