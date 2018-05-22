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

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.utils.Array;

import org.catrobat.catroid.content.Look;

import java.util.Iterator;

public class StopOtherScriptsAction extends Action {

	private ScriptSequenceAction currentAction;
	private Look currentLook;

	@Override
	public boolean act(float delta) {
		if (currentLook == null || currentLook.getActions() == null) {
			return true;
		}
		Look look = (Look) actor;
		Array<Action> otherActions = getOtherActions(look);
		look.stopThreads(otherActions);
		return true;
	}

	private Array<Action> getOtherActions(Look look) {
		Array<Action> actions = new Array<>(look.getActions());
		Iterator<Action> it = actions.iterator();
		Action action;

		while (it.hasNext()) {
			action = it.next();
			if (action instanceof ScriptSequenceAction
					&& ((ScriptSequenceAction) action).getScript() == currentAction.getScript()) {
				it.remove();
			}
		}
		return actions;
	}

	public void setCurrentAction(ScriptSequenceAction currentAction) {
		this.currentAction = currentAction;
	}

	public void setCurrentLook(Look currentLook) {
		this.currentLook = currentLook;
	}
}
