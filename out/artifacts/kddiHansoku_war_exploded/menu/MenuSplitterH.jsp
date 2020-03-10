<%@ include file="/taglibs.jsp" %>
<html>
<head>
	<link rel="stylesheet" href="<%=Constants.DOC_ROOT%>/css/default.css" type="text/css">
	<script type="text/javascript">
		var timeID = null;
		var flag = -1;
		var MaxLeftWidth = 170;
		function to_change(){
			flag = flag * -1;
			move();
		}
		function move(){
			var framewidth;
			var speed = 15;
			var interval = 10;	
			framewidth = parent.document.getElementById("menu").width;
			framewidth = framewidth - speed*flag;
			if ( framewidth <= 0 || framewidth >=MaxLeftWidth ){
				clearTimeout(timeID);
				if(framewidth >=MaxLeftWidth){
				    parent.document.getElementById("mainframeset").cols =MaxLeftWidth + ",12,*";
				}else{
				    parent.document.getElementById("mainframeset").cols =0 + ",12,*";
				}
				if(flag ==-1){
					document.getElementById("js_splitter").src="<%=Constants.DOC_ROOT%>/img/bwp_splitter_h_l.gif";
				}else{
					document.getElementById("js_splitter").src="<%=Constants.DOC_ROOT%>/img/bwp_splitter_h_r.gif";
				}
			}else{
				timeID = setTimeout("move()",interval);
				parent.document.getElementById("mainframeset").cols =framewidth + ",12,*";
			}
		}
	</script>
</head>
	<body width="100%" class="splitter_h">
		<table height="100%" width=0 border="0" cellspacing="0"
			cellpadding="0">
			<tr>
				<td align="center" >
					<img id="js_splitter" onClick="to_change();" src="<%=Constants.DOC_ROOT%>/img/bwp_splitter_h_l.gif"  border="0">
				</td>
			</tr>
		</table>
	</body>
</html>