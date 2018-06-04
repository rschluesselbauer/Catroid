/*
 * Catroid: An on-device visual programming system for Android devices
 * Copyright (C) 2010-2018 The Catrobat Team
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

package org.catrobat.catroid.content.actions;

import android.util.Log;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.utils.Array;

import org.catrobat.catroid.content.Look;
import org.catrobat.catroid.content.Script;

import java.util.Iterator;

public class StopOtherScriptsAction extends Action {
	public static final String TAG = StopOtherScriptsAction.class.getSimpleName();
	private Script currentScript;
	private Look currentLook;

	@Override
	public boolean act(float delta) {
		System.out.println(TAG + "::act: Stop other scripts");
		if (currentLook == null || currentLook.getActions() == null) {
			throw new RuntimeException("CurrentLook must be set to a valid look");
		}
		Look look = (Look) actor;
		Array<Action> otherActions = getOtherThreads(look);
		look.stopThreads(otherActions);
		System.out.println(TAG + "::act: Stopped actions " + otherActions.toString());
		return true;
	}

	private Array<Action> getOtherThreads(Look look) {
		Array<Action> actions = new Array<>(look.getActions());
		Iterator<Action> it = actions.iterator();
		Action action;

		while (it.hasNext()) {
			action = it.next();
			if (action instanceof ScriptSequenceAction
					&& ((ScriptSequenceAction) action).getScript() == currentScript) {
				it.remove();
				System.out.println(TAG + "::getOtherThreads: Remove thread with script " + ((ScriptSequenceAction)
						action).getScript().toString() + " from otherThreads");
				Log.d(TAG, "getOtherThreads: Remove thread with script " + ((ScriptSequenceAction)
						action).getScript() + " from otherThreads");
			} else {
				System.out.println(TAG + "::getOtherThreads: Add thread with script " + ((ScriptSequenceAction)
						action).getScript().toString() + " to otherThreads");
				Log.d(TAG, "getOtherThreads: Add thread with script " + ((ScriptSequenceAction)
						action).getScript() + " to otherThreads");
			}
		}
		return actions;
	}

	public void setCurrentScript(Script currentScript) {
		this.currentScript = currentScript;
	}

	public void setCurrentLook(Look currentLook) {
		this.currentLook = currentLook;
	}
}
