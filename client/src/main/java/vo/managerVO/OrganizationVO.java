package vo.managerVO;

/**
 * 记录了机构的信息
 * @author 张仁知
 */
public class OrganizationVO {
	/**
	 * 机构类型
	 */
	private String type;
	/**
	 * 机构编号
	 */
	private String id;
	/**
	 * 机构名称
	 */
	private String name;
	
	private boolean isApproved;
	
	private boolean isPassed;
	
	public OrganizationVO(String type, String id, String name,
			boolean isApproved, boolean isPassed) {
		super();
		this.type = type;
		this.id = id;
		this.name = name;
		this.isApproved = isApproved;
		this.isPassed = isPassed;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	public boolean isPassed() {
		return isPassed;
	}

	public void setPassed(boolean isPassed) {
		this.isPassed = isPassed;
	}
	
}
