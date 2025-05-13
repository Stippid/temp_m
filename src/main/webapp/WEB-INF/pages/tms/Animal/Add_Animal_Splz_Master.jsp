
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>

<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script>
	
	<form:form name="" id="" action="Add_Splz_Act?${_csrf.parameterName}=${_csrf.token}" method='POST' commandName="Add_Splz_Cmd" enctype="multipart/form-data">
	<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<strong><h3>SPECIALISATION MASTER</h3></strong>
					<strong><label for="text-input" id="lbladd" name="lbladd" class=" form-control-label"></label> SPECIALISATION </strong>
				</div>
				<div class="card-body card-block">
						<div class="col-md-12">
						<div class="col-md-8">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"><strong style="color: red;">*</strong> Type of Specialisation
									</label>
								</div>
								<div class="col-12 col-md-8">
									<input id="type_splztn" name="type_splztn" class="form-control" autocomplete ="off" maxlength="50" value="${type_splztn1}" oninput="this.value = this.value.toUpperCase()">
									<input type="hidden" id="id" name="id"  autocomplete ="off" class = "form-control">
								</div>
							</div>
						</div>
					</div>
				</div>
					<div class="form-control card-footer" align="center">
					<input type="submit" id="svbtn" class="btn btn-primary btn-sm" value="Save" onclick="return Validate();">
					<a href="Animal_Specialization_Master" type="reset" class="btn btn-success btn-sm"> Clear </a> 
					<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" value="Search" onclick="Search();">
				</div>
			</div>
		</div>
	</form:form>
	
<div class="container" id="getAnimalSplzSearch2" style="display: none;">
		 <div id="divShow" style="display: block;">
</div>
						
 <div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
		 <span id="ip"></span>
			<table id="getAnimalSplzSearch" class="table no-margin table-striped  table-hover  table-bordered report_print">
				<thead style="background-color: #9c27b0; color: white;text-align: center;">
					<tr>
						<th width="10%">Ser No</th>
						<th width="70%">Type of Specialisation</th>
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
							<td width="70%" >${item.type_splztn}</td>
							<td width="20%" style="text-align: center;">${item.modify_by}</td> 
						</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
</div> 

<Script>
	$(document).ready(function() {	
		document.getElementById("id").value = 0;
		document.getElementById('lbladd').innerHTML = "ADD ";
	});

	function editData(id,spz){	
		document.getElementById('lbladd').innerHTML = "UPDATE";
		 $("#id").val(id);
		 $("#type_splztn").val(spz);
	}
	
	function deleteData(id){	
                $.post("deleteSplz?"+key+"="+value, {
                    id : id
            }, function(data) {
                    //        alert(data);
                    //var json = JSON.parse(data);
                    //...

            }).done(function(data) {
                    alert(data);
                    $("#type_splztn1").val($("#type_splztn").val());
			        document.getElementById('searchForm').submit();
			        $("#getAnimalSplzSearch2").show();
                    
            }).fail(function(xhr, textStatus, errorThrown) {
            });
      }
	
			function Search(){
		        $("#type_splztn1").val($("#type_splztn").val());
		        document.getElementById('searchForm').submit();
		        $("#getAnimalSplzSearch2").show();
			}

function Validate() {
		if ($("input#type_splztn").val() == "") {
			alert("Please Enter type of Specilization ");
			$("input#type_splztn").focus();
			return false;
		}
	}
</Script>

        <c:url value="search_spz_master1" var="searchUrl" />
		<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="anml_type3">
			<input type="hidden" name="type_splztn1" id="type_splztn1" value="0"/>
		</form:form> 
	
<script>

$(document).ready(function() {
	if('${list.size()}' == ''){
		$("#getAnimalSplzSearch2").hide();
	}else{
	$("#type_splztn").val('${type_splztn1}');
				$("#getAnimalSplzSearch2").show();
				$("div#divwatermark").val('').addClass('watermarked');								
				watermarkreport();
	}
		
});
</script>
	

	