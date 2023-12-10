package com.kotbegemot.testtask1.service.util;

import com.kotbegemot.testtask1.service.exception.IllegalPageNumberException;
import org.springframework.data.domain.Page;

/**
 * Class for methods that check page indexes validity
 */
public class PageUtils
{
    /**
     * Check that request pageNumber is valid page number. If not valid, throw {@link IllegalPageNumberException}
     * @param page Page from database with requested number = pageNumber
     * @param pageNumber Requested pageNumber
     * @param <T> Type of page
     */
    public static <T> void checkPageNumber(Page<T> page, Integer pageNumber)
    {
        if(pageNumber >= page.getTotalPages() && pageNumber != 0)
        {
            throw new IllegalPageNumberException();
        }
    }
}