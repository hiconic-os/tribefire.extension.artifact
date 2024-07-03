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
package com.braintribe.artifact.processing.core.test.walk;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.Assert;

import com.braintribe.artifact.processing.ArtifactProcessingCoreExpert;
import com.braintribe.artifact.processing.core.test.AbstractArtifactProcessingLab;
import com.braintribe.artifact.processing.core.test.Commons;
import com.braintribe.model.artifact.processing.ArtifactResolution;
import com.braintribe.model.artifact.processing.AssetFilterContext;
import com.braintribe.model.artifact.processing.HasArtifactIdentification;
import com.braintribe.model.artifact.processing.PlatformAssetResolution;
import com.braintribe.model.artifact.processing.ResolvedArtifact;
import com.braintribe.model.artifact.processing.cfg.repository.RepositoryConfiguration;
import com.braintribe.model.artifact.processing.cfg.resolution.FilterScope;
import com.braintribe.model.artifact.processing.cfg.resolution.ResolutionConfiguration;
import com.braintribe.model.artifact.processing.cfg.resolution.ResolutionScope;
import com.braintribe.model.artifact.processing.service.data.ResolvedPlatformAssets;
import com.braintribe.model.artifact.processing.service.request.ResolvePlatformAssets;

public abstract class AbstractDependenciesLab extends AbstractArtifactProcessingLab {
	
	
	protected ArtifactResolution resolvedDependencies(File localRepository, HasArtifactIdentification hasArtifactIdentification, RepositoryConfiguration scopeConfiguration, ResolutionConfiguration walkConfiguration) {		
		return ArtifactProcessingCoreExpert.getArtifactResolution( localRepository, hasArtifactIdentification, scopeConfiguration, walkConfiguration);
	}
	
	protected PlatformAssetResolution resolvedAssets(File localRepository, HasArtifactIdentification hasArtifactIdentification, RepositoryConfiguration scopeConfiguration, AssetFilterContext context) {		
		return ArtifactProcessingCoreExpert.getAssetResolution(localRepository, hasArtifactIdentification, scopeConfiguration, context);
	}
	
	protected ResolvedPlatformAssets resolvedAssets(File localRepository, ResolvePlatformAssets pas, RepositoryConfiguration scopeConfiguration, AssetFilterContext context) {		
		return ArtifactProcessingCoreExpert.resolvePlatformAssets(localRepository, pas, scopeConfiguration, context);
	}
	
	
	protected void validateResult( List<ResolvedArtifact> found, List<ResolvedArtifact> expected) {
		List<ResolvedArtifact> unexpected = new ArrayList<>();
		List<ResolvedArtifact> matched = new ArrayList<>();
		for (ResolvedArtifact ra : found) {			
			boolean foundMatch = false;
			for (ResolvedArtifact rb : expected) {
				if (Commons.compare(ra, rb)) {
					foundMatch = true;
					matched.add(rb);					
				}	
			}		
			if (!foundMatch) {
				unexpected.add(ra);
			}
		}
		
		if (unexpected.size() > 0) {
			// dump			
			Assert.fail( "unexpected : [" + Commons.rsToString( unexpected) + "]");
		}
		if (matched.size() != expected.size()) {
			List<ResolvedArtifact> temp = new ArrayList<>( expected);
			temp.removeAll( matched);
			Assert.fail( "not found [" + Commons.rsToString( temp));
		}		
	}
	
	/**
	 * @param resolutionScope
	 * @param skipOptionals
	 * @param filterScopes
	 * @return
	 */
	protected ResolutionConfiguration buildWalkConfiguration( ResolutionScope resolutionScope, boolean skipOptionals, FilterScope ...filterScopes) {
		ResolutionConfiguration walkConfiguration = ResolutionConfiguration.T.create();		
		walkConfiguration.setResolutionScope(resolutionScope);
		if (filterScopes != null) {
			walkConfiguration.setFilterScopes( new HashSet<>(Arrays.asList(filterScopes)));
		}
		walkConfiguration.setIncludeOptionals( !skipOptionals);		
		return walkConfiguration;
	}
}
