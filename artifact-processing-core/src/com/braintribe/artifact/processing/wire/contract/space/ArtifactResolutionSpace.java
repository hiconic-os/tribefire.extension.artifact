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
package com.braintribe.artifact.processing.wire.contract.space;

import com.braintribe.artifact.processing.backend.ArtifactProcessingWalkConfigurationExpert;
import com.braintribe.artifact.processing.wire.contract.ArtifactIdentificationContract;
import com.braintribe.artifact.processing.wire.contract.ArtifactResolutionContract;
import com.braintribe.artifact.processing.wire.contract.RepositoryConfigurationContract;
import com.braintribe.artifact.processing.wire.contract.ResolutionConfigurationContract;
import com.braintribe.artifact.processing.wire.contract.exp.ArtifactResolver;
import com.braintribe.artifact.processing.wire.contract.exp.ArtifactResolverImpl;
import com.braintribe.build.artifact.walk.multi.Walker;
import com.braintribe.build.artifacts.mc.wire.classwalk.context.WalkerContext;
import com.braintribe.build.artifacts.mc.wire.classwalk.contract.ClasspathResolverContract;
import com.braintribe.model.artifact.processing.cfg.resolution.ResolutionConfiguration;
import com.braintribe.wire.api.annotation.Import;

public class ArtifactResolutionSpace implements ArtifactResolutionContract {
	
	@Import
	RepositoryConfigurationContract repositoryConfigurationContract;
	
	@Import
	ResolutionConfigurationContract resolutionConfigurationContract;
	
	@Import
	ArtifactIdentificationContract artifactIdentificationContract;
	
	@Import 
	ClasspathResolverContract classpathResolverContract;

	@Override
	public ArtifactResolver artifactResolver() {
		ArtifactResolverImpl bean = new ArtifactResolverImpl();
		bean.setArtifact( artifactIdentificationContract.artifactIdentification());
		
		bean.setDependencyResolver( classpathResolverContract.dependencyResolver());
		bean.setRepositoryReflection( classpathResolverContract.repositoryReflection());
		
		ResolutionConfiguration resolutionConfiguration = resolutionConfigurationContract.resolutionConfiguration();
		WalkerContext walkerContext = ArtifactProcessingWalkConfigurationExpert.acquireWalkerContext( resolutionConfiguration);		
		bean.setEnricher( classpathResolverContract.contextualizedEnricher(walkerContext));
		Walker walker = classpathResolverContract.walker( walkerContext);
		bean.setWalker(walker);
		if (resolutionConfiguration != null) {
			bean.setSortOrder( resolutionConfiguration.getSortOrder());
		}
		return bean;
	}
	
	

}
