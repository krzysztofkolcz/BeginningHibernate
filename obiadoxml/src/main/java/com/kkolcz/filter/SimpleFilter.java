package com.kkolcz.filter;

import java.io.IOException;

import org.slf4j.LoggerFactory;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.stereotype.Component;

@Component
public class SimpleFilter extends OncePerRequestFilter {

    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(SimpleFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {
        logger.debug("[doFilterInternal]", "");
        res.addHeader("dummy-header", "dummy-value");
        try {
            chain.doFilter(req, res);
        } finally {
            res.addHeader("dummy-header", "dummy-value");
        }
    }
}

