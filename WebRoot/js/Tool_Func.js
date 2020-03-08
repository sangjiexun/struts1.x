function hyde1(num)
{
	if(num == 1){
		document.all["div1"].style.display = 'block'; 
		document.all["div2"].style.display = 'none'; 
        }else if (num == 2){
		document.all["div1"].style.display = 'none'; 
		document.all["div2"].style.display = 'block'; 
	}	
}

 	/***********************************************************************************************
	* 数値フォーマット（カンマ編集）
	************************************************************************************************/
	function toComma(Value)
	{
		if(Value == ""){return Value;}
		var eValue = Value.toString();
		var intPart = "";
		var decPart = "";
		if (eValue.indexOf(",")>=0)
		{
			eValue=eValue.replace(/,/g,"");
		}
		
		if (eValue.indexOf(".")>=0)
		{
			intPart=eValue.split(".")[0];
			intPart=parseInt(intPart,10);
			decPart=eValue.split(".")[1];
			decPart=parseInt(decPart,10);
		}
		else
		{
			intPart = eValue;
			intPart=parseInt(eValue,10);
		}
		var num  = intPart+"";
		var re=/(-?\d+)(\d{3})/
		while(re.test(num))
		{
			num=num.replace(re,"$1,$2");
		}
		if (eValue.indexOf(".")>=0)
		{
			eValue=num + "." + decPart;
		}
		else
		{
			eValue=num;
		}

		return eValue;
	}
	function reComma(eValue){
		return eValue.toString().replace(/,/g,"");
	}
	function lpad(_obj){
    	_obj.value=padLeft(_obj.value,6);
    }
    
    function padLeft(str,lenght){
    	if(str==""){
    		return "";
    	}
		if(str.length>=lenght)
			return str;
		else
		return padLeft("0" +str,lenght);
		
	}