package cn.zhang.model;

import java.util.Arrays;

public class Image {
	private Integer id;
	private String name;
	private String src;
	private byte []byteContent;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public byte[] getByteContent() {
		return byteContent;
	}
	public void setByteContent(byte[] byteContent) {
		this.byteContent = byteContent;
	}
	public Image(Integer id, String name, String src, byte[] byteContent) {
		super();
		this.id = id;
		this.name = name;
		this.src = src;
		this.byteContent = byteContent;
	}
	public Image(String name, String src, byte[] byteContent) {
		super();
		this.name = name;
		this.src = src;
		this.byteContent = byteContent;
	}
	
	public Image() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Image [" + (id != null ? "id=" + id + ", " : "") + (name != null ? "name=" + name + ", " : "")
				+ (src != null ? "src=" + src + ", " : "")
				+ (byteContent != null ? "byteContent=" + Arrays.toString(byteContent) : "") + "]";
	}
	
	
}
