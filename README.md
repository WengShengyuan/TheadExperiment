# TheadExperiment
线程池特性检验以及一种可配置的线程类的实现

## 线程池特性检验（APP.java）

## 可配置的线程池以及线程行为回调接口

1、可配置线程`ConfigurableThread.java `

可指定：最大执行次数，最长执行时间，循环体内函数重写，链式配置特性。

2、线程行为回调接口`Callable.java`

规范可配置线程子类的行为：超时、超过执行次数、执行成功、线程异常。
