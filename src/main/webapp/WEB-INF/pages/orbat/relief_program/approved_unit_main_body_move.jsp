<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

${jsCSS}
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
		/* width : 80px; */
	}
</style>
<div class="animated fadeIn">

    	<div class="col-md-12" align="center">
    		<div class="card">
    			<div class="card-header"><h5><b>SEARCH MAIN BODY MOVEMENT</b><br></h5><h6><span style="font-size:12px;color:red;">(To be Approved by Imdt Higher FMN)</span></h6></div>
    			<div class="card-body">
    				<div class="col-md-12">
          				<div class="col-md-6">
          					<div class="row form-group">
								<div class="col col-md-5">
               						<label class=" form-control-label"><strong style="color: red;">*</strong> SUS No </label>
               					</div>
               					<div class="col-12 col-md-7">
               						<input type="text" id="sus_no" name="sus_no"  maxlength="8" style="font-family: 'FontAwesome',Arial;" value="${sus_no1}" placeholder="Enter SUS No" class="form-control autocomplete" autocomplete="off">
								</div>               					
							</div>
          				</div>
          				<div class="col-md-6">
          					<div class="row form-group">
               					<div class="col col-md-5">
               						<label class=" form-control-label"><strong style="color: red;">*</strong> Unit Name </label>
               					</div>
               					<div class="col-12 col-md-7">
               						<input type="text" id="unit_name" name="unit_name" maxlength="100" style="font-family: 'FontAwesome',Arial;"  value="${unit_name1}" placeholder="Enter Unit Name" class="form-control autocomplete" autocomplete="off">
								</div>
 							</div>
          				</div>
          			</div>
          			<div class="col-md-12">
          				<div class="col-md-6">
          					<div class="row form-group">
               					<div class="col col-md-5">
               						<label for="text-input" class=" form-control-label">Status </label>
               					</div>
               					<div class="col-12 col-md-7">
           							<select name="status" id="status" class="form-control-sm form-control">
               							<option value="0">Pending</option>
				                    	<option value="1">Approved</option>
				                    </select>
               					</div>
							</div>
          				</div>	          				
          			</div>
   				</div>
				<div class="card-footer" align="center">
					<a href="approved_unit_main_body_move" class="btn btn-primary btn-sm">Reset</a>
			  		<input type="submit" class="btn btn-primary btn-sm" value="Search" onclick="return searchMainBody();">
                </div> 
    		</div>
			<div class="card" id="report2" style="overflow: auto; display: none;">
   				<div class="col-md-12">
					<table id="SearchReport1" class="table no-margin table-striped  table-hover  table-bordered" style="width: 100%;">
						<thead style="background-color: #9c27b0; color: white;">
							<tr style="font-size: 14px;text-align: center !important;">
							   <th >SUS No</th>
								<th >Unit Name</th>
								<th >Imdt Higher FMN</th>
								<!-- <th >Arm Name</th> -->
								<th >Mode of Tpt</th> 
								<th >NMB Date</th>
								<th >Indn/De-Indn pd</th>
								<th >To Location</th>
								<th >NRS</th>
								<!-- <th >Type of stn</th>
								<th >Type of terrain</th> -->
								<th >Move of Adv Party Date</th>
								<!-- <th >Re-placed by SUS No</th> -->
								<th >To Unit</th>
								<th>Dep Date</th>
								<th>Arr Date</th>
								<th >ACTION</th>
							</tr>
					  	</thead>
					 	<tbody>
					 		<c:if test="${getsearchMainBodyReportList.size() == 0}" >
								<tr>
									<td colspan="15" align="center" style="color: red;"> Data Not Available </td>
								</tr>
							</c:if>
							<c:forEach var="item" items="${getsearchMainBodyReportList}" varStatus="num" > 
								<tr  style="font-size: 13px;">
									<%-- <td>${item[0]}</td> --%>
									<td>${item[2]}</td>
									<td>${item[3]}</td>
									<td>${item[4]}</td>
									<td>${item[5]}</td>
									<td>${item[6]}</td>
									<td>${item[7]}</td>
									<td>${item[8]}</td>
									<td>${item[9]}</td>
									<td>${item[10]}</td>
									<td>${item[11]}</td>
									<td>${item[12]}</td>
									<td>${item[13]}</td>
									<%-- <td>${item[14]}</td> --%>
									<%-- <td>${item[15]}</td> --%>
									<td>
										<c:if test="${roleType == 'ALL' || roleType == 'APP'}"> 
											<c:if test="${item[14] == 0}">
												<i class="action_icons action_approve" onclick="approvedReliefData('${item[0]}','${item[2]}')" title="Approve Data"></i>
											</c:if>
										</c:if>
										<c:if test="${item[14] == 1}">
							    		   <i>Approved</i>
							    		</c:if>
									</td>
								</tr>
							</c:forEach>
					 	</tbody>
					</table> 
				</div>
			</div>
		</div>
	</div>


<c:url value="approved_unit_main_body_move1" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="sus_no1">
	<input type="hidden" name="sus_no1" id="sus_no1" value="0"/>
	<input type="hidden" name="unit_name1" id="unit_name1" value="0"/>
	<input type="hidden" name="status1" id="status1" value="0"/>
</form:form> 


<c:url value="approvedUnitReliefDetails" var="appUrl" />
<form:form action="${appUrl}" method="post" id="appForm" name="appForm" modelAttribute="sus_no2">
	<input type="hidden" name="sus_no2" id="sus_no2" value="0"/>
	<input type="hidden" name="status2" id="status2" value="0"/>
	<input type="hidden" name="id2" id="id2" value="0"/>
</form:form> 

<script type="text/javascript">
jQuery(function() { 
	
	if('${roleAccess}' == 'Unit'){
		jQuery("#sus_no").attr('readonly','readonly');
		jQuery("#unit_name").attr('readonly','readonly');
	}
	
	if('${getsearchMainBodyReportList.size()}' > 0){
		jQuery("#report2").show();
		jQuery("#status").val('${status1}');
		jQuery("#sus_no").val('${sus_no1}');
		jQuery("#unit_name").val('${unit_name1}');
	}else{
		jQuery("#report2").show();
	}
	
	 	jQuery("#unit_name").keypress(function(){
			var unit_name = this.value;
			
				 var susNoAuto=jQuery("#unit_name");
				  susNoAuto.autocomplete({
				      source: function( request, response ) {
				        $.ajax({
				        type: 'POST',
				        url: "getUnitNameActiveBySDAndUnitPending?"+key+"="+value,
				        data: {unit_name:unit_name},
				          success: function( data ) {
				        	 var susval = [];
				        	  var length = data.length-1;
				        	var enc = "";
				        	if(data.length != 0){
				        		enc = data[length].substring(0,16);
				        	}
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
				        	  alert("Please Enter Approved Unit Name");
				        	  document.getElementById("sus_no").value="";
				        	  susNoAuto.val("");	        	  
				        	  susNoAuto.focus();
				        	  return false;	             
				          }   	         
				      }, 
				      select: function( event, ui ) {
				      	jQuery(this).val(ui.item.value);
				      	$.post("getActiveSusNoFromUnitName?"+key+"="+value,{unit_name:ui.item.value}, function(j) {
				    		if(j.length != 0){
				    			var length = j.length-1;
								var enc = j[length][0].substring(0,16);
		 	        			$("#sus_no").val(dec(enc,j[0][0]));
				    		}
				      	});
				      } 	     
				});
			
		}); 
 	  
 	   // Source Sus No auto
 		jQuery("input#sus_no").keyup(function(){
 			var sus_no = this.value;
 			var unitNameAuto=jQuery("#sus_no");
 			unitNameAuto.autocomplete({
 			      source: function( request, response ) {
 			        $.ajax({
 			        type: 'POST',
 			        url: "getSusNoActiveBySDAndUnitPending?"+key+"="+value,
 			        data: {sus_no:sus_no},
 			          success: function( data ) {
 			        	  var susval = [];
 			        	  var length = data.length-1;
 			        	  var enc = "";
					        	if(data.length != 0){
					        		enc = data[length].substring(0,16);
					        	}
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
 			        	  alert("Please Enter Approved SUS NO");
 			        	  jQuery("#unit_name").val("");
 			        	  unitNameAuto.val("");	        	  
 			        	  unitNameAuto.focus();
 			        	  return false;	             
 			          }   	         
 			      }, 
 			      select: function( event, ui ) {
 			      	var sus_no = ui.item.value;
 			      	$.post("getActiveUnitNameFromSusNo?"+key+"="+value,{sus_no:sus_no}, function(j) {
 			       		var length = j.length-1;
 						var enc = j[length].substring(0,16);
 				   		jQuery("#unit_name").val(dec(enc,j[0]));
 				   	});
 			     }
 			});
 		});
});	 

	function searchMainBody()
	{
		var status = jQuery("#status").val();
		var sus_no = jQuery("#sus_no").val();
		var unit_name = jQuery("#unit_name").val();
        
		if(sus_no == ""){
			alert("Please Enter SUS NO");
			jQuery("#sus_no").focus();
			return false;
		}
		if('${roleAccess}' == 'Unit'){
			jQuery("#sus_no1").val('${sus_no1}');
			jQuery("#unit_name1").val('${unit_name1}');
		}else{
			jQuery("#sus_no1").val(sus_no);
			jQuery("#unit_name1").val(unit_name);	
		}
		jQuery("#status1").val(status);
		jQuery("#WaitLoader").show();
        document.getElementById('searchForm').submit();
	}
	
	function approvedReliefData(id,sus_no) {
		var status = jQuery("#status").val();
		if('${roleAccess}' == 'Unit'){
			jQuery("#sus_no2").val('${sus_no1}');
		}else{
			jQuery("#sus_no2").val(sus_no);
		}
		jQuery("#status2").val(status);
		jQuery("#id2").val(id);
        document.getElementById('appForm').submit();
    }
</script>