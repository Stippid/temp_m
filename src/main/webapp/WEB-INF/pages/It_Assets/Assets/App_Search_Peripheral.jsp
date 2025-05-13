
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



  
      <div class="" align="center">
          <div class="card">
              <div class="card-header">
		             <h5>EDIT PERIPHERAL CENSUS RETURN</h5>
		             
		      </div>
			<div class="card-body card-block">
				<div class="row">
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
									<input type="text" id="year_of_manufacturing" name="year_of_manufacturing" class="form-control autocomplete" maxlength="4" onkeypress="return isNumber(event)" onkeyup="validateYear(this);" onblur="validateYearLn(this);" autocomplete="off"></input>
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
									<input type="text" id="machine_no" name="machine_no" class="form-control autocomplete" autocomplete="off"/>
								</div>

							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3">
									<label for="text-input" class=" form-control-label"><strong style="color: red;"></strong>Model Number</label>
								</div>
								<div class="col-md-8">
										<input type="text" id="model_no" name="model_no" class="form-control autocomplete" autocomplete="off"/>
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
<!-- 										<input type="date" id="from_date" name="from_date" class="form-control autocomplete" autocomplete="off"/> -->
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
<!-- 										<input type="date" id="to_date" name="to_date" class="form-control autocomplete" autocomplete="off"/> -->
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
									<option value="0" >Pending for Approval</option>
									<option value="1" >Approved</option>
									<option value="3" >Rejected</option>
									</select>
								</div>

							</div>
						</div>
						
							</div>
			
				</div>
			</div>
	    	<div class="card-footer" align="center">
			     <a href="Search_App_PeripheralUrl"class="btn btn-primary btn-sm"  id="btn_clc">Clear</a>
		          <i class="fa fa-search"></i><input type="button" class="btn btn-success btn-sm" onclick="Search();" value="Search"  />            
              </div>
          </div>
          
          <div class="" id="divPrint" >				  
				   <div  class="watermarked" data-watermark="" id="divwatermark"><span id="ip"></span>
		                 <table id="BloodReport" class="display">
		                      <thead >
		                          <tr>
<!-- 			                         <th>Ser No</th>                                                                               -->
                                     <th id="2">Peripheral Type</th>
                                     <th id="3">Type of HW</th>
                                     <th id="4">Year Of Proc</th>
                                     <th id="5">Year Of Manufacturing</th>
                                     <th id="6">Proc Cost</th>
                                     <th id="7">Machine Make</th>
                                     <th id="8">Machine No</th>
                                     <th id="9">Make & Model</th>
                                     <th id="10">Remarks</th>
                                     <th id="11">Warranty</th>
                                     <th id="12">Serviceable State</th>
                                     <th id="13">UN-Serviceable State</th>
                                  <th data-orderable="false" >Action</th>
		                          </tr>                                                        
		                      </thead> 

		                 </table>
		            </div>	          
	        </div> 
      </div>
  
<c:url value="App_Search_PeripheralUrl" var="App_Search_PeripheralUrl" />
<form:form action="${App_Search_PeripheralUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="id7">
     <input type="hidden" name="assets_type1" id="assets_type1" value="0"/>
     <input type="hidden" name="year_of_manufacturing1" id="year_of_manufacturing1" value=""/>
     <input type="hidden" name="machine_make1" id="machine_make1" value=""/>
     <input type="hidden" name="machine_no1" id="machine_no1" value="0"/>
     <input type="hidden" name="model_no1" id="model_no1" value="0"/>
     <input type="hidden" name="unit_sus_no1" id="unit_sus_no1" value="0"/>
     <input type="hidden" name="unit_name1" id="unit_name1" value=""/>
      <input type="hidden" name="from_date1" id="from_date1" value=""/>
     <input type="hidden" name="to_date1" id="to_date1" value=""/>
      <input type="hidden" name="status1" id="status1" value="0"/>
    
</form:form>

<!--** AHM BISAG **  --> 

 <c:url value="AppEditPeripheralUrl" var="AppEditPeripheralUrl" />
<form:form action="${AppEditPeripheralUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="id5" >
<input type="hidden" name="id5" id="id5" value="0" />
	<input type="hidden" name="ch_id1" id="ch_id1" value="0" />
</form:form> 

<!--** END AHM BISAG **  --> 
	
 <c:url value="View_Peripherals" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm" name="viewForm" modelAttribute="id4" >
	<input type="hidden" name="viewid" id="viewid" value="0" />
</form:form>  
	
 <c:url value="Delete_Peripherals" var="deleteUrl" />
	<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="id1">
		<input type="hidden" name="id1" id="id1" value="0"/> 
	</form:form>
	
	
 <c:url value="Approve_Update_Peripheral" var="Approve_Update_Peripheral" />
<form:form action="${Approve_Update_Peripheral}" method="post" id="approveForm" name="approveForm" modelAttribute="id3">
	<input type="hidden" name="id3" id="id3" value="0"/> 
	 <input type="hidden" name="status" id="status" value="0"/>
		<input type="hidden" name="modified_by" id="modified_by" />
		<input type="hidden" name="modified_date" id="modified_date" value="0"/>
</form:form>	
<c:url value="Reject_Update_Peripheral" var="Reject_Update_Peripheral" />
<form:form action="${Reject_Update_Peripheral}" method="post" id="rejectForm" name="rejectForm" modelAttribute="id9">
	<input type="hidden" name="id9" id="id9" value="0"/> 
</form:form>


<script type="text/javascript">
function data(BloodReport){
        jsondata = [];

        var table = $('#'+BloodReport).DataTable();
        var info = table.page.info();
        var currentPage = info.page;
        var pageLength = info.length;
        var startPage = info.start;
        var endPage = info.end;
        var Search = table.search();
        var order = table.order();
        var orderColunm = $(table.column(order[0][0]).header()).attr('id');
        var orderType = order[0][1];
        
        var assets_type=$("#assets_type").val() ;
         var year_of_manufacturing=$("#year_of_manufacturing").val() ;
         var proc_cost=$("#proc_cost").val() ;
         var machine_make=$("#machine_make").val() ;
         var machine_no=$("#machine_no").val() ;
         var model_no=$("#model_no").val() ;
         var unit_sus_no=$("#unit_sus_no").val() ;
         var unit_name=$("#unit_name").val() ;
         var from_date=$("#from_date").val() ;
         var to_date=$("#to_date").val() ;
         var status=$("#status").val() ;

         
        $.post("getFilteredPeripheral?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType,
        	assets_type:assets_type,year_of_manufacturing:year_of_manufacturing,proc_cost:proc_cost,
                machine_make:machine_make,machine_no:machine_no,model_no:model_no,unit_sus_no:unit_sus_no,unit_name:unit_name,from_date:from_date,
                to_date:to_date,status:status},function(j) {
                        
                for (var i = 0; i < j.length; i++) {
                        jsondata.push([j[i].assets_name,j[i].type_of_hw,j[i].year_of_proc,j[i].year_of_manufacturing,j[i].b_cost,j[i].make_name,j[i].machine_no,
                                j[i].model_no,j[i].remarks,j[i].warranty,j[i].service_state
                                ,j[i].unservice_state,j[i].action]);
                }
        });
        $.post("getTotalPeripheralCount?"+key+"="+value,{Search:Search,assets_type:assets_type,year_of_manufacturing:year_of_manufacturing,proc_cost:proc_cost,
            machine_make:machine_make,machine_no:machine_no,model_no:model_no,unit_sus_no:unit_sus_no,unit_name:unit_name,from_date:from_date,
            to_date:to_date,status:status},function(j) {
                FilteredRecords = j;
        });
}
</script>


<script>
function btn_clc(){
	location.reload(true);
}

 function deleteData(id){
	$("#id1").val(id);
	document.getElementById('deleteForm').submit();
} 
 
 function editData(id,ch_id){
	 $("#id5").val(id);
	 $("#ch_id1").val(ch_id);
	document.getElementById('updateForm').submit();
} 
function Search(){
	

	$("#assets_type1").val($("#assets_type").val());
	$("#year_of_manufacturing1").val($("#year_of_manufacturing").val());
	$("#machine_make1").val($("#machine_make").val());
	$("#machine_no1").val($("#machine_no").val());
	$("#model_no1").val($("#model_no").val());
	$("#unit_sus_no1").val($("#unit_sus_no").val());
	$("#unit_name1").val($("#unit_name").val());  
	$("#from_date1").val($("#from_date").val()); 
	$("#to_date1").val($("#to_date").val());
	$("#status1").val($("#status").val());
	$("#searchForm").submit();
}

function viewData(id){
	 $("#viewid").val(id);
	document.getElementById('viewForm').submit();
}

function Approve(id){
	$("#id3").val(id);
	document.getElementById('approveForm').submit();
	
}

function Reject(id){
	$("#id9").val(id);
	document.getElementById('rejectForm').submit();
}  

</script>

<script> 
$(document).ready(function() {

		        mockjax1('BloodReport');
		        table = dataTable('BloodReport');
		        $('#btn-reload').on('click', function(){
		            table.ajax.reload();
		    });
		        datepicketDate('from_date');
		    	datepicketDate('to_date');
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
	if('${status1}' != "")
	{
	$("#status").val("${status1}");
	}

});	
</script>