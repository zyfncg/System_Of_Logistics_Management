package stub.businessLogicImpl_stub.repositoryBLImpl_stub;

import java.util.ArrayList;
import java.util.List;

import businessLogicService.repositoryBLService.ManageRepositoryBLService;
import constant.AreaCodeType;
import dataService.repositoryDataService.ManageRepositoryDataService;
import po.repositoryPO.DeliveryInfoPO;
import po.repositoryPO.RepositoryInfoPO;
import po.repositoryPO.RepositoryPO;
import stub.dataImpl_stub.repositoryDataImpl_stub.ManageRepositoryDataImpl_Stub;
import vo.repositoryVO.DeliveryInfoVO;
import vo.repositoryVO.RepositoryInfoVO;
import vo.repositoryVO.RepositoryVO;

public class ManageRepositoryBLImpl_Stub implements ManageRepositoryBLService{
	private ManageRepositoryDataService manageRepositoryData =new ManageRepositoryDataImpl_Stub();
	private List<RepositoryVO> repositoryVOs;
	private List<RepositoryInfoVO> repositoryVOs2;
	private List<DeliveryInfoVO> deliveryInfoVOs;

	public List<RepositoryInfoVO> SeeRepositoryBL(String time) {
		// TODO Auto-generated method stub
		List<RepositoryInfoPO> repositoryPOs = manageRepositoryData.SeeRepositoryDT(time);
		repositoryVOs2 = new ArrayList<RepositoryInfoVO>();
		for(int i=0;i<(repositoryPOs.size());i++){
			repositoryVOs2.add(this.RepositoryInfoPOtoRepositoryInfoVO(repositoryPOs.get(i)));
		}
		return repositoryVOs2;
	}

	public List<DeliveryInfoVO> CheckRepositoryBL() {
		// TODO Auto-generated method stub
		List<DeliveryInfoPO> deliveryInfoPOs = manageRepositoryData.CheckRepositoryDT();
		deliveryInfoVOs = new ArrayList<DeliveryInfoVO>();
		for(int i=0;i<(deliveryInfoPOs.size());i++){
			deliveryInfoVOs.add(this.DeliveryInfoPOtoDeliveryInfoVO(deliveryInfoPOs.get(i)));
		}
		return deliveryInfoVOs;
	}

	public boolean SetWarnValueBL(AreaCodeType ID, int allNum, double WarnNum) {
		// TODO Auto-generated method stub
		return manageRepositoryData.UpdataRepositoryWarnDT(allNum, WarnNum, ID);
	}

	public double GetWranNumBL(AreaCodeType ID) {
		// TODO Auto-generated method stub
		return manageRepositoryData.GetWarnNumDT(ID);
	}

	public int GetRepositoryNumBL(AreaCodeType ID) {
		// TODO Auto-generated method stub
		return manageRepositoryData.GetRepositoryNumDT(ID);
	}

	public int GetRepositoryExistBL(AreaCodeType ID) {
		// TODO Auto-generated method stub
		return manageRepositoryData.GetRepositoryExistDT(ID);
	}

	public List<RepositoryVO> GetRepositoryInfoBL() {
		// TODO Auto-generated method stub
		List<RepositoryPO> repositoryPOs = manageRepositoryData.GetRepositoryInfoDT();
		repositoryVOs = new ArrayList<RepositoryVO>();
		for(int i=0;i<(repositoryPOs.size());i++){
			RepositoryVO repositoryvo=this.RepositoryPOtoRepositoryVO(repositoryPOs.get(i));
			repositoryVOs.add(repositoryvo);
		}
		return repositoryVOs;
	}
	private RepositoryVO RepositoryPOtoRepositoryVO(RepositoryPO repositoryPO){
		return new RepositoryVO(repositoryPO.getdeliveryid(), repositoryPO.getinrepositorydate(),
				repositoryPO.getarrivalid(), repositoryPO.getareaCode(), 
				repositoryPO.getrowid(), repositoryPO.getshelfid(), repositoryPO.getposid());
	}
	private RepositoryInfoVO RepositoryInfoPOtoRepositoryInfoVO(RepositoryInfoPO repositoryInfoPO){
		return new RepositoryInfoVO(repositoryInfoPO.getdeliveryid(),repositoryInfoPO.getareaCode(), 
				repositoryInfoPO.getrowid(), repositoryInfoPO.getshelfid(), 
				repositoryInfoPO.getposid(), repositoryInfoPO.getbeinrepository());
	}
	private DeliveryInfoVO DeliveryInfoPOtoDeliveryInfoVO(DeliveryInfoPO deliveryInfoPO){
		return new DeliveryInfoVO(deliveryInfoPO.getdeliveryid(), deliveryInfoPO.getinrepositorydate(), 
				deliveryInfoPO.getarrivalid(), deliveryInfoPO.getareaCode(), 
				deliveryInfoPO.getrowid(), deliveryInfoPO.getshelfid(), deliveryInfoPO.getposid());
	}

}