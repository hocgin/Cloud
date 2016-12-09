package in.hocg.app.services;

import in.hocg.app.beans.File;
import in.hocg.app.plugins.redis.RedisService;
import in.hocg.def.base.service.TableService;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.lang.Files;
import org.nutz.lang.Lang;
import org.nutz.lang.random.R;
import org.nutz.mvc.upload.TempFile;

import java.io.IOException;

/**
 * Created by hocgin on 16-12-9.
 */
public class FileService extends TableService<File> {
	
	@Inject
	RedisService redisService;
	
	/**
	 * 上传文件
	 * @param tempFile
	 * @return
	 */
	public File upload(TempFile tempFile) {
		java.io.File uploadFile = tempFile.getFile();
		String md5 = Lang.md5(uploadFile);
		File fetchFile = fetch2(md5);
		if (fetchFile == null) {
			fetchFile = new File();
			fetchFile.setMd5(md5);
			fetchFile.setSize(tempFile.getSize());
			fetchFile.setType(tempFile.getContentType());
			fetchFile.setName(tempFile.getSubmittedFileName());
			try {
				String name = R.UU16();
				moveUploadDirectory(uploadFile, name);
				fetchFile.setLocalName(name);
			} catch (IOException e) {
				e.printStackTrace();
			}
			save(fetchFile);
		}
		return fetchFile;
	}
	
	/**
	 * 获取需下载的文件对象
	 * @param file
	 * @return
	 */
	public java.io.File download(File file) {
		return new java.io.File(String.format("%s%s", _uploadDirectory(), file.getLocalName()));
	}
	
	
	public java.io.File download(String id) {
		File file = fetch(id);
		return file == null? null :download(file);
	}
	
	/**
	 * 删除文件
	 * - 移至Trash目录
	 * @param file file 对象
	 * @return
	 */
	public void trash(File file) {
		if (file != null) {
			String path = redisService.get(RedisService.Service.FILE_KEEP_PATH);
			String filePath = String.format("%s%s", path, file.getLocalName());
			java.io.File trashFile = new java.io.File(filePath);
			try {
				moveTrashDirectory(trashFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			_delete(file);
		}
	}
	public void trash(String id) {
		File fetch = fetch(id);
		trash(fetch);
	}
	
	
	/**
	 * md5 搜搜文件
	 * @param md5
	 * @return
	 */
	public File fetch2(String md5) {
		return fetch(all().and("md5", "=", md5));
	}
	
	/**
	 * 移动文件至上传目录
	 * @param uploadFile 待移动文件
	 * @param name 重命名文件
	 * @return
	 * @throws IOException
	 */
	private boolean moveUploadDirectory(java.io.File uploadFile, String name) throws IOException {
		java.io.File target = new java.io.File(String.format("%s%s", _uploadDirectory(), name));
		return  Files.move(uploadFile, target);
	}
	
	/**
	 * 获取文件上传目录
	 * @return
	 */
	private String _uploadDirectory() {
		return redisService.get(RedisService.Service.FILE_KEEP_PATH);
	}
	
	/**
	 * 移动文件至删除目录
	 * @param trashFile
	 * @return
	 * @throws IOException
	 */
	private boolean moveTrashDirectory(java.io.File trashFile) throws IOException {
		// 原文件名-删除时间戳
		String name = trashFile.getName() + "-" + System.currentTimeMillis();
		java.io.File target = new java.io.File(String.format("%s/%s", _trashDirectory(), name));
		return  Files.move(trashFile, target);
	}
	
	/**
	 * 获取文件回收站目录
	 * @return
	 */
	private String _trashDirectory() {
		return redisService.get(RedisService.Service.FILE_TRASH_PATH);
	}
	
}
