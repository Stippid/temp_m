<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/Calender/jquery-2.2.3.min.js"></script>
<style>
.overSelect {
	position: absolute;
	left: 0;
	right: 0;
	top: 0;
	bottom: 0;
}

.selectBox {
	position: relative;
}

.selectBox select {
	width: 100%;
	font-weight: bold;
}

.checkboxes {
	display: none;
	border: 1px #dadada solid;
	width: 250px;
	display: none;
	border: 0.5px #000000 solid;
	position: absolute;
	background-color: #FFFFFF;
	width: 100%;
	z-index: 1;
}

.checkboxes label {
	margin-left: 10px;
	text-align: left;
	display: block;
}

.checkboxes label:hover {
	background-color: #1e90ff;
}


</style>



<div class="animated fadeIn">
	<div class="">
		<div class="" align="center">
			<div class="card">
				<div class="card-header">
					<h5>
					Intervention Identification (OH / MR)
					</h5>
				</div>
				<div class="card-body card-block">
					<div class="col-md-12" id="tpt">
						<div class="col-md-6">
							<div class="row form-group">
								<div class=" col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">*</strong> VEH CAT</label>
								</div>
								<div class="col-md-8">
									<select id="type_veh" class="form-control-sm form-control" style="width: 100%" onchange="changehrs();getMCTMainList();"><!-- onchange="getPRFList(this.value)" -->
										<option value="">--Select--</option>
										<option value="0">A Vehicles</option>
										<option value="1">B Vehicles</option>
										<option value="2">C Vehicles</option>
									</select>
								</div>
							</div>
						</div>
						<!-- <div class="col-md-6">
							<div class="row form-group">
								<div class=" col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">*</strong> VEH TYPE</label>
								</div>
								<div class="col-md-8">
									<select id="prf_code" class="form-control-sm form-control" style="width: 100%"></select>
								</div>
							</div>
						</div> -->
						
						
						<div class="col-md-6">
							<div class="row form-group">
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
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class=" col-md-4">
									<label class=" form-control-label"><strong style="color: red;">*</strong>Nodal Dte</label>
								</div>
								<div class="col-md-8">
									<select id="line_dte_sus" name="line_dte_sus" class="form-control-sm form-control" onchange="getMCTMainList();">
										${selectLine_dte}
										<c:forEach var="item" items="${getLine_DteList}"
											varStatus="num">
											<option value="${item.line_dte_sus}"
												name="${item.line_dte_name}">${item.line_dte_name}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div> 
						<div class="col-md-6">
							<div class="row form-group">
								<div class=" col-md-4">
									<label class=" form-control-label">SUB CAT (MCT 4)</label>
									<input
										type="hidden" id="CheckVal" name="CheckVal">
								</div>
								<div class="col-md-8">
							
										<div class="selectBox" onclick="showCheckboxes1()">
										<select id="mct_main" name="mct_main"
											class="form-control-sm form-control">
											<option value="" hidden>--Select--</option>


										</select>
										<div class="overSelect"></div>
									</div>
									<div id="checkboxes1" class="checkboxes"
										style="max-height: 200px; overflow: auto;">

										<div id="chk" class="chk"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-3">
							<div class="row form-group">
								<div class="col-md-4" style="display:none" id="kms_hide" >
									<label class=" form-control-label"> Kms
									</label>
								</div>
								<div class="col-md-4" style="display:none" id="hrs" >
									<label class=" form-control-label"> HRs
									</label>
								</div>
								
								<div class="col-md-8">
									<input type="text" id="kms" name="kms" onkeypress="return isNumber0_9Only(event);" 
									class="form-control autocomplete" autocomplete="off" maxlength="6" value="0">
								</div>
							</div>
						</div>
						<div class="col-md-3">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">Vintage</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="vintag" name="vintag" onkeypress="return isNumber0_9Only(event);"
										class="form-control autocomplete" autocomplete="off" maxlength="2" value="0">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class=" col-md-4">
									<label class=" form-control-label">Forecast</label>
								</div>
								<div class="col-md-8">
									<select id="py" class="form-control-sm form-control" style="width: 100%">
										<option value="0">--Current Yr--</option>
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
									</select>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Type Of Unit</label>
								</div>
								<div class="col-md-8">
									<select name="type_force" id="type_force"  class="form-control-sm form-control">
				                    	<option value="">--Select--</option>
	                               		<c:forEach var="item" items="${getTypeOfUnitList}">
                 							<option value="${item[0]}" >${item[0]} - ${item[1]}</option>
                 						</c:forEach>
				                  	</select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
						
						<div class="row form-group">
								<div class=" col-md-4">
									<label class=" form-control-label">Type Of Intervention</label>
								</div>
								<div class="col-md-8">
									<select id="type_of_intervention" class="form-control-sm form-control" style="width: 100%" ><!-- onchange="getPRFList(this.value)" -->
										<option value="">--Select--</option>
										<option value="0">OH 1</option>
										<option value="1">OH 2 </option>
										<option value="2">OH 3</option>
										<option value="3">MR 1</option>
										<option value="4">MR 2</option>
										<option value="5">MR 3</option>
									</select>
								</div>
							</div>
						</div>
						
						
					</div>
					<div class="col-md-12">
									<div class="col-md-6">
											<div class="row form-group">
													<div class=" col-md-4">
					  		<label class=" form-control-label">Command:</label>
						<input type="hidden" id="CheckVal2" name="CheckVal2">
					  		  	</div>
					  		  		
					  		  			<div class="col-md-8">
					  		  		<div class="selectBox" onclick="showCheckboxes5()">
				
						<select name="cont_comd" id="cont_comd"
							class="form-control ${not empty selectedGetCommandList ? 'green-style' : ''}">
								
							<c:if test="${empty selectedGetCommandList}">
							
								<option>--Select--</option>
							</c:if>

							<c:if test="${not empty selectedGetCommandList}">
								<option>--Selected--</option>
							</c:if>
						</select>

						<div class="overSelect" ></div>
					</div>

					<div id="checkboxes5"
						class="checkboxes ${not empty selectedGetCommandList}"
						style="max-height: 200px; overflow: auto;">
						<div id="chk_box">

						 <input type="text" id="searchBox" name="searchBox"
						class="form-control autocomplete custom_search" autocomplete="off" placeholder="Search Comd"  oninput="filterOptions('searchBox','.quali_subjectlist_cmd')"
						maxlength="40">
							<c:forEach var="item" items="${getCommandList}" varStatus="num">
								<label for="one" class="quali_subjectlist_cmd"> <input
									type="checkbox" name="multisub_cmd"  onclick=""  class="cmdCheckBox" value="${item[0]}"
									<c:forEach var="selectedGetCommandList" items="${selectedGetCommandList}">
   											<c:if test="${item[0] eq selectedGetCommandList}">
        										checked
    										</c:if>
											</c:forEach> />
									${item[1]}
								</label>
							</c:forEach>
						</div>
					</div>
					  		  	
					  		  	</div>
					  		  	</div>
					  	</div>
					  	<div class="col-md-6">
							
								<div class="row form-group">
								<div class=" col-md-4">
									<label class=" form-control-label">Line Dte</label>
								</div>
								<div class="col-md-8">
									<select id="line_dte_sus_main" name="line_dte_sus_main" class="form-control-sm form-control" onchange="">
										${selectLine_dte}
										<c:forEach var="item" items="${getLine_DteList2}"
											varStatus="num">
											<option value="${item.line_dte_sus}"
												name="${item.line_dte_name}">${item.line_dte_name}</option>
										</c:forEach>
									</select>
								</div>
								
								
								
							</div>
							
							</div>
				</div>
				</div>
				 <input type="hidden" id="count" name="count">
                <div class="form-control card-footer" align="center">
                    <a href="Qfd_url" type="reset" class="btn btn-success btn-sm">Clear </a>
                    <i class="action_icons searchButton"></i><input type="button"
                        class="btn btn-info btn-sm" onclick="return Search();" value="Search">
                        <i class="fa fa-file-excel-o" id="btnExport" style="font-size: x-large; color: green; text-align: right;" aria-hidden="true" title="EXPORT TO EXCEL" onclick="getExcel();"></i>
                </div>
                </div>
    </div>
</div>
            <!--     <div class="card-body"> -->
                    <div class="" id="reportDiv">
                        <div class="col-md-12">
                     <div align="right"><h6>Total Count : ${list.size()}</h6></div> 
                            <div class="watermarked" data-watermark="" id="divwatermark">
                                <span id="ip"></span>
                                <table id="getVehicleStatusTbl"
                                    class="table no-margin table-striped   table-hover   table-bordered report_print">
                                    <thead   style="font-size: 15px;text-align: center ;">
                                        <tr>
                                            <th rowspan="2" style="width:5%;">S No</th>
                                            <th rowspan="2"   style="width:10%;">Comd</th>
                                            <th rowspan="2"   style="width:10%;">Corps</th>
                                            <th rowspan="2"   style="width:10%;">Div</th>
                                            <th rowspan="2"   style="width:10%;">Bde</th>
                                            <th rowspan="2"   style="width:10%;">Unit</th>
                                            <th rowspan="2"   style="width:10%;">SUS No</th>
                                             <th rowspan="2"   style="width:10%;">Line Dte </th>
                                     <!--        <th rowspan="2"   style="width:10%;">Nomen</th> -->
                                             <th rowspan="2"   style="width:10%;">Nomen</th>
                                            <th rowspan="2"   style="width:10%;">BA No</th>
                                            <c:if test="${type_veh1 == 0 || type_veh1 == 1 }">
                                            <th rowspan="2"   style="width:10%;" >Kms Run</th>
                                        </c:if>
                                        <c:if test="${type_veh1 == 2 }">
                                            <th rowspan="2" style="width:5%;">HRs</th>
                                        </c:if>
                                            
                                            <th rowspan="2"   style="width:5%;">Vintage</th>
                                            <th rowspan="2"   style="width:5%;">Cl</th>
                                        <th colspan="2" style="width:10%;">OH1</th>
                                        <th colspan="2" style="width:10%;">MR1</th>
                                        <th colspan="2" style="width:10%;">OH2</th>
                                        <th colspan="2" style="width:10%;">MR2</th>
                                        <th colspan="2" style="width:10%;">OH3</th>
                                        <th colspan="2" style="width:10%;">MR3</th>
                                        </tr>
                                        
                                           <tr>                                                       
                                                         <td style="width:5%;">Due</td>
                                                         <td style="width:5%;">Done</td>
                                                         <td style="width:5%;">Due</td>
                                                         <td style="width:5%;">Done</td>
                                                         <td style="width:5%;">Due</td>
                                                         <td style="width:5%;">Done</td>
                                                         <td style="width:5%;">Due</td>
                                                         <td style="width:5%;">Done</td>
                                                         <td style="width:5%;">Due</td>
                                                         <td style="width:5%;">Done</td>
                                                         <td style="width:5%;">Due</td>
                                                         <td style="width:5%;">Done</td>
                                                           
                                                                 </tr>
                                    </thead>
                                    <tbody>
                                        <c:if test="${list.size() == 0}">
                                            <tr>
                                                <td colspan="24" align="center" style="color: red;">Data Not Available</td>
                                            </tr>
                                        </c:if>
                                        <c:forEach var="item" items="${list}" varStatus="num">
                                            <tr>
                                                <td style="width:5%;">${num.index+1}</td>
                                                <td>${item[0]}</td>
                                                <td >${item[1]}</td>
                                                <td >${item[2]}</td>
                                                <td >${item[3]}</td>
                                                <td >${item[4]}</td>
                                                <td>${item[5]}</td>
                                                 <td >${item[10]}</td>
                                           <%--      <td >${item[6]}</td> --%>
                                                 <td >${item[23]}</td>
                                                 <td>${item[6]}</td> 
                                                <td >${item[7]}</td>
                                                <td >${item[8]}</td>    
                                                <td >${item[9]}</td>
                                                <td >${item[11]}</td> <!-- oh1_due_date -->
                                                <td >${item[14]}</td><!-- oh1_done_date -->
                                                <td >${item[17]}</td><!-- mr1_due_date -->
                                                <td >${item[20]}</td><!-- mr1_done_date -->
                                                <td >${item[12]}</td><!-- oh2_due_date -->
                                                  <td >${item[15]}</td><!-- oh2_done_date -->
                                                <td >${item[18]}</td><!-- mr2_due_date -->
                                                <td >${item[21]}</td><!-- mr2_done_date -->
                                                <td >${item[13]}</td>
                                                    <td >${item[16]}</td>
                                                <td >${item[19]}</td><!-- mr3_due_date -->
                                                <td >${item[22]}</td><!-- mr3_done_date -->
                                                
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>

                            </div>
                        </div>
                    </div>
                <!-- </div> -->
            </div>

<c:url value="getSearch_qfd_report" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="type_veh1">
	<input type="hidden" name="type_veh1" id="type_veh1" />
	<input type="hidden" name="mct_main1" id="mct_main1" />
	<input type="hidden" name="line_dte_sus1" id="line_dte_sus1" />
	<input type="hidden" name="kms1" id="kms1" />
	<input type="hidden" name="vintag1" id="vintag1" />
	<input type="hidden" name="py1" id="py1" />
	
		<input type="hidden" id="CheckVal1" name="CheckVal1">
		<input type="hidden" id="CheckVal3" name="CheckVal3">
	<input type="hidden" name="type_force1" id="type_force1" />
	<input type="hidden" id="type_of_intervention1" name="type_of_intervention1"> 
	<input type="hidden" id="line_dte_sus_main1" name="line_dte_sus_main1"> 
	<input type="hidden" id="ddlYears1" name="ddlYears1"> 
	
</form:form>

<c:url value="Excel_qfd_report" var="excelUrl" />
<form:form action="${excelUrl}" method="post" id="ExcelForm" name="ExcelForm" modelAttribute="typeReport1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
	<input type="hidden" name="type_vehex" id="type_vehex" />
	<input type="hidden" name="mct_mainex" id="mct_mainex" />
	<input type="hidden" name="line_dte_susex" id="line_dte_susex" />
	<input type="hidden" name="kmsex" id="kmsex" />
	<input type="hidden" name="vintagex" id="vintagex" />
	<input type="hidden" name="pyex" id="pyex" />
	<input type="hidden" name="type_forceex" id="type_forceex" />
</form:form> 
<script>
	function isNumber0_9Only(evt) {
		var charCode = (evt.which) ? evt.which : evt.keyCode;
		if (!(charCode >= 48 && charCode <= 57) && charCode != 8) {
			return false;
		}
		return true;
	}
/* 	function getPRFList(val){
		var options = '<option value="0">' + "--Select--" + '</option>';
		if (val != "") {
			$.post("getTptSummaryInPRFList?" + key + "=" + value, {type : val}).done(function(j) {
				for (var i = 0; i < j.length; i++) {
					if (j[i].prf_code == '${prf_code1}') {
						options += '<option value="'+j[i].prf_code+'" name="' + j[i].group_name+ '" selected=selected>'+ j[i].group_name + '</option>';
					} else {
						options += '<option value="'+j[i].prf_code+'" name="' + j[i].group_name+ '" >'+ j[i].group_name + '</option>';
					}
				}
				$("select#prf_code").html(options);
			}).fail(function(xhr, textStatus, errorThrown) {});
		} else {
			$("select#prf_code").html(options);
		}
		
	} */
function getMCTMainList(){
		
 		var w=$("#CheckVal").val();

		var type_veh = $('#type_veh').val();
		var line_dte_sus = $('#line_dte_sus').val();

		
		var option='';
		if (line_dte_sus != "" &&type_veh!="") {
			$.post("getMctNoDigitDetailListforqfd4?" + key + "=" + value, {line_dte_sus : line_dte_sus,type_veh:type_veh}).done(function(j) {
		
				for (var i = 0; i < j.length; i++) {
			option +='<label for="one" class="quali_subjectlist" ><input  class="nrCheckBox" type="checkbox" onchange="findselected()"  name="multisub_cmd" id=" '+j[i].mct_main_id+ ' "value="'+j[i].mct_main_id+'"/>&nbsp&nbsp&nbsp'+j[i].mct_main_id+'-'+j[i].mct_nomen+'</label>';
	
				}
				$("#chk").html(option);
				setchecked(w);
			}).fail(function(xhr, textStatus, errorThrown) {});
	} 
		
	}
	function Search() {
		
	if ($('#type_veh').val() == "") {
			alert("Please Select Veh Cat");
			$('#type_veh').focus();
			return false;
		/* } else if ($('#prf_code').val() == "0") {
			alert("please select Veh Type");
			$('#prf_code').focus();
			return false; */
		} else if ($('#line_dte_sus').val() == "0") {
			alert("Please Select Nodal Dte");
			$('#line_dte_sus').focus();
			return false;
		} else {
			findselected();
			findselected2();
			$("#type_veh1").val($('#type_veh').val());
			$("#ddlYears1").val($('#ddlYears').val());
			//$("#prf_code1").val($('#prf_code').val());
			$("#mct_main1").val($('#mct_main').val());
			$("#line_dte_sus1").val($('#line_dte_sus').val());
			$("#kms1").val($('#kms').val());
			$("#vintag1").val($('#vintag').val());
			$("#py1").val($('#py').val());
			$("#type_force1").val($('#type_force').val());
			$("#CheckVal1").val($("#CheckVal").val());
			$("#CheckVal3").val($("#CheckVal2").val());
			$("#line_dte_sus_main1").val($('#line_dte_sus_main').val());
			document.getElementById('searchForm').submit();
		}
	}
	
	function findselected2() {
	 	
		var nrSel = $('#chk_box input[type=checkbox]:checked' ).map(function() {
				return $(this).attr('value');
			}).get();
			var b=nrSel.join(',');
			$("#CheckVal2").val(b);

		}
	
	
	function findselected() {
	 	
		var nrSel = $('.chk input[type=checkbox]:checked' ).map(function() {
				return $(this).attr('value');
			}).get();
			var b=nrSel.join(',');
			$("#CheckVal").val(b);
		
		}
	
	function getExcel() {	
		
	 	
		var type_veh=$("#type_veh").val();
	 	var mct_main=$("#mct_main").val();
	 	var line_dte_sus=$("#line_dte_sus").val();
	 	var kms=$("#kms").val();
	 	var vintag=$("#vintag").val();
	 	var py=$("#py").val();
	 	var type_force=$("#type_force").val();
	 	
		
	 
		
	 	
		
		
		$("#type_vehex").val(type_veh);
		$("#mct_mainex").val(mct_main);
		$("#line_dte_susex").val(line_dte_sus);
		$("#kmsex").val(kms);
		$("#vintagex").val(vintag);
		$("#pyex").val(py);
		$("#type_forceex").val(type_force);
		
		document.getElementById('typeReport1').value = 'excelL';
		document.getElementById('ExcelForm').submit();
	}
	$(document).ready(function() {
		getyear() 
		var valu='${CheckVal3}'
		setchecked(valu)
		var q5 = '${type_veh1}';
		if (q5 != "") {
			$("#type_veh").val(q5);
		}
		//getPRFList($('#type_veh').val());
		var q2 = '${line_dte_sus1}';
		if (q2 != "") {
			$("#line_dte_sus").val(q2);
			getMCTMainList(q2)
		}
		
		var q9 = '${line_dte_sus_main1}';
		if (q9 != "") {
			$("#line_dte_sus_main").val(q9);
			
		}
		
		var q3 = '${CheckVal1}';
		if (q3 != "") {
			$("#CheckVal").val(q3);
		}
		
		
		
		if ('${kms1}' == '') {
			$("#kms").val('0');
		} else {
			$("#kms").val('${kms1}');
		}
		if ('${vintag1}' == '') {
			$("#vintag").val('0');
		} else {
			$("#vintag").val('${vintag1}');
		}
		if ('${py1}' == '') {
			$("#py").val('0');
		} else {
			$("#py").val('${py1}');
		}
		if ('${type_force1}' == '') {
			$("#type_force").val('');
		} else {
			$("#type_force").val('${type_force1}');
		}
		if (q5 == "2") {
			$("#hrs").show();
			$("#kms_hide").hide();
		}
		else  {
			$("#kms_hide").show();
			$("#hrs").hide();
		}
		
	});

	/*function MCTAutocomplete() {
		
		var line_dte_sus = $("select#line_dte_sus").val();
		var mct_numberAuto=$("input#mct_main");

		if (line_dte_sus == "0") {
			alert("Please Select Line Dte.");
			$("select#line_dte_sus").focus();
			
			return false;
		}
		mct_numberAuto.autocomplete({
			source : function(request, response) {
				
				$.ajax({
					type : 'POST',
					url : "getMctNo4DigitDetailListforqfd?" + key + "=" + value,
					data : {line_dte_sus : line_dte_sus},
					success : function(data) {
						// 
						//	 					alert(data);
						var susval = [];
						var length = data.length - 1;
						if (data.length != 0) {
							var enc = data[length].substring(0, 16);
						}
						for (var i = 0; i < data.length; i++) {
							susval.push(dec(enc, data[i]));
						}
						response(susval);
					}
				});
			},
			minLength : 1,
			autoFill : true,
			change : function(event, ui) {
				if (ui.item) {
					return true;
				} else {
					alert("Please Enter MCT No.");
					$("#mct_main").val("");
					mct_numberAuto.val("");
					mct_numberAuto.focus();
					return false;
				}
			},
			
		});
		//	 	});
	}*/
	function changehrs(){
		if ($("#type_veh").val() == "2") {
			$("#hrs").show();
			$("#kms_hide").hide();
		}
		else  {
			$("#kms_hide").show();
			$("#hrs").hide();
		}
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
	}
	
	function showCheckboxes1()
	{

	var checkboxes = document.getElementById("checkboxes1");
			$("#checkboxes1").toggle();
			$('.cont_comd').each(function() {
				$(this).show()
			})

	}

	
	function showCheckboxes5() {
	 	
		var checkboxes = document.getElementById("checkboxes5");
		$("#checkboxes5").toggle();
			$('.cont_comd').each(function() {
				$(this).show();
			})
	}
		function setchecked(val)
		{
			
			var myArray = val.split(",");

			 $(' input[type="checkbox"]').each(function() {   	
				 if(myArray.includes(this.value)){
						$( this ).attr( 'checked', true );
				 }
		      });
			  
		}
		function filterOptions(a,b) {
	        var searchBox = document.getElementById(a);
	        var checkboxes = document.querySelectorAll(b);

	        checkboxes.forEach(function (checkbox) {
	            var labelText = checkbox.textContent || checkbox.innerText;
	            var shouldShow = labelText.toLowerCase().includes(searchBox.value.toLowerCase());
	            checkbox.style.display = shouldShow ? 'block' : 'none';
	            
	        });
	        searchBox.value = searchBox.value.toUpperCase();
	    }
		
		function setchecked(val)
		{
// 			var w='${CheckVal2}';
			
			var  myArray = val.split(",");

			 $(' input[type="checkbox"]').each(function() {   	
				 if(myArray.includes(this.value)){
						$( this ).attr( 'checked', true );
				 }
		      });
			  
		}
		
	
	
</script>