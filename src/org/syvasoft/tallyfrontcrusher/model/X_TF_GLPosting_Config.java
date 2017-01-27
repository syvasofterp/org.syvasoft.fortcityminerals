/******************************************************************************
 * Product: iDempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2012 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for TF_GLPosting_Config
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_TF_GLPosting_Config extends PO implements I_TF_GLPosting_Config, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170127L;

    /** Standard Constructor */
    public X_TF_GLPosting_Config (Properties ctx, int TF_GLPosting_Config_ID, String trxName)
    {
      super (ctx, TF_GLPosting_Config_ID, trxName);
      /** if (TF_GLPosting_Config_ID == 0)
        {
			setC_AcctSchema_ID (0);
			setJobworkExpenseAcct_ID (0);
			setJobworkExpenseVarianceAcct_ID (0);
			setJobworkPayableClearingAcct_ID (0);
			setQuarryExp_Acct (0);
			setQuarryRent_Acct (0);
			setTF_GLPosting_Config_ID (0);
			setVehicleExp_Acct (0);
			setVehicleRent_Acct (0);
        } */
    }

    /** Load Constructor */
    public X_TF_GLPosting_Config (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 1 - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_TF_GLPosting_Config[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_C_AcctSchema getC_AcctSchema() throws RuntimeException
    {
		return (org.compiere.model.I_C_AcctSchema)MTable.get(getCtx(), org.compiere.model.I_C_AcctSchema.Table_Name)
			.getPO(getC_AcctSchema_ID(), get_TrxName());	}

	/** Set Accounting Schema.
		@param C_AcctSchema_ID 
		Rules for accounting
	  */
	public void setC_AcctSchema_ID (int C_AcctSchema_ID)
	{
		if (C_AcctSchema_ID < 1) 
			set_Value (COLUMNNAME_C_AcctSchema_ID, null);
		else 
			set_Value (COLUMNNAME_C_AcctSchema_ID, Integer.valueOf(C_AcctSchema_ID));
	}

	/** Get Accounting Schema.
		@return Rules for accounting
	  */
	public int getC_AcctSchema_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_AcctSchema_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Product getJobWork_Product() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getJobWork_Product_ID(), get_TrxName());	}

	/** Set Job Work.
		@param JobWork_Product_ID Job Work	  */
	public void setJobWork_Product_ID (int JobWork_Product_ID)
	{
		if (JobWork_Product_ID < 1) 
			set_Value (COLUMNNAME_JobWork_Product_ID, null);
		else 
			set_Value (COLUMNNAME_JobWork_Product_ID, Integer.valueOf(JobWork_Product_ID));
	}

	/** Get Job Work.
		@return Job Work	  */
	public int getJobWork_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_JobWork_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ElementValue getJobworkExpenseAcct() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getJobworkExpenseAcct_ID(), get_TrxName());	}

	/** Set Jobwork Expense.
		@param JobworkExpenseAcct_ID Jobwork Expense	  */
	public void setJobworkExpenseAcct_ID (int JobworkExpenseAcct_ID)
	{
		if (JobworkExpenseAcct_ID < 1) 
			set_Value (COLUMNNAME_JobworkExpenseAcct_ID, null);
		else 
			set_Value (COLUMNNAME_JobworkExpenseAcct_ID, Integer.valueOf(JobworkExpenseAcct_ID));
	}

	/** Get Jobwork Expense.
		@return Jobwork Expense	  */
	public int getJobworkExpenseAcct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_JobworkExpenseAcct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ElementValue getJobworkExpenseVarianceAcct() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getJobworkExpenseVarianceAcct_ID(), get_TrxName());	}

	/** Set Jobwork Expense Variance.
		@param JobworkExpenseVarianceAcct_ID Jobwork Expense Variance	  */
	public void setJobworkExpenseVarianceAcct_ID (int JobworkExpenseVarianceAcct_ID)
	{
		if (JobworkExpenseVarianceAcct_ID < 1) 
			set_Value (COLUMNNAME_JobworkExpenseVarianceAcct_ID, null);
		else 
			set_Value (COLUMNNAME_JobworkExpenseVarianceAcct_ID, Integer.valueOf(JobworkExpenseVarianceAcct_ID));
	}

	/** Get Jobwork Expense Variance.
		@return Jobwork Expense Variance	  */
	public int getJobworkExpenseVarianceAcct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_JobworkExpenseVarianceAcct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ElementValue getJobworkPayableClearingAcct() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getJobworkPayableClearingAcct_ID(), get_TrxName());	}

	/** Set Jobwork Payable Clearing.
		@param JobworkPayableClearingAcct_ID Jobwork Payable Clearing	  */
	public void setJobworkPayableClearingAcct_ID (int JobworkPayableClearingAcct_ID)
	{
		if (JobworkPayableClearingAcct_ID < 1) 
			set_Value (COLUMNNAME_JobworkPayableClearingAcct_ID, null);
		else 
			set_Value (COLUMNNAME_JobworkPayableClearingAcct_ID, Integer.valueOf(JobworkPayableClearingAcct_ID));
	}

	/** Get Jobwork Payable Clearing.
		@return Jobwork Payable Clearing	  */
	public int getJobworkPayableClearingAcct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_JobworkPayableClearingAcct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ElementValue getQuarryExp_A() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getQuarryExp_Acct(), get_TrxName());	}

	/** Set Quarry Expense.
		@param QuarryExp_Acct Quarry Expense	  */
	public void setQuarryExp_Acct (int QuarryExp_Acct)
	{
		set_Value (COLUMNNAME_QuarryExp_Acct, Integer.valueOf(QuarryExp_Acct));
	}

	/** Get Quarry Expense.
		@return Quarry Expense	  */
	public int getQuarryExp_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_QuarryExp_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ElementValue getQuarryRent_A() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getQuarryRent_Acct(), get_TrxName());	}

	/** Set Quarry Rent.
		@param QuarryRent_Acct Quarry Rent	  */
	public void setQuarryRent_Acct (int QuarryRent_Acct)
	{
		set_Value (COLUMNNAME_QuarryRent_Acct, Integer.valueOf(QuarryRent_Acct));
	}

	/** Get Quarry Rent.
		@return Quarry Rent	  */
	public int getQuarryRent_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_QuarryRent_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ElementValue getSalariesExpenseA() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getSalariesExpenseAcct(), get_TrxName());	}

	/** Set Salary Expense.
		@param SalariesExpenseAcct Salary Expense	  */
	public void setSalariesExpenseAcct (int SalariesExpenseAcct)
	{
		set_Value (COLUMNNAME_SalariesExpenseAcct, Integer.valueOf(SalariesExpenseAcct));
	}

	/** Get Salary Expense.
		@return Salary Expense	  */
	public int getSalariesExpenseAcct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SalariesExpenseAcct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ElementValue getSalaryPayable_A() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getSalaryPayable_Acct(), get_TrxName());	}

	/** Set Salary Payable.
		@param SalaryPayable_Acct Salary Payable	  */
	public void setSalaryPayable_Acct (int SalaryPayable_Acct)
	{
		set_Value (COLUMNNAME_SalaryPayable_Acct, Integer.valueOf(SalaryPayable_Acct));
	}

	/** Get Salary Payable.
		@return Salary Payable	  */
	public int getSalaryPayable_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SalaryPayable_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set GL Posting Configuratoin.
		@param TF_GLPosting_Config_ID GL Posting Configuratoin	  */
	public void setTF_GLPosting_Config_ID (int TF_GLPosting_Config_ID)
	{
		if (TF_GLPosting_Config_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TF_GLPosting_Config_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_GLPosting_Config_ID, Integer.valueOf(TF_GLPosting_Config_ID));
	}

	/** Get GL Posting Configuratoin.
		@return GL Posting Configuratoin	  */
	public int getTF_GLPosting_Config_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_GLPosting_Config_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set TF_GLPosting_Config_UU.
		@param TF_GLPosting_Config_UU TF_GLPosting_Config_UU	  */
	public void setTF_GLPosting_Config_UU (String TF_GLPosting_Config_UU)
	{
		set_ValueNoCheck (COLUMNNAME_TF_GLPosting_Config_UU, TF_GLPosting_Config_UU);
	}

	/** Get TF_GLPosting_Config_UU.
		@return TF_GLPosting_Config_UU	  */
	public String getTF_GLPosting_Config_UU () 
	{
		return (String)get_Value(COLUMNNAME_TF_GLPosting_Config_UU);
	}

	public org.compiere.model.I_C_ElementValue getVehicleExp_A() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getVehicleExp_Acct(), get_TrxName());	}

	/** Set Vehicle Expense.
		@param VehicleExp_Acct Vehicle Expense	  */
	public void setVehicleExp_Acct (int VehicleExp_Acct)
	{
		set_Value (COLUMNNAME_VehicleExp_Acct, Integer.valueOf(VehicleExp_Acct));
	}

	/** Get Vehicle Expense.
		@return Vehicle Expense	  */
	public int getVehicleExp_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_VehicleExp_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ElementValue getVehicleRent_A() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getVehicleRent_Acct(), get_TrxName());	}

	/** Set Vehicle Rent.
		@param VehicleRent_Acct Vehicle Rent	  */
	public void setVehicleRent_Acct (int VehicleRent_Acct)
	{
		set_Value (COLUMNNAME_VehicleRent_Acct, Integer.valueOf(VehicleRent_Acct));
	}

	/** Get Vehicle Rent.
		@return Vehicle Rent	  */
	public int getVehicleRent_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_VehicleRent_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}