var move=false;
function StartDrag(obj){
	if(event.button==1&&event.srcElement.tagName.toUpperCase()=="DIV"&&event.srcElement.id=="myDrag"){
		obj.style.zIndex=1;
		obj.mouseDownY=event.offsetY;
		obj.mouseDownX=event.offsetX;
		obj.setCapture();
		move=true;
	}
}

function Drag(obj){
	if(move){
		var oldwin=obj.parentNode;
		if((event.clientY - obj.mouseDownY) < 0){
			oldwin.style.top = 0;
		}else{
			oldwin.style.top = (event.clientY - obj.mouseDownY);
		}
		if((event.clientX- obj.mouseDownX) < 0){
			oldwin.style.left = 0;
		}else{
			oldwin.style.left = (event.clientX- obj.mouseDownX);
		}
		getIE(oldwin.style.top);
	}
}
function StopDrag(obj){
	obj.releaseCapture();
	move=false;
	try{
		document.formx.strToolbarX.value = obj.parentNode.style.left;
		document.formx.strToolbarY.value = obj.parentNode.style.top;
	}catch(e){
	}
}

function getIE(divTopWithPX){
	var e = document.getElementById("space1");
	var divTop = parseInt(divTopWithPX);
	var t=e.offsetTop;
	var l=e.offsetLeft;
	while(e=e.offsetParent){
	  t+=e.offsetTop;
	  l+=e.offsetLeft;
	}
	if((t-20)<= divTop && divTop< (t+20)){
		getShowHidd(1);
	}else{
		getShowHidd(0);
	}
}

function getShowHidd(flag){
	var space_1 =	document.getElementById("space1");
	if(flag){
		space_1.style.height = "50px";
		document.formx.strSpaceHeight.value = "50px";
	}else{
		space_1.style.height = "12px";
		document.formx.strSpaceHeight.value = "12px";
	}
}

function popJump(selOBJ){
  n = selOBJ.selectedIndex;
  page_size = selOBJ.options[n].value;
  if(page_size != -1){
    document.formx.intPageSize.value = page_size;
    document.formx.intPageNo.value = 1;
    document.formx.submit();
	 }
}

function toHanNum(motoTxt){
    var motoText = motoTxt.toString();
    han = "0123456789.,-+";
    zen = "‚O‚P‚Q‚R‚S‚T‚U‚V‚W‚XDC|{";
    str = "";
    for (i=0; i<motoText.length; i++)
    {
        c = motoText.charAt(i);
        n = zen.indexOf(c,0);
        if (n >= 0) c = han.charAt(n);
        str += c;
    }
    return str;
}
function page_submit(page) { 
  var page_n = toHanNum(page);
  if (isNaN(page_n))
    page_n = 1;
  document.formx.intPageNo.value = page_n;
  document.formx.submit();
}
function goto_p() {
  page_submit(document.getElementById("go_page").value);
}
function goto_p_pull() {
  page_submit(document.getElementById("go_page_pull").value);
}

