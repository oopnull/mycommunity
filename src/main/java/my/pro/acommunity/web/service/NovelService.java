package my.pro.acommunity.web.service;


import my.pro.acommunity.dto.PaginationDTO;
import my.pro.acommunity.model.Novel;

import java.util.List;

public interface NovelService {
	/**
	 * ����id����С˵
	 * @param id
	 * @return
	 */
	public Novel findNovelInfo(String id);
	
	/**
	 * ����id����С˵�ĵ�һ�½����ݵ�ַ
	 * @param id
	 * @return
	 */
	public Novel findNowRead(String id);
	
	/**
	 * ����С˵���ֻ������߲���С˵
	 * @param kw
	 * @return
	 */
	public List<Novel> findNovelName(String kw);
	/**
	 * ���Ҷ���С˵
	 * @return
	 */
	public List<Novel> findUrbanNovel();
	/**
	 * ��������ħ��С˵
	 * @return
	 */
	public List<Novel> findFantasyMagicNovel();
	/**
	 * ��������ħ��С˵
	 * @return
	 */
	public List<Novel> findComprehensionNovel();
	/**
	 * ���Ҷ�������5��С˵
	 * @return
	 */
	public List<Novel> findUrbanLimitNovel();
	/**
	 * ��������ħ��5��С˵
	 * @return
	 */
	public List<Novel> findFantasyMagicLimitNovel();
	/**
	 * ������������5��С˵
	 * @return
	 */
	public List<Novel> findComprehensionLimitNovel();
	/**
	 * ���ҿƻ������С˵
	 * @return
	 */
	public List<Novel> findScienceFictionNovel();
	/**
	 * ���ҿֲ���㤵�С˵
	 * @return
	 */
	public List<Novel> findHorrorNovel();
	/**
	 * �������ξ�����С˵
	 * @return
	 */
	public List<Novel> findOnlineGameNovel();
	/**
	 * �������ξ�����С˵
	 * @return
	 */
	public List<Novel> findHistoricalNovel();
	/**
	 * �����������͵�С˵
	 * @return
	 */
	public List<Novel> findOtherNovel();
	/**
	 * ���ҿƻ������5��С˵
	 * @return
	 */
	public List<Novel> findScienceFictionLimitNovel();
	/**
	 * ���ҿֲ���㤵�5��С˵
	 * @return
	 */
	public List<Novel> findHorrorLimitNovel();
	/**
	 * �������ξ�����5��С˵
	 * @return
	 */
	public List<Novel> findOnlineGameLimitNovel();
	/**
	 * ������ʷ���µ�5��С˵
	 * @return
	 */
	public List<Novel> findHistoricalLimitNovel();
	/**
	 * �����������͵�5��С˵
	 * @return
	 */
	public List<Novel> findOtherLimitNovel();
	
    List<Novel> getAllNovels();

}
