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
