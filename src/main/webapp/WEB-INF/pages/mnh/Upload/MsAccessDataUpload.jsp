<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="Stylesheet" href="js/Calender/jquery-ui.css">
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script type="text/javascript" src="js/common/commonmethod.js"></script>

<form:form action="MsAccessDataUploadAction?${_csrf.parameterName}=${_csrf.token}" id="MsAccessDataUpload" method="post" class="form-horizontal" enctype="multipart/form-data">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header ">
				<h5>DATA UPLOAD FROM MS-ACCESS TO AROGYA</h5>
				<h6>
					<span style="font-size: 12px; color: red">(To be uploaded by Medical Unit/MISO)</span>
				</h6>
			</div>
			<div class="card-body card-block">
				<div class="row">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Hospital Name</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="unit_name1" name="unit_name1" class="form-control-sm form-control" placeholder="Search..." autocomplete="off" maxlength="100" title="Type Unit Name or Part of Unit Name to Search">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong>SUS No</label>
								</div>
								<div class="col-md-8">
									<input type="hidden" id=id_hid name="id_hid"> 
									<input type="text" id="sus_no1" name="sus_no1" class="form-control-sm form-control" placeholder="Search..." autocomplete="off" maxlength="8" title="Type SUS No or Part of SUS No to Search" />
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">Click Choose File</label>
								</div>
								<div class="col-md-8">
									<input type="file" id="file_browser" name="file_browser" class="form-control-sm form-control"/>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label" style="padding-left: 13px;">From Date</label>
								</div>
								<div class="col-md-8">
									<input type="date" id="frm_dt" name="frm_dt" class="form-control-sm form-control" onchange="CheckDate(this.value);" autocomplete="off" min="1899-01-01" max="${date}">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">To Date</label>
								</div>
								<div class="col-md-8">
									<input type="date" id="to_dt" name="to_dt" class="form-control-sm form-control" onchange="CheckDate(this.value);" autocomplete="off" min="1899-01-01" max="${date}">
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="card-footer" align="center">
				<input type="reset" class="btn btn-primary btn-sm" value="Clear" /> 
				<input type="submit" class="btn btn-success btn-sm" id="Saveid" value="Upload" onclick="validate1();">
			</div>
			<div class="row" id="divPrint" style="display: none;">
				<div class="col-md-12">
					<div class="container" align="center">
						<table id="SearchReport" class="table no-margin table-striped  table-hover  table-bordered" width="100%">
							<thead style="background-color: #9c27b0; color: white;">
								<tr>
									<th style="font-size: 15px">Sr. No.</th>
									<th style="font-size: 15px">MEDICAL UNIT</th>
									<th style="font-size: 15px">UNIT NAME</th>
									<th style="font-size: 15px">MONTH/YEAR</th>
									<th style="font-size: 15px">COUNT</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>


<script type="text/javascript" src="js/Calender/jquery-ui.js"></script>
<script type="text/javascript" src="js/common/commonmethod.js"></script>
<script type="text/javascript" src="js/cue/cueWatermark.js"></script>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<!-- for Functions -->
<script>
	function btn_clc() {
		location.reload(true);
	}

	function validate1() {
		
		if ($("#sus_no1").val() == "") {
			alert("Please Enter the SUS No");
			$("#sus_no1").focus();
			return false;
		}else if ($("#unit_name1").val() == "") {
			alert("Please Enter the Unit Name");
			$("#unit_name1").focus();
			return false;
		}else if ($("#file_browser").val() == "") {
			alert("Please Upload the File.");
			$("#store_type").focus();
			return false;
		}else if ($("#frm_dt").val() == "") {
			alert("Please select From Date.");
			$("#frm_dt").focus();
			return false;
		}else if ($("#to_dt").val() == "") {
			alert("Please select To Date");
			$("#to_dt").focus();
			return false;
		}
		return true;
		
		
		/*else {
			var q1 = $("#sus_no1").val();
			var q3 = $("#frm_dt").val();
			var q4 = $("#to_dt").val();
			var filename = $("#file_browser").val();
			if (q4 != "") {
				$("#sus_no1").attr('readonly', false);
				$("#unit_name1").attr('readonly', false);
				$("#exportAccessData").submit();
			} else {
				$.post("checkDetailsOfArogya?" + key + "=" + value, { d1 : q1, d3 : q3, d4 : q4 }, function(j) {
					var enc;
					var a;
					if (j != "") {
						enc = j[j.length - 1].substring(0, 16);
						a = dec(enc, j[0]);
					}
					alert("Dup Check " + a);
					if (q1 == a) {
						alert("Data already exists");
					} else {
						$("#sus_no1").attr('readonly', false);
						$("#unit_name1").attr('readonly', false);
						defaultval();
						$("#exportAccessData").submit();
					}
				});
			}
		}*/
	}
</script>

<script>
	var key = "${_csrf.parameterName}";
	var value = "${_csrf.token}";
	jQuery(function() {
		jQuery("#sus_no1").keypress(function() {
			var sus_no = this.value;
			var susNoAuto = jQuery("#sus_no1");
			susNoAuto.autocomplete({
				source : function(request, response) {
					jQuery.ajax({
						type : 'POST',
						url : "getTargetSUSNoList?"+ key+ "="+ value,
						data : {sus_no : sus_no},
						success : function(data) {
							var susval = [];
							var length = data.length - 1;
							if (data.length != 0) {
								var enc = data[length].substring(0,16);
							}
							for (var i = 0; i < data.length; i++) {
								susval.push(dec(enc,data[i]));
							}
							var dataCountry1 = susval.join("|");
							var myResponse = [];
							var autoTextVal = susNoAuto.val();
							jQuery.each(dataCountry1.toString().split("|"),function(i,e) {
								var newE = e.substring(0,autoTextVal.length);
								if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
									myResponse.push(e);
								}
							});
							response(myResponse);
						}
					});
				},
				minLength : 1,
				autoFill : true,
				change : function(event, ui) {
					if (ui.item) {
							return true;
						} else {
							alert("Please Enter Approved SUS No.");
							document.getElementById("sus_no1").value = "";
							susNoAuto.val("");
							susNoAuto.focus();
							return false;
						}
					},
				select : function(event, ui) {
					var unit_sus_no = ui.item.value;
					$.post("getActiveUnitNameFromSusNo?"+ key + "="+ value,{sus_no : unit_sus_no}).done(function(j) {
						if (j == "") {
							alert("Please Enter Approved Unit SUS No.");
							document.getElementById("unit_name1").value = "";
							susNoAuto.val("");
							susNoAuto.focus();
						} else {
							var length = j.length - 1;
							var enc = j[length].substring(0,16);
							$("#unit_name1").val(dec(enc,j[0]));
						}
					}).fail(function(xhr,textStatus,errorThrown) {});
				}
			});
		});
		// End

		// Source Unit Name

		
	jQuery("#unit_name1").keypress(function() {
		var unit_name = this.value;
		var susNoAuto = jQuery("#unit_name1");
		susNoAuto.autocomplete({
			source : function(request, response) {
				jQuery.ajax({
					type : 'POST',
					url : "getTargetUnitsNameActiveList?"+ key+ "="+ value,
					data : {unit_name : unit_name},
					success : function(data) {
						var susval = [];
						var length = data.length - 1;
						if (data.length != 0) {
							var enc = data[length].substring(0,16);
						}
						for (var i = 0; i < data.length; i++) {
							susval.push(dec(enc,data[i]));
						}
						var dataCountry1 = susval.join("|");
						var myResponse = [];
						var autoTextVal = susNoAuto.val();
						jQuery.each(dataCountry1.toString().split("|"),
						function(i,e) {
							var newE = e.substring(0,autoTextVal.length);
							if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
								myResponse.push(e);
							}
						});
						response(myResponse);
					}
				});
			},
			minLength : 1,
			autoFill : true,
			change : function(event, ui) {
				if (ui.item) {
					return true;
				} else {
					alert("Please Enter Approved Unit Name.");
					document.getElementById("unit_name1").value = "";
					susNoAuto.val("");
					susNoAuto.focus();
					return false;
				}
			},
			select : function(event, ui) {
				var target_unit_name = ui.item.value;
				$.post("getTargetSUSFromUNITNAME?"+ key + "="+ value,{
					target_unit_name : target_unit_name
				}).done(function(j) {
					var length = j.length - 1;
					var enc = j[length].substring(0,16);
					jQuery("#sus_no1").val(dec(enc,j[0]));
				}).fail(function(xhr,textStatus,errorThrown) {});
			}
		});
	});
});
</script>
<!-- for On Load Methods -->
<script>
	$(document).ready(function() {
		$("#sus_no1").val('${sus_no}');
		$("#unit_name1").val('${unit_name}');
		if ('${sus_no}' != "" || '${unit_name}' != "") {
			$("#divPrint").hide();
		}
		$("#file_browser").change(function(){
			checkFileExt(this);
		});
	});
	function CheckDate(obj) {
		var k = new Date();
		var c_d = k.getFullYear() + "-" + ("0" + (k.getMonth() + 1)).slice(-2) + "-" + ("0" + k.getDate()).slice(-2);
		if ($("#frm_dt").val() > c_d) {
			$("#frm_dt").focus();
			alert("Can't select the Future Date");
			$("#frm_dt").val("");
			$("#to_dt").val("");
			return false;
		}
		if ($("#to_dt").val() > c_d) {
			$("#to_dt").focus();
			alert("Can't select the Future Date");
			$("#frm_dt").val("");
			$("#to_dt").val("");
			return false;
		}
	}
	
	function checkFileExtImage(file) {
	    var ext = file.value.match(/\.([^\.]+)$/)[1];
	  switch (ext) {
	            case 'mdb':
	            case 'accdb':
	            case 'mde':
	            
	           
	      break;
	            default:
	              alert('Only *.mdb, *.accdb and *.mde  file extensions allowed');
	             file.value = "";
	  }
	}
</script>