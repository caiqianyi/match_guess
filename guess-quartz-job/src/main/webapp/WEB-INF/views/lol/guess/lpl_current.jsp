<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>lpl 比赛竞猜</title>
<script type="text/javascript" src="/plugin/jquery.min.js?v=20170531"></script>
<script type="text/javascript">
$(function (){
	function lpl_match_data(){
		$.ajax({
			url: '/matchs/lpl_guess_list/current/?t=' + Math.random(),
			type: 'GET',
			dataType: 'JSON',
			success: function(datas) {
				for(var i=0;i<datas.length;i++){
					var item = datas[i];
					var matchId = item.matchId,sportName = item.sportName;//赛事名称
					var homeName = item.homeName,homeLogoUrl = item.homeLogoUrl;
					var awayName = item.awayName,awayLogoUrl = item.awayLogoUrl;
					var startTime = item.startTime;
					var matchState = item.matchState;//比赛状态  20未开始
					var matchType = item.matchType;
					var result = item.result;
					var dayTxt = item.timeTxt,timeTxt = item.timeTxt;//比赛日期
					var topicList = item.topicList;
					
					
					var html = "";
					
					html+='<div class="item">';
					html+='	<h6 class="guess-team-title">'+sportName+'</h6>';
					html+='	<span>'+dayTxt+'</span> <strong>'+timeTxt+'</strong></time>';
					html+='	<div class="cpm-hide">';
					html+='		'+result+'';
					html+='	</div>';
					html+='	<h6 class="guess-team-title">比赛状态：'+matchState+'</h6>';
					html+='	<img src="'+homeLogoUrl+'" alt="true">';
					html+='	<span class="guess-team-name">'+homeName+'</span>';
					html+='	<span class="guess-team-vs">VS</span><span class="guess-team-visitors"><span>243950</span>';
					html+='	<img src="'+awayLogoUrl+'" alt="true">';
					html+='	<span class="guess-team-name">'+awayName+'</span>';
					
					for(var j=0;j<topicList.length;j++){
						html+='	<div class="">';
						html+='		<h6 class="guess-team-desc">'+topicList[j].guessContent+'</h6>';
						html+='		<div class="guess-question-option">';
						html+='		<button class="btn" type="button" data-index="A">';
						html+='		<span class="guess-question-option-title">'+topicList[j].optionFormat.A.desc+'</span>';
						html+='		<span class="guess-question-option-desc">'+topicList[j].optionFormat.A.odds+'</span>';
						html+='		</button>';
						html+='		<button class="btn" type="button" data-index="C">';
						html+='			<span class="guess-question-option-title">'+topicList[j].optionFormat.B.desc+'</span>';
						html+='			<span class="guess-question-option-desc">'+topicList[j].optionFormat.B.odds+'</span>';
						html+='		</button>';
						html+='		<p class="text-muted">竞猜金额</p>';
						html+='		<p class="text-muted">预测奖励</p>';
						html+='		<strong class="text-strong">0</strong>';
						html+='		<button class="btn btn-primary" type="button"';
						html+='		data-pe="click:_es.lol.pay">立即竞猜</button>';
						html+='		</div>';
						html+='	</div>';
						html+='</div>';
					}
					
					$("body").html(html);
				}
			},
			error: function() {
				//alert();
			}
		});
	}
	lpl_match_data();
});
</script>
</head>
<body>
	
</body>
</html>