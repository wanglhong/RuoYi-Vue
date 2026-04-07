package com.ruoyi.framework.config;

import com.mybatisflex.spring.FlexSqlSessionFactoryBean;
import com.ruoyi.common.utils.StringUtils;
import org.apache.ibatis.io.VFS;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * MyBatis-Flex 配置类
 *
 * @author ruoyi
 */
@Configuration
public class MyBatisFlexConfig {

    @Autowired
    private Environment env;

    static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";

    public static String setTypeAliasesPackage(String typeAliasesPackage) {
        ResourcePatternResolver resolver = (ResourcePatternResolver) new PathMatchingResourcePatternResolver();
        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resolver);
        List<String> allResult = new ArrayList<String>();
        try {
            for (String aliasesPackage : typeAliasesPackage.split(",")) {
                List<String> result = new ArrayList<String>();
                aliasesPackage = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
                        + ClassUtils.convertClassNameToResourcePath(aliasesPackage.trim()) + "/" + DEFAULT_RESOURCE_PATTERN;
                Resource[] resources = resolver.getResources(aliasesPackage);
                if (resources != null && resources.length > 0) {
                    MetadataReader metadataReader = null;
                    for (Resource resource : resources) {
                        if (resource.isReadable()) {
                            metadataReader = metadataReaderFactory.getMetadataReader(resource);
                            try {
                                result.add(Class.forName(metadataReader.getClassMetadata().getClassName()).getPackage().getName());
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                if (result.size() > 0) {
                    HashSet<String> hashResult = new HashSet<String>(result);
                    allResult.addAll(hashResult);
                }
            }
            if (allResult.size() > 0) {
                typeAliasesPackage = String.join(",", (String[]) allResult.toArray(new String[0]));
            } else {
                throw new RuntimeException("mybatis typeAliasesPackage 路径扫描错误,参数typeAliasesPackage:" + typeAliasesPackage + "未找到任何包");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return typeAliasesPackage;
    }

    public Resource[] resolveMapperLocations(String[] mapperLocations) {
        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
        List<Resource> resources = new ArrayList<Resource>();
        if (mapperLocations != null) {
            for (String mapperLocation : mapperLocations) {
                try {
                    Resource[] mappers = resourceResolver.getResources(mapperLocation);
                    resources.addAll(Arrays.asList(mappers));
                } catch (IOException e) {
                    // ignore
                }
            }
        }
        return resources.toArray(new Resource[resources.size()]);
    }

    private static final String RUO_YI_TYPE_ALIASES_PACKAGE = "com.ruoyi.**.domain";
    private static final String RUO_YI_MAPPER_LOCATIONS = "classpath*:mapper/**/*Mapper.xml";
    private static final String RUO_YI_CONFIG_LOCATION = "classpath:mybatis/mybatis-config.xml";

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        // 支持 kebab-case 和 camelCase 两种配置格式
        String typeAliasesPackage = env.getProperty("mybatis-flex.type-aliases-package");
        String mapperLocations = env.getProperty("mybatis-flex.mapper-locations");
        String configLocation = env.getProperty("mybatis-flex.config-location");
        if (StringUtils.isNotBlank(typeAliasesPackage)) {
            typeAliasesPackage = setTypeAliasesPackage(typeAliasesPackage);
        }
        if (StringUtils.isEmpty(typeAliasesPackage)) {
            typeAliasesPackage = RUO_YI_TYPE_ALIASES_PACKAGE;
        } else {
            typeAliasesPackage = RUO_YI_TYPE_ALIASES_PACKAGE + "," + typeAliasesPackage;
        }
        if (StringUtils.isEmpty(mapperLocations)) {
            mapperLocations = RUO_YI_MAPPER_LOCATIONS;
        } else {
            mapperLocations = RUO_YI_MAPPER_LOCATIONS + "," + mapperLocations;
        }
        if (StringUtils.isEmpty(configLocation)) {
            configLocation = RUO_YI_CONFIG_LOCATION;
        }
        final FlexSqlSessionFactoryBean sessionFactory = new FlexSqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setTypeAliasesPackage(typeAliasesPackage);
        sessionFactory.setMapperLocations(resolveMapperLocations(StringUtils.split(mapperLocations, ",")));
        sessionFactory.setConfigLocation(new DefaultResourceLoader().getResource(configLocation));
        return sessionFactory.getObject();
    }

}
