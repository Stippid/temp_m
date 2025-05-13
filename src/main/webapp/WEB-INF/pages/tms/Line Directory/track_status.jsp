<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<link href="js/cue/cueWatermark.css" rel="Stylesheet"></link>
<script src="js/Calender/datePicketValidation.js"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/assets/js/jquery-3.7.1.min.js"></script>
<script src="js/assets/js/jquery-ui.min.js"></script>

<link rel="stylesheet" href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>

<div class="animated fadeIn">
	<div class="">
		<div class="col-md-12" align="center">
			<div class="card">
				<div class="card-header">
					<h5>A:Track Status</h5>
				</div>



				<div class="card-body">
				<div align="center">
				
								<div class="col-md-12">
			
								<div class="row form-group">
									<div class="col col-md-6">
										<div class="form-check-inline form-check" style="display: inline-block; width: 700px;">
										<c:if test="${roleAccess != 'CIN' }">
											<label for="inline-radio1" class="form-check-label">
												<input type="radio" id="inline_radios1" name="inline-radios" value="1" class="form-control form-check-input" onclick="ClearField(1);">Industry Mode
											</label> &nbsp;&nbsp;&nbsp; 
											<label for="inline-radio1" class="form-check-label "> 
												<input type="radio" id="inline_radios2" name="inline-radios" value="2" class="form-control form-check-input" onclick="ClearField(2);">Service Mode
											</label>&nbsp;&nbsp;&nbsp; 	
											
											
																</c:if>			
																
																	<c:if test="${roleAccess == 'CIN' }">
															
																		<label for="inline-radio1" class="form-check-label "> 
												<input type="radio" id="inline_radios2" name="inline-radios" value="2" class="form-control form-check-input" onclick="ClearField(2);">Service Mode
											</label>&nbsp;&nbsp;&nbsp; 	
											
																</c:if>	
										</div>
									</div>
								
								<div class="col-md-6">							
								<div class=" col-md-4">
									<label class=" form-control-label">PY</label>
								</div>
								<div class="col-md-8">
									 <select id="ddlYears" name="ddlYears" class="form-control-sm form-control" >
								    <option value='0'>--Select Year--</option>
								 </select>
								</div>
							
						</div>
							</div>
						</div>
								
								
							</div>
					<div class="" id="reportDiv">
						<div class="col-md-12">
							<div align="right">
								<h6>Total Count : ${list.size()}</h6>
							</div>
							<div class="watermarked" data-watermark="" id="divwatermark">
								<span id="ip"></span>
								<table id="getVehicleStatusTbl"
									class="table no-margin table-striped  table-hover  table-bordered report_print_scroll resizeble-table">
									<thead style="font-size: 15px; text-align: center;">
										<tr>
											<th style="width: 5%;">Sr No</th>
										
											<th>Comd</th>
											<th>Corps</th>
											<th>Div</th>
											<th>Bde</th>
											<!-- <th>SUS No</th> -->
											<th>Unit </th>
											<th>BA No</th>
											<th>Type Of Intervention</th>
											<th>Type Of Veh</th>
											<th>KMs</th>
											<th>Vintage</th>
											<th>Feeding Date</th>
									  <th>
										<c:if test="${mode1 == 2 or mode1 == 0 or mode1 == null}">
								 Designated Base Wksp
                                          </c:if>
                                          <c:if test="${mode1 == 1}">
                                     Designated Repair Agency
                                             </c:if>
                                             </th>
                                          	<th>CIN</th>
												<th>Date Of Arrival</th>
												<th>OH Status</th>
												<th>OH Date</th>
												<th>Remark</th>
												<th id="action">Action</th>

										</tr>
									</thead>
									<tbody>
										<c:if test="${list.size() == 0}">
											<tr>
												<td colspan="11" align="center" style="color: red;">Data
													Not Available</td>
											</tr>
										</c:if>
										<c:forEach var="item" items="${list}" varStatus="num">
											<tr>
												<td style="width: 5%;">${num.index+1}</td>
											
												<td>${item[8]}</td>
												<td>${item[9]}</td>
												<td>${item[10]}</td>
												<td>${item[11]}</td>
												<%-- <td>${item[17]}</td> --%>
												<td>${item[13]}</td>
												<td>${item[1]}<input type="hidden"
													id="ba_no${num.index+1}" name="ba_no${num.index+1}"
													value="${item[1]}"></td>
													<td>${item[16]}</td>
												<td>${item[2]}<input type="hidden"
													id="veh_type${num.index+1}" name="veh_type${num.index+1}"
													value="${item[2]}"></td>
													
													
												<td>${item[3]}</td>
												<td>${item[4]}</td>
												
												<td>${item[5]}</td>
												<td>  
										
											<c:if  test="${mode1 == 2 }">
											
												<c:if test="${roleAccess == 'MISO' or roleAccess == 'HQ_BWG' }">
												<c:if test="${item[14] == null}">
														<select name="base_wp${num.index+1}" id="base_wp${num.index+1}" class="form-control">
												       <option value="0">--Select--</option>
														<c:forEach var="item1" items="${base_work_shopList}">
															<option value="${item1[1]}" name="${item1[1]}">${item1[1]} ${item1[2]}</option>
														</c:forEach>
														</select>
														
														<input type="button" class="btn btn-primary btn-sm"
																id="btn_s${num.index+1}" value="Submit"
																onclick="return update_base_wshop(${num.index+1},${item[0]});">
															<label class=" form-control-label"
																id="lab_l${num.index+1}" style="display: none"><strong
																style="color: Green;"><u>Submitted Successfully</u></strong></label>
												
														</c:if>
														</c:if>
																<c:if test="${item[14] != null}">
															${item[14]}
														
														</c:if>
															</c:if>
														
															<c:if  test="${mode1 == 1 }">
													
															<c:if test="${ roleAccess == 'Line_dte' }">
															<c:if test="${item[14] == null}">
														<select name="base_wp${num.index+1}" id="base_wp${num.index+1}" class="form-control">
												       <option value="0">--Select--</option>
														<c:forEach var="item1" items="${repair_shopList}">
															<option value="${item1[1]}" name="${item1[1]}">${item1[1]} ${item1[2]}</option>
														</c:forEach>
														</select>
														
														<input type="button" class="btn btn-primary btn-sm"
																id="btn_s${num.index+1}" value="Submit"
																onclick="return update_base_wshop(${num.index+1},${item[0]});">
															<label class=" form-control-label"
																id="lab_l${num.index+1}" style="display: none"><strong
																style="color: Green;"><u>Submitted Successfully</u></strong></label>
												
														</c:if>
													
																<c:if test="${item[14] != null}">
															${item[14]}
														
														</c:if>			
																</c:if>
																
																
																	<c:if test="${ roleAccess != 'Line_dte' }">
																<c:if test="${item[14] != null}">
															${item[14]}
														
														</c:if>			
																</c:if>
																
																
																
																</c:if>
												</td>
												
													<td>	
												 
													<input type="hidden" id="cin_+${num.index+1}" value="${item[0]}" >
														<c:if  test="${mode1 == 2 }">
														
													<c:if test="${roleAccess == 'CIN'}">
													<c:if test="${item[15] == 0 or item[15]==null }">
													<input type="button" class="btn btn-primary btn-sm"
																id="btn_s${num.index+1}" value="Generate"
																onclick="return generate_cin(${num.index+1},${item[0]});">
													
													</c:if>
												
													</c:if>
													
														<c:if test="${item[15] != 0 and item[15]!=null}">
															${item[0]}															
														</c:if>	
															</c:if>
													
															<c:if  test="${mode1 == 1 }">
														<c:if test="${roleAccess == 'Line_dte'}">
													<c:if test="${item[15] == 0 or item[15]==null }">
													<input type="button" class="btn btn-primary btn-sm"
																id="btn_s${num.index+1}" value="Generate"
																onclick="return generate_cin_industry(${num.index+1},${item[0]});">
													
													</c:if>
												
													</c:if>
														<c:if test="${item[15] != 0 and item[15] != null }">
															${item[0]}															
														</c:if>	
															
															</c:if>
													 </td>
	
													<td>${item[17]}</td>
													<td>${item[18]}</td>
													<td>${item[19]}</td>
													<td>${item[20]}</td>
													<c:if test="${roleAccess != 'MISO'}">
													<td id="action${num.index+1}">${item[21]}</td>		
													</c:if>										
											</tr>
										</c:forEach>
									</tbody>
								</table>

							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
</div>
<%-- <c:url value="gettrackstatus_report" var="searchUrl" /> --%>
<%-- <form:form action="${searchUrl}" method="post" id="searchForm" --%>
<%-- 	name="searchForm"> --%>
<%-- </form:form> --%>
<c:url value="gettrackstatus_report" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm">
	<input type="hidden" name="mode1" id="mode1" />
	<input type="hidden" name="py1" id="py1" />
</form:form>

<script>
$(document).ready(function() {
    $("#getVehicleStatusTbl th").resizable({
        handles: "e"
    });
});
</script>
<script>
$(document).ready(function() {
	getyear() ;


	
	var mode1='${mode1}';
	var py1='${py1}';

	// $("#inline_radios").val(mode1);
	if(mode1 === "1") {
	    $("#inline_radios1").prop("checked", true);
	}
	if(mode1 === "2") {
	    $("#inline_radios2").prop("checked", true);
	}

	if("${roleAccess}" === "MISO" ){	
		
		document.getElementById('action').style.display = "none";
	 }

$("#ddlYears").val(py1);
});







function generate_cin_industry(sr,cin_id)

{
	var base_wp=$("#base_wp"+sr).val();
	if(base_wp==""|| base_wp=="0")
		{
		alert("Please Select Designated Repair Agency");
		$('#base_wp'+sr).focus();
		return false;
		}
	$.post('update_cin_qr_action?' + key + "=" + value, {
		cin_id: cin_id,
	}, function(data) {
alert(data);
location.reload();
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
	});
	
	
	}
	
function generate_cin(sr,cin_id)

{
	var base_wp=$("#base_wp"+sr).val();
	if(base_wp==""|| base_wp=="0")
		{
		alert("Please Select Designated Repair Agency");
		$('#base_wp'+sr).focus();
		return false;
		}
	
	$.post('update_cin_qr_action?' + key + "=" + value, {
		cin_id: cin_id,
	}, function(data) {
alert(data);
location.reload();
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
	});
	
	
	}
	
	
	
	
	function dt_arival_change(id)
	{

// 		var oh=$("#oh_status"+id).val();
// 		var min=$("#dt_arrival"+id).val();
// 			var minDateValue = $("#dt_arrival"+id).val();
// 			var dateParts = minDateValue.split("-");
// 			var minDate = new Date(dateParts[2], dateParts[1] - 1, dateParts[0]);
		
// 			$('#oh_date'+id).attr('min', minDate);		
			
// 			  $('#oh_date').datepicker({
// 			        dateFormat: 'yy-mm-dd',
// 			        minDate: 0, 
// 			        onSelect: function(dateText) {			          
// 			            $('#dt_arrival').attr('min', dateText);
// 			        }
// 			    });

// 			    // Set the minimum date based on another date input field
// 			    $('#dt_arrival').on('change', function() {
// 			        var minDateValue = $(this).val();
// 			        $('#oh_date').datepicker('option', 'minDate', minDateValue);
// 			    });
			
			
			
			
	}
	
	
	function isNumber0_9Only(evt) {
		var charCode = (evt.which) ? evt.which : evt.keyCode;
		if (!(charCode >= 48 && charCode <= 57) && charCode != 8) {
			return false;
		}
		return true;
	}

	function Search() {
		

			document.getElementById('searchForm').submit();
		}
	
	
	function update_base_wshop(ps,cin_no){
		$.ajaxSetup({
	        async : false
		});
		if ($('#base_wp'+ps).val() == "0") {
			alert("Please Select Base workshop");
			$('#base_wp'+ps).focus();
			return false;
		} 
		var base_wp = $('#base_wp'+ps).val();
		
		$.post('update_base_action?' + key + "=" + value, {
			base_wp: base_wp,
			cin_no:cin_no
		}, function(data) {
			if(data.error == null) {
					alert("Data Saved Successfully");
					$('#btn_s'+ps).hide();
					$('#base_wp'+ps).hide();
					$('#lab_l'+ps).show();
					location.reload();
			} else alert("Data Not Saved");
		}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});
	}
	
	
	
	
	function CancelData(cin_no,ps){
	$.ajaxSetup({
        async : false
	});
	var remarks = $('#remarks'+ps).val();
	
	
	$.post('cancle_cin_action?' + key + "=" + value, {cin_no:cin_no,remarks:remarks
	}, function(data) {
		if(data.error == null) {
				alert("Data Cancel Successfully");
				
				
		} else alert("Data Not Saved");
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
	});
	}
	function oh_dt_st(ps){
		
		if($('#oh_status'+ps).val() == "Yes")	{
		document.getElementById('oh_date'+ps).readOnly = false;
		}else
			{ 
			 document.getElementById('oh_date'+ps).readOnly = true;	
			 document.getElementById('oh_date'+ps).value = "";	
			}
		 
	      }
	
	function oh_dt_st_basesubmit(ps,cin_no,qr_status){
	
	var dt_arrival = $('#dt_arrival'+ps).val();
	var oh_date = $('#oh_date'+ps).val();
	var oh_status = $('#oh_status'+ps).val();
	var ba_no = $('#ba_no'+ps).val();
	var veh_type = $('#veh_type'+ps).val();
	
	if (qr_status == ""||qr_status == "0") {
		alert("Please Generate  CIN");
		$('#btn_s' + ps).focus();
		return false;
	} 
	
	if (dt_arrival == "") {
		alert("Please Select Date Of Arrival");
		$('#dt_arrival'+ps).focus();
		return false;
	} 
	if(oh_status == "Yes" &&  oh_date == "" )	{
		alert("Please Select OH Date");
		$('#oh_date'+ps).focus();
		return false;
	}
	if(oh_status == "Yes" &&  oh_date < dt_arrival )	{
		 alert(" OH Date Should be Greater then  Date Of Arrival");
	        $('#oh_date' + ps).focus();
	        return false;
	}
	
	
	
	$.post('update_track_action?' + key + "=" + value, {
		dt_arrival: dt_arrival,
		oh_date:oh_date,
		oh_status:oh_status,
		cin_no:cin_no,
		veh_type:veh_type,
		ba_no:ba_no
	}, function(data) {
		if(data == "OH Completed") {
			
			$("#action"+ps).text(data);
			alert(data);
		}else{
			alert(data);
		}
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
	});
	
	}
	function ClearField(a)
	{
	var b=$("#ddlYears").val()
		$("#mode1").val(a);
		$("#py1").val(b);
		document.getElementById('searchForm').submit();
	}
	function ClearField2(b)
	{
		var radios = document.getElementsByName('inline-radios');
        var a = null;
        
        for (var i = 0; i < radios.length; i++) {
        	debugger;
            if (radios[i].checked) {
                a = radios[i].value;
                break;
            }
        }
if(a==null)
	{
	alert("Please select Mode");
	return false;
	}
		$("#mode1").val(a);
		$("#py1").val(b);
		document.getElementById('searchForm').submit();
	}

	function getyear() 
	{
	        var ddlYears = document.getElementById("ddlYears");
	        var currentYear = (new Date()).getFullYear();
	        for (var i = 0; i <= 20; i++) {
	            var option = document.createElement("OPTION");
	            option.innerHTML = i+2024;
	            option.value = i+2024;            
	            ddlYears.appendChild(option);
	        }
	        ddlYears.addEventListener('change', function() {
	            ClearField2(this.value);
	        });
	}
	
	
	
</script>