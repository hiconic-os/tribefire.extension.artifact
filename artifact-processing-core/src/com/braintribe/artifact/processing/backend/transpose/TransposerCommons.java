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
package com.braintribe.artifact.processing.backend.transpose;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Collections;
import java.util.List;

import com.braintribe.build.artifact.retrieval.multi.repository.reflection.ArtifactReflectionExpert;
import com.braintribe.build.artifact.retrieval.multi.repository.reflection.RepositoryReflection;
import com.braintribe.cfg.Configurable;
import com.braintribe.cfg.Required;
import com.braintribe.logging.Logger;
import com.braintribe.model.artifact.Part;
import com.braintribe.model.artifact.PartTuple;
import com.braintribe.model.artifact.Solution;
import com.braintribe.model.artifact.info.RepositoryOrigin;
import com.braintribe.model.artifact.info.VersionInfo;
import com.braintribe.model.artifact.processing.ResolvedArtifactPart;

public class TransposerCommons {
	private static Logger log = Logger.getLogger(TransposerCommons.class);
	protected RepositoryReflection repositoryReflection;
	
	@Configurable @Required
	public void setRepositoryReflection(RepositoryReflection repositoryReflection) {
		this.repositoryReflection = repositoryReflection;
	}
	
	/**
	 * acquire the information about the repositories that contain the solution. Local repo has its URL cleaned
	 * @param solution - the {@link Solution} to retrieve the information
	 * @return - a {@link List} of {@link RepositoryOrigin}
	 */
	protected List<RepositoryOrigin> getRepositoryOrigins( Solution solution) {
		ArtifactReflectionExpert artifactReflectionExpert = repositoryReflection.acquireArtifactReflectionExpert(solution);		
		VersionInfo versionOrigin = artifactReflectionExpert.getVersionOrigin(solution.getVersion(), null);
		if (versionOrigin ==  null)
			return Collections.emptyList();
		// clean URL of local repository  
		List<RepositoryOrigin> repositoryOrigins = versionOrigin.getRepositoryOrigins();
		for (RepositoryOrigin origin : repositoryOrigins) {
			// 
			if (origin.getName()!= null && origin.getName().equalsIgnoreCase("local")) {
				origin.setUrl( null);
				break;
			}
		}
		
		return repositoryOrigins;
		
	}
	
	/**
	 * transpose a {@link Part} into a {@link ResolvedArtifactPart}
	 * @param part - the {@link Part} to transpose
	 * @return - the {@link ResolvedArtifactPart} as a result
	 */
	protected ResolvedArtifactPart transpose(Part part) {
		
		ResolvedArtifactPart resolvedPart = ResolvedArtifactPart.T.create();
		
		String location = part.getLocation();
		PartTuple tuple = part.getType();
		
		resolvedPart.setClassifier( tuple.getClassifier());
		resolvedPart.setType( tuple.getType());
		
		try {
			String url = new File( location).toURI().toURL().toString();
			resolvedPart.setUrl(url);
		} catch (MalformedURLException e) {
			log.error( "cannot turn part's location [" + location + "] to an valid URL", e);
		}
				
		return resolvedPart;
	}
}
