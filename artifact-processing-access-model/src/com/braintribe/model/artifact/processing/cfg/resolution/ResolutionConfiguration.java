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
package com.braintribe.model.artifact.processing.cfg.resolution;

import java.util.List;
import java.util.Set;

import com.braintribe.model.artifact.processing.cfg.NamedConfiguration;
import com.braintribe.model.generic.reflection.EntityType;
import com.braintribe.model.generic.reflection.EntityTypes;

/**
 * the configuration required for the dependency-tree traversing features   
 * @author pit
 *
 */
public interface ResolutionConfiguration extends NamedConfiguration {
	
	final EntityType<ResolutionConfiguration> T = EntityTypes.T(ResolutionConfiguration.class);
	
	/** 
	 * a comma-delimited string of allowed/disallowed tags, ie {@code [!]<tag>[,[!]<tag>,..]}, also '*' or '!*' is valid
	 * @return - the tag rule to use.
	 * May be null, defaults to ignore tags 
	 */
	String getTagRule();
	/**
	 * a comma-delimited string of allowed/disallowed tags, ie {@code [!]<tag>[,[!]<tag>,..]}, also '*' or '!*' is valid
	 * @param tagRule - the tag rule to use.
	 * May be null, defaults to ignore tags
	 */
	void setTagRule(String tagRule);
	
	/**
	 * a comma-delimited string of allowed classifier/packaging tuples, ie {@code [<classifier>:]<packaging>[,[<classifier>:]<packaging>,..]}
	 * @return - the type rule to use.
	 * May be null, defaults to standard (no packaging or jar packaging) 
	 */
	String getTypeRule();
	/**
	 * a comma-delimited string of allowed classifier/packaging tuples, ie {@code [<classifier>:]<packaging>[,[<classifier>:]<packaging>,..]}
	 * @param typeRule - the type rule to use
	 * May be null, defaults to standard (no packaging or jar packaging) 
	 */
	void setTypeRule( String typeRule);
		
	/**
	 * @return - whether to abort if an unresolved dependency is found (or to continue)
	 */
	boolean getAbortOnUnresolvedDependency();
	/**
	 * @param abort - whether to abort if an unresolved dependency is found (or to continue)
	 */
	void setAbortOnUnresolvedDependency( boolean abort);
	
	/**
	 * @return - the {@link ResolutionScope} for this configuration
	 */
	ResolutionScope getResolutionScope();
	/**
	 * @param semantics - the {@link ResolutionScope} for this configuration
	 */
	void setResolutionScope( ResolutionScope semantics);
	
	/**
	 * @return - a {@link Set} of {@link FilterScope} to filter the dependencies while traversing
	 */
	Set<FilterScope> getFilterScopes();
	/**
	 * @param filterscopes - a {@link Set} of {@link FilterScope} to filter the dependencies while traversing
	 */
	void setFilterScopes( Set<FilterScope> filterscopes);
		
	/**
	 * @return - whether dependencies tagged 'optional' should be included
	 */
	boolean getIncludeOptionals();
	/**
	 * @param includeOptionals - whether dependencies tagged 'optional' should be included
	 */
	void setIncludeOptionals( boolean includeOptionals);
	
	
	/**
	 * @return - in what order the returned solutions should appear in
	 */
	ResolutionSortOrder getSortOrder();
	
	/**
	 * @param order - in what order the returned solutions should appear in (default is buildOrder)
	 */
	void setSortOrder( ResolutionSortOrder order);

	/**
	 * declares what parts of the artifacts should be retrieved 
	 * @return - a {@link List} of {@code <classifier>:<type>} tuples.
	 * May be null, only POM are retrieved
	 */
	List<String> getParts();
	/**
	 * declares what parts of the artifacts should be retrieved
	 * @param tuples  - a {@link List} of {@code <classifier>:<type>} tuples
	 * May be null, only POM are retrieved
	 */
	void setParts( List<String> tuples);		
}
