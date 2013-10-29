<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/function/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>列表</title>
<link rel="stylesheet" type="text/css" href="${prc}/function/js/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="${prc}/function/js/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="${prc}/function/css/gzt.css" type="text/css"></link>
<link rel="stylesheet" type="text/css" href="${prc}/function/css/index.css" type="text/css"></link>
<script type="text/javascript" src="${prc}/function/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${prc}/function/js/jquery.form.js"></script>
<script type="text/javascript" src="${prc}/function/js/jquery.json-2.4.min.js"></script>
<script type="text/javascript" src="${prc}/function/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${prc}/function/js/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${prc}/function/js/datePicker/WdatePicker.js"></script>
<script type="text/javascript">
        $(function () {
            $("table tr:odd").css("background", "#f0f8fc");
            $("table tr:even").css("background", "#d5e0ee");
            $("table tr").mouseover(function () {
                $(this).css("color","#f00");
            });
            $("table tr").mouseleave(function () {
                $(this).css("color", "#000");
            });
            $(".cx1").click(function(){
            	$.post("toAdd_subject.action",{moduleId : "${moduleId}" },function(data){
            		if(data != ''){
            			$("#addsubjectDiv").html(data);
            			$(".bg").css("display", "block");
                        $(".add").css("display", "block");
            		}
          	  });
               
            });
            $("#edit").click(function(){
            	$.post("getById_subject.action",function(data){
            		if(data != ''){
            			$("#addsubjectDiv").html(data);
            			$(".bg").css("display", "block");
                        $(".add").css("display", "block");
            		}
          	  });
            });
            
            $(".add-top img").click(function () {
                $(".bg").css("display", "none");
                $("#add").css("display", "none");
            });
        });
       
        //查询
        function matteredit(obj){
        	$.post("getById_subject.action?id="+obj,function(data){
        		if(data != ''){
        			$("#addsubjectDiv").html(data);
        			$(".bg").css("display", "block");
                    $(".add").css("display", "block");
        		}
      	  });
        }
      
        //异步新建提交
    	function matterSubmit(){
    		if(!doValid()){return;};
    		isSubjectExist();
    	}	
    	function on_delete(subjectId){
    		if(confirm("确定要删除吗?")){
    			$.post("delete_subject.action", { id: subjectId},
    				function(data){
    					var dateObj=$.parseJSON(data);
    					if(0==dateObj.result){
    						document.location.href="find_subject.action?moduleId=${moduleId}";
    					}else{
    						alert("删除失败");
    					}
    			});
    		}
    	}
    	
    	//验证主题重名
    	function isSubjectExist(){
    		//var url ="isExist_subject.action";
    	//	var subjectTile = $("input[name='subject.title']").val();
    	//	$.post(url,{subjectTitle:subjectTitle},function(data){
    			//	if(data.result == 0){
    						doSave();
    		//		}else{
    		//			alert(data.msg);
    		//		};
    		//	},"json");
    	}
    	
    	function doSave(){
        	var users = $("#cc").combotree('getValues');
    		$("#saveForm").ajaxSubmit({
    			url:"add_subject.action",
    			data: {executeUsers:$("#cc").combotree('getText'),executeUsersId:users.toString()},
    			success:function(data){
    				var dateObj=$.parseJSON(data);
    				alert((0==dateObj.result)?"保存成功!":"保存失败!");
    				document.location.href="find_subject.action?moduleId=${moduleId}";
    			}
    		});
    	}
    function matterQuery(){
    	$("#form").submit();
    }
</script>
</head>
<body>
<form action="find_Subject.action" id="form" method="post">
<div class="con_conent fixed">
     <h1 class="title"><span class="title">当前位置：</span><span class="text">首页　-　<a href="${prc}/scMaster2/teacherInput_index.action">校长工作台</a>　-　</span><span class="back">临时事项</span></h1>
        <div class="table_box fixed">
            <div class="nav">
                <span>提交日期:</span>
                <select id=""></select>
                <span>事项标题:</span>
                <input type="text" id="" name="subject.title" />
                <a class="cx" href="javascript:matterQuery();">查询</a>
                <a class="cx1" href="javascript:void(0);">增加</a>
            </div>
        <table width="100%" border="0">
            <tr>
                <th width="5%" scope="col">序号</th>
                <th width="30%" scope="col">标题</th>
                <th width="20%" scope="col">创建日期</th>
                <th width="10%" scope="col">创建人</th>
                <th width="15%" scope="col">所属部门</th>
                <th width="20%" scope="col">操作</th>
            </tr>
            <c:forEach items="${pm.datas}" var="sub" varStatus="i">
            <tr>
                <td align="center">${i.index+1 }</td>
                <td align="center">${sub.title }</td>
                <td align="center">${sub.createTime }</td>
                <td align="center">${sub.createrId}</td>
                <td align="center">${sub.id}</td>
                <td align="center">
                <span class="space"><a href="reportDetailed_subject.action?id=${sub.id }">查看</a></span>
                <span class="space" id="cx2"><a href="javascript:matteredit('${sub.id }')" id="edit">编辑</a>
                </span><span class="space"><a href="javascript:void(0);" onclick="on_delete('${sub.id}')">删除</a></span></td>
            </tr>
            </c:forEach>
        </table>
    	<c:if test="${pm.total>0}">
    		   <div  style="text-align:center;font-size:15px;margin-top:20px;">
        		<pg:pager url="${prc}/scMaster2/listMasterStatistics_data.action"
					items="${pm.totalPageNo}" maxPageItems="${pm.totalPageNo}" maxIndexPages="3" export="currentPageNumber=pageNumber">
				<pg:param name="start" value="${start}"/>
				<pg:param name="end" value="${end}"/>
				<pg:param name="matcher" value="${matcher}"/>
				总计${pm.total}条
				<pg:first>
					<a href="${pageUrl}">首页</a>
				</pg:first>
				<pg:prev>
					<a href="${pageUrl}" >上一页</a> 
				</pg:prev>
				<pg:pages>
					<c:choose>
						<c:when test="${currentPageNumber eq pageNumber}">
							<font color="red">${pageNumber}</font>
						</c:when>
						<c:otherwise>
							<a href="${pageUrl}">${pageNumber }</a>
						</c:otherwise>
					</c:choose>
				</pg:pages>
				<pg:next>
					<a href="${pageUrl}" >下一页</a> 
				</pg:next>
				<pg:last>
					<a href="${pageUrl}">尾页</a>
				</pg:last>
			</pg:pager>
			</div>
		</c:if> 
		</div>
    </div>
           <!--弹出层-->
    <div class="bg"></div>
      <div id="addsubjectDiv" >
     
       </div>
     </form>
     <script>
      function doValid(){
		 if($("#addSubjectInput").val()==''){
			 alert("请添加主题");
				return false;
		 }
    	  var text = $("#textContent").val();
    	  if(text.length==0){
    		  alert("请填写简介！");
    		  return false;
    	  }
    	  return true;
      }
 
   </script>
</body>
</html>