# Dao类说明文档

<blockquote>
    Dao类是访问数据库数据的操作类，你可以把一些你寄希望于在数据库上的操作放在这里，用的时候只要在这里调用即可，这样可以大大减少重复代码的定义，而且调用及其简单。
</blockquote>

## DBUtil

数据库工具类，在DBUtil中配置好了数据库的URL，默认的数据库账户密码(最高权限)

通过DBUtil的对象调用getConn可以返回对应数据库的连接(打开连接)

## BaseDao

所有Dao类的父类

在DBUtil已经配置连接好数据库的基础上，在打开的连接里创建Statement, PreparedStatement, CallableStatement 

用于接受SQL语句

exeQuery, exeProcedure, exeUpdate

查询，更新数据库，返回结果集

## 使用示例

