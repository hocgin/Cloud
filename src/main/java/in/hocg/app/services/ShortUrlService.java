package in.hocg.app.services;

import in.hocg.app.beans.ShortUrl;
import in.hocg.def.base.service.SoftDeletedService;
import in.hocg.utils.ShortURL;

/**
 * (๑`灬´๑)
 * Created by hocgin on 十一月28  028.
 */
public class ShortUrlService extends SoftDeletedService<ShortUrl> {
	
	/**
	 * 生成并记录短链
	 * @param url
	 * @return
	 */
	public String generate(String url) {
		ShortUrl shortUrl = fetch(withTrashed().where("url", "=", url));
		if (shortUrl == null) {
			shortUrl = new ShortUrl();
			shortUrl.setUrl(url);
			shortUrl.setCode(ShortURL.encode(count()));
		}
		save(shortUrl);
		return shortUrl.getCode();
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
