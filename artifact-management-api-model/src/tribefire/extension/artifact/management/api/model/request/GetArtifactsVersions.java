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

import com.braintribe.model.generic.annotation.meta.Alias;
import com.braintribe.model.generic.annotation.meta.Description;
import com.braintribe.model.generic.annotation.meta.Mandatory;
import com.braintribe.model.generic.eval.EvalContext;
import com.braintribe.model.generic.eval.Evaluator;
import com.braintribe.model.generic.reflection.EntityType;
import com.braintribe.model.generic.reflection.EntityTypes;
import com.braintribe.model.service.api.ServiceRequest;

import tribefire.extension.artifact.management.api.model.data.ArtifactVersions;

/**
 * returns a list of {@link ArtifactVersions}
 * @author pit
 *
 */
@Description("Determines the versions of given artifacts. Each artifact is given by a formatted string such as: org.fox:fix, org.fox:fix#2.0, org.fox:fix#[1.0,1.1). "
		+ "If no range is specified, it will be interpreted as an unbounded interval.")
public interface GetArtifactsVersions extends ArtifactManagementRequest {
	
	EntityType<GetArtifactsVersions> T = EntityTypes.T(GetArtifactsVersions.class);
	
	String artifacts = "artifacts";

	@Alias("a")
	@Mandatory
	@Description("List of artifact qualifications with optional version range to determine the available versions within the range (full range if not given explicitly). Examples: org.fox:fix, org.fox:fix#2.0, org.fox:fix#[1.0,1.1)")
	List<String> getArtifacts();
	void setArtifacts(List<String> artifacts);

	@Override
	EvalContext<List<ArtifactVersions>> eval(Evaluator<ServiceRequest> evaluator);
}
