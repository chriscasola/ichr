/*******************************************************************************
 * Copyright (c) 2013 -- Steve Berselli, Chris Casola,
 *							Silvia Zamora-Palacios, Dongni Zhang
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package ichr.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

/**
 * @author Chris Casola
 * @version Apr 17, 2013
 *
 */
@SuppressWarnings("serial")
public class UserNamesModel extends AbstractListModel {
	
	protected final List<String> usernames;
	
	public UserNamesModel() {
		usernames = new ArrayList<String>();
	}
	
	public void updateModel(String[] names) {
		usernames.clear();
		for (int i = 0; i < names.length; i++) {
			usernames.add(names[i]);
		}
		this.fireContentsChanged(this, 0, getSize());
	}

	/* (non-Javadoc)
	 * @see javax.swing.ListModel#getElementAt(int)
	 */
	@Override
	public Object getElementAt(int index) {
		return usernames.get(index);
	}

	/* (non-Javadoc)
	 * @see javax.swing.ListModel#getSize()
	 */
	@Override
	public int getSize() {
		return usernames.size();
	}

}
