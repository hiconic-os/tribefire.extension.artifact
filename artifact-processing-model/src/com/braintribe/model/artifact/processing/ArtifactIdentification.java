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
package com.braintribe.model.artifact.processing;

import com.braintribe.model.generic.GenericEntity;
import com.braintribe.model.generic.annotation.ToStringInformation;
import com.braintribe.model.generic.annotation.meta.Mandatory;
import com.braintribe.model.generic.reflection.EntityType;
import com.braintribe.model.generic.reflection.EntityTypes;

/**
 * a simple container for the three identification values of an artifact, i.e. group, artifact and version <br/>
 * Bear in mind that semantically, the version is rather a version range, so you may give a range like [1.0,1.1), 
 * and that a seemingly simple version 1.0 is turned into [1.0,1.1).
 *    
 * @author pit
 *
 */
@ToStringInformation("${groupId}:${artifactId}#${version}")
public interface ArtifactIdentification extends GenericEntity{
		
	final EntityType<ArtifactIdentification> T = EntityTypes.T(ArtifactIdentification.class);
	
	String groupId = "groupId";
	String artifactId = "artifactId";
	String version = "version";
	
	/**
	 * @param groupId - the group id of the artifact
	 */
	@Mandatory
	void setGroupId( String groupId);
	/**
	 * @return - the group id of the artifact
	 */
	String getGroupId();
	
	/**
	 * @param artifactId - the artifact id of the artifact
	 */
	@Mandatory
	void setArtifactId( String artifactId);
	/**
	 * @return - the artifact id of the artifact
	 */
	String getArtifactId();
	
	// actually, this is a VersionRange!
	/**
	 * @param version - the version EXPRESSION of the artifact (may be a range)
	 */
	void setVersion( String version);
	/**
	 * @return - the version EXPRESSION of the artifact (may be a range)
	 */
	String getVersion();
	
}
