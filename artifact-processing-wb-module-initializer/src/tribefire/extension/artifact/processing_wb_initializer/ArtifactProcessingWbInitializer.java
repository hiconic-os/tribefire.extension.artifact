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
package tribefire.extension.artifact.processing_wb_initializer;

import static com.braintribe.wire.api.util.Lists.list;

import com.braintribe.model.processing.session.api.collaboration.PersistenceInitializationContext;
import com.braintribe.wire.api.module.WireTerminalModule;

import tribefire.cortex.assets.default_wb_initializer.wire.contract.DefaultWbContract;
import tribefire.cortex.initializer.support.api.WiredInitializerContext;
import tribefire.cortex.initializer.support.impl.AbstractInitializer;
import tribefire.extension.artifact.processing_wb_initializer.wire.ArtifactProcessingWbInitializerWireModule;
import tribefire.extension.artifact.processing_wb_initializer.wire.contract.ArtifactProcessingWbInitializerContract;
import tribefire.extension.artifact.processing_wb_initializer.wire.contract.ArtifactProcessingWbInitializerMainContract;

public class ArtifactProcessingWbInitializer extends AbstractInitializer<ArtifactProcessingWbInitializerMainContract> {

	@Override
	public WireTerminalModule<ArtifactProcessingWbInitializerMainContract> getInitializerWireModule() {
		return ArtifactProcessingWbInitializerWireModule.INSTANCE;
	}
	
	@Override
	public void initialize(PersistenceInitializationContext context, WiredInitializerContext<ArtifactProcessingWbInitializerMainContract> initializerContext, ArtifactProcessingWbInitializerMainContract initializerMainContract) {

		DefaultWbContract workbenchContract = initializerMainContract.workbenchContract();
		ArtifactProcessingWbInitializerContract accessWb = initializerMainContract.artifactProcessingWbInitializerContract();
		
		workbenchContract.defaultRootPerspective().getFolders().add(accessWb.artifactProcessingFolder());
		
		workbenchContract.defaultHomeFolderPerspective().getFolders().addAll(list(
				accessWb.mavenConfigurationFolder(),
				accessWb.simplifiedConfigurationFolder(),
				accessWb.repositoryPolicyFolder(),
				accessWb.resolutionConfigurationFolder(),
				accessWb.assetContextsFolder()
				));
	}
}
