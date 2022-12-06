package com.sdq.libs.base.android

/**
 *
 * @PackageName com.sdq.libs.base.android
 * @date 2022/12/6 9:15
 * @author songdongqi
 */
class HandlePrinciple {
    /**
     * Linux 五大I/O模型
     * 1、阻塞
     * 2、非阻塞
     * 3、I/O多路复用——select、poll、epoll
     * 在 Linux 中，任何可以进行 I/O 操作的对象都可以看做是流，一个 文件， socket， pipe，我们都可以把他们看作流。
     * 一个典型的例子为，客户端要从 socket 中读数据，但是服务器还没有把数据传回来，这时候该怎么办？
     * 阻塞： 线程阻塞到 recv() 方法，直到读到数据后再继续向下执行。
     * 非阻塞：recv() 方法没读到数据立刻返回 -1 ，用户线程按照固定间隔轮询 recv() 方法，直到有数据返回。
     * 能够让用户程序拥有 “同时监听多个流读写事件” 的机制，就被称为 I/O 多路复用
     */


    /**
     *  epoll 提供的三个函数：
     *  int epoll_create(int size);
     *  int epoll_ctl(int epfd, int op, int fd, struct epoll_event *event);
     *  int epoll_wait(int epfd, struct epoll_event *events, int maxevents, int timeout);

     *  • epoll_create() 用于创建一个 epoll 池。
     *  • epoll_ctl() 用来执行 fd 的 “增删改” 操作，最后一个参数 event 是告诉内核 需要监听什么事件。还是以网络
     *  请求举例， socketfd  监听的就是 可读事件，一旦接收到服务器返回的数据，监听 socketfd 的对象将会收到 回调
     *  通知，表示 socket 中有数据可以读了。
     *  • epoll_wait() 是 使用户线程阻塞 的方法，它的第二个参数 events 接受的是一个 集合对象，如果有多个事件同
     *  时发生，events 对象可以从内核得到发生的事件的集合。
     */

    /**
     * Linux eventfd
     * eventfd 是专门用来传递事件的 fd ，它提供的功能也非常简单：累计计数。
     * int efd = eventfd();
     * write(efd, 1);//写入数字1
     * write(efd, 2);//再写入数字2
     * int res = read(efd);
     * printf(res);//输出值为 3

     * 通过 write() 函数，我们可以向 eventfd 中写入一个 int 类型的值，并且，只要没有发生 读 操作，eventfd 中
     * 保存的值将会一直累加。
     * 通过 read() 函数可以将 eventfd 保存的值读了出来，并且，在没有新的值加入之前，再次调用 read() 方法会发生
     * 阻塞，直到有人重新向 eventfd 写入值。
     * eventfd 实现的是计数的功能，只要 eventfd 计数不为 0 ，那么表示 fd 是可读的。再结合 epoll 的特性，我们可
     * 以非常轻松的创建出 生产者/消费者模型。
     * epoll + eventfd 作为消费者大部分时候处于阻塞休眠状态，而一旦有请求入队（eventfd 被写入值），消费者就立刻
     * 唤醒处理，Handler 机制的底层逻辑就是利用 epoll + eventfd。
     */

    /**
     *  MessageQueue
     *  1、nativeInit() 初始化消息队列
     *  /system/core/libutils/Looper.cpp
     *  class looper {
     *      Looper::Looper() {
     *          int mWakeEventFd = eventfd();//来监听 MessageQueue 是否有新消息加入
     *          rebuildEpollLocked();
     *      }
     *      void rebuildEpollLocked(){
     *          int mEpollFd = epoll_create();//哎，这儿非常重要，在 Looper 初始化时创建了 epoll 对象
     *          epoll_ctl(mEpollFd, EPOLL_CTL_ADD, mWakeEventFd, & eventItem);//把用于唤醒消息队列的eventfd 添加到 epoll 池
     *      }
     *  }
     *
     * 2、nativePollOnce() 消息的循环与阻塞
     * Looper#loop()
     * -> MessageQueue#next()
     * -> MessageQueue#nativePollOnce()
     * -> NativeMessageQueue#pollOnce() //注意，进入 Native 层
     * -> Looper#pollOnce()
     * -> Looper#pollInner()
     * -> epoll_wait()
     *
     *  3、nativeWake()消息的分送与唤醒。
     *  /system/core/libutils/Looper.cpp
     *  class looper {
     *      void Looper::wake() {
     *          int inc = 1;
     *          write(mWakeEventFd, &inc);
     *      }
     *  }
     */

//    https://mp.weixin.qq.com/s/4gIXQ0YRNDBbPaaGTzfXCA
}