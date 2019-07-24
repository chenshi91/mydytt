package com.dytt.module.test.mybatisplus;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

/**
 * @author chenshi
 * @date 2019-06-20
 */
public class AutoGeneratorDemo {

    @Test
    public void system() {
        AutoGenerator mpg = new AutoGenerator() {{
            // 全局配置
            //输出文件路径
            setGlobalConfig(new GlobalConfig() {{
                setOutputDir("D:\\javaProjects\\mydytt\\service-modules\\service-test\\src\\main\\java");
                setFileOverride(true);
                //不需要ActiveRecord特性的请改为false
                setActiveRecord(false);
                //XML 二级缓存
                setEnableCache(false);
                //XML ResultMap
                setBaseResultMap(false);
                //XML columList
                setBaseColumnList(false);
                //作者
                setAuthor("chenshi");
                // 自定义文件命名，注意 %s 会自动填充表实体属性！
                setControllerName("%sController");
                setServiceName("%sService");
                setServiceImplName("%sServiceImpl");
                setMapperName("%sMapper");
                setXmlName("%sMapper");
            }});

            // 数据源配置
            setDataSource(new DataSourceConfig() {{
                setDbType(DbType.MYSQL);
                setDriverName("com.mysql.jdbc.Driver");
                setUsername("root");
                setPassword("123456");
                setUrl("jdbc:mysql://localhost:3306/dytt?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&serverTimezone=GMT%2B8");
            }});

            // 策略配置
            setStrategy(new StrategyConfig() {{
                setNaming(NamingStrategy.underline_to_camel);
                //需要生成的表
                setInclude(new String[]{"users"});
                //全局大写命名
                setCapitalMode(true);
                //【实体】是否为构建者模型
                setEntityBuilderModel(true);
                //【实体】是否为lombok模型
                setEntityLombokModel(true);
                setRestControllerStyle(true);
                setSuperEntityClass("com.dytt.common.mvc.BaseEntity");
                setSuperMapperClass("com.dytt.common.mvc.BaseMapper");
                setSuperServiceImplClass("com.dytt.common.mvc.BaseServiceImpl");
                setSuperServiceClass("com.dytt.common.mvc.BaseService");
                setSuperControllerClass("com.dytt.common.mvc.BaseController");
                setSuperEntityColumns("id", "create_user_id", "create_date", "last_update_user_id", "last_update_date");
            }});

            // 包配置
            setPackageInfo(new PackageConfig() {{
                setParent("com.dytt.module.test");
                setController("controller");
                setService("service");
                setServiceImpl("service.impl");
                setMapper("dao");
                setEntity("entity");
                setXml("xml");
            }});
        }};

        // 执行生成
        mpg.execute();

    }


}
