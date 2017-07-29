/**
 * 
 */
var sf = function(league){
	this.init(league);
};

sf.prototype = {
	matchs : [],
	guess : [],
	league : "lpl",
	ispull : false,
	ispaint : false,
	lottery : {},
	money : 1,
	init:function(league){
		this.league = league;
		this.$pull();
		var _this = this;
		$("#betBtn").click(function(){
			$(".bqZdy").addClass("hidden");
			$(".zdymoney").addClass("hidden");
			$(".czMoney").toggleClass("hidden");
		});
		
		$(".price").click(function(){
			$(".price").removeClass("czCurrent");
			$(this).toggleClass("czCurrent");
			var money = this.id.replace("money_","");
			$("#automoney").val(money);
			$("#automoney").change(); 
		});
		
		$("#zdymoney").click(function(){
			$(".czMoney").addClass("hidden");
			$(".bqZdy").removeClass("hidden");
		});
		
		$("#automoney").change(function(){
			_this.money = $(this).val();
			_this.cal();
		});
	},
	$pull : function(){
		var _this = this;
		_this.ispull = false;
		_this.ispaint = false;
		_this.matchs = [];
		_this.guess = [];
		$.ajax({
	        type: "GET",
	        url: "/lol/guess/"+_this.league+"/sf.json",
	        data: {},
	        dataType: "json",
	        success: function(datas){
	        	_this.ispull = true;
	        	_this.guess = datas.guess;
	        	_this.matchs = datas.matchs;
	        	_this.repaint();
	        }
	    });
	}
}; 

sf.prototype.repaint = function(){
	function up(odds){
		if(odds.length > 1){
	   		return odds[odds.length-1] > odds[odds.length-2] ? '<i class="c_090">↓</i>' : '<i class="c_e24949">↑</i>';
	   	}
		return "";
	}
	if(this.ispull){
		this.lottery = {};
		var html = "";
       	var time_group = null;
       	for(var match_id in this.guess){
       		var data = this.guess[match_id];
    	   	var match = this.matchs[match_id];
    	   	
    	   	var match_date = new Date(); 
    	   	match_date.setTime(match.match_date);
    	   	var t_group = match_date.format("yyyy-MM-dd");
    	   	
    	   	if(!time_group || time_group != t_group){
    	   		html += '<dt class="">';
       		   	html += '	<p class="text-left lh24em jcOp padding-lr">'+t_group+'&nbsp;'+match_date.week_cn()+' </p>';
			   	html += '</dt>';
    	   	}
		   	time_group = t_group;
	       	html += '<dd>';
    	   	html += '	<ul class="games-list" id="games_1" style="transform-origin: 0px 0px 0px; opacity: 1; transform: scale(1, 1);">';
		   	html += '		<li id="381801" style="transform-origin: 0px 0px 0px; opacity: 1; transform: scale(1, 1);">';
		   	html += '			<div class="games-th fdrelative">';
		   	html += '				<div class="j-site" style="position: relative;margin-top: 15px;">'; 
		   	html += '					<span class="j1">'+match.game_name+'</span>';
			html += '					<span class="j1">'+match_date.format("hh:mm")+'</span>';
			html += '				</div>';
			html += '			</div>';
			html += '			<div class="games-td" name="team" id="s_'+match.match_id+'">';
			html += '				<div class="j-site" name="match">';
			html += '					<span><img src="/statics/images/lol/logo/'+match.league+'/'+match.home_name+'.png" width="40" height="40" alt="'+match.home_name+'logo"></span>';
			html += '					<span class="jn fdrelative">胜<em>'+data.odds.s[data.odds.s.length-1].toFixed(2)+'</em>'+up(data.odds.s)+'</span>';
			html += '				</div>';
   			html += '			</div>';
			html += '			<div class="games-td-vs">'; 
			html += '				<div class="j-site" name="match" style="position: relative;margin-top: 15px;"><span name="frq">'+match.home_name+'&nbsp;vs&nbsp;'+match.away_name+'</span><span class="jn">'+match.game_type_name+'</span></div>'; 
			html += '			</div>';
			html += '			<div class="games-td" name="team" id="f_'+match.match_id+'"">';
			html += '				<div class="j-site" name="match">';
			html += '					<span><img src="/statics/images/lol/logo/'+match.league+'/'+match.away_name+'.png" width="40" height="40" alt="'+match.away_name+'logo"></span>';
			html += '					<span class="jn fdrelative">负<em>'+data.odds.f[data.odds.f.length-1].toFixed(2)+up(data.odds.f)+'</em></span>';
			html += '				</div>';
			html += '			</div>';
			html += '		</li>';
			html += '		<input name="endDate" value="201707208102000" type="hidden">';
		   	html += '	</ul>';
		   	html += '</dd>'; 
       }
       $("#box-games").html(html);
       this.ispaint = true;
       this.onBind();
	}
};

sf.prototype.onBind = function(){
	var _this = this;
	if(_this.ispaint){
		$("#box-games div[name=team]").click(function(){
			var ops = this.id.split("_");
			var match_id = ops[1],op = ops[0];
			
			$(".j-site",this).toggleClass("t-select");
			
			if(!_this.lottery[match_id]){
				_this.lottery[match_id] = new Set();
			}
			
			if($(".j-site",this).hasClass("t-select")){
				_this.lottery[match_id].add(op);
			}else{
				_this.lottery[match_id]["delete"](op);
			}
			
			_this.cal();
			
		});
	}
};

sf.prototype.cal = function(){
	var lots = this.lottery,min_odds = [],max_odds = [],nums = [],length = 0;
	for(var match_id in this.lottery){
		var ops = this.lottery[match_id];
		if(ops.size == 0){
			continue;
		}
		length++;
		var guess = this.guess[match_id];
	   	var match = this.matchs[match_id];
	   	var min_o = ops.size == 1 || guess.odds[ops.get(0)] < guess.odds[ops.get(1)] ? guess.odds[ops.get(0)] : guess.odds[ops.get(1)];
	   	var max_o = ops.size == 1 || guess.odds[ops.get(0)] > guess.odds[ops.get(1)] ? guess.odds[ops.get(0)] : guess.odds[ops.get(1)];
	   	
	   	min_odds[min_odds.length]=min_o[min_o.length-1];
	   	max_odds[max_odds.length]=max_o[max_o.length-1];
	   	nums[nums.length] = ops.size;
	} 
	function arrayMul(array){
		var val = 0;
		for(var i =0;i<array.length;i++){
			var min = parseFloat(array[i]);
			if(i == 0){
				val = min;
				continue;
			}
			val = val.mul(min);
		}
		return val;
	}
	var min_bonus = arrayMul(min_odds) * this.money,max_bonus = arrayMul(max_odds) * this.money,num = arrayMul(nums);
	var result = {"min":min_bonus.toFixed(2),"max":max_bonus.toFixed(2),"num":num,"match_size":length};
	$("#showNumber").html(result.match_size);
	$("#SimulateMoney").html(result.min == result.max ? result.min : result.min+"~"+result.max);
	
	return result;
}
var _sf = null;
$(function (){
	
	var league = $("input[name=league]").val();
	
	_sf = new sf(league);
});