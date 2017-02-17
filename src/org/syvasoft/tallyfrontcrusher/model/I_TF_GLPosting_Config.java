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
package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for TF_GLPosting_Config
 *  @author iDempiere (generated) 
 *  @version Release 3.1
 */
@SuppressWarnings("all")
public interface I_TF_GLPosting_Config 
{

    /** TableName=TF_GLPosting_Config */
    public static final String Table_Name = "TF_GLPosting_Config";

    /** AD_Table_ID=1000177 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 1 - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(1);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name C_AcctSchema_ID */
    public static final String COLUMNNAME_C_AcctSchema_ID = "C_AcctSchema_ID";

	/** Set Accounting Schema.
	  * Rules for accounting
	  */
	public void setC_AcctSchema_ID (int C_AcctSchema_ID);

	/** Get Accounting Schema.
	  * Rules for accounting
	  */
	public int getC_AcctSchema_ID();

	public org.compiere.model.I_C_AcctSchema getC_AcctSchema() throws RuntimeException;

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name JobWork_Product_ID */
    public static final String COLUMNNAME_JobWork_Product_ID = "JobWork_Product_ID";

	/** Set Job Work	  */
	public void setJobWork_Product_ID (int JobWork_Product_ID);

	/** Get Job Work	  */
	public int getJobWork_Product_ID();

	public org.compiere.model.I_M_Product getJobWork_Product() throws RuntimeException;

    /** Column name JobworkExpenseAcct_ID */
    public static final String COLUMNNAME_JobworkExpenseAcct_ID = "JobworkExpenseAcct_ID";

	/** Set Jobwork Expense	  */
	public void setJobworkExpenseAcct_ID (int JobworkExpenseAcct_ID);

	/** Get Jobwork Expense	  */
	public int getJobworkExpenseAcct_ID();

	public org.compiere.model.I_C_ElementValue getJobworkExpenseAcct() throws RuntimeException;

    /** Column name JobworkExpenseVarianceAcct_ID */
    public static final String COLUMNNAME_JobworkExpenseVarianceAcct_ID = "JobworkExpenseVarianceAcct_ID";

	/** Set Jobwork Expense Variance	  */
	public void setJobworkExpenseVarianceAcct_ID (int JobworkExpenseVarianceAcct_ID);

	/** Get Jobwork Expense Variance	  */
	public int getJobworkExpenseVarianceAcct_ID();

	public org.compiere.model.I_C_ElementValue getJobworkExpenseVarianceAcct() throws RuntimeException;

    /** Column name JobworkPayableClearingAcct_ID */
    public static final String COLUMNNAME_JobworkPayableClearingAcct_ID = "JobworkPayableClearingAcct_ID";

	/** Set Jobwork Payable Clearing	  */
	public void setJobworkPayableClearingAcct_ID (int JobworkPayableClearingAcct_ID);

	/** Get Jobwork Payable Clearing	  */
	public int getJobworkPayableClearingAcct_ID();

	public org.compiere.model.I_C_ElementValue getJobworkPayableClearingAcct() throws RuntimeException;

    /** Column name QuarryExp_Acct */
    public static final String COLUMNNAME_QuarryExp_Acct = "QuarryExp_Acct";

	/** Set Quarry Expense	  */
	public void setQuarryExp_Acct (int QuarryExp_Acct);

	/** Get Quarry Expense	  */
	public int getQuarryExp_Acct();

	public org.compiere.model.I_C_ElementValue getQuarryExp_A() throws RuntimeException;

    /** Column name QuarryRent_Acct */
    public static final String COLUMNNAME_QuarryRent_Acct = "QuarryRent_Acct";

	/** Set Quarry Rent	  */
	public void setQuarryRent_Acct (int QuarryRent_Acct);

	/** Get Quarry Rent	  */
	public int getQuarryRent_Acct();

	public org.compiere.model.I_C_ElementValue getQuarryRent_A() throws RuntimeException;

    /** Column name SalariesAdvanceAcct_ID */
    public static final String COLUMNNAME_SalariesAdvanceAcct_ID = "SalariesAdvanceAcct_ID";

	/** Set Salary Advance	  */
	public void setSalariesAdvanceAcct_ID (int SalariesAdvanceAcct_ID);

	/** Get Salary Advance	  */
	public int getSalariesAdvanceAcct_ID();

	public org.compiere.model.I_C_ElementValue getSalariesAdvanceAcct() throws RuntimeException;

    /** Column name SalariesExpenseAcct */
    public static final String COLUMNNAME_SalariesExpenseAcct = "SalariesExpenseAcct";

	/** Set Salary Expense	  */
	public void setSalariesExpenseAcct (int SalariesExpenseAcct);

	/** Get Salary Expense	  */
	public int getSalariesExpenseAcct();

	public org.compiere.model.I_C_ElementValue getSalariesExpenseA() throws RuntimeException;

    /** Column name SalaryPayable_Acct */
    public static final String COLUMNNAME_SalaryPayable_Acct = "SalaryPayable_Acct";

	/** Set Salary Payable	  */
	public void setSalaryPayable_Acct (int SalaryPayable_Acct);

	/** Get Salary Payable	  */
	public int getSalaryPayable_Acct();

	public org.compiere.model.I_C_ElementValue getSalaryPayable_A() throws RuntimeException;

    /** Column name TF_GLPosting_Config_ID */
    public static final String COLUMNNAME_TF_GLPosting_Config_ID = "TF_GLPosting_Config_ID";

	/** Set GL Posting Configuratoin	  */
	public void setTF_GLPosting_Config_ID (int TF_GLPosting_Config_ID);

	/** Get GL Posting Configuratoin	  */
	public int getTF_GLPosting_Config_ID();

    /** Column name TF_GLPosting_Config_UU */
    public static final String COLUMNNAME_TF_GLPosting_Config_UU = "TF_GLPosting_Config_UU";

	/** Set TF_GLPosting_Config_UU	  */
	public void setTF_GLPosting_Config_UU (String TF_GLPosting_Config_UU);

	/** Get TF_GLPosting_Config_UU	  */
	public String getTF_GLPosting_Config_UU();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();

    /** Column name VehicleExp_Acct */
    public static final String COLUMNNAME_VehicleExp_Acct = "VehicleExp_Acct";

	/** Set Vehicle Expense	  */
	public void setVehicleExp_Acct (int VehicleExp_Acct);

	/** Get Vehicle Expense	  */
	public int getVehicleExp_Acct();

	public org.compiere.model.I_C_ElementValue getVehicleExp_A() throws RuntimeException;

    /** Column name VehicleRent_Acct */
    public static final String COLUMNNAME_VehicleRent_Acct = "VehicleRent_Acct";

	/** Set Vehicle Rent	  */
	public void setVehicleRent_Acct (int VehicleRent_Acct);

	/** Get Vehicle Rent	  */
	public int getVehicleRent_Acct();

	public org.compiere.model.I_C_ElementValue getVehicleRent_A() throws RuntimeException;

    /** Column name WageAdvanceAcct_ID */
    public static final String COLUMNNAME_WageAdvanceAcct_ID = "WageAdvanceAcct_ID";

	/** Set Wage Advance	  */
	public void setWageAdvanceAcct_ID (int WageAdvanceAcct_ID);

	/** Get Wage Advance	  */
	public int getWageAdvanceAcct_ID();

	public org.compiere.model.I_C_ElementValue getWageAdvanceAcct() throws RuntimeException;

    /** Column name WageExpenseAcct_ID */
    public static final String COLUMNNAME_WageExpenseAcct_ID = "WageExpenseAcct_ID";

	/** Set Wage Expense	  */
	public void setWageExpenseAcct_ID (int WageExpenseAcct_ID);

	/** Get Wage Expense	  */
	public int getWageExpenseAcct_ID();

	public org.compiere.model.I_C_ElementValue getWageExpenseAcct() throws RuntimeException;

    /** Column name WageIncentiveAcct_ID */
    public static final String COLUMNNAME_WageIncentiveAcct_ID = "WageIncentiveAcct_ID";

	/** Set Wage Incentive	  */
	public void setWageIncentiveAcct_ID (int WageIncentiveAcct_ID);

	/** Get Wage Incentive	  */
	public int getWageIncentiveAcct_ID();

	public org.compiere.model.I_C_ElementValue getWageIncentiveAcct() throws RuntimeException;

    /** Column name WagePayableAcct_ID */
    public static final String COLUMNNAME_WagePayableAcct_ID = "WagePayableAcct_ID";

	/** Set Wage Payable	  */
	public void setWagePayableAcct_ID (int WagePayableAcct_ID);

	/** Get Wage Payable	  */
	public int getWagePayableAcct_ID();

	public org.compiere.model.I_C_ElementValue getWagePayableAcct() throws RuntimeException;
}
