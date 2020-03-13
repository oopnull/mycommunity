package my.pro.acommunity.web.spider.interfaces;


import my.pro.acommunity.web.model.ChapterDetail;

public interface IChapterDetailSpider {
	/**
	 * 给我一个url，我就给你一个对应网站的章节内容实体
	 * @param url
	 * @return
	 */
	public ChapterDetail getChapterDetail(String url);
}
