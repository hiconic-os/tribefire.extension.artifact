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
package com.braintribe.artifact.processing;

import java.util.Set;
import java.util.UUID;

import com.braintribe.build.artifact.name.NameParser;
import com.braintribe.build.artifact.retrieval.multi.resolving.DependencyResolver;
import com.braintribe.model.artifact.Dependency;
import com.braintribe.model.artifact.Identification;
import com.braintribe.model.artifact.Solution;
import com.braintribe.model.artifact.processing.ArtifactIdentification;
import com.braintribe.model.artifact.processing.version.VersionProcessor;
import com.braintribe.model.artifact.processing.version.VersionRangeProcessor;
import com.braintribe.model.artifact.version.Version;
import com.braintribe.model.artifact.version.VersionRange;

public class ArtifactProcessingCoreCommons {

	/**
	 * ensures that sufficient data is presented and if so is turned into a dependency. 
	 * Caution: versionRange may be null (then it acts as an identification
	 * @param in
	 * @return - an {@link Identification} if no version is passed, otherwise a {@link Dependency}
	 */
	public static Identification ensureIdentificationOrDependency( ArtifactIdentification in) {
		if (in.getVersion() != null) {
			Dependency dependency = Dependency.T.create();
			dependency.setGroupId( in.getGroupId());
			dependency.setArtifactId( in.getArtifactId());
			VersionRange range = VersionRangeProcessor.createFromString( in.getVersion());
			range = VersionRangeProcessor.autoRangify(range);
			dependency.setVersionRange( range);
			return dependency;
		}
		else {
			Identification identification = Identification.T.create();
			identification.setGroupId( in.getGroupId());
			identification.setArtifactId( in.getArtifactId());
			return identification;
		}
	}
	
	/**
	 * build a solution from an artifact identification
	 * @param in - the {@link ArtifactIdentification}
	 * @return - the resulting {@link Solution}
	 */
	public static Solution ensureSolution(ArtifactIdentification in) {
		Solution solution = Solution.T.create();
		solution.setGroupId( in.getGroupId());
		solution.setArtifactId( in.getArtifactId());
		Version range = VersionProcessor.createFromString( in.getVersion());
		solution.setVersion( range);
		return solution;
		
	}

	/**
	 * ensure that the passed data is a valid dependency, while auto rangify it
	 * @param in - an {@link ArtifactIdentification}
	 * @return - a {@link Dependency}
	 */
	public static Dependency ensureDependency(ArtifactIdentification in) {
		Dependency dependency = Dependency.T.create();
		dependency.setGroupId( in.getGroupId());
		dependency.setArtifactId( in.getArtifactId());
		VersionRange range = VersionRangeProcessor.createFromString( in.getVersion());		
		dependency.setVersionRange( VersionRangeProcessor.autoRangify(range, true));
		return dependency;
		
	}
	
	public static Solution extractSolution( Set<Solution> solutions) {
		if (solutions == null || solutions.isEmpty()) {
			return null;
		}
		if (solutions.size() == 1) {
			return solutions.toArray( new Solution[0])[0];
		}
		// release has precedence? 		
		for (Solution solution : solutions) {
			String versionAsString = VersionProcessor.toString( solution.getVersion());
			if (versionAsString.toUpperCase().endsWith( "-SNAPSHOT")) {
				continue;
			}
			return solution;
		}
		return null;
	}
	
	public static Solution determineSolution( ArtifactIdentification in, DependencyResolver dependencyResolver) throws IllegalArgumentException {
		Dependency dependency = ArtifactProcessingCoreCommons.ensureDependency( in);
		Set<Solution> matchingSolutions = dependencyResolver.resolveTopDependency( UUID.randomUUID().toString(), dependency);	
		Solution solution = ArtifactProcessingCoreCommons.extractSolution(matchingSolutions);
		if (solution == null) {
			// honk? 
			String msg = "no matching solution found for [" + NameParser.buildName(dependency) + "]";
			throw new IllegalArgumentException(msg);			
		}
		return solution;
	}
	
}
