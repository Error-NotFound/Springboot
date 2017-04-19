package cn.zhang.model;

public class SampleTemp {
	private String sampleId;
	private String content;
	public String getSampleId() {
		return sampleId;
	}
	public void setSampleId(String sampleId) {
		this.sampleId = sampleId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public SampleTemp() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SampleTemp(String sampleId, String content) {
		super();
		this.sampleId = sampleId;
		this.content = content;
	}
	
}
