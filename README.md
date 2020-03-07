# struts1.x
2020年1-5月 对日K公司制造、保守项目
1月：公司/商户/仓库 管理员后台页面设计 JSP设计
2月：回环测试页面数据域渲染、出入力测试
3月：数据库存储过程开发PLSQL
4月：功能接口开发，Excel-CSV出力、Ajax回调功能、功能测试
5月上线——K促销活动页面

lastest 1.3
備考欄

<HR style="FILTER: alpha(opacity=100,finishopacity=0,style=1)" width="80%" color=#987cb9 SIZE=3>
  [参考で](https://www.genuitec.com/docs/web-apps/java-ee-web/creating-web-application-struts-1-x/ )

   
   1. 使用任意一种服务器即可 </p>生产环境服务器统一由Docker中Weblogic集中部署</p>
   2. 开发环境服务器TomEE or TomCat 如加入EJB则统一用 JBoss(TomXX系列不支持EJB)</p>
   3. myEclipse：用于生成Action与From</p>
   4. IDEA：用于Test及最终部署</p>
   5. 数据库统一Oracle12c版本
   6. 数据库连开发环境JDBC 生产后改用 JNDI
   7. CRUD事务用存储过程创建
   8. SQL涉及多表联查、带参函数功能
   9. 业务使用后记得关闭并返还信息区分
   10. 页面：欢迎页、登录、注册、库存管理、出入库、订单打印、Excel报表生成、PDF报表生成
