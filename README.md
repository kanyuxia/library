## Library

该库主要是自己写代码的基础库。



### 统一Maven依赖

自己在之前写Java项目中发现由于各个依赖Jar包的不统一造成代码的不一致、依赖杂乱，严重的可能导致错误。所以，通过统一Maven依赖，把基础的依赖版本定为一致。

```xml
<spring.boot.version>1.5.8.RELEASE</spring.boot.version>
<commons-lang3.version>3.6</commons-lang3.version>
<java.version>1.8</java.version>
<guava.version>23.0</guava.version>
<fastjson.version>1.2.44</fastjson.version>
<druid.version>1.1.9</druid.version>
<druid.spring.starter.version>1.1.9</druid.spring.starter.version>
<mybatis.version>3.4.5</mybatis.version>
<mybatis.spring.version>1.3.1</mybatis.spring.version>
<mybatis.spring.starter.version>1.3.1</mybatis.spring.starter.version>
<shiro.web.version>1.4.0</shiro.web.version>
<shiro.spring.version>1.4.0</shiro.spring.version>
<shiro.cache.version>1.4.0</shiro.cache.version>
<shiro.ehcache.version>1.4.0</shiro.ehcache.version>
<netty.version>4.1.16.Final</netty.version>
```

统一项目相关Jar包，包括抽象日志sl4j、简化代码lombok（需要Lombok插件支持）、测试junit

```xml
<dependencies>
  <dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>jcl-over-slf4j</artifactId>
  </dependency>
  <dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>jul-to-slf4j</artifactId>
  </dependency>
  <dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>log4j-over-slf4j</artifactId>
  </dependency>
  <dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
  </dependency>
  <dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <scope>test</scope>
  </dependency>
</dependencies>
```

统一项目编译、打包插件

```xml
<build>
  <plugins>
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-compiler-plugin</artifactId>
      <version>3.7.0</version>
      <configuration>
        <source>${java.version}</source>
        <target>${java.version}</target>
        <encoding>UTF-8</encoding>
        <optimize>true</optimize>
        <useIncrementalCompilation>false</useIncrementalCompilation>
      </configuration>
    </plugin>
    <plugin>
      <artifactId>maven-source-plugin</artifactId>
      <version>3.0.1</version>
      <configuration>
        <attach>true</attach>
      </configuration>
      <executions>
        <execution>
          <phase>package</phase>
          <goals>
            <goal>jar-no-fork</goal>
          </goals>
        </execution>
      </executions>
    </plugin>
  </plugins>
</build>
```



## util

该库主要是基础代码，主要为了方便使用而编写的代码，该代码有一部分为公司相关Jar包。该库主要有下面一些常用组件：

* 编解码：	主要有MD5、SHA1、SHA256、AES的编解码，其都是通过JDK自带的编解码工具实现。
* IO：目前IO只涉及到CloseUtils，主要是为了关闭IO流、简化代码和增加代码美感。
* JDBC：目前只有DriverUtils，主要通过DriverManager方式获取Conection。注意，需要在classpath目录下加入db.properties文件，文件中需要加入driver、url、username、password属性。
* lang：该包下主要有IdCardUtils、RandomUtils、RegexpUtils三种工具类，从名字可以看出来主要是身份证验证、生成随机数、常用正则验证（密码、邮箱等，可能这里有点不太优雅是密码等位数固定）
* web：该包下主要是开发Java Web相关工具类，把常用方法进行简单的封装以简化开发。主要y有CookieUtils、IpUtils、JsonUtils、RequestUtils、ResponseJson、ResponseUtils，其中Json使用的是Jackson进行编解码操作。

注意：上诉工具部分来自公司，侵删。



## Git规范 

该Git规范主要是公司使用，这里自己拿过来主要为了放在这里，以后自己在提交代码时也使用该规范。

```
feat:新增feature,组件升级
fix:修复bug
docs:仅仅修改了文档，比如README, CHANGELOG, CONTRIBUTE等等
style:仅仅修改了空格、格式缩进、都好等等，不改变代码逻辑
refactor:代码重构，没有加新功能或者修复bug
perf:优化相关，比如提升性能、体验
test:测试用例，包括单元测试、集成测试等
chore:改变构建流程、或者增加依赖库、工具等
revert:回滚到上一个版本
```

