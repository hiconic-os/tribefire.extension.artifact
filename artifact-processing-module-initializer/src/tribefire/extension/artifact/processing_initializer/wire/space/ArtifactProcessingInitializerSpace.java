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
package tribefire.extension.artifact.processing_initializer.wire.space;

import com.braintribe.model.accessdeployment.smood.CollaborativeSmoodAccess;
import com.braintribe.model.artifact.processing.deployment.ArtifactProcessingExpert;
import com.braintribe.model.artifact.processing.service.request.ArtifactProcessingRequest;
import com.braintribe.model.extensiondeployment.meta.ProcessWith;
import com.braintribe.model.service.domain.ServiceDomain;
import com.braintribe.wire.api.annotation.Import;
import com.braintribe.wire.api.annotation.Managed;

import tribefire.cortex.initializer.support.integrity.wire.contract.CoreInstancesContract;
import tribefire.cortex.initializer.support.wire.space.AbstractInitializerSpace;

import tribefire.extension.artifact.processing_initializer.wire.contract.ArtifactProcessingInitializerContract;
import tribefire.extension.artifact.processing_initializer.wire.contract.ArtifactProcessingInitializerModelsContract;
import tribefire.extension.artifact.processing_initializer.wire.contract.ExistingInstancesContract;

@Managed
public class ArtifactProcessingInitializerSpace extends AbstractInitializerSpace implements ArtifactProcessingInitializerContract {

	 private static final String NAME = "artifactProcessingExpert";
	 private static final String PRC = "serviceProcessor." + NAME;
	 
	@Import
	ExistingInstancesContract existingInstancesContract;
	
	@Import
	CoreInstancesContract coreInstancesContract;
	
	@Import
	ArtifactProcessingInitializerModelsContract artifactProcessingInitializerModelsContract;
	
	@Managed	
	public ArtifactProcessingExpert artifactProcessingExpert() {
		ArtifactProcessingExpert bean = create( ArtifactProcessingExpert.T);
		bean.setExternalId( PRC);
		bean.setName( "Artifact Processing Expert");
		bean.setModule(existingInstancesContract.artifactProcessingModule());
		bean.setConfigurationAccess( configurationAccess());
		return bean;
	}
	
	@Managed
	@Override
	public ProcessWith processWithArtifactProcessingExpert() {
		ProcessWith bean = create( ProcessWith.T);
		bean.setProcessor( artifactProcessingExpert());
		return bean;
	}
	
	@Managed
	@Override
	public ServiceDomain serviceDomain() {
		ServiceDomain bean = create( ServiceDomain.T);
		bean.setServiceModel( artifactProcessingInitializerModelsContract.configuredServiceModel());
		bean.setExternalId(ArtifactProcessingRequest.DOMAIN_ID);
		return bean;
	}
	
	@Managed
	@Override
	public CollaborativeSmoodAccess configurationAccess() {
		CollaborativeSmoodAccess bean = create(CollaborativeSmoodAccess.T);
		
		bean.setExternalId("access.repositoryConfiguration");
		bean.setName("Repository Configuration Access");
		bean.setMetaModel( existingInstancesContract.artifactProcessingAccessModel());
		bean.setWorkbenchAccess(configurationWorkbenchAccess());
		
		return bean;
	}
	
	@Managed
	private CollaborativeSmoodAccess configurationWorkbenchAccess() {
		CollaborativeSmoodAccess bean = create(CollaborativeSmoodAccess.T);
		bean.setExternalId("access.repositoryConfiguration.wb");
		bean.setName("Repository Configuration Workbench Access");

		bean.setMetaModel(artifactProcessingInitializerModelsContract.configurationAccessWorkbenchModel());
		bean.setWorkbenchAccess(coreInstancesContract.workbenchAccess());
		return bean;
	}
}
