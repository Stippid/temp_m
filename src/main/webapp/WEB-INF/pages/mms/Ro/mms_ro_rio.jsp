<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script>

<style>
.selected-unit-container {
	background-color: mistyrose; 
	padding: 5px; 
	display: inline-block; 
	margin: 5px; 
}
  ::placeholder {
            color: #bebebe !important;
        }

.selected-unit-container:hover {
	background-color: #ffe4e1;
}
table thead {
    font-size: 12px;
    background-color: mistyrose;
    color: black;
}

.unit-name {
	display: inline-block;
}

.cancel-sign {
	color: red;
	margin-left: 6px; 
	font-weight: bold; 
	cursor: pointer;
}

.checkboxes {
    display: none;
    border: 1px #dadada solid;
    width: 250px;
    display: none;
    border: 0.5px #dadada  solid;
    position: absolute;
    background-color: #FFFFFF;
    width: 100%;
    z-index: 1;
}

.checkboxes label {
	margin-left: 10px;
	text-align: left;
	display: block;
}

.checkboxes label:hover {
	background-color: #1e90ff;
}

#quali_sub_list:empty {
	display: none;
}
#wpncat_sub_list:empty {
	display: none;
}

#prf_sub_list:empty {
	display: none;
}

#unit_sub_list:empty {
	display: none;
}
 .row-spacing {
        margin-bottom: 50px; 
    }
    
    .caret-button {
    display: inline-block;
    width: 20px; 
    height: 20px;
    text-align: center;
    line-height: 20px; 
   background-color: #64d27d; 
    color: #fff;
    border-radius: 50%; 
    cursor: pointer;
}

.caret-button:hover {
    background-color: #006400; 
}
 .overSelect {
    position: absolute;
    left: 0;
    right: 0;
    top: 0;
    bottom: 0;
}

.selectBox {
    position: relative;
}

.selectBox select {
    width: 100%;
    font-weight: bold;
}

.checkboxes {
    display: none;
    border: 1px #dadada solid;
    width: 250px;
    display: none;
    border: 0.5px #000000 solid;
    position: absolute;
    background-color: #FFFFFF;
    width: 100%;
    z-index: 1;
}

.checkboxes label {
    margin-left: 10px;
    text-align: left;
    display: block;
}

.checkboxes label:hover {
    background-color: #1e90ff;
}

    

</style>

<div class="animated fadeIn" id="printableArea">
	<div class="">
		<div class="col-md-12" align="center">
			<div class="card">
				<div class="card-header" align="center">
					<h5>MMS RO GENERATION</h5>
				</div>
				<div class="card-body">
				
				<div class="row">
				
						
						
							<div class="col-md-3">
					<div class="form-group row">
								<label for="arms" class="col-sm-4 col-form-label"><strong
									style="color: red;">*</strong>Wpn/ cont store Cat</label> <input type="hidden"
									id="CheckValarm" name="CheckValarm">
								<div class="col-sm-8">
									<div class="selectBox" onclick="showCheckboxeswpncat()">
										<select name="wpncat" id="wpncat" class=" form-control">
											<option>Select Wpn/ cont store cat</option>
										</select>
										<div class="overSelect"></div>
									</div>
									<div id="checkboxeswpncat" class="checkboxes"
										style="max-height: 150px; overflow: auto;">
										<div>
											<input type="text" id="wpncat_search"
												class="form-control autocomplete" autocomplete="off"
												placeholder="Search wpncat">
										</div>
										<div>
											<c:forEach var="item" items="${getItemGroupList}" varStatus="num">
												<label for="one" class="wpncat_list"> <input
													type="checkbox" name="multiwpncat" value="${item[0]}" />
													${item[1]}
												</label>
											</c:forEach>
										</div>
									</div>								
						         	<br>								
									<div id="wpncat_sub_list"
											style="max-height: 100px; overflow: auto; width: 100%; border: 2px solid;">
										</div>									
							
								
</div>
								</div>
							</div>
								
				<div class="col-md-3">
					<div class="form-group row">
								<label for="arms" class="col-sm-4 col-form-label"><strong
									style="color: red;">*</strong>PRF</label> <input type="hidden"
									id="CheckValarm" name="CheckValarm">
								<div class="col-sm-8">
									<div class="selectBox" onclick="showCheckboxesprf()">
										<select name="prf" id="prf" class=" form-control">
											<option>Select PRF Groups</option>
										</select>
										<div class="overSelect"></div>
									</div>
									<div id="checkboxesprf" class="checkboxes"
										style="max-height: 150px; overflow: auto;">
										<div>
											<input type="text" id="prf_search"
												class="form-control autocomplete" autocomplete="off"
												placeholder="Search PRF Group">
										</div>
										<div>
											<c:forEach var="item" items="${m_11}" varStatus="num">
												<label for="one" class="prf_list"> <input
													type="checkbox" name="multiprf" value="${item[0]}" />
													${item[1]}
												</label>
											</c:forEach>
										</div>
									</div>
									<br>								
									<div id="prf_sub_list"
											style="max-height: 100px; overflow: auto; width: 100%; border: 2px solid;">
										</div>	
</div>
								</div>
							</div>
						
						<div class="col-md-4">
                    <div class="col-md-12" style='background-color: mistyrose; padding: 7px;'>
    <div class="col-md-5" align="center">
        <b>DEPO Detail<span id="tregn" style='font-size: 14px;'></span></b>
    </div>
    <div class="col-md-7" align="right">
        <input type="text" id="searchInput" placeholder="Search Depo">
    </div>
</div>
                 <div class="col-md-12" style="height: 150px; overflow: auto; width: 100%; border: 1px solid #000; text-align: left;">
                      <div >
                      <table id="depo_tbl" class="table no-margin table-striped  table-hover  table-bordered">
                  <tbody>
                
                </tbody>
            </table>
        </div>
    </div>
</div>
				<div class="col-md-2">		
				<div class="form-group row">
								<label class="col-sm-4 col-form-label"><strong style="color: red;">* </strong> Type of RO  </label>
								<div class="col-sm-8">
							<select name="type_of_ro" id="type_of_ro" class="form-control-sm form-control">
											<option value="0">--Select--</option>
											<option value="1" name="S">RO</option>
											<option value="3" name="L">Loan RO</option>
											<option value="4" name="N">NRU RO</option>
											<option value="5" name="P">Conditional RO</option>
										</select>
								</div>
							</div>	
							
								<div class="form-group row">
								<label class="col-sm-4 col-form-label"><strong style="color: red;">* </strong>RO No</label>
								<div class="col-md-8">
										<input type="text" id="ro_no" name="ro_no" class="form-control form-control-sm autocomplete" value="" autocomplete="off" maxlength="50">
									</div>
							</div>
									
				
				</div>
					
				</div>
<div class="row row-spacing"></div>
<div class="row"> 
	              <div class="col-md-3">
							<div class="form-group row">
								<label class="col-sm-4 col-form-label"><strong style="color: red;"> </strong> Arm/Service </label>
								<div class="col-sm-8">
								<select  id="arm"  name="arm" class="form-control" >
                                     <option value="">--Select--</option>
                 						<c:forEach var="item" items="${getArmNameList}" varStatus="num" >
           									<option value="${item[0]}" > ${item[1]}</option>
           								</c:forEach>
                 						
                 						</select>
								</div>
							</div>
						</div>
						
						
							<div class="col-md-3">
							<div class="form-group row">
								<label class="col-sm-4 col-form-label">Force Type <strong style="color: red;"></strong></label>
								<div class="col-sm-8">
								<select id="force_type" name="force_type" class="form-control">
               						<option value="">--Select--</option>
                 						<c:forEach var="item" items="${getForceTypeList}" varStatus="num" >
           									<option value="${item[1]}" > ${item[1]}</option>
           								</c:forEach>
               						</select>
								</div>
							</div>
						</div>
						
				
								<div class="col-md-3">
								<div class="form-group row">
								<label for="arms" class="col-sm-4 col-form-label"><strong
									style="color: red;"></strong>Comd</label> <input type="hidden"
									id="CheckValarm" name="CheckValarm">
								<div class="col-sm-8">
									<div class="selectBox" onclick="showCheckboxescomd()">
										<select name="comd" id="comd" class=" form-control">
											<option>Select Comd</option>
										</select>
										<div class="overSelect"></div>
									</div>
									<div id="checkboxescomd" class="checkboxes"
										style="max-height: 150px; overflow: auto;">
										<div>
											<input type="text" id="comd_search"
												class="form-control autocomplete" autocomplete="off"
												placeholder="Search Comd">
										</div>
										<div id="comd_div">
											<c:forEach var="item" items="${getCommandList}" varStatus="num">
												<label for="one" class="comd_list"> <input
													type="checkbox" name="multicomd" value="${item[0]}" />
													${item[1]}
												</label>
											</c:forEach>
										</div>
									</div>
</div>
								</div>							
						</div>
						
						
						
						<div class="col-md-3">
						
						<div class="form-group row">
								<label for="cont_corps" class="col-sm-4 col-form-label"><strong
									style="color: red;"></strong>Corps</label>
								<div class="col-sm-8">
									<div class="multiselect">

										<div class="selectBox" onclick="showCheckboxescorps()">
											<select name="corps" id="corps" class=" form-control">
												<option>Select Corps</option>
											</select>
											<div class="overSelect"></div>
										</div>
										<div id="checkboxescorps" class="checkboxes"
											style="max-height: 200px; overflow: auto;">
											<div>
												<input type="text" id="corps_search"
													class="form-control autocomplete" autocomplete="off"
													placeholder="Search Corps">
											</div>
										
										</div>

									</div>
								</div>
							</div>
						
						</div>

</div>

					<div class="row">				
				
						<div class="col-md-3">

							<div class="form-group row">
								<label for="cont_corps" class="col-sm-4 col-form-label"><strong
									style="color: red;"></strong>Div</label>
								<div class="col-sm-8">
									<div class="multiselect">

										<div class="selectBox" onclick="showCheckboxesdiv()">
											<select name="division" id="division" class=" form-control">
												<option>Select Div</option>
											</select>
											<div class="overSelect"></div>
										</div>
										<div id="checkboxesdivision" class="checkboxes"
											style="max-height: 200px; overflow: auto;">
											<div>
												<input type="text" id="division_search"
													class="form-control autocomplete" autocomplete="off"
													placeholder="Search Div">
											</div>

										</div>

									</div>
								</div>
							</div>

						</div>
						
						
						<div class="col-md-3">
						
						<div class="form-group row">
								<label for="cont_corps" class="col-sm-4 col-form-label"><strong
									style="color: red;"></strong>Bde</label>
								<div class="col-sm-8">
									<div class="multiselect">

										<div class="selectBox" onclick="showCheckboxesbde()">
											<select name="bde" id="bde" class=" form-control">
												<option>Select bde</option>
											</select>
											<div class="overSelect"></div>
										</div>
										<div id="checkboxesbde" class="checkboxes"
											style="max-height: 200px; overflow: auto;">
											<div>
												<input type="text" id="bde_search"
													class="form-control autocomplete" autocomplete="off"
													placeholder="Search bde">
											</div>
										
										</div>

									</div>
								</div>
							</div>
						
						</div>
							<div class="col-md-3">
							<div class="form-group row">
								<label for="cont_bde" class="col-sm-4 col-form-label"><strong
									style="color: red;">*</strong>Unit Name</label>
								<div class="col-sm-8">
									<div class="selectBox" onclick="showCheckboxesunit()">
										<select name="unit_name" id="unit_name" class=" form-control">
											<option>Select Units</option>
										</select>
										<div class="overSelect"></div>
									</div>
									<div id="checkboxesunit" class="checkboxes"
										style="max-height: 200px; overflow: auto;">
										<div>
											<input type="text" id="unit_search"
												class="form-control autocomplete" autocomplete="off"
												placeholder="Search Unit">
										</div>
										<div>
											<c:forEach var="item" items="${unit_list}" varStatus="num">
												<label for="one" class="unit_list"> <input
													type="checkbox" name="multiunit" value="${item[0]}" />
													${item[0]}-${item[1]}
												</label>
											</c:forEach>
										</div>
									</div>

								</div>
							</div>
							<div id="selected_units_display"></div>
						</div>
							<div class="col-md-3">
							<div class="row form-group">

								<div class="col-md-12">
									<div class="row">

										<div id="unit_sub_list"
											style="max-height: 100px; overflow: auto; width: 100%; border: 1px solid;">

										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="form-control card-footer" align="center" id="buttonDiv">
						<a href="mms_ro_generation_new" class="btn btn-success btn-sm"
							style="border-radius: 5px;">Clear</a>
						<button class="btn btn-primary btn-sm" onclick="return Search();"
							style="border-radius: 5px;">Submit</button>
					</div>
					<br>
					<div></div>				

					<div class="col-md-12 "
						style='background-color: mistyrose; padding: 7px;'>
						<div class="col-md-5" align="center">
						 <b>DEPO Detail<span id="tregn"
									style='font-size: 14px;'></span></b>
						</div>
						<div class="col-md-5" align="Center">
							<b>UNIT Detail<span id="tregn" style='font-size: 14px;'></span></b>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-4"
							style="height: 300px; overflow: auto; width: 99%; border: 1px solid #000; text-align: left;"
							id="srctable">
							<table id="depo_table"
								class="table no-margin table-striped  table-hover  table-bordered">							
								<tbody>
									
								</tbody>
							</table>
						</div>
						<div class="col-md-8"
							style="height: 300px; overflow: auto; width: 99%; border: 1px solid #000; text-align: left;"
							id="tartable">					

							<table id="unit_table"
								class="table no-margin table-striped  table-hover  table-bordered">
								<thead style="font-size: 15px; text-align: center;">
									<tr>
										<th>Unit Name</th>
										<th>ITEM</th>
										<th>UE</th>
										<th>UH</th>
										<th>DEFI</th>
										<th>SURP</th>
										<th>ISSUE QTY</th>

									</tr>
								</thead>
								<tbody>
									
								</tbody>
							</table>
						</div>


					</div>

				</div>



			</div>
			<div class="form-control card-footer" align="center">
    <button class="btn btn-primary btn-sm" id="save_per" 
        onclick="generate_Ro();" style="border-radius: 5px;">GENERATE
        RO</button>
</div>

		</div>
	</div>
</div>
</div>


<c:url value="getunit_ue_uh" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm">
	<input type="hidden" name="prfgroupcodes" id="prfgroupcodes" value="0" />
	<input type="hidden" name="unit_id" id="unit_id" value="0" />
</form:form>
<script>
var depotSus;
	$(document).ready(
			function() {
				
				$('#unit_sub_list').css('border', 'none');
				$('#prf_sub_list').css('border', 'none');
				$('#quali_sub_list').css('border', 'none');
				$('#wpncat_sub_list').css('border', 'none');
			

				$("#sus_no").val('${sus_no1}');
				//$("#unit_name").val('${unit_name1}');

				if ('${cont_comd1}' != "") {
					$("#cont_comd").val('${cont_comd1}');
					getCONTCorps('${cont_comd1}');
				}
				if ('${cont_corps1}' != "") {
					getCONTDiv('${cont_corps1}');
				}
				if ('${cont_div1}' != "") {
					getCONTBde('${cont_div1}');
				}
				
				if ('${cont_div1}' != "") {
					getCONTUnit('${cont_div1}');
				}

				var select = '<option value="' + "0" + '">' + "--Select--"
						+ '</option>';
						
						var arm_code="";						
				$('select#arm').change(function() {		
					hidechekbox();
							 arm_code = this.value;
							if (arm_code == "0") {
								$("select#cont_comd").html(select);
								$("select#force_type").html(select);
							} else {
								$("select#cont_comd").html(select);
								$("select#force_type").html(select);
								getCONTforce(arm_code);
								getCommand(arm_code);
								//getCONTcomnd(arm_code)
							}
						});	
				$('select#force_type').change(function() {
					hidechekbox();
							var force_type = this.value;							
							if (force_type == "0") {
								$("select#cont_comd").html(select);								
							} else {								
								$("select#cont_comd").html(select);							
								getCONTcomnd(arm_code,force_type)
							}
						});
				$('select#cont_comd').change(function() {
					var fcode = this.value;
					if (fcode == "0") {
						$("select#cont_corps").html(select);
						$("select#cont_div").html(select);
						$("select#cont_bde").html(select);
					} else {
						
						$("select#cont_corps").html(select);
						$("select#cont_div").html(select);
						$("select#cont_bde").html(select);

						getCONTCorps(fcode);

						fcode += "00";
						getCONTDiv(fcode);

						fcode += "000";
						getCONTBde(fcode);
						getCONTUnit(fcode);
					}
				});
				$('select#cont_corps').change(function() {
					var fcode = this.value;
					if (fcode == "0") {
						$("select#cont_div").html(select);
						$("select#cont_bde").html(select);
					} else {
						$("select#cont_div").html(select);
						$("select#cont_bde").html(select);
						getCONTDiv(fcode);
						fcode += "000";
						getCONTBde(fcode);
						getCONTUnit(fcode);
					}
				});
				$('select#cont_div').change(function() {
					var fcode = this.value;
					if (fcode == "0") {
						$("select#cont_bde").html(select)
					} else {
						$("select#cont_bde").html(select)
						getCONTBde(fcode);
						getCONTUnit(fcode);
					}
				});
				$('select#cont_bde').change(function() {
					var fcode = this.value;
					if (fcode == "0") {
						$("select#unit_name").html(select)
					} else {
						$("select#unit_name").html(select)						
						getCONTUnit(fcode);
					}
				});
				
				$("#depo_tbl tbody").on('change', '.depot-checkbox', function() {	
					hidechekbox();
					 $('#depo_table tbody').empty();
				    $('.depot-checkbox').not(this).prop('checked', false);
				    
				    if ($(this).is(':checked')) {				       
				        depotSus = $(this).data('depot-sus');
				        
				        get_depots_item(depotSus);
				      
				    }
				});
				
			

			   
			});

	var key = "${_csrf.parameterName}";
	var value = "${_csrf.token}";

	function getCONTCorps(fcode) {
		var fcode1 = fcode[0];
		$.post("getCorpsDetailsList?" + key + "=" + value, {
			fcode : fcode1
		}, function(j) {
			if (j != "") {
				var length = j.length - 1;
				var enc = j[length][0].substring(0, 16);
				var options = '<option value="' + "0" + '">' + "--Select--"
						+ '</option>';

				for (var i = 0; i < length; i++) {
					if ('${cont_corps1}' == dec(enc, j[i][0])) {
						options += '<option value="' + dec(enc, j[i][0])
								+ '" name="' + dec(enc, j[i][1])
								+ '" selected=selected >' + dec(enc, j[i][1])
								+ '</option>';
					} else {
						options += '<option value="' + dec(enc, j[i][0])
								+ '" >' + dec(enc, j[i][1]) + '</option>';
					}
				}
				$("select#cont_corps").html(options);
			}
		});
	}
	function getCONTDiv(fcode) {
		var fcode1 = fcode[0] + fcode[1] + fcode[2];
		$.post("getDivDetailsList?" + key + "=" + value, {
			fcode : fcode1
		}, function(j) {
			if (j != "") {
				var length = j.length - 1;
				var enc = j[length][0].substring(0, 16);
				var options = '<option value="' + "0" + '">' + "--Select--"
						+ '</option>';
				for (var i = 0; i < length; i++) {
					if ('${cont_div1}' == dec(enc, j[i][0])) {
						options += '<option value="' + dec(enc, j[i][0])
								+ '" name="' + dec(enc, j[i][1])
								+ '" selected=selected >' + dec(enc, j[i][1])
								+ '</option>';
					} else {
						options += '<option value="' + dec(enc, j[i][0])
								+ '" >' + dec(enc, j[i][1]) + '</option>';
					}
				}
				$("select#cont_div").html(options);
			}
		});
	}
	function getCONTBde(fcode) {
		var fcode1 = fcode[0] + fcode[1] + fcode[2] + fcode[3] + fcode[4]
				+ fcode[5];
		$.post("getBdeDetailsList?" + key + "=" + value, {
			fcode : fcode1
		}, function(j) {
			if (j != "") {
				var length = j.length - 1;
				var enc = j[length][0].substring(0, 16);
				var options = '<option value="' + "0" + '">' + "--Select--"
						+ '</option>';
				jQuery("select#cont_bde").html(options);
				for (var i = 0; i < length; i++) {
					if ('${cont_bde1}' == dec(enc, j[i][0])) {
						options += '<option value="' + dec(enc, j[i][0])
								+ '" name="' + dec(enc, j[i][1])
								+ '" selected=selected>' + dec(enc, j[i][1])
								+ '</option>';
						$("#cont_bname").val(dec(enc, j[i][1]));
					} else {
						options += '<option value="' + dec(enc, j[i][0])
								+ '" name="' + dec(enc, j[i][1]) + '">'
								+ dec(enc, j[i][1]) + '</option>';
					}
				}
				$("select#cont_bde").html(options);
			}
		});
	}
	
	
	function getCONTforce(arm_code) {
		
		var arm_code = arm_code;
		$.post("getforceList?" + key + "=" + value, {
			arm_code : arm_code
		}, function(j) {
			if (j != "") {	
				
				var options = '<option value="' + "0" + '">' + "--Select--"
						+ '</option>';
				for (var i = 0; i < j.length; i++) {
					
						options += '<option value="' + j[i][0]
								+ '" >' + j[i][1] + '</option>';
					
				}
				$("select#force_type").html(options);
			
			}
		});
	}

	//////////----------------unit multi selection---------------------///////////////////////////

	$("input[type='checkbox'][name='multiunit']").click(function() {	
		 $('#unit_sub_list').css('border', '1px solid');
		var num = 0;
		$("input[type='checkbox'][name='multiunit']").each(function() {
			if (this.checked) {
				num = num + 1
			}

		});
		if (num != 0)
			$("#unit_name option:first").text('PRF Groups(' + num + ')');
		else
			$("#unit_name option:first").text("Select PRF Groups");
	});
	$("#unit_search").keyup(function() {
		var re = new RegExp($(this).val(), "i")
		$('.unit_list').each(function() {
			var text = $(this).text(), matches = !!text.match(re);
			$(this).toggle(matches)
		})
	});

	$("input[type='checkbox'][name='multiunit']")
			.click(
					function() {
						 $('#unit_sub_list').css('border', '1px solid');						
						var num = 0;
						$('#unit_sub_list').empty()
						$("input[type='checkbox'][name='multiunit']")
								.each(
										function() {
											if (this.checked) {
												 $('#unit_sub_list').append("<span class='subspan'>" + $(this).parent().text() + "<i class='fa fa-times' style='margin: 5px; font-size: 15px;' onclick='removeUnitFn(\"" + $(this).val() + "\")'></i></span><br>");
												num = num + 1;
											}

										});
						if (num != 0)
							$("#unit_name option:first").text(
									'Unit Names(' + num + ')');
						else
							$("#unit_name option:first").text(
									"Select Unit Names");
					});

	function removeUnitFn(value) {	    
	    $("input[type='checkbox'][name='multiunit'][value='" + value + "']").prop('checked', false);

	    var num = 0;
	    $('#unit_sub_list').empty();
	   
	    $("input[type='checkbox'][name='multiunit']").each(function() {
	        if (this.checked) {
	            $('#unit_sub_list').append("<span class='subspan'>" + $(this).parent().text() + "<i class='fa fa-times' style='margin: 5px; font-size: 15px;' onclick='removeUnitFn(\"" + $(this).val() + "\")'></i></span><br>");
	            num++;
	        }
	    });
	  
	    $("#unit_name option:first").text(num !== 0 ? 'Unit Names(' + num + ')' : 'Select Unit Names');
	}



	function showCheckboxesunit() {
		var checkboxes = document.getElementById("checkboxesunit");
		$("#checkboxesunit").toggle();
		$("#unit_search").val('');
		$("#checkboxeswpncat").hide();
		$("#checkboxesprf").hide();
		$("#checkboxes").hide();
		$("#checkboxescomd").hide();
		$("#checkboxescorps").hide();
		$("#checkboxesdivision").hide();		
		//$("#checkboxesunit").hide();
		$("#checkboxesbde").hide();
		$('.unit_list').each(function() {
			$(this).show()
		})
	}

	//-----------------prf group multi select-------------------------/////////////

	$("input[type='checkbox'][name='multiprf']").click(function() {		
		 $('#prf_sub_list').css('border', '1px solid');
		var num = 0;
		$("input[type='checkbox'][name='multiprf']").each(function() {
			if (this.checked) {
				num = num + 1
			}

		});
		if (num != 0){
			$("#prf option:first").text('PRF Groups(' + num + ')');
		}
		else{	
	
			$("#prf option:first").text("Select PRF Groups");}
		$('#depo_table tbody').empty();
		get_item_depotwise();
		
	});
	$("#prf_search").keyup(function() {
		var re = new RegExp($(this).val(), "i")
		$('.prf_list').each(function() {
			var text = $(this).text(), matches = !!text.match(re);
			$(this).toggle(matches)
		})
	});

	$("input[type='checkbox'][name='multiprf']")
			.click(
					function() {
						debugger;
						 $('#prf_sub_list').css('border', '1px solid');						
						var num = 0;
						$('#prf_sub_list').empty()
						$("input[type='checkbox'][name='multiprf']").each(function() {if (this.checked) {
									               
									                $('#prf_sub_list').append("<span class='subspan'>" + this.parentElement.innerText +
									                    "<i class='fa fa-times' style='margin: 5px; font-size: 15px;' onclick='removePrfFn(" + this.value + ")'></i><span> <br>");
									                num++;
									            }

										});
						if (num != 0)
							$("#prf option:first").text(
									'PRF Groups(' + num + ')');
						else
							$("#prf option:first").text("Select PRF Groups");
					});

	function removePrfFn(value) {
		debugger;
		$("input[type='checkbox'][name='multiprf'][value='" + value + "']")
				.prop('checked', false);

		var num = 0;
		$('#prf_sub_list').empty();
		$("input[type='checkbox'][name='multiprf']")
				.each(
						function() {
							if (this.checked) {
								$('#prf_sub_list')
										.append(
												"<span class='subspan'>"
														+ $(this).parent()
																.text()
														+ "<i class='fa fa-times' style='margin: 5px; font-size: 15px;' onclick='removePrfFn(\""
														+ $(this).val()
														+ "\")'></i></span><br>");
								num++;
							}
						});

		$("#prf option:first").text(
				num != 0 ? 'PRF Groups(' + num + ')' : 'Select PRF Groups');
		$('#depo_table tbody').empty();
		get_item_depotwise();
	}

	function showCheckboxesprf() {
		var checkboxes = document.getElementById("checkboxesprf");
		$("#checkboxesprf").toggle();
		$("#prf_search").val('');
		$("#checkboxeswpncat").hide();
		//$("#checkboxesprf").hide();
		$("#checkboxes").hide();
		$("#checkboxescomd").hide();
		$("#checkboxescorps").hide();
		$("#checkboxesdivision").hide();		
		$("#checkboxesunit").hide();
		$("#checkboxesbde").hide();
		$('.pfr_list').each(function() {
			$(this).show()
		})	
	}

	////-----------------------------depot multi selection---------------------------////

// Attach event listener to checkboxes
$(document).on('click', "input[type='checkbox'][name='multisub']", function() {
    $('#quali_sub_list').css('border', '1px solid');
    var num = $("input[type='checkbox'][name='multisub']:checked").length;
    if (num !== 0) {
        $("#sub_quali option:first").text('Depos(' + num + ')');
    } else {
        $("#sub_quali option:first").text("Select Depots");
    }
    get_item_depotwise();
});

	$("#quali_sub_search").keyup(function() {
		var re = new RegExp($(this).val(), "i")
		$('.quali_subjectlist').each(function() {
			var text = $(this).text(), matches = !!text.match(re);
			$(this).toggle(matches)
		})
	});

	
	$(document).on('click', "input[type='checkbox'][name='multisub']", function() {
	    
	   
	    $('#quali_sub_list').css('border', '1px solid');
	    var num = 0;
	    $('#quali_sub_list').empty();
	    $("input[type='checkbox'][name='multisub']").each(function() {
	        if (this.checked) {
	        	$('#quali_sub_list').append("<span class='subspan'>" + this.parentElement.innerText + "<i class='fa fa-times' style='margin: 5px;  font-size: 15px;' onclick='removeSubFn(\"" + this.value + "\")'></i></span><br>");

	            num = num + 1;
	        }
	    });
	    if (num != 0)
	        $("#sub_quali option:first").text('Depos(' + num + ')');
	    else
	        $("#sub_quali option:first").text("Select Depots");
	});


	function removeSubFn(value) {

		$("input[type='checkbox'][name='multisub'][value='" + value + "']")
				.attr('checked', false);

		var num = 0;
		$('#quali_sub_list').empty()
		$("input[type='checkbox'][name='multisub']")
				.each(
						function() {
							if (this.checked) {

								$('#quali_sub_list').append("<span class='subspan'>" + this.parentElement.innerText + "<i class='fa fa-times' style='margin: 5px;  font-size: 15px;' onclick='removeSubFn(\"" + this.value + "\")'></i></span><br>");

								num = num + 1;
							}
						});
		if (num != 0){
			$("#sub_quali option:first").text('Depos(' + num + ')');}
		else{
			$("#sub_quali option:first").text("Select Depos");}
		
		get_item_depotwise();

	}

	function showCheckboxes() {		
		var checkboxes = document.getElementById("checkboxes");
		$("#checkboxes").toggle();
		$("#quali_sub_search").val('');
		$("#checkboxescorps").toggle();
		$("#corps_search").val('');
		$("#checkboxesprf").hide();
		//$("#checkboxes").hide();
		$("#checkboxescomd").hide();
		$("#checkboxescorps").hide();
		$("#checkboxesdivision").hide();		
		$("#checkboxesunit").hide();
		$("#checkboxesbde").hide();
		$('.quali_subjectlist').each(function() {
			$(this).show()
		})
		//get_item_depotwise();
	}
	
	
	
////-----------------------------wpn cat multi selection---------------------------////

		$("input[type='checkbox'][name='multiwpncat']").click(function() {
		// access properties using this keyword
		 $('#wpncat_sub_list').css('border', '1px solid');
		var num = 0;
		$("input[type='checkbox'][name='multiwpncat']").each(function() {
			if (this.checked) {
				num = num + 1
			}

		});
		if (num != 0){
			$("#wpncat option:first").text('WPN CAT(' + num + ')');
		}
		else{	
	
			$("#wpncat option:first").text("Select Wpn/ cont store cat");}
		//updateDepotOptions();
		
	});
	$("#wpncat_search").keyup(function() {
		var re = new RegExp($(this).val(), "i")
		$('.wpncat_list').each(function() {
			var text = $(this).text(), matches = !!text.match(re);
			$(this).toggle(matches)
		})
	});

	$("input[type='checkbox'][name='multiwpncat']")
			.click(
					function() {
						 $('#wpncat_sub_list').css('border', '1px solid');						
						var num = 0;
						$('#wpncat_sub_list').empty()
						$("input[type='checkbox'][name='multiwpncat']")
								.each(
										function() {
											if (this.checked) {
												$('#wpncat_sub_list')
														.append(
																"<span class='subspan'>"
																		+ this.parentElement.innerText
																		+ "<i class='fa fa-times' style='margin: 5px;  font-size: 15px;' onclick='removewpncatFn("
																		+ this.value
																		+ ")'></i><span> <br>");
												num = num + 1;
											}

										});
						if (num != 0)
							$("#wpncat option:first").text(
									'WPN CAT(' + num + ')');
						else
							$("#wpncat option:first").text("Select WPN CAT");
					});

	function removewpncatFn(value) {
   
		$("input[type='checkbox'][name='multiwpncat'][value='" + value + "']")
				.prop('checked', false); 

		var num = 0;
		$('#wpncat_sub_list').empty();
		$("input[type='checkbox'][name='multiwpncat']")
				.each(
						function() {
							if (this.checked) {
								$('#wpncat_sub_list')
										.append(
												"<span class='subspan'>"
														+ $(this).parent()
																.text()
														+ "<i class='fa fa-times' style='margin: 5px; font-size: 15px;' onclick='removewpncatFn(\""
														+ $(this).val()
														+ "\")'></i></span><br>");
								num++;
							}
						});

		$("#wpncat option:first").text(
				num != 0 ? 'WPN CAT(' + num + ')' : 'Select WPN CAT');
		//updateDepotOptions();
	}

	function showCheckboxeswpncat() {
		var checkboxes = document.getElementById("checkboxeswpncat");
		$("#checkboxeswpncat").toggle();
		$("#wpncat_search").val('');		
		$("#checkboxesprf").hide();
		$("#checkboxes").hide();
		$("#checkboxescomd").hide();
		$("#checkboxescorps").hide();
		$("#checkboxesdivision").hide();		
		$("#checkboxesunit").hide();
		$("#checkboxesbde").hide();
		$('.wpncat_list').each(function() {
			$(this).show()
		})
		//updateDepotOptions();
	}
	
	
////-----------------------------comd multi selection---------------------------////


	$("input[type='checkbox'][name='multicomd']").click(function() {
		
		 $('#comd_sub_list').css('border', '1px solid');
		var num = 0;
		$("input[type='checkbox'][name='multicomd']").each(function() {
			if (this.checked) {
				num = num + 1
			}

		});
		if (num != 0){
			$("#comd option:first").text('comd(' + num + ')');
		}
		else{	
	
			$("#comd option:first").text("Select comd");}
		updatecorpslist();
		
	});
	$("#comd_search").keyup(function() {
		var re = new RegExp($(this).val(), "i")
		$('.comd_list').each(function() {
			var text = $(this).text(), matches = !!text.match(re);
			$(this).toggle(matches)
		})
	});

	$("input[type='checkbox'][name='multicomd']")
			.click(
					function() {
						 $('#comd_sub_list').css('border', '1px solid');
						// access properties using this keyword
						var num = 0;
						$('#comd_sub_list').empty()
						$("input[type='checkbox'][name='multicomd']")
								.each(
										function() {
											if (this.checked) {
												$('#comd_sub_list')
														.append(
																"<span class='subspan'>"
																		+ this.parentElement.innerText
																		+ "<i class='fa fa-times' style='margin: 5px;  font-size: 15px;' onclick='removecomdFn("
																		+ this.value
																		+ ")'></i><span> <br>");
												num = num + 1;
											}

										});
						if (num != 0)
							$("#comd option:first").text(
									'comd(' + num + ')');
						else
							$("#comd option:first").text("Select comd");
					});

	function removecomdFn(value) {
   
		$("input[type='checkbox'][name='multicomd'][value='" + value + "']")
				.prop('checked', false); 

		var num = 0;
		$('#comd_sub_list').empty();
		$("input[type='checkbox'][name='multicomd']")
				.each(
						function() {
							if (this.checked) {
								$('#comd_sub_list')
										.append(
												"<span class='subspan'>"
														+ $(this).parent()
																.text()
														+ "<i class='fa fa-times' style='margin: 5px; font-size: 15px;' onclick='removecomdFn(\""
														+ $(this).val()
														+ "\")'></i></span><br>");
								num++;
							}
						});

		$("#comd option:first").text(
				num != 0 ? 'comd(' + num + ')' : 'Select comd');
		updatecorpslist();
	}

	function showCheckboxescomd() {
		var checkboxes = document.getElementById("checkboxeswpncat");
		$("#checkboxescomd").toggle();
		$("#comd_search").val('');
	
		$("#checkboxesprf").hide();
		$("#checkboxeswpncat").hide();
		$("#checkboxes").hide();
		//$("#checkboxescomd").hide();
		$("#checkboxescorps").hide();
		$("#checkboxesdivision").hide();		
		$("#checkboxesunit").hide();
		$("#checkboxesbde").hide();
		$('.comd_list').each(function() {
			$(this).show()
		})
		//updatecorpslist();
	}
	
	
/////////////////////////// corps multiselect /////////////////////

$(document).on('click', "input[type='checkbox'][name='multicorps']", function() {
    $('#corps_sub_list').css('border', '1px solid');
    var num = $("input[type='checkbox'][name='multicorps']:checked").length;
    if (num !== 0) {
        $("#corps option:first").text('corps(' + num + ')');
    } else {
        $("#corps option:first").text("Select corps");
    }
    updatedivlist();
});

	$("#corps_search").keyup(function() {
		var re = new RegExp($(this).val(), "i")
		$('.corps_list').each(function() {
			var text = $(this).text(), matches = !!text.match(re);
			$(this).toggle(matches)
		})
	});

	
	$(document).on('click', "input[type='checkbox'][name='multicorps']", function() {
	    
	    // Access properties using this keyword
	    $('#corps_sub_list').css('border', '1px solid');
	    var num = 0;
	    $('#corps_list').empty();
	    $("input[type='checkbox'][name='multicorps']").each(function() {
	        if (this.checked) {
	        	$('#corps_sub_list').append("<span class='subspan'>" + this.parentElement.innerText + "<i class='fa fa-times' style='margin: 5px;  font-size: 15px;' onclick='removecorpsFn(\"" + this.value + "\")'></i></span><br>");

	            num = num + 1;
	        }
	    });
	    if (num != 0)
	        $("#corps option:first").text('corps(' + num + ')');
	    else
	        $("#corps option:first").text("Select corps");
	});


	function removecorpsFn(value) {

		$("input[type='checkbox'][name='multicorps'][value='" + value + "']")
				.attr('checked', false);

		var num = 0;
		$('#corps_sub_list').empty()
		$("input[type='checkbox'][name='multicorps']")
				.each(
						function() {
							if (this.checked) {

								$('#corps_sub_list').append("<span class='subspan'>" + this.parentElement.innerText + "<i class='fa fa-times' style='margin: 5px;  font-size: 15px;' onclick='removecorpsFn(\"" + this.value + "\")'></i></span><br>");

								num = num + 1;
							}
						});
		if (num != 0){
			$("#corps option:first").text('corps(' + num + ')');}
		else{
			$("#corps option:first").text("Select corps");}
		
		 updatedivlist();

	}

	function showCheckboxescorps() {		
		var checkboxes = document.getElementById("checkboxescorps");
		$("#checkboxescorps").toggle();
		$("#corps_search").val('');
		$("#checkboxesprf").hide();
		$("#checkboxes").hide();
		$("#checkboxescomd").hide();
		$("#checkboxeswpncat").hide();
		//$("#checkboxescorps").hide();
		$("#checkboxesdivision").hide();		
		$("#checkboxesunit").hide();
		$("#checkboxesbde").hide();
		$('.corps_list').each(function() {
			$(this).show()
		})
		// updatedivlist();
	}
	
/////////////////////////// division multiselect /////////////////////

	$(document).on('click', "input[type='checkbox'][name='multidivision']", function() {
	    $('#division_sub_list').css('border', '1px solid');
	    var num = $("input[type='checkbox'][name='multidivision']:checked").length;
	    if (num !== 0) {
	        $("#division option:first").text('div(' + num + ')');
	    } else {
	        $("#division option:first").text("Select div");
	    }
	    updatebdelist();
	});

		$("#division_search").keyup(function() {
			var re = new RegExp($(this).val(), "i")
			$('.division_list').each(function() {
				var text = $(this).text(), matches = !!text.match(re);
				$(this).toggle(matches)
			})
		});

		
		$(document).on('click', "input[type='checkbox'][name='multidivision']", function() {
		    
		    // Access properties using this keyword
		    $('#division_sub_list').css('border', '1px solid');
		    var num = 0;
		    $('#division_list').empty();
		    $("input[type='checkbox'][name='multidivision']").each(function() {
		        if (this.checked) {
		        	$('#division_sub_list').append("<span class='subspan'>" + this.parentElement.innerText + "<i class='fa fa-times' style='margin: 5px;  font-size: 15px;' onclick='removedivisionFn(\"" + this.value + "\")'></i></span><br>");

		            num = num + 1;
		        }
		    });
		    if (num != 0)
		        $("#division option:first").text('div(' + num + ')');
		    else
		        $("#division option:first").text("Select div");
		});


		function removedivisionFn(value) {

			$("input[type='checkbox'][name='multidivision'][value='" + value + "']")
					.attr('checked', false);

			var num = 0;
			$('#division_sub_list').empty()
			$("input[type='checkbox'][name='multidivision']")
					.each(
							function() {
								if (this.checked) {

									$('#division_sub_list').append("<span class='subspan'>" + this.parentElement.innerText + "<i class='fa fa-times' style='margin: 5px;  font-size: 15px;' onclick='removedivisionFn(\"" + this.value + "\")'></i></span><br>");

									num = num + 1;
								}
							});
			if (num != 0){
				$("#division option:first").text('div(' + num + ')');}
			else{
				$("#division option:first").text("Select div");}
			
			  updatebdelist();

		}

		function showCheckboxesdiv() {		
			var checkboxes = document.getElementById("checkboxesdivision");
			$("#checkboxesdivision").toggle();
			$("#division_search").val('');
			$("#checkboxesprf").hide();
			$("#checkboxeswpncat").hide();
			$("#checkboxes").hide();
			$("#checkboxescomd").hide();
			$("#checkboxescorps").hide();
			//$("#checkboxesdivision").hide();
			$("#checkboxesunit").hide();
			$("#checkboxesbde").hide();
			$('.division_list').each(function() {
				$(this).show()
			})
			 // updatebdelist();
		}
		
		
		
/////////////////////////// bde multiselect /////////////////////

		$(document).on('click', "input[type='checkbox'][name='multibde']", function() {
		    $('#bde_sub_list').css('border', '1px solid');
		    var num = $("input[type='checkbox'][name='multibde']:checked").length;
		    if (num !== 0) {
		        $("#bde option:first").text('bde(' + num + ')');
		    } else {
		        $("#bde option:first").text("Select bde");
		    }
		    updateunitlist();
		    
		});

			$("#bde_search").keyup(function() {
				var re = new RegExp($(this).val(), "i")
				$('.bde_list').each(function() {
					var text = $(this).text(), matches = !!text.match(re);
					$(this).toggle(matches)
				})
			});

			
			$(document).on('click', "input[type='checkbox'][name='multibde']", function() {			 
			    $('#bde_sub_list').css('border', '1px solid');
			    var num = 0;
			    $('#bde_list').empty();
			    $("input[type='checkbox'][name='multibde']").each(function() {
			        if (this.checked) {
			        	$('#bde_sub_list').append("<span class='subspan'>" + this.parentElement.innerText + "<i class='fa fa-times' style='margin: 5px;  font-size: 15px;' onclick='removebdeFn(\"" + this.value + "\")'></i></span><br>");

			            num = num + 1;
			        }
			    });
			    if (num != 0)
			        $("#bde option:first").text('bde(' + num + ')');
			    else
			        $("#bde option:first").text("Select bde");
			});


			function removebdeFn(value) {

				$("input[type='checkbox'][name='multibde'][value='" + value + "']")
						.attr('checked', false);

				var num = 0;
				$('#bde_sub_list').empty()
				$("input[type='checkbox'][name='multibde']")
						.each(
								function() {
									if (this.checked) {

										$('#bde_sub_list').append("<span class='subspan'>" + this.parentElement.innerText + "<i class='fa fa-times' style='margin: 5px;  font-size: 15px;' onclick='removebdeFn(\"" + this.value + "\")'></i></span><br>");

										num = num + 1;
									}
								});
				if (num != 0){
					$("#bde option:first").text('bde(' + num + ')');}
				else{
					$("#bde option:first").text("Select bde");}
			
				updateunitlist();

			}

			function showCheckboxesbde() {		
				var checkboxes = document.getElementById("checkboxesbde");
				$("#checkboxesbde").toggle();
				$("#bde_search").val('');
				$("#checkboxesprf").hide();
				$("#checkboxes").hide();
				$("#checkboxescorps").hide();
				$("#checkboxescomd").hide();
				$("#checkboxesdivision").hide();
				$("#checkboxesunit").hide();
				$("#checkboxeswpncat").hide();

				$('.bde_list').each(function() {
					$(this).show()
				})
				
			}

	

function Search() {
	
	 hidechekbox();
	$('#unit_table tbody').empty();
    var prfgroup = $('input[name="multiprf"]:checkbox:checked').map(
            function() {
                return this.value;
            }).get();
        var prfcode = prfgroup.join(",");

        if (prfcode.trim() == "") {
            alert("Please Select PRF");
            $("select#prf").focus();
            return false;
        }
        
        if (depotSus === "" || typeof depotSus === "undefined") {
       	 alert("Please Select Depo");
       	 return false;
       }

        if ($('#type_of_ro').val() == 0) {       	
            alert("Please Select Type Of Ro");
            $("select#type_of_ro").focus();
            return false;
        }
        
      
    var unitvar = $('input[name="multiunit"]:checkbox:checked').map(
        function() {
            return this.value;
        }).get();
    var unit = unitvar.join(",");

    if (unit.trim() == "") {
        alert("Please Select Unit Name");
        $("select#unit_name").focus();
        return false;
    }



    $.post("unit_wies_prf_ue_uh?" + key + "=" + value, {
        prfcode: prfcode,
        unit: unit
    }, function(j) {
        var currentUnit = null;
        var tbody = $("#unit_table tbody");

        for (var i = 0; i < j.length; i++) {
            var unitName = j[i].unit_name;
            if (unitName !== currentUnit) {
                var unitRow = $("<tr>").addClass("unit-name-row");
                unitRow.append('<td colspan="7" style="text-align: left; font-weight: bold;">' + unitName + '</td>');
                tbody.append(unitRow);
                currentUnit = unitName;
            }

            var newRow = $("<tr>");
            newRow.append("<td>" + "" + "</td>");
            newRow.append("<td style='text-align: center; font-weight: bold;'>" + j[i].item_nomen + "</td>");
            newRow.append("<td style='text-align: center; font-weight: bold;'>" + j[i].totue + "</td>");
            newRow.append("<td style='text-align: center; font-weight: bold;'>" + j[i].totuh + "</td>");
            newRow.append("<td style='text-align: center; font-weight: bold; color: red;'>" + j[i].defi + "</td>");
            newRow.append("<td style='text-align: center; font-weight: bold; color: green;'>" + j[i].sur + "</td>");
            newRow.append("<td style='display: none;' id='" + j[i].prf_group_code + "' value='" + j[i].prf_group_code + "'>" +j[i].prf_group_code + "</td>");
            

            var button = $('<span>').addClass('issueQtyInput caret-button').attr('id_type', j[i].item_code).attr('sus_no', j[i].sus_no).attr('totue', j[i].totue).attr('totuh', j[i].totuh).attr('prf_code',j[i].prf_group_code).attr('type_of_eqpt',j[i].type_of_eqpt).text('^');

            button.on('click', function() {
                var itemcode = $(this).attr('id_type');
                var sus_no = $(this).attr('sus_no'); 
                var ue=$(this).attr('totue'); 
                var uh=$(this).attr('totuh'); 
                var prf_code=$(this).attr('prf_code'); 
                var type_of_eqpt=$(this).attr('type_of_eqpt'); 
                var currentRow = $(this).closest("tr");
                var newRow = currentRow.next('.additional-row');

                if (newRow.length) {
                    $(this).text('v');
                    $(this).prop('disabled', false);
                } else {
                    $(this).text('v');
                    $(this).prop('disabled', true);
                    $(this).css('background-color', '#007bff');
                    $.post("unitanditemcode_wise_census?" + key + "=" + value, {
                        itemcode: itemcode,
                        sus_no: sus_no,
                        type_of_eqpt:type_of_eqpt
                    }, function(m) {
                        for (var i = 0; i < m.length; i++) {
                            newRow = $("<tr>").addClass("additional-row");
                            newRow.append("<td>" + "" + "</td>");
                            newRow.append("<td>" + m[i].nomen + "</td>");
                            newRow.append("<td>" + "" + "</td>");
                            newRow.append("<td>" + m[i].totuh + "</td>");
                            newRow.append("<td>" + "" + "</td>");
                            newRow.append("<td>" + "" + "</td>");
                            newRow.append('<td><input type="number" id="' + sus_no + m[i].census_no + '" class="form-control new-input" onchange="validateNumber(this.id, this.value)"></td>');
                            newRow.append("<td style='display: none;' id='" + m[i].census_no + "' value='" + m[i].census_no + "'>" + m[i].census_no + "</td>");
                            newRow.append("<td style='display: none;' id='" + sus_no + "' value='" + sus_no + "'>" +sus_no + "</td>");
                            newRow.append("<td style='display: none;' id='" + itemcode + "' value='" + itemcode + "'>" +itemcode + "</td>");
                            newRow.append("<td style='display: none;' id='" + ue + "' value='" + ue + "'>" +ue + "</td>");
                            newRow.append("<td style='display: none;' id='" + uh + "' value='" + uh + "'>" +uh + "</td>");
                            newRow.append("<td style='display: none;' id='" + prf_code + "' value='" + prf_code + "'>" +prf_code + "</td>");
                            newRow.append("<td style='display: none;' id='" + type_of_eqpt + "' value='" + type_of_eqpt + "'>" +type_of_eqpt + "</td>");
                            
                          
                           
                           
                            currentRow.after(newRow);
                        }
                    });
                }
            });

            newRow.append($('<td>').append(button));

            tbody.append(newRow);
        }
    });
}

	
 	function get_depots_item(depotSus) {   
	  
	    $.post("get_depots_item?" + key + "=" + value, {	    	
	        selectedPRFs: selectedPRFs.join(","),
	        depotSus:depotSus
	    }, function(j) {
	        var currentDepot = null; 
	        var tbody = $("#depo_table tbody");	  
	        tbody.empty();
	        for (var i = 0; i < j.length; i++) {			
	            if (j[i].depot_sus !== currentDepot) {
	               
	            	 var depotRow = $("<tr>");	                
	                 var unitNameCell = $("<td colspan='2' style='font-weight: bold;'>" + j[i].unit_name + "</td>");	              
	                 depotRow.append(unitNameCell);

	                 tbody.append(depotRow);
	                 currentDepot = j[i].depot_sus;
	            }
	            
	        
	            var newRow = $("<tr>");	        
	            newRow.append("<td style='text-align: left; background-color: white;'>" + j[i].nomen + "</td>");
	            newRow.append("<td style='text-align: center; font-weight: bold; background-color: white; ' id='" + j[i].census_no  + "' value='" + j[i].depot_stock + "'>" + j[i].depot_stock + "</td>");
	            newRow.append("<td style='display: none;' id='" + j[i].depot_sus + j[i].census_no  + "' data-value='" + j[i].depot_stock + "'>" + j[i].depot_stock + "</td>");

	            tbody.append(newRow);
	        }
	    });

	} 
	
	var selectedPRFs = [];
	function get_item_depotwise() {		
		selectedPRFs=[];
		    $("input[type='checkbox'][name='multiprf']:checked").each(function() {
		    	selectedPRFs.push($(this).val());
		    }); 	   
		  
		    $.post("get_item_depowise?" + key + "=" + value, {	    	
		        selectedPRFs: selectedPRFs.join(","),
		        search:""
		    }, function(j) {
		        var currentDepot = null; 
		        var tbody = $("#depo_tbl tbody");	  
		        tbody.empty();
		        for (var i = 0; i < j.length; i++) {			
		            if (j[i].depot_sus !== currentDepot) {
		               
		            	 var depotRow = $("<tr>");
		                 var checkboxCell = $("<td>").append("<input type='checkbox' class='depot-checkbox' data-depot-sus='" + j[i].depot_sus + "'>");
		                 var unitNameCell = $("<td colspan='2' style='font-weight: bold;'>" + j[i].unit_name + "</td>");

		                 depotRow.append(checkboxCell);
		                 depotRow.append(unitNameCell);

		                 tbody.append(depotRow);
		                 currentDepot = j[i].depot_sus;
		            }
		            
		        
		            var newRow = $("<tr>");
		            newRow.append("<td style='text-align: left; background-color: white;'>" + ""+ "</td>");
		            newRow.append("<td style='text-align: left; background-color: white;'>" + j[i].nomen + "</td>");
		            newRow.append("<td style='text-align: center; font-weight: bold; background-color: white; '>" + j[i].depot_stock + "</td>");
		            newRow.append("<td style='display: none;' id='" + j[i].depot_sus + j[i].census_no  + "' value='" + j[i].depot_stock + "'>" + j[i].depot_stock + "</td>");

		            tbody.append(newRow);
		        }
		    });

		} 
	
	var formationcode = [];

	function updatecorpslist() {
		debugger;
		formationcode=[];
	    $("input[type='checkbox'][name='multicomd']:checked").each(function() {
	    	formationcode.push($(this).val());
	    	//formationcodecorps.push($(this).val() + "00");
	    	//formationcodediv.push($(this).val() + "00000");
	    	//formationcodebde.push($(this).val() + "00000000");
	    });	 
	   
	  /*   updatedivlist();
	    formationcodecorps=[];
	    updatebdelist();
	    formationcodediv=[];
	    updateunitlist();
	    formationcodebde=[]; */
	    
	    $.post("get_corps?" + key + "=" + value, {        
	    	formationcode: formationcode.join(",")
	    }, function(j) {
	    
	        var dropdown = $("#corps"); 
	        dropdown.empty(); 
	        
	        var checkboxesContainer = $("#checkboxescorps div:last-child"); 
	        checkboxesContainer.empty(); 
	
	        dropdown.append($('<option>', { value: '', text: 'Select corps' }));	        
	      
	        for (var i = 0; i < j.length; i++) {	           
	            checkboxesContainer.append('<label for="one" class="corps_list" ><input type="checkbox" name="multicorps" value="' + j[i].form_code_control + '"> ' + j[i].unit_name + '</label>');
	        }
	    });  
	}
	
	var formationcodecorps = [];
	function updatedivlist() {
		debugger;
		//formationcodecorps=[];
	    $("input[type='checkbox'][name='multicorps']:checked").each(function() {
	    	formationcodecorps.push($(this).val());
	    });
	
	    $.post("get_division?" + key + "=" + value, {        
	    	formationcodecorps: formationcodecorps.join(",")
	    }, function(j) {
	    
	        var dropdown = $("#division"); 
	        dropdown.empty(); 
	        
	        var checkboxesContainer = $("#checkboxesdivision div:last-child"); 
	        checkboxesContainer.empty(); 
	
	        dropdown.append($('<option>', { value: '', text: 'Select Division' }));	        
	      
	        for (var i = 0; i < j.length; i++) {	           
	            checkboxesContainer.append('<label for="one" class="division_list" ><input type="checkbox" name="multidivision" value="' + j[i].form_code_control + '"> ' + j[i].unit_name + '</label>');
	        }
	    });  
	}
	
	var formationcodediv = [];
	function updatebdelist() {
		debugger;
		//formationcodediv=[];
	    $("input[type='checkbox'][name='multidivision']:checked").each(function() {
	    	formationcodediv.push($(this).val());
	    });
	 
	    $.post("get_bde?" + key + "=" + value, {        
	    	formationcodediv: formationcodediv.join(",")
	    }, function(j) {
	    
	        var dropdown = $("#bde"); 
	        dropdown.empty(); 
	        
	        var checkboxesContainer = $("#checkboxesbde div:last-child"); 
	        checkboxesContainer.empty(); 
	
	        dropdown.append($('<option>', { value: '', text: 'Select bde' }));	        
	      
	        for (var i = 0; i < j.length; i++) {	           
	            checkboxesContainer.append('<label for="one" class="bde_list" ><input type="checkbox" name="multibde" value="' + j[i].form_code_control + '"> ' + j[i].unit_name + '</label>');
	        }
	    });  
	}
	
	var formationcodebde = [];
	function updateunitlist() {
		debugger;
		//formationcodebde=[];
	    $("input[type='checkbox'][name='multibde']:checked").each(function() {
	    	formationcodebde.push($(this).val());
	    });
	 
	    $.post("get_unit?" + key + "=" + value, {        
	    	formationcodebde: formationcodebde.join(",")
	    }, function(j) {
	        var dropdown = $("#unit_name"); 
	        dropdown.empty(); 
	        
	        var checkboxesContainer = $("#checkboxesunit div:last-child"); 
	        checkboxesContainer.empty(); 
	
	        dropdown.append($('<option>', { value: '', text: 'Select Unit' }));	        
	      
	        for (var i = 0; i < j.length; i++) {	           
	            checkboxesContainer.append('<label for="one" class="unit_list" ><input type="checkbox" name="multiunit" value="' + j[i].sus_no + '"> ' + j[i].sus_no +'-'+ j[i].unit_name + '</label>');
	        }
	    });  
	}

	

	
	function getCONTcomnd(arm_code,force_type) {	
		arm_code = arm_code;
		var force_type=force_type;	
		$.post("getcomndList?" + key + "=" + value, {
			arm_code : arm_code,
			force_type:force_type
		}, function(j) {
			if (j != "") {	
				
				var options = '<option value="' + "0" + '">' + "--Select--"
						+ '</option>';
				for (var i = 0; i < j.length; i++) {
					
						options += '<option value="' + j[i][0]
								+ '" >' + j[i][1] + '</option>';
					
				}
				$("select#cont_comd").html(options);
			
			}
		});
	}		

	
	function validateNumber(id, inputValue) { 
	    
	    var tableRows = $("#depo_table tbody tr");
	    var unit_census_id = id;
	    var depot_census_id = "";
	    var targetLastDigits = id.slice(-9);
	    var depotitemqty ="";
	    var matchFound = false;
	    
	    tableRows.each(function() {
	        var row = $(this);
	        var depot_census_id = row.find("td[id$='" + targetLastDigits + "']").attr('id');
	        if (depot_census_id) {
	            matchFound = true;
	            depotitemqty = row.find("td[id='" + depot_census_id + "']").text();
	            return false; 
	        }
	    });

	    if (matchFound) {  
	    	var value = parseFloat(inputValue);
	        if (isNaN(value)) {
	            alert("Please enter a valid number.");
	            $("#" + id).val("");
	            $("#" + id).focus();	       
	        }	  
	        if(depotitemqty < value){
	            alert("Please Enter Available qty");          
	            $("#" + id).val("");
	            $("#" + id).focus();
	        }
	    } else {  
	        $("#" + id).val("");
	        alert("Item not present in Depo.");
	    }
	}





function generate_Ro() {
	$('#save_per').prop('disabled', true);	
	$("#WaitLoader").show();
    var tableRows = $("#unit_table tbody tr");
    var rowData = [];

    tableRows.each(function(index, element) {
        var row = {};
        var columns = $(this).find("td");

        var inputField = $(columns[6]).find("input");
        var issueQty = inputField.val();

        if (issueQty > 0) {
            var censusNo = $(columns[7]).text();
            var susNo = $(columns[8]).text();
            var itemCode = $(columns[9]).text();
            var itemcodeue = $(columns[10]).text();
            var itemcodeuh = $(columns[11]).text();
            var prf_code = $(columns[12]).text(); 
            var type_of_eqpt = $(columns[13]).text(); 
            var dataString = censusNo + ":" + susNo + ":" + itemCode + ":" + itemcodeue + ":" + itemcodeuh + ":" + prf_code + ":" + type_of_eqpt + ":" + issueQty; 
            rowData.push(dataString);
        }
    });

    var actiondata = rowData.join(",");
    var depot_sus_no = depotSus;
    var ro_no = $("#ro_no").val();    
  
    $.post('generat_ro_action?' + key + "=" + value, { rowData: actiondata,depot_sus_no:depot_sus_no,ro_no:ro_no },
        function(data) {
    	$("#WaitLoader").hide();
           if (parseInt(data) > 0) {
                alert('Data saved successfully.');
                $('#save_per').prop('disabled', false);	
                window.location.reload();                
            } else{
            	$('#save_per').prop('disabled', false);	
            	alert(data);
            }
                
        }).fail(function(xhr, textStatus, errorThrown) {
        alert('Failed to fetch');
        $('#save_per').prop('disabled', false);	
        $("#WaitLoader").hide();
    });
}


$("#type_of_ro").change(function(){
	hidechekbox();
	var d = new Date();
	var year = d.getFullYear();
	var type_of_ro = $("#type_of_ro").find('option:selected').attr("name");
	
	if(type_of_ro != undefined){
		var roNo = year+"-"+type_of_ro+"-";	
		$.post("generatemmsRONo?"+key+"="+value,{roNo:roNo}, function(j) {
			$("#ro_no").val(j[0]);
		});
	}else{
		$("#ro_no").val("");
	}
	
	
});

function getCommand(arm){
	debugger;
	document.getElementById('comd_div').innerHTML = '';

	arm_code = arm;
	var force_type="";	
	$.post("getcomndList?" + key + "=" + value, {
		arm_code : arm_code,
		force_type:force_type
	}, function(j) {
		var options = '<option value="' + "0" + '">' + ""
		+ '</option>';
		if (j != "") {	

			options='';
			for (var i = 0; i < j.length; i++) {

			  options += '<label for="multicomd' + (i + 1) + '" class="comd_list"><input type="checkbox" class="corpsCheckBox" onclick="onChangeCorps();" name="multicomd' + (i + 1) + '" value="' + j[i][0] + '" /> ' + j[i][1] + ' </label>';

			
			
				
			}
			
		
		}
		$("#comd_div").html(options);
	});
	}
function filterOptions(a,b) {
    var searchBox = document.getElementById(a);
    var checkboxes = document.querySelectorAll(b);

    checkboxes.forEach(function (checkbox) {
            var labelText = checkbox.textContent || checkbox.innerText;
            var shouldShow = labelText.toLowerCase().includes(searchBox.value.toLowerCase());
            checkbox.style.display = shouldShow ? 'block' : 'none';
            
    });
    searchBox.value = searchBox.value.toUpperCase();
}

function hidechekbox(){
	$("#checkboxesprf").hide();
	$("#checkboxes").hide();
	$("#checkboxescomd").hide();
	$("#checkboxeswpncat").hide();
	$("#checkboxescorps").hide();
	$("#checkboxesdivision").hide();		
	$("#checkboxesunit").hide();
	$("#checkboxesbde").hide();
}

$("#searchInput").on("keyup", function() {					
	
    var search = $(this).val().toLowerCase().trim();

    $.post("get_item_depowise?" + key + "=" + value, {	    	
        selectedPRFs: selectedPRFs.join(","),
        search:search
    }, function(j) {
        var currentDepot = null; 
        var tbody = $("#depo_tbl tbody");	  
        tbody.empty();
        for (var i = 0; i < j.length; i++) {			
            if (j[i].depot_sus !== currentDepot) {
               
            	 var depotRow = $("<tr>");
                 var checkboxCell = $("<td>").append("<input type='checkbox' class='depot-checkbox' data-depot-sus='" + j[i].depot_sus + "'>");
                 var unitNameCell = $("<td colspan='2' style='font-weight: bold;'>" + j[i].unit_name + "</td>");

                 depotRow.append(checkboxCell);
                 depotRow.append(unitNameCell);

                 tbody.append(depotRow);
                 currentDepot = j[i].depot_sus;
            }
            
        
            var newRow = $("<tr>");
            newRow.append("<td style='text-align: left; background-color: white;'>" + ""+ "</td>");
            newRow.append("<td style='text-align: left; background-color: white;'>" + j[i].nomen + "</td>");
            newRow.append("<td style='text-align: center; font-weight: bold; background-color: white; '>" + j[i].depot_stock + "</td>");
            newRow.append("<td style='display: none;' id='" + j[i].depot_sus + j[i].census_no  + "' value='" + j[i].depot_stock + "'>" + j[i].depot_stock + "</td>");

            tbody.append(newRow);
        }
    });
    
});
</script>