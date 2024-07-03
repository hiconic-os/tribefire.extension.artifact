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

import com.braintribe.model.generic.annotation.meta.Alias;
import com.braintribe.model.generic.annotation.meta.Description;
import com.braintribe.model.generic.annotation.meta.PositionalArguments;
import com.braintribe.model.generic.eval.EvalContext;
import com.braintribe.model.generic.eval.Evaluator;
import com.braintribe.model.generic.reflection.EntityType;
import com.braintribe.model.generic.reflection.EntityTypes;
import com.braintribe.model.service.api.ServiceRequest;
import com.braintribe.model.service.api.result.Neutral;

import tribefire.extension.artifact.management.api.model.data.ResolutionTransitivity;

@Description("Uploads artifacts from a given arbitrary folder structure which is recursively scanned for upload candidates. "
		+ "A candidate is detected by the existence of a file with the extension *.pom. "
		+ "Each file in such a candidate directory must be formatted like artifactId-classifier-version.type to be recognized and uploaded as an artifact part.")
@PositionalArguments({"path","repoId"})
public interface ImportArtifacts extends ArtifactManagementRequest { 

	EntityType<ImportArtifacts> T = EntityTypes.T(ImportArtifacts.class);
	
	String update = "update";
	String transitivity = "transitivity";

	@Alias("u")
	@Description("If set, already existing artifacts in the repository will be updated. New parts are added and already existing parts "
			+ "are updated if their hash differs.")
	boolean getUpdate();
	void setUpdate(boolean value);
	
	ResolutionTransitivity getTransitivity();
	void setTransitivity(ResolutionTransitivity transitivity);
	
	

	@Override
	EvalContext<Neutral> eval(Evaluator<ServiceRequest> evaluator);
			
}
