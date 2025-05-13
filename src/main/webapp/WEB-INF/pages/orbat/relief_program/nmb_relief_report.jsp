<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables"%>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>

		<div class="animated fadeIn">
			<div class="container" align="center">
	    		<div class="card">
	    			<div class="card-header"><h5><b>NMB RELIEF REPORT </b><br></h5></div>
    				<div class="card-body">
    					<div class="col-md-12">
	          				<div class="col-md-6">
	          					<div class="row form-group">
                					<div class="col col-md-4">
                  						<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> NMB Date (From)</label>
                					</div>
                					<div class="col-12 col-md-8">
                  						<input type="date" id="nmb_date" name="nmb_date" class="form-control">
									</div>
								</div>
	          				</div>
	          				<div class="col-md-6">
	          					<div class="row form-group">
	               					<div class="col col-md-4">
	                 						<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> NMB Date (To)</label>
	               					</div>
	               					<div class="col-12 col-md-8">
	                 						<input type="date" id="nmb_date2" name="nmb_date" class="form-control">
									</div>
 								</div>
	          				</div>
	          			</div>
	          			<div class="col-md-12">
	          				<div class="col-md-6">
	          					<div class="row form-group">
               					<div class="col col-md-4">
                 						<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Status</label>
               					</div>
               					<div class="col-12 col-md-8">
              							<select name="status" id="status" class="form-control-sm form-control">
               								<option value="0">SD Pending</option>
				                    		<option value="1">Unit Pending</option>
				                    		<option value="pending">Imdt Fmn Pending</option>
				                    		<option value="approve">Final Approval Pending</option>
				                    	</select>
	               					</div>
								</div>
	          				</div>
	          			
	          			</div>
           			</div>
					<div class="card-footer" align="center">
						<a href="nmb_reliefReport" class="btn btn-success btn-sm" >Clear</a>   
	              		<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" value="Search"  onclick="return searchReliefData();" >
	              		
					</div> 
	    		</div>
    					<div class="col-md-12" id="report1" style="overflow: auto; display: none;">
	    			    	<table id="SearchReport" class="table no-margin table-striped  table-hover  table-bordered"  style="width: 100%;">
								<thead style="background-color: #9c27b0; color: white;font-size: 12px">
									<tr>
										<th >Ser No</th>
										<th >SUS No</th>
										<th >Unit Name</th>
										<th >From Comd Name</th>
										<th >From Corps Name</th>
										<th >From Div Name</th> 
										<th >From BDE Name</th>
										<th >From Loc</th>
										<th >To Comd Name</th>
										<th >To Corps Name</th>
										<th >To Div Name</th> 
										<th >To BDE Name</th>
										<th >To Loc</th>
										<th>NMB Date</th>
									</tr>
								</thead>
								<tbody style="font-size: 11px">
									<c:forEach var="item" items="${getReliefReportList}" varStatus="num" >
										<tr>
											<td>${num.index +1 }</td>
											<td>${item[0]}</td>
											<td>${item[1]}</td>
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
											<%-- <td>${item[12]}</td> --%>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>	
			</div>
		</div>
	
	
	<c:url value="nmb_reliefReport1" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="code_value1">
		<input type="hidden" name="nmb_date1" id="nmb_date1" value="0"/>
		<input type="hidden" name="nmb_date12" id="nmb_date12" value="0"/>
		<input type="hidden" name="status1" id="status1" value="0"/>
		<input type="hidden" name="auth_letter1" id="auth_letter1" value="0"/>
	</form:form> 
	
	
<script type="text/javascript">

$(document).ready(function() {
	$("#nmb_date").val('${nmb_date1}');
	$("#nmb_date2").val('${nmb_date12}');
	
	if('${status1}' != ""){
		$("#status").val('${status1}');
	}
	
	 if('${getReliefReportList.size()}' != "0" && '${getReliefReportList[0][0]}' != "" ){
		$("#report1").show();
	}else{
		$("#report1").hide();
	}
	
});

	function searchReliefData() {
	 	var period_from = $("#nmb_date").val();
		var period_to = $("#nmb_date2").val();
		var status = $("#status").val(); 
		
		if ($("input#nmb_date").val() == "") {
			alert("Please Select NMB From Date");
			$("#nmb_date").focus();
			return false;
		}
		if ($("input#nmb_date2").val() == "") {
			alert("Please Select NMB To Date");
			$("#nmb_date2").focus();
			return false;
		}
		if ($("#status").val() == "") {
			alert("please Select Status");
			$("#status").focus();
			return false;
		}
		$("#nmb_date1").val($("#nmb_date").val());
		$("#nmb_date12").val($("#nmb_date2").val());
		$("#status1").val($("#status").val());
		$("#auth_letter1").val("");
		$("#WaitLoader").show();
		document.getElementById('searchForm').submit();
	}

</script>