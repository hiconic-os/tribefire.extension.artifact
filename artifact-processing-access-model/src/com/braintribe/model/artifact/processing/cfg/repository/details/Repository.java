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
package com.braintribe.model.artifact.processing.cfg.repository.details;

import java.net.URL;

import com.braintribe.model.generic.GenericEntity;
import com.braintribe.model.generic.annotation.meta.Confidential;
import com.braintribe.model.generic.annotation.meta.Mandatory;
import com.braintribe.model.generic.reflection.EntityType;
import com.braintribe.model.generic.reflection.EntityTypes;

/**
 * a dumbed- down/simplified version of a repository, 
 * containing only the minimal requirements, but still allowing all sensible configuration possibilities
 * 
 * @author pit
 *
 */
public interface Repository extends GenericEntity {

	final EntityType<Repository> T = EntityTypes.T(Repository.class);

	/**
	 * @return - the name (and ID) of the {@link Repository}
	 */
	@Mandatory
	String getName();
	/**
	 * @param name - the name (and ID) of the {@link Repository}
	 */
	void setName(String name);
	
	/**
	 * @return - the {@link String} representation of the {@link URL} of the {@link Repository}. May contain variables like ${env.*}
	 */
	@Mandatory
	String getUrl();
	/**
	 * @param url  - the {@link String} representation of the {@link URL} of the {@link Repository}. May contain variables like ${env.*}
	 */
	void setUrl( String url);
	
	/**
	 * @return - the name of the user as {@link String}. May contain variables like ${env.*}
	 */
	String getUser();
	/**
	 * @param user - the name of the user as {@link String}. May contain variables like ${env.*}
	 */
	void setUser( String user);
	
	/**
	 * @return - the password of the user as {@link String}. May contain variables like ${env.*}
	 */
	@Confidential
	String getPassword();
	/**
	 * @param password - the password of the user as {@link String}. May contain variables like ${env.*}
	 */
	void setPassword( String password);
	
	/**
	 * @return - the {@link RepositoryPolicy} declared for this {@link Repository} when it comes to RELEASES.
	 * May be null, defaults to disabled
	 */
	RepositoryPolicy getRepositoryPolicyForReleases();
	/**
	 * @param roleSettings - the {@link RepositoryPolicy} declared for this {@link Repository} when it comes to RELEASES.
	 * May be null, defaults to disabled
	 */
	void setRepositoryPolicyForReleases( RepositoryPolicy roleSettings);
	
	/** 
	 * @return - the {@link RepositoryPolicy} declared for this {@link Repository} when it comes to SNAPSHOTS.
	 * May be null, defaults to disabled 
	 */
	RepositoryPolicy getRepositoryPolicyForSnapshots();
	/**
	 * @param roleSettings -the {@link RepositoryPolicy} declared for this {@link Repository} when it comes to SNAPSHOTS.
	 * May be null, defaults to disabled
	 */
	void setRepositoryPolicyForSnapshots( RepositoryPolicy roleSettings);		
	
	/**
	 * some repositories such as Maven Central (sic!) have incomplete indices. Ours do not.<br/> 
	 * If set to true, it will believe what the repo tells it (be faster), 
	 * if set to false, it will double check if the repo tells it that there's nothing there (slower ) 
	 * @return - whether the metadata indices of the {@link Repository} are guaranteed to be correct
	 */
	boolean getRemoteIndexCanBeTrusted();
	/**
	 * some repositories such as Maven Central (sic!) have incomplete indices. Ours do not.<br/> 
	 * If set to true, it will believe what the repo tells it (be faster), 
	 * if set to false, it will double check if the repo tells it that there's nothing there (slower ) 
	 * @param trustworthy - whether the metadata indices of the {@link Repository} are guaranteed to be correct
	 */
	void setRemoteIndexCanBeTrusted( boolean trustworthy);
	
	/**
	 * some repositories do not like somebody enumerating their content. Ours don't mind.<br/>
	 * true if indexing (enumerating) is fine -> faster,
	 * false if indexing (enumerating) is forbidden -> slower,
	 * @return - whether indexing (enumerating) is fine
	 */
	boolean getAllowsIndexing();
	/**
	 * some repositories do not like somebody enumerating their content. Ours don't mind.<br/>
	 * true if indexing (enumerating) is fine -> faster,
	 * false if indexing (enumerating) is forbidden -> slower,
	 * @param - whether indexing (enumerating) is fine
	 */
	void setAllowsIndexing( boolean iterable);
	
	/**
	 * some repositories have bad https relevant certificates. Ours do not.<br/>
	 * true if the access to the url of the repositories needs be to lenient (insecure),
	 * false if the access to the url of the repositories can be strict (secure),
	 * @return - whether the URL is weakly certified
	 */
	boolean getIsWeaklyCertified();
	/**
	 * some repositories have bad https relevant certificates. Ours do not.<br/>
	 * true if the access to the url of the repositories needs be to lenient (insecure),
	 * false if the access to the url of the repositories can be strict (secure),
	 * @param - whether the URL is weakly certified
	 */
	void setIsWeaklyCertified( boolean weaklyCertified);
}
