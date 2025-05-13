<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link href="js/JS_CSS/jquery.dataTables.min.css" rel="stylesheet"> 
<script src="js/JS_CSS/jquery.dataTables.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/miso/orbatJs/ChkDateLessThan.js" type="text/javascript"></script>

<!-- new -->
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 




<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>

        
<style type="text/css">
	  table.dataTable, table.dataTable th, table.dataTable td {
		-webkit-box-sizing: content-box;
		-moz-box-sizing: content-box;
		box-sizing: content-box;
		/* width: 80px; */
		text-align: center;
		
	}
	.dataTables_scrollHead {
		overflow-y:scroll !important;
		overflow-x:hidden !important;
	}
	.DataTables_sort_wrapper{
		/* width : 80px; */
	}
	
	table.dataTable tr.odd {
  			background-color: #f0e2f3;
	}
	table.dataTable thead {
  			background-color: #9c27b0;
  			color: white;
	}
		.dataTables_paginate.paging_full_numbers{
			margin-top: 0.755em;
		}
		.dataTables_paginate.paging_full_numbers a{
			background-color: #9c27b0;
			border: 1px solid #9c27b0;
			color: #fff;
			border-radius: 5px;
			padding: 3px 10px;
			margin-right: 5px;
		}
		.dataTables_paginate.paging_full_numbers a:hover{
			background-color: #cb5adf;
			border-color: #cb5adf;
		}
		.dataTables_info{
			color:#9c27b0 !important;
			font-weight: bold;
		}
		
		.print_btn{
		  margin:0 auto;
		}
		.print_btn input{
		  background-color: #9c27b0;
          border-color: #9c27b0;
		}
		.print_btn input:hover{
			background-color: #cb5adf;
			border-color: #cb5adf;
		}
	</style>
	
<form:form action="" method="post"  class="form-horizontal"> 
	<div class="animated fadeIn">
		<div class="container" align="center">
	    	<div class="row">
				<div class="card">
					<div class="card-header" style="border: 1px solid rgba(0,0,0,.125);"><h5><b>VIEW UNIT PROFILE</b></h5> <strong>Unit Details </strong> </div>
							<div class="card-body card-block">
	            				<!-- <form action="" method="post" enctype="multipart/form-data" class="form-horizontal"> -->
	            				<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
		                					<div class="col col-md-4">
		                  						<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Search For</label>
		                					</div>
		                					<div class="col-12 col-md-8">
		                						<div class="row form-group">
													<div class="col-md-6">
														<input type="radio" id="Active" name="srch-radios" value="Active" class=""> <!-- onclick="return getStatus('Active');"  --> 
														Active Units
													</div>	
													<div class=" col-md-6">
														<input type="radio" id="Inactive" name="srch-radios" value="Inactive" class="" ><!-- onclick="return getStatus('Inactive');" -->
														<label class=" form-control-label">Inactive Units</label>
													</div>
												</div>                					
		                					</div>
		              					</div>
									</div>
								</div>
								<div class="col-md-12">	
									<div class="col-md-6">
										 <div class="row form-group">
							                <div class="col col-md-4">
							                  <label class=" form-control-label">Unit Name</label>
							                </div>
							                <div class="col-12 col-md-8">
							                  <input type="text" id="unit_name" name="unit_name" maxlength="100" placeholder="Enter Unit Name" class="form-control" >
							                </div>
							              </div>
									</div>
									<div class="col-md-6">
										 <div class="row form-group">
							                <div class="col col-md-4">
							                  <label class=" form-control-label">SUS No</label>
							                </div>
							                <div class="col-12 col-md-8">
							                  <input type="text" id="sus_no" name="sus_no" maxlength="8" placeholder="Enter SUS No" class="form-control " >
							                </div>
							             </div>
									</div>
								</div>		
									
								<div class="col-md-12">	
									<%-- <div class="col-md-6">
										<div class="row form-group">
	                						<div class="col col-md-4">
	                  							<label class=" form-control-label">Parent Arm</label>
	                						</div>
	                						<div class="col-12 col-md-8">
	                 							<select name="parent_arm" id="parent_arm" class="form-control-sm form-control">
	                 								<option value="0">--Select--</option>
	                 								<c:forEach var="item" items="${getPrantArmList}" varStatus="num" >
		                  								<option value="${item.code}">${item.code} - ${item.code_value}</option>
		                  							</c:forEach>
	                 							</select>
	                						</div>
	              						</div>
									</div> --%>

							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label" id="fmn"><strong
											style="color: red;">*</strong>Fmn Name </label>
									</div>
									<div class="col-12 col-md-8">

										<input type="text" id="fmn_name" name="fmn_name" maxlength="100" style="font-family: 'FontAwesome',Arial;"  class="form-control autocomplete" autocomplete="off" >
									</div>
								</div>
							</div>
							
							<div class="col-md-6" style="height: 100%">
								<div class="row form-group">
						                	<div class="col col-md-4">
						                  		<label class=" form-control-label">LOC</label> <br>
						                  		<!-- <label class=" form-control-label">NRS</label> -->
						                	</div>
						                	<div class="col-12 col-md-8">
						                		<div style="display: none;">
						                			<input type="text" id="loc" name="loc" maxlength="100" class="form-control" style="width:86%;display: inline-block;" readonly="readonly">
						                		</div>
						                		<input type="text" id="code" name="code" maxlength="5" class="form-control" style="width:86%;height : 20%;display: inline-block;" readonly="readonly">
						                		<i class="fa fa-search" onclick="openLocLOV();"></i>
			              					</div>
			              					
			              		</div>
					          </div>
							
							
							
							</div>
								<div class="col-md-12">	
									<%-- <div class="col-md-6">
										<div class="row form-group"  style="height: 100%">
		                					<div class="col col-md-4">
		                  						<label class=" form-control-label">Arm Name</label>
		                					</div>
		                					<div class="col-12 col-md-8">
		                 						<select name="arm_name" id="arm_name" class="form-control-sm form-control" multiple="multiple">
		                 							<option value="0">--Select--</option>
		                  							<c:forEach var="item" items="${getArmNameList}" varStatus="num" >
		                  								<option value="${item.arm_code}" >${item.arm_code} - ${item.arm_desc}</option>
		                  							</c:forEach>
		                 						</select>
		                					</div>
		                				</div>	
									</div> --%>
									
									<div class="col-md-6">
									<div class="row form-group">
										<div class="col col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">*</strong> Arm Name</label>
										</div>
										<div class="col-12 col-md-8">
											<select id="arm_name" name="arm_name" class="form-control"
												onchange="updateFormCode()">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getArmNameList}"
													varStatus="num">
													<option value="${item.arm_code}">${item.arm_code}
														- ${item.arm_desc}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
									
								</div>
								
									
					</div>
					<div class="card-footer" align="center" class="form-control">
						<input type="reset" class="btn btn-success btn-sm" value="Clear">   
	              		<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" onclick="return getUnitReport();" value="Search"> 
	              	</div> 
		            <div class="col-md-12" id="unitReport" >
         				<datatables:table id="applicanttbl" url="getUnitProfileRpt_cii" serverSide="true" pipelining="true" pipeSize="3"	row="latlon" rowIdBase="applicant_id" rowIdPrefix="latlon_" displayLength="10"  jqueryUI="true"
	    					bDestroy="true" filterable="true" sortable="true" processing="true" border="1" scrollY="100%" autoWidth="true" pageable="true" paginationType="full_numbers" stateSave="false" deferRender="true"  scrollX="100%" scrollCollapse="true" >  
							    <datatables:column title="Ser No" property="id" searchable="false" data-halign="left" data-valign="left" visible="false" />
							    <datatables:column title="SUS No" property="sus_no" searchable="true" data-halign="left" data-valign="left"/>
							    <datatables:column title="Unit Name" property="unit_name" searchable="true" data-halign="left" data-valign="left" />
								<datatables:column title="Actions" renderFunction="actionsUnitDetails" sortable="false" searchable="false" display="HTML" />
						</datatables:table>    
                        <br/>
		        	</div>
	        	</div>
			</div>
		</div>
	</div>
</form:form>

	<c:url value="viewUnitDetailsUrlCii" var="viewUrl" />
	<form:form action="${viewUrl}" method="post" id="viewDetailsForm" name="viewDetailsForm" modelAttribute="id">
		<input type="hidden" name="id" id="id" value="0"/>
		<input type="hidden" name="statusSusDetails" id="statusSusDetails" value="0">
	</form:form> 
	

	<c:url value="search_unit_profileSetSession_cii" var="reloadUrl" />
	<form:form action="${reloadUrl}" method="post" id="reloadReport" name="reloadReport">
		<input type="hidden" name="status1" id="status1" />
		<input type="hidden" name="unit_name1" id="unit_name1" />
		<input type="hidden" name="sus_no1" id="sus_no1" />
		<input type="hidden" name="parent_arm1" id="parent_arm1" />
		<input type="hidden" name="fmn1" id="fmn1" />
		<input type="hidden" name="arm_name1" id="arm_name1" />
		<input type="hidden" name="loc1" id="loc1" />
		<input type="hidden" name="code1" id="code1" />
		
		<input type="hidden" name="fmn_name1" id="fmn_name1" />
		
		<input type="hidden" name="type_force1" id="type_force1" />
		<input type="hidden" name="ct_part_i_ii1" id="ct_part_i_ii1" />
		
		<input type="hidden" name="comm_depart_date1" id="comm_depart_date1" />
		<input type="hidden" name="compltn_arrv_date1" id="compltn_arrv_date1" /> 
	
	</form:form>
	

<script>
      $(document).ready(function() {
    	  $("input#fmn_name").keyup(function() {
    			var fmn_name = this.value;
    			var fmnNameAuto = $("#fmn_name");
    			fmnNameAuto.autocomplete({
    				source : function(request, response) {
    					$.ajax({
    						type : 'POST',
    						url : "getFmnName?" + key + "=" + value,
    						data : {
    							fmn_name : fmn_name
    						},
    						success : function(data) {
    							debugger
    							 var susval = [];
    					        	  var length = data.length-1;
    					        	  var enc = data[length].substring(0,16);
    						        	for(var i = 0;i<data.length;i++){
    						        		susval.push(dec(enc,data[i]));
    						        	}
    							
    							response(susval);
    						}
    					});
    				},
    				minLength : 1,
    				autoFill : true,
    			});
    		});
    	  
    	  $("input[name=srch-radios][value='${status1}']").prop("checked",true);
    	  $("#unit_name").val('${unit_name1}');
    	  $("#sus_no").val('${sus_no1}');
    	/* if('${parent_arm1}' != ""){
    	  	$("select#parent_arm").val('${parent_arm1}');
    	} */
    	//$("#fmn").val('${fmn1}');  
    	$("#arm_name").val('${arm_name1}');
    	//$("#fmn").val('${fmn1}');
    	//$("#loc").val('${loc1}');
    	//$("#code").val('${code1}');
    	//$("#type_force").val('${type_force1}');
    	//$("#ct_part_i_ii").val('${ct_part_i_ii1}');
    	//$("#comm_depart_date").val('${comm_depart_date1}');
    	//$("#compltn_arrv_date").val('${compltn_arrv_date1}');
    	$("#fmn_name").val('${fmn_name1}');
    		
    	if('${status1}' != ''){
    	  //	getStatus()
		}else{
       	 	$("#unitReport").hide();
       	}
    }); 
       
    function getUnitReport() {
    	 
    	document.getElementById('status1').value = $('input[name=srch-radios]:checked').val();
    	
    	if($('input[name=srch-radios]:checked').val()== undefined){
    		alert("please Select one Search for Active Units or Inactive Units")
    		return false;
    	}
    	document.getElementById('unit_name1').value = $("#unit_name").val();
    	document.getElementById('sus_no1').value = $("#sus_no").val();
    	//document.getElementById('parent_arm1').value = $("#parent_arm").val();
    	document.getElementById('fmn_name1').value = $("#fmn_name").val();
    	//document.getElementById('fmn1').value = $("#fmn").val();
    	document.getElementById('arm_name1').value = $("#arm_name").val();
    	//document.getElementById('code1').value = $("#code").val();
    	//document.getElementById('loc1').value = $("#loc").val();
    	//document.getElementById('type_force1').value = $("#type_force").val();
    	//document.getElementById('ct_part_i_ii1').value = $("#ct_part_i_ii").val();
    	//document.getElementById('comm_depart_date1').value = $("#comm_depart_date").val();
    	//document.getElementById('compltn_arrv_date1').value = $("#compltn_arrv_date").val();
    	 $("#WaitLoader").show();
		document.getElementById('reloadReport').submit();
		
    	$("div#unitReport").show();
		return false;	
	}
    
    function actionsUnitDetails(data, type, full) {
    	var f="";
    	var view = "onclick=\"  viewDetails('"+full.id+"')\"";
		f +='<a hreF="#" '+view+' title="View Data"><i class="fa fa-eye" style="font-size:24px;color:red"></i> </a>'; 
		return f;
	}
    
    function viewDetails(id) {
    	document.getElementById("statusSusDetails").value = $('input[name=srch-radios]:checked').val();
    	document.getElementById("id").value=id;
    	document.getElementById("viewDetailsForm").submit();
	}
    
    var popupWindow = null
    function openLocLOV(url) {
    	popupWindow = window.open("locationLOV", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=900,height=600,fullscreen=no");
    }
    function HandlePopupResult(loc,nrs_name,loc_code,trn_type,typeofloc,nrscode) {
        $("#loc").val(loc_code);
    	//$("#nrs_name").val(nrs_name);
    	$("#code").val(loc);
    }
    function parent_disable() {
    	if(popupWindow && !popupWindow.closed)
    		popupWindow.focus();
    }
</script>

