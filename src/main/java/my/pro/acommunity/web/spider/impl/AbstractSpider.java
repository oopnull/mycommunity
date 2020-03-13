package my.pro.acommunity.web.spider.impl;

import my.pro.acommunity.web.spider.NovelSiteEnum;
import my.pro.acommunity.web.spider.util.NovelSpiderHttpGet;
import my.pro.acommunity.web.spider.util.NovelSpiderUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public abstract class AbstractSpider {
	/**
	 * 抓取指定小说网站的内容
	 * @param url
	 * @return
	 * @throws Exception
	 */
	protected String crawl(String url) throws Exception {


		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			 CloseableHttpResponse httpResponse = httpClient.execute(new NovelSpiderHttpGet(url))
		)
		{
			String result = EntityUtils.toString(httpResponse.getEntity(), NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url)).get("charset"));
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


}
