/**容器**/
var parentDiv = "body";

var initNotes = function(subjectId){
	$.post(ctxPath + '/scMaster2/getNotesBySubjectId_note.action?subjectId='+subjectId,function(data){
		if(data && data.length>0){
			for(var i = 0;i < data.length;i++){
				var option = data[i];
				console.log(option.left + option.top);
				option.zIndex = i*10 + 100;
				option.saveOnCreate = false;
				createNote(option);
			}
		}
	});	
};

var newNote = function(subjectId){
	var option = new Object();
	option.saveOnCreate = true;
	option.top = '276px';
	option.left = '741px';
	option.parentId = subjectId;
	createNote(option);
};

var createNote = function(options){

	if(!options.id){
		options.id = UUID.generate();
	}
	options.containment = parentDiv;

	options.beforeClose = function(notesData){
		var result = false;
		$.ajax({
			url:ctxPath + '/scMaster2/remove_note.action',
			async:false,
			data : {id:notesData.id}
		}).done(function(msg){if(msg=='true'){result=true;}});
		return result;
	};

	options.save = function(notesData){

		var result = false;
		notesData.parentId = options.parentId;
		$.ajax({
			url:ctxPath + '/scMaster2/update_note.action',
			async:false,
			data : notesData
		}).done(function(msg){if(msg=='true'){result=true;}});
		return result;
	};

	$("<div class='sticky_notes' id='" + options.id +"' ></div>").appendTo($(parentDiv));
	$("#" + options.id).StickyNotes(options).mousedown();
};

