package com.kotbegemot.testtask1.service.util;

import com.kotbegemot.testtask1.service.exception.IllegalPageNumberException;
import org.springframework.data.domain.Page;

public class PageUtils
{
    public static <T> void checkPageNumber(Page<T> page, Integer pageNumber)
    {
        if(pageNumber >= page.getTotalPages() && pageNumber != 0)
        {
            throw new IllegalPageNumberException();
        }
    }
}