/**
 * 
 */
Date.prototype.format = function(format) {
       var date = {
              "M+": this.getMonth() + 1,
              "d+": this.getDate(),
              "h+": this.getHours(),
              "m+": this.getMinutes(),
              "s+": this.getSeconds(),
              "q+": Math.floor((this.getMonth() + 3) / 3),
              "S+": this.getMilliseconds()
       };
       if (/(y+)/i.test(format)) {
              format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
       }
       for (var k in date) { 
              if (new RegExp("(" + k + ")").test(format)) {
                     format = format.replace(RegExp.$1, RegExp.$1.length == 1
                            ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
              }
       }
       return format;
}

Date.prototype.week_cn = function(format) {
    var weeks = ["日","一","二","三","四","五","六"];
    return "周"+weeks[this.getDay()];
}

Set.prototype.get = function(index){
	var array = [];
	this.forEach(function(a){
		array[array.length] = a;
	});
	return array[index];
}

//给Number类型增加一个mul方法，调用起来更加方便。  
Number.prototype.mul = function (arg){  

	var m = 0, s1 = arg.toString(), s2 = this.toString();
	try {
		m += s1.split(".")[1].length

	} catch (e) {
	}
	try {
		m += s2.split(".")[1].length
	} catch (e) {
	}
	return Number(s1.replace(".", "")) * Number(s2.replace(".", ""))
			/ Math.pow(10, m)  
}  