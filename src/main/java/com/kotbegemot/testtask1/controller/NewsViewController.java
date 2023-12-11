package com.kotbegemot.testtask1.controller;

import com.kotbegemot.testtask1.api.dto.NewsDTO;
import com.kotbegemot.testtask1.api.dto.NewsFormDTO;
import com.kotbegemot.testtask1.api.dto.PagedNewsDTO;
import com.kotbegemot.testtask1.service.NewsService;
import com.kotbegemot.testtask1.service.implementation.NewsServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

/**
 * Controller to serve news content
 */
@Controller
@RequestMapping("/news")
public class NewsViewController {
    private static final Logger logger = LoggerFactory.getLogger(NewsViewController.class);
    private static final String SMALL_PAGE_SIZE = "10";
    private static final String MEDIUM_PAGE_SIZE = "20";
    private static final String LARGE_PAGE_SIZE = "50";
    private static final NewsFormDTO DEFAULT_NEWS = new NewsFormDTO(null, "", "", null);
    private static final String DEFAULT_PAGE_SIZE = SMALL_PAGE_SIZE;
    public static final String[] PAGE_SIZE_LIST = {SMALL_PAGE_SIZE, MEDIUM_PAGE_SIZE, LARGE_PAGE_SIZE};
    private static final String DEFAULT_PAGE_NUMBER = "0";
    private NewsService newsService;
    private Validator validator;

    public NewsViewController(NewsServiceImpl newsService1, Validator validator1) {
        newsService = newsService1;
        validator = validator1;
    }

    /**
     * Method returning the main page view
     *
     * @param pageSize   Not null page size of one
     * @param pageNumber Zero based page number
     * @param model      Spring MVC view model
     * @return Name of Main page view
     */
    @GetMapping(path = "/mainPage", produces = MediaType.TEXT_HTML_VALUE)
    public String getNewsLineMainPage(@RequestParam(name = "pageSize", required = false, defaultValue = SMALL_PAGE_SIZE) @Valid @NotNull @Positive Integer pageSize,
                                      @RequestParam(name = "pageNumber", required = false, defaultValue = DEFAULT_PAGE_NUMBER) @Valid @NotNull @PositiveOrZero Integer pageNumber,
                                      Model model) {
        PagedNewsDTO news = newsService.getPageByNumber(pageNumber, pageSize);
        model.addAttribute("pageSizes", PAGE_SIZE_LIST);
        model.addAttribute("page", news);
        return "news";
    }

    /**
     * Method returning the add page view
     *
     * @param model Spring MVC view model
     * @return Name of Main page view
     */
    @GetMapping(path = "/addPage", produces = MediaType.TEXT_HTML_VALUE)
    public String getNewsAddPage(Model model) {
        model.addAttribute("newEntity", DEFAULT_NEWS);
        return "addOrEdit";
    }

    /**
     * Method returning the edit page view
     *
     * @param id    News id in database
     * @param model Spring MVC view model
     * @return Name of Edit news view
     */
    @GetMapping(path = "/editPage", produces = MediaType.TEXT_HTML_VALUE)
    public String getNewsEditPage(@RequestParam(name = "id", required = true) @Valid @NotNull @PositiveOrZero Long id, Model model) {
        NewsDTO news = newsService.getNewsById(id);
        model.addAttribute("newEntity", news);
        return "addOrEdit";
    }

    /**
     * Method adding new news
     *
     * @param newEntity Model object, based on form values
     * @param request   Http request
     * @return Redirect to main page on success
     */
    @PostMapping(path = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String addNews(@Valid @ModelAttribute(name = "newEntity") NewsFormDTO newEntity, BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            return "addOrEdit";
        }
        newsService.saveOrUpdateNews(newEntity);
        request.setAttribute(
                View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.FOUND);
        return "redirect:/news/mainPage";
    }

    /**
     * Method editing existing in database news
     *
     * @param newEntity Model object, based on form values
     * @param request   Http request
     * @return Redirect to main page on success
     */
    @PostMapping(path = "/edit", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String editNews(@Valid @ModelAttribute NewsFormDTO newEntity, BindingResult result,
                                 HttpServletRequest request) {
        if (result.hasErrors()) {
            return "addOrEdit";
        }
        newsService.saveOrUpdateNews(newEntity);
        request.setAttribute(
                View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.FOUND);
        return "redirect:/news/mainPage";
    }
}