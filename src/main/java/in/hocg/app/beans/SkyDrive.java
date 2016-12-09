package in.hocg.app.beans;

import in.hocg.app.plugins.shiro.bean.User;
import in.hocg.def.base.bean.BaseTable;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Table;

/**
 * Created by hocgin on 16-12-9.
 */
@Table("t_sky_drives")
public class SkyDrive extends BaseTable {
	
	@Column("type")
	@Comment("文件类型, [目录/ ..]")
	private String type;
	
	@Column("parent_directory_id")
	@Comment("父目录ID, 根目录为 null")
	private String parentDirectoryId;
	
	@Column("file_id")
	@Comment("文件表id, 如果是目录则为null")
	private String fileId;
	
	@Column("owner_id")
	@Comment("拥有者, User id")
	private String ownerId;
	
	@Column("access_password")
	@Comment("访问密码")
	private String accessPassword;
	
	// -- 关系
	@One(target = SkyDrive.class, field = "parentDirectoryId")
	private SkyDrive parentDirectory;
	
	@One(target = File.class, field = "fileId")
	private File file;
	
	@One(target = User.class, field = "ownerId")
	private User owner;
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getParentDirectoryId() {
		return parentDirectoryId;
	}
	
	public void setParentDirectoryId(String parentDirectoryId) {
		this.parentDirectoryId = parentDirectoryId;
	}
	
	public String getFileId() {
		return fileId;
	}
	
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	
	public String getOwnerId() {
		return ownerId;
	}
	
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	
	public String getAccessPassword() {
		return accessPassword;
	}
	
	public void setAccessPassword(String accessPassword) {
		this.accessPassword = accessPassword;
	}
	
	public SkyDrive getParentDirectory() {
		return parentDirectory;
	}
	
	public void setParentDirectory(SkyDrive parentDirectory) {
		this.parentDirectory = parentDirectory;
	}
	
	public File getFile() {
		return file;
	}
	
	public void setFile(File file) {
		this.file = file;
	}
	
	public User getOwner() {
		return owner;
	}
	
	public void setOwner(User owner) {
		this.owner = owner;
	}
}
