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
package tribefire.extension.artifact.processing_initializer;

import com.braintribe.model.artifact.processing.service.request.ArtifactProcessingRequest;
import com.braintribe.model.processing.meta.editor.BasicModelMetaDataEditor;
import com.braintribe.model.processing.meta.editor.ModelMetaDataEditor;
import com.braintribe.model.processing.session.api.collaboration.PersistenceInitializationContext;
import com.braintribe.wire.api.module.WireTerminalModule;

import tribefire.cortex.initializer.support.api.WiredInitializerContext;
import tribefire.cortex.initializer.support.impl.AbstractInitializer;
import tribefire.cortex.initializer.support.integrity.wire.contract.CoreInstancesContract;
import tribefire.extension.artifact.processing_initializer.wire.ArtifactProcessingInitializerWireModule;
import tribefire.extension.artifact.processing_initializer.wire.contract.ArtifactProcessingInitializerContract;
import tribefire.extension.artifact.processing_initializer.wire.contract.ArtifactProcessingInitializerMainContract;
import tribefire.extension.artifact.processing_initializer.wire.contract.ExistingInstancesContract;

public class ArtifactProcessingInitializer extends AbstractInitializer<ArtifactProcessingInitializerMainContract> {

	@Override
	public WireTerminalModule<ArtifactProcessingInitializerMainContract> getInitializerWireModule() {
		return ArtifactProcessingInitializerWireModule.INSTANCE;
	}
	private void addMetadataToModels(WiredInitializerContext<ArtifactProcessingInitializerMainContract> context) {
		ArtifactProcessingInitializerContract artifactProcessingInitializerContract = context.contract().artifactProcessingInitializerContract();
		ExistingInstancesContract existingInstancesContract = context.contract().existingInstancesContract();
		
		ModelMetaDataEditor editor = new BasicModelMetaDataEditor( existingInstancesContract.artifactProcessingServiceModel());
		editor.onEntityType(ArtifactProcessingRequest.T).addMetaData( artifactProcessingInitializerContract.processWithArtifactProcessingExpert());		
	}
	
	
	@Override
	public void initialize(PersistenceInitializationContext context, WiredInitializerContext<ArtifactProcessingInitializerMainContract> initializerContext, ArtifactProcessingInitializerMainContract initializerMainContract) {
		ArtifactProcessingInitializerContract artifactProcessingInitializerContract = initializerMainContract.artifactProcessingInitializerContract();
		artifactProcessingInitializerContract.serviceDomain();
		artifactProcessingInitializerContract.configurationAccess();
		addMetadataToModels(initializerContext);

		ExistingInstancesContract existingInstancesContract = initializerContext.contract().existingInstancesContract();
		CoreInstancesContract coreInstancesContract = initializerContext.contract().coreInstancesContract();
		
		coreInstancesContract.cortexModel().getDependencies().add( existingInstancesContract.artifactProcessingDeploymentModel());	
	}
}
