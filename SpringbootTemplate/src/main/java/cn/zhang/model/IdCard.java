package cn.zhang.model;

public class IdCard {
	private String id;
	private String idNo;
	private String areaName;
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public IdCard(String idNo, String areaName) {
		super();
		this.idNo = idNo;
		this.areaName = areaName;
	}
	
	public IdCard(String id, String idNo, String areaName) {
		super();
		this.id = id;
		this.idNo = idNo;
		this.areaName = areaName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "IdCard [" + (id != null ? "id=" + id + ", " : "") + (idNo != null ? "idNo=" + idNo + ", " : "")
				+ (areaName != null ? "areaName=" + areaName : "") + "]";
	}
	public IdCard() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
