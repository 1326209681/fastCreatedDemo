<html>
<head>
    <title>demo</title>
    <meta charset="utf-8">

</head>
<#-- freemarker注释：我是一个注释，不会输出-->
<!-- html注释 我会输出-->
<body>
<#--FTL指令-->
<#--FTL指令 1. include指令-->
<#include "head.ftl">

<#-- 插值 -->
${name},你好。${message}<br/>

<#--FTL指令2.  assign指令-->
<#assign linkman="周先生">
${linkman}  <br/>

<#--FTL指令 3. if 指令-->
<#if success=true>
您已通过实名认证
<#else>
您未通过审核
</#if><br/>

<#--FTL指令 4.  list指令 -->
-----商品列表--------<br/>
<#list goodsList as goods>
    ${goods_index+1} 商品名称：${goods.name}商品价格：${goods.price}<br/>
</#list>

<#-- 内建函数-->

<#--内建函数 1.获取集合长度 -->
一共${goodsList?size} 条记录         <br/>

<#--内建函数 2.将JSON字符串转换为对象-->
<#assign text="{'bank':'招商银行','account':'546135615464'}">
<#assign data=text?eval>
开户行：${data.bank}  卡号：${data.account}         <br/>


<#--内建函数 3.日期格式化-->
当前日期：${today?date}   <br/>
当前时间：${today?time}  <br/>
当前日期+时间： ${today?datetime}      <br/>
自定义日期：${today?string("yyyy年MM月dd日 HH:mm:ss")}       <br/>

<#--内建函数 4. 数字转为字符串-->
<#--显示普通数字-->
累计积分：${point}    <br/>
<#--显示字符串数字-->
累计积分：${point?c}


<#--运算符-->
<#--1.空值运算符-->
<#if aaa??>
aaa存在: ${aaa}
<#else >
aaa不存在
</#if>

<#-- 2. 缺少变量默认值： ！-->
${bbb!"bbb不存在"}     <br/>

<#--3. 运算符举例-->
<#if (100>10)>
100肯定比10大啊
</#if>      <br/>

</body>
</html>