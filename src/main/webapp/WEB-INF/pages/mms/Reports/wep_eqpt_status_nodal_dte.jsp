<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<link href="js/cue/cueWatermark.css" rel="Stylesheet"></link>

<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.min.js"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/jquery.mockjax.min.js"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.mockjax.js"></script>



<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<!-- new datatables -->
<link rel="stylesheet" href="js/datatable/css/datatables.min.css">
<script type="text/javascript"
	src="js/datatable/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/datatable/js/jquery.mockjax.js"></script>

<!-- resizable_col -->
<link rel="stylesheet"
	href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js"
	type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<script src="js/upload_xls/xlsx.full.min.js"></script>
<style type="text/css">
.multipleSelection {
	width: 100%;
	/*             background-color: #FFFFFF !important; */
	position: relative;
	display: inline-block;
	transition: border-color .15s ease-in-out, box-shadow .15s ease-in-out
		!important;
	border-radius: .2rem !important;
	font-size: .875rem !important;
	line-height: 1.5 !important;
	color: #495057 !important;
	background-color: #fff !important;
	background-clip: padding-box !important;
	border: 1px solid #ced4da !important;
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
					<h5>WPNS AND EQPTS Detls : NODAL DTE</h5>
				</div>
				<div class="card-body">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">*</strong> Nodal Dte</label>
								</div>
								<div class="col-12 col-md-8">
									<select id="line_dte_sus" name="line_dte_sus"
										class="form-control-sm form-control"
										onchange="getwpncatList(this.value)"
										onclick="getwpncatList(this.value)">
										${selectLine_dte}
										<c:forEach var="item" items="${getLine_DteList}"
											varStatus="num">
											<option value="${item.line_dte_sus}"
												name="${item.line_dte_name}">${item.line_dte_name}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"></strong> WPN CAT</label>
								</div>
								<div class="col-12 col-md-8">

									<select id="type_wep_eqpt" class="form-control-sm form-control"
										style="width: 100%" onchange="getPRFList(this.value)">
									</select>
								</div>
							</div>
						</div>
					</div>






					<div class="col-md-12 "
						style='background-color: mistyrose; padding: 7px;'>
						<div class="col-md-5" align="left">
							<b><input type=checkbox id='nSelAll' name='tregn'
								onclick="callsetall();">&nbsp;Select all (<span
								id="sregn" style='font-size: 14px;'>0</span>)</b>&nbsp;&nbsp; <input
								id="InputSearch" type="text"
								placeholder="Search Item Nomenclature .." size="20">
						</div>
						<div class="col-md-5" align="right">
							<b>Selected Item Nomenclature - <span id="tregn"
								style='font-size: 14px;'>0</span></b>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6"
							style="height: 200px; overflow: auto; width: 99%; border: 1px solid #000; text-align: left;"
							id="srctable"></div>
						<div class="col-md-6"
							style="height: 200px; overflow: auto; width: 99%; border: 1px solid #000; text-align: left;"
							id="tartable"></div>
						<input type="hidden" id="c_val" name="c_val" value="0"> <input
							type="hidden" id="item_no_list" name="item_no_list"
							placeholder="" class="form-control-sm nrform-control"> <input
							type="hidden" id="item_name_list" name="item_name_list"
							placeholder="" class="form-control-sm nrform-control">
					</div>
					<div class="col-md-12">
						<br>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"> Command</label>
									</div>
									<div class="col-12 col-md-8">
										<select id="cont_comd" name="cont_comd"
											class="form-control-sm form-control"> ${selectcomd}
											<c:forEach var="item" items="${getCommandList}"
												varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class="form-control-label"> Corps</label>
									</div>
									<div class="col-12 col-md-8">
										<select id="cont_corps" name="cont_corps"
											class="form-control-sm form-control">
											${selectcorps}
											<c:forEach var="item" items="${getCorpsList}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"> Division</label>
								</div>
								<div class="col-12 col-md-8">
									<select id="cont_div" name="cont_div"
										class="form-control-sm form-control"> ${selectDiv}
										<c:forEach var="item" items="${getDivList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"> Brigade</label>
								</div>
								<div class="col-12 col-md-8">
									<select id="cont_bde" name="cont_bde"
										class="form-control-sm form-control"> ${selectBde}
										<c:forEach var="item" items="${getBdeList}" varStatus="num">
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
								<div class="col col-md-4">
									<label class=" form-control-label">Unit Name </label>
								</div>
								<div class="col-12 col-md-8">
									<textarea id="unit_name" name="unit_name"
										class="form-control autocomplete" style="font-size: 12px;"
										autocomplete="off" maxlength="100"
										placeholder="select Unit Name">${unit_name}</textarea>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">SUS No</label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="sus_no" value='${sus_no}' name="sus_no"
										class="form-control autocomplete" maxlength="8"
										autocomplete="off" placeholder="Select SUS No">
								</div>
							</div>
						</div>
					</div>
				</div>


				<div class="form-control card-footer" align="center" id="buttonDiv">
					<a href="wep_eqpt_status" class="btn btn-success btn-sm"
						style="border-radius: 5px;">Clear</a>
					<!-- <button class="btn btn-primary btn-sm" onclick="return Search();" style="border-radius: 5px;">Search</button> -->
					<button id="btn_search" class="btn btn-primary btn-sm"
						style="border-radius: 5px;">Search</button>
					<i class="fa fa-download"></i><input type="button" id="exportId"
						class="btn btn-sm btn_report"
						style="background-color: #e37c22; color: white;" value="Export"
						onclick="exportToExcel('EqptStatusTbl', 'wpns_and_eqpts_notdal_dt')">

				</div>
				<div class="card-body">
					<div class="col-md-12">
						<div class="watermarked" data-watermark="" id="divwatermark">
							<span id="ip"></span>
							<table id="EqptStatusTbl" style="width: 100%; display: none;"
								class="table no-margin table-striped">
								<thead>
									<tr>

										<th rowspan="2" style="text-align: center; width: 3%;">Ser
											No</th>
										<th rowspan="2" style="text-align: center; width: 6%;">Comd</th>
										<th rowspan="2" style="text-align: center; width: 9%;">Corps</th>
										<th rowspan="2" style="text-align: center; width: 9%;">Division</th>
										<th rowspan="2" style="text-align: center; width: 9%;">Brigade</th>
										<th rowspan="2" style="text-align: center; width: 10%;">Unit
											Name</th>
										<th rowspan="2" style="text-align: center; width: 10%;">Line
											Dte</th>
										<th rowspan="2" style="text-align: center; width: 10%;">Item
											Name</th>
										<th rowspan="2" style="text-align: center; width: 7%;">UE</th>
										<th colspan="6" style="text-align: center; width: 30%;">UH</th>
										<th rowspan="2" style="text-align: center; width: 6%;">Total
											UH</th>
									</tr>
									<tr>
										<th style="text-align: center;">Against UE</th>
										<th style="text-align: center;">On Loan</th>
										<th style="text-align: center;">Sector Store</th>
										<th style="text-align: center;">ACSFP</th>
										<th style="text-align: center;">WWR Unit</th>
										<th style="text-align: center;">WWR Depot</th>
									</tr>
								</thead>
								<tbody style="font-size: 11px; font-weight: bold;"></tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="animated fadeIn" id="printDiv" style="display: none;">
	<div class="">
		<div class="container" align="center">
			<div class="col-md-12" align="center">
				<input type="button" class="btn btn-primary btn-sm" value="Print"
					onclick="printDiv('printableArea')">
			</div>
		</div>
	</div>
</div>

<script>
	function showCheckboxes() {
		var checkboxes = document.getElementById("checkboxes");
		$("#checkboxes").toggle();
		$("#user_role_id").val('');
		$('.prf_code').each(function() {
			$(this).show()
		})
	}
	$(document).ready(
			function() {
				$("div#divwatermark").val('').addClass('watermarked');
				//watermarkreport();
				var a = '${getLine_Dtesize}';
				if (parseInt(a) == 1) {
					var val = $("#line_dte_sus").val();
					getwpncatList(val);
				}
				//sus_nowithUnitName_list();
				$("#InputSearch").on(
						"keyup",
						function() {
							var value = $(this).val().toLowerCase();
							$("#srctable tr").filter(
									function() {
										$(this).toggle(
												$(this).text().toLowerCase()
														.indexOf(value) > -1)
									});
						});

				var table = "";
				$('#btn_search').on(
						'click',
						function() {

							if ($("#line_dte_sus").val() == "0") {
								alert("Please select Nodal Dte.");
							} else if ($("#prf_code").val() == "0") {
								alert("Please Select WPN SUB CAT");
								$("#prf_code").focus();
							} else {
								if (table == "") {
									//$("#reportDiv").show();
									$('#EqptStatusTbl').show();
									mockjax1('EqptStatusTbl');
									table = dataTable('EqptStatusTbl', [ 0, 7,
											8, 9, 10, 11, 12, 13, 14 ], []);
								} else {
									table.ajax.reload();
								}
							}
						});

				var select = '<option value="' + "0" + '">' + "--Select--"
						+ '</option>';
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
					}
				});
				$('select#cont_div').change(function() {
					var fcode = this.value;
					if (fcode == "0") {
						$("select#cont_bde").html(select)
					} else {
						$("select#cont_bde").html(select)
						getCONTBde(fcode);
					}
				});
				colAdj("EqptStatusTbl");
			});

	function sus_nowithUnitName_list() {
		var line_dte_sus = $("#line_dte_sus").val();
		$.post("getMMSUnitNameWITHSUSNo?" + key + "=" + value, {
			line_dte_sus : line_dte_sus
		}, function(data) {
		}).done(function(data) {
			alert(data);
			if (data == "") {
				$("#srctable").empty();
				$("#tartable").empty();
				//alert("No Regd Data Found");
			} else {
				$("#d_reg").show();
				$("#d_reg2").show();
				$("#d_reg3").show();
				$("#srctable").val(data);
				drawregn(data);
			}
		}).fail(function(xhr, textStatus, errorThrown) {
		});
	}
	function drawregn(j) {
		var ii = 0;
		$("#srctable").empty();
		$("#tartable").empty();
		for (var i = 0; i < j.length; i++) {
			var row = "<tr id='SRC"+j[i][1]+"' padding='5px;'>";
			row = row
					+ "<td>&nbsp;<input class='nrCheckBox' type=checkbox id='"
					+ j[i].item_code + "' name='" + j[i].item_type
					+ "' onclick='findselected();'>&nbsp;";
			row = row + j[i].item_code + " - " + j[i].item_type + "</td>";
			$("#srctable").append(row);
			ii = ii + 1;
		}
		$("#sregn").text(ii);
	}
	function callsetall() {
		var chkclk = $('#nSelAll').prop('checked');
		if (chkclk) {
			$('.nrCheckBox').prop('checked', true);

		} else {
			$('.nrCheckBox').prop('checked', false);
		}
		findselected();
	}
	function findselected() {
		$("#srctable tr").css('background-color', 'white');

		var nrSel = $('.nrCheckBox:checkbox:checked').map(function() {
			var bb = $(this).attr('id');
			$("#SRC" + bb).css('background-color', 'yellow');
			return bb;
		}).get();
		var b = nrSel.join(':');

		$("#c_val").val(nrSel.length);

		$("#item_no_list").val(b);

		var nrSel = $('.nrCheckBox:checkbox:checked').map(function() {
			var bb1 = $(this).attr('name');
			return bb1;
		}).get();
		var c = nrSel.join(':');
		$("#item_name_list").val(c);

		drawtregn(c);
	}
	function drawtregn(data) {
		var ii = 0;
		$("#tartable").empty();
		var datap = data.split(":");
		for (var i = 0; i < datap.length; i++) {
			var nkrow = "<tr id='tarTableTr' padding='5px;'>";
			nkrow = nkrow + "<td>&nbsp;&nbsp;";
			nkrow = nkrow + datap[i] + "</td>";
			$("#tartable").append(nkrow);
			ii = ii + 1;
		}
		$("#tregn").text(ii);
	}
	//260194
	function fnExcelReport() {
		if ($("#type_wep_eqpt").val() == "") {
			alert("Please select WPN CAT.");
		} else {
			var tab_text = "<table><tr>";
			var textRange;
			var j = 0;
			tab = document.getElementById('EqptStatusTbl');

			for (j = 0; j < tab.rows.length; j++) {
				tab_text = tab_text + tab.rows[j].innerHTML + "</tr>";
			}
			tab_text = tab_text + "</table>";
			var ua = window.navigator.userAgent;
			var msie = ua.indexOf("MSIE ");
			if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./)) {
				txtArea1.document.open("txt/html", "replace");
				txtArea1.document.write(tab_text);
				txtArea1.document.close();
				txtArea1.focus();
				sa = txtArea1.document.execCommand("SaveAs", true,
						"Say Thanks to Sumit.xls");
			} else
				sa = window.open('data:application/vnd.ms-excel,'
						+ encodeURIComponent(tab_text));
			return (sa);
		}
	}

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
						document.getElementById("unit_name").value = "";
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
												document
														.getElementById("sus_no").value = "";
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

	/////change by bisag

	function getwpncatList(val) {
		var options = '<option value="0">' + "--Select--" + '</option>';
		if (val != "") {
			$
					.post("getwpncatnodal_dte?" + key + "=" + value, {
						line_dte_sus : val
					})
					.done(
							function(j) {
								for (var i = 0; i < j.length; i++) {

									options += '<option value="'+j[i].item_group+'" name="' + j[i].item_group+ '" >'
											+ j[i].item_group + '</option>';
								}
								$("select#type_wep_eqpt").html(options);
							}).fail(function(xhr, textStatus, errorThrown) {
					});
		} else {
			$("select#type_wep_eqpt").html(options);
		}
	}

	function getPRFList(val) {
		var a = $("#line_dte_sus").val();
		if (val != "") {
			$.post("getPRFListbyItemGroup_nodal_dte?" + key + "=" + value, {
				type : val,
				dte : a
			}).done(function(data) {
				if (data == "") {
					$("#srctable").empty();
					$("#tartable").empty();
					$("#sregn").text(data.length);
					$("#tregn").text(data.length);
					//alert("No Regd Data Found");
				} else {
					$("#d_reg").show();
					$("#d_reg2").show();
					$("#d_reg3").show();
					$("#srctable").val(data);
					$("#tregn").text(0);
					drawregn(data);
				}
			}).fail(function(xhr, textStatus, errorThrown) {
			});
		}
	}
	function data(tableName) {
		jQuery("#WaitLoader").show();
		jsondata = [];
		// Default Parameter
		var table = $('#' + tableName).DataTable();
		var info = table.page.info();
		var currentPage = info.page;
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = order[0][0] + 1;// for Colunm Id wise Ordering
		//var orderColunm = $(table.column(order[0][0]).header()).html().toLowerCase(); // for Colunm Name wise Ordering
		var orderType = order[0][1];
		// Default Parameter
		// No Change

		// Advanced Search Parameter

		var line_dte_sus = $("#line_dte_sus").val();
		var type_wep_eqpt = $("#type_wep_eqpt").val();
		var item_no_list = $("#item_no_list").val();
		//alert(item_no_list);
		var cont_comd = $("#cont_comd").val();
		var cont_corps = $("#cont_corps").val();
		var cont_div = $("#cont_div").val();
		var cont_bde = $("#cont_bde").val();
		var sus_no = $("#sus_no").val();
		// Advanced Search Parameter
		$.post("wep_eqpt_statusList_nodal_dte?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			orderColunm : orderColunm,
			orderType : orderType,
			Search : Search,
			line_dte_sus : line_dte_sus,
			type_wep_eqpt : type_wep_eqpt,
			item_no_list : item_no_list,
			comd : cont_comd,
			corps : cont_corps,
			div : cont_div,
			bde : cont_bde,
			sus_no : sus_no
		}, function(j) {
			for (var i = 0; i < j.length; i++) {

				jsondata
						.push([ (i + 1), j[i].comd, j[i].corps, j[i].div,
								j[i].bde, j[i].unit_name, j[i].line_dte_name,
								j[i].item_type, j[i].ue, j[i].uh, j[i].ls,
								j[i].ss, j[i].ac, j[i].wwr_unit,
								j[i].wwr_depot, j[i].total_uh ]);
			}

		});
		$.post("wep_eqpt_statusCount_nodal_dte?" + key + "=" + value, {
			Search : Search,
			line_dte_sus : line_dte_sus,
			type_wep_eqpt : type_wep_eqpt,
			item_no_list : item_no_list,
			comd : cont_comd,
			corps : cont_corps,
			div : cont_div,
			bde : cont_bde,
			sus_no : sus_no
		}, function(j) {
			FilteredRecords = j;
		});
		jQuery("#WaitLoader").hide();
	}

	function exportToExcel(tableId, fileName) {
		var table = document.getElementById(tableId);
		var ws = XLSX.utils.table_to_sheet(table);
		var wb = XLSX.utils.book_new();
		XLSX.utils.book_append_sheet(wb, ws, "wpns_eqpts_nodal_dt");
		XLSX.writeFile(wb, fileName + ".xlsx");
	}
</script>