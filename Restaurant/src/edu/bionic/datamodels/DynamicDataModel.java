/* The MIT License
 *
 * Copyright (c) 2010-2012 www.myjeeva.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE. 
 * 
 */
package edu.bionic.datamodels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.ajax4jsf.model.DataVisitor;
import org.ajax4jsf.model.Range;
import org.ajax4jsf.model.SequenceRange;
import org.ajax4jsf.model.ExtendedDataModel;

;

/**
 * @desc Dynamic data model for JSF/Richfaces datatable component
 * @filename DynamicDataModel.java
 * @author Jeevanandam Madanagopal (jeeva@myjeeva.com), changes made by Vadym Turovskyi
 * 
 */
public abstract class DynamicDataModel<E extends edu.bionic.model.DirtyHackForPaging<Integer>> extends ExtendedDataModel<E> {

	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 8687309180897048109L;
	private Integer rowId;
	private Map<Integer, E> wrappedData = new HashMap<Integer, E>();
	private List<Integer> wrappedKeys = null;
	protected Integer totalRows = null;
	private Integer firstRow = 0;
	private Integer numberOfRows = 0;
	private Integer rowIndex = 0;


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ajax4jsf.model.ExtendedDataModel#getRowKey()
	 */
	@Override
	public Object getRowKey() {
		return rowId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ajax4jsf.model.ExtendedDataModel#setRowKey(java.lang.Object)
	 */
	@Override
	public void setRowKey(Object key) {
		this.rowId = (Integer) key;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.ajax4jsf.model.ExtendedDataModel#walk(javax.faces.context.FacesContext
	 * , org.ajax4jsf.model.DataVisitor, org.ajax4jsf.model.Range,
	 * java.lang.Object)
	 */
	@Override
	public void walk(FacesContext context, DataVisitor visitor, Range range, Object argument) {
		 
	    // This is method we have to populate a data for DataTable
	    //Range parameter provides the firstRow value and no. of rows value. Extract those values to local variables
	    this.firstRow = ((SequenceRange) range).getFirstRow();
	    this.numberOfRows = ((SequenceRange) range).getRows();
	 
	    //we can populate the data from Service Call/Web Service Call For eg. Data from database or Search results using Range values
	    //Define getItemsByrange() method to extract the required data by passing the range values
	    List<E> EList = getItemsByrange(this.firstRow, this.numberOfRows.intValue());
	 
	    // Now store the data to wrappedKeys & wrappedData components for the framework to make use of.
	    wrappedKeys = new ArrayList<>();
	    rowIndex = 0;
	    if (!EList.isEmpty()) {
	        for (E item : EList) {
	        wrappedKeys.add(item.getId());
	        wrappedData.put(item.getId(), item);
	        visitor.process(context, item.getId(), argument);
	        rowIndex++;
	        }
	    }
	    
	}

	protected abstract List<E> getItemsByrange(Integer firstRow2, int intValue) ;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.faces.model.DataModel#getRowCount()
	 */
	@Override
	public int getRowCount() {
		if (null == totalRows) {
			return this.totalRows;
		}
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.faces.model.DataModel#getRowData()
	 */
	@Override
	public E getRowData() {
		if (null == rowId) {
			return null;
		}
		return wrappedData.get(rowId);
	}

	

	public ExtendedDataModel<E> getSerializableModel(Range range) {
		if (null != wrappedKeys) {
			return this;
		}
		return null;
	}

	@Override
	public int getRowIndex() {
		return this.rowIndex;
	}
	 
	@Override
	public Object getWrappedData() {
		return this;
	}
	 
	@Override
	public boolean isRowAvailable() {
	    if (rowId == null) {
	        return false;
	    } else {
	       // return dataProvider.hasItemByRowId(rowId);
	    	//TODO CHECK THIS SHIT
	    	return true;
	    }
	}
	 
	@Override
	public void setRowIndex(int rowIndex) {
	    this.rowIndex = rowIndex;
	}
	 
	@Override
	public void setWrappedData(Object data) {
	    throw new UnsupportedOperationException();
	}

}
