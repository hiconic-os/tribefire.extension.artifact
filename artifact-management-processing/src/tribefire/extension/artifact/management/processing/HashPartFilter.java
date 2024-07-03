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

import java.util.function.Predicate;
import java.util.regex.Pattern;

public class HashPartFilter implements Predicate<String> {
	private static Pattern shaPattern = Pattern.compile(".*\\.sha\\d*$");
	public static final HashPartFilter INSTANCE = new HashPartFilter();
	
	@Override
	public boolean test(String partType) {
		if (partType == null)
			return false;
		
		if (partType.endsWith(".md5"))
			return true;
		
		if (shaPattern.matcher(partType).matches())
			return true;
		
		if (partType.endsWith(".asc"))
			return true;
		
		return false;

	}
}
