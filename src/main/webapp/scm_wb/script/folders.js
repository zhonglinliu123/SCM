///////////////////////////////////////////////////////////////// INIT ////////////////////////////////////////////////////////////

//  Init!

var OutlookBar={
"format":
   {"heightpanel":25, "imagewidth":50, "imageheight":50, "arrowheight":17,"heightcell":100,"coloroutlook":"#DBEAF5","arrange_text":"bottom", "rollback":false, "img_arrows_up":"/SCM/scm_wb/images/pic/arup2.gif","img_arrows_dn":"/SCM/scm_wb/images/pic/ardn2.gif"},
"panels":
[
   {"text":"系统管理", "panelCSS":"panel" ,"textCSS":"textpanel",
       "items":
       [
          {"text":"用户管理","textCSS":"a1", "image":'/SCM/scm_wb/images/pic/1.gif',    "url":'/SCM/scm_wb/system_manage/usermanage.jsp', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"},
          {"text":"重新登陆","textCSS":"a1","image":'/SCM/scm_wb/images/pic/2.gif',    "url":'/SCM/scm_wb/system_manage/relogin.html', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"},
          {"text":"退出系统","textCSS":"a1", "image":'/SCM/scm_wb/images/pic/3.gif',    "url":'/SCM/scm_wb/system_manage/leave.html', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"}
          
      ]
   },
    {"text":"采购管理", "panelCSS":"panel" ,"textCSS":"textpanel",
       "items":
       [
          {"text":"供应商管理","textCSS":"a1", "image":'/SCM/scm_wb/images/pic/1.gif',    "url":'/SCM/scm_wb/purchase_manage/supplier_manage.jsp', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"},
          {"text":"添加采购单","textCSS":"a1","image":'/SCM/scm_wb/images/pic/2.gif',    "url":'/SCM/scm_wb/purchase_manage/add_purchase_order.jsp', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"},
          {"text":"采购单了结","textCSS":"a1", "image":'/SCM/scm_wb/images/pic/3.gif',    "url":'/SCM/scm_wb/purchase_manage/purchase_order_end.jsp', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"},
          {"text":"采购单查询","textCSS":"a1", "image":'/SCM/scm_wb/images/pic/4.gif',    "url":'/SCM/scm_wb/purchase_manage/purchase_order_search.jsp', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"},
          
      ]
   },
   {"text":"仓储管理", "panelCSS":"panel" ,"textCSS":"textpanel",
       "items":
       [
          {"text":"入库登记","textCSS":"a1", "image":'/SCM/scm_wb/images/pic/8.gif',    "url":'/SCM/scm_wb/storage_manage/in_storage_record.jsp', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"},
          {"text":"出库登记","textCSS":"a1", "image":'/SCM/scm_wb/images/pic/9.gif',    "url":'xxwh/khxx.htm', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"},
		  {"text":"库存盘点","textCSS":"a1", "image":'/SCM/scm_wb/images/pic/9.gif',    "url":'gztm/count.htm', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"},
          {"text":"库存查询","textCSS":"a1", "image":'/SCM/scm_wb/images/pic/10.gif',    "url":'xxwh/ccht.htm', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"}
      ]
   },
   {"text":"财务收支", "panelCSS":"panel" ,"textCSS":"textpanel",
       "items":
       [
          {"text":"收款登记","textCSS":"a1", "image":'/SCM/scm_wb/images/pic/11.gif',    "url":'gztm/inMoney.htm', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"},
          {"text":"付款登记","textCSS":"a1", "image":'/SCM/scm_wb/images/pic/12.gif',    "url":'xxcx/splb.htm', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"},
          {"text":"收支查询","textCSS":"a1", "image":'/SCM/scm_wb/images/pic/13.gif',    "url":'xxcx/dlqy.htm', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"}
         
      ]
   }
   ,
   {"text":"销售管理", "panelCSS":"panel" ,"textCSS":"textpanel",
       "items":
       [
          {"text":"产品分类管理","textCSS":"a1", "image":'/SCM/scm_wb/images/pic/11.gif',   "url":'/SCM/storeCan/GoodsClassServlet_c', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"},
          {"text":"产品管理","textCSS":"a1", "image":'/SCM/scm_wb/images/pic/12.gif',    "url":'/SCM/storeCan/GoodsServlet_c', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"},
          {"text":"客户管理","textCSS":"a1", "image":'/SCM/scm_wb/images/pic/13.gif',    "url":'/SCM/storeCan/CustomerShowServlet_c', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"},
          {"text":"新添销售单","textCSS":"a1", "image":'/SCM/scm_wb/images/pic/13.gif',    "url":'/SCM/storeCan/SomainShowServlet_c', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"},
		  {"text":"销售单了结","textCSS":"a1", "image":'/SCM/scm_wb/images/pic/13.gif',    "url":'/SCM/storeCan/somainOver.jsp', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"},
		  {"text":"销售单查询","textCSS":"a1", "image":'/SCM/scm_wb/images/pic/13.gif',    "url":'/SCM/storeCan/selectSomain.jsp', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"}
         
      ]
   }
   ,
    {"text":"业务报表", "panelCSS":"panel" ,"textCSS":"textpanel",
       "items":
       [
          {"text":"月度采购报表","textCSS":"a1", "image":'/SCM/scm_wb/images/pic/11.gif',    "url":'xxcx/spxx.htm', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"},
          {"text":"月度收支登记表","textCSS":"a1", "image":'/SCM/scm_wb/images/pic/12.gif',    "url":'xxcx/splb.htm', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"},
          {"text":"月度入库报表","textCSS":"a1", "image":'/SCM/scm_wb/images/pic/13.gif',    "url":'xxcx/dlqy.htm', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"},
		  {"text":"月度出库报表","textCSS":"a1", "image":'/SCM/scm_wb/images/pic/13.gif',    "url":'xxcx/dlqy.htm', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"},
		  {"text":"产品库存报表","textCSS":"a1", "image":'/SCM/scm_wb/images/pic/13.gif',    "url":'xxcx/dlqy.htm', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"},
		  {"text":"月度销售报表","textCSS":"a1", "image":'/SCM/scm_wb/images/pic/13.gif',    "url":'/SCM/storeCan/salesReport.jsp', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"}
      ]
   }
   
   ,
     {"text":"网上销售", "panelCSS":"panel" ,"textCSS":"textpanel",
       "items":
       [
          {"text":"客户注册","textCSS":"a1", "image":'/SCM/scm_wb/images/pic/11.gif',    "url":'xxcx/spxx.htm', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"},
          {"text":"商品展示","textCSS":"a1", "image":'/SCM/scm_wb/images/pic/12.gif',    "url":'xxcx/splb.htm', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"},
          {"text":"网上下单","textCSS":"a1", "image":'/SCM/scm_wb/images/pic/13.gif',    "url":'xxcx/dlqy.htm', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"},
		  {"text":"月度出库报表","textCSS":"a1", "image":'/SCM/scm_wb/images/pic/13.gif',    "url":'xxcx/dlqy.htm', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"},
		  {"text":"产品库存报表","textCSS":"a1", "image":'/SCM/scm_wb/images/pic/13.gif',    "url":'xxcx/dlqy.htm', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"},
		  {"text":"月度销售报表","textCSS":"a1", "image":'/SCM/scm_wb/images/pic/13.gif',    "url":'xxcx/dlqy.htm', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"}
      ]
   }
]
}
