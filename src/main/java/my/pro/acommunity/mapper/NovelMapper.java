package my.pro.acommunity.mapper;

import my.pro.acommunity.model.Novel;

import java.util.List;

public interface NovelMapper {
	/** ������������ */
	public void batchInsert(List<Novel> novels);

	/** ���Ҷ���С˵ */
	public List<Novel> findUrbanNovel();

	/** ����id����С˵ */
	public Novel findNovelInfo(String id);

	/** ����id����С˵�ĵ�һ�½����ݵ�ַ */
	public Novel findNowRead(String id);

	/** ����С˵���ֻ����߲���С˵ */
	public List<Novel> findNovelName(String kw);

	/** ��������ħ��С˵ */
	public List<Novel> findFantasyMagicNovel();

	/** ������������С˵ */
	public List<Novel> findComprehensionNovel();

	/** ���Ҷ�������5��С˵ */
	public List<Novel> findUrbanLimitNovel();

	/** ��������ħ��5��С˵ */
	public List<Novel> findFantasyMagicLimitNovel();

	/** ������������5��С˵ */
	public List<Novel> findComprehensionLimitNovel();

	/** ���ҿƻ�����С˵ */
	public List<Novel> findScienceFictionNovel();

	/** ���ҿֲ����С˵ */
	public List<Novel> findHorrorNovel();

	/** �������ξ���С˵ */
	public List<Novel> findOnlineGameNovel();

	/** ������ʷ����С˵ */
	public List<Novel> findHistoricalNovel();

	/** ������������С˵ */
	public List<Novel> findOtherNovel();
	/** ���ҿƻ�����5��С˵ */
	public List<Novel> findScienceFictionLimitNovel();
	/** ���ҿֲ����5��С˵ */
	public List<Novel> findHorrorLimitNovel();
	/** �������ξ���5��С˵ */
	public List<Novel> findOnlineGameLimitNovel();
	/** ������ʷ����5��С˵ */
	public List<Novel> findHistoricalLimitNovel();
	/** ������������5��С˵ */
	public List<Novel> findOtherLimitNovel();

    List<Novel> getAllNovels();
}