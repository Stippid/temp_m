<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<form:form name="ViewrioActionForm" id="ViewrioActionForm"
	action="ViewrioAction" method="POST"
	commandName="tms_viewrio_masterCmd">
	<div class="animated fadeIn" id="printableArea">
		<div class="animated fadeIn">
			<div class="">
				<div class="container" align="center">
					<div class="card">
						<div class="card-header" align="center">
							<h5>DETAILS OF GENERATED RELEASE ISSUE ORDER</h5>
						</div>
						<div class="card-body card-block" id="mainView"
							style="display: block;">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>RIO No</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="hidden" id="id" name="id" class="form-control" value="${tms_viewrio_masterCmd.id}"> 
										<input type="text" id="rio_no" name="rio_no" class="form-control" readonly="readonly" value="${rio_no}"> 
										<input type="hidden" id="command_sus_no1" name="command_sus_no1" value="${tms_viewrio_masterCmd.command_sus_no}" class="form-control"> 
										<input type="hidden" id="depot_sus_no1" name="depot_sus_no1" value="${tms_viewrio_masterCmd.depot_sus_no}" class="form-control">
									</div>
								</div>
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>Comd Name</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="command_sus_no" name="command_sus_no"
											value="${command_sus_no}" class="form-control"
											readonly="readonly">
									</div>
								</div>
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>Unit Name</label>
									</div>
									<div class="col-12 col-md-8">
										<textarea id="unit_name" name="unit_name"
											class="form-control autocomplete" style="font-size: 11px;"
											autocomplete="off" readonly="readonly">${unit_name}</textarea>
									</div>
								</div>

								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>MCT </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="mct" name="mct" class="form-control"
											value="${tms_viewrio_masterCmd.mct}" readonly="readonly">
									</div>
								</div>

								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">In Lieu MCT No</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="inliuemct" name="inliuemct"
											class="form-control"
											value="${tms_viewrio_masterCmd.inliuemct}"
											readonly="readonly">
									</div>
								</div>

								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>Accounting</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="accounting" name="accounting"
											class="form-control"
											value="${tms_viewrio_masterCmd.accounting}"
											readonly="readonly">
									</div>
								</div>

								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>Auth Of AHQ RO No</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="ro_no" name="ro_no"
											class="form-control" value="${tms_viewrio_masterCmd.ro_no}"
											readonly="readonly">
									</div>
								</div>

								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>Issue Stock</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="issue_stock" name="issue_stock"
											class="form-control"
											value="${tms_viewrio_masterCmd.issue_stock}"
											readonly="readonly">
									</div>
								</div>

								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>Valid up To</label> <br> 
											<b>Type of Vehicle : ${tms_viewrio_masterCmd.type_of_vehicle}</b>
											<input type="hidden"  name="type_of_vehicle"  value="${tms_viewrio_masterCmd.type_of_vehicle}">
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="valid_up_to1" name="valid_up_to1"
											class="form-control" readonly="readonly">
									</div>
								</div>

							</div>

							<div class="col-md-6 col-md-offset-1">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>Depot</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="depot_sus_no" name="depot_sus_no"
											class="form-control" value="${depot_sus_no}"
											readonly="readonly" />
									</div>
								</div>

								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Type of Issue</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="hidden" id="type_of_issue" name="type_of_issue"
											class="form-control" value="${tms_viewrio_masterCmd.type_of_issue}" readonly="readonly">
										
										<c:forEach var="item" items="${getMvcrpartCissuetypeList}" varStatus="num" >
											<c:if test="${item[0] == tms_viewrio_masterCmd.type_of_issue}">
												<input type="text" class="form-control" value="${item[1]}" readonly="readonly">
											</c:if>
										</c:forEach>
									</div>
								</div>

								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>SUS No</label>
									</div>
									<div class="col-12 col-md-8" style="width: 100%">
										<input type="hidden" id="sus_no" name="sus_no"
											value="${tms_viewrio_masterCmd.sus_no}" readonly="readonly"
											style="width: 40%"> <label
											style="border: 1px solid #ced4da; border-radius: .25rem; font-size: 1rem; padding: .375rem .75rem; background-color: #e9ecef;">${tms_viewrio_masterCmd.sus_no}
										</label> <label style="color: green; font-size: 12px;"> Last
											Uptd : ${last_update_unit_date} </label>
									</div>
								</div>

								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;">* </strong>Nomenclature</label>
									</div>
									<div class="col-12 col-md-8">
										<textarea id="mct_nomen" name="mct_nomen" class="form-control" autocomplete="off" style="font-size: 11px;" maxlength="300" readonly="readonly">${mct_nomen}</textarea>
									</div>
								</div>

								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">In Lieu Nomenclature</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="inlieu_mct_nomen" name="inlieu_mct_nomen" class="form-control" value="${inlieu_mct_nomen}" readonly="readonly">
									</div>
								</div>

								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>NRS</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="nrs" name="nrs" class="form-control"
											value="${nrs}" readonly="readonly">
									</div>
								</div>

								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>Class</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="class_vehicle" name="class_vehicle"
											class="form-control"
											value="${tms_viewrio_masterCmd.class_vehicle}"
											readonly="readonly">
									</div>
								</div>

								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>Issue Precedence</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="issue_precedence"
											name="issue_precedence" class="form-control"
											value="${tms_viewrio_masterCmd.issue_precedence}"
											readonly="readonly">
									</div>
								</div>
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>Qty</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="hidden" id="quantityhd" name="quantityhd"
											class="form-control"
											value="${tms_viewrio_masterCmd.quantity}"> <input
											type="hidden" id="remaining_quantity"
											name="remaining_quantity" class="form-control"
											value="${tms_viewrio_masterCmd.remaining_quantity}">
										<input type="text" id="quantity" name="quantity"
											onkeypress="return validateNumber(event,this);"
											placeholder="Please enter max ${tms_viewrio_masterCmd.quantity} RO Quantity"
											class="form-control"
											value="${tms_viewrio_masterCmd.quantity}">
									</div>
								</div>
							</div>
						</div>
						<div class="card-body card-block" style="overflow: auto;">
							<div class="col s12" style="">
								<table
									class="table no-margin table-striped  table-hover  table-bordered"
									id="addQuantity">
									<thead>
										<tr>
											<th>Ser No</th>
											<th>BA No</th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
							</div>
						</div>
						<input type="hidden" id="count" name="count">
						<div class="card-footer" align="center" id="btnhide" style="display: block;">
							<jsp:include page="../../../admin/approve_line.jsp" ></jsp:include>
							<input type="Submit" class="btn btn-success btn-sm" value="Approve" onclick="return validate();">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>

<script>
var length1 = "";
$(document).ready(function() {
  
	var s = "${tms_viewrio_masterCmd.quantity}"
	var mct = '${tms_viewrio_masterCmd.mct}';
	var sus_no = '${tms_viewrio_masterCmd.depot_sus_no}';
	var type_of_vehicle = '${tms_viewrio_masterCmd.type_of_vehicle}';
	var rio_id='${rio_id}';
	
	$("table#addQuantity tbody").empty();
	$.post("getFreeStockBANoList?"+key+"="+value, {mct:mct,d_sus_no:sus_no,type_of_vehicle:type_of_vehicle}).done(function(k) {
		$.post("getquantitysumpending?"+key+"="+value, {rio_id:rio_id,mct:mct,sus_no:sus_no}).done(function(l) {
	var p=0;
	 	for (var x=0; x <= k.length; x++) {
	       	if(x>l-1&& p<s){
	       	   	document.getElementById("count").value = p;
	       	if(k[x] != undefined){
	       		p++;
	   			$("table#addQuantity").append('<tr id="'+p+'"><td>'+p+'</td>' +'<td><input type="text" name="ba_no'+p+'" id="ba_no'+x+'" value="'+k[x]+'" class="form-control" readonly="readonly" /></td>' +'</tr>');
	       	}
	        
	       	}
		}

		if(k.length == s){
			length1 = k.length;
		}
		if(k.length < s){
			length1 = k.length;
		}
		if(k.length > s){
			length1 = s;
		}
		
	});
	}).fail(function(xhr, textStatus, errorThrown) {
	});
	
	 
	 $("#quantity").keyup(function(){
		var qty = this.value;
		var quantity = '${tms_viewrio_masterCmd.quantity}';
		if(parseInt(qty) > parseInt(quantity)){
			alert("Please enter Max " + '${tms_viewrio_masterCmd.quantity}' +" RO Quantity");
			$("#quantity").val("");
		}else{
			$("table#addQuantity tbody").empty();
			var mct = '${tms_viewrio_masterCmd.mct}';
			var sus_no = '${tms_viewrio_masterCmd.depot_sus_no}';
			var type_of_vehicle = '${tms_viewrio_masterCmd.type_of_vehicle}';
			 $.post("getFreeStockBANoList?"+key+"="+value, {mct:mct,d_sus_no:sus_no,type_of_vehicle:type_of_vehicle}).done(function(k) {
					$.post("getquantitysumpending?"+key+"="+value, {rio_id:rio_id,mct:mct,sus_no:sus_no}).done(function(l) {
						var p=0;
						 	for (var x=0; x <= k.length; x++) {
						       
						       	if(x>l-1&& p<qty){
						       		document.getElementById("count").value = p;
						       	
						       	if(k[x] != undefined){
						       		p++;
						   			$("table#addQuantity").append('<tr id="'+p+'"><td>'+p+'</td>' +'<td><input type="text" name="ba_no'+p+'" id="ba_no'+x+'" value="'+k[x]+'" class="form-control" readonly="readonly" /></td>' +'</tr>');
						       	}
						        
						       	}
							}
					});
			}).fail(function(xhr, textStatus, errorThrown) {
			});
		}
	});
	var mct = '${tms_viewrio_masterCmd.mct}';	  
	var d = new Date('${tms_viewrio_masterCmd.issue_date}') ;
	var valid_date = d.addMonths(3);
	$("#valid_up_to1").val(valid_date);
});

function validate(){
	var rio_no  = $("#rio_no").val();
	if(rio_no == 0 || rio_no == ""){
		alert("RIO No not Generated Properly. (Incorrect MCT No or Depot SUS No)");
		$("#rio_no").focus();
		return false;
	}
	var quantity  = $("#quantity").val();
	if(quantity == 0 || quantity == ""){
		alert("Please Enter Quantity.");
		$("#quantity").focus();
		return false;
	}
	if(parseInt(quantity) <= parseInt(length1)){
		if(confirm("FreeStock Consist of  " + quantity + " remaining will be confirmed by Depot, Do you want to continue?")){
			return true;	
		}else{
			return false;
		}	
	}else{
		alert("BA No Not Available in Free Stock.");
		return false;
	}
}

Date.prototype.addMonths = function (value) {
    var n = this.getDate();
    this.setDate(1);
    this.setMonth(this.getMonth() + value);
    this.setDate(Math.min(n, this.getDaysInMonth()));
    var datestring =  this.getFullYear() + "-" +  ("0"+(this.getMonth()+1)).slice(-2) + "-" + ("0" + this.getDate()).slice(-2); // + " " + ("0" + this.getHours()).slice(-2); + ":" + ("0" + this.getMinutes()).slice(-2) +":00.0";
    return datestring;
};
Date.isLeapYear = function (year) { 
    return (((year % 4 === 0) && (year % 100 !== 0)) || (year % 400 === 0)); 
};

Date.getDaysInMonth = function (year, month) {
    return [31, (Date.isLeapYear(year) ? 29 : 28), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31][month];
};

Date.prototype.isLeapYear = function () { 
    return Date.isLeapYear(this.getFullYear()); 
};

Date.prototype.getDaysInMonth = function () { 
    return Date.getDaysInMonth(this.getFullYear(), this.getMonth());
};

function printDiv(divName) {
	var printContents =  document.getElementById(divName).innerHTML;
    var originalContents = document.body.innerHTML;
    document.body.innerHTML = printContents;
    window.print();
    document.body.innerHTML = originalContents;
}
</script>
<script>
	function validateNumber(evt) {
		var charCode = (evt.which) ? evt.which : evt.keyCode;
		if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)) {
			return false;
		} else {
			if (charCode == 46) {
			return false;
			}
		}
		return true;
	}
</script>