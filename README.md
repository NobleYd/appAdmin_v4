# 简介
本文将给您介绍 AppAdmin 后台管理系统开发框架。 
AppAdmin后台管理系统开发框架是一套Java开发的整合了当前众多比较流行的Java后台开发框架的系统，使用H5响应式布局。 
整合了 spring + springMVC + hibernate （JPA） + shiro + ehcache 等框架，功能包括基本的系统管理、权限、角色、存储（oss、本地、ftp）、缓存、站内信、smtp邮件发送、微信公众号开发、富文本内容使用文件存储的统一实现等等众多模块，持续更新开发中。

# 主流技术框架
- Spring 
	- Spring是一个开源框架，Spring是于2003 年兴起的一个轻量级的Java 开发框架，由OP）。简单来说，Spring是一个分层的JavaSE/EEfull-stack(一站式) 轻量级开源框架。
- SpringMVC
	- Spring MVC属于SpringFrameWork的后续产品，已经融合在Spring Web Flow里面。Spring 框架提供了构建 Web 应用程序的全功能 MVC 模块。使用 Spring 可插入的 MVC 架构，从而在使用Spring进行WEB开发时，可以选择使用Spring的SpringMVC框架或集成其他MVC开发框架，如Struts1，Struts2等。
- JPA（Hiberntea）
	- JPA全称Java Persistence API。JPA通过JDK 5.0注解或XML描述对象－关系表的映射关系，并将运行期的实体对象持久化到数据库中。
	- JPA是需要Provider来实现其功能的，Hibernate就是JPA Provider中很强的一个，应该说无人能出其右。从功能上来说，JPA就是Hibernate功能的一个子集。Hibernate 从3.2开始，就开始兼容JPA。Hibernate3.2获得了Sun TCK的JPA(Java Persistence API) 兼容认证。
	- 只要熟悉Hibernate或者其他ORM框架，在使用JPA时会发现其实非常容易上手。例如实体对象的状态，在Hibernate有自由、持久、游离三种，JPA里有new，managed，detached，removed，明眼人一看就知道，这些状态都是一一对应的。再如flush方法，都是对应的，而其他的再如说Query query = manager.createQuery(sql)，它在Hibernate里写法上是session，而在JPA中变成了manager，所以从Hibernate到JPA的代价应该是非常小的。
	- 在ORM的领域中，看来JPA已经是王道，规范就是规范。在各大厂商的支持下，JPA的使用开始变得广泛。
- C3P0数据源 
	- 这是一个开源的JDBC连接池，它实现了数据源和JNDI绑定，支持JDBC3规范和JDBC2的标准扩展。目前使用它的开源项目有Hibernate，Spring等。
- Freemarker 
	- FreeMarker是一款模板引擎： 即一种基于模板和要改变的数据， 并用来生成输出文本（HTML网页、电子邮件、配置文件、源代码等）的通用工具。 它不是面向最终用户的，而是一个Java类库，是一款程序员可以嵌入他们所开发产品的组件。
FreeMarker是免费的，基于Apache许可证2.0版本发布。其模板编写为FreeMarker Template Language（FTL），属于简单、专用的语言。需要准备数据在真实编程语言中来显示，比如数据库查询和业务运算， 之后模板显示已经准备好的数据。在模板中，主要用于如何展现数据， 而在模板之外注意于要展示什么数据。
- Jetty服务器 
	- Jetty 是一个开源的servlet容器，它为基于Java的web容器，例如JSP和servlet提供运行环境。Jetty是使用Java语言编写的，它的API以一组JAR包的形式发布。开发人员可以将Jetty容器实例化成一个对象，可以迅速为一些独立运行（stand-alone）的Java应用提供网络和web连接。
- Apache Shiro 
	- 这是一个强大易用的 Java 安全框架，提供了认证、授权、加密和会话管理功能，可为任何应用提供安全保障-从命令行应用、移动应用到大型网络及企业应用。
- JCaptcha 
	- 这是一个开源的用来生成图形验证码的 Java 开源组件，使用起来也是非常的简单方便。
- JackSon Json 
	- 这是一个 Java 用来处理 JSON 格式数据的类库，性能非常好。
- EhCache 
	- EhCache 是一个纯Java的进程内缓存框架，具有快速、精干等特点，是Hibernate中默认的CacheProvider。
- MemoryCache 
	- MemoryCache 是一个使用内存作为缓存的技术。
- dom4j 
	- dom4j是一个Java的XML API，是jdom的升级品，用来读写XML文件的。dom4j是一个十分优秀的JavaXML API，具有性能优异、功能强大和极其易使用的特点，它的性能超过sun公司官方的dom技术，同时它也是一个开放源代码的软件，可以在SourceForge上找到它。在IBM developerWorks上面还可以找到一篇文章，对主流的Java XML API进行的性能、功能和易用性的评测，所以可以知道dom4j无论在哪个方面都是非常出色的。如今可以看到越来越多的Java软件都在使用dom4j来读写XML，特别值得一提的是连Sun的JAXM也在用dom4j。这已经是必须使用的jar包， Hibernate也用它来读写配置文件。
- POI 
	- Apache POI是Apache软件基金会的开放源码函式库，POI提供API给Java程序对Microsoft Office格式档案读和写的功能。
- xstream 
	- Xstream是一种OXMapping 技术，是用来处理XML文件序列化的框架,在将javaBean序列化，或将XML文件反序列化的时候，不需要其它辅助类和映射文件，使得XML序列化不再繁琐。

# 技术特色
- 配置注解化、properties化、xml化 
	- 系统中有的配置通过properties文件实现【数据库、定时任务等】，有的配置通过xml实现【系统业务设置】，其他的框架机制上的配置则通过注解实现【控制器、业务层、数据访问层、实体类等】。
- JPA Criteria查询封装 
	- 众所周知，使用Hibernate查询有很多种方式，可以使用Hibernate提供的HQL（hibernate query language）语言，还可以使用JPA标准的JPQL（Java Persistence Query Language）语言，还可以使用原生的数据库SQL语言。
	- SQL在效率、统计、需要使用特殊技巧的时候起到了很大的作用，但是相对比较难写。
	- HQL和JPQL类似，只是JPQL是标准，HQL是Hibernate查询语言，所以俩者之间选择JPQL，在遇到一些稍微复杂的问题时可以使用JPQL查询。
	- 此外，系统中85%以上都是使用JPA提供的另外一套查询机制，Criteria查询方式。这种方式的特点是完全面向对象的查询，即使纯粹没有学过任何类SQL语言的开发者，只要Java学得好，面向对象的思想够深，看懂Criteria查询是很简单的。
	- 高度抽取公用方法，查询，筛选，排序，分页等机制全部使用公用方法支持。通过泛型机制、配合少量反射技术实现公用方法的类型严格检测。
- 系统存储插件化 
	- 系统中当前支持三大中存储方式的实现，即本地存储、FTP存储、阿里云（OSS）存储。
	- 三大存储方式都通过插件化开发，系统启动过程中，可通过后台存储插件相关功能处随时修改存储方式。
	- 本地存储是默认方式。阿里云存储只需要您自行申请到OSS接口，将相关信息配置到后台即可，FTP存储同理。
- 高度模块化、子系统化 
	- 系统中分公用模块、业务子模块（buzz包下分类），比如微信模块、开发工具模块、演示实体模块等。
- CURD生成（开发利器） 
	- 开发工具模块支持自定义实体名字、是否分页、是否可批量删除、是否树形实体、可以配置实体的属性（名字、类型、是否必须、是否支持搜索、是否支持字段排序、是否唯一）等。然后一键生成从后台到前台的所有代码文件、页面文件。
	- 支持字符串、整数、长整数、日期、布尔、枚举、部分关联类型、BigDecimal、以及自定义类型等属性类型，关联类型当前支持select方式、search方式实现。
	- 生成功能可支持分页、curd、字段排序搜索、页面验证、后台验证、ajax验证等等功能。
	- 此外，开发模块高度二次开发化，使用主题开发方式，只要您的技术够好或者更好，支持自定义模板主题，生成您自己的主题页面。
- Excel报表视图基类实现 
	- 当您再需要导出excel的时候，只需要简单的返回一个excel视图，配置好您要导出的实体的字段列表，字段列长（可选）即可立即实现excel报表导出功能了。
- 丰富的工具类 
	- Freemarker模板操作工具类
	- Http请求工具类
	- 图片处理工具类
	- JackSon处理工具类
	- MemoryCache工具类
	- RSA加密工具类
	- WebUtils工具类【操作cookie等】
- 多方面安全 
	- 自动实现Token机制
	- Shiro权限控制
	- resource等资源访问控制

# 代码片段
- 属性筛选封装Filter类
```java
public class Filter implements Serializable {
    /**
     * 运算符
     */
    public enum Operator {
        /** 等于 */
        eq,
        /** 不等于 */
        ne,
        /** 大于 */
        gt, greaterThan,
        /** 小于 */
        lt, lessThan,
        /** 大于等于 */
        ge, greaterThanOrEqualTo,
        /** 小于等于 */
        le, lessThanOrEqualTo,
        /** 相似 */
        like,
        /** 包含 */
        in,
        /** 为Null */
        isNull,
        /** 不为Null */
        isNotNull;
    }

    /** 默认是否忽略大小写 */
    private static final boolean DEFAULT_IGNORE_CASE = false;

    /** 属性 */
    private String property;

    /** 运算符 */
    private Operator operator;

    /** 值 */
    private Object value;

    /** 标识值value是否是另外一个属性的名称 */
    private Boolean isValuePropery = false;

    /** 是否忽略大小写(仅针对value为String类型情况有效) */
    private Boolean ignoreCase = DEFAULT_IGNORE_CASE;

    /**
     * 初始化一个新创建的Filter对象
     */
    private Filter() {
    }

    /**
     * 初始化一个新创建的Filter对象(使用Object value)
     * 
     * @param property
     *            属性
     * @param operator
     *            运算符
     * @param isValuePropery
     *            value是否是属性
     * @param value
     *            值
     */
    private Filter(String property, Operator operator, Object value, boolean isValuePropery) {
        this.property = property;
        this.operator = operator;
        this.value = value;
        this.isValuePropery = isValuePropery;
    }

    /**
     * 初始化一个新创建的Filter对象(使用Object value)
     * 
     * @param property
     *            属性
     * @param operator
     *            运算符
     * @param value
     *            值
     * @param isValuePropery
     *            value是否是属性
     * @param ignoreCase
     *            忽略大小写
     */
    private Filter(String property, Operator operator, Object value, boolean isValuePropery, boolean ignoreCase) {
        this.property = property;
        this.operator = operator;
        this.value = value;
        this.ignoreCase = ignoreCase;
        this.isValuePropery = isValuePropery;
    }
}
```
- BaseDaoImpl中的addRestrictions方法
```java
protected void addRestrictions(CriteriaQuery<T> criteriaQuery, List<Filter> filters) {
        if (criteriaQuery == null || filters == null || filters.isEmpty()) {
            return;
        }
        Root<T> root = getRoot(criteriaQuery);
        if (root == null) {
            return;
        }
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        Predicate restrictions = criteriaQuery.getRestriction() != null ? criteriaQuery.getRestriction() : criteriaBuilder.conjunction();
        for (Filter filter : filters) {
            if (filter == null || StringUtils.isEmpty(filter.getProperty())) {
                continue;
            }
            if (filter.getOperator() == Operator.eq && filter.getValue() != null) {
                if (filter.getIgnoreCase() != null && filter.getIgnoreCase() && filter.getValue() instanceof String) {
                    if (filter.valueIsPropery()) {
                        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(criteriaBuilder.lower(root.<String> get(filter.getProperty())), criteriaBuilder.lower(root.<String> get(filter.getComparePropery()))));
                    } else {
                        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(criteriaBuilder.lower(root.<String> get(filter.getProperty())), ((String) filter.getValue()).toLowerCase()));
                    }
                } else {
                    if (filter.valueIsPropery()) {
                        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get(filter.getProperty()), root.get(filter.getComparePropery())));
                    } else {
                        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get(filter.getProperty()), filter.getValue()));
                    }
                }
            } else if (filter.getOperator() == Operator.ne && filter.getValue() != null) {
                if (filter.getIgnoreCase() != null && filter.getIgnoreCase() && filter.getValue() instanceof String) {
                    if (filter.valueIsPropery()) {
                        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.notEqual(criteriaBuilder.lower(root.<String> get(filter.getProperty())), criteriaBuilder.lower(root.<String> get(filter.getComparePropery()))));
                    } else {
                        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.notEqual(criteriaBuilder.lower(root.<String> get(filter.getProperty())), ((String) filter.getValue()).toLowerCase()));
                    }
                } else {
                    if (filter.valueIsPropery()) {
                        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.notEqual(root.get(filter.getProperty()), root.get(filter.getComparePropery())));
                    } else {
                        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.notEqual(root.get(filter.getProperty()), filter.getValue()));
                    }
                }
            } else if (filter.getOperator() == Operator.gt && filter.getValue() != null) {
                if (filter.valueIsPropery()) {
                    restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.gt(root.<Number> get(filter.getProperty()), root.<Number> get(filter.getComparePropery())));
                } else {
                    restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.gt(root.<Number> get(filter.getProperty()), (Number) filter.getValue()));
                }
            } else if (filter.getOperator() == Operator.greaterThan && filter.getValue() != null) {
                if (filter.valueIsPropery()) {
                    restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.greaterThan(root.<Date> get(filter.getProperty()), root.<Date> get(filter.getComparePropery())));
                } else {
                    restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.greaterThan(root.<Date> get(filter.getProperty()), (Date) filter.getValue()));
                }
            } else if (filter.getOperator() == Operator.lt && filter.getValue() != null) {
                if (filter.valueIsPropery()) {
                    restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.lt(root.<Number> get(filter.getProperty()), root.<Number> get(filter.getComparePropery())));
                } else {
                    restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.lt(root.<Number> get(filter.getProperty()), (Number) filter.getValue()));
                }
            } else if (filter.getOperator() == Operator.lessThan && filter.getValue() != null) {
                if (filter.valueIsPropery()) {
                    restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.lessThan(root.<Date> get(filter.getProperty()), root.<Date> get(filter.getComparePropery())));
                } else {
                    restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.lessThan(root.<Date> get(filter.getProperty()), (Date) filter.getValue()));
                }
            } else if (filter.getOperator() == Operator.ge && filter.getValue() != null) {
                if (filter.valueIsPropery()) {
                    restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.ge(root.<Number> get(filter.getProperty()), root.<Number> get(filter.getComparePropery())));
                } else {
                    restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.ge(root.<Number> get(filter.getProperty()), (Number) filter.getValue()));
                }
            } else if (filter.getOperator() == Operator.greaterThanOrEqualTo && filter.getValue() != null) {
                if (filter.valueIsPropery()) {
                    restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.greaterThanOrEqualTo(root.<Date> get(filter.getProperty()), root.<Date> get(filter.getComparePropery())));
                } else {
                    restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.greaterThanOrEqualTo(root.<Date> get(filter.getProperty()), (Date) filter.getValue()));
                }
            } else if (filter.getOperator() == Operator.le && filter.getValue() != null) {
                if (filter.valueIsPropery()) {
                    restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.le(root.<Number> get(filter.getProperty()), root.<Number> get(filter.getComparePropery())));
                } else {
                    restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.le(root.<Number> get(filter.getProperty()), (Number) filter.getValue()));
                }
            } else if (filter.getOperator() == Operator.lessThanOrEqualTo && filter.getValue() != null) {
                if (filter.valueIsPropery()) {
                    restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.lessThanOrEqualTo(root.<Date> get(filter.getProperty()), root.<Date> get(filter.getComparePropery())));
                } else {
                    restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.lessThanOrEqualTo(root.<Date> get(filter.getProperty()), (Date) filter.getValue()));
                }
            } else if (filter.getOperator() == Operator.like && filter.getValue() != null && filter.getValue() instanceof String) {
                restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.like(root.<String> get(filter.getProperty()), (String) filter.getValue()));
            } else if (filter.getOperator() == Operator.in && filter.getValue() != null) {
                restrictions = criteriaBuilder.and(restrictions, root.get(filter.getProperty()).in((Collection<?>) filter.getValue()));
            } else if (filter.getOperator() == Operator.isNull) {
                restrictions = criteriaBuilder.and(restrictions, root.get(filter.getProperty()).isNull());
            } else if (filter.getOperator() == Operator.isNotNull) {
                restrictions = criteriaBuilder.and(restrictions, root.get(filter.getProperty()).isNotNull());
            }
        }
        criteriaQuery.where(restrictions);
    }
```
- FilePlugin
```java
public class FilePlugin extends StoragePlugin implements ServletContextAware {

    /** servletContext */
    private ServletContext servletContext;

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Override
    public String getName() {
        return "本地文件存储";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public String getAuthor() {
        return "APP";
    }

    @Override
    public String getSiteUrl() {
        return "http://";
    }

    @Override
    public String getInstallUrl() {
        return "file/install.jhtml";
    }

    @Override
    public String getUninstallUrl() {
        return "file/uninstall.jhtml";
    }

    @Override
    public String getSettingUrl() {
        return "file/setting.jhtml";
    }

    @Override
    public void upload(String path, File file, String contentType) {
        File destFile = new File(servletContext.getRealPath(path));
        try {
            FileUtils.moveFile(file, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getUrl(String path) {
        Setting setting = SettingUtils.get();
        return setting.getSiteUrl() + path;
    }

    @Override
    public List<FileInfo> browser(String path) {
        Setting setting = SettingUtils.get();
        List<FileInfo> fileInfos = new ArrayList<FileInfo>();
        File directory = new File(servletContext.getRealPath(path));
        if (directory.exists() && directory.isDirectory()) {
            for (File file : directory.listFiles()) {
                FileInfo fileInfo = new FileInfo();
                fileInfo.setName(file.getName());
                fileInfo.setUrl(setting.getSiteUrl() + path + file.getName());
                fileInfo.setIsDirectory(file.isDirectory());
                fileInfo.setSize(file.length());
                fileInfo.setLastModified(new Date(file.lastModified()));
                fileInfos.add(fileInfo);
            }
        }
        return fileInfos;
    }

}
```
- WeiXinConfig中的refreshAccessToken方法
```java
/***
     * 刷新 AccessToken
     */
    public void refreshAccessToken() {
        if (accessTokenRefreshingFlag.compareAndSet(false, true)) {
            try {
                // 记录上一次 AccessToken 刷新时间
                long lastWeixinAccessTokenStartTime = weixinAccessTokenStartTime;
                // 设置新的 AccessToken 开始时间
                this.weixinAccessTokenStartTime = System.currentTimeMillis();
                // 刷新
                Map<String, Object> jsonMap = WeiXinHttpUtils.getJson(WeiXinURL.get_accessTokenUrl, this, null, null);
                if (jsonMap.containsKey("access_token")) {
                    // example: {"access_token":"ACCESS_TOKEN","expires_in":7200}
                    this.accessToken = jsonMap.get("access_token").toString().trim();
                    if (jsonMap.containsKey("expires_in")) {
                        this.expireTime = (Integer) jsonMap.get("expires_in") * 1000;
                    }
                    log.info("WeiXinConfig.refreshAccessToken() info. jsonMap = " + jsonMap.toString());
                } else if (jsonMap.containsKey("errcode")) {
                    // example: {"errcode":40013,"errmsg":"invalid appid"}
                    log.error("WeiXinConfig.refreshAccessToken() error. jsonMap = " + jsonMap.toString());
                    this.weixinAccessTokenStartTime = lastWeixinAccessTokenStartTime;
                } else {
                    // unknown
                    log.error("WeiXinConfig.refreshAccessToken() unknown error. jsonMap = " + jsonMap.toString());
                    this.weixinAccessTokenStartTime = lastWeixinAccessTokenStartTime;
                }
            } finally {
                accessTokenRefreshingFlag.set(false);
            }
        }
    }
```
# 系统展示
## 高大上的登录界面
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190121230006290.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3UwMTM4ODcyNTQ=,size_16,color_FFFFFF,t_70)
## 开发工具基本配置
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190121230037751.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3UwMTM4ODcyNTQ=,size_16,color_FFFFFF,t_70)
## CURD属性配置
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190121230050474.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3UwMTM4ODcyNTQ=,size_16,color_FFFFFF,t_70)
## CURD一键生成
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190121230100280.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3UwMTM4ODcyNTQ=,size_16,color_FFFFFF,t_70)
## 角色编辑
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190121230112634.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3UwMTM4ODcyNTQ=,size_16,color_FFFFFF,t_70)
## 微信设置
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190121230123443.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3UwMTM4ODcyNTQ=,size_16,color_FFFFFF,t_70)
## 微信状态查看
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190121230134865.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3UwMTM4ODcyNTQ=,size_16,color_FFFFFF,t_70)
## 树形演示实体
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190121230153870.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3UwMTM4ODcyNTQ=,size_16,color_FFFFFF,t_70)
## 管理员列表
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190121230205191.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3UwMTM4ODcyNTQ=,size_16,color_FFFFFF,t_70)
## 存储插件列表
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190121230215308.jpg)
## 二维码生成
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190121230224944.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3UwMTM4ODcyNTQ=,size_16,color_FFFFFF,t_70)
以上仅仅是部分功能，很多列表页面比较类似就不重复了。
