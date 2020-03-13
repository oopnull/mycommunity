package my.pro.acommunity.web.spider.util;


import my.pro.acommunity.web.spider.NovelSiteEnum;
import my.pro.acommunity.web.spider.impl.chapter.DefaultChapterDetailSpider;
import my.pro.acommunity.web.spider.interfaces.IChapterDetailSpider;

public final class ChapterDetailSpiderFactory {
	private  ChapterDetailSpiderFactory() {}
	/**
	 * 通过给定的URL返回一个实现了IChapterDetailSpider的具体实现类
	 * @param url
	 * @return
	 */
	public static IChapterDetailSpider getsChapterDetailSpider(String url) {
		IChapterDetailSpider spider = null;
		NovelSiteEnum novelSiteEnum = NovelSiteEnum.getEnumByUrl(url);
		switch (novelSiteEnum) {
		case Bxwx:
			spider =new DefaultChapterDetailSpider();
			break;
		}
		return spider;
	}

}
