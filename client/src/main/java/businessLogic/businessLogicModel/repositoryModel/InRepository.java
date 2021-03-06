package businessLogic.businessLogicModel.repositoryModel;

import java.rmi.RemoteException;

import businessLogic.businessLogicModel.util.CommonLogic;
import dataService.repositoryDataService.InRepositoryDataService;
import network.RMI;
import po.repositoryPO.InRepositoryPO;
import vo.repositoryVO.InRepositoryVO;

public class InRepository {
	private InRepositoryDataService inRepositoryDataService = RMI
			.<InRepositoryDataService> getDataService("inrepository");
	
	public boolean addInRepositoryFormBL(InRepositoryVO inRepositoryVO) {
		// TODO Auto-generated method stub
		InRepositoryPO inRepositoryPO = InRepositoryVOtoInRepositoryPO(inRepositoryVO);
		boolean add = false;
		try {
			boolean temp2 = inRepositoryDataService.UpdateRepositoryInfoDT(inRepositoryPO);
			if (temp2) {
				boolean temp1 = inRepositoryDataService.AddInRepositoryFormDT(inRepositoryPO);
				if (temp1 && temp2) {
					add = true;
				}				
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return add;
	}

	public boolean modifyInRepositoryFormBL(InRepositoryVO inRepositoryVO) {
		// TODO Auto-generated method stub
		InRepositoryPO inRepositoryPO = InRepositoryVOtoInRepositoryPO(inRepositoryVO);
		boolean modify = false;
		try {
			boolean temp2 = inRepositoryDataService.modifyUpdateRepositoryInfoDT(inRepositoryPO);
			if (temp2) {
				boolean temp1 = inRepositoryDataService.ModifyInRepositoryFormDT(inRepositoryPO);
				if (temp1 && temp2) {
					modify = true;
				}				
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modify;
	}

	public InRepositoryVO findInRepositoryFormBL(String InRepositoryNumber) {
		// TODO Auto-generated method stub
		InRepositoryPO inRepositoryPO = null;
		InRepositoryVO result = null;
		try {
			inRepositoryPO = inRepositoryDataService.FindInRepositoryFormDT(InRepositoryNumber);
			if (inRepositoryPO == null) {
				return null;
			} else {
				result = InRepositoryPOtoInRepositoryVO(inRepositoryPO);
				result.setVerifyResult(true);
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	private InRepositoryVO InRepositoryPOtoInRepositoryVO(InRepositoryPO inRepositoryPO) {
		return new InRepositoryVO(inRepositoryPO.getdeliveryid(), inRepositoryPO.getinrepositorydate(),
				inRepositoryPO.getarrivalid(), inRepositoryPO.getareaCode(), inRepositoryPO.getrowid(),
				inRepositoryPO.getshelfid(), inRepositoryPO.getposid(),inRepositoryPO.gettransitionid());
	}

	private InRepositoryPO InRepositoryVOtoInRepositoryPO(InRepositoryVO inRepositoryVO) {
		return new InRepositoryPO(inRepositoryVO.getdeliveryid(), inRepositoryVO.getinrepositorydate(),
				inRepositoryVO.getarrivalid(), inRepositoryVO.getareaCode(), inRepositoryVO.getrowid(),
				inRepositoryVO.getshelfid(), inRepositoryVO.getposid(),inRepositoryVO.gettransitionid());
	}

	public boolean verify(InRepositoryVO inRepositoryVO) {
		if (inRepositoryVO.getdeliveryid().equals("") ||(!inRepositoryVO.getdeliveryid().matches("\\d{10}") )) {
			inRepositoryVO.seterrorMsg("快递编号不能为空或输入错误(10位)");
			return false;
		}
		if (inRepositoryVO.getarrivalid().equals("")) {
			inRepositoryVO.seterrorMsg("目的地不能为空");
			return false;
		}
		if (inRepositoryVO.getinrepositorydate().equals("")) {
			inRepositoryVO.seterrorMsg("入库时间不可为空");
			return false;
		}
		if (!CommonLogic.isDate(inRepositoryVO.getinrepositorydate())) {
			inRepositoryVO.seterrorMsg("输入时间有误");
			return false;
		}
		// String[] aStrings =inRepositoryVO.getinrepositorydate().split("-");
		// String date = "";
		// if(aStrings[0].equals(" ")||aStrings[1].equals("
		// ")||aStrings[2].equals(" ")){
		// inRepositoryVO.seterrorMsg("入库日期不能为空");
		// return false;
		// }
		// for(int i=0;i<3;i++){
		// aStrings[i] =aStrings[i].trim();
		// if (i == 2) {
		// date += aStrings[i];
		// }
		// else {
		// date = date + aStrings[i] + "-";
		// }
		// }
		// int[] aint = new int[3];
		// for(int i=0;i<3;i++){
		// aint[i] = Integer.parseInt(aStrings[i]);
		//
		// }
		// if(aint[1]<1||aint[1]>12){
		// inRepositoryVO.seterrorMsg("月份输入错误");
		// return false;
		// }
		// if (aint[2]<1||aint[2]>31) {
		// inRepositoryVO.seterrorMsg("日期输入错误");
		// return false;
		// }
		// SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// try {
		// // 设置lenient为false.
		// // 否则SimpleDateFormat会比较宽松地验证日期，比如2007-02-29会被接受，并转换成2007-03-01
		// format.setLenient(false);
		// format.parse(date);
		// } catch (ParseException e) {
		// // e.printStackTrace();
		// // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
		// inRepositoryVO.seterrorMsg("日期输入有误");
		// return false;
		// }

		if (inRepositoryVO.getrowid().equals("")||(!CommonLogic.isNumber(inRepositoryVO.getrowid()))) {
			inRepositoryVO.seterrorMsg("排号不能为空或输入错误");
			return false;
		}
		if (inRepositoryVO.getshelfid().equals("")||(!CommonLogic.isNumber(inRepositoryVO.getshelfid()))) {
			inRepositoryVO.seterrorMsg("架号不能为空或输入错误");
			return false;
		}
		if (inRepositoryVO.getposid().equals("")||(!CommonLogic.isNumber(inRepositoryVO.getposid()))) {
			inRepositoryVO.seterrorMsg("位号不能为空或输入错误");
			return false;
		}
		return true;
	}
}
