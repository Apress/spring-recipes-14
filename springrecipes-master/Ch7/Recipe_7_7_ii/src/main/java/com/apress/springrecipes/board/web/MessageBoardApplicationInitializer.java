package com.apress.springrecipes.board.web;

import com.apress.springrecipes.board.config.MessageBoardConfiguration;
import com.apress.springrecipes.board.web.config.MessageBoardAclSecurityConfiguration;
import com.apress.springrecipes.board.web.config.MessageBoardSecurityConfiguration;
import com.apress.springrecipes.board.web.config.MessageBoardWebConfiguration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created by marten on 06-06-14.
 */
@Order(1)
public class MessageBoardApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{MessageBoardConfiguration.class, MessageBoardSecurityConfiguration.class, MessageBoardAclSecurityConfiguration.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{MessageBoardWebConfiguration.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

}
