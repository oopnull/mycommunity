package my.pro.acommunity.web.spider.impl.novel;

import my.pro.acommunity.model.Novel;
import my.pro.acommunity.web.model.NovelInfo;
import my.pro.acommunity.web.spider.NovelSiteEnum;
import my.pro.acommunity.web.spider.interfaces.INovelInfoSpider;
import my.pro.acommunity.web.spider.util.NovelSpiderFactory;
import my.pro.acommunity.web.spider.util.NovelSpiderUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 笔下文学网站的书籍列表爬取
 */
public class BxwxNovelSpider extends AbstractNovelSpider {
	public BxwxNovelSpider() {}

	@Override
	public List<Novel> getsNovel(String url, Integer maxTryTimes) {
		List<Novel> novels = new ArrayList<>();
		INovelInfoSpider infoSpider = NovelSpiderFactory.getNovelInfoSpider(url);
		try {
			Elements trs = super.getsTr(url, maxTryTimes);
			for (int index = 1, size = trs.size(); index < size; index++) {
				try{
					Element tr = trs.get(index);
					Elements tds = tr.getElementsByTag("td");
					Novel novel = new Novel();
					novel.setName(tds.first().text());
					String novelUrl = tds.first().getElementsByTag("a").first().absUrl("href");
					NovelInfo novelInfo = infoSpider.getNovelInfo(novelUrl);
					novel.setNovelInfo(novelInfo);
					
					novel.setNovelUrl(novelUrl);
					novel.setLastUpdateChapter(tds.get(1).text());
					String result = tds.get(1).getElementsByTag("a").first().absUrl("href");
					novel.setLastUpdateChapterUrl(result);
					novel.setAuthor(tds.get(2).text());
					
					novel.setLastUpdateTime(NovelSpiderUtil.getDate(tds.get(4).text(), "yy-MM-dd"));//2016-10-14 yyyy-MM-dd
					novel.setStatus(NovelSpiderUtil.getNovelStatus(tds.get(5).text()));
					novel.setPlatformId(NovelSiteEnum.getEnumByUrl(url).getId());
					
					
					String firstUrl = super.crawl(result);
					Document doc = Jsoup.parse(firstUrl);
					doc.setBaseUri(url);
					Map<String, String> contexts = NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(firstUrl));
					String contentSelector = contexts.get("chapter-list-selector");
					String first = result.replace("index.html", "");
					String firstContentUrl = first+doc.select(contentSelector).get(1).absUrl("href").replace("https://www.bxwx8.la/bsort/0/", "");
					novel.setFirstcontenturl(firstContentUrl);
					System.out.println("名字:"+novel.getName());
				
					
					
					novels.add(novel);
					
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return novels;
	}
}
