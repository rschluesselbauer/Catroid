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
package org.catrobat.catroid.content;

import org.catrobat.catroid.content.bricks.ScriptBrick;
import org.catrobat.catroid.content.bricks.WhenRaspiPinChangedBrick;

public class RaspiInterruptScript extends Script {
	// BC-TODO: Test
	private static final long serialVersionUID = 1L;
	private String pin;
	private String eventValue;

	public RaspiInterruptScript(String pin, String eventValue) {
		this.pin = pin;
		this.eventValue = eventValue;
	}

	@Override
	public ScriptBrick getScriptBrick() {
		if (brick == null) {
			brick = new WhenRaspiPinChangedBrick(this);
		}
		return brick;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public void setEventValue(String eventValue) {
		this.eventValue = eventValue;
	}

	public String getPin() {
		return pin;
	}

	public String getEventValue() {
		return eventValue;
	}

	@Override
	public Script copyScriptForSprite(Sprite copySprite) {
		RaspiInterruptScript cloneScript = new RaspiInterruptScript(pin, eventValue);
		doCopy(copySprite, cloneScript);
		return cloneScript;
	}
}
