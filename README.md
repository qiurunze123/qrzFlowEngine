# qrzFlowEngine
极简流程引擎框架【配置化，节点话，原子化，自闭环处理】
【有思路的我三天就可以一顿爆写哈哈】
1.目标
1. 简单便捷、轻量级的配置能力，易于上手。
2. 低代码设计，组件化封装。
3. 流程编排，快速支持多场景迭代。
4. 抽出业务原子能力，进行数据解耦与迭代，能够支撑供应商入驻链路整体拆解能力

极简流程引擎框架【配置化，节点话，原子化，自闭环处理】
自己开发了版极简流程引擎框架，已经完成了，但是还没梳理完，慢慢更新！

1.符合业务要求，可承接流程原子
2.处理各节点闭环结果表达式
3.处理最终结果表达式
4.可扩展可各类型流程
5.原子能力可复用
6.流程配置化
7.具备重试&持久化&回放能力
8.轻量级引擎，用起来爽方便快捷

流程节点模块
SNode节点信息 
● 负责每个节点信息处理与描述
● ReturnHandler 结果动作组件  【异常处理】【结果处理】
QLNode节点 
● 负责QL节点信息描述
● 最终路由组件处理 qlExpress 表达式处理
SFlow流程类
● 负责流程信息描述 
● SNode节点与QLNode节点处理
流程注册模块
SFlowConfig 配置文件信息
● Diamond 配置文件处理
SFlowInstance 流程实例类
● 流程类
● 流程状态
SFlowRegistry
● Flow流程注册
● 创建流程实例对象
组件注册模块
SContext流程上下文基础
● 流程上下文基础信息
● 流程实例信息
● QL表达结果
XX-SContext场景流程上下文基础
● 携带某一种场景-整体流程参数信息
SComponent组件接口
● 原子节点组件实现接口
●  SComponent<T extends SContext> extends InitializingBean  
● 组件实例化SComponent
SComponentRegistry  组件注册器
● 原子节点组件注册
执行器模块
InstanceExecutor 实例执行器
● 实例Node节点执行
● 实例QLNode节点执行
SFlowEngine flow执行引擎
● Flow-Node 节点执行引擎
● Flow-QLNode节点执行引擎

![【供应商入驻引擎领域模型大而全】 drawio](https://github.com/qiurunze123/SFlowEngine/assets/22807361/ac0bdb2a-f4f6-4a26-bc21-28f415d50411)

![【配置中心】 drawio](https://github.com/qiurunze123/imageall/blob/master/%E9%85%8D%E7%BD%AE%E7%A9%BA%E9%97%B4diamond.png)
![【图2】 drawio](https://github.com/qiurunze123/imageall/blob/master/%E9%85%8D%E7%BD%AE%E7%A9%BA%E9%97%B4diamond.png)
![【图3】 drawio](https://github.com/qiurunze123/imageall/blob/master/flow%E5%AE%8C%E6%95%B4%E6%B5%81%E7%A8%8B.png)
