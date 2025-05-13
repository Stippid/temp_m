<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript"
	src="js/AES_ENC_DEC/lib/excluded/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/miso/miso_js/jquery-1.12.3.js"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">

<form:form id="dailyReceiptIssueReportedit" name="dailyReceiptIssueReporteidt"  
	action="#" method='POST' commandName="edit_avn_daily_Receipt_ReportCMD">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5>EDIT AVN DAILY RECEIPT REPORT</h5>
					<h6 class="enter_by">
						<span style="font-size: 12px; color: red;">(To be entered
							by Miso)</span>
					</h6>
				</div>
				<div class="card-header">
					<strong>Basic Details</strong>
				</div>
				<div class="card-body card-block">

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group" style="padding-top: 10px;">
								<div class="col col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>DRR Ser No </label>
								</div>
								<div class="col-12 col-md-8">
								<input type="hidden" id="id" name="id" placeholder="" class="form-control" value="${edit_avn_daily_Receipt_ReportCMD.id}">
								<input type="text" id="drr_dir_ser_no" name="drr_dir_ser_no" placeholder="" class="form-control autocomplete" autocomplete="off" value="${edit_avn_daily_Receipt_ReportCMD.drr_ser_no}" readonly="readonly" maxlength="50">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">*</strong> SUS No</label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="sus_no" value='${edit_avn_daily_Receipt_ReportCMD.unit_sus_no}' name="sus_no"
										class="form-control autocomplete" maxlength="8"
										autocomplete="off" readonly="readonly">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">*</strong> Unit Name</label>
								</div>
								<div class="col-12 col-md-8">
									<textarea id="unit_name" name="unit_name"
										class="form-control autocomplete" style="font-size: 11px;"
										autocomplete="off" maxlength="100" readonly="readonly"></textarea>
								</div>
							</div>
						</div>
					</div> 
				</div>

				<div class="card-header">
					<strong>Avn Daily Receipt Details</strong>
				</div>
				<div class="card-body card-block">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>Tail No </label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="tail_no" name="tail_no" placeholder=""
										class="form-control autocomplete" autocomplete="off"
										maxlength="10" value='${edit_avn_daily_Receipt_ReportCMD.tail_no}' oninput="handleInput(this)">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>ENG Type</label>
								</div>
								<div class="col-md-8">
								<input type="text" id="eng_type" name="eng_type"
										placeholder="" class="form-control autocomplete"
										autocomplete="off" readonly="readonly">
								</div>
							</div>
						</div>
					</div> 
					 <div class="col-md-12" id="lhser">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>LH-ENG Ser No</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="lh_eng_ser_no" name="lh_eng_ser_no"
										placeholder="" class="form-control autocomplete"
										autocomplete="off" readonly="readonly">
								</div>
							</div>
						</div>
						<div class="col-md-6" >
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>RH-ENG Ser No</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="rh_eng_ser_no" name="rh_eng_ser_no" 
										placeholder="" class="form-control autocomplete" 
										autocomplete="off" readonly="readonly">
								</div>
							</div>
						</div>
						</div>
						<div class="col-md-12" id="lhhrs">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>LH-ENG Hrs</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="lh_eng_hrs" name="lh_eng_hrs"
									 class="form-control" readonly="readonly">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>RH-ENG Hrs</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="rh_eng_hrs" name="rh_eng_hrs"
									 class="form-control" readonly="readonly">
								</div>
							</div>
						</div>
						</div>
							<div class="col-md-12" id="enghrs">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>ENG ser no</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="eng_ser_no" name="eng_ser_no"
									 class="form-control" readonly="readonly">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>ENG Hrs</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="eng_hrs" name="eng_hrs"
									 class="form-control" readonly="readonly">
								</div>
							</div>
						</div>
						</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>Type of aircraft</label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="aircraft_type"
										name="aircraft_type" placeholder=""
										class="form-control autocomplete" autocomplete="off"
										readonly="readonly" maxlength="20">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group" style="padding-top: 10px;">
								<div class="col col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>AVN Agency</label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="avn_agency" name="avn_agency" placeholder=""
										class="form-control autocomplete" autocomplete="off"
										maxlength="10" value='${edit_avn_daily_Receipt_ReportCMD.agency_name}' oninput="handleInput(this)">
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>Auth </label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="authority" name="authority"
										placeholder="" value='${edit_avn_daily_Receipt_ReportCMD.authority}' class="form-control" autocomplete="off"
										maxlength="50">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>Remarks </label>
								</div>
								<div class="col-12 col-md-8">
									<textarea class="form-control" value='${edit_avn_daily_Receipt_ReportCMD.remarks}' name="remarks" id="remarks"
										maxlength="255"></textarea>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="form-control card-footer" align="center">
					<input type="hidden" id="count" name="count"> 
					<a class="btn btn-success" id="addAVNReceiptDetails" onclick='submitData()'>Update</a>
					<a href="search_avn_daily_receipt_report" type="reset"
						class="btn btn-success">  Back  </a>
				</div>
			</div>
		</div>
	</div>
</form:form>

<script>
	function validate() {
		if ($("#drr_dir_ser_no").val() == "") {
			alert("Please Enter the DRR Ser No.");
			return false;
		}
		return true;
	}
</script>
<script>
$(document)
.ready(function() {
			var year = '${year}';
			var today = new Date();
			var date = today.getDate()+'-'+ (today.getMonth()+1)+'-'+today.getFullYear();
			$('#std_nomenclature').val("");
			year = year + "-" + "DRR" + "-"
			$("#drr_dir_ser_nolbl").text(year);
			$("#date_report").val(date);
			 $("#lhser").hide();
		     $("#lhhrs").hide();
		     
		     
		     var unit_sus_no = '${edit_avn_daily_Receipt_ReportCMD.unit_sus_no}';
			   	$.post("getActiveUnitNameFromSusNo?"+key+"="+value, {
			   		sus_no:unit_sus_no
			}).done(function(j) {
				if(j == ""){
		      	 	alert("Please Enter Approved Unit SUS No");
		   	  		document.getElementById("unit_name").value="";
		   	  		susNoAuto.val("");
		   	  		susNoAuto.focus();
		      	}else{
		      		var length = j.length-1;
		        	var enc = j[length].substring(0,16);
		        	$("#unit_name").val(dec(enc,j[0]));
		       		
		       	}
			}).fail(function(xhr, textStatus, errorThrown) {
			});
			   	
			   	var tail_no ='${edit_avn_daily_Receipt_ReportCMD.tail_no}';
				$.post("getAircraftDetails?" + key + "=" + value, {
				    tail_no: tail_no
				}).done(function(j) {
				    // Process the final list data
				    var length = j.length - 1;
				    var enc = j[length][0].substring(0, 16); // Example decryption

				    // Decrypt the fields (example: decrypt the first record)
				    var lhhrs = dec(enc, j[0][2]);
				    var rhhrs = dec(enc, j[0][3]);

				    // Set the values in the fields
				    $("#lh_eng_ser_no").val(dec(enc, j[0][0]));
				    $("#rh_eng_ser_no").val(dec(enc, j[0][1]));
				    $("#lh_eng_hrs").val(lhhrs);
				    $("#rh_eng_hrs").val(rhhrs);
				    $("#aircraft_type").val(dec(enc, j[0][4]));
				    $("#eng_type").val(dec(enc, j[0][5]));
				    $("#eng_ser_no").val(dec(enc, j[0][2]));
				    $("#eng_hrs").val(dec(enc, j[0][3]));

				    // Get the source table information from the last element in the response
				    var sourceTable = j[0][6]; // Assuming the source table is at index 6
					console.log(sourceTable);
				    // If the source table is 'RPAS' or 'CHTL', hide the engine serial and hours fields
				    if (sourceTable === 'RPAS' || sourceTable === 'CHTL') {
				        // Hide the fields using their closest form group
				        $("#lhser").hide();
				        $("#lhhrs").hide();
				        $("#enghrs").show();
				    } else {
				        // If not RPAS or CHTL, make sure the fields are visible
				        $("#lhser").show();
				        $("#lhhrs").show(); 
				        $("#enghrs").hide();
				    }

				    // Call the function to update agency or any other logic based on aircraft type
				    getAgency(dec(enc, j[0][4]));
				}).fail(function(xhr, textStatus, errorThrown) {
				    console.error("Error fetching aircraft details:", errorThrown);
				});
		});

function reloadPage() {
	location.reload(true);
}
</script>
<script>
	jQuery(function() {
		// Source SUS No
		jQuery("#sus_no").keypress(function() {
			var sus_no = this.value;
			var susNoAuto = jQuery("#sus_no");
			susNoAuto.autocomplete({
				source : function(request, response) {
					jQuery.ajax({
						type : 'POST',
						url : "getTargetSUSNoList?" + key + "=" + value,
						data : {
							sus_no : sus_no
						},
						success : function(data) {
							var susval = [];
							var length = data.length - 1;
							if (data.length != 0) {
								var enc = data[length].substring(0, 16);
							}
							for (var i = 0; i < data.length; i++) {
								susval.push(dec(enc, data[i]));
							}
							response(susval);
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
						document.getElementById("sus_no").value = "";
						susNoAuto.val("");
						susNoAuto.focus();
						return false;
					}
				},
				select : function(event, ui) {
					var sus_no = ui.item.value;
					$.post("getActiveUnitNameFromSusNo?" + key + "=" + value, {
						sus_no : sus_no
					}).done(function(j) {
						var length = j.length - 1;
						var enc = j[length].substring(0, 16);
						jQuery("#unit_name").val(dec(enc, j[0]));
					}).fail(function(xhr, textStatus, errorThrown) {
					});
				}
			});
		});
		// End

		// Source Unit Name
		jQuery("#unit_name")
				.keyup(
						function() {
							var unit_name = this.value;
							var susNoAuto = jQuery("#unit_name");
							susNoAuto
									.autocomplete({
										source : function(request, response) {
											jQuery
													.ajax({
														type : 'POST',
														url : "getTargetUnitsNameActiveList?"
																+ key
																+ "="
																+ value,
														data : {
															unit_name : unit_name
														},
														success : function(data) {
															var susval = [];
															var length = data.length - 1;
															if (data.length != 0) {
																var enc = data[length]
																		.substring(
																				0,
																				16);
															}
															for (var i = 0; i < data.length; i++) {
																susval
																		.push(dec(
																				enc,
																				data[i]));

															}
															response(susval);
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
												document
														.getElementById("unit_name").value = "";
												susNoAuto.val("");
												susNoAuto.focus();
												return false;
											}
										},
										select : function(event, ui) {
											var target_unit_name = ui.item.value;
											$
													.post(
															"getTargetSUSFromUNITNAME?"
																	+ key + "="
																	+ value,
															{
																target_unit_name : target_unit_name
															})
													.done(
															function(j) {
																var length = j.length - 1;
																var enc = j[length]
																		.substring(
																				0,
																				16);
																jQuery(
																		"#sus_no")
																		.val(
																				dec(
																						enc,
																						j[0]));
															})
													.fail(
															function(xhr,
																	textStatus,
																	errorThrown) {
															});
										}
									});
						});
	});
</script>
<script>
// Function to block special characters and convert text to uppercase
function handleInput(input) {
    input.value = input.value.toUpperCase();

    const regex = /[^A-Z0-9\s-]/g; 

    input.value = input.value.replace(regex, '');
}
</script>
 <script type="text/javascript">
jQuery(function() {
	// Source TAIL No
	jQuery("#tail_no").keypress(function() {
		var tail_no = this.value;
		var sus_no = jQuery("#sus_no").val();
		var tailNoAuto = jQuery("#tail_no");
		tailNoAuto.autocomplete({
			source : function(request, response) {
				jQuery.ajax({
					type : 'POST',
					url : "gettailNoList?" + key + "=" + value,
					data : {
						tail_no : tail_no,
						sus_no : sus_no
					},
					success : function(data) {
						var tailval = [];
						var length = data.length - 1;
						if (data.length != 0) {
							var enc = data[length].substring(0, 16);
						}
						for (var i = 0; i < data.length; i++) {
							tailval.push(dec(enc, data[i]));
						}
						response(tailval);
					}
				});
			},
			minLength : 1,
			autoFill : true,
			change : function(event, ui) {
				if (ui.item) {
					return true;
				} else {
					alert("Please Enter Right TailNo.");
					document.getElementById("tail_no").value = "";
					tailNoAuto.val("");
					tailNoAuto.focus();
					return false;
				}
			},
			select : function(event, ui) {
				var tail_no = ui.item.value;
				$.post("getAircraftDetails?" + key + "=" + value, {
				    tail_no: tail_no
				}).done(function(j) {
				    // Process the final list data
				    var length = j.length - 1;
				    var enc = j[length][0].substring(0, 16); // Example decryption

				    // Decrypt the fields (example: decrypt the first record)
				    var lhhrs = dec(enc, j[0][2]);
				    var rhhrs = dec(enc, j[0][3]);

				    // Set the values in the fields
				    $("#lh_eng_ser_no").val(dec(enc, j[0][0]));
				    $("#rh_eng_ser_no").val(dec(enc, j[0][1]));
				    $("#lh_eng_hrs").val(lhhrs);
				    $("#rh_eng_hrs").val(rhhrs);
				    $("#aircraft_type").val(dec(enc, j[0][4]));
				    $("#eng_type").val(dec(enc, j[0][5]));
				    $("#eng_ser_no").val(dec(enc, j[0][2]));
				    $("#eng_hrs").val(dec(enc, j[0][3]));

				    // Get the source table information from the last element in the response
				    var sourceTable = j[0][6]; // Assuming the source table is at index 6
					console.log(sourceTable);
				    // If the source table is 'RPAS' or 'CHTL', hide the engine serial and hours fields
				    if (sourceTable === 'RPAS' || sourceTable === 'CHTL') {
				        // Hide the fields using their closest form group
				        $("#lhser").hide();
				        $("#lhhrs").hide();
				        $("#enghrs").show();
				    } else {
				        // If not RPAS or CHTL, make sure the fields are visible
				        $("#lhser").show();
				        $("#lhhrs").show(); 
				        $("#enghrs").hide();
				    }

				    // Call the function to update agency or any other logic based on aircraft type
				    getAgency(dec(enc, j[0][4]));
				}).fail(function(xhr, textStatus, errorThrown) {
				    console.error("Error fetching aircraft details:", errorThrown);
				});
	        }
		});
	});
});
function getAgency(type_of_airtcraft){
	jQuery("#avn_agency").keypress(function() {
		var avn_agency = this.value;
		var type_of_airtcraft1 = type_of_airtcraft;
		console.log(type_of_airtcraft1);
		var avnAgencyAuto = jQuery("#avn_agency");
		avnAgencyAuto.autocomplete({
			source : function(request, response) {
				jQuery.ajax({
					type : 'POST',
					url : "getavnAgencyList?" + key + "=" + value,
					data : {
						type_of_airtcraft:type_of_airtcraft1
					},
					success : function(data) {
						var agencyval = [];
						var length = data.length - 1;
						if (data.length != 0) {
							var enc = data[length].substring(0, 16);
						}
						for (var i = 0; i < data.length; i++) {
							agencyval.push(dec(enc, data[i]));
						}
						response(agencyval);
					}
				});
			},
			minLength : 1,
			autoFill : true,
			change : function(event, ui) {
				if (ui.item) {
					return true;
				} else {
					alert("Please Enter Right AVN Agency.");
					document.getElementById("avn_agency").value = "";
					avnAgencyAuto.val("");
					avnAgencyAuto.focus();
					return false;
				}
			}
		});
	});
}
</script>
<script type="text/javascript">
function submitData() {
    // Collect form data
    var drr_ser_no = $("#drr_dir_ser_no").val();  
    var tail_no = $('#tail_no').val();
    var authority = $('#authority').val();
    var remarks = $('#remarks').val();
    var unit_sus_no = $('#sus_no').val();  
    var agency_name = $('#avn_agency').val();
    var std_nomen = $('#aircraft_type').val();
    var unit_name = $('#unit_name').val();
     
    jQuery.ajax({
        type: 'POST',
        url: "updateAviationData?" + key + "=" + value, 
        data: {
            drr_ser_no: drr_ser_no,
            tail_no: tail_no,
            authority: authority,
            remarks: remarks,
            unit_sus_no: unit_sus_no,
            agency_name: agency_name,
            std_nomen: std_nomen
        },
        success: function(response) {
            if (response === "Data Updated Successfully.") {
                alert("Data Updated successfully!");
            } else {
                alert(response);  
            }
        },
        error: function(xhr, textStatus, errorThrown) {
            console.error("AJAX request failed:", textStatus, errorThrown);  
            alert("An error occurred while updating data.");
        }
    });

    return true;
}

</script>
