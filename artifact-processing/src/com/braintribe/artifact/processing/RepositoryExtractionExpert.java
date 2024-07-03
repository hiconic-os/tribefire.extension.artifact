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
package com.braintribe.artifact.processing;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import com.braintribe.build.artifact.name.NameParser;
import com.braintribe.build.artifacts.mc.wire.buildwalk.space.FilterConfigurationSpace;
import com.braintribe.logging.Logger;
import com.braintribe.model.artifact.Dependency;
import com.braintribe.model.artifact.PartTuple;
import com.braintribe.model.artifact.Solution;

public class RepositoryExtractionExpert {
	private static Logger log = Logger.getLogger(RepositoryExtractionExpert.class);
	private List<Pattern> globalExclusions;
	
	private boolean isExcluded(Solution solution) {
		return isExcluded(NameParser.buildName(solution));
	}
	
	private boolean isExcluded(String artifactId) {
		if (globalExclusions != null && !globalExclusions.isEmpty()) {
			for (Pattern p : globalExclusions) {
				if (p.matcher(artifactId).matches()) {
					log.debug("Excluding artifact "+artifactId+" because it is globally excluded.");
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean isIncluded(Dependency dependency) {
		String scope = dependency.getScope();
		
		boolean excluded = dependency.getOptional() || (scope != null && (scope.equalsIgnoreCase("provided") || scope.equalsIgnoreCase("test")));
		return !excluded;
	}
	
	private List<Pattern> parseExclusionPatterns(List<String> lines) {
		List<Pattern> exclusions = new ArrayList<>();
		for (String line : lines) {
			if (line.trim().length() > 0 && !line.startsWith("#")) {
				try {
					exclusions.add(Pattern.compile(line));
				} catch(Exception e) {
					log.warn("Could not compile pattern [" + line + "]", e);
				}
			}
		}
		return exclusions;		
	}

	private class RepositoryExtractFilterConfiguration extends FilterConfigurationSpace {
		private List<Pattern> globalExclusions;
		
		public RepositoryExtractFilterConfiguration( List<Pattern> exclusions) {
			this.globalExclusions = exclusions;
		}
		
		@Override
		public Predicate<? super Solution> solutionFilter() {
			// filter the solutions with the configured global exclusions
			return RepositoryExtractionExpert.this::isExcluded;
		}

		@Override
		public Predicate<? super Dependency> dependencyFilter() {
			// filter the dependency with the correct scope and optional flag
			return RepositoryExtractionExpert.this::isIncluded;
		}

		@Override
		public Predicate<? super PartTuple> partFilter() {
			// every part should be included as this is a full export of the artifact
			return p -> true;
		}
	}

}
