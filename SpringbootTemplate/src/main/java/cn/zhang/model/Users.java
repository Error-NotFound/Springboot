package cn.zhang.model;
/**
 * 用户 实体类
 * @author zcm
 *
 */
public class Users {
	private Integer userId;
	private String name;
	private String password;
	private String address;
	private String createTime;
	private Integer delFlag;
	private String remark1;
	private String remark2;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	public String getRemark1() {
		return remark1;
	}
	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}
	public String getRemark2() {
		return remark2;
	}
	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}
	public Users(Integer userId, String name, String password, String address, String createTime, Integer delFlag,
			String remark1, String remark2) {
		super();
		this.userId = userId;
		this.name = name;
		this.password = password;
		this.address = address;
		this.createTime = createTime;
		this.delFlag = delFlag;
		this.remark1 = remark1;
		this.remark2 = remark2;
	}
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
