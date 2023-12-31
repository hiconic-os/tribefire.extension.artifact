// ============================================================================
// Copyright BRAINTRIBE TECHNOLOGY GMBH, Austria, 2002-2022
// 
// This library is free software; you can redistribute it and/or modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either version 3 of the License, or (at your option) any later version.
// 
// This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
// 
// You should have received a copy of the GNU Lesser General Public License along with this library; See http://www.gnu.org/licenses/.
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
