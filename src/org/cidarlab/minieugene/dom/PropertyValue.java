/*
Copyright (c) 2012 Boston University.
All rights reserved.
Permission is hereby granted, without written agreement and without
license or royalty fees, to use, copy, modify, and distribute this
software and its documentation for any purpose, provided that the above
copyright notice and the following two paragraphs appear in all copies
of this software.

IN NO EVENT SHALL BOSTON UNIVERSITY BE LIABLE TO ANY PARTY
FOR DIRECT, INDIRECT, SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES
ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
BOSTON UNIVERSITY HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

BOSTON UNIVERSITY SPECIFICALLY DISCLAIMS ANY WARRANTIES,
INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. THE SOFTWARE
PROVIDED HEREUNDER IS ON AN "AS IS" BASIS, AND BOSTON UNIVERSITY HAS
NO OBLIGATION TO PROVIDE MAINTENANCE, SUPPORT, UPDATES,
ENHANCEMENTS, OR MODIFICATIONS.
 */

package org.cidarlab.minieugene.dom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.cidarlab.minieugene.constants.PropertyType;
import org.cidarlab.minieugene.exception.EugeneException;


/**
 * 
 * @author Ernst Oberortner
 */
public class PropertyValue 
	extends Property {

	private static final long serialVersionUID = 3074125010808580574L;

	private boolean bool;
	private String txt;
	private double num;
	private List<Double> numList;
	private List<String> txtList;

	public PropertyValue(String name, PropertyType type, String value) {
		super(name, type);

		txt = new String();
		num = 0.0;
		bool = false;
		numList = new ArrayList<Double>();
		txtList = new ArrayList<String>();
	}

	public PropertyValue(Property property) {
		super(property.getName(), property.getType());
	}
	
	public void setTxt(String txt) {
		if (null != txt) {
			if (txt.startsWith("\"") && txt.endsWith("\"")) {
				this.txt = txt.substring(1, txt.length() - 1);
				return;
			}
		}
		this.txt = txt;
	}

	public String getTxt() {
		return this.txt;
	}

	public void setTxtList(ArrayList<String> txtList) {
		this.txtList = txtList;
	}

	public void addTxt(String s) {
		if (PropertyType.TXTLIST.equals(this.type)) {
			this.txtList.add(s);
		} else {
			System.err.println("cannot add a string to property "
					+ this.getName());
		}
	}

	public void setTxtList(List<String> txtList) {
		if (null == txtList)
			return;
		this.txtList = new ArrayList<String>();
		Iterator<String> it = txtList.iterator();
		while (it.hasNext()) {
			this.txtList.add(it.next());
		}
	}

	public List<String> getTxtList() {
		return this.txtList;
	}

	public void setNum(double num) {
		this.num = num;
	}

	public double getNum() {
		return this.num;
	}

	public void setNumList(List<Double> numList) {
		this.numList = numList;
	}

	public void addNum(double d) {
		if (PropertyType.NUMLIST.equals(this.type)) {
			this.numList.add(new Double(d));
		} else {
			System.err.println("cannot add a number to property "
					+ this.getName());
		}
	}

	public List<Double> getNumList() {
		return this.numList;
	}

	public void setBoolean(boolean bool) {
		this.bool = bool;
	}

	public boolean getBoolean() {
		return this.bool;
	}

	public void setValue(PropertyValue value) {
		if (PropertyType.NUM.equals(this.type)) {
			this.num = Double.parseDouble(String.valueOf(value.getNum()));
		} else if (PropertyType.NUMLIST.equals(this.type)) {
			this.numList = new ArrayList<Double>();
			for (int i = 0; i < value.getNumList().size(); i++) {
				this.numList.add(new Double(value.getNumList().get(i)));
			}
		} else if (PropertyType.TXT.equals(this.type)) {
			this.txt = new String(value.getTxt());
		} else if (PropertyType.TXTLIST.equals(this.type)) {
			this.txtList = new ArrayList<String>();
			for (int i = 0; i < value.getTxtList().size(); i++) {
				this.txtList.add(new String(value.getTxtList().get(i)));
			}
		} else if (PropertyType.BOOLEAN.equals(this.type)) {
			this.bool = new Boolean(String.valueOf(value.getBoolean()));
		}
	}

	public String getValue() {
		if (PropertyType.NUM.equals(this.type)) {
			String numberD = String.valueOf(this.num);
	        numberD = numberD.substring( numberD.indexOf ( "." )+1);
	        
	        if(Integer.parseInt(numberD) == 0) {
				return String.valueOf((int)this.num);
	        } else {
	        	return String.valueOf(this.num);
	        }		
	    } else if (PropertyType.NUMLIST.equals(this.type)) {
			return this.numList.toString();
		} else if (PropertyType.TXT.equals(this.type)) {
			return this.txt;
		} else if (PropertyType.TXTLIST.equals(this.type)) {
			return this.txtList.toString();
		} else if (PropertyType.BOOLEAN.equals(this.type)) {
			return String.valueOf(this.bool);
		}
		return new String();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(PropertyType.NUM.equals(this.type)) {
			
			String numberD = String.valueOf(this.num);
	        numberD = numberD.substring( numberD.indexOf ( "." )+1);
	        
	        if(Integer.parseInt(numberD) == 0) {
				sb.append((int)this.num);
	        } else {
	        	sb.append(this.num);
	        }
		} else {
			sb.append(this.getValue());
		}
		return sb.toString();
	}

	public boolean compareTo(PropertyValue objVariable) {
		if (this.equals(objVariable)) {
			return true;
		}
		return false;
	}

	public boolean equals(NamedElement objElement) {
		if (objElement instanceof PropertyValue) {
			PropertyValue objVar = (PropertyValue) objElement;
			if (this.getValue() == null && objVar == null
					|| this.getValue() != null
					&& this.getValue().equals(objVar.getValue())) {
				return true;
			}
		}
		return false;
	}

	public int size() {
		if (PropertyType.NUM.equals(this.getType())
				|| PropertyType.BOOLEAN.equals(this.getType())) {
			return 1;
		} else if (PropertyType.NUMLIST.equals(this.getType())) {
			return this.getNumList().size();
		} else if (PropertyType.TXT.equals(this.getType())) {
			return this.getTxt().length();
		} else if (PropertyType.TXTLIST.equals(this.getType())) {
			return this.getTxtList().size();
		}
		return -1;
	}


//	public NamedElement get(int idx) {
//		if (PropertyType.NUMLIST.equals(this.getType())) {
//			if (idx >= 0 && idx < this.getNumList().size()) {
//				PropertyValue objVariable = new PropertyValue(PropertyType.NUM,
//						PropertyType.NUM);
//				objVariable.setNum(this.numList.get(idx));
//				return objVariable;
//			}
//		} else if (PropertyType.TXTLIST.equals(this.getType())) {
//			if (idx >= 0 && idx < this.getTxtList().size()) {
//				PropertyValue objVariable = new PropertyValue(PropertyType.TXT,
//						PropertyType.TXT);
//				objVariable.setTxt(this.getTxtList().get(idx));
//				return objVariable;
//			}
//		} else if (PropertyType.TXT.equals(this.getType())) {
//			if (idx >= 0 && idx < this.getTxt().length()) {
//				PropertyValue objVariable = new PropertyValue(PropertyType.TXT,
//						PropertyType.TXT);
//				objVariable.setTxt(String.valueOf(this.getTxt().charAt(idx)));
//				return objVariable;
//			}
//		}
//		
//		return null;
//	}

	public void assign(NamedElement objElement)
			throws EugeneException {

		if (objElement instanceof PropertyValue) {
			PropertyValue objVariable = (PropertyValue) objElement;
			if (this.getType().equals(objVariable.getType())) {
				this.setValue((PropertyValue) objElement);
			} else {
				throw new EugeneException("Cannot assign a "
						+ objVariable.getType() + " value to a "
						+ this.getType() + " value!");
			}
		}
	}

	public void set(int idx, NamedElement objElement)
			throws EugeneException {
		
		if (objElement instanceof PropertyValue) {
			PropertyValue objVariable = (PropertyValue) objElement;

			if (PropertyType.NUMLIST.equals(this.getType())
					&& PropertyType.NUM.equals(objVariable.getType())) {
				if (idx >= 0 && idx < this.numList.size()) {
					this.numList.set(idx, new Double(objVariable.getNum()));
				} else {
					throw new EugeneException(
							"The array index (" + idx
									+ ") is out of bounds for the "
									+ this.getName() + " variable!");
				}
			} else if (PropertyType.TXTLIST.equals(this.getType())
					&& PropertyType.TXT.equals(objVariable.getType())) {
				if (idx >= 0 && idx < this.txtList.size()) {
					this.txtList.set(idx, new String(objVariable.getTxt()));
				} else {
					throw new EugeneException(
							"The array index (" + idx
									+ ") is out of bounds for the "
									+ this.getName() + " variable!");
				}
			} else {
				throw new EugeneException("Cannot assign a "
						+ objVariable.getType() + " value to an element of a "
						+ this.getType() + " list!");
			}
		} else {
			throw new EugeneException("Cannot assign the "
					+ objElement + " element to the " + (idx + 1)
					+ " element of the " + this.getName() + " Variable");
		}
	}

	public void set(String sElementName, NamedElement objElement)
			throws EugeneException {
		throw new EugeneException("This is not possible!");
	}

	public void increase() throws EugeneException {
		if (!PropertyType.NUM.equals(this.getType())) {
			throw new EugeneException(
					"Cannot increase the value of a " + this.getType() + "!");
		}
		this.setNum(this.getNum() + 1);
	}

	public void decrease() throws EugeneException {
		if (!PropertyType.NUM.equals(this.getType())) {
			throw new EugeneException(
					"Cannot increase the value of a " + this.getType() + "!");
		}
		this.setNum(this.getNum() - 1);
	}

//	public PropertyValue add(PropertyValue objVariable) 
//			throws EugeneException {
//		PropertyValue retVal = (PropertyValue) null;
//		if (null != objVariable) {
//			if (PropertyType.TXT.equals(this.getType())) {
//				// <TXT> + <TXT>
//				if (PropertyType.TXT.equals(objVariable.getType())) {
//					retVal = new PropertyValue(null, PropertyType.TXT);
//					StringBuilder sb = new StringBuilder();
//					sb.append(this.getTxt());
//					sb.append(objVariable.getTxt());
//					retVal.setTxt(sb.toString());
//
//					// <TXT> + <TXTLIST>
//				} else if (PropertyType.TXTLIST
//						.equals(objVariable.getType())) {
//					retVal = new PropertyValue(null, PropertyType.TXTLIST);
//
//					ArrayList<String> lst = new ArrayList<String>();
//
//					lst.add(this.getTxt());
//
//					int size = objVariable.getTxtList().size();
//					for (int i = 0; i < size; i++) {
//						lst.add(objVariable.getTxtList().get(i));
//					}
//
//					retVal.setTxtList(lst);
//
//					// <TXT> + <NUM>
//				} else if (PropertyType.NUM.equals(objVariable.getType())) {
//					retVal = new PropertyValue(null, PropertyType.TXT);
//					retVal.setTxt(this.getTxt() + (int) objVariable.getNum());
//				}
//			} else if (PropertyType.TXTLIST.equals(this.getType())) {
//				if (PropertyType.TXTLIST.equals(objVariable.getType())) {
//					retVal = new PropertyValue(null, PropertyType.TXTLIST);
//
//					ArrayList<String> lst = new ArrayList<String>();
//					int size = this.getTxtList().size();
//					for (int i = 0; i < size; i++) {
//						lst.add(this.getTxtList().get(i));
//					}
//
//					size = objVariable.getTxtList().size();
//					for (int i = 0; i < size; i++) {
//						lst.add(objVariable.getTxtList().get(i));
//					}
//
//					retVal.setTxtList(lst);
//				} else if (PropertyType.TXT.equals(objVariable.getType())) {
//
//					retVal = new PropertyValue(null, PropertyType.TXTLIST);
//
//					ArrayList<String> lst = new ArrayList<String>();
//					int size = this.getTxtList().size();
//					for (int i = 0; i < size; i++) {
//						lst.add(this.getTxtList().get(i));
//					}
//
//					lst.add(objVariable.getTxt());
//
//					retVal.setTxtList(lst);
//				}
//			} else if (PropertyType.NUM.equals(this.getType())) {
//				// <NUM> + <NUM>
//				if (PropertyType.NUM.equals(objVariable.getType())) {
//					retVal = new PropertyValue(null, PropertyType.NUM);
//					retVal.setNum(this.getNum() + objVariable.getNum());
//
//					// <NUM> + <NUMLIST>
//				} else if (PropertyType.NUMLIST
//						.equals(objVariable.getType())) {
//					retVal = new PropertyValue(null, PropertyType.NUMLIST);
//
//					ArrayList<Double> lst = new ArrayList<Double>();
//
//					lst.add(this.getNum());
//
//					int size = objVariable.getNumList().size();
//					for (int i = 0; i < size; i++) {
//						lst.add(objVariable.getNumList().get(i));
//					}
//
//					retVal.setNumList(lst);
//
//					// <NUM> + <TXT> -> <TXT>
//				} else if (PropertyType.TXT.equals(objVariable.getType())) {
//					retVal = new PropertyValue(null, PropertyType.TXT);
//					try {
//						retVal.setTxt((long)this.getNum() + 
//								objVariable.getTxt());
//					} catch(Exception e) {
//						throw new EugeneException(
//								"I cannot concatenate "+this.getNum()+" and "+objVariable.getValue()+"!");
//					}
//				}
//			} else if (PropertyType.NUMLIST.equals(this.getType())) {
//				if (PropertyType.NUMLIST.equals(objVariable.getType())) {
//					retVal = new PropertyValue(null, PropertyType.NUMLIST);
//
//					ArrayList<Double> lst = new ArrayList<Double>();
//					int size = this.getNumList().size();
//					for (int i = 0; i < size; i++) {
//						lst.add(this.getNumList().get(i));
//					}
//
//					size = objVariable.getNumList().size();
//					for (int i = 0; i < size; i++) {
//						lst.add(objVariable.getNumList().get(i));
//					}
//
//					retVal.setNumList(lst);
//				} else if (PropertyType.NUM.equals(objVariable.getType())) {
//
//					retVal = new PropertyValue(null, PropertyType.NUMLIST);
//
//					ArrayList<Double> lst = new ArrayList<Double>();
//					int size = this.getNumList().size();
//					for (int i = 0; i < size; i++) {
//						lst.add(this.getNumList().get(i));
//					}
//					lst.add(objVariable.getNum());
//
//					retVal.setNumList(lst);
//				}
//			}
//		}
//		return retVal;
//	}
	
	@Override
	public int hashCode() {
		int hash = this.getName().hashCode();
		hash += this.getType().hashCode();
		hash += this.getValue().hashCode();
		return hash;
	}
}
