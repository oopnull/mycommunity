package my.pro.acommunity.web.spider.util;


import my.pro.acommunity.web.spider.NovelSiteEnum;
import my.pro.acommunity.web.spider.impl.chapter.BxwxChapterSpider;
import my.pro.acommunity.web.spider.interfaces.IChapterSpider;

public final class ChapterSpiderFactory {
	private ChapterSpiderFactory() {}
	/**
	 * 通过给定的URL返回一个实现了IChapterSpider接口的实现类
	 * @param url
	 * @return
	 */
	public static IChapterSpider getChapterSpider(String url) {
		NovelSiteEnum novelSiteEnum = NovelSiteEnum.getEnumByUrl(url);
		IChapterSpider chapterSpider = null;
		switch (novelSiteEnum) {
		case Bxwx:
			chapterSpider =new BxwxChapterSpider();
			break;
		/*case KSW:
		case WDWX:
		case QB:
		case SHU:
			chapterSpider =new DefaultChapterSpider();
			break;*/
		}
		return chapterSpider;
	}
}
