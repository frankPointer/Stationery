# 准备工作
在包 preparation 中类都是写项目之前或者写项目的时候所参考的类
## emp 包
emp 包就是我用来测试使用 JDBC 对数据库中的一个表来进行增删改查而写的
但是没有完全写完，里面的功能写了一半之后就没有再写了
## gui 包
这个包里面的都是关于图形化界面的一下准备工作
其中 **DBTableDemo** 类就是将一个表格的数据使用 swing 中的 JTable 展示出来
![](https://picgo-1314080015.cos.ap-nanjing.myqcloud.com/PIctures/202309052020214.png)
如果想要写图形化界面的话这个应该是必不可少要了解的

**BoxTest** 是一个我找到的一个可以快速添加组件的方法

**AdjustingWidth** 是JTable 的一个功能，还没研究透彻

**BGPanel** 是将图片在JFrame的panel层面画出来，可以很方便的添加背景

# 正式项目
**Application** 是一个启动类
**ui** 里面的**ApplicationMainInterface** 是登录界面的Frame基本界面如下
![](https://picgo-1314080015.cos.ap-nanjing.myqcloud.com/PIctures/202309052025103.png)
**包 constants** 是用来存储一些常量的类