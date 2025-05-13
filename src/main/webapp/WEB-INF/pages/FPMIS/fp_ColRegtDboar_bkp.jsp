<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="js/common/nrcss.css">
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/amchart4/core.js"></script>
<script src="js/amchart4/charts.js"></script>
<script src="js/amchart4/animated.js"></script>

<link rel="Stylesheet" href="js/Calender/jquery-ui.css">
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link rel="stylesheet" href="js/JS_CSS/jquery.dataTables.min.css">


<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>

<link href="js/JS_CSS/jquery.dataTables.min.css" rel="stylesheet">

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script>
var role = '${role}';
</script>
<style>
.button {
	/* Green */
	border: none;
	color: white;
	padding: 2px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin: 4px 2px;
	cursor: pointer;
}

.table tbody {
	display: block;
	max-height: 300px;
	overflow-y: scroll;
	width: 100%;
	scrollbar-width: thin;
}

.table thead, .table tbody tr {
	display: table;
	width: 100%;
	table-layout: fixed;
}

.table thead {
	width: calc(100% - 8px);
}
/* D3 */
#BlockDescTblList1 {
	border-collapse: collapse;
	-moz-border-radius: 10px;
	-webkit-border-radius: 10px;
	box-shadow: 0px 0px 22px #888;
	-moz-box-shadow: 0px 0px 22px #888;
	-webkit-box-shadow: 0px 0px 22px #888;
}

#BlockDescTblList1 td {
	padding: -1px;
	border: 1px solid black;
}

#BlockDescTblList1 th {
	text-align: center;
	padding: 7px 2px;
	border: 1px solid #000;
	background-color: #9c27b0;
	color: #fff;
}

#BlockDescTblList1 tr:nth-child(odd) {
	background-color: white;
}

#BlockDescTblList1 tr:nth-child(even) {
	background-color: #cedeef;
}

.highlight {
	background-color: #FFFF88;
}

table.dataTable.nowrap th, table.dataTable.nowrap td {
	white-space: normal;
}

.pagination {
	border-radius: 4px;
	display: inline-block;
	margin: 0px 0 -5px;
	padding-left: 0;
	width: 370px;
}

.paging_full_numbers {
	width: 370px !important;
}

.pagination>.active>a, .pagination>.active>a:focus, .pagination>.active>a:hover,
	.pagination>.active>span, .pagination>.active>span:focus, .pagination>.active>span:hover
	{
	z-index: 2;
	color: #000;
	cursor: default;
	background-color: #66cc99; /* #2191c0; */
	border: 2px solid #00664d; /* 1px solid #4297d7; */
	font-weight: bold;
}

table.dataTable.display tbody tr.odd>.sorting_1, table.dataTable.order-column.stripe tbody tr.odd>.sorting_1
	{
	background-color: white;
}

table.dataTable.display tbody tr.even>.sorting_1, table.dataTable.order-column.stripe tbody tr.even>.sorting_1
	{
	background-color: #cedeef;
}

.dataTables_wrapper .dataTables_filter {
	float: right;
	text-align: right;
	padding-left: 70%;
}

.nPopClosePOS{
	float:right;text-align:right;self-align:right;width: 50px;top: inherit;
	margin-top:10px;
	margin-right:20px;
	margin-left:90%;
}
.fullscr {
	background:lightgray;
	width:99vw;
	height:88vh;
	left:0px;
	top:60px;
	z-index:100;
	position:fixed;
	border:16px groove floralwhite!important;
	overflow:auto;
}
.info-box {
	width: 150px;
}

.col-dash {
	-webkit-box-flex: 0;
	-ms-flex: 0 0 13% !important;
	flex: 0 0 13% !important;
	max-width: 13% !important;
}

.table tr th:first-child {
	
}

.nChip {
	display:inline-block;
	padding:3 30px;
	height:63px;
	font-size:18px;
	line-height: 50px;
	border-radius: 25px 10px 10px 25px;
	border:2px solid gray!important;
	box-shadow: 0 10px 8px 0 rgba(0,0,0,.8);
	background-color: azure;
	cursor:pointer;
}

.nChipW {
	width:300px;
}	

.nChip img {
	float:left;
	margin:0 10px 0 0px;
	height:60px;
	width:50px;
	border-radius:50%;
	z-index:2;
}

.nChip span {
	width:inherit;
	COLOR:blue;
}

.nChipBlock {
	margin-top:25px;
	margin-bottom:5px;
}

.nChipDiv {
	position: fixed;
	top: 50px;
	left: 10px;
	height:90vh;
	border:3px solid red;
}

.Box {
	text-align:center;
	background-color:transparent;
	border:none;
}

.DBHead {
	width:100%;
	text-align:center;
	padding:7px 10px;
}

.DBHeadC1 {
	font-size:3.0em;
	color:blue;
}

.DBHeadC2 {
	font-size:1.6em;
	color:forestgreen;
}

.Box1 {
	font-size:1.4em;
}
.Box2 {
	font-size:0.8em;
}

a, a:hover, a:active, a:visited {
	color:blue;
}

.blink {
  animation: blinker 1.5s step-start infinite;
  color:forestgreen;
  text-shadow:5px red;
}

 @keyframes blinker {
  50% {
    opacity: 0;
  }
} 
</style>
<script>
	var role = "${role}";
</script>

<title>MISO - Wpn and Eqpt State</title>
<body onload="setMenu();" background="js/common/img/sikhlibg2a.jpg">
<div class="animated fadeIn">
<div class="row" style="align-items: flex-start !important;">
			<div class="nPopBody" style="height:125px;width:100%;background-color:transparent;color:blue;font-size:1.2em!important;border:0px inset!important;overflow: hidden;">												
				<div id="nMsgBoard" style="width:100%;height:inherit">
					<div class="col-md-1"><img src="js/common/img/sikhlilogo1.jpeg" style="width:100px;height:92px"></div>												
					<div class="col-md-9">
						<p class="DBHead DBHeadC1"><b>THE SIKH LIGHT INFANTRY</b></p>
						<!-- <p class="DBHead DBHeadC2 blink nPopHindiBold"><i>"Deg Teg Fateh"</i></p> -->
						<p class="DBHead DBHeadC2 blink1 nPopHindiBold" style="font-size:3em">*nsx rsx Qrsg*</p>
					</div>
					<div class="col-md-2"><img src="js/common/img/indianarmy.jpg" style="width:100px;height:92px;float:right;"></div>
				</div>
				<!-- <a class="nViewMore" href="fp_advisory_upd" onclick="localStorage.Abandon();" title="View More Info ...">View More ...</a> -->
			</div>
</div>
							<div class="row" style="align-items: flex-start !important;backgrond:url('js/common/img/sikhlibg1.jpg')">
							         <div class="col-md-4 nChipBlock" ondblclick="callPop('1');">							         			         	
							         	<div class="nChip nChipW" id="chartgauge_1" >							         		
							         		<img src="js/common/img/sikhlilogo2.jpg">
							         		<span data-head='Fund vs Allotment' class="form-control Box Box1">The Units</span>
							         		<!-- <span data-head='Fund vs Allotment' class="form-control Box Box2">DV</span> -->
							         	</div>
										<!-- <a class="nViewMore" href="fpmyDashboard?nPara=0" onclick="localStorage.Abandon();" title="Minor Head Wise BreakUp ...">Details ...</a> -->						         	
									 </div>
									 <div class="col-md-4 nChipBlock" ondblclick="callPop('2');">
							         	<div class="nChip nChipW" id="chartgauge_1" >
							         		<img src="js/common/img/sikhlilogo2.jpg">
							         		<span data-head='Fund vs Allotment' class="form-control Box Box1">Col of the Regts</span>
							         	</div>						         	
									 </div>
									 <div class="col-md-4 nChipBlock" ondblclick="callPop('3');">		         	
							         	<div class="nChip nChipW" id="chartgauge_1" >
							         		<img src="js/common/img/sikhlilogo2.jpg">
							         		<span data-head='Fund vs Allotment' class="form-control Box Box1">The Tigers</span>
							         	</div>
									 </div>
									 <div class="col-md-4 nChipBlock" ondblclick="callPop('4');">		         	
							         	<div class="nChip nChipW" id="chartgauge_1" >
							         		<img src="js/common/img/sikhlilogo2.jpg">
							         		<span data-head='Fund vs Allotment' class="form-control Box Box1">Sports</span>
							         	</div>
									 </div>
							         <div class="col-md-4 nChipBlock" ondblclick="callPop('5');">
							         	<!-- <span data-head='Fund vs Allotment' class="form-control spedoBox">Fund vs Allocation</span> -->		         	
							         	<div class="nChip nChipW" id="chartgauge_1" >
							         		<img src="js/common/img/sikhlilogo2.jpg">
							         		<span data-head='Fund vs Allotment' class="form-control Box Box1">The Gallants</span>
							         	</div>
										<!-- <a class="nViewMore" href="fpmyDashboard?nPara=0" onclick="localStorage.Abandon();" title="Minor Head Wise BreakUp ...">Details ...</a> -->						         	
									 </div>
									 <div class="col-md-4 nChipBlock" ondblclick="callPop('6');">
							         	<div class="nChip nChipW" id="chartgauge_1" >
							         		<img src="js/common/img/sikhlilogo2.jpg">
							         		<span data-head='Fund vs Allotment' class="form-control Box Box1">Veterans</span>
							         	</div>						         	
									 </div>
									 <div class="col-md-4 nChipBlock" ondblclick="callPop('7');">		         	
							         	<div class="nChip nChipW" id="chartgauge_1" >
							         		<img src="js/common/img/sikhlilogo2.jpg">
							         		<span data-head='Fund vs Allotment' class="form-control Box Box1">ERE</span>
							         	</div>
									 </div>
									 <div class="col-md-4 nChipBlock" ondblclick="callPop('8');">		         	
							         	<div class="nChip nChipW" id="chartgauge_1" >
							         		<img src="js/common/img/sikhlilogo2.jpg">
							         		<span data-head='Fund vs Allotment' class="form-control Box Box1">Manpower</span>
							         	</div>
									 </div>
							         <div class="col-md-4 nChipBlock" ondblclick="callPop('9');">
							         	<!-- <span data-head='Fund vs Allotment' class="form-control spedoBox">Fund vs Allocation</span> -->		         	
							         	<div class="nChip nChipW" id="chartgauge_1" >
							         		<img src="js/common/img/sikhlilogo2.jpg">
							         		<span data-head='Fund vs Allotment' class="form-control Box Box1">Comd Plg</span>
							         	</div>
										<!-- <a class="nViewMore" href="fpmyDashboard?nPara=0" onclick="localStorage.Abandon();" title="Minor Head Wise BreakUp ...">Details ...</a> -->						         	
									 </div>
									 <div class="col-md-4 nChipBlock" ondblclick="callPop('10');">
							         	<div class="nChip nChipW" id="chartgauge_1" >
							         		<img src="js/common/img/sikhlilogo2.jpg">
							         		<span data-head='Fund vs Allotment' class="form-control Box Box1">Veer Naris</span>
							         	</div>						         	
									 </div>
									 <div class="col-md-4 nChipBlock" ondblclick="callPop('11');">		         	
							         	<div class="nChip nChipW" id="chartgauge_1" >
							         		<img src="js/common/img/sikhlilogo2.jpg">
							         		<!-- <span data-head='Fund vs Allotment' class="form-control Box Box1">Mgt of Regtl Affairs</span> -->
							         		<a class="Box Box1" href="fpDOLetters" onclick="localStorage.Abandon();" title="Mgt of Regtl Affairs">Mgt of Regtl Affairs</a>							         		
							         	</div>
									 </div>
									 <div class="col-md-4 nChipBlock" ondblclick="callPop('12');">		         	
							         	<div class="nChip nChipW" id="chartgauge_1" >
							         		<img src="js/common/img/sikhlilogo2.jpg">
							         		<span data-head='Fund vs Allotment' class="form-control Box Box1">Offrs Course Profile</span>
							         	</div>
									 </div>
									 <div class="col-md-4 nChipBlock" ondblclick="callPop('13');">		         	
							         	<div class="nChip nChipW" id="chartgauge_1" >
							         		<img src="js/common/img/sikhlilogo2.jpg">
							         		<span data-head='Fund vs Allotment' class="form-control Box Box1" style="font-size:1.2em!important">JCO ORs Course Profile</span>
							         	</div>
									 </div>
									 <!-- <div class="col-md-4 nChipBlock" id="dbexpbkd">		         	
							         	<div class="nChip nChipW" id="chartgauge_1" >
							         		<img src="js/common/img/sikhlilogo2.jpg">
							         	</div>
									 </div>
							         <div class="col-md-4 nChipBlock" onclick="dbbreak('fndalt');">
							         	<span data-head='Fund vs Allotment' class="form-control spedoBox">Fund vs Allocation</span>		         	
							         	<div class="nChip nChipW" id="chartgauge_1" >
							         		<img src="js/common/img/sikhlilogo2.jpg">
							         	</div>
										<a class="nViewMore" href="fpmyDashboard?nPara=0" onclick="localStorage.Abandon();" title="Minor Head Wise BreakUp ...">Details ...</a>						         	
									 </div>
									 <div class="col-md-4 nChipBlock" id="">
							         	<div class="nChip nChipW" id="chartgauge_1" >
							         		<img src="js/common/img/sikhlilogo2.jpg">
							         	</div>						         	
									 </div>
									 <div class="col-md-4 nChipBlock" id="dbexpbkd">		         	
							         	<div class="nChip nChipW" id="chartgauge_1" >
							         		<img src="js/common/img/sikhlilogo2.jpg">
							         	</div>
									 </div> -->
								</div>
								<div class="col-md-8" style="margin-top:2px;">
									<%-- <c:set var="ln" value="0" />
									 <c:set var="ln" value="${ln+1}" />
							         <div class="col-md-12 form-control-sm" >
							         	<div class="nDBoardHeader bg-deep-pink" id="nbox_7" style="width:90%;padding:0px;height:50px!important;">New Lifting of Ban <span class="nRibbinHeadPre" id="nboxdata_7" onclick="javascript:getnewimpsdetl('XNR','7');">
							         	0</span></div>		         	
							         		<div id="box_7" class="nRibbinShape form-control" style="min-height:310px;width:90%;padding:0px;display:none;">						         		
								         		<div class="nRibbinDataDiv">
										 			<span class="nRibbinData" id="ndb1_b7" ><i style="font-size:18px;" class="fa fa-hourglass fa-spin"></i></span>								 				
										 		</div>
									 		</div>
									 	</div>
										 <div class="col-md-12 form-control-sm" id="div_fallot">
										 	<div class="nDBoardHeader bg-red" id="nbox_8" style="width:90%;padding:0px;height:50px!important;">Lifted Offr  <span class="nRibbinHeadPre" id="nboxdata_8" onclick="javascript:getnewimpsdetl('XNR','8');">
										 	0</span></div>		         	
								         	<div id="box_8" class="nRibbinShape form-control" style="min-height:310px;width:90%;;padding:0px;display:none;">							         		
								         		<div class="nRibbinDataDiv">
										 			<span class="nRibbinData" id="ndb1_b8"><i style="font-size:18px;" class="fa fa-hourglass fa-spin"></i></span>								 			
										 		</div>
										 	</div>
										 </div>
						 				<div class="col-md-12 form-control-sm" >
						 					<div class="nDBoardHeader bg-purple" id="nbox_5"  style="width:90%;padding:0px;height:50px!important;">New Lifting of Ban  <span class="nRibbinHeadPre" id="nboxdata_3" onclick="javascript:getnewimpsdetl('XNR','5');">0</span></div>		         	
								         	<div id="box_5" class="nRibbinShape form-control" style="min-height:310px;width:90%;;padding:0px;display:none;">							         		
								         		<div class="nRibbinDataDiv">
										 			<span class="nRibbinData" id="ndb1_b5"><i style="font-size:18px;" class="fa fa-hourglass fa-spin"></i></span>
										 		</div>
										 	</div>
									 </div>
									 <div class="col-md-12 form-control-sm" >
						 					<div class="nDBoardHeader bg-green" id="nbox_5"  style="width:90%;padding:0px;height:50px!important;">New Lifting of Ban  <span class="nRibbinHeadPre" id="nboxdata_3" onclick="javascript:getnewimpsdetl('XNR','5');">0</span></div>		         	
								         	<div id="box_5" class="nRibbinShape form-control" style="min-height:310px;width:90%;;padding:0px;display:none;">							         		
								         		<div class="nRibbinDataDiv">
										 			<span class="nRibbinData" id="ndb1_b5"><i style="font-size:18px;" class="fa fa-hourglass fa-spin"></i></span>
										 		</div>
										 	</div>
									 </div>				 --%>
									  <div id="ifrmDiv" class="col-md-12 form-control-sm nChipDiv" style="display:none;">	
									  		<div class="nPopClose nPopClosePOS" style="" onclick="javascript:nExpdBtn('ifrmDiv','12');"><span id="dbrdExpdBtn"  style="color:yellow;font-size:3.5em;" title="Close Window"><i class="fa fa-close"></i></span></div>								  
	                						<c:set var="dataf" value=''/>
											<iframe id="ttf" frameborder=0 src="${dataf}" style="width:100%;height:100%;top:0px;left:0px;"></iframe>
									  </div>
									  <%-- <div id="ifrmDiv1" class="col-md-6 form-control-sm">	
									  		<div class="nPopClose nPopClosePOS" style="" onclick="javascript:nExpdBtn('ifrmDiv1','6');"><span id="dbrdExpdBtn"  style="color:blue;font-size:1.4em;" title="Expand"><i class="fa fa-arrows-alt"></i></span></div>								  
	                						<c:set var="dataf" value="https://131.3.54.20:8443/open-view/12909/2c665fcd5584d2d4717e030b5b56c48c"/>
											<iframe frameborder=0 src="${dataf}" style="width:98%;height:98%;min-height:58vh;top:0px;left:0px;"></iframe>
									  </div> --%>				  							
								</div>							
							</div>
	<%-- <div class="row">
		<div class="col-md-12" align="center">
			<div class="card">
				<div id="DASHBOARD_tabs">
					<div>
						<c:if test="${nPara=='WPN'}">  <!-- Weapon -->
                				<c:set var="dataf" value="https://131.1.13.45:8443/open-view/7637/78904ef9080a019108faafdc893ff48c"/>
						</c:if>
						<c:if test="${nPara=='TPT'}"> <!-- Transport -->
                				<c:set var="dataf" value="https://131.3.54.7:8443/open-view/6611/5e52c06220e11cc968a6cb66a85a9fe8"/>
                				<c:set var="dataf" value="https://131.1.13.45:8443/open-view/7637/78904ef9080a019108faafdc893ff48c"/>
						</c:if>
						<c:if test="${nPara=='FP'}"> <!-- Finance -->
                				<c:set var="dataf" value="https://131.1.13.45:8443/open-view/7637/78904ef9080a019108faafdc893ff48c"/>
						</c:if>	
						<c:if test="${nPara=='COLR'}"> <!-- Col of Regt -->
                				<c:set var="dataf" value="https://131.3.54.99:8443/open-view/7630/03cde4397c41cfdb83590b62f5396bab"/>
						</c:if>					
						<c:set var="dataf" value="https://131.3.54.99:8443/open-view/7715/33334d5fd324ea67584b67d15296265b"/>
						<iframe frameborder=0 src="${dataf}" style="width:100%;height:80vh"></iframe>
					</div>	
				</div>
			</div>
		</div>
	</div> --%>
</div>
<div class="row">
	<div class="col-md-12">
		<span class="line"></span>
	</div>
</div>
<c:url value="fpDOLetters" var="backUrl" />
<form:form action="${backUrl}" method="post" id="m1_bkd" name="m1_bkd" modelAttribute="m1_nomen">
</form:form>
<script type="text/javascript">
	function callmgt(){
		$("#m1_bkd").submit();
	}

	$(document).ready(function() {
		setMenu();
		$("#openDB").click(function(){
			var opt="location=0,status=0,resizable=0,menubar=0,toolbar=0,titlebar=0,scrollbars=1,width="+screen.width+",height="+screen.height;
			//var winObj = window.open("https://demopc:8443/open-view/6678/28a00d4b40dcc4cd1349fd7af89e48c3","FP BI Reports",opt);
			/* var winObj = window.open("http://131.3.54.193:3000/public/dashboard/997eed7b-c793-40f5-b8c4-30533e5c2499","FP BI Reports",opt); */
			var winObj = window.open("http://131.3.54.193:3000/public/dashboard/783f3672-3226-43e0-aaa8-8f7fecf056f0","FP BI Reports",opt);
			
			winObj.onload = function(){
				console.log("loaded");	
			}
		});
		$(".nChipBlock").attr("title","Double Click to View Details.");
/* 		
		
		$("#unit_name1").val('${unit_name1}');
		var q = $("#fin_year").val();
		$("#fin_year1").val(q);
 */
	});
	
	function callPop(a){
		//alert(a);
		if (a=="1") {			
			var nb="https://131.1.13.54:8443/open-view/6707/7320db862d1d7874e6d5d29eadc1f15b";
		} else if (a=="2") {			
			var nb="https://131.1.13.54:8443/open-view/6698/755f2baca4ae0e89b7857e9a440c1474";
		} else if (a=="3") {			
			var nb="https://131.1.13.54:8443/open-view/6695/568e8654954a35572c7cedeee3f1b81b";
		} else if (a=="4") {			
			var nb="https://131.1.13.54:8443/open-view/6658/27de715601ea362fd063ac638cd8be4e";
		} else if (a=="5") {			
			var nb="https://131.1.13.54:8443/open-view/6673/25d1a5c2894e471097ff5e66a2466f1c";
		} else if (a=="6") {			
			var nb="https://131.1.13.54:8443/open-view/6763/ad79f06356be29733fcbbf698049ade0";
		} else if (a=="7") {			
			var nb="https://131.1.13.54:8443/open-view/5709/0f6a1952d7a13b161bbe2dc4283c592d";
		} else if (a=="8") {			
			var nb="https://131.1.13.54:8443/open-view/6692/ad25f7dde7394d123414201219b67c65";
		} else if (a=="9") {			
			var nb="https://131.1.13.54:8443/open-view/6757/405761c091f3173ae62b149e4d95523f";
		} else if (a=="10") {			
			var nb="https://131.1.13.54:8443/open-view/6769/eccdb35c63e99c96dcae73d5b37472c8";
		} else if (a=="11") {			
			callmgt();
		} else if (a=="12") {			
			var nb="https://131.1.13.54:8443/open-view/6638/e7524a77b910e1aca0bbafd981a92e7e";
		} else if (a=="13") {			
			var nb="https://131.1.13.54:8443/open-view/6316/f1816c2b0a525168c1262d4db937d88f";
		} else {
			var nb="https://131.1.13.54:8443/open-view/5709/0f6a1952d7a13b161bbe2dc4283c592d";
		}
		
		
		
		$("#ifrmDiv").show();
		$("#ttf").attr("src",nb);
		nExpdBtn('ifrmDiv','12');
	}

	function nExpdBtn(a,b){
		if ($("#"+a).hasClass("fullscr")) {
			$("#"+a).removeClass("fullscr");
			$("#"+a).hide();
		} else {
			$("#"+a).addClass("fullscr");
		}
	}

</script>