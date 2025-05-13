
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>

<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script>
		
		<form:form name="Add_Type_of_Dog" id="Add_Type_of_Dog" action="Add_Type_of_DogAct?${_csrf.parameterName}=${_csrf.token}" method='POST' commandName="Add_Type_of_DogCmd" enctype="multipart/form-data">
			<div class="container" align="center">
				<div class="card">
				    <div class="card-header">
						<strong><h3>TYPE OF DOG MASTER</h3></strong>
						<strong><label for="text-input" id="lbladd" name="lbladd" class=" form-control-label"></label> TYPE OF DOG </strong>
					</div>
					<div class="card-body card-block">
					  <div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"><strong style="color: red;">*</strong> Type of Dog</label>
									</div>
									<div class="col-12 col-md-8">
										<input id="type_dog" name="type_dog" class="form-control" autocomplete="off" maxlength="30" value="${type_dog1}" oninput="this.value = this.value.toUpperCase()">
										<input type="hidden" id="id" name="id"  class="form-control">
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="form-control card-footer" align="center">
						<input type="submit" id="svbtn" class="btn btn-primary btn-sm" value="Save" onclick="return Validate();">
						<a href="Add_Type_of_Dog" type="reset" class="btn btn-success btn-sm"> Clear </a> 
						<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" value="Search" onclick="Search();">
					</div>
				</div>
			</div>
		
		</form:form>
	
	   <div class="container" id="getTypeDogSearch" style=" display: none; "  >
		 <div id="divShow" style="display: block;">
		 </div>		
							
		<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
			<span id="ip"></span>
						 
				<table id="getTypeDogSearch" class="table no-margin table-striped  table-hover  table-bordered report_print">
					<thead style="background-color: #9c27b0; color: white;text-align: center;">
				           <tr>
								<th width="10%">Ser No</th>
								<th width="70%">Type of Dog</th>
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
								<td width="70%" >${item.type_dog}</td>
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
	
		function editData(id,dog){	
			document.getElementById('lbladd').innerHTML = "UPDATE";
			 $("#id").val(id);
			 $("#type_dog").val(dog);
		}
		
		function deleteData(id){	
                    $.post("deleteTypeDog?"+key+"="+value, {
                        id : id
                }, function(data) {
                        //        alert(data);
                        //var json = JSON.parse(data);
                        //...

                }).done(function(data) {
                        alert(data);
                
                        $("#type_dog1").val($("#type_dog").val());
    			        document.getElementById('searchForm').submit();
                        
                }).fail(function(xhr, textStatus, errorThrown) {
                });
		}
				 
			 function Search(){
			        $("#type_dog1").val($("#type_dog").val());
			        document.getElementById('searchForm').submit();
				}
				
			function Validate() {
				if ($("input#type_dog").val() == "") {
					alert("Please Enter type of dog");
					$("input#type_dog").focus();
					return false;
				}
			}
				
			
  </Script>
		
		<c:url value="search_type_dog_master1" var="searchUrl" />
		<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="anml_type3">
			<input type="hidden" name="type_dog1" id="type_dog1" value="0"/>
		</form:form> 
		
<script>

$(document).ready(function() {
	$("#type_dog").val('${type_dog1}');
	if('${list.size()}' == ''){
		$("#getTypeDogSearch").hide();
	}else{
		$("#getTypeDogSearch").show();	
		$("div#divwatermark").val('').addClass('watermarked');								
		watermarkreport();
	}
});
</script>
		