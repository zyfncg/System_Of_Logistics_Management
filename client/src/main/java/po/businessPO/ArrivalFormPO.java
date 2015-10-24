package po.businessPO;

import constant.CargoState;

public class ArrivalFormPO {

	private String date;
	private String transitNumber;
	private String departPlace;
	private CargoState state;
	
	public ArrivalFormPO(String date, String transitNumber, String departPlace,
			CargoState state) {
		this.date = date;
		this.transitNumber = transitNumber;
		this.departPlace = departPlace;
		this.state = state;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTransitNumber() {
		return transitNumber;
	}
	public void setTransitNumber(String transitNumber) {
		this.transitNumber = transitNumber;
	}
	public String getDepartPlace() {
		return departPlace;
	}
	public void setDepartPlace(String departPlace) {
		this.departPlace = departPlace;
	}
	public CargoState getState() {
		return state;
	}
	public void setState(CargoState state) {
		this.state = state;
	}
	
	
}