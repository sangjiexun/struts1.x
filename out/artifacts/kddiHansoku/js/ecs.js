/*  ECS-Solution JavaScript framework, version 0.1.0
 *  (c) 2005-2008 DHK
 *  Author:LiHuan
/*--------------------------------------------------------------------------*/
window.onload = function() {
   var colorful = new ColorfulInput;
   colorful.set();
   //2012.7.20 KDDIでは先頭項目フォーカスを設定しない
   //var firstFocus = new setFirstFocus;
   //firstFocus.set();
}
function setFirstFocus() {
   this.skip  = ["button","submit","hidden"];

   this.set = function() {
      for (var i = 0; i < document.forms.length; i++) {
         for (var f = 0; f < document.forms[i].length; f++) {
            var elm = document.forms[i][f];
            if(!this._checkSkip(elm)) continue;
            if(elm.disabled == true) continue;
            if(elm.readOnly == true) continue;
            elm.focus();
			return;
         }
      }
   }
   this._checkSkip = function(elm) {
      for(var i in this.skip) {
         if(elm.type == this.skip[i]) return false;
      }
      return true;
   }
}

function ColorfulInput() {
   this.skip  = ["button","submit","textarea","checkbox","radio","select-one"];
   this.color = { 'blur': '', 'focus': '#ffcc99' };

   this.set = function() {
      for (var i = 0; i < document.forms.length; i++) {
         for (var f = 0; f < document.forms[i].length; f++) {
            var elm = document.forms[i][f];
            if(!this._checkSkip(elm)) continue;

            this._setColor(elm, 'focus');
            this._setColor(elm, 'blur');
            elm.onkeypress=function(){_chkInp();};
         }
      }
   }

   this._checkSkip = function(elm) {
      for(var i in this.skip) {
         if(elm.type == this.skip[i]) return false;
      }
      return true;
   }

   this._setColor = function(elm, type) {
      var color = this.color[type];
      var event = function() { elm.style.backgroundColor = color; };

      if(elm.addEventListener) {
         elm.addEventListener(type, event, false);
      } else if(elm.attachEvent) {
         elm.attachEvent('on'+type, event);
      } else {
         elm['on'+type] = event;
      }
   }
}

/* Input Text Box Check */
function _chkInp(){
	var regCh = /[\W\w]/;
	var regAll = /[\W\w]/;
	if(event == null ||event.srcElement == null) return;
	try{
		switch(event.srcElement.className){
			case "float": regCh = /[0-9.]/; regAll = /^[0-9.]*$/; break;
			case "code": regCh = /[0-9a-zA-Z-_*]/; regAll = /^[0-9a-zA-Z-_*]*$/; break;
			case "code2": regCh = /[0-9a-zA-Z-_\/*]/; regAll = /^[0-9a-zA-Z-_\/*]*$/; break;
			case "code3": regCh = /[0-9a-zA-Z-_ \/*]/; regAll = /^[0-9a-zA-Z- _\/*]*$/; break;
			case "numcode": regCh = /[0-9]/; regAll = /^[0-9]*$/; break;
			case "half": regCh = /\S/; regAll = /^\S*$/; break;
			case "int": regCh = /[0-9-]/; regAll = /^[0-9-]*$/; break;
			case "tel": regCh = /[0-9-]/; regAll = /^[0-9-]*$/; break;
			case "zip": regCh = /[0-9-]/; regAll = /^[0-9-]*$/; break;
			case "zip1": regCh = /[0-9]/; regAll = /^[0-9]*$/; break;
			case "zip2": regCh = /[0-9]/; regAll = /^[0-9]*$/; break;
			case "num": regCh = /[0-9]/; regAll = /^[0-9]*$/; break;
			case "en": regCh = /^[\x00-\xff]*$/; regAll = /^[\x00-\xff]*$/; break;
			case "ymd": eval("regCh = /[0-9/]/; regAll = /^[0-9/]*$/;"); break;
			case "ym": eval("regCh = /[0-9/]/; regAll = /^[0-9/]*$/;"); break;
			case "time": eval("regCh = /[0-9:]/; regAll = /^[0-9:]*$/;"); break;
			case "mail": regCh = /[0-9a-zA-Z-_@.*]/; regAll = /^[0-9a-zA-Z-_@.*]*$/; break;
			case "color": regCh = /[0-9a-fA-F#]/; regAll = /^[0-9a-fA-F#]*$/; break;
			default: break;
		}
		switch(event.type){
			case "keypress": event.returnValue = regCh.test(String.fromCharCode(event.keyCode)); break;
			case "paste" :
				event.returnValue = regAll.test(window.clipboardData.getData("text"));
				if (!event.returnValue) {
					if (regAll.test(window.clipboardData.getData("text").trim())){
						event.srcElement.value = window.clipboardData.getData("text").trim();
					}
				}
				break;
			case "drop" : event.returnValue = regAll.test(event.dataTransfer.getData("text")); break;
			case "blur" : if(regAll.test(event.srcElement.vlaue)) alert("Input Error!"); return;
			default: break;
		}
		if(!event.returnValue && event.preventDefault){
			event.preventDefault();
		}
	}catch(e){

 }
}


//カンマ付与
function _toComma(checkString){
	if(checkString=="-"){
		return "";
	}
	checkString = reComma(new String(checkString).replace(/[ 　]/g, ""));

	if(checkString=="") return "";
	var newString = "";
	var flag = "";	// マイナスのとき"-"を格納
	var flg = 1;
	for (i=checkString.length-1; i>=0; i--) {
		ch = checkString.substring(i, i+1);
		if (ch == "-"&&i == 0) {	// 左端で "-"はマイナス記号
			flag = ch;
		} else if ((ch >= "0"&&ch <= "9")) {
			newString = ch + newString;
		} else if (flg == 1&&ch == ".") {
			newString = ch + newString;
			flg = 0;
		} else {
			return 0;
		}
	}
	//カンマ区切りにする
	cnt = 0;
	n   = "";

	//newString = eval(newString).toString(10);
	newString = parseFloat(newString)+"";

	for (i=newString.length-1; i>=0; i--)
	{
		ch = newString.substring(i, i+1);
		if(flg == 1) {
			if ((ch >= "0"&&ch <= "9")) {
				n = newString.charAt(i) + n;
				cnt++;
				if (((cnt % 3) == 0)&&(i != 0)) n = ","+n;
			}
		}
		else
		{
			n = newString.charAt(i) + n;
		}
		if(newString.charAt(i) == ".") {
			flg = 1;
		}
	}
	return (flag+n);
}

//カンマ削除
function _reComma(val){
	num = val.split(",");
	newstring = num.join("");
	return newstring;
}

//---------------Date Utils -----------------//
function _praseDate(DateString,Dilimeter_s,Dilimeter_r)
{
	if(!_isDate(DateString,Dilimeter_s)) return "";
	if (DateString==null) return "";

	var year="";
	var month="";
	var day="";
	var temparray;
	if (Dilimeter_s=="" || Dilimeter_s==null)
	{
		if(DateString.length!=8) return "";
		year=DateString.substr(0,4);
		month=DateString.substr(4,2);
		day=DateString.substr(6,2);
	}
	else
	{
		temparray=DateString.split(Dilimeter_s);
		if (temparray.length!=3) return false;
		if (temparray[0].length==4) {
			year=temparray[0];
			month=temparray[1]<10?"0"+(temparray[1]*1):temparray[1];
			day=temparray[2]<10?"0"+(temparray[2]*1):temparray[2];

		}
		else {
			year=temparray[2];
			month=temparray[0]<10?"0"+(temparray[0]*1):temparray[0];
			day=temparray[1]<10?"0"+(temparray[1]*1):temparray[1];
		}
	}
	return  year +  Dilimeter_r + month + Dilimeter_r + day;
}
function _getLastDay(year,month) {
	var m = [31,28,31,30,31,30,31,31,30,31,30,31];
	if (month != 2) return m[month - 1];
	if (year%4 != 0) return m[1];
	if (year%100 == 0 && year%400 != 0) return m[1];
	return m[1] + 1;
}

function _formateDate(obj,strErr)
{
    mc();
	var strDate = obj.value;
	if(strDate==null || strDate=="") return;
	if( (strDate.length <= 2 && strDate <= 31 && strDate >= 0)
		|| (strDate == "99")
		|| (strDate.length == 4 && strDate != "9999")){

	    var strM;
	    var strD;
		var myDate = new Date();

		if(strDate.length == 4){
			strM = strDate.substr(0,2);
			strD = strDate.substr(2,2);
		}else{
			strM = myDate.getMonth() + 1
			strD = strDate;
		}

		var lastDay = _getLastDay(myDate.getYear(),strM);

		if(strD == "99"){
			strD = lastDay;
		}

		if(strD <= lastDay){

			var tempMonth = strM;
			var tempDate;
			if(strD != 0){
				tempDate = strD;
			}else{
				if(myDate.getDate()<=lastDay){
					tempDate = myDate.getDate();
				}else{
					tempDate = lastDay;
				}
			}
			if(tempMonth < 10){
				tempMonth = "0" + (tempMonth*1);
			}
			if(tempDate < 10){
				tempDate = "0" + (tempDate*1);
			}
			obj.value = myDate.getYear()+"/"+tempMonth+"/"+tempDate;
			return;
		}
	}

	if((strDate.length == 3 && strDate == "999")
       || (strDate.length == 4 && strDate == "9999")
       || (strDate.length == 8 && strDate == "99999999")
       || (strDate.length == 10 && strDate == "9999999999")
       ){
			obj.value = "9999/12/31";
			return;
	}

	if(!_chkDate(strDate))
	{
		m(strErr);
		//obj.focus();
		return;
	}

	if(strDate.indexOf("/")<0)
	{
		obj.value = _praseDate(strDate,"","/");
	}else{
		obj.value = _praseDate(strDate,"/","/");
	}
}
function _formateYM(obj,strErr){
    mc();
    var strDate = obj.value;
    if(strDate==null || strDate=="") return;

    if((strDate.indexOf("/") < 0 && strDate.length != 6)||(strDate.indexOf("/") > 0 && strDate.length != 7)) {
        m(strErr);
        //obj.focus();
        return;
    }

    var strY = "";
    var strM = "";
    if(strDate.indexOf("/")<0){
	    strY = strDate.substr(0,4);
	    strM = strDate.substr(4,2);
    }else{
        strY = strDate.substr(0,4);
        strM = strDate.substr(5,2);
    }
    //if(strY < 1900 || strY > 2100){
    if(strY < 1900){
        m(strErr);
        obj.focus();
        return;
    }

    if(strM < 0 || strM > 12){
        m(strErr);
        obj.focus();
        return;
    }

    obj.value = strY+"/"+strM;
}

function _isYm(obj){
    var strDate = obj.value;
    if(strDate==null || strDate=="") return true;

    if((strDate.indexOf("/") < 0 && strDate.length != 6)||(strDate.indexOf("/") > 0 && strDate.length != 7)) {
        return false;
    }

    var strY = "";
    var strM = "";

    if(strDate.indexOf("/")<0){
        strY = strDate.substr(0,4);
        strM = strDate.substr(4,2);
    }else{
        strY = strDate.substr(0,4);
        strM = strDate.substr(5,2);
    }
    //if(strY < 1900 || strY > 2100){
    if(strY < 1900){
        return false;
    }

    if(strM < 1 || strM > 12){
        return false;
    }
    return true;
}

function _isDate(DateString,Dilimeter)
{
	if (DateString==null) return false;
	var pattern;
	var year="";
	var month="";
	var day="";
	var temp="";
	var temparray=new Array();
	if (Dilimeter=="" || Dilimeter==null)
	{
		//pattern = /^[1-2][0-9]{3}[0-1][0-9][0-3][0-9]$/;
		pattern = /^[1-9][0-9]{3}[0-1][0-9][0-3][0-9]$/;

		if(DateString.length!=8) return false;
		year=DateString.substr(0,4);
		month=DateString.substr(4,2);
		day=DateString.substr(6,2);
	}
	else
	{

		//pattern = /^[1-2][0-9]{3}[\/][0-1][0-9][\/][0-3][0-9]$/;
		pattern = /^[1-9][0-9]{3}[\/][0-1]?[0-9][\/][0-3]?[0-9]$/;
		temparray=DateString.split(Dilimeter);
		if (temparray.length!=3) return false;
		if (temparray[0].length==4) {
			year=temparray[0];
			month=temparray[1];
			day=temparray[2];
		}
		else {
			year=temparray[2];
			month=temparray[0];
			day=temparray[1];
		}
	}

	if (!pattern.test(DateString)){
		return false;
	}
	if (isNaN(Date.parse(month+"/"+day+"/"+year))) {
		return false;
	}

	var dd = parseInt(day,10);
	var mm = parseInt(month,10)-1;
	var yy = parseInt(year,10);
	var date = new Date(yy,mm,dd);
	//if (yy<1901 || yy>2100) {
	if (yy<1901) {
		return false;
	}

	if (dd!=date.getDate() || mm!=date.getMonth() || yy!=date.getFullYear()) {
		return false;
	}
	return true;
}

function _chkDate(strDate)
{
	if(strDate==null||strDate=="") return true;
	if(strDate.indexOf("/")>0)
    {
		return _isDate(strDate,"/");
    }else{
		return _isDate(strDate);
    }
}

function $$(element) {
  return document.getElementById(element);
}

// Ajax By JQuery
function _SendByAjax(_url,_param,_debug,_async){
	$.ajax({url : _url,
	        type : 'post',
	        dataType : 'html',
	        data : _param,
	        async : _async,
	        timeout : 10000,
	        error: function (xhr, desc, exceptionobj) {
	        	alert("Ajax post response error ! \nServer response data is:\n"+xhr.responseText);
  			},
	        success: function(data,textStatus){
		        		if(_debug == "1"){
		        			alert(data);
		        		}
		        		try{
		        			eval(data);
		        		}catch(e){
	        				alert("Ajax javascript execute error ! \nServer response data is:\n"+data);
						}
					}
	        });
}
//loading画像指定対応 _id にloading画像のidを指定する 2015/04/07 K.Karasawa
function _SendByAjax2(_url,_param,_debug,_async,_id){
	$(_id).show();
	$.ajax({url : _url,
			type : 'post',
			dataType : 'html',
			data : _param,
			async : _async,
			timeout : 10000,
			error: function (xhr, desc, exceptionobj) {
				alert("Ajax post response error ! \nServer response data is:\n"+xhr.responseText);
			},
			success: function(data,textStatus){
				if(_debug == "1"){
					alert(data);
				}
				try{
					eval(data);
				}catch(e){
					alert("Ajax javascript execute error ! \nServer response data is:\n"+data);
				}
			},
			complete: function(){
				$(_id).hide();
			}
		});
}

//JQuery Page Init
function _PageInit(_table,_checkbox,_imagelink,_enter,_input){
	if(arguments.length >= 1){
	    if(arguments.length >= 1 && _table == 1){_TableInit();_TableInit2();_TableInit3();}
	    if(arguments.length >= 2 && _checkbox == 1){_CheckBoxAllInit();}
	    if(arguments.length >= 3 && _imagelink == 1){_ImageLinkInit();}
	    if(arguments.length >= 4 && _enter == 1){_Enter2Tab();}
	    if(arguments.length >= 5 && _input == 1){_InputInit();}
	}else{
	    _TableInit();
	    _TableInit2();
	    _TableInit3();
	    _CheckBoxAllInit();
	    _ImageLinkInit();
	    _Enter2Tab();
	    _InputInit();
	}
}

function _InputInit(){
    $('input[onkeypress]').each(function(i,text){this.onpaste = function(){_chkInp();};
                                                  this.ondrop = function(){_chkInp();};
                                                    });

}

//Table Init By JQuery
function _TableInit(){
	$(".lt").each(function(index,table){
			var rowSpan = $(this).find("tr:first-child").children().attr("rowSpan");
			//var rowSpan = $(this).find("tr:first").find("th:first").attr("rowSpan");
			var groupIndex = rowSpan + 1;
			var groupId = 1;
			$(this).find("tr").not(".head").each(
				function(i,row){
					var bgColor = "";
					if(this.style.backgroundColor != ""){
						bgColor = this.style.backgroundColor;
					}else{
						if( (i % (rowSpan*2)) < rowSpan){
							bgColor = "#ffffff";
						}else{
							bgColor = "#EEEEEE";
						}
					}
					if(groupIndex <= 1){
						groupIndex = rowSpan;
						groupId = groupId + 1;
					}else{
						groupIndex = groupIndex - 1;
					}
					this.style.backgroundColor = bgColor;
					this._groupId = groupId;
					$(this).find("input[type=text][readOnly]").each(function(i,inputText){ this.style.backgroundColor=bgColor;})
					/*
					$(this).hover(function(){_ChangeColor(table,row,"#FFFF66");},
								  function(){_ChangeColor(table,row,bgColor);});
					*/
				})

		});
}//Table Init2 By JQuery
function _TableInit2(){
	$(".lt2").each(function(index,table){
			var rowSpan = $(this).find("tr:first-child").children().attr("rowSpan");
			//var rowSpan = $(this).find("tr:first").find("th:first").attr("rowSpan");
			var groupIndex = rowSpan + 1;
			var groupId = 1;
			$(this).find("tr").not(".head").each(
				function(i,row){
					var bgColor = "";
					if(this.style.backgroundColor != ""){
						bgColor = this.style.backgroundColor;
					}else{
						if( (i % 2) < 1){
							bgColor = "#ffffff";
						}else{
							bgColor = "#EEEEEE";
						}
					}
					if(groupIndex <= 1){
						groupIndex = rowSpan;
						groupId = groupId + 1;
					}else{
						groupIndex = groupIndex - 1;
					}
					this.style.backgroundColor = bgColor;
					this._groupId = groupId;
					$(this).find("input[type=text][readOnly]").each(function(i,inputText){ this.style.backgroundColor=bgColor;})
					/*
					$(this).hover(function(){_ChangeColor(table,row,"#FFFF66");},
								  function(){_ChangeColor(table,row,bgColor);});
					*/
				})

		});
}
function _TableInit3(){
	$(".kanri_lt").each(function(index,table){
			var rowSpan = $(this).find("tr:first-child").children().attr("rowSpan");
			//var rowSpan = $(this).find("tr:first").find("th:first").attr("rowSpan");
			var groupIndex = rowSpan + 1;
			var groupId = 1;
			$(this).find("tr").not(".head").each(
				function(i,row){
					var bgColor = "";
					if(this.style.backgroundColor != ""){
						bgColor = this.style.backgroundColor;
					}else{
						if( (i % 2) < 1){
							bgColor = "#ffffff";
						}else{
							bgColor = "#FFF8DC";
						}
					}
					if(groupIndex <= 1){
						groupIndex = rowSpan;
						groupId = groupId + 1;
					}else{
						groupIndex = groupIndex - 1;
					}
					this.style.backgroundColor = bgColor;
					this._groupId = groupId;
					$(this).find("input[type=text][readOnly]").each(function(i,inputText){ this.style.backgroundColor=bgColor;})
					/*
					$(this).hover(function(){_ChangeColor(table,row,"#FFFF66");},
								  function(){_ChangeColor(table,row,bgColor);});
					*/
				})

		});
}
// Change Color By JQuery
function _ChangeColor(_table,_row,_color){
	$(_table).find("[_groupId="+ _row._groupId +"]").each(
		function(i){
			this.style.backgroundColor = _color;
			$(this).find("input[type=text][readOnly]").each(function(i,inputText){ this.style.backgroundColor=_color;});
		}
	);
}

function _ImageLinkInit(){
    $('a[class^=img]').each(function(i,link){this.tabIndex=-1;});
    $('a[class=but039]').each(function(i,link){this.tabIndex=-1;});
}

function _CheckBoxAllInit(){
	$("input[_target]").each(
		function(i,checkboxAll){
			$(checkboxAll).click(function(){_CheckAllChecked(checkboxAll)});
			$("input[name="+ $(checkboxAll).attr("_target")+"]",$$(checkboxAll._targetForm)).each(
				function(j,checkbox){
					$(checkbox).click(function(){_Checked(checkboxAll)});
				}
			);
			_Checked(checkboxAll);
		}
	);
}

function _Checked(checkboxAll){
	var checkedLength = $("input[name="+ $(checkboxAll).attr("_target") + "]",$$(checkboxAll._targetForm)).length;
	if(checkedLength == 0){
		return ;
	}
	if(checkedLength == $("input[name="+ $(checkboxAll).attr("_target") +"][checked]",$$(checkboxAll._targetForm)).length){
		checkboxAll.checked = true;
	}else{
		checkboxAll.checked = false;
	}
}

function _CheckAllChecked(checkboxAll){
	$("input[name="+ $(checkboxAll).attr("_target") +"]",$$(checkboxAll._targetForm)).each(
		function(j,checkbox){
			checkbox.checked = checkboxAll.checked;
		}
	);
}

// Change Display Div By JQuery
function _ChangePageDivSize(_select,_divId,_rowspan,_rowHeight){
	var _span = 0;
	if(_rowspan >1){
		_span = 1;
	}
	if($$(_divId).scrollHeight > (_select.value * (_rowspan <= 1? 1 : _rowspan) * _rowHeight)){
		$("#"+_divId).css("height", ((_select.value * (_rowspan <= 1? 1 : _rowspan) * _rowHeight)+_span)+"px");
	}else{
		$("#"+_divId).css("height", "auto");
	}
	$("#"+_divId).css("max-height", ((_select.value * (_rowspan <= 1? 1 : _rowspan) * _rowHeight)+_span)+"px");
	try{
		$.cookie(_select.id, _select.value);
	}catch(e){
	}
}

function _Enter2Tab(){
	document.body.onkeydown=function(){
		try{
			if(event.srcElement.type == "file" && event.keyCode==13){
				return false;
			};
			if(event.srcElement.value == "住所反映" && event.keyCode==13){
				event.keyCode = 9;
				return;
			};
			if(event.srcElement.value == "新規登録" && event.keyCode==13){
				event.keyCode = 9;
				return;
			};
			if(event.srcElement.type == "textarea" && event.keyCode==13){
				return;
			};
			if (!(event.srcElement.type == "button" || event.srcElement.type == "submit")){
				if (event.keyCode == 13){
					event.keyCode = 9;
				}
			}
			} catch(e) {
		}
	}
}

function _InitPageDiv(v_id){
	var pageSize = 10;
	try{
		if($.cookie(v_id) == "" || $.cookie(v_id) == null || $.cookie(v_id) == "null"){
		}else{
			pageSize = $.cookie(v_id);
		}
	}catch(e){

	}
	try{
		$$(v_id).value = pageSize;
		$($$(v_id)).change();
	}catch(e){
	}
}
//  Hyper Message Box
function mc(){
    mheadHide();
    if($(".error_strings").length > 0){
        $(".error_strings").eq(0).html("");
        document.body.style.backgroundColor = _ok_color;
    }
}
function m(_msg){
    if($(".error_strings").length > 0){
        mheadShow();
        $(".error_strings").eq(0).html("");
        $(".error_strings").eq(0).append("<ul><li>"+_msg+"</li></ul>");
		document.body.style.backgroundColor = _error_color;
    }else{
        alert("@"+_msg+"@");
    }
}
function m2(_msg){
    if(_msg == "") return;
    if($(".error_strings").length > 0){
        mheadShow();
        var _errorDiv = $(".error_strings").eq(0);
        if($(_errorDiv).find("ul").length == 0){
            $(_errorDiv).append("<ul></ul>");
        }
        $(_errorDiv).find("ul").append("<li>"+_msg+"</li>");
		document.body.style.backgroundColor = _error_color;
    }else{
        alert("@"+_msg+"@");
    }

    if($('.error_strings').text().indexOf('ERROR') > 0){
    	mheadShow();
    } else {
    	mheadHide();
    }
}
function mheadShow() {
    var obj = document.getElementById("tipDiv");
    if(obj == null){
    } else {
        sc2();
        obj.style.display = "";
    }
}
function mheadHide() {
    var obj = document.getElementById("tipDiv");
    if(obj == null){
    } else {
        obj.style.display = "none";
    }
}
function sc2(){
    document.getElementById('tipDiv').style.top = document.body.scrollTop;
    document.getElementById('tipDiv').style.left = document.body.scrollLeft + document.body.clientWidth - 330;
}

function btnDisabled() {
	$("input[type=button]").each(function() {
        this.disabled = "disabled";
    });
}
function btnAbled() {
	$("input[type=button]").each(function() {
        this.disabled = false;
    });
}
function isNull(str){
    if(str.Trim()==""){
        return true;
    }
    else{
        return false;
    }
}
//check string can change to negative integer or positive integer;
//intstring:be checked string,sign:'+' or '-'
function isInteger(intstring,sign) {
    var integer;
    if (sign!=null && sign!="-" && sign!="+") return false;
    integer=parseInt(intstring);
    if (isNaN(integer)) return false;
    if (integer.toString().length==intstring.length) {
        if ((sign==null) || (sign=="-" && integer<0) || (sign=="+" && integer>0)) {
            return true;
        }
        else {
            return false;
        }
    }
    else {
        return false;
    }
}

//check decimail digits float
//len:float max length
//dec_digits:decimal digits
function isFloat(floatStr,len,dec_digits) {
    var t_float;
    if (isNaN(parseFloat(floatStr)))
        return false;

    var pattern;
    var pattern_str = '';
    var step_num;
    for (var i=len-dec_digits;i>0;i-=3) {
        if ((i / 3) > 1) {
            step_num = 3;
            pattern_str =  '(' + pattern_str + '(,?[0-9]{1,' +step_num+ '})?)';
        }
        else {
            step_num = i%3;
            if (step_num == 0)
                step_num = 3;
            pattern_str = '(([0-9]{1,'+step_num+'})?' + pattern_str + ')';
        }
    }

    pattern_str = '/^' +pattern_str + '([.][0-9]{1,' + dec_digits + '})?$/';
    //document.write(pattern_str);
    eval('pattern = ' + pattern_str);
    if (pattern == null){
        return false;
    }
    else{
        if (!pattern.test(floatStr)){
            return false;
        }
    }
    return true;
}

function isMail(strValue) {
    //var reg = /^[^\d\-_][\w\-]*[^\-_]@[^\-][a-zA-Z\d\-]*[^\-](\.[^\-][a-zA-Z\d\-]*[^\-])*\.[a-zA-Z]{3}(\.[a-zA-Z]{2})?$/;
    var reg = /^([\w-\.])+@([\w-])+(\.[\w-])+/;
    if(!reg.test(strValue)){
        return false;
    }
    return true;
}

function isZip(strValue) {
    var reg = /^[0-9\-]{1,10}$/;
    if(!reg.test(strValue)){
            return false;
    }
    return true;
}

function isTel(strValue) {
    var reg = /^[0-9\-]{1,20}$/;
    if(!reg.test(strValue)){
        return false;
    }
        return true;
}

function isDate(DateString,Dilimeter){
    _isDate(DateString,Dilimeter);
}

function isCode(SpecialStr) {
    var strFormat = /^[0-9a-zA-Z\-]*$/;
    if (strFormat.test(SpecialStr)){
        return true;
    }else{
        return false;
    }
}

//Trim,LTrim,RTrim method add to String object;
String.prototype.Trim = function()
{
    return this.replace(/(^ *)|( *$)/g, "");
}
String.prototype.LTrim = function()
{
    return this.replace(/(^ *)/g, "");
}
String.prototype.RTrim = function()
{
    return this.replace(/( *$)/g, "");
}

function bindCalender(objText,objLink,strNewId){
    objText.id = strNewId;
    objLink.id = objText.id + '_calendar_link_id';
    Calendar.setup({inputField:objText.id,ifFormat:'%Y/%m/%d',showsTime:false,weekNumbers:false,button: objLink.id,singleClick : true,step:1});
}

/**
 * 半角カナであるかをチェックします。
 *
 * @param チェックする値
 * @return ture : 半角カナ / flase : 半角カナ以外
 */
function isHankakuKana(value) {
  for (var i = 0; i < value.length; ++i) {
    var c = value.charCodeAt(i);
    //  半角カタカナは不許可
    if (c >= 0xff61 && c <= 0xff9f) {
      return true;
    }
  }
  return false;
}

/**
 * 全角であるかをチェックします。
 *
 * @param チェックする値
 * @return ture : 全角 / flase : 全角以外
 */
function isZenkaku(value) {
  for (var i = 0; i < value.length; ++i) {
    var c = value.charCodeAt(i);
    //  半角カタカナは不許可
    if (c < 256 || (c >= 0xff61 && c <= 0xff9f)) {
      return false;
    }
  }
  return true;
}

/**
 * 文字列のバイト数を取得する。
 * 全角を2バイト、半角を1バイトとしてカウントします。
 *
 * @param バイトを取得する値
 * @return 取得したバイト数
 */
function getByteCount(value) {
  var count = 0;
  for ( var i = 0; i < value.length; ++i ) {
    var sub = value.substring(i, i + 1);
    //全角の場合２バイト追加。
    if( isZenkaku(sub) ){
      count += 2;
    } else {
      count += 1;
    }
  }
  return count;
}

function errorShow(){
	document.body.scrollTop = document.body.scrollHeight;
}

function InputToComma(obj){
	try{
		obj.value=reComma(obj.value);
	}catch(e){}
}

function InputReComma(obj){
	try{
		obj.value=toComma(obj.value);
	}catch(e){}
}
