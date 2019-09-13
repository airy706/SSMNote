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

     */
}
