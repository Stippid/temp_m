<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	HttpSession sess = request.getSession(false);
	if (sess.getAttribute("userId") == null){
		sess.invalidate();
		response.sendRedirect("/login"); return; 
	} 
%>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="Stylesheet" href="js/Calender/jquery-ui.css">
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>

<link href="js/JS_CSS/jquery.dataTables.min.css" rel="stylesheet">

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script>

<script>
var username="${username}";
</script>

<form:form action="" id="" method="post" class="form-horizontal"
	commandName="view_Daily_Morning_bed_stateCMD">
	<div class="card-body card-block">
		<div class="row">
			<div id="div_hd" class="card-header " style="text-align: center;">
				<h4>LAST MONTHLY MORNING BED STATE REPORT</h4>

			</div>
			<div class="col-md-3">
				<label id="unit_name1" name="unit_name1">
					<h5>Hospital Name: ${list[0][0]}</h5>
				</label>
			</div>

		</div>
	</div>
	<div class="nkpageland" id="printableArea">
		<div class="container">
			<div id="divShow" style="display: block;">
				<div id="divShow1" align="center" style="display: block;"></div>

				<div id="divSerachInput" class="col-md-12">
					<div class="col-md-6">
						<input id="searchInput" type="text"
							style="font-family: 'fontello', Arial; margin-bottom: 5px;"
							placeholder="Search Word" size="35" class="form-control">
					</div>
				</div>

				<div class="col-md-12" id="divPrint" style="display: block;">

					<div class="watermarked" data-watermark="" id="divwatermark"
						style="display: block;">
						<span id="ip"></span>
						<table id="SearchReport" border="1" style="width: 100%;"
							class="table no-margin table-striped  table-hover  table-bordered report_print">
							<thead>


								<tr style="text-align: center;">
									<th style="width: 10%;">Ser No</th>

									<th>Auth Bed</th>
									<th>Bed laid Out</th>
									<th>Total No Of Patients</th>
									<th>Sur</th>
									<th>Defi</th>
									<th>Date</th>

								</tr>
							</thead>
							<tbody>

								<c:forEach var="list" items="${list}" varStatus="num">
									<tr>

										<td style="width: 10%; text-align: center;">${num.index+1}</td>

										<td style="text-align: center;">${list[1]}</td>
										<td style="text-align: center;">${list[2]}</td>
										<td style="text-align: center;">${list[3]}</td>
										<td style="text-align: center;">${list[5]}</td>
										<td style="text-align: center;">${list[6]}</td>
										<td style="text-align: center;">${list[7]}</td>


									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>

			</div>
			<div class="form-control card-footer" align="center">


				<input type="button" class="btn btn-primary btn-sm btn_report"
					value="Print Page" id="btn_p" onclick="printDiv();">


			</div>
		</div>
	</div>

</form:form>

<c:url value="getview_morning_bed_Report" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="sus1">
	<input type="hidden" name="sus1" id="sus1" />
	<input type="hidden" name="unit1" id="unit1" />
	<input type="hidden" name="cmd1" id="cmd1" />
	<input type="hidden" name="frm_dt1" id="frm_dt1" />
	<input type="hidden" name="to_dt1" id="to_dt1" />
</form:form>



<script>

function parent_disable1() {
	if(newWin && !newWin.closed)
		newWin.focus();
}

function printDiv() {
	
	var printLbl = [];
	var printVal = [];
	
		

	
	
	$("#div_hd").hide();
	$("#btn_p").hide();
	
	 printDivOptimize12('divShow','  LAST MONTHLY MORNING BED STATE REPORT ',printLbl,printVal,"");
 	 		
	 $("#div_hd").show();
		$("#btn_p").show();
}
</script>
<script>
$(document).ready(function() {
	
	 $("#searchInput").hide();
	$("div#divwatermark").val('').addClass('watermarked');	
	watermarkreport();
	
	
	
	$("#searchInput").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#SearchReport tbody tr").filter(function() { 
		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});
	
	$().getCurDt(to_date);    
	
	
   
	if('${frm_dt1}' != "" || '${to_dt1}' != ""){
		$("#divPrint").show();
	}
	
	if('${size}' == 0 && '${size}' != ""){
		$("#divPrint").show();
	} 
	
	$("#divPrint").show();
	
	var q = '${sus1}';
	if(q != ""){ 
		$("#sus_no1").val(q);
	}
	
	var q1 = '${unit1}';
	if(q1 != ""){ 
		$("#unit_name1").val(q1);
	}
	
	if(q != "" && q1 != ""){
    	$("#command").attr('disabled',true);
    }
    
	var q2 = '${cmd1}';
    if(q2 != ""){
    	$("#command").val(q2);
	}
   
	
	var q6 = '${frm_dt1}';
	if(q6 != ""){ 
		$("#from_date").val(q6);
	}
	
	var q7 = '${to_dt1}';
	
	if(q7 != ""){ 
		$("#to_date").val(q7);
	}
	
	
	
	
	
	$('#unit_name1').change(function(){
		var y = this.value;
		
		$.post("getMNHSUSNoByUnitName?"+key+"="+value,{y:y},function(j) {
			var enc = j[j.length-1].substring(0,16);
			var a = dec(enc,j[0]);
			getCommand(a);		
		});
		
    }); 
	
	$('#sus_no1').change(function(){
		var y = this.value;
		getCommand(y);
    });
	 
	//Search();
	
	try{
		if(window.location.href.includes("msg=")){
			var url = window.location.href.split("?msg")[0];
		    window.location = url;
	    } 
	}catch (e) {
		// TODO: handle exception
	}	
});
</script>