package businessLogicImpl.transitionBLImpl;

import java.util.List;

import businessLogicService.transitionBLService.TransferringBLService;
import vo.repositoryVO.RepositoryVO;
import vo.transitionVO.TransferringVO;

public class TransferringBLImpl implements TransferringBLService{

	public TransferringVO findTransferringFormBL(String transferringNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean addTransferringFormBL(TransferringVO transferringVO) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean modifyTransferringFormBL(TransferringVO transferringVO) {
		// TODO Auto-generated method stub
		return false;
	}

	public double tranferringFare(String CityFrom, String CityTo) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<RepositoryVO> getRepositoryInfo() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * description:取得当天的中转单列表信息（实际取得中转单列表的BL）
	 * @param date ：当天的日期
	 * @return List<TransferringVO>, 返回中转单列表信息的值对象，具体参照TransferringVO的定义
	 * */
	public List<TransferringVO> GetTansferringInfoBL(String date){
		return null;
	}

}
