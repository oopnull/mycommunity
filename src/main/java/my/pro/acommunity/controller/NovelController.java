package my.pro.acommunity.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import my.pro.acommunity.dto.PaginationDTO;
import my.pro.acommunity.model.Novel;
import my.pro.acommunity.web.model.Chapter;
import my.pro.acommunity.web.model.ChapterDetail;
import my.pro.acommunity.web.service.NovelService;
import my.pro.acommunity.web.spider.util.ChapterDetailSpiderFactory;
import my.pro.acommunity.web.spider.util.ChapterSpiderFactory;
import my.pro.acommunity.web.spider.util.NovelSpiderUtil;
//import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
public class NovelController {
	
	@Resource
	private NovelService service;
 	/**
	 *小说网站解析规则所在
	 */
	static {
		NovelSpiderUtil.setConfPath("F:/novel/Spider-Rule.xml");
	}


	/**
	 *
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/")
	public String indexNovel(Model model,@RequestParam(name = "page",defaultValue = "1")
			Integer page) {

		//把列表的信息获取得到
		Page<?> currentPage = PageHelper.startPage(page, 5);
		List<Novel> novels=service.getAllNovels();
		PageInfo<?> pageHelper=currentPage.toPageInfo();
		model.addAttribute("novels",novels);
		model.addAttribute("page",page);
		model.addAttribute("pageHelper",pageHelper);

		model.addAttribute("UrbanNovel", service.findUrbanLimitNovel());
		model.addAttribute("FantasyMagic", service.findFantasyMagicLimitNovel());
		model.addAttribute("Comprehension", service.findComprehensionLimitNovel());
		model.addAttribute("ScienceFiction", service.findScienceFictionLimitNovel());
		model.addAttribute("Horror", service.findHorrorLimitNovel());
		model.addAttribute("OnlineGame", service.findOnlineGameLimitNovel());
		model.addAttribute("Historical", service.findHistoricalLimitNovel());
		model.addAttribute("Other", service.findOtherLimitNovel());
		return "indexNovel";
	}

	/**
	 *
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/urbanNovel")
	public String urbanNovel(Model model,@RequestParam(name = "page",defaultValue = "1")
			Integer page
	) {
		Page<?> currentPage = PageHelper.startPage(page, 5);
		List<Novel> novels=service.findUrbanNovel();
		PageInfo<?> pageHelper=currentPage.toPageInfo();
		model.addAttribute("novels",novels);
		model.addAttribute("page",page);
		model.addAttribute("pageHelper",pageHelper);

		model.addAttribute("UrbanNovel", service.findUrbanNovel());
		return "section/urbanNovel";
	}

	/**
	 *
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/fantasyMagic")
	public String fantasyMagic(Model model,@RequestParam(name = "page",defaultValue = "1")
			Integer page
	) {
		Page<?> currentPage = PageHelper.startPage(page, 5);
		List<Novel> novels=service.findFantasyMagicNovel();
		PageInfo<?> pageHelper=currentPage.toPageInfo();
		model.addAttribute("novels",novels);
		model.addAttribute("page",page);
		model.addAttribute("pageHelper",pageHelper);

		model.addAttribute("FantasyMagic", service.findFantasyMagicNovel());
		return "section/fantasyMagic";
	}
	/**
	 *
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/comprehension")
	public String comprehension(Model model,@RequestParam(name = "page",defaultValue = "1")
			Integer page
	) {
		Page<?> currentPage = PageHelper.startPage(page, 5);
		List<Novel> novels=service.findComprehensionNovel();
		PageInfo<?> pageHelper=currentPage.toPageInfo();
		model.addAttribute("novels",novels);
		model.addAttribute("page",page);
		model.addAttribute("pageHelper",pageHelper);

		model.addAttribute("Comprehension", service.findComprehensionNovel());

		return "section/comprehension";
	}
	/**
	 *
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/scienceFiction")
	public String scienceFiction(Model model,@RequestParam(name = "page",defaultValue = "1")
			Integer page) {
		Page<?> currentPage = PageHelper.startPage(page, 5);
		List<Novel> novels=service.findScienceFictionNovel();
		PageInfo<?> pageHelper=currentPage.toPageInfo();
		model.addAttribute("novels",novels);
		model.addAttribute("page",page);
		model.addAttribute("pageHelper",pageHelper);

		model.addAttribute("ScienceFiction", service.findScienceFictionNovel());
		return "section/scienceFiction";
	}
	/**
	 *
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/horror")
	public String Horror(Model model,@RequestParam(name = "page",defaultValue = "1")
			Integer page) {
		Page<?> currentPage = PageHelper.startPage(page, 5);
		List<Novel> novels=service.findHorrorNovel();
		PageInfo<?> pageHelper=currentPage.toPageInfo();
		model.addAttribute("novels",novels);
		model.addAttribute("page",page);
		model.addAttribute("pageHelper",pageHelper);

		model.addAttribute("Horror", service.findHorrorNovel());
		return "section/horror";
	}
	/**
	 *
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/onlineGame")
	public String OnlineGame(Model model,@RequestParam(name = "page",defaultValue = "1")
			Integer page) {
		Page<?> currentPage = PageHelper.startPage(page, 5);
		List<Novel> novels=service.findOnlineGameNovel();
		PageInfo<?> pageHelper=currentPage.toPageInfo();
		model.addAttribute("novels",novels);
		model.addAttribute("page",page);
		model.addAttribute("pageHelper",pageHelper);

		model.addAttribute("OnlineGame", service.findOnlineGameNovel());

		return "section/onlineGame";
	}
	/**
	 *
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/historical")
	public String Historical(Model model,@RequestParam(name = "page",defaultValue = "1")
			Integer page) {
		Page<?> currentPage = PageHelper.startPage(page, 5);
		List<Novel> novels=service.findHorrorNovel();
		PageInfo<?> pageHelper=currentPage.toPageInfo();
		model.addAttribute("novels",novels);
		model.addAttribute("page",page);
		model.addAttribute("pageHelper",pageHelper);

		model.addAttribute("Historical", service.findHistoricalNovel());

		return "section/historical";
	}
	/**
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/other")
	public String Other(Model model,@RequestParam(name = "page",defaultValue = "1")
			Integer page) {
		Page<?> currentPage = PageHelper.startPage(page, 5);
		List<Novel> novels=service.findOtherNovel();
		PageInfo<?> pageHelper=currentPage.toPageInfo();
		model.addAttribute("novels",novels);
		model.addAttribute("page",page);
		model.addAttribute("pageHelper",pageHelper);

		model.addAttribute("Other", service.findOtherNovel());
		return "section/other";
	}


	/**
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/novelInfo")
	public String novelInfo(Model model, @RequestParam(value = "id",required = false)
			String id) {
		model.addAttribute("NovelInfo", service.findNovelInfo(id));
		return "novelinfo/novelinfo";
	}

	/**
	 * @param
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@GetMapping(value = "/search")
	public String getsNovelByKeyword(Model model,@RequestParam(value = "kw",required = false) String kw,@RequestParam(name = "page",defaultValue = "1")
			Integer page) {
		Page<?> currentPage = PageHelper.startPage(page, 5);
		List<Novel> novels=service.findOtherNovel();
		PageInfo<?> pageHelper=currentPage.toPageInfo();
		model.addAttribute("novels",novels);
		model.addAttribute("page",page);
		model.addAttribute("pageHelper",pageHelper);

		if (kw == null ||kw == "") {
			model.addAttribute("msg", "获取失败，请重新操作");
			return "section/searchnull";
		}else {
			model.addAttribute("NovelName", service.findNovelName(kw));
		return "section/search";
		}
	}

	/**
	 * ��ת���½��б�
	 * @param url
	 * @return
	 */
	@GetMapping(value = "/chapterList")
	public String showChapterList(@RequestParam(value = "url",required = false) String url,@RequestParam(value = "id",required = false) String id
	,Model model ,@RequestParam(name = "page",defaultValue = "1")
	Integer page,@RequestParam(name = "size",defaultValue = "10")Integer size) {
		List<Chapter> chapters = ChapterSpiderFactory.getChapterSpider(url).getChapters(url);
		PageInfo<Chapter> p =new PageInfo(chapters,10);

		//PageInfo<?> pageHelper=currentPage.toPageInfo();
		model.addAttribute("chapters",chapters);
		model.addAttribute("page",page);
		model.addAttribute("pageHelper",p);
		model.addAttribute("ChapterList", service.findNovelInfo(id));
		model.addAttribute("baseUrl", url);
		return "chapter/chapterlist";
	}

	/**
	 * @param url
	 * @return
	 */
	@GetMapping(value = "/nowRead")
	public String nowRead(@RequestParam(value = "url",required = false) String url,
								@RequestParam(value = "id",required = false)String id,
								@RequestParam(value = "baseUrl",required = false)String baseUrl,Model model) {
			model.addAttribute("nowReadName", service.findNovelInfo(id));
		try {
			ChapterDetail detail = ChapterDetailSpiderFactory.getsChapterDetailSpider(url).getChapterDetail(url);
			detail.setContent(detail.getContent().replaceAll("\n", "<br>"));
			model.addAttribute("chapterDetail", detail);
			model.addAttribute("isSuccess", true);

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("isSuccess", false);
		}
		model.addAttribute("baseUrl", baseUrl);
		return "content/nowReadContent";
	}

	/**
	 * @param url
	 * @param baseUrl
	 * @return
	 */
	@GetMapping(value = "/chapterDetail")
	public String showChapterDetail(@RequestParam(value = "url",required = false) String url,
										  @RequestParam(value = "id",required = false)String id,
										  @RequestParam(value = "baseUrl",required = false)String baseUrl,Model model) {
		model.addAttribute("nowReadName", service.findNovelInfo(id));
		model.addAttribute("ChapterDetailName", service.findNovelInfo(id));
		try {
			ChapterDetail detail = ChapterDetailSpiderFactory.getsChapterDetailSpider(url).getChapterDetail(url);
			detail.setContent(detail.getContent().replaceAll("\n", "<br>"));
			model.addAttribute("chapterDetail", detail);
			model.addAttribute("isSuccess", true);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("isSuccess", false);
		}
		model.addAttribute("baseUrl", baseUrl);
		return "content/content";
	}
}
