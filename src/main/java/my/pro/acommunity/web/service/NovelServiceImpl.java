package my.pro.acommunity.web.service;

import my.pro.acommunity.dto.PaginationDTO;
import my.pro.acommunity.dto.QuestionDTO;
import my.pro.acommunity.dto.QuestionQueryDTO;
import my.pro.acommunity.mapper.NovelMapper;
import my.pro.acommunity.model.Novel;
import my.pro.acommunity.model.Question;
import my.pro.acommunity.model.QuestionExample;
import my.pro.acommunity.model.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NovelServiceImpl implements NovelService {

	@Resource
	private NovelMapper mapper;
	
	@Override
	public List<Novel> findUrbanNovel() {
		
		return mapper.findUrbanNovel();
	}
	@Override
	public Novel findNovelInfo(String id) {

		return mapper.findNovelInfo(id);
	}
	@Override
	public Novel findNowRead(String id) {

		return mapper.findNowRead(id);
	}
	@Override
	public List<Novel> findNovelName(String kw) {
		kw = "%" + kw + "%";
		return mapper.findNovelName(kw);
	}
	@Override
	public List<Novel> findFantasyMagicNovel() {

		return mapper.findFantasyMagicNovel();
	}
	@Override
	public List<Novel> findComprehensionNovel() {

		return mapper.findComprehensionNovel();
	}
	@Override
	public List<Novel> findUrbanLimitNovel() {
		
		return mapper.findUrbanLimitNovel();
	}
	@Override
	public List<Novel> findFantasyMagicLimitNovel() {
		
		return mapper.findFantasyMagicLimitNovel();
	}
	@Override
	public List<Novel> findComprehensionLimitNovel() {
		
		return mapper.findComprehensionLimitNovel();
	}
	@Override
	public List<Novel> findScienceFictionNovel() {
		// TODO Auto-generated method stub
		return mapper.findScienceFictionNovel();
	}
	@Override
	public List<Novel> findHorrorNovel() {
		// TODO Auto-generated method stub
		return mapper.findHorrorNovel();
	}
	@Override
	public List<Novel> findOnlineGameNovel() {
		// TODO Auto-generated method stub
		return mapper.findOnlineGameNovel();
	}
	@Override
	public List<Novel> findHistoricalNovel() {
		// TODO Auto-generated method stub
		return mapper.findHistoricalNovel();
	}
	@Override
	public List<Novel> findOtherNovel() {
		// TODO Auto-generated method stub
		return mapper.findOtherNovel();
	}
	@Override
	public List<Novel> findScienceFictionLimitNovel() {
		// TODO Auto-generated method stub
		return mapper.findScienceFictionLimitNovel();
	}
	@Override
	public List<Novel> findHorrorLimitNovel() {
		// TODO Auto-generated method stub
		return mapper.findHorrorLimitNovel();
	}
	@Override
	public List<Novel> findOnlineGameLimitNovel() {
		// TODO Auto-generated method stub
		return mapper.findOnlineGameLimitNovel();
	}
	@Override
	public List<Novel> findHistoricalLimitNovel() {
		// TODO Auto-generated method stub
		return mapper.findHistoricalLimitNovel();
	}
	@Override
	public List<Novel> findOtherLimitNovel() {
		// TODO Auto-generated method stub
		return mapper.findOtherLimitNovel();
	}

	@Override
	public List<Novel> getAllNovels() {
		
		return mapper.getAllNovels();
	}


}
