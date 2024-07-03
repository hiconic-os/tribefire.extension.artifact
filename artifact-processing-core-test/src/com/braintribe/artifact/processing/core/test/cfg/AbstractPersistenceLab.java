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
package com.braintribe.artifact.processing.core.test.cfg;

import java.io.File;

import com.braintribe.artifact.processing.backend.ArtifactProcessingSettingsPersistenceExpert;
import com.braintribe.artifact.processing.core.test.ResourceGenerator;
import com.braintribe.artifact.processing.core.test.ResourceProvidingSession;
import com.braintribe.model.artifact.processing.cfg.repository.MavenRepositoryConfiguration;
import com.braintribe.model.artifact.processing.cfg.repository.RepositoryConfiguration;
import com.braintribe.model.maven.settings.Settings;

public abstract class AbstractPersistenceLab {
	 
	private ResourceProvidingSession session = new ResourceProvidingSession();
	protected File contents = new File( "res/resources");		
		
	/**
	 * @param scopeCfg
	 * @param validators
	 */
	protected void test( RepositoryConfiguration scopeCfg, Validator ... validators) {
		ArtifactProcessingSettingsPersistenceExpert settingsPersistenceExpert = new ArtifactProcessingSettingsPersistenceExpert();
		settingsPersistenceExpert.setScopeConfiguration(scopeCfg);
		Settings settings = settingsPersistenceExpert.loadSettings();
				
		if (validators != null) {
			for (Validator validator : validators) {
				validator.validate(scopeCfg, settings);
			}
		}		
	}
	
	protected void testResource(String name, Validator ... validators) {
		MavenRepositoryConfiguration rCfg = MavenRepositoryConfiguration.T.create();
		rCfg.setSettingsAsResource( ResourceGenerator.filesystemResourceFromFile(session, new File( contents, name)));
		
		test( rCfg, validators);
	}
}
