package stub.dataImpl_stub.managerDataImpl_stub;

import dataService.managerDataService.StaffManagementDataService;
import po.managerPO.StaffPO;

public class StaffManagementDataImpl_Stub implements StaffManagementDataService {
	public boolean addStaff(StaffPO staffPO) {
		return false;
	}
	
	public StaffPO findStaff(String id) {
//		return new StaffPO("小明", "B1235468", "快递员", "男",
//			"1987-02-15", "按提成");
		return null;
	}
	
	public boolean deleteStaff(String id) {
		return false;
	}
	
	public boolean modifyStaff(StaffPO staffPO) {
		return false;
	}
}
