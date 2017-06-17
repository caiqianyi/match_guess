<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Index</title>
	<link rel="stylesheet" type="text/css" href="/plugin/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="/plugin/themes/icon.css">
	<script type="text/javascript" src="/plugin/jquery.min.js"></script>
	<script type="text/javascript" src="/plugin/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="/plugin/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="/plugin/commons.js"></script>
	<script type="text/javascript" src="/js/task-info.js"></script>
	<script type="text/javascript">
	function addTab(_title,_url){
		var boxId = '#centerTab';
		if($(boxId).tabs('exists',_title) ){
		    var tab = $(boxId).tabs('getTab',_title);
		    var index = $(boxId).tabs('getTabIndex',tab);
		    $(boxId).tabs('select',index);
		    if(tab && tab.find('iframe').length > 0){  
		        var _refresh_ifram = tab.find('iframe')[0];  
		        _refresh_ifram.contentWindow.location.href=_url;  
		    } 
		}else{  
		    var _content ="<iframe scrolling='auto' frameborder='0' src='"+_url+"' style='width:100%; height:100%'></iframe>";
		    $(boxId).tabs('add',{
		         title:_title,
		         content:_content,
		         closable:true});
		}
	}
	</script>
</head>
<body class="easyui-layout" style="overflow-y:hidden">
<!-- Header -->
	<div data-options="region:'north',border:false" style="overflow: hidden; height: 30px; line-height: 30px;">
        <span style="padding-left:10px; font-size: 16px;">闲徕互娱-任务管理</span>
    </div>
	
	<!-- 左边菜单导航  -->
	<div data-options="region:'west',title:'Navigation',iconCls:'icon-tip'" style="width:220px;">
		<div class="easyui-accordion" data-options="fit:true,border:false,animate:true,plain:true">
			<div title="Vegetables" style="padding:10px;">
				<ul class="easyui-tree">
					<c:forEach items="${jobGroups}" var="jobGroup">
						<li><a href="/index?jobGroup=${jobGroup}">${jobGroup}</a></li>
					</c:forEach>
					<li><a href="/index">ALL</a></li>
				</ul>
			</div>
		</div>
	</div>
	
	<!-- 中间内容页面  -->
	<div id="mainPanle" data-options="region:'center'" style="overflow-y:hidden">
		<div id="centerTab" class="easyui-tabs" data-options="fit:true,border:false">
			<div title="Home" style="padding:0px;" data-options="iconCls: 'icon-home'">
				<!-- Grid -->
				<table id="taskinfo-grid"></table>
				
				<!-- ToolBar -->
				<div id="taskinfo-tbar">
					<table cellpadding="0" cellspacing="0" style="width:100%">
						<tr>
							<td>
								<a href="#" class="easyui-linkbutton" iconCls="grid-add" plain="true" onclick="TaskInfo.addTaskInfo()">Add</a>
								<div class="datagrid-btn-separator" style="vertical-align: middle; height: 15px;display:inline-block;float:none"></div>
								<a href="#" class="easyui-linkbutton" iconCls="grid-edit" plain="true" onclick="TaskInfo.updateTaskInfo()">Edit</a>
								<div class="datagrid-btn-separator" style="vertical-align: middle; height: 15px;display:inline-block;float:none"></div>
								<a href="#" class="easyui-linkbutton" iconCls="grid-del" plain="true" onclick="TaskInfo.deleteTaskInfo()">Remove</a>
							</td>
						</tr>
					</table>
				</div>
				<!-- 窗口Window -->
				<div id="taskinfoDialog" style="padding:10px 0px; display: none;">
					<form id="taskinfoForm" class="easyui-form" method="post" data-options="novalidate:true">
						<table style="width: 650px;margin: 0 auto;">
							<tr>
								<td>JobName</td>
								<td>
									<select class="easyui-combobox" name="jobName" style="width:100%;" data-options="required:true">
										<c:forEach items="${jobClassNames}" var="jobName">
											<option value="${jobName }">${jobName }</option>
										</c:forEach>
									</select>
								</td>
								<td width="100"></td>
							</tr>
							<tr>
								<td>JobGroup</td>
								<td>
									<select class="easyui-combobox" name="jobGroup" style="width:100%;" data-options="required:true">
										<c:forEach items="${jobGroups}" var="jobGroup">
											<option value="${jobGroup }">${jobGroup }</option>
										</c:forEach>
									</select>
								</td>
								<td width="100"></td>
							</tr>
							<tr>
								<td>CronExpression</td>
								<td>
									<input class="easyui-textbox" name="cronExpression" data-options="required:true" style="width:100%">
								</td>
								<td width="100"></td>
							</tr>
							<tr>
								<td>配置说明</td>
								<td colspan="2">
									<table>
										<tr><td> 1）- 用于指定一个范围。例如:0 45 3-8 ? * *   意义：在上午的3点至上午的8点的45分时触发</td></tr>
										<tr><td> 2）* 指示着在这个域上包含所有合法的值。例如：0 * 17 * * ?   意义：每天从下午5点到下午5:59中的每分钟激发一次</td></tr>
										<tr><td> 3）/ 斜杠 (/) 是用于时间表的递增的。例如：0/15 0/30 * * * ?  意义：在整点和半点时每15秒触发</td></tr>
										<tr><td> 4）? 只能用在日和周域上，但是不能在这两个域上同时使用。你可以认为? 字符是 "我并不关心在该域上是什么值。"</td></tr>
										<tr><td> 5）， 逗号 (,) 是用来在给某个域上指定一个值列表的。 例如： 0 0,15,30,45 * * * ?  意义：每个15秒触发一次</td></tr>
										<tr><td> 6）L 字母：L 说明了某域上允许的最后一个值。它仅被日和周域支持。当用在日域上，表示的是在月域上指定的月份的最后一天</td></tr>
										<tr><td> 7）W 字母：W 字符代表着平日 (Mon-Fri)，并且仅能用于日域中。它用来指定离指定日的最近的一个平日</td></tr>
										<tr><td> 8）# 仅能用于周域中。它用于指定月份中的第几周的哪一天。例如，如果你指定周域的值为6#3，它意思是某月的第三个周五。</td></tr>
										<tr><td> 注意，假如你指定#5，然而月份中没有第 5 周，那么该月不会触发</td></tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>字符说明</td>
								<td colspan="2">
									<table>
										<tr><td>---------------------------------------------------字符解释--------------------------------------------</td></tr>
										<tr><td> 1）- 用于指定一个范围。例如:0 45 3-8 ? * *   意义：在上午的3点至上午的8点的45分时触发</td></tr>
										<tr><td> 2）* 指示着在这个域上包含所有合法的值。例如：0 * 17 * * ?   意义：每天从下午5点到下午5:59中的每分钟激发一次</td></tr>
										<tr><td> 3）/ 斜杠 (/) 是用于时间表的递增的。例如：0/15 0/30 * * * ?  意义：在整点和半点时每15秒触发</td></tr>
										<tr><td> 4）? 只能用在日和周域上，但是不能在这两个域上同时使用。你可以认为? 字符是 "我并不关心在该域上是什么值。"</td></tr>
										<tr><td> 5）， 逗号 (,) 是用来在给某个域上指定一个值列表的。 例如： 0 0,15,30,45 * * * ?  意义：每个15秒触发一次</td></tr>
										<tr><td> 6）L 字母：L 说明了某域上允许的最后一个值。它仅被日和周域支持。当用在日域上，表示的是在月域上指定的月份的最后一天</td></tr>
										<tr><td> 7）W 字母：W 字符代表着平日 (Mon-Fri)，并且仅能用于日域中。它用来指定离指定日的最近的一个平日</td></tr>
										<tr><td> 8）# 仅能用于周域中。它用于指定月份中的第几周的哪一天。例如，如果你指定周域的值为6#3，它意思是某月的第三个周五。</td></tr>
										<tr><td> 注意，假如你指定#5，然而月份中没有第 5 周，那么该月不会触发</td></tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>Description</td>
								<td colspan="2">
									<input class="easyui-textbox" name="jobDescription" style="width:100%;height:60px" data-options="multiline:true,required:true">
									<input type="hidden" name="id" value="0">
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div> 
	</div>
<script type="text/javascript">
(function($) {
	TaskInfo.init({jobGroup:{name:"${jobGroup.name}",value:"${jobGroup}"}});
})(jQuery);
</script>
</body>
</html>
