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
package com.braintribe.model.artifact.processing;

import com.braintribe.model.generic.GenericEntity;
import com.braintribe.model.generic.reflection.EntityType;
import com.braintribe.model.generic.reflection.EntityTypes;

public interface PartIdentification extends GenericEntity{

	EntityType<PartIdentification> T = EntityTypes.T(PartIdentification.class);
	
	String classifier = "classifier";
	String type = "type";
	
	/**
	 * @return - the classifier of the part (the thing after the version in the file's name)
	 */
	String getClassifier();
	/**
	 * @param classifier - the classifier of the part (the thing after the version in the file's name)
	 */
	void setClassifier( String classifier);
	
	/**
	 * @return - the type of the part (the extension of the file)
	 */
	String getType();
	/**
	 * @param partType - the type of the part (the extension of the file)
	 */
	void setType( String partType);
	
}
