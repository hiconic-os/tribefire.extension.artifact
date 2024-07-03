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
package tribefire.extension.artifact.management.api.model.data;

import java.util.List;

import com.braintribe.model.generic.GenericEntity;
import com.braintribe.model.generic.reflection.EntityType;
import com.braintribe.model.generic.reflection.EntityTypes;

/**
 * data as sent be the local repository cleanup
 * @author pit
 *
 */
public interface RepositoryRepairData extends GenericEntity {
		
	EntityType<RepositoryRepairData> T = EntityTypes.T(RepositoryRepairData.class);
	
	String numberOfCleanedLockFiles = "numberOfCleanedLockFiles";
	String cleanedLocalFiles = "cleanedLocalFiles";
	String localRepositoryPath = "localRepositoryPath";

	/**
	 * @return - the number of cleaned lock files
	 */
	Integer getNumberOfCleanedLockFiles();
	void setNumberOfCleanedLockFiles(Integer value);
	
	/**
	 * @return - a {@link List} of the fully qualified names of the cleaned lock files 
	 */
	List<String> getCleanedLockFiles();
	void setCleanedLockFiles(List<String> value);
	
	String getLocalRepositoryPath();
	void setLocalRepositoryPath(String value);


}
