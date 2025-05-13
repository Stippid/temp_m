<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.min.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/jquery.mockjax.min.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.mockjax.js"></script>

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<style type="text/css">
	 table.dataTable, table.dataTable th, table.dataTable td {
		-webkit-box-sizing: content-box;
		-moz-box-sizing: content-box;
		box-sizing: content-box;
		width: 90px;
		text-align: center;
		font-size: 10px;
	}
	
</style>

<form action="" method="post" enctype="multipart/form-data" class="form-horizontal"> 
	<div class="animated fadeIn">
		
	    	<div class="col-md-12" align="center">
	    		<div class="card">
	    			<div class="card-header"><h5>HELD STR OF JCO/OR BY USER ARMS/ FMN AND PARENT ARM/SERVICES.</h5></div>
	    			<div class="card-header"><h5>DATA AS ON : ${date}</h5></div>
	    			<div class="card-header"><h5>REPORT NO MISO/JCO/OR/03</h5></div>
	          			<!-- <div class="card-body card-block"> -->
		          			<%-- <div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
								    	<div class="col col-md-4">
								        	<label class=" form-control-label">Cont Comd </label>
								       	</div>
								        <div class="col-12 col-md-8">
								        	<select id="cont_comd" name="cont_comd" class="form-control" >
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
			                			<div class="col col-md-4">
			                  				<label class="form-control-label">Cont Corps</label>
			               				</div>
			                			<div class="col-12 col-md-8">
			                 				<select id="cont_corps" name="cont_corps" class="form-control" >
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
						                <div class="col col-md-4">
						                  <label class=" form-control-label">Cont Div</label>
						                </div>
						                <div class="col-12 col-md-8">
						                 	<select id="cont_div" name="cont_div" class="form-control" >
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
		                				<div class="col col-md-4">
		                  					<label class=" form-control-label">Cont Bde</label>
		                				</div>
		                				<div class="col-12 col-md-8">
		                 					<select id="cont_bde" name="cont_bde" class="form-control" >
		                 						${selectBde}
		                 						<c:forEach var="item" items="${getBdeList}" varStatus="num" >
	                  								<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
	                  							</c:forEach>
		                 					</select>
		                				</div>
					            	</div>
								</div>
							</div> --%>
							<!-- <div class="col-md-12">
		          				<div class="col-md-6">
		          					<div class="row form-group">
										<div class="col col-md-4">
											<label class=" form-control-label">Unit Name </label>
										</div>
										<div class="col-12 col-md-8">			
											<input type="text" id="unit_name" name="unit_name" maxlength="100" placeholder="select Unit Name" class="form-control autocomplete" >
										</div>
									</div>
		          				</div>
		          				<div class="col-md-6">
		          					<div class="row form-group">
	                					<div class="col col-md-4">
	                  						<label class=" form-control-label">SUS No</label>
							            </div>
							            <div class="col-12 col-md-8">
											<input type="text" id="sus_no" name="sus_no" maxlength="8" placeholder="Select SUS No" class="form-control autocomplete" >
										</div>
	              					</div>
		          				</div>
		          			</div>		 -->
		          			<%-- <div class="col-md-12">
	              				 <div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-4">
	                  						<label for="text-input" class=" form-control-label">Age (From) </label>
	                					</div>
	                					<div class="col-12 col-md-8">
	                  						<input type="date" id="dt_from" name="dt_from" class="form-control">
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
	                					<div class="col col-md-4">
	                  						<label for="text-input" class=" form-control-label">Age (To) </label>
	                					</div>
	                					<div class="col-12 col-md-8">
	                  						<input type="date" id="dt_to" name="dt_to" class="form-control">
										</div>
	  								</div>
								</div> 
							</div>				
						   <div class="col-md-12">
							   <div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label">Marital  Status</label>
											</div>
											<div class="col-md-8">
												<select name="marital_status" id="marital_status"
													class="form-control">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getMarital_statusList}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
							</div> --%>
						<!-- </div> -->
								      
						</div>
	        		</div>
			</div>			
			
		<div class="col-md-12">
			<table id="User_ParentArms_reporttbl" class="display table no-margin table-striped  table-hover table-bordered ">
				<thead>
			        <tr>
						<th>CTPART I</th>
						<th>ARM</th>
						<th>ARM_DECS</th>
						<th>AC</th>
						<th>ARTY</th>
						<th>AAD</th>
						<th>AVN</th>
						<th>ENG</th>
						<th>SIG</th>
						<th>GR</th>
						<th>INF</th>
						<th>MECH</th>
						<th>ASC</th>
						<th>AOC</th>
						<th>EME</th>
						<th>APS</th>
						<th>AEC</th>
						<th>INT</th>
						<th>JAG</th>
						<th>APTC</th>
						<th>SL_RO</th>
						<th>AMC</th>
						<th>ADC</th>
						<th>RVC</th>
						<th>MF</th>
						<th>TOTAL</th>
			    </tr>
				</thead>
			</table>
		</div>
		
		<div class="col-md-12">
			<table id="User_ParentArms_reporttbl_ctpart2" class="display table no-margin table-striped  table-hover table-bordered ">
				<thead>
			        <tr>
			      		<th>CTPART II</th>
						<th>ARM</th>
						<th>ARM_DECS</th>
						<th>AC</th>
						<th>ARTY</th>
						<th>AAD</th>
						<th>AVN</th>
						<th>ENG</th>
						<th>SIG</th>
						<th>GR</th>
						<th>INF</th>
						<th>MECH</th>
						<th>ASC</th>
						<th>AOC</th>
						<th>EME</th>
						<th>APS</th>
						<th>AEC</th>
						<th>INT</th>
						<th>JAG</th>
						<th>APTC</th>
						<th>SL_RO</th>
						<th>AMC</th>
						<th>ADC</th>
						<th>RVC</th>
						<th>MF</th>
						<th>TOTAL</th>
			    </tr>
				</thead>
			</table>
		</div>
		
		<div class="card-footer" align="center">				
           	<i class="action_icons action_download"></i><input type="button"  class="btn btn-primary btn-sm" value="Print" onclick="downloaddata()" >
        	<i class="fa fa-file-excel-o" id="btnExport" style="font-size: x-large; color: green; text-align: right;" aria-hidden="true" title="EXPORT TO EXCEL" onclick="getExcel();"></i> 
        </div>	
		
</form>


<c:url value="Download_User_ParentArms_query_jco" var="dwonloadUrl"/>
<form:form action="${dwonloadUrl}" method="post" id="downloadForm" name="downloadForm" modelAttribute="cont_comd_dn">
	   <input type="hidden" name="cont_comd_dn" id="cont_comd_dn"  value="0">
	   <input type="hidden" name="cont_corps_dn" id="cont_corps_dn" value="0">
	   <input type="hidden" name="cont_div_dn" id="cont_div_dn" value="0">
	   <input type="hidden" name="cont_bde_dn" id="cont_bde_dn" value="0">
	   <input type="hidden" name="cont_comd_tx" id="cont_comd_tx" >
	   <input type="hidden" name="cont_corps_tx" id="cont_corps_tx">
	   <input type="hidden" name="cont_div_tx" id="cont_div_tx">
	   <input type="hidden" name="cont_bde_tx" id="cont_bde_tx">
	   <input type="hidden" name="unit_name_dn" id="unit_name_dn">
	   <input type="hidden" name="sus_no_dn" id="sus_no_dn">
</form:form>

<c:url value="Excel_User_ParentArms_query_jco" var="excelUrl" />
<form:form action="${excelUrl}" method="post" id="ExcelForm" name="ExcelForm" modelAttribute="typeReport1">
	   <input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form> 

<script>
$(document).ready(function() {  
	
	mockjax1('User_ParentArms_reporttbl');
	table = dataTable('User_ParentArms_reporttbl');
	$('#btn-reload').on('click', function(){
		table.ajax.reload();
	});
	$.ajaxSetup({
		async : false
	});
	mockjax1('User_ParentArms_reporttbl_ctpart2');
	table1 = dataTable('User_ParentArms_reporttbl_ctpart2');
	$('#btn-reload').on('click', function(){
		table1.ajax.reload();
	});

});

	
function data(tableName){
	if(tableName == "User_ParentArms_reporttbl"){
		jsondata = [];
		var table = $('#'+tableName).DataTable();
		var info = table.page.info();
	
		var currentPage = info.page;
	    var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = order[0][0] + 1;
		var orderType = order[0][1];
		
		var s_ac = 0; var s_arty = 0; var s_aad = 0; var s_avn = 0; var s_eng = 0; var s_sig = 0; var s_gr = 0; 
		var s_inf = 0; var s_mech = 0; var s_asc1 = 0; var s_aoc = 0; var s_eme = 0; 
		var s_aps = 0; var s_aec = 0; var s_int1 = 0; var s_jag = 0; var s_aptc = 0;
		var s_slro = 0; var s_amc = 0; var s_adc = 0; var s_rvs = 0; var s_mf = 0; var s_total = 0;
		$.post("getUser_ParentArms_ReportDataList_Jco?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType,
			},function(j) {
			
			for (var i = 0; i < j.length; i++) {
				jsondata.push([
					j[i].ct_part_i_ii,j[i].arm_code,j[i].arm_desc,
					j[i].ac,j[i].arty,j[i].aad,j[i].avn,j[i].eng,j[i].sig,j[i].gr,
					j[i].inf,j[i].mech,j[i].asc1,j[i].aoc,j[i].eme,
					j[i].aps,j[i].aec,j[i].int1,j[i].jag,j[i].aptc,
					j[i].slro,j[i].amc,j[i].adc,j[i].rvc,j[i].mf,j[i].total]);
				
				 s_ac += j[i].ac; s_arty += j[i].arty; s_aad += j[i].aad;s_avn += j[i].avn; s_eng += j[i].eng; s_sig += j[i].sig; s_gr += j[i].gr;
				 s_inf += j[i].inf; s_mech += j[i].mech; s_asc1 += j[i].asc1; s_aoc += j[i].aoc; s_eme += j[i].eme;
				 s_aps += j[i].aps; s_aec += j[i].aec; s_int1 += j[i].int1; s_jag += j[i].jag; s_aptc += j[i].aptc;			 
				 s_slro += j[i].slro; s_amc += j[i].amc; s_adc += j[i].adc; s_rvs += j[i].rvc; s_mf += j[i].mf; s_total += j[i].total;
			}
			jsondata.push(["","","TOTAL",
				s_ac,s_arty,s_aad,s_avn,s_eng,s_sig,s_gr,
				s_inf,s_mech,s_asc1,s_aoc,s_eme,
				s_aps,s_aec,s_int1,s_jag,s_aptc,
				s_slro,s_amc,s_adc,s_rvs,s_mf,s_total]);
		});
		$.post("getUser_ParentArms_TotalCount_Jco?"+key+"="+value,{Search:Search},function(j) {
			FilteredRecords = j;
		});
	}
	if(tableName == "User_ParentArms_reporttbl_ctpart2"){
		jsondata = [];
		var table = $('#'+tableName).DataTable();
		var info = table.page.info();

		var currentPage = info.page;
	    var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = order[0][0] + 1;
		var orderType = order[0][1];
		
		var s_ac = 0; var s_arty = 0; var s_aad = 0; var s_avn = 0; var s_eng = 0; var s_sig = 0; var s_gr = 0; 
		var s_inf = 0; var s_mech = 0; var s_asc1 = 0; var s_aoc = 0; var s_eme = 0; 
		var s_aps = 0; var s_aec = 0; var s_int1 = 0; var s_jag = 0; var s_aptc = 0;
		var s_slro = 0; var s_amc = 0; var s_adc = 0; var s_rvs = 0; var s_mf = 0; var s_total = 0;
		$.post("getUser_ParentArms_reporttbl_ctpart2_Jco?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType,
			},function(j) {
			
			for (var i = 0; i < j.length; i++) {
				jsondata.push([
					j[i].ct_part_i_ii,j[i].arm_code,j[i].arm_desc,
					j[i].ac,j[i].arty,j[i].aad,j[i].avn,j[i].eng,j[i].sig,j[i].gr,
					j[i].inf,j[i].mech,j[i].asc1,j[i].aoc,j[i].eme,
					j[i].aps,j[i].aec,j[i].int1,j[i].jag,j[i].aptc,
					j[i].slro,j[i].amc,j[i].adc,j[i].rvc,j[i].mf,j[i].total]);
				
				 s_ac += j[i].ac; s_arty += j[i].arty; s_aad += j[i].aad;s_avn += j[i].avn; s_eng += j[i].eng; s_sig += j[i].sig; s_gr += j[i].gr;
				 s_inf += j[i].inf; s_mech += j[i].mech; s_asc1 += j[i].asc1; s_aoc += j[i].aoc; s_eme += j[i].eme;
				 s_aps += j[i].aps; s_aec += j[i].aec; s_int1 += j[i].int1; s_jag += j[i].jag; s_aptc += j[i].aptc;				 
				 s_slro += j[i].slro; s_amc += j[i].amc; s_adc += j[i].adc; s_rvs += j[i].rvc; s_mf += j[i].mf; s_total += j[i].total;
			}
			jsondata.push(["","","TOTAL",
				s_ac,s_arty,s_aad,s_avn,s_eng,s_sig,s_gr,
				s_inf,s_mech,s_asc1,s_aoc,s_eme,
				s_aps,s_aec,s_int1,s_jag,s_aptc,
				s_slro,s_amc,s_adc,s_rvs,s_mf,s_total]);
		});
		$.post("getUser_ParentArms_reporttbl_ctpart2_TotalCount_Jco?"+key+"="+value,{Search:Search},function(j) {
			FilteredRecords = j;
		});
	}
}	

function downloaddata(){
	var cont_comd_tx = $("#cont_comd option:selected").text();
 	var cont_corps_tx=$("#cont_corps option:selected").text();
 	var cont_div_tx=$("#cont_div option:selected").text();
 	var cont_bde_tx=$("#cont_bde option:selected").text();
 	var marital_status_tx=$("#marital_status option:selected").text();
 	
	var cont_comd=$("#cont_comd").val();
 	var cont_corps=$("#cont_corps").val();
 	var cont_div=$("#cont_div").val();
 	var cont_bde=$("#cont_bde").val();
 	var unit_name=$("#unit_name").val();
 	var sus_no=$("#sus_no").val();
	
 	$("#cont_comd_tx").val(cont_comd_tx);
	$("#cont_corps_tx").val(cont_corps_tx);
	$("#cont_div_tx").val(cont_div_tx);
	$("#cont_bde_tx").val(cont_bde_tx);
	
	
	$("#cont_comd_dn").val(cont_comd);
	$("#cont_corps_dn").val(cont_corps);
	$("#cont_div_dn").val(cont_div);
	$("#cont_bde_dn").val(cont_bde);
	$("#unit_name_dn").val(unit_name);
	$("#sus_no_dn").val(sus_no);
	document.getElementById('downloadForm').submit();		 
}

function getExcel() {	
	
	document.getElementById('typeReport1').value = 'excelL';
	document.getElementById('ExcelForm').submit();
}
</script>