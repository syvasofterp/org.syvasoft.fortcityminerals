package org.syvasoft.tallyfrontcrusher.process;

import org.compiere.process.SvrProcess;
import org.syvasoft.tallyfrontcrusher.model.MSubcontractMaterialMovement;

public class CreateSubcontractRawMaterialMovement extends SvrProcess {

	@Override
	protected void prepare() {
		// TODO Auto-generated method stub

	}

	@Override
	protected String doIt() throws Exception {
		MSubcontractMaterialMovement.createRawmaterialMovementsFromWeighment(get_TrxName());		
		return null;
	}

}
