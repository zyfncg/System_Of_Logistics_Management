package businessLogic.businessLogicModel.businessModel;

import java.rmi.RemoteException;

import network.RMI;
import po.businessPO.ReceivablePO;
import vo.businessVO.ReceivableVO;
import dataService.businessDataService.PaymentDataService;

public class Payment {

//	private PaymentDataService paymentData=new PaymentDataImpl_Stub();
	private PaymentDataService paymentData=RMI.<PaymentDataService>getDataService("receivable");
	
	public boolean addPayentForm(ReceivableVO receivableVO) {
		// TODO Auto-generated method stub
		
		try {
			return paymentData.addPayentForm(receivableVOToPO(receivableVO));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	private ReceivablePO receivableVOToPO(ReceivableVO receivableVO){
		return new ReceivablePO(receivableVO.getDate(),receivableVO.getMoney(),receivableVO.getCourier(),receivableVO.getDeliveryid());
	}
}
