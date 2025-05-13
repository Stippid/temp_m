<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<link href="js/cue/cueWatermark.css" rel="Stylesheet"></link>

<form:form name="Village" id="Village" action="VillageAction" method="post" class="form-horizontal" commandName="villageCMD"> 
	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5>ADD/UPDATE VILLAGE</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;">(To be entered by MISO)</span></h6></div>
	          			<div class="card-body card-block">
	            			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> COUNTRY NAEM</label>
						                </div>
						                <div class="col-md-8">
						                    <select name="country_id" id="country_id" class="form-control-sm form-control"   >
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${country_id}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
						                </div>
						            </div>
	              				</div>
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> STATE NAME</label>
						                </div>
						                <div class="col-md-8">
						                    <select name="state_id" id="state_id" class="form-control-sm form-control"   >
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${state_id}" varStatus="num">
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
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> DISTRICT NAME</label>
						                </div>
						                <div class="col-md-8">
						                    <select name="district_id" id="district_id" class="form-control-sm form-control"   >
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${district_id}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>  
						                </div>
						            </div>
	              				</div>
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> TEHSIL NAME</label>
						                </div>
						                <div class="col-md-8">
						                    <select name="city_id" id="city_id" class="form-control-sm form-control"   >
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${city_id}" varStatus="num">
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
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> VILLAGE NAME</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="village_name" name="village_name" maxlength="50" class="form-control autocomplete" oninput="this.value = this.value.toUpperCase()" autocomplete="off" > 
						                </div>
						            </div>
	              				</div>
	              				
	              			
	              			</div>
	              		
            			</div>
									
						<div class="card-footer" align="center" class="form-control">
							<a href="Village" class="btn btn-success btn-sm">Clear</a> 
		              		<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick="return Validate();">
		              		<i class="action_icons searchButton"></i><input type="button" class="btn btn-info btn-sm" onclick="Search();" value="Search">
			            </div> 	 		
	        	</div>
			</div>
	</div>

	<div class="container" id="village_search">
		<div class="">
			 <div id="divShow" style="display: block;"></div>
			<div class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
				<span id="ip"></span>
				<table id="village_search" class="table no-margin table-striped  table-hover  table-bordered report_print" width = "100%">
						<thead style="color: white;text-align: center;">
							<tr>
								<th style="font-size: 15px ;width: 10%;">Ser No</th>
								<th style="font-size: 15px">Country Name</th>
								<th style="font-size: 15px">State Name</th>
								<th style="font-size: 15px">District Name</th>
								<th style="font-size: 15px">City Name</th>
								<th style="font-size: 15px">Village Name</th>
								<th style="font-size: 15px ;width: 20%;">Action</th>
							</tr>
						</thead>
						<tbody >
						<c:if test="${list.size()==0}">
								<tr>
									<td style="font-size: 15px; text-align: center; color: red;" colspan="7">Data Not Available</td>
								</tr>
							</c:if>
							<c:forEach var="item" items="${list}" varStatus="num" >
								<tr>
									<td style="font-size: 15px;text-align: center ;width: 10%;">${num.index+1}</td> 
									<td style="font-size: 15px;">${item[0]}</td>
									<td style="font-size: 15px;">${item[1]}</td>
									<td style="font-size: 15px;">${item[2]}</td>
									<td style="font-size: 15px;">${item[3]}</td>
									<td style="font-size: 15px;">${item[4]}</td>
									<td style="font-size: 15px;text-align: center ;width: 20%;">${item[5]} ${item[6]}</td> 
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div> 
		</div>
	</form:form>
	
	<c:url value="getSearch_Village_Master" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="country_id1">
			<input type="hidden" name="country_id1" id="country_id1" />
			<input type="hidden" name="state_id1" id="state_id1" />
			<input type="hidden" name="district_id1" id="district_id1" />
			<input type="hidden" name="city_id1" id="city_id1" />
			<input type="hidden" name="village_name1" id="village_name1" />
	</form:form> 
	
	<c:url value="Edit_Village" var="editUrl"/>
	<form:form action="${editUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="id2">
		  <input type="hidden" name="id2" id="id2">
	</form:form>
	
	<c:url value="delete_Village" var="deleteUrl" />
	<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="id1">
		<input type="hidden" name="id1" id="id1" value="0"/> 
	</form:form>
	
	<Script>
	$(document).ready(function() {
		
		 if('${list.size()}' == ""){
			   $("div#village_search").hide();
			 }
		 
		 if('${state_id1}'== 0)
			{}else
			{
				$("#state_id").val('${state_id1}') ;
			} 
		 
		 if('${district_id1}'== 0)
			{}else
			{
				$("#district_id").val('${district_id1}') ;
			} 
		 
		 if('${city_id1}'== 0)
			{}else
			{
				$("#city_id").val('${city_id1}') ;
			} 
		 
		 if('${country_id1}'== 0)
			{}else
			{
				$("#country_id").val('${country_id1}') ;
			} 
		 
		 
		 if('${village_name1}'== "")
			{}else
			{
				$("#village_name").val('${village_name1}') ;
			} 
	});
	function Search(){
			$("#state_id1").val($('#state_id').val());
			$("#district_id1").val($('#district_id').val());
			$("#city_id1").val($('#city_id').val());
			$("#country_id1").val($('#country_id').val());
			$("#village_name1").val($('#village_name').val());
			document.getElementById('searchForm').submit();
	}
	
	function deleteData(id){
		$("#id1").val(id);
		document.getElementById('deleteForm').submit();
		}
		
	function editData(id){	
		$("#id2").val(id);
		$("#updateForm").submit();
	}
	
	function Validate() {
		if ($("select#country_id").val() == 0) {
			alert("Please Select Country");
			$("select#country_id").focus();
			return false;
		}
		
		if ($("select#state_id").val() == 0) {
			alert("Please Select State");
			$("select#state_id").focus();
			return false;
		}
		
		if ($("select#district_id").val() == 0) {
			alert("Please Select District");
			$("select#district_id").focus();
			return false;
		}
		
		if ($("select#city_id").val() == 0) {
			alert("Please Select Tehsil");
			$("select#city_id").focus();
			return false;
		}
		
		if ($("input#village_name").val() == "") {
			alert("Please Enter Village");
			$("input#village_name").focus();
			return false;
		}
		
		return true;
	}
	</Script>


