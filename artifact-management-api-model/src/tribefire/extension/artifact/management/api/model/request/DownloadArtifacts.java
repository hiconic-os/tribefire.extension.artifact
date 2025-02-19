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
import java.util.Set;

import com.braintribe.model.generic.annotation.Initializer;
import com.braintribe.model.generic.annotation.meta.Alias;
import com.braintribe.model.generic.annotation.meta.Description;
import com.braintribe.model.generic.annotation.meta.Mandatory;
import com.braintribe.model.generic.annotation.meta.PositionalArguments;
import com.braintribe.model.generic.eval.EvalContext;
import com.braintribe.model.generic.eval.Evaluator;
import com.braintribe.model.generic.reflection.EntityType;
import com.braintribe.model.generic.reflection.EntityTypes;
import com.braintribe.model.service.api.ServiceRequest;
import com.braintribe.model.service.api.result.Neutral;

@Description("Download the given artifacts to a given path.")
@PositionalArguments({"path","artifacts"})
public interface DownloadArtifacts extends ArtifactManagementRequest { 

	EntityType<DownloadArtifacts> T = EntityTypes.T(DownloadArtifacts.class);
	
	String path = "path";
	String transitive = "transitive";
	String artifacts = "artifacts";
	String scopes = "scopes";
	String includeOptional = "includeOptional";
	String licenseInfo = "licenseInfo";
	String parts = "parts";
	String noCache = "noCache";

	@Alias("p")
	@Description("The path of the root folder where the downloaded artifacts should be placed.")
	@Mandatory
	String getPath();
	void setPath(String value);
	
	@Alias("t")
	@Description("If true the transitive dependencies of the given artifacts will be downloaded as well.")
	boolean getTransitive();
	void setTransitive(boolean transitive);
	
	@Description("If true the local respository or cache will not be used and artifacts will always be freshly downloaded.")
	boolean getNoCache();
	void setNoCache(boolean noCache);
	
	@Alias("l")
	@Description("If true and license related output will be made.")
	boolean getLicenseInfo();
	void setLicenseInfo(boolean licenseInfo);
	
	@Alias("o")
	@Description("If true optional dependencies will be included as well in transitive download.")
	boolean getIncludeOptional();
	void setIncludeOptional(boolean includeOptional);
	
	@Alias("s")
	@Initializer("{runtime}")
	@Description("The resolution scopes used to filter dependencies in transitive download.")
	Set<ResolutionScope> getScopes();
	void setScopes(Set<ResolutionScope> scopes);
	
	@Alias("a")
	@Description("The terminal artifacts to be downloaded.")
	@Mandatory
	List<String> getArtifacts();
	void setArtifacts(List<String> artifacts);
	
	@Description("The parts to be downloaded if available. If the list is empty all parts are downloaded.")
	List<String> getParts();
	void setParts(List<String> parts);
	
	@Override
	EvalContext<Neutral> eval(Evaluator<ServiceRequest> evaluator);
			
}
