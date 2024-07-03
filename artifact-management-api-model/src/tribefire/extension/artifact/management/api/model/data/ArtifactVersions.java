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
package tribefire.extension.artifact.management.api.model.data;

import java.util.List;

import com.braintribe.model.generic.GenericEntity;
import com.braintribe.model.generic.reflection.EntityType;
import com.braintribe.model.generic.reflection.EntityTypes;
import com.braintribe.model.version.Version;

import tribefire.extension.artifact.management.api.model.request.GetArtifactVersions;
import tribefire.extension.artifact.management.api.model.request.GetArtifactsVersions;

/**
 * return value of the {@link GetArtifactsVersions}/ {@link GetArtifactVersions} processor
 * @author pit
 *
 */
public interface ArtifactVersions extends GenericEntity {
	
	EntityType<ArtifactVersions> T = EntityTypes.T(ArtifactVersions.class);
	
	String groupId = "groupId";
	String artifactId = "artifactId";
	String versionsAsStrings = "versionsAsStrings";
	String versions = "versions";

	String getGroupId();
	void setGroupId(String groupId);
	
	String getArtifactId();
	void setArtifactId(String value);
	
	List<String> getVersionsAsStrings();
	void setVersionsAsStrings(List<String> versionsAsStrings);

	List<Version> getVersions();
	void setVersions(List<Version> value);


}
