package com.example.demo.base;

import com.example.demo.context.WebContext;
import com.example.demo.domain.User;
import com.example.demo.utility.ModelMapperSingle;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseApiController {
    protected final static String DEFAULT_PAGE_SIZE="10";
    protected final static ModelMapper modelMapper=ModelMapperSingle.Instance();

    @Autowired
    protected WebContext webContext;

    protected User getCurrentUser(){return webContext.getCurrentUser();}
}
