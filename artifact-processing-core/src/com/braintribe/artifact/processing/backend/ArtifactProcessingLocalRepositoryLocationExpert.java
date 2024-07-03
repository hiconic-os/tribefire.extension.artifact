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
package com.braintribe.artifact.processing.backend;

import com.braintribe.build.artifact.representations.RepresentationException;
import com.braintribe.build.artifact.representations.artifact.maven.settings.LocalRepositoryLocationProvider;
import com.braintribe.model.artifact.processing.cfg.repository.RepositoryConfiguration;
import com.braintribe.model.maven.settings.Settings;

/**
 * produces the path to the local repository as required by MC 
 *
 * a) find drive section .. c:/bla/myrepo
 * b) find variable.. ${M2_REPO}/bla
 * or
 * just a relative path <root>/repo/bla. repo/bla?
 * currently: no support 
 * 
 * @author pit
 *
 */
public class ArtifactProcessingLocalRepositoryLocationExpert implements LocalRepositoryLocationProvider {
	private String configuredExpression;
	private String root;
	private String processedPath;

	@Override
	public String getLocalRepository(String expression) throws RepresentationException {
		if (processedPath == null) {
			processedPath = processPath();
		}
		 
		return processedPath;
	}

	/**
	 * sets the currently configured expression as from the {@link RepositoryConfiguration} so that it can be used as base for
	 * computation of the actual path  
	 * @param configuredExpression - the expression as defined (via the converted {@link Settings}
	 */
	public void setConfiguredExpression(String configuredExpression) {
		this.configuredExpression = configuredExpression;
		
	}

	/**
	 * sets the root path of 
	 * @param object
	 */
	public void setLocalRepositoryFilesystemRoot(String root) {
		this.root = root;		
	}
	
	/**
	 * actually build a path from the file system root and the expression 
	 * @return - the processed path 
	 */
	private String processPath() {
		// do something smart here, for now it's what's specified in the deployables space
		return root;				
	}

}
