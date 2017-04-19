package cn.zhang.model;
/**
 * 出入境关系表
 * @author zcm
 * 2017年4月7日下午4:33:07
 */
public class AbordRelationship {
	private String idNo;
	private String importantIdNo;
	private String relatedPeopleName;
	private String relationship;
	private String nationality;
	private String nation;
	private String gender;
	
	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getImportantIdNo() {
		return importantIdNo;
	}

	public void setImportantIdNo(String importantIdNo) {
		this.importantIdNo = importantIdNo;
	}

	public String getRelatedPeopleName() {
		return relatedPeopleName;
	}

	public void setRelatedPeopleName(String relatedPeopleName) {
		this.relatedPeopleName = relatedPeopleName;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public AbordRelationship(String idNo, String importantIdNo, String relatedPeopleName, String relationship,
			String nationality, String nation, String gender) {
		super();
		this.idNo = idNo;
		this.importantIdNo = importantIdNo;
		this.relatedPeopleName = relatedPeopleName;
		this.relationship = relationship;
		this.nationality = nationality;
		this.nation = nation;
		this.gender = gender;
	}

	public AbordRelationship() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "AbordRelationship [" + (idNo != null ? "idNo=" + idNo + ", " : "")
				+ (importantIdNo != null ? "importantIdNo=" + importantIdNo + ", " : "")
				+ (relatedPeopleName != null ? "relatedPeopleName=" + relatedPeopleName + ", " : "")
				+ (relationship != null ? "relationship=" + relationship + ", " : "")
				+ (nationality != null ? "nationality=" + nationality + ", " : "")
				+ (nation != null ? "nation=" + nation + ", " : "") + (gender != null ? "gender=" + gender : "") + "]";
	}
	
	
	
}
