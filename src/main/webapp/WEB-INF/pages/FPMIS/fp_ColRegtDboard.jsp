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
var curScrNo=1;
var actbtn="N";
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
	background: lightgray;
    width: 99vw!important;
    height: 97vh!important;
    left: 10px!important;
    top: 10px!important;
    z-index: 1000!important;
    position: fixed!important;
    border: 16px groove floralwhite!important;
    overflow: auto;
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

.nChipgp {
	display:inline-block;
	font-size:1.3em;
	line-height: 30px;
	border:0px solid red!important;
	box-shadow: 0 10px 8px 0 rgba(0,0,0,.8);
	/* background-color: rgb(190 210 100 / 45%); */
	background-color: burlywood;
	cursor:pointer;
	width:100%;
	padding:0px;
	margin:10px;
	padding-left:20px;
	color:blue;
	font-weight: bold;
	
}


.nChip {
	display:inline-block;
	/* padding:3 30px; */
	height:53px;
	font-size:1.1em;
	line-height: 50px;
	border-radius: 25px 10px 10px 25px;
	border:2px solid brown!important;
	box-shadow: 0 10px 8px 0 rgba(0,0,0,.8);
	background-color: rgb(190 210 100 / 45%);
	cursor:pointer;
}

.nChipW {
	width:300px;
}	

.nChip img {
	float:left;
	margin:0 10px 0 0px;
	height:50px;
	width:40px;
	border-radius:50%;
	z-index:2;
}

.nChip span {
	width:inherit;
	COLOR:blue;
}

.nChipBlock {
	margin-top:5px;
	margin-bottom:5px;
}

.nChip:hover {
	background-color:rgb(0 255 0 / 90%);
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
	border: 0px solid transparent!important; 
}

.DBHead {
	width:100%;
	text-align:center;
	padding:7px 10px;
}

.DBHeadC1 {
	font-size:3.0em;
	color:blue;
	text-shadow: 5px 3px 5px yellow;
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

<title>MISO - Col of Regt</title>
<body onload="setMenu();">
<c:if test="${nBase[0][4] ==null}">
	<div class="animated fadeIn">
</c:if>
<c:if test="${nBase[0][4] !=null}">
	<c:set var="dataf" value="${fn:split(nBase[0][4],':')}"/>
	<c:if test="${dataf[0] =='I'}">
		<div class="animated fadeIn" style="min-height:70vh;background:url('js/common/img/${dataf[1]}');background-size:100% 100%;">
	</c:if>
	<c:if test="${dataf[0] =='C'}">
		<div class="animated fadeIn" style="height:70vh;background-color:${dataf[1]}">
	</c:if> 
</c:if>
		<c:if test="${role=='COR' or role == 'cor'}">
		<div class="nMsgHeader" style="width:100%!important;text-align: left;padding-left:30px;height:40px;"">
			<span style="font-size:2em;">COL OF REGT</span>
		</div>
		</c:if>
		
		<div class="row" style="align-items: flex-start !important;">
			<div class="nPopBody" style="height:125px;width:100%;background-color:transparent;color:blue;font-size:1.2em!important;border:0px inset!important;overflow: hidden;">												
				<div id="nMsgBoard" style="width:100%;height:inherit">
					<div class="col-md-1">
						<c:if test="${fn:length(nBase[1][4])>0}">
							<img src="js/common/img/${nBase[1][4]}" style="width:100px;height:92px;box-shadow: 0 10px 8px 0 rgba(0,0,0,.8);">
						</c:if>
					</div>												
					<div class="col-md-9">
						<p class="DBHead DBHeadC1" style="color:${nBase[2][8]};"><b>${nBase[2][4]}</b></p>
						<c:if test="${fn:length(nBase[2][5])>0}">							
							<p class="DBHead DBHeadC2 blink1 nPopHindiBold" style="font-size:1.5em;color:${nBase[2][8]}">*${nBase[2][5]}*</p>
						</c:if>						
					</div>
					<div class="col-md-2">
						<c:if test="${fn:length(nBase[1][5])>0}">
							<img src="js/common/img/${nBase[1][5]}" style="width:100px;height:92px;float:right;box-shadow: 0 10px 8px 0 rgba(0,0,0,.8);">
						</c:if>
					</div>
				</div>
			</div>
		</div>
		<div class="row" style="align-items: flex-start !important;">
			<c:set var="tabgp" value="0"></c:set>
			<c:forEach var="itemm" items="${nBase}" varStatus="num">
								
				<c:if test="${itemm[2] =='4'}">
					<c:if test="${itemm[13] != tabgp}">
						<div class="nChipgp">${itemm[12]}</div>
						<c:set var="tabgp" value="${itemm[13]}"></c:set>											
					</c:if>
		         	<div class="col-md-4 nChipBlock" ondblclick="callPop('${itemm[6]}','${itemm[11]}');">							         			         	
			         	<div class="nChip nChipW nGlowBtn" id="chartgauge_1" >
			         		<c:if test="${fn:length(itemm[4])>0}">							         		
			         			<img src="js/common/img/${itemm[4]}">
			         		</c:if>
			         		<span class="form-control Box Box1" style="color:${nBase[2][8]}!important;">${itemm[5]}</span>
			         	</div>
					</div>
				</c:if>
			</c:forEach>
			<div class="col-md-8" style="margin-top:2px;">
				<div id="ifrmDiv" class="col-md-12 form-control-sm nChipDiv" style="display:none;">	
			  		<div class="nPopClose nPopClosePOS" style="" onclick="javascript:nExpdBtn('ifrmDiv','12');"><span id="dbrdExpdBtn"  style="color:yellow;font-size:3.5em;" title="Close Window"><i class="fa fa-close"></i></span></div>								  
           						<c:set var="dataf" value=''/>
					<iframe id="ttf" frameborder=0 src="${dataf}" style="width:100%;height:100%;top:0px;left:0px;"></iframe>
				</div>
			</div>
		</div>
</div>														
<c:url value="fpDOLetterss" var="backUrl" />
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
	
	function callPop111(a){
		alert(a);
		/* var pg='${nBase}';
		for (var b=1;b<=pg.length;b++) {
			console.log(pg[b]);
		} */
		var nb="https://131.1.13.54:8443/open-view/5709/0f6a1952d7a13b161bbe2dc4283c592d";
		var nb=a;
		$("#ifrmDiv").show();
		$("#ttf").attr("src",nb);
		nExpdBtn('ifrmDiv','12');
	}

	function callPop_bkp(a){
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
	
	function callPopqqq(a){
		var opt="location=0,status=0,resizable=0,menubar=0,toolbar=0,titlebar=0,scrollbars=1,width="+screen.width+",height="+screen.height;
		var winObj = window.open(a,"FP BI Reports",opt);		
		winObj.onload = function(){
			console.log("loaded");	
		}
	}
	
	function callPop(a,b){
		//alert(a+","+b);
		if(a==null || a=='' || a=='undefined') {
			alert("No Mapping Found.");
			return false;
		} 
		if (b=='GPH') {
			$("#ifrmDiv").show();
			$("#ttf").attr("src",a);
			nExpdBtn('ifrmDiv','12');
		} else {
			callmgt();
		}
	}
</script>