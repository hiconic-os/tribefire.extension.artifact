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
package com.braintribe.artifact.processing.backend;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.braintribe.build.artifacts.mc.wire.classwalk.context.WalkerContext;
import com.braintribe.build.artifacts.mc.wire.classwalk.external.contract.Scopes;
import com.braintribe.model.artifact.PartTuple;
import com.braintribe.model.artifact.processing.cfg.resolution.FilterScope;
import com.braintribe.model.artifact.processing.cfg.resolution.ResolutionConfiguration;
import com.braintribe.model.artifact.processing.cfg.resolution.ResolutionScope;
import com.braintribe.model.artifact.processing.part.PartTupleProcessor;
import com.braintribe.model.malaclypse.cfg.denotations.scopes.Scope;

/**
 * converts a modeled walker configuration into MC-wiring's configuration
 * @author pit
 *
 */
public class ArtifactProcessingWalkConfigurationExpert {

	/**
	 * transpose a {@link WalkerContext} from a {@link WalkConfiguration}, or create an empty (standard) one
	 * @param walkCfg - the {@link WalkConfiguration} or null
	 * @return - the resulting {@link WalkerContext}
	 */
	public static WalkerContext acquireWalkerContext( ResolutionConfiguration walkCfg) {
		WalkerContext walkerContext = new WalkerContext();
		if (walkCfg == null) {
			walkerContext.setSkipOptionals( true);
			return walkerContext;
		}
		
		//
		// part tuples
		//
		
		// overriding : completely replace the relevant part tuples		
		List<PartTuple> overridingRelevants = walkCfg.getParts().stream().map( p -> {
			return PartTupleProcessor.fromString(p);
		}).collect( Collectors.toList());				 
		walkerContext.setRelevantPartTuples( overridingRelevants);
		
		//
		// scopes 
		//
		Set<Scope> scopes = new HashSet<>();
		// filter scopes  (may be null)
		Set<FilterScope> filterScopes = walkCfg.getFilterScopes();
		if (filterScopes != null && filterScopes.size() > 0) {
			//  
			List<String> scopeNames = filterScopes.stream().map( fs -> {
				return fs.toString();
			}).collect(Collectors.toList());		
			scopes.addAll( Scopes.buildScopes(scopeNames));
		}

		// resolution scope (may be null)  
		ResolutionScope resolutionScope = walkCfg.getResolutionScope();
		if (resolutionScope != null) { 			
			switch (resolutionScope) {
				case compile:
					scopes.addAll( Scopes.compileScopes());								
					break;
				case runtime:
					scopes.addAll(  Scopes.runtimeScopes());				
					break;
				case test:
					scopes.addAll(  Scopes.testScopes());
					break;
				default:
				case all:
					walkerContext.setIgnoreDependencyScopes( true);
					break;			
			}
		}
		if (scopes.size() > 0) { 
			// any scopes configured (via filter or resolution)
			walkerContext.setScopes( scopes);
		}
		else {
			// no scopes collected -> switch to ignore
			walkerContext.setIgnoreDependencyScopes( true);
		}

		//			
		// optionals
		//
		walkerContext.setSkipOptionals( !walkCfg.getIncludeOptionals());
		
		//
		// tag rule
		//
		String tagRule = walkCfg.getTagRule();	
		if (tagRule != null) {
			// transpose if required
			walkerContext.setTagRule(tagRule);
		}
		
		//
		// type rule
		//
		String typeRule = walkCfg.getTypeRule();
		if (typeRule != null) {
			// transpose if required
			walkerContext.setTypeRule(typeRule);
		}
		
		//	
		// abort for unresolved
		//
		walkerContext.setAbortOnUnresolvedDependency( walkCfg.getAbortOnUnresolvedDependency());
		return walkerContext;
	}
}
