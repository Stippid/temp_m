<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion" %>  <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="dandelion" uri="http://github.com/dandelion" %>
<dandelion:asset cssExcludes="datatables"/> 
<dandelion:asset jsExcludes="datatables"/> 
<dandelion:asset jsExcludes="jquery"/> 

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script> <script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script> <script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<link href="js/JS_CSS/jquery.dataTables.min.css" rel="stylesheet">
<script src="js/JS_CSS/jquery.dataTables.js"></script>
<script src="js/miso/orbatJs/ChkDateLessThan.js" type="text/javascript"></script>
<script>

var username="${username}";
console.log(username);
</script>

<style type="text/css">
	table.dataTable, table.dataTable th, table.dataTable td {
		-webkit-box-sizing: content-box;
		-moz-box-sizing: content-box;
		box-sizing: content-box;
		text-align: center;
	}
	.dataTables_scrollHead {
		overflow-y:scroll !important;
		overflow-x:hidden !important;
	}
	.DataTables_sort_wrapper{
		//width : 80px; 
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
	.print_btn input{
	  background-color: #9c27b0;
         border-color: #9c27b0;
	} 
</style>
	
<form:form action="" method="post"  class="form-horizontal"> 
	<div class="animated fadeIn">
		<div class="container" align="center">
	    	<div class="row">
	    		<div class="card">
	          		<div class="card-header"><h5><b>SEARCH CII Unt Dtl</b><br></h5><h6><span style="font-size:12px;color:red;">(..)</span></h6><strong></strong> </div>
	          			<div class="card-body">
            				<div class="col-md-12">
	            				
	              				<div class="col-md-6">
		              				<div class="row form-group">
	                					<div class="col-md-4">
	                  						<label class=" form-control-label"> Unit Name</label>
	                					</div>
	                					<div class="col-md-8">
	                  						<input type="text" id="unit_name" name="unit_name" maxlength="100" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search Unit Name" class="form-control autocomplete" autocomplete="off">
	                					</div>
	              					</div>
	              				</div>
	              				
	              				<div class="col-md-6">
		              				<div class="row form-group">
	                					<div class="col-md-4">
	                  						<label class=" form-control-label"> SUS No</label>
	                					</div>
	                					<div class="col-md-8">
	                  						<input type="text" id="sus_no" name="sus_no" maxlength="100" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search SUS No" class="form-control autocomplete" autocomplete="off">
	                					</div>
	              					</div>
	              				</div>
	              			</div>
	              			
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
	                					<div class="col col-md-4">
	                  						<label for="text-input" class=" form-control-label"> Status </label>
	                					</div>
	                					<div class="col-12 col-md-8">
	                						<select name="status_sus_no" id="status_sus_no" class="form-control-sm form-control">
	                 							<option value="Pending">Pending</option>
							                    <option value="Active">Approved</option>
							                    <option value="Reject">Rejected</option>
								            </select>
	                					</div>
									</div>
								</div>
							</div>
						</div>
						<div class="card-footer" align="center">
							<a href="search_cii_unt_dtl" class="btn btn-success btn-sm" >Clear</a>   
		              		<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" onclick="return Search();" value="Search" /> 
		              		
			            </div> 
			        	
						<div class="container" align="center" id="unitrisindisReport">
							<div class="row">
								<div class="col-md-12" id="table">
									<div class="container" align="center">
									
										<%-- <table id="ReportSearchWePeUpload1" class="table no-margin table-striped  table-hover  table-bordered" >
										
											<thead style="background-color: #9c27b0; color: white;">
												<tr>
													<th style="font-size: 15px;width: 11%;">UNIT NAME</th>
													<th style="font-size: 15px;width: 7%;">SUS NO.</th>
													<th style="font-size: 15px;width: 14%;">ACTION</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="item" items="${list}" varStatus="num" >
													<tr>
														<td style="font-size: 15px;width: 11%;">${item.unit_name} </td>
														<td style="font-size: 15px;width: 7%;">${item.sus_no}</td>
														<td style="font-size: 15px;width: 14%;">${item.status_sus_no}</td>
													</tr>
												</c:forEach>
											</tbody>
										</table> --%>
										
										<table id="ReportSearchWePeUpload1"
				class="table no-margin table-striped  table-hover  table-bordered  report_print">
				<thead>
					<tr>
						<!-- <th class="tableheadSer">Ser No</th> -->
						<th >UNIT NAME</th>
						<th >SUS NO.</th>
						<th >ACTION</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${list}" varStatus="num">
						<tr>
							
							<td >${item.unit_name}</td>
							<td>${item.sus_no}</td>
							<td>${item.status_sus_no}</td>
							
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
</form:form>

	<c:url value="GetSearch_CII" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="unit_name1">
		<input type="hidden" name="status1" id="status1" value="0"/>
		<input type="hidden" name="unit_name1" id="unit_name1"  />
		<input type="hidden" name="unit_sus_no1" id="unit_sus_no1"  />
	</form:form>
	
	<c:url value="ApprovedCIIUrl" var="appUrl" />
	<form:form action="${appUrl}" method="post" id="appForm" name="appForm" modelAttribute="appid">
		<input type="hidden" name="appid" id="appid" value="0"/>
	</form:form>
	
	<c:url value="rejectCIIUrl" var="rejectUrl" />
	<form:form action="${rejectUrl}" method="post" id="rejectForm" name="rejectForm" modelAttribute="rejectid">
		<input type="hidden" name="rejectid" id="rejectid" value="0"/>
	</form:form>
	
	<c:url value="deleteCIIUrl" var="deleteUrl" />
	<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="deleteid">
		<input type="hidden" name="deleteid" id="deleteid" value="0"/>
	</form:form>
	
	<c:url value="updateCIIUrl" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid">
		<input type="hidden" name="updateid" id="updateid" value="0"/>
	</form:form>
	
<script>
	$(document).ready(function() {
		
		 if('${status1}' != ""){
			 $("#ReportSearchWePeUpload1").show();
		 }
		
// 		$("div#unitrisindisReport").hide();
		$("#unit_name").val('${unit_name1}');
    	
    	if('${status_sus_no1}' != ""){
    		if('${status_sus_no1}' == "Inactive"){
    			$("#status_sus_no").val('Active');
    		}else{
    			$("#status_sus_no").val('${status_sus_no1}');
    		}
    	}
		
	});
    
	
	function Search(){
		$("#status1").val($("#status_sus_no").val());
		$("#unit_name1").val($("#unit_name").val());
		$("#sus_no1").val($("#sus_no").val());
	    $("#WaitLoader").show();
	    $("div#unitrisindisReport").show();
		document.getElementById('searchForm').submit();
	}
	
	function Approved(id){
		document.getElementById('appid').value=id;
		document.getElementById('appForm').submit();
	}
	
	function Reject(id){
		document.getElementById('rejectid').value=id;
		document.getElementById('rejectForm').submit();
	}
	
	function Delete1(id){
		document.getElementById('deleteid').value=id;
		document.getElementById('deleteForm').submit();
	}
	
	function Update(id){
		document.getElementById('updateid').value=id;     		   
		document.getElementById('updateForm').submit();
	} 
	
	function getUnitReport() {
        document.getElementById('unit_name1').value = $("#unit_name").val();
        
        if($("#scenario").val() == '4' & $("#status_sus_no").val()== "Active"){
        	document.getElementById('status_sus_no1').value = "Inactive";
        }else{
        	document.getElementById('status_sus_no1').value = $("#status_sus_no").val();
        }
        
    	$("#WaitLoader").show();
        document.getElementById('reloadReport').submit();
  		$("div#unitrisindisReport").show();
  		return false;
   	}
	
	function hideReport(){
		$("div#unitrisindisReport").hide();
	}
	${buttons}
</script>


   
<script>
$("#sus_no").keyup(function(){
	var sus_no = this.value;
	var susNoAuto=$("#sus_no");

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
			      
			      select: function( event, ui ) {
			    	 var unit_name = ui.item.value;
			    
				            $.post("getTargetSUSFromUNITNAME?"+key+"="+value,{target_unit_name:unit_name}, function(data) {
				            }).done(function(data) {
				            	var length = data.length-1;
					        	var enc = data[length].substring(0,16);
					        	$("#sus_no").val(dec(enc,data[0]));
				            }).fail(function(xhr, textStatus, errorThrown) {
				            });
			      } 	     
			}); 			
	});
</script>