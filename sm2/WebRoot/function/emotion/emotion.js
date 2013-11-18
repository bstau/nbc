/**
 *_txtAreaId String 文本框Id (必填)
 *_elementDivId String 指定元素div Id,表情插入到指定的元素内  (必填)
 */
function EmoFace(_txtAreaId,_elementDivId,_emotionSpanId){
	
	var faceTool= new Object();
	faceTool.textAreaId = _txtAreaId;
	faceTool.elementDivId= _elementDivId;
	faceTool.emotionSpanId = _emotionSpanId;
	faceTool.basePath= ctx + "/function/emotion/images/";//图片路径
	//根据id查找文本域
	faceTool.textArea = function(){
		return $("textarea[id='"+faceTool.textAreaId+"']"); 
	};
	//指定元素
	faceTool.element = function(){
		return $("#"+faceTool.elementDivId);
	};
	
	//创建表情控件
	faceTool.Create = function(){
		
		//找不到文本域
		if(!faceTool.textArea().is('textarea')){
			alert("Not found textarea attr id is "+faceTool.textId);
			return false;
		}
		var box = $("<div>"); 
		$.each(faceTool.faces,function(i,f){
			var img = $('<img>');
			img.attr('src',f.img).attr('title','['+f.txt+']'); 
			var a = $('<a>');
			a.attr('title','['+f.txt+']');
			a.append(img);
			a.click(faceTool.insertFace); //绑定点击事件
			box.append(a);
		});
		
		//判断如果指定的元素不为空，将表情插入到指定元素内
		if(faceTool.element()[0]){
			faceTool.element().append(box);
		}else{//如果指定元素为空，则将表情插入到textarea前面
			faceTool.textArea().before(box);
		}
		
		//绑定点击事件
		$("#" + faceTool.elementDivId + " img").click(function () {
           $("#" + faceTool.elementDivId).hide();
        });

		//
        $("#" + faceTool.emotionSpanId).click(function(){
        	faceTool.element().is(":hidden") ?
        		faceTool.element().show():faceTool.element().hide();
        });

	};
	
	/*插入表情*/
	faceTool.insertFace = function(){
		var faceName = $(this).attr('title');
		faceTool.textArea().focus();
		var txtComment = faceTool.textArea()[0];
		if (document.all) {
			var r = document.selection.createRange();
			document.selection.empty();
			r.text = faceName;
			r.collapse();
			r.select();
		} else {
			var newstart = txtComment.selectionStart+faceName.length;
			txtComment.value=
				txtComment.value.substr(0,txtComment.selectionStart) + 
				faceName + txtComment.value.substring(txtComment.selectionEnd);
			txtComment.selectionStart = newstart;
			txtComment.selectionEnd = newstart;
		}
	};
	/*表情描述与路径*/
	faceTool.faces = [
				{ 'txt': '01', 'img': faceTool.basePath + '01.png' },
				{ 'txt': '02', 'img': faceTool.basePath + '02.png' },
				{ 'txt': '03', 'img': faceTool.basePath + '03.png' },
				{ 'txt': '04', 'img': faceTool.basePath + '04.png' },
				{ 'txt': '05', 'img': faceTool.basePath + '05.png' },
				{ 'txt': '06', 'img': faceTool.basePath + '06.png' },
				{ 'txt': '07', 'img': faceTool.basePath + '07.png' },
                { 'txt': '08', 'img': faceTool.basePath + '08.png' },
                { 'txt': '09', 'img': faceTool.basePath + '09.png' },
				{ 'txt': '0A', 'img': faceTool.basePath + '0A.png' },
				{ 'txt': '0B', 'img': faceTool.basePath + '0B.png' },
				{ 'txt': '0C', 'img': faceTool.basePath + '0C.png' },
				{ 'txt': '0D', 'img': faceTool.basePath + '0D.png' },
				{ 'txt': '0E', 'img': faceTool.basePath + '0E.png' },
				{ 'txt': '0F', 'img': faceTool.basePath + '0F.png' }];
	return faceTool;
}