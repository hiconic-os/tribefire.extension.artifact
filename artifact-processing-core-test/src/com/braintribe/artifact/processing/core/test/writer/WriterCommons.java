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
package com.braintribe.artifact.processing.core.test.writer;

import java.util.List;

import com.braintribe.model.artifact.info.RepositoryOrigin;

public class WriterCommons {

	protected String tab( int index) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < index; i++) {
			buffer.append( '\t');
		}
		return buffer.toString();
	}
	
	protected String dump( List<RepositoryOrigin> origins) {
		StringBuffer buffer = new StringBuffer();
		origins.stream().forEach( o -> {
			if (buffer.length() > 0) {
				buffer.append(",");
			}
			buffer.append( dump( o));
		});
		return "(" + buffer.toString() + ")";
	}
	
	protected String dump( RepositoryOrigin origin) {
		return origin.getName() + "@" + origin.getUrl();
	}
}
