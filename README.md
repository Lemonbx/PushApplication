手机通知推送至电脑

此仓库为Android部分

PC仓库 <a href="https://github.com/Lemonbx/PushApplication">https://github.com/Lemonbx/PushApplication</a>

<br>

```快速入门```

首先运行pc客户端，然后使用Android Studio，连接手机，修改 `app\src\main\java\com\luoyuer\pushapplication\service\PushService.java` 第44行的url，将ip:port、username、password修改为你自己的，运行即可。最低only支持Android 8(虽然再低的版本也没啥兼容性问题，但估计也没人用了吧 摊手)

<br>

```注意```

1. 程序没有做保活，请手动去手机管家里允许后台运行
2. 不太会写安卓(摊手)，估计页面是难写出来了，希望踊跃提request(毕竟我就是一做服务端的小菜鸡
3. 请求可能会发不出来，不清楚什么问题，我太菜了呜呜呜
4. 如果愿意的话 你可以把消息推到公众号啥的(，当然你得自己写