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

package org.catrobat.catroid.ui;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import org.catrobat.catroid.content.bricks.UserListBrick;
import org.catrobat.catroid.formulaeditor.UserList;
import org.catrobat.catroid.ui.adapter.UserListAdapterWrapper;
import org.catrobat.catroid.ui.dialogs.NewDataDialog;

public class UserListItemSelectedListenerImpl implements AdapterView.OnItemSelectedListener {

	private UserListBrick userListBrick;

	public UserListItemSelectedListenerImpl(UserListBrick userListBrick) {
		this.userListBrick = userListBrick;
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		if (position == 0 && ((UserListAdapterWrapper) parent.getAdapter()).isTouchInDropDownView()) {
			NewDataDialog dialog = new NewDataDialog((Spinner) parent, NewDataDialog.DialogType.USER_LIST);
			dialog.addUserListDialogListener(userListBrick);
			int spinnerPos = ((UserListAdapterWrapper) parent.getAdapter())
					.getPositionOfItem(userListBrick.getUserList());
			dialog.setUserVariableIfCancel(spinnerPos);
			dialog.show(((Activity) view.getContext()).getFragmentManager(),
					NewDataDialog.DIALOG_FRAGMENT_TAG);
		}
		((UserListAdapterWrapper) parent.getAdapter()).resetIsTouchInDropDownView();
		userListBrick.setUserList((UserList) parent.getItemAtPosition(position));
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		userListBrick.setUserList(null);
	}
}
