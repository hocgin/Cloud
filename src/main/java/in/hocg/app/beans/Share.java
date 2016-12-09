package in.hocg.app.beans;

import in.hocg.def.base.bean.BaseTable;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;

/**
 * Created by hocgin on 16-12-9.
 */
@Table("t_sky_drive_shares")
public class Share extends BaseTable {
	
	@Column("sky_drive_id")
	@Comment("网盘文件ID")
	private String skyDriveId;
	
	@Column("access_password")
	@Comment("访问密码")
	private String accessPassword;
	
	@Column("limit_at")
	@Comment("截止日期")
	private Date limitAt;
	
	// --
	@One(target = SkyDrive.class, field = "skyDriveId")
	private SkyDrive skyDrive;
	
	public String getSkyDriveId() {
		return skyDriveId;
	}
	
	public void setSkyDriveId(String skyDriveId) {
		this.skyDriveId = skyDriveId;
	}
	
	public String getAccessPassword() {
		return accessPassword;
	}
	
	public void setAccessPassword(String accessPassword) {
		this.accessPassword = accessPassword;
	}
	
	public Date getLimitAt() {
		return limitAt;
	}
	
	public void setLimitAt(Date limitAt) {
		this.limitAt = limitAt;
	}
	
	public SkyDrive getSkyDrive() {
		return skyDrive;
	}
	
	public void setSkyDrive(SkyDrive skyDrive) {
		this.skyDrive = skyDrive;
	}
}
