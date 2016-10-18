package com.apress.springrecipes.board.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.acls.AclPermissionEvaluator;
import org.springframework.security.acls.domain.AclAuthorizationStrategyImpl;
import org.springframework.security.acls.domain.ConsoleAuditLogger;
import org.springframework.security.acls.domain.DefaultPermissionGrantingStrategy;
import org.springframework.security.acls.domain.SpringCacheBasedAclCache;
import org.springframework.security.acls.jdbc.BasicLookupStrategy;
import org.springframework.security.acls.jdbc.JdbcMutableAclService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.sql.DataSource;

/**
 * Created by marten on 13-06-14.
 */
@Configuration
public class MessageBoardAclSecurityConfiguration {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private CacheManager cacheManager;

    @Bean
    public SpringCacheBasedAclCache aclCache() {
        Cache cache = cacheManager.getCache("aclCache");
        return new SpringCacheBasedAclCache(cache, permissionGrantingStrategy(), authorizationStrategy());
    }

    @Bean
    public BasicLookupStrategy lookupStrategy() {
        return new BasicLookupStrategy(dataSource, aclCache(), authorizationStrategy(), permissionGrantingStrategy());
    }

    @Bean
    public DefaultPermissionGrantingStrategy permissionGrantingStrategy() {
        return new DefaultPermissionGrantingStrategy(new ConsoleAuditLogger());
    }

    @Bean
    public AclAuthorizationStrategyImpl authorizationStrategy() {
        return new AclAuthorizationStrategyImpl(new SimpleGrantedAuthority("ROLE_ADMIN"));    }

    @Bean
    public JdbcMutableAclService jdbcMutableAclService() {
        return new JdbcMutableAclService(dataSource, lookupStrategy(), aclCache());
    }

    @Bean
    public AclPermissionEvaluator permissionEvaluator() {
        return new AclPermissionEvaluator(jdbcMutableAclService());
    }

}
