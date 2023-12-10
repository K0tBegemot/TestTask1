package com.kotbegemot.testtask1.controller;

import com.kotbegemot.testtask1.api.dto.EditNewsDTO;
import com.kotbegemot.testtask1.api.dto.NewsDTO;
import com.kotbegemot.testtask1.api.dto.PagedNewsDTO;
import com.kotbegemot.testtask1.service.NewsService;
import com.kotbegemot.testtask1.service.implementation.NewsServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import java.util.List;

/**
 * Controller to serve news content
 */
@RestController
@RequestMapping("/news")
public class NewsController
{
    private static final String SMALL_PAGE_SIZE = "10";
    private static final String MEDIUM_PAGE_SIZE = "20";
    private static final String LARGE_PAGE_SIZE = "50";
    private static final String DEFAULT_PAGE_SIZE = SMALL_PAGE_SIZE;
    private static final List<String> PAGE_SIZE_LIST = List.of(SMALL_PAGE_SIZE, MEDIUM_PAGE_SIZE, LARGE_PAGE_SIZE);
    private static final String DEFAULT_PAGE_NUMBER = "0";
    private NewsService newsService;
    public NewsController(NewsServiceImpl newsService1)
    {
        newsService = newsService1;
    }

    /**
     *
     * @param pageSize
     * @param pageNumber
     * @param model
     * @return
     */
    @GetMapping(path = "/mainPage", produces = MediaType.TEXT_HTML_VALUE)
    public String getNewsLineMainPage(@RequestParam(name = "pageSize", required = false, defaultValue = SMALL_PAGE_SIZE) @NotNull @Positive Integer pageSize,
                                      @RequestParam(name = "pageNumber", required = false, defaultValue = DEFAULT_PAGE_NUMBER) @NotNull @PositiveOrZero Integer pageNumber,
                                      Model model)
    {
        PagedNewsDTO news = newsService.getPageByNumber(pageNumber, pageSize);
        model.addAttribute("pageSizes", PAGE_SIZE_LIST);
        model.addAttribute("page", news);
        return "news";
    }

    /**
     *
     * @param model
     * @return
     */
    @GetMapping(path = "/addPage", produces = MediaType.TEXT_HTML_VALUE)
    public String getNewsAddPage(Model model)
    {
        return "addOrEdit";
    }

    /**
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping(path = "/editPage", produces = MediaType.TEXT_HTML_VALUE)
    public String getNewsEditPage(@RequestParam(name = "id", required = true)@NotNull @PositiveOrZero Long id, Model model)
    {
        NewsDTO news = newsService.getNewsById(id);
        model.addAttribute("new_entity", news);
        return "addOrEdit";
    }

    /**
     *
     * @param header
     * @param text
     * @param image
     * @param request
     * @return
     */
    @PostMapping(path = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ModelAndView addNews(@RequestParam(name = "header", required = true)@NotNull String header,
                                @RequestParam(name = "text", required = true)@NotNull String text,
                                @RequestParam(name = "image", required = false)MultipartFile image, HttpServletRequest request)
    {
        EditNewsDTO newsDTO = new EditNewsDTO(header, text, image);
        newsService.saveOrUpdateNews(newsDTO);
        request.setAttribute(
                View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.FOUND);
        return new ModelAndView("redirect:/news/mainPage");
    }

    /**
     *
     * @param id
     * @param header
     * @param text
     * @param image
     * @param request
     * @return
     */
    @PostMapping(path = "/edit", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ModelAndView editNews(@RequestParam(name = "id", required = true)@NotNull @PositiveOrZero Long id,
                         @RequestParam(name = "header", required = true)@NotNull String header,
                         @RequestParam(name = "text", required = true)@NotNull String text,
                         @RequestParam(name = "image", required = false) MultipartFile image,
                         HttpServletRequest request)
    {
        EditNewsDTO newsDTO = new EditNewsDTO(id, header, text, image);
        newsService.saveOrUpdateNews(newsDTO);
        request.setAttribute(
                View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.FOUND);
        return new ModelAndView("redirect:/news/mainPage");
    }

    /**
     *
     * @param imageId
     * @return
     */
    @GetMapping(path = "/image/{imageId}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody byte[] getImage(@PathVariable(name = "imageId") @NotNull @PositiveOrZero Long imageId)
    {
        return newsService.getImage(imageId).getImage();
    }
}