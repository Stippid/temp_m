
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



<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
  <script src="js/Calender/jquery-2.2.3.min.js"></script>
  <script src="js/Calender/jquery-ui.js"></script>
  <script src="js/Calender/datePicketValidation.js"></script>
  
  <!-- new datatables -->
<link rel="stylesheet" href="js/datatable/css/datatables.min.css">
<script type="text/javascript" src="js/datatable/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/datatable/js/jquery.mockjax.js"></script>

   <!-- resizable_col -->
<link rel="stylesheet" href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
	
	<div class="animated fadeIn">
			<div class="container" align="center">
				<div class="card">
					<div class="card-header">
						<strong><h3>SEARCH/APPROVE ANIMAL CENSUS</h3></strong>
					</div>
					<div class="card-body card-block">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;">* </strong>SUS No </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="sus_no" name="sus_no" class="form-control autocomplete" autocomplete="off" maxlength="8" style="display: none">
										<label id="unit_sus_no_hid" style="display: none" ><b> ${roleSusNo} </b></label>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;">* </strong>Unit Name </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="unit_name" name="unit_name" class="form-control autocomplete" autocomplete="off" value="${unit_name01}" maxlength="100" style="display: none">
										<label  id="unit_name_l" style="display: none" ><b>${unit_name}</b></label>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Army No </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="army_no" name="army_no" class="form-control autocomplete" maxlength="11" autocomplete="off"  onkeyup="return specialcharecter(this)" onkeypress="return AvoidSpace(event)"> 
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Status </label>
									</div>
									<div class="col-12 col-md-8">
										<select name="status" id="status" class="form-control-sm form-control">
										  <option value="">-- Select --</option> 
											<option value="0">Pending</option>
											<option value="1">Approved</option>
											<option value="3">Rejected</option>
										</select>
									</div>
								</div>
							</div>
	             					              				
	              			</div>
							 
					</div>
					<div class="form-control card-footer" align="center">
						<a href="Search_Animal_Census" type="reset" class="btn btn-success btn-sm"> Clear </a> 
						<input type="button" id="btdog" class="btn btn-primary btn-sm" value="Search" >
					</div>
				</div>
			</div>
	</div>
	
					
					
<div class="datatablediv">
	<div class="col-md-12" id="getSearch_Letter_a" style="display: block;"> 	 
			<div  class="watermarked" data-watermark="" id="divwatermark"><span id="ip"></span>
		           <table id="getSearchReportanimalr" class="table no-margin table-striped  table-hover  table-bordered report_print" >
		                 <thead style="background-color: #9c27b0; color: white; text-align: center;">
					<tr>
			                        <th style="text-align: center;">Ser No</th>
			                        <th style="text-align: center;"> Unit Sus No </th>
			                        <th style="text-align: center;"> Unit Name </th>
			                          <th style="text-align: center;"> Army No </th>	
			                          <th style="text-align: center;"> Name of Dog </th>
			                          <th style="text-align: center;"> Microchip No </th>		                        
			                          <th style="text-align: center;"> Date of Birth </th>	
			                          <th style="text-align: center;"> Date of TOS </th>  
			                          <th style="text-align: center;"> Reject Remarks </th> 				                                                   	
			                          <th style="text-align: center;"> Action </th>
		                          </tr>  
				</thead>
				</table>
		         </div>				  
		</div> 
		</div>
	
	<c:url value="getSearch_animal_census" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="unit_sus_no1">
		<input type="hidden" name="army_no1" id="army_no1" value="0"/>					
		<input type="hidden" name="status1" id="status1" value="0"/>
		<input type="hidden" name="unit_name1" id="unit_name1"  />
		<input type="hidden" name="unit_sus_no1" id="unit_sus_no1"  />	
	</form:form>

<c:url value="Edit_animal_censusUrl" var="editUrl" />
<form:form action="${editUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2">
	<input type="hidden" name="dog_id2" id="dog_id2">
	<input type="hidden" name="army_no9" id="army_no9" value="0" />
	<input type="hidden" name="status9" id="status9" value="0" />
	<input type="hidden" name="unit_name9" id="unit_name9" />
	<input type="hidden" name="unit_sus_no9" id="unit_sus_no9" />
	<input type="hidden" name="anml_type9" id="anml_type9" />
</form:form>

<c:url value="Delete_census_Url" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id3">
	<input type="hidden" name="id3" id="id3" value="0" />
</form:form>

<c:url value="Get_census_Data" var="approveUrl" />
<form:form action="${approveUrl}" method="post" id="approveForm" name="approveForm" modelAttribute="idj">
<input type="hidden" name="idj" id="idj" value="0" /> 
</form:form>

<c:url value="View_census_Data" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm" name="viewForm" modelAttribute="idv">
<input type="hidden" name="idv" id="idv" value="0" /> 
</form:form>


<%-- <c:url value="ViewSearchAnimal" var="viewSearchUrlAnimal" />
	<form:form action="${viewSearchUrlAnimal}" method="post" id="viewForm1" name="viewForm1" modelAttribute="sus_no12">
		<input type="hidden" name="sus_no12" id="sus_no12" />
		<input type="hidden" name="unit_name12" id="unit_name12" />
		<input type="hidden" name="status12" id="status12"/>
	</form:form>
	
	<c:url value="ViewSearchAnimalaprv" var="viewSearchUrlAnimalaprvv" />
	<form:form action="${viewSearchUrlAnimalaprvv}" method="post" id="viewForm2" name="viewForm2" modelAttribute="sus_no13">
		<input type="hidden" name="sus_no13" id="sus_no13" />
		<input type="hidden" name="unit_name13" id="unit_name13" />
	</form:form> --%>
	
	<script>
		
			$(function() {
		 
		        // Unit SUS No
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
		        			 $.post("getActiveUnitNameFromSusNo?"+key+"="+value, {
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
		
		        // Unit Unit Name
	        	$("#unit_name").keyup(function(){
	        		var unit_name = this.value;
	        	var unit_nameAuto=$("#unit_name");

	        	unit_nameAuto.autocomplete({
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
	                                var dataCountry1 = susval.join("|");
	                                var myResponse = []; 
	        	                    var autoTextVal=unit_nameAuto.val();
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
	        	        	  alert("Please Enter Approved Unit Name.");
	        	        	  document.getElementById("sus_no").value="";
	        	        	  unit_nameAuto.val("");	        	  
	        	        	  unit_nameAuto.focus();
	        	        	  return false;	             
	        	          }   	         
	        	      }, 
	        	      select: function( event, ui ) {
	        	    	  var target_unit_name = ui.item.value;
	        	    		 $.post("getTargetSUSFromUNITNAME?"+key+"="+value, {
	        	    			 target_unit_name:target_unit_name
		                 }, function(j) {
		                        
		                 }).done(function(j) {
		                	 var length = j.length-1;
	                         var enc = j[length].substring(0,16);
	                         $("#sus_no").val(dec(enc,j[0]));   
		                         
		                 }).fail(function(xhr, textStatus, errorThrown) {
		                 });
	        	      } 	     
	        	}); 			
	        });
			});
	</script>
	
	<script>
	
	$(document).ready(function() {
		
		$("#army_no").val('${army_no1}');
		$("#status").val('${status1}');
		$("#unit_name").val('${unit_name1}');
		$("#sus_no").val('${unit_sus_no1}');	
		colAdj("getSearch_animal_census");
		
		
		var r =('${roleAccess}');
		if( r == "Unit"){
			
			 $("#unit_sus_no_hid").show();
			 $("#unit_name_l").show();
			 
		}
		else if (r == "MISO")  {
			
			 $("input#sus_no").show();		 
			 $("input#unit_name").show();
			
		}
		
		if ('${roleSusNo}' != "") {
			$('#roleSusNo').val('${roleSusNo}');
		}
		if ('${list.size()}' == "") {
			$("div#getSearch_animal_census").hide();
		}
		var q1 = '${army_no1}';
		if (q1 != "") {
			$("#army_no").val(q1);
		}
		var q3 = '${unit_name1}';
		if (q3 != "") {
			$("#unit_name").val(q3);
		}
		var q5 = '${unit_sus_no1}';
		if (q5 != "") {
			$("#sus_no").val(q5);
		}
		if ('${status1}' != "") {
			var q4 = '${status1}';
			if (q4 != "") {
				$("#status").val(q4);
			}
		}
		
		
	   var sus_nop = '${roleSusNo}';
	  	$.post("getTargetUnitNameList?"+key+"="+value, {sus_nop:sus_nop}).done(function(data) {
	  		var l=data.length-1;
	  		var enc = data[l].substring(0,16);    	   	 
	  	 	$("#unit_name").text(dec(enc,data[0]));
	  		}).fail(function(xhr, textStatus, errorThrown) {
		  });
		
		
		
		$.ajaxSetup({
			async : false
		});
		
		
		
		
		
	});
	
	
	mockjax1('getSearchReportanimalr');
	table = dataTable('getSearchReportanimalr');
	$('#btdog').on('click', function(){
		table.ajax.reload();
	});

	
	function editData(id) {
		$("#army_no9").val($("#army_no").val());
		$("#status9").val($("#status").val());
		$("#unit_name9").val($("#unit_name").val());
		$("#unit_sus_no9").val($("#sus_no").val());
		$("#anml_type9").val("Army Dog");
		

		document.getElementById('id2').value = id;
		document.getElementById('updateForm').submit();
	}
	
	function delete1Data(id) {

		$("#id3").val(id);
		document.getElementById('deleteForm').submit();
	}
	
	function open_approve(id) {

		$("#idj").val(id);
		document.getElementById('approveForm').submit();

	}
	
	function ViewData(id) {

		$("#idv").val(id);

		/* $("#army_no6").val($("#army_no").val());
		$("#status6").val($("#status").val());
		$("#unit_name6").val($("#unit_name").val());
		$("#unit_sus_no6").val($("#sus_no").val()); */

		document.getElementById('viewForm').submit();

	}
		
		  	  
	</script>
		<script>
		
		$("input#army_no").keyup(function(){
			debugger;
			var army_no = this.value;
			 var susNoAuto=$("#army_no");
				  susNoAuto.autocomplete({
				      source: function( request, response ) {
				        $.ajax({
				        	type: 'POST',
					    	url: "getArmy_noListAnimal?"+key+"="+value,
				        data: {army_no:army_no},
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
				        	  alert("Please Enter Approved Army No");
				        	  document.getElementById("army_no").value="";
				        	  susNoAuto.val("");	        	  
				        	  susNoAuto.focus();
				        	  return false;	             
				          }   	         
				      }, 
				      select: function( event, ui ) {
				    	
				      } 	     
				}); 			
		});
			</script>
			
			<Script>
	
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
		var orderColunm = $(table.column(order[0][0]).header()).attr('id');
		var orderType = order[0][1];
		

		
		var unit_sus_no =	$("#sus_no").val() ;	
		var unit_name = $("#unit_name").val() ;	
		var personnel_no =	$("#army_no").val() ;	
		var status =	$("#status").val() ;	
		
		
		if (tableName=="getSearchReportanimalr") {
			
			$.post("GetSearch_censusCount_animal?"+key+"="+value,{Search:Search,unit_sus_no:unit_sus_no,unit_name:unit_name,
				personnel_no:personnel_no,status:status},function(j) {
				FilteredRecords = j;
				
				
			});
			
			
		
			$.post("GetSearch_censusdata_animal?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType
				,unit_sus_no:unit_sus_no,unit_name:unit_name,
				personnel_no:personnel_no,status:status},function(j) {
					console.log(j[0])
			for (var i = 0; i < j.length; i++) {
				
					var sr = i+1;
		         	var data =[sr,
		         		j[i].unit_sus_no,
		         		j[i].unit_name,
		         		j[i].army_no,
		         		j[i].name,
		         		j[i].microchip_no,
		         		j[i].date_of_birth,
		         		j[i].date_of_tos,
						j[i].reject_remarks,
						j[i].action];
		                 jsondata.push(data);
		         }
					
			});
		}
		

	}	
	
</Script>