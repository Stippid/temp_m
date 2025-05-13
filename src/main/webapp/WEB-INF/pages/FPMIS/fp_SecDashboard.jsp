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
</style>
<script>
	var role = "${role}";
	var username="${username}";
	var screenurl="${screenurl}"
</script>

<title>MISO DashBoards</title>
<body onload="setMenu();">
<div class="animated fadeIn">
	<div class="row">
		<div class="col-md-12" align="center">
			<div class="card">
				<div id="DASHBOARD_tabs">
					<div>
						<c:set var="susno" value="${roleSusNo}"></c:set>
						<c:if test="${nPara=='WPN'}">  <!-- Weapon -->
                				<c:set var="dataf" value="https://misoanalytics.army.mil:8443/open-view/10674/c637fae9172e8a5d4aab1b29c7fe54f4"/>
						</c:if>
						

						<c:if test="${nPara=='FPT16'}"> <!--Dashboard for emim wpn --ROLE(app_emim) -->
                				<c:set var="dataf" value="${screenurl}"/> 	
						</c:if>

						<c:if test="${nPara=='FPT20'}"> <!--Dashboard for TMS-->
                				<c:set var="dataf" value="${screenurl}"/>                				
						</c:if>

						<c:if test="${nPara=='FPT52'}"> <!--Dashboard for sd changed on 26dec 23 cue-->
                				<c:set var="dataf" value="${screenurl}"/>      
						</c:if>

						<c:if test="${nPara=='FPT24'}"> <!--Dashboard for psg-->
                				<c:set var="dataf" value="${screenurl}"/> 
						</c:if>

						<c:if test="${nPara=='FPT25'}"> <!--Dashboard for it asset (not yet assigned)-->
                				<c:set var="dataf" value="${screenurl}"/>     
						</c:if>

						<c:if test="${nPara=='FPT26'}"> <!--Dashboard for login-->
                				<c:set var="dataf" value="${screenurl}"/>    
						</c:if>
						
						<c:if test="${nPara=='FPT27'}"> <!--Cor MAHAR LINK added on 21 feb 24-->
                				<c:set var="dataf" value="${screenurl}"/>     
						</c:if>
						
						<c:if test="${nPara=='FPT28'}"> <!--SD-9 LINK added on 21 feb 24-->
                				<c:set var="dataf" value="${screenurl}"/>     
						</c:if>

						<c:if test="${nPara=='FPT29'}"> <!--ORBAT LINK added on 21 feb 24-->
                				<c:set var="dataf" value="${screenurl}"/>     
						</c:if>
						
						<c:if test="${nPara=='FPT30'}"> <!--CoR link for mgo em im added on 22 feb 24-->
                				<c:set var="dataf" value="${screenurl}"/>    
						</c:if>
				
						<c:if test="${nPara=='FPT31'}"> <!--ddg it on 2024  march 05 -->
                				<c:set var="dataf" value="${screenurl}"/>    
						</c:if>

						<c:if test="${nPara=='FPT32'}"> <!--Reports n Returns added on 21 Mar 24 for Arty-->
                				<c:set var="dataf" value="${screenurl}"/> 
						</c:if>
				<c:if test="${nPara=='FPT33'}"> <!--avn dte (451 unit) added on 19 April 24-->
                				<c:set var="dataf" value="${screenurl}"/>  
						</c:if>
						<c:if test="${nPara=='FPT34'}"> <!--  (gso1_aad6 unit) added on 03 May 24-->
                				<c:set var="dataf" value="${screenurl}"/>     
						</c:if>
						
						<c:if test="${nPara=='FPT35'}"> <!-- For gso1_mech9  added on 06 May 24 -- pswd- adgmechinf-->
                				<c:set var="dataf" value="${screenurl}"/>  
						</c:if>

						<c:if test="${nPara=='FPT36'}"> <!-- For HQ Base Wksp -- hqbwgp - password-->
                				<c:set var="dataf" value="${screenurl}"/>   
						</c:if>
		<c:if test="${nPara=='FPT37'}"> <!-- For ddgit dashboard -->
                				<c:set var="dataf" value="${screenurl}"/>     
						</c:if>
						<c:if test="${nPara=='FPT38'}"> <!--  (col_aad6 unit) added for vcoas visit 24/06/24-->
                				<c:set var="dataf" value="${screenurl}"/>    
						</c:if>
						<c:if test="${nPara=='FPT39'}"> <!--  (colad8_aad8 unit) added for vcoas visit 24/06/24-->
                				<c:set var="dataf" value="${screenurl}"/>     
						</c:if>
						
						<c:if test="${nPara=='FPT40'}"> <!--  (ALL INDIA HOLDING REPORT : 2024-25 mgd/mo/gs/os-->
                				<c:set var="dataf" value="${screenurl}"/> 
						</c:if>

						<c:if test="${nPara=='FPT41'}"><!--(dgarmd corps : gso1_ac4-->
						<c:set var="dataf" value="${screenurl}"/> 
						</c:if>

						<c:if test="${nPara=='FPT42'}"><!--(mcmm_jablapur : -->
						<c:set var="dataf" value="${screenurl}"/> 
						</c:if>

						<c:if test="${nPara=='FPT43'}"><!--(arty :09/aug/24 -->
						<c:set var="dataf" value="${screenurl}"/> 
						</c:if>

					<c:if test="${nPara=='FPT44'}"> <!--(g1_dgol added on 17/oct/24 -->
						<c:set var="dataf" value="${screenurl}"/> 
						</c:if>
						
						<c:if test="${nPara=='FPT45'}"> <!--(for ddg  added on 28/nov/24 link given by poonam maam-->
						<c:set var="dataf" value="${screenurl}"/> 
						</c:if>
						
<!-- 						"https://misoanalytics.army.mil:8443/open-view/222739/fcbff5447d6dcf97139b0a337256e89f -->

			<c:if test="${nPara=='FPT46'}"> <!--(for ddg  added on 29/nov/24 link given by mms jd-->
						<c:set var="dataf" value="${screenurl}"/> 
						</c:if>
						
						<c:if test="${nPara=='FPT47'}"> <!--(for ddg  added on 29/nov/24 link given by mms jd-->
						<c:set var="dataf" value="${screenurl}"/> 
						</c:if>
						
						<c:if test="${nPara=='FPT91'}"> <!--SD-9 LINK added on 21 feb 24-->
                				<c:set var="dataf" value="${screenurl}"/>    
						</c:if>
						<c:if test="${nPara=='FPT92'}"> <!--TMS link added on 191224 for mgoemim-->
                				<c:set var="dataf" value="${screenurl}"/> 
						</c:if>
						<c:if test="${nPara=='FPT93'}"> <!--TMS link added on 191224 for TMS role-->
                				<c:set var="dataf" value="${screenurl}"/>  
						</c:if>
						<c:if test="${nPara=='FPT94'}"> <!--TMS link added on 311224 for DGOL role-->
                				<c:set var="dataf" value="${screenurl}"/>     
						</c:if>

						<iframe frameborder=0 src="${dataf}" style="width:100%;height:78vh"></iframe> </div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-md-12">
		<span class="line"></span>
	</div>
</div>

<script type="text/javascript">
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
/* 		
		
		$("#unit_name1").val('${unit_name1}');
		var q = $("#fin_year").val();
		$("#fin_year1").val(q);
 */
	});
</script>
