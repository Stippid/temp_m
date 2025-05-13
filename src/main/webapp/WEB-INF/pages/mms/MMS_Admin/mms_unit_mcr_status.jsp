<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<link rel="Stylesheet" href="js/Calender/jquery-ui.css" >
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<link rel="stylesheet" href="js/common/nrcss.css">

<script type="text/javascript" src="js/miso/miso_js/jquery-1.12.3.js"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/amchart4/core.js"></script>
<script src="js/amchart4/charts.js"></script>
<script src="js/amchart4/animated.js"></script>
<!-- <link rel="stylesheet" href="js/miso/tmsDashboard/tmsDashboardCSS.css"> -->

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>



<script>
var username="${username}";
var curDate = "${curDate}";

</script>
<body class="mmsbg">
<% int ntotln=0; %>
<div id="nrWaitLoader" style="display:none;" align="center">
	<span id="">Processing Data.Please Wait ...</span>
</div>

<form action="" method="post" class="form-horizontal"> 
<div class="nkpageland" id="printableArea">
     <div class="container" align="center" id="headerView" style="display: block;">  
    		<div class="card"> 

	    
		 <div class="card-header mms-card-header">
		       <b>UNIT MCR STATUS</b>
		 </div> 
		 
		 <div class="col-md-12" style="margin-top: 10px;">
						
							<div class="col-md-6">
							
							
							<div class="row form-group">
								<div class="col-md-4" style="text-align: left;">
									<label class=" form-control-label"> SUS No </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="unit_sus_no" name="unit_sus_no"
										class="form-control autocomplete" autocomplete="off"
										maxlength="8" >
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4" style="text-align: left;">
									<label class=" form-control-label"> Unit Name </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="unit_name" name="unit_name"
										class="form-control autocomplete" autocomplete="off"
										maxlength="50"
										onkeyup="return specialcharecter(this)">
								</div>
							</div>
						</div>
					</div>
					
					<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
							    	<div class="col col-md-4" style="text-align: left;">
							        	<label class=" form-control-label"> Command</label>
							       	</div>
							        <div class="col-12 col-md-8">
							           	<select id="cont_comd" name="cont_comd" class="form-control-sm form-control" >
											${selectcomd}
								            <c:forEach var="item" items="${getCommandList}" varStatus="num" >
								            	<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
                  							</c:forEach>
                  						</select>
							    	</div>
							  	</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
		                			<div class="col col-md-4" style="text-align: left;">
		                  				<label class="form-control-label"> Corps</label>
		               				</div>
		                			<div class="col-12 col-md-8">
		                 				<select id="cont_corps" name="cont_corps" class="form-control-sm form-control" >
		                 					${selectcorps}
		                 					<c:forEach var="item" items="${getCorpsList}" varStatus="num" >
                  								<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
                  							</c:forEach>
		                 				</select>
		                			</div>
				              	</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
					                <div class="col col-md-4" style="text-align: left;">
					                  <label class=" form-control-label"> Division</label>
					                </div>
					                <div class="col-12 col-md-8">
					                 	<select id="cont_div" name="cont_div" class="form-control-sm form-control" >
					                 		${selectDiv}
					                 		<c:forEach var="item" items="${getDivList}" varStatus="num" >
                  								<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
                  							</c:forEach>
					                 	</select>
					                </div>
					            </div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
	                				<div class="col col-md-4" style="text-align: left;">
	                  					<label class=" form-control-label" > Brigade</label>
	                				</div>
	                				<div class="col-12 col-md-8">
	                 					<select id="cont_bde" name="cont_bde" class="form-control-sm form-control" >
	                 						${selectBde}
	                 						<c:forEach var="item" items="${getBdeList}" varStatus="num" >
                  								<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
                  							</c:forEach>
	                 					</select>
	                				</div>
				            	</div>
							</div>
						</div>
		 				<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
					                <div class="col col-md-4" style="text-align: left;">
              						   <label for="text-input" class=" form-control-label">DEO</label>
          							</div>
         							<div class="col-12 col-md-8">
		                				 <select name="domainid" id="domainid" class="form-control-sm form-control" >	
											<option value="ALL">-- ALL DEOs --</option>
										<c:forEach var="item" items="${ml_1}">
										<option value="${item[0]}" name="${item[1]}">${item[1]}</option>	
										</c:forEach>                  							
			     						</select>
		   							</div>
					            </div>
							</div>
		 					<div class="col-md-6">
								<div class="row form-group">
	                				<div class="col col-md-4" style="text-align: left;">
                 					<label class=" form-control-label"><strong style="color: red;">* </strong>Period</label>
           							</div>
           						<div class="col-12 col-md-8">
                 				<input type="month" id="mnth_year" name="mnth_year" class="form-control-sm form-control"/>
		  						</div>
				            	</div>
							</div>
						</div>   
		
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
					                <div class="col col-md-4" style="text-align: left;">
                 						<label for="text-input" class=" form-control-label">Status</label>
           							</div>
           							 <div class="col-12 col-md-8">
               							  <select name="statusid" id="statusid" class="form-control-sm form-control" >	
											<option value="ALL">-- ALL STATUS --</option>
											<%-- <c:forEach var="item" items="${ml_2}"> --%>
											<option value="0" name="DEFAULTER">DEFAULTER</option>
											<option value="1" name="APPROVED">APPROVED</option>
												<%-- </c:forEach> --%>                  							
			    							</select>
		    						</div>	
								</div>
							</div>
						</div>
		
	    <div class="card-footer" align="center" style="margin-top: -10px;">
			<a href="mms_unit_mcr_status" type="reset" class="btn btn-primary btn-sm" onclick="btn_clc();"> Clear </a> 
            <input type="button" class="btn btn-success btn-sm" value="Search" onclick="mms_list_mcr_status();">
            <a href="mmsDashboard"><input type="button" class="btn btn-danger btn-sm" value="Cancel"></a>
        </div>         
   </div>
</div>	

<div id ="tdheaderView" style="display: none;"  align="center" class="nrTableHeading"> <b>UNIT MCR STATUS</b></div>
<div class="card" id="re_tb" style="display: none;background: transparent;">

<div width="100%"> 
							
						<div class="nrTableMain2Search" align="left" id = "SearchViewtr"> 
									<label>Search in Result(<span id="ntotln"></span>)</label>&nbsp;:&nbsp;
									<input id="nrInput" type="text" placeholder="Search..." size="35" style="font-weight: normal;font-size: 14px;">
							    </div>
			    		
</div>						

  <div  class="watermarked" data-watermark="" id="divwatermark" >
	<span id="ip"></span> 
	     <div id="" class="nrTableMainDiv">
		
		            <input type="hidden" id="selectedid" name="selectedid"> 
		            <input type="hidden" id="nrSrcSel" name="nrSrcSel">
		            
            				   <div class="nrTableDataDiv">
								 <table  border="1" class="report_print" width="100%">
	                        		<thead>
	                          			<tr class="nrTableDataHead" style="font-size: 12px; text-align:center;" >
                        				<th class="nrBox" width="10%">DEO</th>
                    					<th class="nrBox" width="20%">Imdt Fmn</th>
                    					<th class="nrBox" width="10%">Sus No</th>
                    					<th class="nrBox" width="20%">Unit Name</th>
                    					<th class="nrBox" width="10%">Status</th>
                    					<th class="nrBox" width="10%">Observations</th>
            							<th class="nrBox" width="10%">Pending</th>
            							<th class="nrBox" width="10%">Resolved</th>
            							</tr>
                        			</thead>
    								<tbody id="nrTable">
	    								   <c:if test="${m_1[0][0] == 'NIL'}">
												<tr class='nrSubHeading'>
													<td colspan='10' style='text-align:center;'>Data Not Available...</td>
												    <% ntotln=0; %>
												</tr>
									       </c:if>
									       
									       <c:if test="${m_1[0][0] != 'NIL'}"> 
												 <c:forEach var="item" items="${m_1}" varStatus="num">
													  <tr style='font-size: 12px' id='NRRDO' name='NRRDO' >
														  <td width="10%" style="text-align: center;">${item[0]}</td>
														  <td width="20%" style="text-align: left;">${item[1]}</td>
														  <td width="10%" style="text-align: center;">${item[2]}</td>
														  <td width="20%" style="text-align: left;">${item[3]}</td>
														  <td width="10%" style="text-align: center;">${item[4]}</td>
														  <td width="10%" style="text-align: center;">${item[5]}</td>
														  <td width="10%" style="text-align: center;">${item[6]}</td>
														  <td width="10%" style="text-align: center;">${item[7]}</td>
														  <% ntotln++; %>
													  </tr>
												 </c:forEach>
	                                      </c:if>
    								</tbody>
								</table>
								</div>
					
		</div>
	</div> 	
	
	<div class="card-footer">
		     <input type="button" class="btn btn-success btn-sm" value="Export" id="btn_e" style="background-color: purple;" onclick="exportData('.nrTableDataDiv');">
		      <input type="button" class="btn btn-primary btn-sm" value="Print Page" id="btn_p" onclick="printDiv();">
    </div>
     </div>
      </div>
 </div>	
</form>

<c:url value="UnitMstatList" var="backUrl" />
<form:form action="${backUrl}" method="post" id="m7_unit1" name="m7_unit1" modelAttribute="m7_domid">
      <input type="hidden" name="m7_domid" id="m7_domid"/>
      <input type="hidden" name="m7_statid" id="m7_statid"/>
      <input type="hidden" name="m7_mthyr" id="m7_mthyr"/>
      <input type="hidden" name="unit_sus_no1" id="unit_sus_no1"/>
      <input type="hidden" name="unit_name1" id="unit_name1"/>
      <input type="hidden" name="cont_comd1" id="cont_comd1"/>
      <input type="hidden" name="cont_corps1" id="cont_corps1"/>
      <input type="hidden" name="cont_div1" id="cont_div1"/>
      <input type="hidden" name="cont_bde1" id="cont_bde1"/>
      
</form:form> 

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>

<script>
$(document).ready(function() {
	$("div#divwatermark").val('').addClass('watermarked');	
	watermarkreport();
	$().getMthYr(mnth_year);
	
	var y1 = '${m_1[0][0]}';

	if(y1 != "" || '${m_1[0]}'.length > 0){
		
		$("#re_tb").show();
		$("#domainid").val('${m_2}');
	    $("#statusid").val('${m_3}');
		$("#mnth_year").val('${m_4}');
		$("#unit_name").val('${m_5}');
		$("#unit_sus_no").val('${m_6}');
		
		
		nrSetWaterMark(<%=ntotln%>);
		$("#ntotln").text(<%=ntotln%>);	
	} 
	if('${cont_comd1}' != ""){
    	$("#cont_comd").val('${cont_comd1}');
    	getCONTCorps('${cont_comd1}');
	}
	if('${cont_corps1}' != ""){
		getCONTDiv('${cont_corps1}');
	}
    if('${cont_div1}' != ""){
    	getCONTBde('${cont_div1}');
    }
    if ('${roleAccess}' == 'Unit') {
		$("#cont_comd").attr("disabled", true);
		$("#cont_corps").attr("disabled", true);
		$("#cont_div").attr("disabled", true);
		$("#cont_bde").attr("disabled", true);
		$("#unit_sus_no").attr("disabled", true);
		$("#unit_name").attr("disabled", true);

		$("#unit_sus_no").val('${sus_no}');
		$("#unit_name").val('${unit_name}');

	
		if ('${cmd_sus}' != "") {
			
			$("#hd_cmd_sus").val('${cmd_sus}');
			getCONTCorps('${cmd_sus}');
			$("#report_all").show();
		}
		if ('${corp_sus}' != "") {
			$("#hd_corp_sus").val('${corp_sus}');
			getCONTDiv('${corp_sus}');
		}
		if ('${div_sus}' != "") {
			$("#hd_div_sus").val('${div_sus}');
			getCONTBde('${div_sus}');
		}
		if ('${bde_sus}' != "") {
			$("#hd_bde_sus").val('${bde_sus}');
		}
	}
    
	if ('${roleSubAccess}' == 'Command') {
		$("#cont_comd").attr("disabled", true);
		if ('${cmd_sus}' != "") {
			$("#report_all").show();
			$("#hd_cmd_sus").val('${cmd_sus}');
			getCONTCorps('${cmd_sus}');
		}
	}
	
    var select = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
   	$('select#cont_comd').change(function() {
	   	var fcode = this.value;
	   	if(fcode == "0"){
	   			$("select#cont_corps").html(select);
	   			$("select#cont_div").html(select);
	   			$("select#cont_bde").html(select);
   		}else{
   			$("select#cont_corps").html(select);
   			$("select#cont_div").html(select);
   			$("select#cont_bde").html(select);
   			
   			getCONTCorps(fcode);
    	
       		fcode += "00";	
   			getCONTDiv(fcode);
       	
       		fcode += "000";	
   			getCONTBde(fcode);
   		}
	});
	$('select#cont_corps').change(function() {
	   	   	var fcode = this.value;
   	   	if(fcode == "0"){
   	   		$("select#cont_div").html(select);
   	   		$("select#cont_bde").html(select);
	   	}else{
	   		$("select#cont_div").html(select);
   	   		$("select#cont_bde").html(select);
	   	   		getCONTDiv(fcode);
	       		fcode += "000";	
	   			getCONTBde(fcode);
	   	}
	});
	$('select#cont_div').change(function() {
	   		var fcode = this.value;    	   	
	   		if(fcode == "0"){
	 		$("select#cont_bde").html(select)
	   	}else{
	   		$("select#cont_bde").html(select)
		   		getCONTBde(fcode);
	   	}
	});
	$("#InputSearch").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#srctable tr").filter(function() {
		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});
	$("#unit_sus_no")
			.keyup(
					function() {
						var sus_no = this.value;
						var susNoAuto = $("#unit_sus_no");

						susNoAuto
								.autocomplete({
									source : function(
											request,
											response) {
										$
												.ajax({
													type : 'POST',
													url : "getTargetSUSNoList?"
															+ key
															+ "="
															+ value,
													data : {
														sus_no : sus_no
													},
													success : function(
															data) {
														var susval = [];
														var length = data.length - 1;
														var enc = data[length]
																.substring(
																		0,
																		16);
														for (var i = 0; i < data.length; i++) {
															susval
																	.push(dec(
																			enc,
																			data[i]));
														}
														var dataCountry1 = susval
																.join("|");
														var myResponse = [];
														var autoTextVal = susNoAuto
																.val();
														$
																.each(
																		dataCountry1
																				.toString()
																				.split(
																						"|"),
																		function(
																				i,
																				e) {
																			var newE = e
																					.substring(
																							0,
																							autoTextVal.length);
																			if (e
																					.toLowerCase()
																					.includes(
																							autoTextVal
																									.toLowerCase())) {
																				myResponse
																						.push(e);
																			}
																		});
														response(myResponse);
													}
												});
									},
									minLength : 1,
									autoFill : true,
									change : function(
											event, ui) {
										if (ui.item) {
											return true;
										} else {
											alert("Please Enter Approved Unit SUS No.");
											document
													.getElementById("unit_name").value = "";
											susNoAuto
													.val("");
											susNoAuto
													.focus();
											return false;
										}
									},	
									select : function(
											event, ui) {
										var sus_no = ui.item.value;
										$
												.post(
														"getTargetUnitNameList?"
																+ key
																+ "="
																+ value,
														{
															sus_no : sus_no
														},
														function(
																j) {

														})
												.done(
														function(
																j) {
															var length = j.length - 1;
															var enc = j[length]
																	.substring(
																			0,
																			16);
															$("#unit_name").val(dec(enc,j[0]));
															getCommand(sus_no);

														})
												.fail(
														function(
																xhr,
																textStatus,
																errorThrown) {
														});
									}
								});
					});

	// unit name
	$("input#unit_name")
			.keypress(
					function() {
						var unit_name = this.value;
						var susNoAuto = $("#unit_name");
						susNoAuto
								.autocomplete({
									source : function(
											request,
											response) {
										$
												.ajax({
													type : 'POST',
													url : "getTargetUnitsNameActiveList?"
															+ key
															+ "="
															+ value,
													data : {
														unit_name : unit_name
													},
													success : function(
															data) {
														var susval = [];
														var length = data.length - 1;
														var enc = data[length]
																.substring(
																		0,
																		16);
														for (var i = 0; i < data.length; i++) {
															susval
																	.push(dec(
																			enc,
																			data[i]));
														}

														response(susval);
													}
												});
									},
									minLength : 1,
									autoFill : true,
									change : function(
											event, ui) {
										if (ui.item) {
											return true;
										} else {
											alert("Please Enter Approved Unit Name.");
											document
													.getElementById("unit_name").value = "";
											susNoAuto
													.val("");
											susNoAuto
													.focus();
											return false;
										}
									},
									select : function(
											event, ui) {
										var unit_name = ui.item.value;

										$
												.post(
														"getTargetSUSFromUNITNAME?"
																+ key
																+ "="
																+ value,
														{
															target_unit_name : unit_name
														},
														function(
																data) {
														})
												.done(
														function(
																data) {
															var length = data.length - 1;
															var enc = data[length].substring(0,16);
															$("#unit_sus_no").val(dec(enc,data[0]));
															var a = dec(enc,data[0]);
														getCommand(a);		
														})
												.fail(
														function(
																xhr,
																textStatus,
																errorThrown) {
															});
									}
								});
				
		});
}); 
</script>

<script>

	


function btn_clc(){
	location.reload(true);
}


function printDiv() {
	  $("#SearchViewtr").hide();
	  $("#tdheaderView").show();
	  $("#headerView").hide();
	  $("#btn_e").hide();
	  $("#btn_p").hide();
	  $("#btn_modify").hide();
	  $('.rdView').css('display','none');
	//let popupWinindow
let innerContents = document.getElementById('printableArea').innerHTML;
	 
popupWindow = window.open('', '_blank', 'width=850,height=500,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
popupWindow.document.open();
//popupWinindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/miso/assets/scss/style.css"><link rel="stylesheet" href="js/cue/printWatermark.css"></head><body onload="window.print();window.close();">' + innerContents + '</html>');
popupWindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><style> table td{font-size:10px; font-weight:normal !important;} table th{font-size:12px !important;} tbody th{font-size:10px; font-weight:normal !important;}</style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' +innerContents+  '</html>');
popupWindow.document.close();
$("#SearchViewtr").show();
$("#tdheaderView").hide();
$("#headerView").show();
$("#btn_e").show();
$("#btn_p").show();
$("#btn_modify").show();
$('.rdView').css('display','block');

}
function exportData(b){
	$().tbtoExcel(b);
	b.preventDefault();
}

function setid(a,st){
	$("#nrSrcSel").val(a);
}

function mms_list_mcr_status(){
	if($("#mnth_year").val()==""){
		alert("Please Select the Period...");
		$("#mnth_year").focus();
	    return false;
	}
	
	var d = new Date();
	var c_d = d.getFullYear()+"-"+("0" + (d.getMonth()+1)).slice(-2);
	
	if($("#mnth_year").val() > c_d){
		$("#mnth_year").focus();
		alert("Can't select the Future Month & Year");
		return false;
	}
	
	$("#m7_domid").val($("#domainid").val());
	$("#m7_statid").val($("#statusid").val());
	$("#m7_mthyr").val($("#mnth_year").val());
	$("#unit_sus_no1").val($("#unit_sus_no").val());
	$("#unit_name1").val($("#unit_name").val());
	$("#cont_comd1").val($("#cont_comd").val());
	$("#cont_corps1").val($("#cont_corps").val()); 
	$("#cont_div1").val($("#cont_div").val());
	$("#cont_bde1").val($("#cont_bde").val());
	$("#nrWaitLoader").show();
	$("#m7_unit1").submit();
}
function getCONTCorps(fcode){
	
 	var fcode1 = fcode[0];
	$.post("getCorpsDetailsList?"+key+"="+value,{fcode:fcode1}, function(j) {
		if(j != ""){
			var length = j.length-1;
			var enc = j[length][0].substring(0,16);
			var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
			
			for ( var i = 0; i < length; i++) {
				if('${cont_corps1}' ==  dec(enc,j[i][0])){
					options += '<option value="' + dec(enc,j[i][0]) +'" name="'+dec(enc,j[i][1])+'" selected=selected >'+ dec(enc,j[i][1]) + '</option>';
				}else{
					options += '<option value="' + dec(enc,j[i][0]) +'" >'+ dec(enc,j[i][1]) + '</option>';
				}
			}	
			$("select#cont_corps").html(options);
		}
	});
 } 
 function getCONTDiv(fcode){
 	var fcode1 = fcode[0] + fcode[1] + fcode[2];
   	$.post("getDivDetailsList?"+key+"="+value,{fcode:fcode1}, function(j) {
   		if(j != ""){
		   	var length = j.length-1;
		var enc = j[length][0].substring(0,16);
		var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
		for ( var i = 0; i < length; i++) {
			if('${cont_div1}' ==  dec(enc,j[i][0])){
				options += '<option value="' + dec(enc,j[i][0]) +'" name="'+dec(enc,j[i][1])+'" selected=selected >'+ dec(enc,j[i][1]) + '</option>';
			}else{
				options += '<option value="' + dec(enc,j[i][0]) +'" >'+ dec(enc,j[i][1]) + '</option>';
			}
		}
	   		$("select#cont_div").html(options);
   		}
	});
 } 
function getCONTBde(fcode){
	var fcode1 = fcode[0] + fcode[1] + fcode[2] + fcode[3] + fcode[4] + fcode[5];
	$.post("getBdeDetailsList?"+key+"="+value,{fcode:fcode1}, function(j) {
		if(j != ""){
			var length = j.length-1;
		var enc = j[length][0].substring(0,16);
		var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
		jQuery("select#cont_bde").html(options);
		for ( var i = 0; i < length; i++) {
			if('${cont_bde1}' ==  dec(enc,j[i][0])){
				options += '<option value="' +  dec(enc,j[i][0])+ '" name="'+dec(enc,j[i][1])+'" selected=selected>'+  dec(enc,j[i][1]) + '</option>';
				$("#cont_bname").val(dec(enc,j[i][1]));
			}else{
				options += '<option value="' +  dec(enc,j[i][0]) +'" name="'+dec(enc,j[i][1])+'">'+ dec(enc,j[i][1]) + '</option>';
			}
		}	
		$("select#cont_bde").html(options);
		}
	});
}

function getCommand(y){
	//$("#cont_comd").attr('disabled',true);
	var FindWhat = "COMMAND";
	
	$.post("getpsg_comndSUS?"+key+"="+value,{FindWhat:FindWhat,a:y},function(j) {
		var a = [];
		var enc = j[j.length-1].substring(0,16);
		for(var i = 0; i < j.length; i++){
			a[i] = dec(enc,j[i]);
			
		}
		var data=a[0].split(",");
		var datap;
		
// 		for(var i = 0; i < data.length-1; i++) {
			datap=data[0].split(":");
			$("#cont_comd").val(datap[0]);  
			getCONTCorps(datap[0]);
			$("#cont_corps").val(datap[1]);  
			getCONTDiv(datap[1]);
			$("#cont_div").val(datap[2]);  
			getCONTBde(datap[2]);
			$("#cont_bde").val(datap[3]);  
			
 		
// 		}	
	}); 
}
</script> 