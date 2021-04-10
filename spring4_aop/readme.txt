对业务层StudentBizImpl增加一些功能，而不修改StudentDizImpl的原来的代码
解决方法：aop

    对业务层中 add() update() 增加一个日志，在操作前输出当前操作的时间
    对业务层中 add() update() 增加bye()，在操作后输出bye
    对业务层中 find() 增加统计运行时间()，在操作后输出执行的时长...

long start = System.currentTimeMills();

find();

long start = System.currentTimeMills();

------------------------
1、在bye中记录更详细的信息.
    目标类，目标方法，参数，返回值...