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

import com.badlogic.gdx.scenes.scene2d.Event;

import java.util.ArrayList;
import java.util.List;

public class BroadcastEvent extends Event {
	private EventIdentifier eventIdentifier;
	private Sprite sender;
	private boolean waitForCompletion;
	private List<Sprite> interrupters = new ArrayList<>();

	public void setEventIdentifier(EventIdentifier eventIdentifier) {
		this.eventIdentifier = eventIdentifier;
	}

	public void setWaitForCompletion(boolean waitForCompletion) {
		this.waitForCompletion = waitForCompletion;
	}

	public boolean removeInterrupter(Sprite sprite) {
		return interrupters.remove(sprite);
	}

	public void addInterrupter(Sprite sprite) {
		interrupters.add(sprite);
	}

	public EventIdentifier getEventIdentifier() {
		return eventIdentifier;
	}

	public boolean waitForCompletion() {
		return waitForCompletion;
	}

	public Sprite getSender() {
		return sender;
	}

	public void setSender(Sprite sender) {
		this.sender = sender;
	}

	public List<Sprite> getInterrupters() {
		return interrupters;
	}
}
