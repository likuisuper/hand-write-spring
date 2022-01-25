# hand-write-spring
手写spring，实现spring的主要功能
#### 第一部分
实现最简单的spring容器，通过new的方式将对象添加到spring容器中
#### 第二部分
在第一步的基础上实现IOC功能，将创建bean的工作交给容器（此时只能根据无参构造函数创建），
并且需要手动注册BeanDefinition
#### 第三部分
支持spring容器根据有参函数创建bean实例
#### 第四部分
支持属性填充，需要手动设置属性
#### 第五部分
实现资源加载器，从spring.xml中解析和注册bean对象，不用手动注册BeanDefinition和手动设置属性
