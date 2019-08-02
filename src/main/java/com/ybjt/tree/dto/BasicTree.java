package com.ybjt.tree.dto;

public class BasicTree {
	private String id;
	
	private String pId;
	
	private Integer flagUse;
	
	private String name;
	
	private Boolean checked;//节点是否被选中
	
	private Boolean nocheck;//节点是否有选项框
	
	private Boolean open;//节点是否展开
	
	private String code;
	
	private String file;

	private String icon;//树形菜单的图标
 
	private Integer rank;
	private String entityId;
	
	private Integer level; //树形菜单的级别
	
	private String url; //树形菜单的级别
 
	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPId() {
		return pId;
	}

	public void setPId(String id) {
		pId = id;
	}

	public Integer getFlagUse() {
		return flagUse;
	}

	public void setFlagUse(Integer flagUse) {
		this.flagUse = flagUse;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getRank() {
		return rank;
	}
 
	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Boolean getNocheck() {
		return nocheck;
	}

	public void setNocheck(Boolean nocheck) {
		this.nocheck = nocheck;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
