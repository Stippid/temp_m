
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
<script src="js/Calender/datePicketValidation.js"></script>
  <!--Replaced by dev 18-12-24  -->
      <div class="" align="center">
          <div class="card">
              <div class="card-header">
		             <h5>SEARCH COMPUTING ASSETS</h5>
		             
		      </div>
			<div class="card-body card-block">
				<div class="row">
				 <div class="col-md-12">
									<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-3">
													<label class=" form-control-label"><strong style="color: red;">* </strong> Unit Name </label>
												</div>
												<div class="col-md-8">
												<!-- 	<input type="text" id="unit_name" name="unit_name" class="form-control autocomplete" autocomplete="off" maxlength="50">				 -->
														
									<input type="text" id="unit_name" name="unit_name" class="form-control autocomplete" autocomplete="off" maxlength="50" style="display: none">				
										   <label  id="unit_name_l" style="display: none" ><b>${unit_name}</b></label> 	
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-3">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong> Unit SUS No </label>
												</div>
												<div class="col-md-8">
											
											 <label id="unit_sus_no_hid" style="display: none" ><b> ${roleSusNo} </b></label>   
						                 <input type="text" id="unit_sus_no" name="unit_sus_no" class="form-control autocomplete" autocomplete="off"   style="display: none"> 
												
												</div>
											</div>
										</div>
								</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3">
									<label for="text-input" class=" form-control-label"><strong style="color: red;"> </strong>Computing Assets Type</label>
								</div>
								<div class="col-md-8">
									<select name="assets_type" id="assets_type" class="form-control">
									<option value="0" >--select--</option>
										<c:forEach var="item" items="${ComputingAssetList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>

							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3">
									<label for="text-input" class=" form-control-label"><strong style="color: red;"> </strong>Model No.</label>
								</div>
								<div class="col-md-8">
									<input type="text" name="model_number" id="model_number" class="form-control" maxlength="50" onkeypress="return onlyAlphaNumeric(event, this)"/>
								</div>

							</div>
						</div>
						
					</div>
					
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3">
									<label for="text-input" class=" form-control-label"><strong style="color: red;"> </strong>Machine Number</label>
								</div>
								<div class="col-md-8">
									<input type="text" name="machine_number" id="machine_number" class="form-control" maxlength="50" onkeypress="return onlyAlphaNumeric(event, this)"/>
								</div>

							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3">
									<label for="text-input" class=" form-control-label"><strong style="color: red;"> </strong>RAM Capacity</label>
								</div>
								<div class="col-md-8">
									<select name="ram_capi" id="ram_capi" class="form-control">
									<option value="0" >--select--</option>
										<c:forEach var="item" items="${ramList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>

							</div>
						</div>
						
					</div>
					
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3">
									<label for="text-input" class=" form-control-label"><strong style="color: red;"> </strong>HDD Capacity</label>
								</div>
								<div class="col-md-8">
									<select name="hdd_capi" id="hdd_capi" class="form-control">
									<option value="0" >--select--</option>
										<c:forEach var="item" items="${hddList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>

							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3">
									<label for="text-input" class=" form-control-label"><strong style="color: red;"></strong>Operating System</label>
								</div>
								<div class="col-md-8">
									<select name="operating_system" id="operating_system" class="form-control">
									<option value="0" >--select--</option>
										<c:forEach var="item" items="${osList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>

							</div>
						</div>
						
					</div>
					
					
					
					<div class="col-md-12" style="display: none">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3">
									<label for="text-input" class=" form-control-label"><strong style="color: red;"></strong>Unit Sus No</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="unit_sus_no" name="unit_sus_no" class="form-control autocomplete" autocomplete="off"/>
								</div>

							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3">
									<label for="text-input" class=" form-control-label"><strong style="color: red;"> </strong>Unit Name</label>
								</div>
								<div class="col-md-8">
								<input type="text" id="unit_name" name="unit_name" class="form-control autocomplete" autocomplete="off"/>
								</div>

							</div>
						</div>
						
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3">
									<label for="text-input" class=" form-control-label"><strong style="color: red;"></strong>From Date</label>
								</div>
								<div class="col-md-8">
									<input type="text" name="from_date" id="from_date" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 94%;display: inline;"
							onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
							onchange="clickrecall(this,'DD/MM/YYYY');" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
								</div>

							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3">
									<label for="text-input" class=" form-control-label"><strong style="color: red;"> </strong>To Date</label>
								</div>
								<div class="col-md-8">
								<input type="text" name="to_date" id="to_date" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 94%;display: inline;"
							onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
							onchange="clickrecall(this,'DD/MM/YYYY');" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
								</div>

							</div>
						</div>
						
					</div>
					
					<div class="col-md-12">
						
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3">
									<label for="text-input" class=" form-control-label"><strong style="color: red;"></strong>Status</label>
								</div>
								<div class="col-md-8">
									<select name="status" id="status" class="form-control">
									<option value="0" >Pending</option>
									<option value="1" >Approved</option>
									<option value="3" >Rejected</option>
									<option value="4" >Deleted</option>
									</select>
								</div>

							</div>
						</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-3">
										<label class=" form-control-label">Serviceable State</label>
									</div>
									<div class="col-md-8">
										<select name="s_state" id="s_state"
											class="form-control" onchange="serviceStChange();">
											<option value="0">--select--</option>
											<c:forEach var="item" items="${getServiceable_StateList}"
												varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>

									</div>
								</div>
							</div>
					</div>
					 
			
				</div>
			</div>
	    	<div class="card-footer" align="center">
			     <a href="Search_Computing_AssetsUrl" class="btn btn-primary btn-sm"  id="btn_clc">Clear</a>
		          <i class="fa fa-search"></i><input type="button" class="btn btn-success btn-sm"  value="Search" onclick="Search();" />            
<!-- 		          <i class="fa fa-download"></i><input type="button" class="btn btn-success btn-sm"  value="Download Computing Data" onclick="getExcel1();" />             -->
              </div>
          </div>
          
          <div class="" id="divPrint" >				  
				   <div  class="watermarked" data-watermark="" id="divwatermark"><span id="ip"></span>
				   <input type="hidden" id="CheckVal" name="CheckVal">
				   <c:if test="${(roleType == 'APP' || roleType == 'ALL')  && status1 == '0'}">
				   <b><input type=checkbox id='nSelAll' name='tregn'
						onclick='setselectall();'>Select all [<span id="tregn">0</span><span
							id='nTotRow1'>/</span><span id="tregnn"> ${list.size()}</span>]</b> 
							</c:if>	
							
		                 <table id="BloodReport" class="display">
		                      <thead >
		                          <tr>
			                          <th id="2">Computing Assets Type</th>
                                     <th id="3">Make & Model</th>
                                     <th id="4">Machine No</th>
                                     <th id="5">Mac Address</th>
                                     <th id="6">Ip Address</th>
                                     <th id="7">Processor Type</th>
                                     <th id="8">RAM Capacity</th>
                                     <th id="9">HDD Capacity</th>
                                     <th id="10">Operating System</th>
                                     <th id="11">Office</th>
                                     <th id="12">OS/Firmware Activation and
										subsequent Patch Updation Mechanism</th>
                                     <th id="13">Deply Envt as Applicable</th>
                                     <c:if test="${(roleType == 'APP' || roleType == 'ALL') && status1 == '0'}">
										<th>Select To Approve/Reject</th>
										</c:if>
                                     
                                     <th class="action">Action</th>
		                          </tr>                                                        
		                      </thead> 

		                 </table>
		                  <c:if test="${(roleType == 'APP' || roleType == 'ALL') && status1 == '0'}">
		                 <input type="button" class="btn btn-success btn-sm" value="Approve"
							onclick="return setApproveStatus();">
							<input type="button" class="btn btn-success btn-sm" value="Reject"
							onclick="return setRejectStatus();">
							</c:if>
		            </div>	          
	        </div>
	        
	        <input type="hidden" id="temp_status" name="temp_status" value="${temp_status}">
	        
	        <div style="display: none;" id="divAD">
				<div class="watermarked" data-watermark="" id="divwatermark"><span id="ip"></span>
					<input type="hidden" id="CheckVal" name="CheckVal">
					<table id="AssetsDeletedTable">
                    	<thead>
							<tr>
								<th>Machine No.</th>
                                <th>SUS No.</th>
                                <th>Deleted Date</th>
                                <th>Deleted By</th>
                                <th class="action">Action</th>
                          	</tr>                                                        
                      	</thead> 
                 	</table>
				</div>	          
			</div>
      </div>
  

<c:url value="Search_Computing_AssetsUrl_1" var="Search_Computing_AssetsUrl_1" />
<form:form action="${Search_Computing_AssetsUrl_1}" method="post" id="searchForm" name="searchForm" modelAttribute="computing_assets1">
     <input type="hidden" name="computing_assets1" id="computing_assets1" value="0"/>
     <input type="hidden" name="model_number1" id="model_number1" value=""/>
     <input type="hidden" name="machine_number1" id="machine_number1" value=""/>
     <input type="hidden" name="ram_capi1" id="ram_capi1" value="0"/>
     <input type="hidden" name="hdd_capi1" id="hdd_capi1" value="0"/>
     <input type="hidden" name="operating_system1" id="operating_system1" value="0"/>
     <input type="hidden" name="unit_sus_no1" id="unit_sus_no1" value=""/>
     <input type="hidden" name="unit_name1" id="unit_name1" value=""/>
      <input type="hidden" name="from_date1" id="from_date1" value=""/>
     <input type="hidden" name="to_date1" id="to_date1" value=""/>
     <input type="hidden" name="assets_type1" id="assets_type1" value="0"/>
      <input type="hidden" name="status1" id="status1" value="0"/>
      <input type="hidden" name="s_state1" id="s_state1" value="0"/>
    
</form:form>

 <c:url value="ComputingAssertsEdit" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="id" >
	<input type="hidden" name="updateid" id="updateid" value="0" />
</form:form> 
	
 <c:url value="ComputingAssertsDelete" var="deleteUrl" />
	<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="id1">
		<input type="hidden" name="id1" id="id1" value="0"/> 
	</form:form>
	
	<c:url value="ComputingAssertsView" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm" name="viewForm" modelAttribute="id" >
	<input type="hidden" name="viewid" id="viewid" value="0" />
</form:form>

<c:url value="AssetDeletedView" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewDelForm" name="viewDelForm" modelAttribute="id" >
	<input type="hidden" name="viewdelid" id="viewdelid" value="0" />
</form:form>

<c:url value="demo_report1" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search1" name="search1" modelAttribute="comd2">
	<input type="hidden" name="typeReport2" id="typeReport2" value="0" />
</form:form>

<script type="text/javascript">
function data(tableName){
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
	
	var unit_name=$("#unit_name").val() ;
	var unit_sus_no=$("#unit_sus_no").val() ;
	var assets_type=$("#assets_type").val() ;
 	var model_number=$("#model_number").val() ;
 	var machine_number=$("#machine_number").val();
 	var ram_capi=$("#ram_capi").val() ;
 	var hdd_capi=$("#hdd_capi").val() ;
 	var operating_system=$("#operating_system").val() ;
 	var from_date=$("#from_date").val() ;
 	var to_date=$("#to_date").val() ;
 	var status=$("#status").val() ;
 	var s_state=$("#s_state").val() ;
	
	if(status != '4' && tableName == "BloodReport"){
		$.post("getFilteredassetcomputing?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType,
			assets_type:assets_type,model_number:model_number,machine_number:machine_number,ram_capi:ram_capi,hdd_capi:hdd_capi,operating_system:operating_system
			,from_date:from_date,to_date:to_date,status:status,s_state:s_state,unit_sus_no:unit_sus_no,unit_name:unit_name},function(j) {
				//left here
				for (var i = 0; i < j.length; i++) {
	         	var data =[j[i].assets_name,j[i].model_number,j[i].machine_number,j[i].mac_address,j[i].ip_address,j[i].processor_type,j[i].ram,
					j[i].hdd,j[i].os,j[i].office,j[i].os_firmware,j[i].dply_env
	                 <c:if test="${(roleType == 'APP' || roleType == 'ALL')  && status1 == '0'}">
	               	 ,j[i].chekboxaction
	                 </c:if>
	                 ,j[i].action ];
	                 jsondata.push(data);
	         }
				$("#nSelAll").prop('checked', false);
				$('#tregn').text("0");
				$('#tregnn').text(j.length);
				
			
		});
		$.post("getTotalAssetCount1?"+key+"="+value,{Search:Search,assets_type:assets_type,model_number:model_number,machine_number:machine_number,ram_capi:ram_capi,hdd_capi:hdd_capi,operating_system:operating_system
			,from_date:from_date,to_date:to_date,status:status,s_state:s_state,unit_sus_no:unit_sus_no,unit_name:unit_name},function(j) {
			FilteredRecords = j;
		});
	} else if (status == '4' && tableName == "AssetsDeletedTable"){
		$.post("DeletedAssetsData?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType,
			assets_type:assets_type,model_number:model_number,machine_number:machine_number,ram_capi:ram_capi,hdd_capi:hdd_capi,operating_system:operating_system
			,from_date:from_date,to_date:to_date,status:status,s_state:s_state,unit_sus_no:unit_sus_no,unit_name:unit_name},function(j) {
			for (var i = 0; i < j.length; i++) {
	         	var data =[j[i].machine_number, j[i].sus_no, j[i].deleted_date, j[i].deleted_by, j[i].action ];
	                 jsondata.push(data);
	         }
		});
		
		$.post("DeletedAssetsCount?"+key+"="+value,{Search:Search,assets_type:assets_type,model_number:model_number,machine_number:machine_number,ram_capi:ram_capi,hdd_capi:hdd_capi,operating_system:operating_system
			,from_date:from_date,to_date:to_date,status:status,s_state:s_state,unit_sus_no:unit_sus_no,unit_name:unit_name},function(j) {
			FilteredRecords = j;
		});
	}
 	
	
}
</script> 
 
<!-- for Functions -->
<script>
function getExcel1() {
	document.getElementById('typeReport2').value = 'excelL';
	document.getElementById('search1').submit();
} 
function btn_clc(){
	location.reload(true);
}

 function deleteData(id){
	$("#id1").val(id);
	document.getElementById('deleteForm').submit();
} 
 
 function editData(id){
	 $("#updateid").val(id);
	document.getElementById('updateForm').submit();
}
 
 function viewData(id){
	 $("#viewid").val(id);
	document.getElementById('viewForm').submit();
}
 
function viewDataDeleted(id){
	 $("#viewdelid").val(id);
	document.getElementById('viewDelForm').submit();
}
 
 function Search(){

	$("#computing_assets1").val($("#asset_type").val());
	$("#model_number1").val($("#model_number").val());
	$("#machine_number1").val($("#machine_number").val());
	$("#ram_capi1").val($("#ram_capi").val());
	$("#hdd_capi1").val($("#hdd_capi").val());
	$("#operating_system1").val($("#operating_system").val());
	$("#unit_sus_no1").val($("#unit_sus_no").val());  
	$("#unit_name1").val($("#unit_name").val()); 
	$("#from_date1").val($("#from_date").val());
	$("#to_date1").val($("#to_date").val());  
	$("#assets_type1").val($("#assets_type").val());
	$("#status1").val($("#status").val());
	$("#s_state1").val($("#s_state").val());
	
	$("#searchForm").submit();
}


</script>
<script>
function findselected(){
	var nrSel=$('.nrCheckBox:checkbox:checked').map(function() {
		return $(this).attr('id');
	}).get();
		
	var b=nrSel.join(':');
	$("#CheckVal").val(b);
	$('#tregn').text(nrSel.length);
}


function setselectall(){

	if ($("#nSelAll").prop("checked")) {
		$(".nrCheckBox").prop('checked', true);
	} else {
		$(".nrCheckBox").prop('checked', false);
	}
	
	var l = $('[name="cbox"]:checked').length;
	 $("#tregn").val(l);
	document.getElementById('tregn').innerHTML = l;
	
}



function drawtregn(data) {
	var ii=0;
	$("#nrTable").empty();

	for (var i = 0; i <data.length; i++) {
		 var nkrow="<tr id='nrTable' padding='5px;'>";
		 nkrow=nkrow+"<td>&nbsp;&nbsp;";
		 nkrow=nkrow+ data[i] + "("+data[i]+")</td>";
		 $("#nrTable").append(nkrow);
		 ii=ii+1;
	}
	$("#tregn").text(ii);
}

function setApproveStatus(){
	
	findselected();
	
	var a = $("#CheckVal").val();

	if(a == ""){
		alert("Please Select the Data for Approval"); 
	}else{

			$.post("Approve_ComputingAssetsData?"+key+"="+value, {a:a,status:"1"}).done(function(j) {
			alert(j);
			Search();
		}); 
	}	
}

function setRejectStatus(){
	
	findselected();
	
	var a = $("#CheckVal").val();

	if(a == ""){
		alert("Please Select the Data for Reject"); 
	}else{

			$.post("Approve_ComputingAssetsData?"+key+"="+value, {a:a,status:"3"}).done(function(j) {
			alert(j);
			Search();
		}); 
	}	
}



var check_count = 0;
function checkbox_count(obj,id)
{
	if(document.getElementById(obj.id).checked == true)
	{ 
		check_count++;
		
	}
	if(document.getElementById(obj.id).checked == false)
	{
		check_count--;
		
	}
	
	document.getElementById('tregn').innerHTML =check_count;
	
}

</script>

<!-- for On Load Methods -->
<script> 
$(document).ready(function() {
	var temp_status = $("#temp_status").val();
	if(temp_status === "true" || temp_status === "True") {
		$("#divPrint").show();
		$("#divAD").hide();
	} else if(temp_status === "false" || temp_status === "False") {
		$("#divPrint").hide();
		$("#divAD").show();
	}
	
	var r =('${roleAccess}');
	if( r == "Unit"){
		
		 $("#unit_sus_no_hid").show();
		 $("#unit_name_l").show();
		 
	}
	else  if(r == "MISO"){
		
		 $("input#unit_sus_no").show();		 
		 $("input#unit_name").show();
		
	}
	
	mockjax1('BloodReport');
	table = dataTable('BloodReport');
	
	mockjax1('AssetsDeletedTable');
	table2 = dataTable('AssetsDeletedTable');
	
	$('#btn-reload').on('click', function(){
    	table.ajax.reload();
    	table2.ajax.reload();
    });
	datepicketDate('from_date');
	datepicketDate('to_date');
	if('${status1}' != "")
	{
	$("#status").val("${status1}");
	}
	if('${assets_type1}' != "")
	{
	$("#assets_type").val("${assets_type1}");
	}
if('${model_number1}' != "")
{
$("input#model_number").val("${model_number1}");
}
if('${machine_number1}' != "")
{
$("input#machine_number").val("${machine_number1}");
}
if('${ram_capi1}' != "")
{
$("select#ram_capi").val("${ram_capi1}");
}
if('${hdd_capi1}' != "")
{
$("select#hdd_capi").val("${hdd_capi1}");
}
if('${operating_system1}' != "")
{
$("select#operating_system").val("${operating_system1}");
}
if('${from_date1}' != "")
{
$("input#from_date").val("${from_date1}");
}
if('${to_date1}' != "")
{
$("input#to_date").val("${to_date1}");
}
if('${s_state1}' != "")
{
$("#s_state").val("${s_state1}");
}
if('${unit_name1}' != "")
{
$("#unit_name").val("${unit_name1}");
}

if('${unit_sus_no1}' != "")
{
$("#unit_sus_no").val("${unit_sus_no1}");
}

		
	try{
		if(window.location.href.includes("msg=")){
			var url = window.location.href.split("?")[0];
		    window.location = url;
	    } 
	}catch (e) {
		// TODO: handle exception
	}	
});	

function validateNumber(evt) {
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if (charCode != 46 && charCode > 31 && (charCode  < 48||charCode> 57)) {
		return false;
	} else {
		if (charCode == 46) {
			return false;
		}
	}
	return true;
}
</script>


<script>
	
		// Unit SUS No

		$("#unit_sus_no").keyup(function(){
			var sus_no = this.value;
			var susNoAuto=$("#unit_sus_no");

			susNoAuto.autocomplete({
			      source: function( request, response ) {
			        $.ajax({
			        type: 'POST',
			        url: "getTargetSUSNoList?"+key+"="+value,
			        data: {sus_no:sus_no},
			          success: function( data ) {
			        	  var susval = [];
		                  var length = data.length-1;
		                  var enc = data[length].substring(0,16);
		                        for(var i = 0;i<data.length;i++){
		                                susval.push(dec(enc,data[i]));
		                        }
		                        var dataCountry1 = susval.join("|");
		                        var myResponse = []; 
						        var autoTextVal=susNoAuto.val();
						$.each(dataCountry1.toString().split("|"), function(i,e){
							var newE = e.substring(0, autoTextVal.length);
							if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
							  myResponse.push(e);
							}
						});      	          
						response( myResponse ); 
			          }
			        });
			      },
				  minLength: 1,
			      autoFill: true,
			      change: function(event, ui) {
			    	 if (ui.item) {   	        	  
			        	  return true;    	            
			          } else {
			        	  alert("Please Enter Approved Unit SUS No.");
			        	  document.getElementById("unit_name").value="";
			        	  susNoAuto.val("");	        	  
			        	  susNoAuto.focus();
			        	  return false;	             
			          }   	         
			    }, 
				select: function( event, ui ) {
					var sus_no = ui.item.value;			      	
					 $.post("getTargetUnitNameList?"+key+"="+value, {
						 sus_no:sus_no
		         }, function(j) {
		                
		         }).done(function(j) {
		        	 var length = j.length-1;
		             var enc = j[length].substring(0,16);
		             $("#unit_name").val(dec(enc,j[0]));   
		                 
		         }).fail(function(xhr, textStatus, errorThrown) {
		         });
				} 	     
			});	
		});

		// unit name
		 $("input#unit_name").keypress(function(){
				var unit_name = this.value;
					 var susNoAuto=$("#unit_name");
					  susNoAuto.autocomplete({
					      source: function( request, response ) {
					        $.ajax({
					        	type: 'POST',
						    	url: "getTargetUnitsNameActiveList?"+key+"="+value,
					        data: {unit_name:unit_name},
					          success: function( data ) {
					        	 var susval = [];
					        	  var length = data.length-1;
					        	  var enc = data[length].substring(0,16);
						        	for(var i = 0;i<data.length;i++){
						        		susval.push(dec(enc,data[i]));
						        	}
						        	   	          
								response( susval ); 
					          }
					        });
					      },
					      minLength: 1,
					      autoFill: true,
					      change: function(event, ui) {
					    	 if (ui.item) {   	        	  
					        	  return true;    	            
					          } else {
					        	  alert("Please Enter Approved Unit Name.");
					        	  document.getElementById("unit_name").value="";
					        	  susNoAuto.val("");	        	  
					        	  susNoAuto.focus();
					        	  return false;	             
					          }   	         
					      }, 
					      select: function( event, ui ) {
					    	 var unit_name = ui.item.value;
					    
						            $.post("getTargetSUSFromUNITNAME?"+key+"="+value,{target_unit_name:unit_name}, function(data) {
						            }).done(function(data) {
						            	var length = data.length-1;
							        	var enc = data[length].substring(0,16);
							        	$("#unit_sus_no").val(dec(enc,data[0]));
						            }).fail(function(xhr, textStatus, errorThrown) {
						            });
					      } 	     
					}); 			
			});

		</script>
 
 