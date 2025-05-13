
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>

<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script>
	 
	<form:form name="Add_Animal_Fitness_Status" id="Add_Animal_Fitness_Status" action="Add_Animal_Fitness_StatusAct?${_csrf.parameterName}=${_csrf.token}" method='POST' commandName="Add_Animal_Fitness_StatusCmd" enctype="multipart/form-data">
		<div class="container" align="center">
			<div class="card">
				    <div class="card-header">
						<strong><h3>FITNESS STATUS MASTER</h3></strong>
						<strong><label for="text-input" id="lbladd" name="lbladd" class=" form-control-label"></label> FITNESS STATUS </strong>
					</div>
				<div class="card-body card-block">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-6">
								<label for="text-input" class=" form-control-label">
								<strong style="color: red;">*</strong> Select Animal Type</label>
							</div>
								<div class="form-check-inline form-check">
									<label for="inline-radio1" class="form-check-label ">
									 <input type="radio" id="anml_type1" name="anml_type" value="Army Dog" class="form-check-input">Army Dogs</label>&nbsp;&nbsp; 
										
									<label for="inline-radio2" class="form-check-label ">
										 <input type="radio" id="anml_type2" name="anml_type" value="Army Equines" class="form-check-input">Army Equines</label>
								</div>
						</div>
					</div>
	
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">
									<strong style="color: red;">*</strong> Fitness Status</label>
								</div>
								<div class="col-12 col-md-8">
									<input id="fitness_status" name="fitness_status" class="form-control" autocomplete="off" maxlength="35" value="${fitness_status1}" oninput="this.value = this.value.toUpperCase()"> 
										<input type="hidden" id="id" name="id" class="form-control">
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="form-control card-footer" align="center">
					<input type="submit" id="svbtn" class="btn btn-primary btn-sm" value="Save" onclick="return Validate();"> 
					<a href="Add_Animal_Fitness_Status" type="reset" class="btn btn-success btn-sm"> Clear </a> 
					<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" value="Search" onclick="Search();">
				</div>
			</div>
		</div>
	</form:form>
	

	<div class="container" id="getFitnessStatusSearch2" style=" display: none; "  >
		 <div id="divShow" style="display: block;">
    </div>
	
	<div class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
		<span id="ip"></span>
			<table id="getFitnessStatusSearch" class="table no-margin table-striped  table-hover  table-bordered report_print" >
				<thead style="background-color: #9c27b0; color: white; text-align: center;">
					<tr>
						<th width="10%">Ser No</th>
						<th width="70%">Fitness Status</th>
						<th width="20%">Action</th>
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
								<td width="70%" >${item.fitness_status}</td>
								<td width="20%" style="text-align: center;">${item.modify_by}</td> 
							</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	
<Script>
		$(document).ready(function() {	
		    document.getElementById("anml_type1").checked = true;
			document.getElementById("id").value = 0;
			document.getElementById('lbladd').innerHTML = "ADD ";
		});
	
		function editData(id,fit){	
			    document.getElementById('lbladd').innerHTML = "UPDATE";
				 $("#id").val(id);
				 $("#fitness_status").val(fit);
				 var anml_type = $('input[name=anml_type]:checked').val();
					if (anml_type == "Army Dog") {
						document.getElementById("anml_type1").checked = true;
					}
					else {
						document.getElementById("anml_type2").checked = true;
					}
		}
		
		function deleteData(id){	
			
                    $.post("deleteFitnessStatus?"+key+"="+value, {
                        id : id
                }, function(data) {
                        //        alert(data);
                        //var json = JSON.parse(data);
                        //...

                }).done(function(data) {
                        alert(data);
                
                         var anml_type = $('input[name=anml_type]:checked').val(); 
                        if (anml_type == undefined){
                                    alert("Please Select Animal Type");
                             }
                                  else{
                                          
                                           $("#anml_type3").val(anml_type);
                                           $("#fitness_status1").val($("#fitness_status").val());
                                           document.getElementById('searchForm').submit();
                                  } 
                        
                }).fail(function(xhr, textStatus, errorThrown) {
                });
	}
			 
	 function Search(){
        var anml_type = $('input[name=anml_type]:checked').val(); 
	        if (anml_type == undefined){
	    		alert("Please Select Animal Type");
	    	 }
	  		else{
	  			
	  			 $("#anml_type3").val(anml_type);
	  			 $("#fitness_status1").val($("#fitness_status").val());
	  			 document.getElementById('searchForm').submit();
	  		}
		}
		
	function Validate() {
		if ($("input#fitness_status").val() == "") {
			alert("Please Enter Fitness Status");
			$("input#fitness_status").focus();
			return false;
		}
	}
</Script>
		   <c:url value="search_fitness_master1" var="searchUrl" />
		<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="anml_type3">
			<input type="hidden" name="fitness_status1" id="fitness_status1" value="0"/>
			<input type="hidden" name="anml_type3" id="anml_type3" value="0"/>
		</form:form> 
		
<script>
$(document).ready(function() {
	$("#anml_type").val('${anml_type3}');
	$("#fitness_status").val('${fitness_status1}');
	$("div#divwatermark").val('').addClass('watermarked');								
	watermarkreport(); 
	 if('${anml_type3}' == "Army Dog")
		{
			$("#getFitnessStatusSearch2").show();
			document.getElementById(anml_type1.id).checked = true;
		}
		else if ('${anml_type3}' == "Army Equines") {
			$("#getFitnessStatusSearch2").show();
			document.getElementById(anml_type2.id).checked = true;
		}
		else 
		{
			$("#getFitnessStatusSearch2").hide();
			document.getElementById(anml_type1.id).checked = true;
			document.getElementById(anml_type2.id).checked = false;
		}
	   if('${list.size()}' != "" && '${list.size()}' != "0"  ){
		 $("#getFitnessStatusSearch2").show();
		}
});
</script>
		
		
