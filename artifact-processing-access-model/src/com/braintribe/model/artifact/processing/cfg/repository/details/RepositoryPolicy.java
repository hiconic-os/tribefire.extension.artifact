// ============================================================================
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
package com.braintribe.model.artifact.processing.cfg.repository.details;

import com.braintribe.model.generic.GenericEntity;
import com.braintribe.model.generic.reflection.EntityType;
import com.braintribe.model.generic.reflection.EntityTypes;

/**
 * declares settings (enablement, update policy enum and parameters (RH URL, intervall expression)<br/> 
 * 
 * {@link RepositoryPolicy#getUpdatePolicyParameter()} : contains either the RH URL or the interval specification, 
 * see Maven documentation  ([interval:]XXX, XXX in minutes)
 * 
 * @author pit
 *
 */
public interface RepositoryPolicy extends GenericEntity {
	
	final EntityType<RepositoryPolicy> T = EntityTypes.T(RepositoryPolicy.class);
	
	// default ignore.. 
	/**
	 * @return - the {@link ChecksumPolicy} set (may be null, defaulting to 'ignore')
	 */
	ChecksumPolicy getCheckSumPolicy();
	/**
	 * @param checkSumPolicy - the {@link ChecksumPolicy} (may be not-set/null, defaulting to 'ignore')
	 */
	void setCheckSumPolicy( ChecksumPolicy checkSumPolicy);

	// default : never ?
	/**
	 * @return - the {@link UpdatePolicy} for the {@link RepositoryPolicy}.
	 * May be null, defaults to 'never'
	 */
	UpdatePolicy getUpdatePolicy();
	/**
	 * @param updatePolicy- the {@link UpdatePolicy} for the {@link RepositoryPolicy}.
	 * May be null, defaults to 'never'
	 */
	void setUpdatePolicy( UpdatePolicy updatePolicy);
	
	/**
	 * if the update policy requires parameters {@link UpdatePolicy#never} or {@link UpdatePolicy#interval}, this must contain it
	 * @return - the required parameter for the chosen {@link UpdatePolicy}  
	 */
	String getUpdatePolicyParameter();
	/**
	 * if the update policy requires parameters {@link UpdatePolicy#never} or {@link UpdatePolicy#interval}, this must contain it
	 * @param updatePolicyParameter - the required parameter for the chosen {@link UpdatePolicy}
	 */
	void setUpdatePolicyParameter(String updatePolicyParameter);
	
	/**
	 * @return - whether to enable this {@link RepositoryPolicy}
	 */
	boolean getEnabled();
	/**
	 * @param enabled - whether to enable this {@link RepositoryPolicy}
	 */
	void setEnabled( boolean enabled);
	
	

}
