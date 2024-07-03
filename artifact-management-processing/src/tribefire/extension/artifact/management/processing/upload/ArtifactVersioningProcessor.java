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
package tribefire.extension.artifact.management.processing.upload;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.braintribe.devrock.mc.api.commons.VersionInfo;
import com.braintribe.devrock.mc.api.resolver.ArtifactResolver;
import com.braintribe.model.artifact.compiled.CompiledDependencyIdentification;
import com.braintribe.model.artifact.essential.ArtifactIdentification;
import com.braintribe.model.version.Version;

/**
 * @author pit
 *
 */
public class ArtifactVersioningProcessor {
	
	/**
	 * retrieves all versions that are associated with the {@link ArtifactIdentification}
	 * @param ai - the {@link ArtifactIdentification}
	 * @param artifactResolver the resolver to use for determination
	 * @return - a {@link List} of {@link Version}
	 */
	public static List<Version> getArtifactVersions( ArtifactIdentification ai, ArtifactResolver artifactResolver, Predicate<Version> versionFilter) {
		return artifactResolver.getVersions(ai).stream().map(VersionInfo::version).filter(versionFilter).collect(Collectors.toList());
	}
	
	public static List<Version> getArtifactVersions( ArtifactIdentification ai, ArtifactResolver artifactResolver) {
		return getArtifactVersions(ai, artifactResolver, v -> true);
	}
	
	/**
	 * retrieves all versions that match the {@link CompiledDependencyIdentification}' range 
	 * @param cdi - the {@link CompiledDependencyIdentification}
	 * @param artifactResolver the resolver to use for determination
	 * @return - a {@link List} of {@link Version}
	 */
	public static List<Version> getArtifactVersions( CompiledDependencyIdentification cdi, ArtifactResolver artifactResolver) {
		return getArtifactVersions(cdi, artifactResolver, cdi.getVersion()::matches);
	}
	
}
