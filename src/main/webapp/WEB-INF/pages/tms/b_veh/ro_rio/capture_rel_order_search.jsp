<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script>

<form name="search_capture_rel_order" id="search_capture_rel_order">
	<div class="animated fadeIn" style="display: block;">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5>SEARCH RELEASE ORDER</h5>
				</div>
				<div class="card-body card-block">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">SUS No</label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="sus_no" name="sus_no"
										class="form-control autocomplete" autocomplete="off"
										maxlength="8">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">Unit Name</label>
								</div>
								<div class="col-12 col-md-8">
									<textarea id="unit_name" name="unit_name"
										class="form-control autocomplete" style="font-size: 11px;"
										autocomplete="off" maxlength="100"></textarea>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">*</strong> Depot SUS No </label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="depot_sus_no" name="depot_sus_no"
										maxlength="8" class="form-control autocomplete"
										autocomplete="off">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">*</strong> Depot Name </label>
								</div>
								<div class="col-12 col-md-8">
									<textarea id="depot_name" name="depot_name" maxlength="100"
										class="form-control autocomplete" autocomplete="off"></textarea>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">*</strong>From </label>
								</div>
								<div class="col-12 col-md-8">
									<input type="date" id="issue_date" name="issue_date"
										placeholder="" class="form-control autocomplete"
										autocomplete="off">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">To </label>
								</div>
								<div class="col-12 col-md-8">
									<input type="date" id="to_date" name="to_date" placeholder=""
										class="form-control autocomplete" autocomplete="off"
										onchange="return checkdate(this)">
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">Type of Issue </label>
								</div>
								<div class="col-12 col-md-8">
									<select name="type_of_issue" id="type_of_issue"
										class="form-control-sm form-control">
										<option value="">--Select--</option>
										<c:forEach var="item" items="${getMvcrpartCissuetypeList}"
											varStatus="num">
											<c:choose>
												<c:when test="${item[0] =='3'}">
													<option value="${item[0]}" style="color: red">${item[1]}</option>
												</c:when>
												<c:otherwise>
													<option value="${item[0]}">${item[1]}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"> Type of RO </label>
								</div>
								<!-- priti 25-08-2020 -->
								<div class="col-12 col-md-8">
									<select name="type_of_ro" id="type_of_ro"
										class="form-control-sm form-control ">
										<option value="">--Select--</option>
										<option value="1">RO</option>
										<option value="3">Loan RO</option>
										<option value="4">NRU RO</option>
										<option value="5">Conditional RO</option>
									</select>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;">*</strong> Status</label>
								</div>
								<div class="col-12 col-md-8">
									<select name="status" id="status"
										class="form-control-sm form-control">
										<option value="0">Pending</option>
										<option value="1">Approved</option>
										<option value="2">Rejected</option>
										<option value="3">Cancel</option>
									</select>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="form-control card-footer" align="center">
					<a href="search_capture_rel_order" class="btn btn-success btn-sm">Clear</a>
					<button type="button" class="btn btn-primary btn-sm"
						onclick="return Search();">Search</button>
					<button type="button" id="printbtn" class="btn btn-primary btn-sm"
						onclick="return printDiv('printableArea')">Print</button>
					<i class="fa fa-download"></i><input type="button" id="exportId"
						class="btn btn-sm btn_report"
						style="background-color: #e37c22; color: white;" value="Export"
						onclick="fnExcelReport();">
				</div>
			</div>
		</div>
	</div>

	<div class="animated fadeIn" id="AllBTN">
		<div class="">
			<div class="container" align="center">
				<div class="card">
					<div class="card-body card-block">
						<div class="col-md-12" align="right">
							<div class="col-md-6"></div>
							<div class="col-md-2">${appButton}</div>
							<div class="col-md-2">${rejectButton}</div>
							<div class="col-md-2">${deleteButton}</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="animated fadeIn" id="printableArea">
		<div class="">
			<div class="col-md-12">
				<div class="card">
					<div class="card-header" id="printableArea3" style="display: none;">
						<table class="col-md-12">
							<tr>
								<td align="left"><img
									src="js/miso/images/indianarmy_smrm5aaa.jpg"
									style="height: 50px;"></td>
								<td align="center">
									<h5>RELEASE ORDER</h5>
								</td>
								<td align="right"><img src="js/miso/images/dgis-logo.png"
									style="max-width: 155px; height: 50px;"></td>
							</tr>
						</table>
					</div>
					<div class="col s12" id="tableshow">
						<div class="animated fadeIn">
							<div align="right">
								<h6>Total Count : ${list.size()}</h6>
							</div>
							<div id="divShow" style="display: block;"></div>
							<div class="watermarked" data-watermark="" id="divwatermark"
								style="display: block;">
								<span id="ip"></span>
								<table id="getSearchReport"
									class="table no-margin table-striped  table-hover  table-bordered report_print">
									<thead>
										<tr>
											<th id="thnSelAll" style="text-align: center; width: 2%;"><input
												type=checkbox id='nSelAll' name='tregn'
												onclick='setselectall();'></th>
											<th style="text-align: center; width: 9%;">RO No</th>
											<th style="text-align: center; width: 15%;">Unit Name</th>
											<th style="text-align: center; width: 5%;">Comd Name</th>
											<th style="text-align: center; width: 15%;">Depot Name</th>
											<!-- <th style="text-align: center;width: 5%;">MCT</th> -->
											<th style="text-align: center; width: 25%;">Nomenclature</th>
											<th style="text-align: center; width: 3%;">Qty</th>
											<th style="text-align: center; width: 7%;">Type Of Issue</th>
											<th style="text-align: center; width: 7%;">Type Of
												Vehicle</th>
											<th style="text-align: center; width: 5%;">Date Of
												Submission</th>
											<th id="validUptoHeader"
												style="text-align: center; width: 5%;">Valid Upto</th>
											<th style="text-align: center; width: 6%;">Inlieu
												Nomenclature</th>
											<th style="text-align: center; width: 10%;">Action</th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${list.size() == 0}">
											<tr>
												<td colspan="11" align="center" style="color: red;">
													Data Not Available</td>
											</tr>
										</c:if>
										<c:forEach items="${list}" var="list" varStatus="num">
											<tr>
												<td id="tdnSelAll" style="text-align: center; width: 2%;">${list[14]}</td>
												<td style="text-align: center; width: 9%;">${list[0]}</td>
												<td style="text-align: center; width: 15%;">${list[1]}</td>
												<td style="text-align: center; width: 5%;">${list[2]}</td>
												<td style="text-align: center; width: 15%;">${list[10]}</td>
												<%-- <td style="text-align: center;width: 5%;">${list[3]}</td> --%>
												<td style="text-align: center; width: 25%;">${list[4]}</td>
												<td style="text-align: center; width: 3%;">${list[5]}</td>
												<td style="text-align: center; width: 7%;">${list[6]}</td>
												<td style="text-align: center; width: 7%;">${list[11]}</td>
												<td style="text-align: center; width: 5%;">${list[7]}</td>
												<td id="validUptoHeader1"
													style="text-align: center; width: 5%;">${list[12]}</td>
												<td style="text-align: center; width: 6%;">${list[8]}</td>
												<td style="text-align: center; width: 10%;">${list[13]}
													${list[15]} ${list[16]}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>



	<div class="modal-open modal" id="getRODetails"
		style="overflow-y: auto;">
		<div class="modal-dialog" style="max-width: 900px;">
			<div class="modal-content">
				<div id="getRODetailsDiv">
					<div class="modal-header">
						<table style="width: 100%;">
							<tr style="height: 30px;">
								<td align="left" width="33.33%"><img
									src="js/miso/images/indianarmy_smrm5aaa.jpg"
									style="height: 50px;"></td>
								<td align="center" width="33.33%">
									<h5>RO DETAILS</h5>
								</td>
								<td align="right" width="33.33%"><img
									src="js/miso/images/dgis-logo.png"
									style="max-width: 155px; height: 50px;"></td>
							</tr>
						</table>
					</div>
					<div class="modal-body">
						<div>
							<table style="width: 100%;">
								<tr style="height: 30px;">
									<td width="20%"><b>RO No </b></td>
									<td width="30%">: <span id="vro_no"></span></td>
									<td width="20%"><b style="color: red">Issue Date</b></td>
									<td width="30%">: <span id="vissue_date" style="color: red"></span></td>
								</tr>
								<tr style="height: 30px;">
									<td width="20%"><b>Unit name</b></td>
									<td width="30%">: <span id="vunit_name"></span></td>
									<td width="20%"><b>Comd name</b></td>
									<td width="30%">: <span id="vcommand_name"></span></td>
								</tr>
								<tr style="height: 30px;">
									<td width="20%"><b>Depot name</b></td>
									<td width="30%">: <span id="vdepot_name"></span></td>
									<td width="20%"><b>nrs</b></td>
									<td width="30%">: <span id="vnrs"></span></td>
								</tr>
								<tr style="height: 30px;">
									<td width="20%"><b>Type of Issue</b></td>
									<td width="30%">: <span id="vtype_of_issue"></span></td>
									<td width="20%"><b>Other Issue Type</b></td>
									<td width="30%">: <span id="vother_issue_type"></span></td>
								</tr>
								<tr style="height: 30px;">
									<td width="20%"><b>Issue Stock</b></td>
									<td width="30%">: <span id="vissue_stock"></span></td>
									<td width="20%"><b>Type of Vehicle</b></td>
									<td width="30%">: <span id="vtype_of_vehicle"></span></td>
								</tr>
								<tr style="height: 30px;">
									<td width="20%"><b>Loan Authority</b></td>
									<td width="30%">: <span id="vloan_auth"></span></td>
									<td width="20%"><b>Loan Duration (years)</b></td>
									<td width="30%">: <span id="vloan_duration"></span></td>
								</tr>
								<tr style="height: 30px;">
									<td width="20%"><b>MCT</b></td>
									<td width="30%">: <span id="vmct"></span></td>
									<td width="20%"><b>Nomenclature</b></td>
									<td width="30%">: <span id="vstd_nomclature"></span></td>
								</tr>
								<tr style="height: 30px;">
									<td width="20%"><b>In Lieu MCT No.</b></td>
									<td width="30%">: <span id="vinliuemct"></span></td>
									<td width="20%"><b>In Lieu Nomenclature</b></td>
									<td width="30%">: <span id="vstd_nomclature_inlieu"></span></td>
								</tr>
								<tr style="height: 30px;">
									<td width="20%"><b>Qty</b></td>
									<td width="30%">: <span id="vquantity"></span></td>
									<td width="20%"><b>Accounting</b></td>
									<td width="30%">: <span id="vaccounting"></span></td>
								</tr>
								<tr style="height: 30px;">
									<td width="20%"><b>Issue Precedence</b></td>
									<td width="30%">: <span id="vissue_precedence"></span></td>
									<td width="20%"><b>Remarks</b></td>
									<td width="30%">: <span id="vremarks"></span></td>
								</tr>
							<!-- 	<tr style="height: 30px;">
									<td width="20%"><b style="color: red">Valid Upto</b></td>
									<td width="30%">: <span style="color: red" id="vvalid_upto"></span></td>
									<td width="20%"><b style="color: red">Validity Period</b></td>
									<td width="30%">: <span id="" style="color: red">45 Days</span></td>
								</tr> -->
							</table>
						</div>
						<div style="border: 1px solid black;">
							<table class="col-md-12">
								<tbody style="overflow: hidden;">
									<tr>
										<td style="width: 100%;" align="center"><label
											style="text-decoration: underline;">NOTES</label></td>
									</tr>
									<tr>
										<td style="width: 100%;">&emsp; 1.This RO is valid for a
											pd of 45 days.No further extension will be gtd on expiry of
											RO</td>
									</tr>
									<tr>
										<td style="width: 100%;">&emsp; 2.Units to apch MISO[TMS]
											& Concerned Depot for org collection based on this RO</td>
									</tr>
									<tr>
										<td style="width: 100%;">&emsp; 3. This RO be treated as
											Mov Sanction for mov of all vehs beyond 960 kms</td>
									</tr>
									<tr>
										<td style="width: 100%;" align="right"><label
											style="text-decoration: underline; margin-right: 200px;">Digitally
												Signed by <span id="vuserid"></span>
										</label></td>
									</tr>
								</tbody>
							</table>
						</div>



					</div>
				</div>
				<div class="modal-footer" align="center">
					<input type="button" class="btn btn-primary btn-sm" value="Print"
						onclick="printDiv1('getRODetailsDiv')">
					<button type="button" class="btn btn-danger btn-sm"
						onclick="closeModal1();">Close</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal-open modal" id="getRODetail12"
		style="overflow-y: auto;">
		<div class="modal-dialog" style="max-width: 900px;">
			<div class="modal-content">
				<div id="getRODetailsDiv1">
					<div class="modal-header">
						<table style="width: 100%;">
							<tr style="height: 30px;">
								<td align="left" width="33.33%"><img
									src="js/miso/images/indianarmy_smrm5aaa.jpg"
									style="height: 50px;"></td>
								<td align="center" width="33.33%">
									<h5>EDIT RO DETAILS</h5>
								</td>
								<td align="right" width="33.33%"><img
									src="js/miso/images/dgis-logo.png"
									style="max-width: 155px; height: 50px;"></td>
							</tr>
						</table>
					</div>
					<div class="modal-body">
						<div>
							<table style="width: 100%;">
								<tr style="height: 30px;">
									<td width="20%"><b>RO No </b></td>
									<td width="30%">: <span id="vro_no1"></span></td>
									<td width="20%"><b style="color: red">Issue Date</b></td>
									<td width="30%">: <span id="vissue_date1"
										style="color: red"></span></td>
								</tr>
								<tr style="height: 30px;">
									<td width="20%"><b>Unit name</b></td>
									<td width="30%">: <span id="vunit_name1"></span></td>
									<td width="20%"><b>Comd name</b></td>
									<td width="30%">: <span id="vcommand_name1"></span></td>
								</tr>
								<tr style="height: 30px;">
									<td width="20%"><b>Depot name</b></td>
									<td width="30%">: <span id="vdepot_name1"></span></td>
									<td width="20%"><b>nrs</b></td>
									<td width="30%">: <span id="vnrs1"></span></td>
								</tr>
								<tr style="height: 30px;">
									<td width="20%"><b>Type of Issue</b></td>
									<td width="30%">: <span id="vtype_of_issue1"></span></td>
									<td width="20%"><b>Other Issue Type</b></td>
									<td width="30%">: <span id="vother_issue_type1"></span></td>
								</tr>
								<tr style="height: 30px;">
									<td width="20%"><b>Issue Stock</b></td>
									<td width="30%">: <span id="vissue_stock1"></span></td>
									<td width="20%"><b>Type of Vehicle</b></td>
									<td width="30%">: <span id="vtype_of_vehicle1"></span></td>
								</tr>
								<tr style="height: 30px;">
									<td width="20%"><b>Loan Authority</b></td>
									<td width="30%">: <span id="vloan_auth"></span></td>
									<td width="20%"><b>Loan Duration (years)</b></td>
									<td width="30%">: <span id="vloan_duration"></span></td>
								</tr>
								<tr style="height: 30px;">
									<td width="20%"><b>MCT</b></td>
									<td width="30%">: <span id="vmct1"></span></td>
									<td width="20%"><b>Nomenclature</b></td>
									<td width="30%">: <span id="vstd_nomclature1"></span></td>
								</tr>
								<tr style="height: 30px;">
									<td width="20%"><b>In Lieu MCT No.</b></td>
									<td width="30%">: <span id="vinliuemct1"></span></td>
									<td width="20%"><b>In Lieu Nomenclature</b></td>
									<td width="30%">: <span id="vstd_nomclature_inlieu1"></span></td>
								</tr>
								<tr style="height: 30px;">
									<td width="20%"><b>Qty</b></td>
									<td width="30%">: <span id="vquantity1"></span></td>
									<td width="20%"><b>Accounting</b></td>
									<td width="30%">: <span id="vaccounting1"></span></td>
								</tr>

								<tr style="height: 30px;">
									<td width="20%"><b>Issue Precedence</b></td>
									<td width="30%">: <span id="vissue_precedence1"></span></td>
									<td width="20%"><b>Remarks</b></td>
									<td width="30%">: <span id="vremarks1"></span></td>
								</tr>
								<tr style="height: 30px;">
									<td width="20%"><b style="color: red">Valid Upto</b></td>
									<td width="30%">: <span id="vvalid_upto1"
										style="color: red"></span></td>
									<td width="20%"><b style="color: red">Valid Period</b></td>
									<td width="30%">: <span id=" " style="color: red">45
											Days</span></td>
								</tr>
								<tr style="height: 30px;">
									<td width="20%"><b style="color: red">Extend Upto</b></td>
									<td width="30%">: <input type="date" id="extended_date1"></td>
								</tr>
								
							</table>
							 <input type="hidden" id="extended_on1">
							  <input type="hidden" id="approved_dt1">
						</div>
					</div>
				</div>
				<div class="modal-footer" align="center">
					<input type="button" class="btn btn-primary btn-sm" value="Update"
						onclick="update()">
					<button type="button" class="btn btn-danger btn-sm"
						onclick="closeModall();">Close</button>
				</div>
			</div>
		</div>
	</div>


	<div class="modal-open modal" id="cancleRODetails"
		style="overflow-y: auto;">
		<div class="modal-dialog" style="max-width: 900px;">
			<div class="modal-content">
				<div id="cancleRODetailsDiv">
					<div class="modal-body">
						<input type="hidden" id="cancleRoNo" name="cancleRoNo"> <input
							type="hidden" id="cancleid" name="cancleid"> <input
							type="hidden" id="cancletype_of_veh" name="cancletype_of_veh">
						<table style="width: 100%;">
							<tr>
								<th>RO No :</th>
								<th><span id="cancleRO"></span></th>
							</tr>
							<tr>
								<th>Remarks <strong style="color: red;">* </strong></th>
								<td><textarea id="cancleRemarks" name="cancleRemarks"
										class="form-control"></textarea></td>
							</tr>
						</table>
					</div>
				</div>
				<div class="modal-footer" align="center">
					<input type="button" class="btn btn-primary btn-sm"
						value="RO Cancel" onclick="return finalCancelRoDetails()">

					<button type="button" class="btn btn-danger btn-sm"
						onclick="closeModalCancleRo();">Close</button>
				</div>
			</div>
		</div>
	</div>

</form>
<c:url value="getAttributeFromROMainAndVehDtl" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="code_value1">
	<input type="hidden" name="sus_no2" id="sus_no2" value="0" />
	<input type="hidden" name="unit_name2" id="unit_name2" value="0" />
	<input type="hidden" name="depot_sus_no2" id="depot_sus_no2" value="0" />
	<input type="hidden" name="depot_name2" id="depot_name2" value="0" />
	<input type="hidden" name="to_date2" id="to_date2" value="0" />
	<input type="hidden" name="type_of_ro2" id="type_of_ro2" value="0" />
	<input type="hidden" name="issue_date2" id="issue_date2" value="0" />
	<input type="hidden" name="status2" id="status2" value="0" />
	<input type="hidden" name="type_of_issue2" id="type_of_issue2"
		value="0" />
</form:form>

<c:url value="Update" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="code_value1">
	<input type="hidden" name="vro_no1" id="vro_no1" value="0" />
	<input type="hidden" name="extended_date1" id="extended_date1"
		value="0" />
</form:form>
<script>
	function cancelRoDetails(id, ro_no, type_of_vehicle) {
		$("#cancleRO").text(ro_no);
		$("#cancleid").val(id);
		$("#cancleRoNo").val(ro_no);
		$("#cancletype_of_veh").val(type_of_vehicle);
		var modal = document.getElementById('cancleRODetails');
		modal.style.display = "block";
	}
	function closeModalCancleRo() {
		var modal = document.getElementById('cancleRODetails');
		modal.style.display = "none";
	}
	function finalCancelRoDetails() {
		var id = $("#cancleid").val();
		var ro_no = $("#cancleRoNo").val();
		var type_veh = $("#cancletype_of_veh").val();

		var remarks = $("#cancleRemarks").val();
		if (remarks == "") {
			alert("please enter Remarks");
			$("#cancleRemarks").fucos();
			return false;
		} else {
			jQuery.ajax({
				type : 'POST',
				url : "getCancelRO?" + key + "=" + value,
				data : {
					id : id,
					ro_no : ro_no,
					type_veh : type_veh,
					remarks : remarks
				},
				success : function(data) {
					alert(data[0].msg);
					if (data[0].msg == "RO Cancelled") {
						location.reload();
					}
				}
			});
		}
	}

	function printDiv1(divName) {
		let popupWinindow
		let innerContents = document.getElementById(divName).innerHTML;
		popupWinindow = window
				.open(
						'',
						'_blank',
						'width=600,height=700,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
		popupWinindow.document.open();
		popupWinindow.oncontextmenu = function() {
			return false;
		}
		popupWinindow.document
				.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><style> table td{font-size:10px; font-weight:normal !important;} table th{font-size:12px !important;} tbody th{font-size:10px; font-weight:normal !important;}</style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">'
						+ innerContents + '</html>');
		//popupWinindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/miso/assets/scss/style.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><style> table td{font-size:8px;} table th{font-size:9px !important;} </style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' +innerContents+  '</html>');
		watermarkreport();
		popupWinindow.document.close();
	}
	$(document).ready(function() {

		$("div#divwatermark").val('').addClass('watermarked');
		watermarkreport();
		if ('${list.size()}' != "") {
			$("#tableshow").show();
		} else {
			$("#tableshow").hide();
		}
		if ('${status}' == '1') {
			$("#validUptoHeader").show();
			$("#validUptoHeader1").show();
		} else {
			$("#validUptoHeader").hide();
			$("#validUptoHeader1").hide();
		}

		if ('${status}' != "") {
			$("#sus_no").val('${sus_no}');
			$("#unit_name").val('${unit_name}');
			$("#depot_sus_no").val('${depot_sus_no}');
			$("#depot_name").val('${depot_name}');
			$("#to_date").val('${to_date}');
			$("#type_of_ro").val('${type_of_ro}');
			$("#issue_date").val('${issue_date}');
			$("#status").val('${status}');
			$("select#type_of_issue").val('${type_of_issue}');
		}
	});

	function closeModal1() {
		var modal = document.getElementById('getRODetails');
		modal.style.display = "none";
	}
	function closeModall() {
		var modal = document.getElementById('getRODetail12');
		modal.style.display = "none";
	}
	function viewRoDetails(id) {
		var modal = document.getElementById('getRODetails');
		modal.style.display = "block";

		jQuery.ajax({
			type : 'POST',
			url : "getRODetails?" + key + "=" + value,
			data : {
				id : id
			},
			success : function(data) {
				if (data.length != 0) {
					$("#vro_no").text(data[0].ro_no);
					$("#vunit_name").text(data[0].unit_name);
					$("#vcommand_name").text(data[0].command_name);
					$("#vdepot_name").text(data[0].depot_name);
					$("#vnrs").text(data[0].nrs);
					$("#vtype_of_issue").text(data[0].type_of_issue);
					$("#vissue_stock").text(data[0].issue_stock);
					$("#vtype_of_vehicle").text(data[0].type_of_vehicle);
					$("#vissue_date").text(data[0].issue_date);
					$("#vother_issue_type").text(data[0].other_issue_type);
					$("#vloan_auth").text(data[0].loan_auth);
					$("#vloan_duration").text(data[0].loan_duration);
					$("#vmct").text(data[0].mct);
					$("#vstd_nomclature").text(data[0].std_nomclature);
					$("#vinliuemct").text(data[0].inliuemct);
					$("#vstd_nomclature_inlieu").text(
							data[0].std_nomclature_inlieu);
					$("#vquantity").text(data[0].quantity);
					$("#vaccounting").text(data[0].accounting);
					$("#vissue_precedence").text(data[0].issue_precedence);
					$("#vremarks").text(data[0].remarks);
					$("#vuserid").text(data[0].modify_by);
					$('#vvalid_upto').text(data[0].valid_upto);

				}
			}
		});
	}

	$(function() {
		$("#sus_no").keypress(function() {
			var sus_no = this.value;
			var susNoAuto = $("#sus_no");
			susNoAuto.autocomplete({
				source : function(request, response) {
					$.ajax({
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
					}, function(data) {
					}).done(function(data) {
						var length = data.length - 1;
						var enc = data[length].substring(0, 16);
						$("#unit_name").val(dec(enc, data[0]));
					}).fail(function(xhr, textStatus, errorThrown) {
					});
				}
			});
		});
		// Source Unit Name

		$("#unit_name")
				.keypress(
						function() {
							var unit_name = this.value;
							var susNoAuto = $("#unit_name");
							susNoAuto
									.autocomplete({
										source : function(request, response) {
											$
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
															}, function(data) {
															})
													.done(
															function(data) {
																var length = data.length - 1;
																var enc = data[length]
																		.substring(
																				0,
																				16);
																$("#sus_no")
																		.val(
																				dec(
																						enc,
																						data[0]));
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
	function printDiv(divName) {
		if ('${list.size()}' == 0) {
			alert("Data Not Available..");
			return false;
		}
		if ($("select#status").val() != "1") {
			alert("Please Select Approved Status.")
			$("select#status").focus();
			return false;
		}
		$("#printableArea").show();
		$("#printableArea3").show();

		let popupWinindow
		let innerContents = document.getElementById(divName).innerHTML;
		popupWinindow = window
				.open(
						'',
						'_blank',
						'width=600,height=700,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
		popupWinindow.document.open();
		popupWinindow.oncontextmenu = function() {
			return false;
		}
		//popupWinindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/miso/assets/scss/style.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><style> table td{font-size:8px;} table th{font-size:9px !important;} </style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' +innerContents+  '</html>');
		popupWinindow.document
				.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><style> table td{font-size:10px; font-weight:normal !important;} table th{font-size:12px !important;} tbody th{font-size:10px; font-weight:normal !important;}</style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">'
						+ innerContents + '</html>');

		///watermarkreport();
		popupWinindow.document.close();
		$("#printableArea3").hide();
	}
	function clearAll() {
		var tab = $("#getSearchReport > tbody");
		tab.empty();
		if (document.getElementById('tableshow').style.display == 'block') {
			document.getElementById('tableshow').style.display = 'none';
		}
		localStorage.clear();
		localStorage.Abandon();
	}
	function Search() {
		$("#sus_no2").val($("#sus_no").val());
		$("#unit_name2").val($("#unit_name").val());
		$("#depot_sus_no2").val($("#depot_sus_no").val());
		$("#depot_name2").val($("#depot_name").val());
		$("#to_date2").val($("#to_date").val());
		$("#type_of_ro2").val($("#type_of_ro").val());
		$("#type_of_issue2").val($("select#type_of_issue").val());
		if ($("#issue_date").val() == "") {
			alert("Please Select Issue Date.");
			$("#issue_date").focus();
			return false;
		}
		if ($("#status").val() == "") {
			alert("Please Select Status.");
			$("#status").focus();
			return false;
		}

		$("#issue_date2").val($("#issue_date").val());
		$("#status2").val($("#status").val());
		$("#WaitLoader").show();
		document.getElementById('searchForm').submit();
	}
	function approve_test() {
		if (confirm('I certify that the above data are true to the best of my knowledge.\nAre You Sure you want to Approve RELEASE ORDER ?')) {

			$("#WaitLoader").show();
			var checkedIds = [];

			var checkboxes = document.getElementsByName('checkbox_name1[]');
			var vals = "";

			for (var i = 0, n = checkboxes.length; i < n; i++) {
				if (checkboxes[i].checked) {
					checkedIds.push(checkboxes[i].value);
				}
			}

			if (checkedIds.length > 0) {
				var checkedIdsStr = checkedIds.join(",");
				$.ajax({
					type : 'POST',
					url : "ApprovedRelOrderUrl?" + key + "=" + value,
					data : {
						appid : checkedIdsStr
					},
					success : function(response) {
						Search();
						alert(response);
						$('#btn_app').prop('disabled', false);
						return true;

					}
				});
			} else {
				alert("Please Select at Least one Release Order.");
				$("#WaitLoader").hide();
				$('#btn_app').prop('disabled', false);
			}
		} else {
			return false;
		}
	}
	function reject_test() {
		$("#WaitLoader").show();
		var chk = "";
		var checkboxes = document.getElementsByName('checkbox_name1[]');
		var vals = "";

		for (var i = 0, n = checkboxes.length; i < n; i++) {
			if (checkboxes[i].checked) {
				chk = checkboxes[i].value;
				$.ajax({
					type : 'POST',
					url : "rejectRelOrderUrl?" + key + "=" + value,
					data : {
						rejectid : checkboxes[i].value
					},
					success : function(response) {
						Search();
						alert(response)
						if (response == "Release Order Reject Successfully.") {
							Search();
						}
						return true;
					}
				});
			}
		}
		if (chk == "") {
			alert("Please Select at Least one Release Order.")
			$("#WaitLoader").hide();
		}
	}
	function delete_test() {
		$("#WaitLoader").show();
		var chk = "";
		var checkboxes = document.getElementsByName('checkbox_name1[]');
		var vals = "";

		for (var i = 0, n = checkboxes.length; i < n; i++) {
			if (checkboxes[i].checked) {
				chk = checkboxes[i].value;
				vals += "," + checkboxes[i].value;
				$.ajax({
					type : 'POST',
					url : "deleteRelOrderUrl?" + key + "=" + value,
					data : {
						deleteid : checkboxes[i].value
					},
					success : function(response) {
						Search();
						alert(response);
						if (response == "Release Order Delete Successfully.") {
							Search();
						}
						return true;
					}
				});
			}
		}
		if (chk == "") {
			alert("Please Select at Least one Release Order.")
			$("#WaitLoader").hide();
		}
	}

	Date.prototype.addMonths = function(value) {
		var n = this.getDate();
		this.setDate(1);
		this.setMonth(this.getMonth() + value);
		this.setDate(Math.min(n, this.getDaysInMonth()));

		var datestring = this.getFullYear() + "-"
				+ ("0" + (this.getMonth() + 1)).slice(-2) + "-"
				+ ("0" + this.getDate()).slice(-2);
		return datestring;
	};
	Date.isLeapYear = function(year) {
		return (((year % 4 === 0) && (year % 100 !== 0)) || (year % 400 === 0));
	};

	Date.getDaysInMonth = function(year, month) {
		return [ 31, (Date.isLeapYear(year) ? 29 : 28), 31, 30, 31, 30, 31, 31,
				30, 31, 30, 31 ][month];
	};

	Date.prototype.isLeapYear = function() {
		return Date.isLeapYear(this.getFullYear());
	};

	Date.prototype.getDaysInMonth = function() {
		return Date.getDaysInMonth(this.getFullYear(), this.getMonth());
	};
</script>
<script>
	function setselectall() {
		if ($("#nSelAll").prop("checked")) {
			$(".nrCheckBox").prop('checked', true);
		} else {
			$(".nrCheckBox").prop('checked', false);
		}
		findselected();
	}

	function findselected() {
		var nrSel = $('.nrCheckBox:checkbox:checked').map(function() {
			$(this).attr('background-color', 'yellow');
			return $(this).attr('id');
		}).get();

		var b = nrSel.join(',');
		$("#nrSrcSel").val(b);
		$('#tregn').text(nrSel.length);
		var nrSel1 = $('.nrCheckBox:checkbox:checked').map(function() {
			$(this).attr('background-color', 'yellow');
			return $(this).attr('name');
		}).get();
		var b1 = nrSel1.join(',');
		$("#statushid").val(b1);

	}
	function validate() {
		if ($("#issue_date").val() == "") {
			alert("Please Enter the From date.");
			$("#issue_date").focus();
			return false;
		}
		if ($("#status").val() == "") {
			alert("Please Select Status.");
			$("#status").focus();
			return false;
		}
	}
</script>
<script>
	$(function() {
		// Source SUS No
		$("#depot_sus_no").keypress(function() {
			var sus_no = this.value;
			var susNoAuto = $("#depot_sus_no");
			susNoAuto.autocomplete({
				source : function(request, response) {
					$.ajax({
						type : 'POST',
						url : "getDepotSUSNoDetailList?" + key + "=" + value,
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
						alert("Please Enter Approved Depot SUS No.");
						document.getElementById("depot_name").value = "";
						susNoAuto.val("");
						susNoAuto.focus();
						return false;
					}
				},
				select : function(event, ui) {
					var sus_no = ui.item.value;

					$.post("getActiveUnitNameFromSusNo?" + key + "=" + value, {
						sus_no : sus_no
					}, function(data) {
					}).done(function(data) {
						var length = data.length - 1;
						var enc = data[length].substring(0, 16);
						$("#depot_name").val(dec(enc, data[0]));

					}).fail(function(xhr, textStatus, errorThrown) {
					});
				}
			});
		});

		// Source Unit Name
		$("#depot_name").keypress(function() {
			var unit_name = this.value;
			var susNoAuto = $("#depot_name");
			susNoAuto.autocomplete({
				source : function(request, response) {
					$.ajax({
						type : 'POST',
						url : "getDepotNameDetailList?" + key + "=" + value,
						data : {
							unit_name : unit_name
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
						alert("Please Enter Approved Unit Name.");
						document.getElementById("depot_sus_no").value = "";
						susNoAuto.val("");
						susNoAuto.focus();
						return false;
					}
				},
				select : function(event, ui) {
					var unit_name = ui.item.value;

					$.post("getTargetSUSFromUNITNAME?" + key + "=" + value, {
						target_unit_name : unit_name
					}, function(data) {
					}).done(function(data) {
						var length = data.length - 1;
						var enc = data[length].substring(0, 16);
						$("#depot_sus_no").val(dec(enc, data[0]));
					}).fail(function(xhr, textStatus, errorThrown) {
					});
				}
			});
		});
	});

	/* NITIN V4 16/11/2022  
	 */
	function fnExcelReport() {

		var status_txt = $("#status option:selected").text();
		var type_of_issue_txt = $("#type_of_issue option:selected").text();
		var type_of_ro_txt = $("#type_of_ro option:selected").text();

		var sus_no = $("#sus_no").val();
		var unit_name = $("#unit_name").val();
		var depot_sus_no = $("#depot_sus_no").val();
		var depot_name = $("#depot_name").val();
		var issue_date = $("#issue_date").val();
		var to_date = $("#to_date").val();
		var status = $("#status").val();
		var type_of_issue = $("#type_of_issue").val();
		var type_of_ro = $("#type_of_ro").val();

		if (status_txt == "--Select--") {
			status_txt = "";
		}

		if (type_of_issue_txt == "--Select--") {
			type_of_issue_txt = "";
		}

		if (type_of_ro_txt == "--Select--") {
			type_of_ro_txt = "";
		}

		$("#status_txt").val(status_txt);
		$("#type_of_issue_txt").val(type_of_issue_txt);
		$("#type_of_ro_txt").val(type_of_ro_txt);
		$("#sus_no_ex").val(sus_no);
		$("#unit_name_ex").val(unit_name);
		$("#depot_sus_no_ex").val(depot_sus_no);
		$("#depot_name_ex").val(depot_name);
		$("#issue_date_ex").val(issue_date);
		$("#to_date_ex").val(to_date);
		$("#status_ex").val(status);
		$("#type_of_ro_ex").val(type_of_ro);
		$("#type_of_issue_ex").val(type_of_issue);

		document.getElementById('typeReport1').value = 'excelL';
		document.getElementById('ExcelForm').submit();
	}

	/* added by Mitesh 17-10 */
	function editRoDetails(id) {

		var modal = document.getElementById('getRODetail12');
		modal.style.display = "block";

		jQuery.ajax({
			type : 'POST',
			url : "getRODetails?" + key + "=" + value,
			data : {
				id : id
			},
			success : function(data) {
				if (data.length != 0) {
					$("#vro_no1").text(data[0].ro_no);
					$("#vro_no1").val(data[0].ro_no);
					$("#vunit_name1").text(data[0].unit_name);
					$("#vcommand_name1").text(data[0].command_name);
					$("#vdepot_name1").text(data[0].depot_name);
					$("#vnrs1").text(data[0].nrs);
					$("#vtype_of_issue1").text(data[0].type_of_issue);
					$("#vissue_stock1").text(data[0].issue_stock);
					$("#vtype_of_vehicle1").text(data[0].type_of_vehicle);
					$("#vissue_date1").text(data[0].issue_date);
					$("#vother_issue_type1").text(data[0].other_issue_type);
					$("#vloan_auth1").text(data[0].loan_auth);
					$("#vloan_duration1").text(data[0].loan_duration);
					$("#vmct1").text(data[0].mct);
					$("#vstd_nomclature1").text(data[0].std_nomclature);
					$("#vinliuemct1").text(data[0].inliuemct);
					$("#vstd_nomclature_inlieu1").text(data[0].std_nomclature_inlieu);
					$("#vquantity1").text(data[0].quantity);
					$("#vaccounting1").text(data[0].accounting);
					$("#vissue_precedence1").text(data[0].issue_precedence);
					$("#vremarks1").text(data[0].remarks);
					$("#vuserid1").text(data[0].modify_by);
					const appdt = data[0].approve_date;
					$("#approved_dt1").text(data[0].approve_date);
					$("#approved_dt1").val(data[0].approve_date);
					const extendeddate = data[0].extended_on;
					$("#extended_on1").text(data[0].extended_on);
					$("#extended_on1").val(data[0].extended_on);
					const validUpto = data[0].valid_upto;
					$("#vvalid_upto1").text(validUpto);
					$("#vvalid_upto1").val(validUpto);
					
					
		
					 	const validDate = createValidDate(appdt);
					
						const validDt = createValidDate(validUpto);
						
						//const exdt = createValidDate(extendeddate);
					
					
					 if (validDate) {
					     validDate.setDate(validDate.getDate() + 45);
					     
				
					    	 validDt.setDate(validDt.getDate() + 1); 
					     const extendedDateStr = validDt.toISOString().split('T')[0]; // Get yyyy-mm-dd
					     document.getElementById('extended_date1').value = extendedDateStr;
					    
					     
					     // Calculate the maximum selectable date (valid date + 45 days)
					     const maxDate = new Date(validDate);
					     maxDate.setDate(validDate.getDate() + 45);
					     
					     // Set the min and max attributes for the input field
					     document.getElementById('extended_date1').setAttribute('min', extendedDateStr);
					     document.getElementById('extended_date1').setAttribute('max', maxDate.toISOString().split('T')[0]);

					     // Add an event listener to check date selection
					     document.getElementById('extended_date1').addEventListener('change', function() {
					         const selectedDate = createValidDate(this.value);

					         if (selectedDate) {
					             // Check if the selected date is outside the valid range
					             if (selectedDate < validDate && selectedDate > maxDate) {
					                 alert('Please select a date between the valid upto date and the maximum date (next 45 days).');
					                 this.value = extendedDateStr; // Reset to valid upto date
					             }
					         } else {
					             alert('Invalid date format. Please select a valid date.');
					             this.value = extendedDateStr; // Reset to valid upto date
					         }
					     });
					 } else {
					     console.error('Invalid date format');
					 }
				}
			}
		});
	}
	/* added by Mitesh 17-10 */
	function update() {
		var ro_no = $("#vro_no1").val();
		console.log(ro_no);
		var extended_date = $("#extended_date1").val();
		if (extended_date == "") {
			alert("Please Select Extended Date.");
			$("#extended_date1").fucos();
			return false;
		} else {
			jQuery.ajax({
				type : 'POST',
				url : "UpdateRoMain?" + key + "=" + value,
				data : {
					ro_no : ro_no,
					extended_date : extended_date
				},
				success : function(data) {
					alert(data[0].msg);
					if (data[0].msg == "Ro Extended Successfully") {

						location.reload();
					}
				}
			});
		}
	}
	
	// Added by Mitesh 17-10
	//Function to convert dd-mm-yyyy to a Date object 
	  function createValidDate(dateStr) {
	      const parts = dateStr.split('-');
	      if (parts.length !== 3) return null; // Check if the format is correct
	      const day = parseInt(parts[0], 10);
	      const month = parseInt(parts[1], 10) - 1; // Month is 0-based in JavaScript
	      const year = parseInt(parts[2], 10);

	      // Create a new Date object
	      const date = new Date(year, month, day);
	      return isNaN(date.getTime()) ? null : date; // Return null if the date is invalid
	  }
</script>

<!--  NITIN V4 16/11/2022  
  -->
<c:url value="Excel_data_ro" var="excelUrl" />
<form:form action="${excelUrl}" method="post" id="ExcelForm"
	name="ExcelForm" modelAttribute="typeReport1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
	<input type="hidden" name="status_ex" id="status_ex" value="">
	<input type="hidden" name="sus_no_ex" id="sus_no_ex">
	<input type="hidden" name="unit_name_ex" id="unit_name_ex">
	<input type="hidden" name="depot_sus_no_ex" id="depot_sus_no_ex">
	<input type="hidden" name="depot_name_ex" id="depot_name_ex">
	<input type="hidden" name="issue_date_ex" id="issue_date_ex">
	<input type="hidden" name="to_date_ex" id="to_date_ex">
	<input type="hidden" name="status_txt" id="status_txt">
	<input type="hidden" name="type_of_issue_txt" id="type_of_issue_txt">
	<input type="hidden" name="type_of_ro_txt" id="type_of_ro_txt">
	<input type="hidden" name="type_of_issue_ex" id="type_of_issue_ex">
	<input type="hidden" name="type_of_ro_ex" id="type_of_ro_ex" value="">

</form:form>
