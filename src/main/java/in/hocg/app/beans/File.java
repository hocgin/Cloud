package in.hocg.app.beans;

import in.hocg.def.base.bean.BaseTable;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Table;

/**
 * Created by hocgin on 16-12-9.
 */
@Table("t_files")
public class File extends BaseTable {
	
	@Column("md5")
	@Comment("MD5, 文件标识;唯一;")
	private String md5;
	
	@Column("size")
	@Comment("文件大小, KB")
	private Long size;
	
	@Column("name")
	@Comment("原文件名")
	private String name;
	
	@Column("local_name")
	@Comment("服务器文件名")
	private String localName;
	
	@Column("type")
	@Comment("文件类型")
	private String type;
	
	public String getMd5() {
		return md5;
	}
	
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	
	public Long getSize() {
		return size;
	}
	
	public void setSize(Long size) {
		this.size = size;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLocalName() {
		return localName;
	}
	
	public void setLocalName(String localName) {
		this.localName = localName;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
}
