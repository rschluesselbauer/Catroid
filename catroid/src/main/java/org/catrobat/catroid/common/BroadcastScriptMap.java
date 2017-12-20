/*
 * Catroid: An on-device visual programming system for Android devices
 * Copyright (C) 2010-2017 The Catrobat Team
 * (<http://developer.catrobat.org/credits>)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * An additional term exception under section 7 of the GNU Affero
 * General Public License, version 3, is available at
 * http://developer.catrobat.org/license_additional_term
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.catrobat.catroid.common;

import org.catrobat.catroid.content.Script;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BroadcastScriptMap {
	private Map<String, Map<String, List<Script>>> broadcastScriptMap = new HashMap<>();

	public boolean containsKey(String key, String sceneName) {
		return broadcastScriptMap.get(sceneName) != null && broadcastScriptMap.get(sceneName).containsKey(key);
	}

	public List<Script> get(String key, String sceneName) {
		Map<String, List<Script>> map = broadcastScriptMap.get(sceneName);
		if (map != null) {
			return map.get(key);
		}
		return null;
	}

	public List<Script> put(String sceneName, String key, Script script) {
		Map<String, List<Script>> map = broadcastScriptMap.get(sceneName);
		if (map == null) {
			map = new HashMap<>();
			broadcastScriptMap.put(sceneName, map);
		}
		List<Script> oldScripts = map.get(key);
		if (oldScripts == null) {
			oldScripts = new ArrayList<>();
			map.put(key, oldScripts);
		}
		oldScripts.add(script);
		return oldScripts;
	}

	public void remove(String key, String sceneName) {
		if (containsKey(key, sceneName)) {
			broadcastScriptMap.get(sceneName).remove(key);
		}
	}

	public void clear() {
		broadcastScriptMap.clear();
	}

	public void clearScene(String sceneName) {
		if (broadcastScriptMap.containsKey(sceneName)) {
			broadcastScriptMap.get(sceneName).clear();
		}
	}
}
