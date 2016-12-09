package in.hocg.app.modules;

import in.hocg.app.plugins.redis.RedisService;
import in.hocg.def.base.module.BaseModule;
import org.nutz.img.Images;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import java.io.File;

/**
 * Created by hocgin on 16-12-9.
 * 图片处理
 */
@At("/img")
@Ok("raw")
public class ImageModule extends BaseModule {
	
	@Inject
	RedisService redisService;
	
	/**
	 * 需带后缀
	 * @param fileName
	 */
	@At("/?")
	public Object name(String fileName) {
		File image = getName(fileName);
		if (image != null) {
			return image;
		}
		return fail("图片不存在");
	}
	
	@At("/?/?/?")
	public Object rect(int width, int height, String fileName) {
		File image = getName(fileName);
		if (image != null) {
			return Images.scale(Images.read(image), width, height);
		}
		return fail("图片不存在");
	}
	
	@At("/?/?")
	public Object square(int width, String fileName) {
		File image = getName(fileName);
		if (image != null) {
			return Images.scale(Images.read(image), width, -1);
		}
		return fail("图片不存在");
	}
	
	
	/**
	 * 获取图片文件
	 * @param name
	 * @return
	 */
	private java.io.File getName(String name) {
		String directory = redisService.getUploadDirectory();
		String path = String.format("%s%s", directory, name);
		java.io.File image = new java.io.File(path);
		/**
		 * TODO
		 * - 此处图片需要读取缓存
		 * - 此处应该有张默认图片
		 */
		return image.exists() ? image : null;
	}
	
	
}
