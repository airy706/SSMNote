public class SSM {
    /*
mybatis:持久层框架，不屏蔽SQL，更为灵活精准编写SQL，
sqlSessionfactorybulider:建造者模式
sqlsessionfactory工厂模式
seqsession回话（获取mapper,发送SQL给数据库，数据库事务管理）
mapper：映射器，提供映射规则，SQL语句，动态的SQL，配置缓存，有接口和xml 映射文件组成

mybatis 配置文件:别名，Setting配置，属性配置，环境配置，映射器配置

映射器：有xml文件和接口组成
mappers
    select语句：ID，参数类型
               rueslttype （返回结果类型，使用自动映射或者驼峰映射)
               resultMap()，映射结果集，定义映射规则，级联，类型转换器等
                            构造方法
                            id 主键，对应数组表的主键
                            result 对应数据表的非主键和类的属性
                            assoication一对一级联 proper对应属性，column对应列，作为select的参数
                            collection一对多级联
                            descriminator鉴别器级联，通过一些条件确定具体的级联类  column作为条件，case作为条件的值
                            级联的延迟加载，层级加载，开启后可以在加载一个数据表信息的时候不会加载出其级联的信息。
                            或者在级联语句中设置加载属性，快加载和懒加载
                接口中输入参数（@param，多个参数用javabean，混合使用）
      insert语句：主键自动回填
      SQL元素，作为SQL语句的一部分，可以被其他语句引用

缓存：一级缓存（sqlsession）:sqlseesion获取到数据库数据后，将其缓存到sqlsession中，下一获取相同数据直接从缓存加载
      二级缓存（sqlseesionfactory）:对象要支持序列化，同一个sqlssesionfactory内共享一个缓存。





     */

    /*
    Spring

    spring IOC IOC容器，进行控制反转，能够通过注解或者描述等方式通过IOC容器来产生特定的对象。
               IOC容器管理各个BEAn和它们之间的关系，降低对象之间的耦合。

               BEeanFactory  和 appliactionContext接口
               bean的初始化和依赖注入：流程   资源定位，bean定义，将bean定义发布到容器中，初始化，依赖注入，一系列步骤（包含自定义初始化方法），
               生存期，销毁，自定义销毁方法

     依赖注入：构造器注入（有参无参构造方法），setter注入（java bean中定义无参构造方法，setter方法）均通过反射机制，接口注入（很少）
     装配bean:(1)xml装配，只要适合第三方类库
             （2）注解装配  a:组件扫描的方式，@ccomponent @componentscan(扫描对应的包，加载bean)
                            b:自动装配，按照类型寻找对应的类并装配成bean,自动装配的歧义问题：@primary（优先装配） @qualified(按名称装配)
                            c：@bean 可用于方法上，将方法的返回值作为bean
     加载属性文件：（1）使用xml文件context:property-placeholder
                   (2)使用注解方式@propertySource

     bean的作用于：@scope
                   1 单例2 原型 3会话 4请求



     springaop
     面向切面编程，动态代理模式实现（反射）
     在执行一个方法添加拦截器的功能
     切面：切点，连接点（具体拦截的方法），通知（前，后，afterreturn ,afterthrow,around,代理对象通过反射执行真实对象方法或者环绕方法之前后的通知，
     环绕通知可以取代真实对象的方法，可以通过反射回调真实方法）
     注解方式：@aspect（类上）  @pointcut（方法） @before（通知方法）等
     XML方式：aop config aspeect before等


     分页插件：github.pagehelper 获取一天的信息记录（页数，每页容量）

     mybatis配置
     mybaits-spring:配置数据源(dbcp)
                    配置sqlseesionfactory(包括数据源，mybatis配置文件（属性少的话可直接在当前配置）,分页插件等)
                    配置映射器 ：单个配置mapper mapperfacotrybean
                                 通过扫描配置多个mapper mapperscannerconfigurer(basepackage,annotationclass)

                    配置事务管理器（数据源，是否默认会回滚等）

     spring数据库事务：
        事务管理器
        声明式事务 transactional(事务管理器，传播行为，隔离级别，超时时间),
                  底层实现为动态代理，当事务不抛出异常时，由事务管理器提交事务，抛出异常由事务管理器回滚事务
                  约定流程：IOC容器初始化时，会读取配置的事务信息，然后保存到一个事务定义类中。当spring通过事务管理器创建事务时
                            ，将事务定义类中信息设置在事务。
        xml方式配置声明式事务
        注解方式配置声明式事务（@transactional）

        数据库事务：
                  A原子性：事务是不可分割的一个单位，事务的操作要么全部成功，要么全部失败
                  C一致性：事务前后数据保持一致性（一致性比如说数据的和之类）
                  I隔离性：存在并发事务时，事务之间的隔离程度
                  D持久性：事务完成后，对数据库数据的修改是持久的，不可回滚的

        隔离级别：脏读：一个事务可以读到另一个事物未提交的数据
                 提交读：一个事务只能读到另一个事务已经提交的数据（不可重复读，指针对同一个记录，事务开始和要提交的时候数据不同）
                 可重复读：使对同一个数据记录的读写操作按照一个序列来执行，保证可重复读（同时对多个纪录读写时导致幻读）
                 可序列化：对SQL按照顺序读写

                 性能和数据安全

        传播行为：方法调用之间的事务策略，一个方法调用量一个方法时，对事务的特性进行传播配置
                 required(默认)
                 requied new
                 nested(嵌套事务，当调用方法时出现异常，只回滚方法内部的sql，不回滚主方法的SQL)
        @transactionnal失效问题：1 底层为SPRINGaop,动态代理，对于静态方法和非public 方法无效
                                2 因为为动态代理，自调用时（一个类的方法调用自身的方法）无需代理对象，


        springmvc:流程：请求----diapatcherservlet----处理器映射（可以添加拦截器）-----处理器适配器-----视图与模型----dispa


        web项目配置文件：web.xml
                        配置spring IOC配置文件位置
                        配置contextLoaderListner(在web工程前后添加自己的代码，主要为IOC容器的初始化和关闭)

                        配置dispatcherServlet(加载，urlpattern)
         springmvc配置文件：拦截器链，校验器，文件上传（Multipartresolver）等
        springmvc获取参数：
                        （1）不用注解获取参数：单个参数（HTTP请求参数与方法参数相同），多个参数（建立一个POJO类对应http请求）
                        2@requestparam(参数名称)
                        通过URL获取参数，requestmapping与pathvirable配合使用
                        @requestBody()获取JSON参数
        保存并获取属性参数：
                      @requestattribute,  获取请求对象中的属性
                      @sessionattribute,获取会话对象中的属性
                      @sessionattributes,设置会话对象中的属性（设置键值对，只能对类标注）
      拦截器：作用 在处理器处理前后或者视图渲染之后进行一定的业务逻辑
             方法：prehandler，在处理器之前执行的前值方法，返回true继续执行，false则停止执行后面逻辑
                   posthandler,在处理区之后执行的后置方法
                   aftercompletion,在视图渲染之后执行的完成方法（无关有无异常）
             执行流程：
             单个拦截器：  前值方法，为真则继续执行，处理器方法，后置方法，视图渲染，完成方法
                                    为假则停止执行
             多个拦截器：（责任链模式）
                          若正常执行，则顺序为，按照配置的拦截器顺序，从第一个拦截器依次往后执行前值方法，然后执行
                                      处理器方法，然后逆序执行后置方法，视图渲染后，逆序执行完成方法
                          若执行中，一拦截器前值方法为false，则不在执行之后的拦截器前值方法，处理器方法，后置方法，按逆序执行
                                    前置方法为true的拦截器的完成方法。
        转换器：mappingjacksontohttpmessageconverte
     */
}
