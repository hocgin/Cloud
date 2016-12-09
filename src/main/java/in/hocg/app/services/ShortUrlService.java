package in.hocg.app.services;

import in.hocg.app.beans.ShortUrl;
import in.hocg.app.plugins.redis.RedisService;
import in.hocg.def.base.service.SoftDeletedService;
import in.hocg.utils.ShortURL;
import org.nutz.ioc.loader.annotation.Inject;

/**
 * (๑`灬´๑)
 * Created by hocgin on 十一月28  028.
 */
public class ShortUrlService extends SoftDeletedService<ShortUrl> {
	
	@Inject
	protected RedisService redisService;
	/**
	 * 生成并记录短链
	 * @param url
	 * @return url
	 */
	public String generate(String url) {
		ShortUrl shortUrl = fetch(withTrashed().where("url", "=", url));
		if (shortUrl == null) {
			shortUrl = new ShortUrl();
			shortUrl.setUrl(url);
			shortUrl.setCode(ShortURL.encode(count()+1));
		}
		save(shortUrl);
		return String.format("%s%s", redisService.get(RedisService.Service.SHORT_URL_DOMAIN), shortUrl.getCode());
	}
	
	/**
	 * 解析为url
	 * - 不能解析出被拉黑的
	 * @param code
	 * @return
	 */
	public String analysis(String code) {
		ShortUrl shortUrl = fetch(all().where("code", "=", code));
		return shortUrl == null ? null : shortUrl.getUrl();
	}
}
