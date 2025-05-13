	
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
	
	<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
	<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
	<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 
	
	<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
	
	<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
	
	<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
	<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
	<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
	
	<link rel="stylesheet" href="js/cue/cueWatermark.css">
	<script src="js/cue/cueWatermark.js"></script>
	<script src="js/cue/printAllPages.js" type="text/javascript"></script>
	
	<div class="animated fadeIn">
			<div class="container" align="center">
				<div class="card">
					<div class="card-header">
						<strong><u>ANIMALS</u></strong>
						<strong><h3>ANIMAL STATUS : SEARCH</h3></strong>
					</div>
					<div class="card-body card-block">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">SUS No </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="sus_no" name="sus_no" class="form-control autocomplete" autocomplete="off" maxlength="8">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Unit Name </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="unit_name" name="unit_name" class="form-control autocomplete" autocomplete="off" value="${unit_name01}" maxlength="100">
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Status </label>
									</div>
									<div class="col-12 col-md-8">
										<select name="status" id="status" class="form-control-sm form-control">
											<option value="0">Pending</option>
											<option value="1">Approved</option>
											<option value="2">Rejected</option>
										</select>
									</div>
								</div>
							</div>
						</div>
						
							<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label">Date (From) </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="date" id="from_date" name="from_date" class="form-control" class="form-control" min='1899-01-01' max='${date}' value="${from_date}">
									</div>
								</div>
							</div>
							
								<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label">Date (To) </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="date" id="to_date" name="to_date" class="form-control" class="form-control" min='1899-01-01' max='${date}' value="${to_date}" onchange="return checkdate(this,from_date)">
									</div>
								</div>
							</div>
					</div>	 
					</div>
					<div class="form-control card-footer" align="center">
						<a href="Search_Animal_Status" type="reset" class="btn btn-success btn-sm"> Clear </a> 
						<i class="fa fa-search"></i><input type="button" id="btdog" class="btn btn-primary btn-sm" value="Search" onclick="Search();">
					</div>
				</div>
			</div>
	</div>
	
	<div class="container" id="divPrint">
		<div align="right"><h6>Total Count : ${list.size()}</h6></div>
		<div class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
			<span id="ip"></span>
			<table id="getSearchReportanimalr" class="table no-margin table-striped  table-hover  table-bordered report_print" style="display: none;">
				<thead style="background-color: #9c27b0; color: white; text-align: center;">
					<tr>
						<th width="10%">Ser No</th>
						<th width="40%">SUS No</th>
						<th width="40%">Unit Name</th>
						<th width="10%" id="thaction">Action</th>
					</tr>
				</thead>
				<tbody >
						<c:if test="${list.size() == 0}" >
							<tr>
								<td align="center" style="color: red;"> Data Not Available </td>
							</tr>
						</c:if>
						<c:forEach var="item" items="${list}" varStatus="num" >
								<tr>
								    <td width="10%" style="text-align: center;">${num.index+1}</td>
									<td width="40%" style="text-align: center;">${item[0]}</td>
									<td width="40%" >${item[1]}</td>
									<td width="10%" id="thaction1" style="text-align: center;">${item[2]}</td>
								</tr>
							</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	
	<c:url value="search_st" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="sus_no01">
		<input type="hidden" name="sus_no01" id="sus_no01"/>
		<input type="hidden" name="unit_name01" id="unit_name01" value="0"/>
		<input type="hidden" name="status1" id="status1" value="0"/>
		<input type="hidden" name="from_date1" id="from_date1" value="0"/>
		<input type="hidden" name="to_date1" id="to_date1" value="0"/>
	</form:form> 
	
	<c:url value="ViewSearchAnimal" var="viewSearchUrlAnimal" />
	<form:form action="${viewSearchUrlAnimal}" method="post" id="viewForm1" name="viewForm1" modelAttribute="sus_no12">
		<input type="hidden" name="sus_no12" id="sus_no12" />
		<input type="hidden" name="unit_name12" id="unit_name12" />
		<input type="hidden" name="status12" id="status12"/>
	</form:form>
	
	<c:url value="ViewSearchAnimalaprv" var="viewSearchUrlAnimalaprvv" />
	<form:form action="${viewSearchUrlAnimalaprvv}" method="post" id="viewForm2" name="viewForm2" modelAttribute="sus_no13">
		<input type="hidden" name="sus_no13" id="sus_no13" />
		<input type="hidden" name="unit_name13" id="unit_name13" />
	</form:form>
	
	<script>
			function Pending(sus_no) {
				$("input#sus_no12").val(sus_no);
				$("input#status12").val(status);
				document.getElementById('unit_name12').value = $("#unit_name").val();
				document.getElementById('viewForm1').submit();
			}
			
			function Approve(sus_no) {				
				$("input#sus_no13").val(sus_no);
				document.getElementById('unit_name13').value = $("#unit_name").val();
				document.getElementById('viewForm2').submit();
			}
		
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
		if('${status1}' != ""){
			$("#sus_no").val('${sus_no01}');
			$("#unit_name").val('${unit_name01}');
			$("#status").val('${status1}');
			$("#from_date").val('${from_date1}');
			$("#to_date").val('${to_date1}');
			$("div#divwatermark").val('').addClass('watermarked');								
			watermarkreport(); 
			$("#getSearchReportanimalr").show();
		}
		
		if('${status1}' == 1){
			if('${list.size()}' != "" && '${list.size()}' != "0"  ){
				 $("#getSearchReportanimalr").show();
			}
		}
		
		
		if('${status1}' == 2)
		{ 
			$("td#thaction1").hide();
			$("th#thaction").hide();
		}
	});
	
			function Search(){
				$("#sus_no01").val($("#sus_no").val());
		        $("#unit_name01").val($("#unit_name").val());
		        $("#status1").val($("#status").val());
		        $("#from_date1").val($("#from_date").val());
		        $("#to_date1").val($("#to_date").val());
				document.getElementById('searchForm').submit();
			}
		
		  	function checkdate(obj,from_date) {
				if(from_date.value !="")
				{
					var id = obj.id;
					var myDate = document.getElementById(id).value;
					var Date1 = from_date.value;
					if ((Date.parse(myDate) < Date.parse(Date1))) {
						alert('To Date should not be less than From Date');
						$("input#to_date").focus();
						obj.value = "";
					}
				}
			}  
	</script>