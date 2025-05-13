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
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<!--Replaced by dev 18-12-24  -->

  
      <div class="" align="center">
          <div class="card">
              <div class="card-header">
		             <h5>SEARCH/EDIT PERIPHERAL ASSETS</h5>
		             
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
									<label for="text-input" class=" form-control-label"><strong style="color: red;"></strong>Peripheral Type</label>
								</div>
								<div class="col-md-8">
									<select name="assets_type" id="assets_type" class="form-control">
									<option value="0" >--select--</option>
										<c:forEach var="item" items="${getPeripheral}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>

							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3">
									<label for="text-input" class=" form-control-label"><strong style="color: red;"></strong>Year Of Manufacturing</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="year_of_manufacturing" name="year_of_manufacturing"  maxlength="4" class="form-control autocomplete" maxlength="4" onkeypress="return validateNumber(event)" onkeyup="validateYear(this);" onblur="validateYearLn(this);" autocomplete="off"></input>
								</div>

							</div>
						</div>
						
					</div>
					
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3">
									<label for="text-input" class=" form-control-label"><strong style="color: red;"></strong>Machine No</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="machine_no" name="machine_no" class="form-control autocomplete" maxlength="50" autocomplete="off" onkeypress="return onlyAlphaNumeric(event, this)"/>
								</div>

							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3">
									<label for="text-input" class=" form-control-label"><strong style="color: red;"></strong>Model Number</label>
								</div>
								<div class="col-md-8">
										<input type="text" id="model_no" name="model_no" class="form-control autocomplete" maxlength="50" autocomplete="off" onkeypress="return onlyAlphaNumeric(event, this)"/>
								</div>

							</div>
						</div>
					</div>
					
					<div class="col-md-12" style="display: none">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3">
									<label for="text-input" class=" form-control-label"><strong style="color: red;"> </strong>Unit Name</label>
								</div>
								<div class="col-md-8">
										<input type="text" id="unit_name" name="unit_name" class="form-control autocomplete"  autocomplete="off"/>
								</div>

							</div>
						</div>
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
						
						
					</div>
					<div class="col-md-12">
						
						
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3">
									<label for="text-input" class=" form-control-label"><strong style="color: red;"> </strong>From Date</label>
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
									<option value="0">Pending</option>
									<option value="1">Approved</option>
									<option value="3">Rejected</option>
									<option value="4">Deleted</option>
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
									  <select name="s_state" id="s_state" class="form-control" onchange="serviceStChange();">
									  	<option value="0">--Select--</option>
										 <c:forEach var="item" items="${getServiceable_StateList}" varStatus="num">
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
			     <a href="Search_PeripheralUrl"class="btn btn-primary btn-sm"  id="btn_clc">Clear</a>
		          <i class="fa fa-search"></i><input type="button" class="btn btn-success btn-sm"  value="Search" onclick="Search();" />            
<!-- 		           <i class="fa fa-download"></i><input type="button" class="btn btn-success btn-sm"  value="Download Peripheral Data" onclick="getExcel();" />             -->
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
                                     <th id="2">Peripheral Type</th>
                                     <th id="3">Type of HW</th>
                                     <th id="4">Year Of Proc</th>
                                     <th id="5">Year Of Manufacturing</th>
                                     <th id="8">Machine No</th>
                                     <th id="9">Make & Model</th>
                                     <th id="10">Remarks</th>
                                      <c:if test="${roleType == 'APP'  && status1 == '0'}">
										<th>Select To Approve</th>
										</c:if>
                                     <th data-orderable="false" >Action</th>
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
	        
	        <div style="display: none;" id="divPD">
				<div class="watermarked" data-watermark="" id="divwatermark"><span id="ip"></span>
					<input type="hidden" id="CheckVal" name="CheckVal">
					<table id="PeripheralDeletedTable">
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
  


<c:url value="Search_PeripheralUrl_1" var="Search_PeripheralUrl_1" />
<form:form action="${Search_PeripheralUrl_1}" method="post" id="searchForm" name="searchForm" modelAttribute="machine_make1">
     <input type="hidden" name="machine_make1" id="machine_make1" value=""/>
     <input type="hidden" name="machine_no1" id="machine_no1" value=""/>
     <input type="hidden" name="model_no1" id="model_no1" value=""/>   
     <input type="hidden" name="year_of_manufacturing1" id="year_of_manufacturing1" value="0"/>
     <input type="hidden" name="unit_sus_no1" id="unit_sus_no1" value=""/>
     <input type="hidden" name="unit_name1" id="unit_name1" value=""/>
     <input type="hidden" name="from_date1" id="from_date1" value=""/>
     <input type="hidden" name="to_date1" id="to_date1" value=""/>
     <input type="hidden" name="assets_type1" id="assets_type1" value="0"/>
      <input type="hidden" name="status1" id="status1" value="0"/>
       <input type="hidden" name="s_state1" id="s_state1" value="0"/>
     
</form:form>

 <c:url value="Edit_Peripherals" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="id" >
	<input type="hidden" name="updateid" id="updateid" value="0" />
</form:form> 
	
 <c:url value="Delete_Peripherals" var="deleteUrl" />
	<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="id1">
		<input type="hidden" name="id1" id="id1" value="0"/> 
	</form:form>
	
	 <c:url value="View_Peripherals" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm" name="viewForm" modelAttribute="id" >
	<input type="hidden" name="viewid" id="viewid" value="0" />
</form:form>

<c:url value="View_Deleted_Peripherals" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewDeletedForm" name="viewDeletedForm" modelAttribute="id" >
	<input type="hidden" name="viewdelid" id="viewdelid" value="0" />
</form:form>
 
<c:url value="demo_report" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2" name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form> 
 
<!-- for Functions -->
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
         var assets_type=$("#assets_type").val() ;
         var year_of_manufacturing=$("#year_of_manufacturing").val() ;
         var machine_make=$("#machine_make").val() ;
         var machine_no=$("#machine_no").val() ;
         var model_no=$("#model_no").val() ;
         var from_date=$("#from_date").val() ;
         var to_date=$("#to_date").val() ;
         var status=$("#status").val() ;
         var s_state=$("#s_state").val() ;
     	var unit_name=$("#unit_name").val() ;
     	var unit_sus_no=$("#unit_sus_no").val() ;
         
     	if(status != '4' && tableName == "BloodReport"){
         
        $.post("getFilteredPeripheral1?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType,
        	assets_type:assets_type,year_of_manufacturing:year_of_manufacturing,
                machine_make:machine_make,machine_no:machine_no,model_no:model_no,from_date:from_date,
                to_date:to_date,status:status,s_state:s_state,unit_sus_no:unit_sus_no,unit_name:unit_name},function(j) {
                        
                for (var i = 0; i < j.length; i++) {
                	var data =[j[i].assets_name,j[i].type_of_hw,j[i].year_of_proc,j[i].year_of_manufacturing,j[i].machine_no,
                        j[i].model_no,j[i].remarks
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
        $.post("getTotalPeripheralCount1?"+key+"="+value,{Search:Search,assets_type:assets_type,year_of_manufacturing:year_of_manufacturing,
            machine_make:machine_make,machine_no:machine_no,model_no:model_no,from_date:from_date,
            to_date:to_date,status:status,s_state:s_state,unit_sus_no:unit_sus_no,unit_name:unit_name},function(j) {
                FilteredRecords = j;
        });
     	} else if (status == '4' && tableName == "PeripheralDeletedTable"){
     		$.post("DeletedPeripheralData?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType,
            	assets_type:assets_type,year_of_manufacturing:year_of_manufacturing,
                    machine_make:machine_make,machine_no:machine_no,model_no:model_no,from_date:from_date,
                    to_date:to_date,status:status,s_state:s_state,unit_sus_no:unit_sus_no,unit_name:unit_name},function(j) {
                            
                    for (var i = 0; i < j.length; i++) {
                    	var data =[j[i].machine_no, j[i].sus_no, j[i].deleted_date, j[i].deleted_by, j[i].action];
						jsondata.push(data);
                    }
                    
            });
            $.post("DeletedPeripheralCount?"+key+"="+value,{Search:Search,assets_type:assets_type,year_of_manufacturing:year_of_manufacturing,
                machine_make:machine_make,machine_no:machine_no,model_no:model_no,from_date:from_date,
                to_date:to_date,status:status,s_state:s_state,unit_sus_no:unit_sus_no,unit_name:unit_name},function(j) {
                    FilteredRecords = j;
            });
     	}
}
</script>






<script>
function getExcel() {
	

	document.getElementById('typeReport1').value = 'excelL';
	document.getElementById('search2').submit();

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
 
function viewDeletedData(id){
	$("#viewdelid").val(id);
	document.getElementById('viewDeletedForm').submit();
}
 
function Search(){
	


	$("#machine_make1").val($("#machine_make").val()); 
	$("#machine_no1").val($("#machine_no").val());   
	$("#model_no1").val($("#model_no").val());   
	$("#year_of_manufacturing1").val($("#year_of_manufacturing").val()); 
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

			$.post("Approve_PeripheralAssetsData?"+key+"="+value, {a:a,status:"1"}).done(function(j) {
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

			$.post("Approve_PeripheralAssetsData?"+key+"="+value, {a:a,status:"3"}).done(function(j) {
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
		$("#divPD").hide();
	} else if(temp_status === "false" || temp_status === "False") {
		$("#divPrint").hide();
		$("#divPD").show();
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
	
	mockjax1('PeripheralDeletedTable');
	table2 = dataTable('PeripheralDeletedTable');
	
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
	if ("${assets_type1}" !=""){
		$("#assets_type").val("${assets_type1}");
	} 
	if ("${year_of_manufacturing1}" !=""){
		$("#year_of_manufacturing").val("${year_of_manufacturing1}");
	} 
	if ("${machine_make1}" !=""){
		$("input#machine_make").val("${machine_make1}");
	} 
	if ("${machine_no1}" !=""){
		$("#machine_no").val("${machine_no1}");
	} 
	if ("${model_no1}" !=""){
		$("#model_no").val("${model_no1}");
	} 
	if ("${from_date1}" !=""){
		$("#from_date").val("${from_date1}");
	} 
	if ("${to_date1}" !=""){
		$("#to_date").val("${to_date1}");
	} 
	if ("${s_state1}" !=""){
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

function validateYear(e){
	var year = $(e).val();
	
	if (year.length == 4  && (parseInt(year) <= 1890 || parseInt(year) >=2099)) {
		alert("Please Enter Year In Range");
		$(e).val("");
	}
}


function validateYearLn(e){
	var year = $(e).val();
	
	if (year.length >= 1 && year.length < 4 ) {
		alert("Please Enter Complete Year");
		$(e).val("");
		$(e).focus();
	}
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
 
 




