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
import javax.swing.ComboBoxModel;

/**
 * @author Chris Casola
 * @version Apr 16, 2013
 *
 */
@SuppressWarnings("serial")
public class SampleTypesModel extends AbstractListModel implements ComboBoxModel{
	
	protected final List<String> sampleTypes;
	
	protected Object selectedItem = null;
	
	public SampleTypesModel() {
		sampleTypes = new ArrayList<String>();
	}
	
	public void updateModel(String[] types) {
		sampleTypes.clear();
		selectedItem = null;
		for (int i = 0; i < types.length; i++) {
			sampleTypes.add(types[i]);
		}
		
		if (sampleTypes.size() > 0) {
			selectedItem = sampleTypes.get(0);
		}
		this.fireContentsChanged(this, 0, getSize());
	}

	/* (non-Javadoc)
	 * @see javax.swing.ListModel#getElementAt(int)
	 */
	@Override
	public Object getElementAt(int index) {
		return sampleTypes.get(index);
	}

	/* (non-Javadoc)
	 * @see javax.swing.ListModel#getSize()
	 */
	@Override
	public int getSize() {
		return sampleTypes.size();
	}

	@Override
	public Object getSelectedItem() {
		return selectedItem;
	}

	@Override
	public void setSelectedItem(Object item) {
		selectedItem = item;
	}

}
