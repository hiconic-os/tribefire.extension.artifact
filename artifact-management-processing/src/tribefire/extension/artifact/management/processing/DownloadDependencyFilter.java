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
package tribefire.extension.artifact.management.processing;

import java.util.Set;
import java.util.function.Predicate;

import com.braintribe.model.artifact.analysis.AnalysisDependency;

import tribefire.extension.artifact.management.api.model.request.ResolutionScope;

public class DownloadDependencyFilter implements Predicate<AnalysisDependency> {
	private Set<ResolutionScope> scopes;
	private boolean includeOptional;
	
	public DownloadDependencyFilter(Set<ResolutionScope> scopes, boolean includeOptional) {
		super();
		this.scopes = scopes;
		this.includeOptional = includeOptional;
	}

	@Override
	public boolean test(AnalysisDependency dependency) {
		if (!includeOptional && dependency.getOptional())
			return false;
		
		for (ResolutionScope scope: scopes) {
			String depScope = dependency.getScope();
			
			switch (depScope) {
			case "parent":
			case "import":
				return true;
			}
			
			switch (scope) {
			case compile:
				switch(depScope) {
				case "compile":
				case "provided":
					return true;
				}
				break;
			case runtime:
				switch(depScope) {
				case "compile":
				case "runtime":
					return true;
				}
				break;
			case test:
				switch(depScope) {
				case "compile":
				case "test":
					return true;
				}
				break;
			default:
				break;
			}
		}
		return false;
	}
}
