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

import com.braintribe.model.meta.GmMetaModel;
import com.braintribe.wire.api.annotation.Import;
import com.braintribe.wire.api.annotation.Managed;

import tribefire.cortex.initializer.support.integrity.wire.contract.CoreInstancesContract;
import tribefire.cortex.initializer.support.wire.space.AbstractInitializerSpace;

import tribefire.extension.artifact.processing_initializer.wire.contract.ArtifactProcessingInitializerModelsContract;
import tribefire.extension.artifact.processing_initializer.wire.contract.ExistingInstancesContract;

@Managed
public class ArtifactProcessingInitializerModelsSpace extends AbstractInitializerSpace implements ArtifactProcessingInitializerModelsContract {

	private static final String GRP = "tribefire.extension.artifact";
	private static final String ART = "artifact-processing-service-model";
	
	@Import
	ExistingInstancesContract existingInstancesContract;
	
	@Import
	CoreInstancesContract coreInstancesContract;
		

	@Managed
	@Override
	public GmMetaModel configuredServiceModel() {
		GmMetaModel bean = create( GmMetaModel.T);		
		bean.setName( GRP + ":configured-" + ART);
		bean.getDependencies().add( existingInstancesContract.artifactProcessingServiceModel());		
		return bean;
	}
	
	@Managed
	@Override
	public GmMetaModel configurationAccessWorkbenchModel() {
		GmMetaModel bean = create( GmMetaModel.T);		
		
		bean.setName( GRP + ":configuration-access-workbench-model");
		
		bean.getDependencies().add( existingInstancesContract.artifactProcessingAccessModel());		
		bean.getDependencies().add( existingInstancesContract.artifactProcessingServiceModel());		
		
		bean.getDependencies().add(coreInstancesContract.workbenchModel());
		bean.getDependencies().add(coreInstancesContract.essentialMetaDataModel());
		
		return bean;
	}

}
