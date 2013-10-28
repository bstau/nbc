<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>
<%@ taglib prefix="invStatus" uri="InvStatus.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	
	<title>邀请查看 教师列表</title>
	
	<link href="${prc}/function/function-invatition/css/index.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${prc}/function/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="${prc}/function/js/jqui.js"></script>
	<script type="text/javascript" src="${prc}/function/kindeditor-4.1.5/kindeditor-min.js" ></script>
	<script type="text/javascript" src="${prc}/function/kindeditor-4.1.5/lang/zh_CN.js"></script>
	
	<link type="text/css" href="${prc}/function/swfupload/css/default.css" rel="stylesheet"/>
<script type="text/javascript" src="${prc}/function/swfupload/js/swfupload.js"></script>
<script type="text/javascript" src="${prc}/function/swfupload/js/fileprogress.js"></script>
<script type="text/javascript" src="${prc}/function/swfupload/js/handlers.js"></script>
<script type="text/javascript" src="${prc}/function/swfupload/js/swfupload.queue.js"></script>
	
	<script type="text/javascript">
    $(function () {
    	
        $("table tr:odd").css("background", "#f0f8fc");
        $("table tr:even").css("background", "#d5e0ee");
        
        $(".cx1").click(function () {
            $(".bg").css("display", "block");
            $(".add1").css("display", "block");
        });
        
        $(".add-top1 img").click(function () {
            $(".bg").css("display", "none");
            $(".add1").css("display", "none");
        });
     
        $(".download").click(function () {
            $(".bg").css("display", "block");
            $(".add-load").css("display", "block");
        });
        
        $(".add-loadtop1 img").click(function () {
            $(".bg").css("display", "none");
            $(".add-load").css("display", "none");
        });
        
        $(".lian").click(function () {
            $(".plans").css("display", "block");
            $(".plan").css("display", "none");
            $(".password").css("display", "none");
        });
        
        $(".wen").click(function () {
            $(".plan").css("display", "block");
            $(".password").css("display", "block");
            $(".plans").css("display", "none");
        });
    });
    
</script>

<script type="text/javascript">
	KindEditor.ready(function(K) {
	editorCourseContent = K.create('textarea[name="detailContent"]', {
		resizeType : 0,
		allowFileManager : false,
		allowImageUpload : true,
		uploadJson : '${prc}/scMaster2/upload.action',
		items : [
				'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
				'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
				'insertunorderedlist', 'link', '|', 'insertfile', 'image']
		});
	});
</script>


<script type="text/javascript">
	var swfu;
	window.onload = function() {
		var settings = {
			flash_url : "${prc}/function/swfupload/js/swfupload.swf",
			upload_url: "${prc}/scMaster2/swfUpload.action",	
			file_post_name: "exl",   
			file_size_limit : "20 MB",
			file_types : "*.*",
			file_types_description : "",
			file_upload_limit : 20,
			file_queue_limit : 1,
			custom_settings : {
				progressTarget : "fsUploadProgress"
			},
			debug: false,
			// Button settings
			button_image_url: "${prc}/function/swfupload/images/TestImageNoText_65x29.png",
			button_width: "60",
			button_height: "24",
			button_placeholder_id: "spanButtonPlaceHolder",
			button_text: '<span class="">浏览文件</span>',
			button_text_style: ".theFont { font-size: 12;text-align:center;color:#ffffff;}",
			button_text_top_padding: 2,
			
			// The event handler functions are defined in handlers.js
		    file_queued_handler : fileQueued,
			file_queue_error_handler : fileQueueError,
			upload_start_handler : uploadStart,
			upload_progress_handler : uploadProgress,
			upload_error_handler : uploadError,
			upload_success_handler : uploadSuccess,
			queue_complete_handler : queueComplete,
			use_query_string : true
		};
		swfu = new SWFUpload(settings);
	};
	function uploadSuccess(){
		alert('上传成功');
		window.location.href='#';//TODO
	}
	function uploadError(){
		alert('上传失败');
		window.location.href='#';//TODO
	}

</script>
</head>
<body>
	<div class="con_conent fixed">
		<h1 class="title">
			<span class="title">当前位置：</span>
			<span class="text">首页 - </span>
			<span class="back">给校长发出的邀请</span>
		</h1>
		<div class="table_box fixed">
			<div class="nav">
				<span>提交日期:</span>
				<select> </select> 
				<span>事项标题:</span> 
				<input type="text" />
				<a class="cx" href="#">查询</a>
				<a class="cx1" href="#">增加</a>
			</div>
			<table width="100%" border="0">
				<tr>
					<th width="25%" scope="col">标题</th>
					<th width="25%" scope="col">发布时间</th>
					<th width="25%" scope="col">状态</th>
					<th width="25%" scope="col">操作</th>
				</tr>
				<c:forEach items="${pm.datas }" var="subject">
					<tr>
						<td>
							<c:out value="${subject.title}" escapeXml="true"></c:out>
						</td>
						<td>
							<fmt:formatDate value="${subject.createTime}" pattern="yyyy-MM-dd" />
						</td>
						<td><invStatus:showStatus statusId="${subject.status}" /></td>
						<td>
							<span class="space">发布</span>
							<span class="space">查看</span>
							<span class="space download">
								<a href="#">下载</a>
							</span>
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td>东兴杯论文一等奖</td>
					<td>2013-4-23</td>
					<td>已发布</td>
					<td>
						<span class="space">发布</span>
						<span class="space">查看</span>
						<span class="space download">
							<a href="#">下载</a>
						</span>
					</td>
				</tr>
				<tr>
					<td>全国优秀校长论坛讲稿</td>
					<td>2013-4-23</td>
					<td>已发布</td>
					<td>
						<span class="space">发布</span>
						<span class="space">查看</span>
						<span class="space">附件</span>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<!--新增-->
	<div class="bg"></div>
	
	<div class="add1">
		<div class="add-top1">
			<p>新增</p>
			<img src="${prc}/function/img/erro.jpg" class="close" style="cursor: pointer;" />
		</div>
		<div class="add-down">
			<p class="tit">
				<span>标题名称：</span>
				<input type="text" value="东兴杯论文一等奖" />
			</p>
			<div class="tit1">
				<p>事件详情：</p>
				<textarea name="detailContent" style="width:500px;height: 200px;"></textarea>
			</div>
			<p class="tit2">
				附件/链接：
				<span>
					<a href="#" class="wen">
						<input type="radio" name="a2" />文件
					</a>
				</span>
				<span>
					<a href="#" class="lian">
						<input type="radio" name="a2" />链接
					</a>
				</span>
			</p>
			
			<div class="tit3 plan">
				<div style="margin-left:50px; maroverflow:auto;overflow-x:hidden;">
                	<div style="height: 20px;width:500px;" class="fieldset flash" id="fsUploadProgress">
                	</div>
                </div>
			</div>
			
			<div class="password" id="divMovieContainer" >
				<span id="spanButtonPlaceHolder" ></span>
				<input id="btnUpload1" type="button" value="开始上传" onclick="swfu.startUpload()" 
				style="width:66px; height:26px;font-size:12px;background: url(${prc}/function/img/btnbg.jpg) no-repeat" />
			</div>
			
			<div class="tit3 plans">
				<input type="radio" checked="checked" />
				<p>链接</p>
				<textarea></textarea>
			</div>
			
			<a href="#" class="return" style="margin-left: 200px;">提交</a> 
			<a href="#" class="return">返回</a>
		</div>
	</div>
	<!--新增 END-->
	
	<!--弹出层1-->
	<div class="bg"></div>
	<div class="add-load">
		<div class="add-loadtop1">
			<p>下载</p>
			<img src="${prc}/function/img/erro.jpg" class="close" style="cursor: pointer;" />
		</div>
		<div class="add-loaddown">
			<table width="430px"
				style="border: 1px solid #d7d7d7; margin-top: 10px;">
				<tr>
					<th width="13%" scope="col">
						<input type="checkbox" />
					</th>
					<th width="21%" scope="col">附件名称</th>
					<th width="18%" scope="col">大小</th>
					<th width="27%" scope="col">下载进度</th>
					<th width="21%" scope="col">速度</th>
				</tr>
				<tr>
					<td>
						<input type="checkbox" />
					</td>
					<td>
						<span class="blue">我们的家园</span>
					</td>
					<td>
						<span class="blue">1.61GB</span>
					</td>
					<td>
						<p class="load">0.0%</p>
					</td>
					<td>46KB/s</td>
				</tr>
				<tr>
					<td>
						<input type="checkbox" />
					</td>
					<td>
						<span class="blue">我们的家园</span>
					</td>
					<td>
						<span class="blue">1.61GB</span>
					</td>
					<td>
						<p class="load">0.0%</p>
					</td>
					<td>46KB/s</td>
				</tr>
				<tr>
					<td>
						<input type="checkbox" />
					</td>
					<td>
						<span class="blue">我们的家园</span>
					</td>
					<td>
						<span class="blue">1.61GB</span>
					</td>
					<td>
						<p class="load">0.0%</p>
					</td>
					<td>46KB/s</td>
				</tr>
				<tr>
					<td>
						<input type="checkbox" />
					</td>
					<td>
						<span class="blue">我们的家园</span>
					</td>
					<td>
						<span class="blue">1.61GB</span>
					</td>
					<td>
						<p class="load">0.0%</p>
					</td>
					<td>46KB/s</td>
				</tr>
				<tr>
					<td>
						<input type="checkbox" />
					</td>
					<td>
						<span class="blue">我们的家园</span>
					</td>
					<td>
						<span class="blue">1.61GB</span>
					</td>
					<td>
						<p class="load">0.0%</p>
					</td>
					<td>46KB/s</td>
				</tr>
			</table>

			<a href="#" class="return" style="margin-left: 120px;">下载</a> 
			<a href="#" class="return">返回</a>
		</div>
	</div>
</body>
</html>