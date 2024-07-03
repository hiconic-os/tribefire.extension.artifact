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

import java.util.Arrays;
import java.util.Collections;

import org.junit.Assert;
import org.junit.Test;

import com.braintribe.model.artifact.processing.cfg.repository.RepositoryConfiguration;
import com.braintribe.model.artifact.processing.cfg.repository.SimplifiedRepositoryConfiguration;
import com.braintribe.model.artifact.processing.cfg.repository.details.Repository;
import com.braintribe.model.maven.settings.Settings;

/**
 * @author pit
 *
 */
public class SettingsPersistenceLab extends AbstractPersistenceLab {
	

	@Test
	public void testSingleModelled() {
		
		SimplifiedRepositoryConfiguration msCfg = SimplifiedRepositoryConfiguration.T.create();
		
		Repository r = RepoHelper.createRepoOne();
				
		msCfg.setRepositories( Collections.singletonList(r));
		
		//
		Validator validator = new Validator() {			
			@Override
			public void validate(RepositoryConfiguration scopeConfiguration, Settings settings) {
				//											
				RepoHelper.validateRepo(r, settings);							
			}
		};
		
		test( msCfg, validator);
	}
	
	
	@Test
	public void testDoubleModelled() {
		
		SimplifiedRepositoryConfiguration msCfg = SimplifiedRepositoryConfiguration.T.create();
		
		Repository rOne = RepoHelper.createRepoOne();
		
		Repository rTwo = RepoHelper.createRepoTwo();
						
		msCfg.setRepositories( Arrays.asList( rOne, rTwo));
		
		Validator validator = new Validator() {			
			@Override
			public void validate(RepositoryConfiguration scopeConfiguration, Settings settings) {
				//				
				for (Repository repo : ((SimplifiedRepositoryConfiguration) scopeConfiguration).getRepositories()) {					
					RepoHelper.validateRepo(repo, settings);
				}				
			}
		};
		
		test( msCfg, validator);
	}


	
	@Test
	public void testStandardResource() {
		Validator validator = new Validator() {			
			@Override
			public void validate(RepositoryConfiguration scopeConfiguration, Settings settings) {
				Assert.assertTrue( "no settings transferred", settings != null);				
			}
		};
		testResource( "settings.xml", validator);
	}
	

}
