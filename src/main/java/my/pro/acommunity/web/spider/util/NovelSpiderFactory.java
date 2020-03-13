package my.pro.acommunity.web.spider.util;

import my.pro.acommunity.web.spider.NovelSiteEnum;
import my.pro.acommunity.web.spider.impl.chapter.BxwxChapterSpider;
import my.pro.acommunity.web.spider.impl.chapter.DefaultChapterDetailSpider;
import my.pro.acommunity.web.spider.impl.novel.BxwxNovelInfoSpider;
import my.pro.acommunity.web.spider.impl.novel.BxwxNovelSpider;
import my.pro.acommunity.web.spider.interfaces.IChapterDetailSpider;
import my.pro.acommunity.web.spider.interfaces.IChapterSpider;
import my.pro.acommunity.web.spider.interfaces.INovelInfoSpider;
import my.pro.acommunity.web.spider.interfaces.INovelSpider;

/**
 * 生产书籍列表的实现类
 */
public final class NovelSpiderFactory {
	private NovelSpiderFactory() {}
	public static BxwxNovelSpider bxwxNovelSpider;
	public static BxwxNovelInfoSpider bxwxNovelInfoSpider;
	public static BxwxChapterSpider bxwxChapterSpider;
	public static DefaultChapterDetailSpider defaultChapterDetailSpider;
	
	public static INovelSpider getNovelSpider(String url) {
		NovelSiteEnum novelSiteEnum = NovelSiteEnum.getEnumByUrl(url);
		switch (novelSiteEnum) {
		case Bxwx : return bxwxNovelSpider==null?new BxwxNovelSpider():bxwxNovelSpider;
		default : throw new RuntimeException(url + "暂时不被支持");
		}
	}
	public static INovelInfoSpider getNovelInfoSpider(String url) {
		NovelSiteEnum novelSiteEnum = NovelSiteEnum.getEnumByUrl(url);
		switch (novelSiteEnum) {
		case Bxwx : return bxwxNovelInfoSpider==null?new BxwxNovelInfoSpider():bxwxNovelInfoSpider;
		default : throw new RuntimeException(url + "暂时不被支持");
		}
	}
	public static IChapterSpider getChapterSpider(String url) {
		NovelSiteEnum novelSiteEnum = NovelSiteEnum.getEnumByUrl(url);
		switch (novelSiteEnum) {
		case Bxwx : return bxwxChapterSpider==null?new BxwxChapterSpider():bxwxChapterSpider;
		default : throw new RuntimeException(url + "暂时不被支持");
		}
	}
	public static IChapterDetailSpider getChapterDetailSpider(String url) {
		NovelSiteEnum novelSiteEnum = NovelSiteEnum.getEnumByUrl(url);
		switch (novelSiteEnum) {
		case Bxwx : return defaultChapterDetailSpider==null?new DefaultChapterDetailSpider():defaultChapterDetailSpider;
		default : throw new RuntimeException(url + "暂时不被支持");
		}
	}
}
