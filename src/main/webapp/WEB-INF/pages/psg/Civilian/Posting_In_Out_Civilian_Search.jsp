<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<link href="js/cue/cueWatermark.css" rel="Stylesheet"></link>
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>

<!-- resizable_col -->
<link rel="stylesheet" href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js" type="text/javascript"></script>
<style>

.card-header {
	margin-bottom: 0;
	background-color: #cccccc;
	border-bottom: 1px solid #cccccc;
	padding: 12px;
}

.card-header1 {
	margin-bottom: 0;
	background-color: #f5f5f5;
	border-bottom: 1px solid #cccccc;
	padding: 3px;

}
</style>
<div class="animated fadeIn">
	<div class="container" align="center">
		<div class="card">
			<form>
				<div class="card-header">
					<h5>Search/Approve Posting</h5>
					<h6 class="enter_by">
<!-- 						<span style="font-size: 12px; color: red;">(To be entered -->
<!-- 							by unit)</span> -->
					</h6>
				</div>
				<div class="card-body card-block">
					<div class="col-md-12">
						<div class="col-md-6" id="">
							<div class="row form-group">
								<div class="col-md-4">
									<strong style="color: red;">* </strong><label
										class=" form-control-label"> Action </label>
								</div>
								<div class="col-md-8">
									<select name="type" id="type" onchange="fnType()"
										class="form-control">
										<c:forEach var="item" items="${getPostINOutstatusList}"
											varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>

							</div>
						</div>
					</div>
				</div>
			</form>

			<!-- POST OUT FORM START  -->
			<div id="div_post_out" class="tabcontent" style="display: none;">
				<form id="post_out_form">
					<div class="card">
						<div class="card-header1">
							<h5 style="	font-weight: bold;">POST OUT</h5>
							<h6 class="enter_by">
								<span style="font-size: 12px; color: red;"></span>
							</h6>
						</div>
						<div class="card-body card-block">
							<br>
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"> From SUS No </label>
										</div>
										<div class="col-md-8">
											<input type="text" id="from_sus_no_out"
												name="from_sus_no_out" value="${roleSusNo}"
												class="form-control autocomplete" autocomplete="off"
												onkeyup="search_sus1from(this,'from_unit_name_out');specialcharecter(this)"
												maxlength="8" onkeypress="return AvoidSpace(event)">
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"> From Unit Name </label>
										</div>
										<div class="col-md-8">
											<input type="text" id="from_unit_name_out"
												name="from_unit_name_out" value="${unit_name}"
												class="form-control autocomplete" autocomplete="off"
												onkeyup="search_unit1_from(this,'from_sus_no_out');specialcharecter(this)"
												maxlength="50">
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"> To Sus No </label>
										</div>
										<div class="col-md-8">
											<input type="text" id="to_sus_no" name="to_sus_no"
												onkeyup="search_sus(this,'to_unit_name_out');specialcharecter(this)"
												class="form-control autocomplete" autocomplete="off"
												maxlength="8" onkeypress="return AvoidSpace(event)">
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"> To Unit Name </label>
										</div>
										<div class="col-md-8">
											<input type="text" id="to_unit_name_out"
												name="to_unit_name_out" value=""
												class="form-control autocomplete" autocomplete="off"
												onkeyup="search_unit_name(this,'to_sus_no');specialcharecter(this)"
												maxlength="50">
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"> Employee No </label>
										</div>
										<div class="col-md-8">
											<input type="text" id="employee_no" name="employee_no"
												class="form-control autocomplete" autocomplete="off" onkeyup="getemployee_no(this,'from_sus_no')"
												maxlength="50"
												onkeypress="return AvoidSpace(event)">
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"> Designation </label>
										</div>
									 	<div class="col-md-8">
											<select name="rank" id="rank"
												class="form-control-sm form-control">
												<option value="0">--Select--</option>
										<c:forEach var="item" items="${getDesignationList}"
											varStatus="num">
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
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> Status</label>
										</div>
										<div class="col-md-8">
											<select name="statusout" id="statusout"
												class="form-control-sm form-control">
												<option value="0">Pending</option>
												<option value="1">Approved / Updated</option>
												<option value="3">Rejected</option>
												<option value="4">View History/Cancel</option>
											</select>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label">Created by </label>
										</div>
										<div class="col-md-8">


											<select name="cr_by_out" id="cr_by_out"
												class="form-control-sm form-control">
												<option value="">--Select--</option>
												<c:forEach var="item" items="${getRoleNameList_dash}"
													varStatus="num">
													<option value="${item.userId}" name="${item.userName}">${item.userName}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>

								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label">Created Date </label>
										</div>
										<div class="col-md-8">
											<input type="text" name="cr_date_out" id="cr_date_out"
												maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
												class="form-control" style="width: 85%; display: inline;">

										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="card-footer" align="center" class="form-control">
							<a href="Search_Posting_In_Out_CivilianUrl"
								class="btn btn-success btn-sm">Clear</a> <input type="button"
								class="btn btn-info btn-sm" onclick="getPostData();"
								value="Search">
						</div>
					</div>
				</form>
			</div>
			<!-- --------------------INNN---------------------------- -->
			<div id="div_post_in" class="tabcontent" style="display: none;">
				<form id="post_out_in">
					<div class="card">
						<div class="card-header1">
							<h5 style="	font-weight: bold;">POST IN</h5>
							<h6 class="enter_by">
								<span style="font-size: 12px; color: red;"></span>
							</h6>
						</div>
						<div class="card-body card-block">
							<br>
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"> From SUS No </label>
										</div>
										<div class="col-md-8">
											<input type="text" id="from_sus_no_in" name="from_sus_no_in"
												class="form-control autocomplete" autocomplete="off"
												onkeyup="search_sus1from(this,'from_unit_name_in');specialcharecter(this)"
												maxlength="8" onkeypress="return AvoidSpace(event)">
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"> From Unit Name </label>
										</div>
										<div class="col-md-8">
											<input type="text" id="from_unit_name_in"
												name="from_unit_name_in" class="form-control autocomplete"
												autocomplete="off"
												onkeyup="search_unit1_from(this,'from_sus_no_in');specialcharecter(this)"
												maxlength="50">
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"> To Sus No </label>
										</div>
										<div class="col-md-8">
											<!-- <input type="hidden" name="comm_id" id="comm_id" value="0"/>  -->
											<input type="text" id="to_sus_no_in" name="to_sus_no_in"
												value="${roleSusNo}"
												onkeyup="search_sus(this,'to_unit_name_in');specialcharecter(this)"
												maxlength="8" onkeypress="return AvoidSpace(event)"
												class="form-control autocomplete" autocomplete="off">
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"> To Unit Name </label>
										</div>
										<div class="col-md-8">
											<input type="text" id="to_unit_name_in"
												name="to_unit_name_in" value="${unit_name}"
												class="form-control autocomplete" autocomplete="off"
												onkeyup="search_unit_name(this,'to_sus_no_in');specialcharecter(this)"
												maxlength="50">
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;"></strong> Employee No </label>
										</div>
										<div class="col-md-8">
											<input type="text" id="inemployee_no" name="employee_no"
												class="form-control autocomplete" autocomplete="off"
												onkeyup="getemployee_no(this,'from_sus_no_in')"
												onkeypress="return AvoidSpace(event)">
												
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"> Designation </label>
										</div>
										<div class="col-md-8">
											<select name="rank" id="inrank"
												class="form-control-sm form-control">
												<option value="">--Select--</option>
												<c:forEach var="item" items="${getDesignationList}"
													varStatus="num">
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
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> Status </label>
										</div>
										<div class="col-md-8">
											<select name="statusin" id="statusin"
												class="form-control-sm form-control">
												<option value="0">Pending</option>
												<option value="1">Approved / Updated</option>
												<option value="3">Rejected</option>
												<option value="4">View History/Cancel</option>
											</select>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label">Created by </label>
										</div>
										<div class="col-md-8">


											<select name="cr_by_in" id="cr_by_in"
												class="form-control-sm form-control">
												<option value="">--Select--</option>
												<c:forEach var="item" items="${getRoleNameList_dash}"
													varStatus="num">
													<option value="${item.userId}" name="${item.userName}">${item.userName}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>

								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label">Created Date </label>
										</div>
										<div class="col-md-8">
											<input type="text" name="cr_date_in" id="cr_date_in" maxlength="10"
												onclick="clickclear(this, 'DD/MM/YYYY')"
												class="form-control" style="width: 85%; display: inline;">

										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="card-footer" align="center" class="form-control">
							<a href="Search_Posting_In_Out_CivilianUrl"
								class="btn btn-success btn-sm">Clear</a> <input type="button"
								class="btn btn-info btn-sm" onclick="getPostData();"
								value="Search">
						</div>
					</div>
				</form>
			</div>
			<!-- OUT TABLE -->

			<div class="container" id="divPrint" style="display: none;">
				<div class="watermarked" data-watermark="" id="divwatermark">
					<span id="ip"></span>
					<table id="searchPostTable"
						class="table no-margin table-striped  table-hover  table-bordered">
						<thead>
							<tr>
								<th style="font-size: 15px; text-align: center">Ser No</th>
								<th style="font-size: 15px; text-align: center">Employee No</th>
								<th style="font-size: 15px; text-align: center">Name</th>
								<th style="font-size: 15px; text-align: center">Designation</th>
								<th class="postoutclass" style="font-size: 15px; text-align: center">Auth No</th>
								<th class="postoutclass" style="font-size: 15px; text-align: center">Auth Date</th>
								<th style="font-size: 15px; text-align: center">Unit SUS No</th>
								
								<th style="font-size: 15px; text-align: center; display: none;" id="th_sos">Date of SOS</th>
								<th style="font-size: 15px; text-align: center; display: none;" id="th_tos">Date of TOS</th>
								<th style="font-size: 15px; text-align: center">From SUS No</th>
								<th class="remarks" style="font-size: 15px; text-align: center">Reject Remarks</th>
								<th class="actionclass" style="font-size: 15px; text-align: center">ACTION</th>
							</tr>
						</thead>
						<tbody id="searchposttbody">
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>

<c:url value="Search_Posting_Out_Civilian" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="employee_no1">
	<input type="hidden" name="employee_no1" id="employee_no1" value="0" />
	<input type="hidden" name="rank1" id="rank1" />
	<input type="hidden" name="to_sus_no1" id="to_sus_no1" value="0" />
	<input type="hidden" name="from_sus_no1" id="from_sus_no1" value="0" />
	<input type="hidden" name="type1" id="type1" value="0" />
	<input type="hidden" name="cr_date1" id="cr_date1"  />
	<input type="hidden" name="cr_by1" id="cr_by1"  />
</form:form>

<c:url value="Search_Posting_In_Civilian" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm1"
	name="searchForm1" modelAttribute="employee_no2">
	<input type="hidden" name="employee_no2" id="employee_no2" value="0" />
	<input type="hidden" name="rank2" id="rank2" />
	<input type="hidden" name="to_sus_no2" id="to_sus_no2" value="0" />
	<input type="hidden" name="from_sus_no2" id="from_sus_no2" value="0" />
	<input type="hidden" name="type2" id="type2" value="0" />
	<input type="hidden" name="cr_date2" id="cr_date2"  />
	<input type="hidden" name="cr_by2" id="cr_by2"  />
</form:form>

<c:url value="Approve_PostOUT_Civilian" var="approveUrl" />
<form:form action="${approveUrl}" method="post" id="approveForm" name="approveForm" modelAttribute="id3">
	 <input type="hidden" name="id3" id="id3" value="0"/> 
	 <input type="hidden" name="statusapp1" id="statusapp1" value="0"/> 
	 <input type="hidden" name="civ_idapp1" id="civ_idapp1" value="0"/>  
	 <input type="hidden" name="from_sus_noapp1" id="from_sus_noapp1" value="0"/>  
	 <input type="hidden" name="sus_noapp1" id="sus_noapp1" value="0"/> 
	 <input type="hidden" name="employee_noapp1" id="employee_noapp1" value="0" />
	 <input type="hidden" name="rankapp1" id="rankapp1" />
	 <input type="hidden" name="to_sus_noapp1" id="to_sus_noapp1" value="0" />
	 <input type="hidden" name="typeappapp1" id="typeapp1" value="0" />
	 <input type="hidden" name="dt_tos1" id="dt_tos1" value="0" />	
</form:form> 

<c:url value="Approve_PostIN_Civilian" var="inapproveUrl" />
<form:form action="${inapproveUrl}" method="post" id="inapproveForm" name="inapproveForm" modelAttribute="id4">
	<input type="hidden" name="id4" id="id4" value="0"/> 
	<input type="hidden" name="statusapp2" id="statusapp2" value="0"/> 
	<input type="hidden" name="civ_idapp2" id="civ_idapp2" value="0"/>  
	<input type="hidden" name="from_sus_noapp2" id="from_sus_noapp2" value="0"/> 
	<input type="hidden" name="sus_noapp2" id="sus_noapp2" value="0"/>  
	<input type="hidden" name="employee_noapp2" id="employee_noapp2" value="0" />
	<input type="hidden" name="rankapp2" id="rankapp2" />
	<input type="hidden" name="to_sus_noapp2" id="to_sus_noapp2" value="0" />
	<input type="hidden" name="typeapp2" id="typeapp2" value="0" />	
	<input type="hidden" name="dt_tos2" id="dt_tos2" value="0" />	
	
</form:form> 

<c:url value="Reject_PostOUT_Civilian" var="rejectUrl" />
<form:form action="${rejectUrl}" method="post" id="rejectForm" name="rejectForm" modelAttribute="idoutr">
	 <input type="hidden" name="idoutr" id="idoutr" value="0"/> 
	 <input type="hidden" name="statusoutr" id="statusoutr" value="0"/> 
	 <input type="hidden" name="civ_idoutr" id="civ_idoutr" value="0"/>  
	 <input type="hidden" name="from_sus_nooutr" id="from_sus_nooutr" value="0"/>  
	 <input type="hidden" name="sus_nooutr" id="sus_nooutr" value="0"/> 
	 <input type="hidden" name="employee_nooutr" id="employee_nooutr" value="0" />
	 <input type="hidden" name="rankoutr" id="rankoutr" />
	 <input type="hidden" name="to_sus_nooutr" id="to_sus_nooutr" value="0" />
	 <input type="hidden" name="typeoutr" id="typeoutr" value="0" />
	  <input type="hidden" name="rej_remarkoutr" id="rej_remarkoutr" value="0" />
</form:form> 

<c:url value="Reject_PostIN_Civilian" var="inrejectUrl" />
<form:form action="${inrejectUrl}" method="post" id="inrejectForm" name="inrejectForm" modelAttribute="idinr">
	<input type="hidden" name="idinr" id="idinr" value="0"/> 
	<input type="hidden" name="statusinr" id="statusinr" value="0"/> 
	<input type="hidden" name="civ_idinr" id="civ_idinr" value="0"/>  
	<input type="hidden" name="from_sus_noinr" id="from_sus_noinr" value="0"/>  
	<input type="hidden" name="sus_noinr" id="sus_noinr" value="0"/> 
	<input type="hidden" name="employee_noinr" id="employee_noinr" value="0" />
	<input type="hidden" name="rankinr" id="rankinr" />
	<input type="hidden" name="to_sus_noinr" id="to_sus_noinr" value="0" />
	<input type="hidden" name="typeinr" id="typeinr" value="0" />
	 <input type="hidden" name="rej_remarkinr" id="rej_remarkinr" value="0" />	
</form:form> 

<c:url value="Edit_Post_Out_CivilianUrl" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid">
	<input type="hidden" name="updateid" id="updateid" value="0" />
	<input type="hidden" name="employee_no6" id="employee_no6" value="0" />
	<input type="hidden" name="rank6" id="rank6" />
	<input type="hidden" name="to_sus_no6" id="to_sus_no6" value="0" />
	<input type="hidden" name="status6" id="status6" value="0" />
	<input type="hidden" name="from_sus_no6" id="from_sus_no6" value="0" />
	<input type="hidden" name="type6" id="type6" value="0" />
</form:form>

<c:url value="Edit_Post_In_CivilianUrl" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="inupdateForm" name="inupdateForm" modelAttribute="updateid">
	<input type="hidden" name="inupdateid" id="inupdateid" value="0" />
	<input type="hidden" name="status3" id="status3" value="0" />
	<input type="hidden" name="employee_no3" id="employee_no3" value="0" />
	<input type="hidden" name="rank3" id="rank3" />
	<input type="hidden" name="to_sus_no3" id="to_sus_no3" value="0" />
	<input type="hidden" name="from_sus_no3" id="from_sus_no3" value="0" />
	<input type="hidden" name="type3" id="type3" value="0" />

</form:form>
 
<!--  //////////////////////Cancel History//////////////////////////// -->

<c:url value="CancelRejectHistory_PostINOUT_Civilian" var="CancelRejectUrl" />
<form:form action="${CancelRejectUrl}" method="post" id="CancelRejectForm" name="CancelRejectForm" modelAttribute="idcrh">
	 <input type="hidden" name="idcrh" id="idcrh" value="0"/> 
	 <input type="hidden" name="statuscrh" id="statuscrh" value="0"/> 
	 <input type="hidden" name="civ_idcrh" id="civ_idcrh" value="0"/>  
	 <input type="hidden" name="from_sus_nocrh" id="from_sus_nocrh" value="0"/>  
	 <input type="hidden" name="employee_nocrh" id="employee_nocrh" value="0" />
	 <input type="hidden" name="rankcrh" id="rankcrh" />
	 <input type="hidden" name="to_sus_nocrh" id="to_sus_nocrh" value="0" />
	 <input type="hidden" name="typecrh" id="typecrh" value="0" />
	 <input type="hidden" name="reject_remarkscrh" id="reject_remarkscrh" value="0" />
</form:form>
 
<c:url value="ApproveCancelHistory_PostINOUT_Civilian" var="ApproveCancelUrl" />
<form:form action="${ApproveCancelUrl}" method="post" id="ApproveCancelForm" name="ApproveCancelForm" modelAttribute="idach">
	<input type="hidden" name="idach" id="idach" value="0"/> 
	<input type="hidden" name="statusach" id="statusach" value="0"/> 
	<input type="hidden" name="civ_idach" id="civ_idach" value="0"/>  
	<input type="hidden" name="from_sus_noach" id="from_sus_noach" value="0"/>  
	<input type="hidden" name="employee_noach" id="employee_noach" value="0" />
	<input type="hidden" name="rankach" id="rankach" />
	<input type="hidden" name="to_sus_noach" id="to_sus_noach" value="0" />
	<input type="hidden" name="typeach" id="typeach" value="0" />
</form:form> 

<script>

$(document).ready(function() {	
	
	jQuery(function($) {
			datepicketDate('cr_date_out');
			datepicketDate('cr_date_in');
			
		});
	
		if ('${type4}' == 'postin') {
			$("#inemployee_no").val('${employee_no4}');
			$("#inrank").val('${rank4}');
			$("#to_sus_no_in").val('${to_sus_no4}');
			$("#from_sus_no_in").val('${from_sus_no4}');
			$("#statusin").val('${status4}');
			var sus_no = '${to_sus_no4}';
			var sus_no2 = '${from_sus_no4}';
			getunit(sus_no, 'to_unit_name_in');
			getunit(sus_no2, 'from_unit_name_in');
			$("#div_post_in").show();
			$("#div_post_out").hide();
			$("#type").val('${type4}');
		}
		if ('${type4}' == 'postout') {
			$("#employee_no").val('${employee_no4}');
			$("#rank").val('${rank4}');
			$("#to_sus_no").val('${to_sus_no4}');
			$("#from_sus_no_out").val('${from_sus_no4}');
			$("#statusout").val('${status4}');
			var sus_no = '${to_sus_no4}';
			var sus_no2 = '${from_sus_no4}';
			getunit(sus_no, 'to_unit_name_out');
			getunit(sus_no2, 'from_unit_name_out');
			$("#div_post_out").show();
			$("#div_post_in").hide();
			$("select#type").val('${type4}');
		}

		if ('${roleSusNo}' != "") {
			$('#roleSusNo').val('${roleSusNo}');
		}

		if ('${roleAccess}' == 'Unit') {
			$("#from_sus_no_out").prop('readonly', true);
			$("#from_unit_name_out").prop('readonly', true);
			$("#to_sus_no_in").prop('readonly', true);
			$("#to_unit_name_in").prop('readonly', true);

		}

		if ($('#type').val() == 'postout') {
			$("#div_post_out").show();
			$("#div_post_in").hide();

		} else if ($('#type').val() == 'postin') {
			$("#div_post_in").show();
			$("#div_post_out").hide();
		}

		getPostData();
	});

	function getunit(val, id) {
		$.post("getTargetUnitNameList?" + key + "=" + value, {
			sus_no : val
		}, function(j) {

		}).done(function(j) {
			var length = j.length - 1;
			var enc = j[length].substring(0, 16);
			$("#" + id).val(dec(enc, j[0]));
		}).fail(function(xhr, textStatus, errorThrown) {
		});
	}

	function fnType() {
		if ($('#type').val() == 'postout') {
			$("#div_post_out").show();
			$("#div_post_in").hide();
		} else if ($('#type').val() == 'postin') {
			$("#div_post_in").show();
			$("#div_post_out").hide();
		}

		$("#employee_no").val('');
		$("#inemployee_no").val('');
		$("#inrank").val('');
		$("#from_sus_no_in").val('');
		$("#from_unit_name_in").val('');
		$("#to_sus_no_out").val('');
		$("#to_unit_name_out").val('');
		$("#rank").val('');
		$("#to_sus_no").val('');
		$("#to_unit_name_out").val('');
		$("#divPrint").hide();
	}

	function Search() {
		$("#employee_no1").val($("#employee_no").val());
		$("#rank1").val($("#rank").val());
		$("#to_sus_no1").val($("#to_sus_no").val());
		$("#from_sus_no1").val($("#from_sus_no_out").val());
		$("#type1").val($("#type").val());
		$("#cr_by1").val($("#cr_by_out").val()) ;
		$("#cr_date1").val($("#cr_date_out").val()) ;
		document.getElementById('searchForm').submit();
	}
	function INSearch() {
		$("#employee_no2").val($("#inemployee_no").val());
		$("#rank2").val($("#inrank").val());
		$("#to_sus_no2").val($("#to_sus_no_in").val());
		$("#from_sus_no2").val($("#from_sus_no_in").val());
		$("#type2").val($("#type").val());
		$("#cr_by2").val($("#cr_by_in").val()) ;
		$("#cr_date2").val($("#cr_date_in").val()) ;
		document.getElementById('searchForm1').submit();
	}
	function Approved(id, civ_id, sus_no, tos_dt) {
		$("#id3").val(id);
		$("#civ_idapp1").val(civ_id);
		$("#sus_noapp1").val(sus_no);
		$("#dt_tos1").val(tos_dt);
		$("#typeapp1").val($("#type").val());
		$("#employee_noapp1").val($("#employee_no").val());
		$("#rankappapp1").val($("#rank").val());
		$("#to_sus_noapp1").val($("#to_sus_no").val());
		$("#from_sus_noapp1").val($("#from_sus_no_out").val());
		$("#statusapp1").val($("#statusout").val());
		document.getElementById('approveForm').submit();
	}
	function InApproved(id, civ_id, sus_no, tos_dt) {
		$("#id4").val(id);
		$("#civ_idapp2").val(civ_id);
		$("#sus_noapp2").val(sus_no);
		$("#dt_tos2").val(tos_dt);
		$("#typeapp2").val($("#type").val());
		$("#employee_noapp2").val($("#inemployee_no").val());
		$("#rankapp2").val($("#inrank").val());
		$("#to_sus_noapp2").val($("#to_sus_no_in").val());
		$("#from_sus_noapp2").val($("#from_sus_no_in").val());
		$("#statusapp2").val($("#statusin").val());
		document.getElementById('inapproveForm').submit();
	}
	function Reject(id, civ_id, sus_no) {
		$("#idoutr").val(id);
		$("#civ_idoutr").val(civ_id);
		$("#sus_nooutr").val(sus_no);
		$("#typeoutr").val($("#type").val());
		$("#employee_nooutr").val($("#employee_no").val());
		$("#rankoutr").val($("#rank").val());
		$("#to_sus_nooutr").val($("#to_sus_no").val());
		$("#from_sus_nooutr").val($("#from_sus_no_out").val());
		$("#statusoutr").val($("#statusout").val());
		$("#rej_remarkoutr").val($("#reject_remarks").val());
		document.getElementById('rejectForm').submit();
	}
	function InReject(id, civ_id, sus_no) {
		$("#idinr").val(id);
		$("#civ_idinr").val(civ_id);
		$("#sus_noinr").val(sus_no);
		$("#typeinr").val($("#type").val());
		$("#employee_noinr").val($("#inemployee_no").val());
		$("#rankinr").val($("#inrank").val());
		$("#to_sus_noinr").val($("#to_sus_no_in").val());
		$("#from_sus_noinr").val($("#from_sus_no_in").val());
		$("#statusinr").val($("#statusin").val());
		$("#rej_remarkinr").val($("#reject_remarks").val());
		document.getElementById('inrejectForm').submit();
	}
	function editData(id) {
		$("#employee_no6").val($("#employee_no").val());
		$("#rank6").val($("#rank").val());
		$("#to_sus_no6").val($("#to_sus_no").val());
		$("#from_sus_no6").val($("#from_sus_no_out").val());
		$("#type6").val($("#type").val());
		$("#status6").val($("#statusout").val());
		document.getElementById('updateid').value = id;
		document.getElementById('updateForm').submit();
	}
	function IneditData(id) {
		$("#employee_no3").val($("#inemployee_no").val());
		$("#rank3").val($("#inrank").val());
		$("#to_sus_no3").val($("#to_sus_no_in").val());
		$("#from_sus_no3").val($("#from_sus_no_in").val());
		$("#type3").val($("#type").val());
		$("#status3").val($("#statusin").val());
		document.getElementById('inupdateid').value = id;
		document.getElementById('inupdateForm').submit();
	}

	function getPostData() {

		var type = $("#type").val();
		var employee_no = $("#inemployee_no").val();
		var rank = $("#rank").val();
		var to_sus_no = $("#to_sus_no").val();
		var status = $("#statusout").val();
		var from_sus_no = $("#from_sus_no_out").val();
		var reject_remarks = $("#reject_remarks").val();
		var cr_by =	$("#cr_by_out").val() ;	
		var cr_date =	$("#cr_date_out").val() ;	
		var cpurl;
		if (type != '0') {

			if (type == 'postin') {
				cpurl = 'Search_Posting_In_Civilian?';
				employee_no = $("#inemployee_no").val();
				rank = $("#inrank").val();
				from_sus_no = $("#from_sus_no_in").val();
				to_sus_no = $("#to_sus_no_in").val();
				status = $("#statusin").val();
				cr_by =	$("#cr_by_in").val() ;	
				cr_date =	$("#cr_date_in").val() ;	
				
			} else if (type == 'postout') {
				cpurl = 'Search_Posting_Out_Civilian?';
				employee_no = $("#employee_no").val();
				rank = $("#rank").val();
				from_sus_no = $("#from_sus_no_out").val();
				to_sus_no = $("#to_sus_no").val();
				status = $("#statusout").val();
				cr_by =	$("#cr_by_out").val() ;	
				cr_date =	$("#cr_date_out").val() ;	
			}
			if (to_sus_no != '' && from_sus_no != '') {
				if (to_sus_no == from_sus_no) {
					alert("From Sus and To Sus Can't Be Same");
					return false;
				}
			}
			$
					.post(
							cpurl + key + "=" + value,
							{
								employee_no : employee_no,
								rank : rank,
								to_sus_no : to_sus_no,
								from_sus_no : from_sus_no,
								status : status,
								reject_remarks : reject_remarks,
								cr_by:cr_by,
								cr_date:cr_date
							},
							function(k) {
								$('#searchposttbody').empty();
								if (k.length > 0) {

									if (k[0][0] == "error") {
										alert(k[0][1]);
										return false;
									}
									if (k[0][0] != "error") {
										var x = 0
										for (i = 0; i < k.length; i++) {

											if (k[i][10] == null) {
												k[i][10] = "First Post In";
											}
											x = x + 1;
											$("table#searchPostTable")
													.append(
															'<tr>' + '<td>'
																	+ x
																	+ '</td>'
																	+ '<td > '
																	+ k[i][0]
																	+ ' </td>'
																	+ '<td > '
																	+ k[i][1]
																	+ ' </td>'
																	+ '<td > '
																	+ k[i][2]
																	+ ' </td>'
																	+ '<td class="postoutclass"> '
																	+ k[i][3]
																	+ ' </td>'
																	+ '<td  class="postoutclass" > '
																	+ k[i][4]
																	+ '</td>'
																	+ '<td> '
																	+ k[i][5]
																	+ ' </td>'
																	+ '<td > '
																	+ k[i][6]
																	+ ' </td>'
																	+ '<td> '
																	+ k[i][10]
																	+ ' </td>'
																	+ '<td class="remarks"> '
																	+ k[i][7]
																	+ '</td>'
																	+ '<td class="actionclass" align="center"> '
																	+ k[i][8]
																	+ ' '
																	+ k[i][9]
																	+ ' </td>'
																	+ '</tr>');
										}

										if (type == 'postin') {

											$("#th_tos").show();
											$("#th_sos").hide();
											$('.postoutclass').hide();

											if ($("#statusin").val() == 3) {

												$('.remarks').show();
											} else {
												$('.remarks').hide();
											}

											if ($("#statusin").val() == 1) {
												$('.actionclass').hide();
											} else {
												$('.actionclass').show();
											}
										} else if (type == 'postout') {
											$('.postoutclass').show();

											if ($("#statusin").val() == 3) {
												$('.remarks').show();
											} else {
												$('.remarks').hide();
											}

											if ($("#statusout").val() == 1) {
												$('.actionclass').hide();
											} else {
												$('.actionclass').show();
											}

											$("#th_tos").hide();
											$("#th_sos").show();
										}

										$("#divPrint").show();
									}
								} else {
									$("#divPrint").show();
									$("table#searchPostTable")
											.append(
													'<tr><td colspan="9" style = "color: red;" align="center">Data Not Available</td>'
															+ '</tr>');
								}
							});
			$("#employee_no3").val($("#inemployee_no").val());
			$("#rank3").val($("#inrank").val());
			$("#to_sus_no3").val($("#to_sus_no").val());
			$("#from_sus_no3").val($("#from_sus_no_out").val());
			$("#type3").val($("#type").val());
			$("#service_category3").val($("#service_category").val());
		} else {
			$("#divPrint").hide();
		}
	}

	/////
	function deletePostData(id) {
		$.post('Delete_PostIN_Civilian?' + key + "=" + value, {
			id : id
		}, function(k) {
			alert(k);
			getPostData();
		});
	}
	///Sus No
	function search_sus(sus_obj, unit_id) {
		var sus_no = sus_obj.value;
		var susNoAuto = $("#" + sus_obj.id);
		susNoAuto.autocomplete({
			source : function(request, response) {
				$.ajax({
					type : 'POST',
					url : "getTargetSUSNoList_postin?" + key + "=" + value,
					data : {
						sus_no : sus_no
					},
					success : function(data) {
						var susval = [];
						var length = data.length - 1;
						var enc = data[length].substring(0, 16);
						for (var i = 0; i < data.length; i++) {
							susval.push(dec(enc, data[i]));
						}
						var dataCountry1 = susval.join("|");
						var myResponse = [];
						var autoTextVal = susNoAuto.val();
						$.each(dataCountry1.toString().split("|"), function(i,
								e) {
							var newE = e.substring(0, autoTextVal.length);
							if (e.toLowerCase().includes(
									autoTextVal.toLowerCase())) {
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
					alert("Please Enter Approved Unit SUS No.");
					$('#' + unit_id).val('');
					susNoAuto.val("");
					susNoAuto.focus();
					return false;
				}
			},
			select : function(event, ui) {
				var sus_no = ui.item.value;
				$.post("getTargetUnitNameList?" + key + "=" + value, {
					sus_no : sus_no
				}, function(j) {

				}).done(function(j) {
					var length = j.length - 1;
					var enc = j[length].substring(0, 16);

					$('#' + unit_id).val(dec(enc, j[0]));

				}).fail(function(xhr, textStatus, errorThrown) {
				});
			}
		});
	}

	////Personal No

	function getemployee_no(obj, id) {

		removeMinDate();
		roleSus = $("#" + id).val();
		if (roleSus != '') {
			var employee_no = obj.value;
			var susNoAuto = $("#" + obj.id);
			var perurl;
			perurl = 'getEmployee_NoListApproved_Civilian?';
			susNoAuto.autocomplete({
				source : function(request, response) {
					$.ajax({
						type : 'POST',
						url : perurl + key + "=" + value,
						data : {
							employee_no : employee_no,
							roleSus : roleSus
						},
						success : function(data) {
							var susval = [];
							var length = data.length - 1;
							var enc = data[length].substring(0, 16);
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
						alert("Please Enter Approved Employee No");
						susNoAuto.val("");
						susNoAuto.focus();
						return false;
					}
				},
				select : function(event, ui) {

				}
			});
		} else {
			alert("please Enter From Sus");
			$("#" + id).focus();
			$("#" + id).val('');
		}
	}
	var civ_id = null;
	function employee_number() {
		fn_getOfficerData();
	}

	function removeMinDate() {
		setMinDate('out_auth_dt', '01/01/1890');
		setMinDate('in_auth_dt', '01/01/1890');
		setMinDate('dt_of_sos', '01/01/1890');
		setMinDate('dt_of_tos', '01/01/1890');
	}

	function search_unit_name(unitObj, sus_id) {
		var unit_name = unitObj.value;
		var susNoAuto = $("#" + unitObj.id);
		susNoAuto.autocomplete({
			source : function(request, response) {
				$.ajax({
					type : 'POST',
					url : "getTargetUnitsNameActiveList_postin?" + key + "="
							+ value,
					data : {
						unit_name : unit_name
					},
					success : function(data) {
						var susval = [];
						var length = data.length - 1;
						var enc = data[length].substring(0, 16);
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
					$('#' + sus_id).val('');
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
					$("#" + sus_id).val(dec(enc, data[0]));
				}).fail(function(xhr, textStatus, errorThrown) {
				});
			}
		});
	}

	/////////////////History cancel//////////////

	function RejectCancelHisData(idcan, civ_idcan) {

		$("#idcrh").val(idcan);
		$("#civ_idcrh").val(civ_idcan);
		$("#typecrh").val($("#type").val());
		$("#reject_remarkscrh").val($("#reject_remarks").val());

		if ($("#type").val() == "postin") {
			$("#employee_nocrh").val($("#inemployee_no").val());
			$("#rankcrh").val($("#inrank").val());
			$("#to_sus_nocrh").val($("#to_sus_no_in").val());
			$("#from_sus_nocrh").val($("#from_sus_no_in").val());
			$("#statuscrh").val($("#statusin").val());
		} else {
			$("#employee_nocrh").val($("#employee_no").val());
			$("#rankcrh").val($("#rank").val());
			$("#to_sus_nocrh").val($("#to_sus_no").val());
			$("#from_sus_nocrh").val($("#from_sus_no_out").val());
			$("#statuscrh").val($("#statusout").val());
		}

		document.getElementById('CancelRejectForm').submit();
	}

	function ApprovedCancelHisData(idcan, civ_idcan) {

		$("#idach").val(idcan);
		$("#civ_idach").val(civ_idcan);
		$("#typeach").val($("#type").val());

		if ($("#type").val() == "postin") {
			$("#employee_noach").val($("#inemployee_no").val());
			$("#rankach").val($("#inrank").val());
			$("#to_sus_noach").val($("#to_sus_no_in").val());
			$("#from_sus_noach").val($("#from_sus_no_in").val());
			$("#statusach").val($("#statusin").val());
		} else {
			$("#employee_noach").val($("#employee_no").val());
			$("#rankach").val($("#rank").val());
			$("#to_sus_noach").val($("#to_sus_no").val());
			$("#from_sus_noach").val($("#from_sus_no_out").val());
			$("#statusach").val($("#statusout").val());
		}
		document.getElementById('ApproveCancelForm').submit();
	}

	/* ///bisag 20_03_2023 v2(change invalid status) */
	function search_sus1from(sus_obj, unit_id) {
		//	 	removeMinDate();
		var sus_no = sus_obj.value;
		var susNoAuto = $("#" + sus_obj.id);

		susNoAuto.autocomplete({
			source : function(request, response) {
				$.ajax({
					type : 'POST',
					url : "getSUSNoList_Active_or_Inactive?" + key + "=" + value,
					data : {
						sus_no : sus_no
					},
					success : function(data) {
						var susval = [];
						var length = data.length - 1;
						var enc = data[length].substring(0, 16);
						for (var i = 0; i < data.length; i++) {
							susval.push(dec(enc, data[i]));
						}
						var dataCountry1 = susval.join("|");
						var myResponse = [];
						var autoTextVal = susNoAuto.val();
						$.each(dataCountry1.toString().split("|"), function(i,
								e) {
							var newE = e.substring(0, autoTextVal.length);
							if (e.toLowerCase().includes(
									autoTextVal.toLowerCase())) {
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
					alert("Please Enter Approved Unit SUS No.");
					$('#' + unit_id).val('');
					susNoAuto.val("");
					susNoAuto.focus();
					return false;
				}
			},
			select : function(event, ui) {
				var sus_no = ui.item.value;
				$.post("getUnitNameList_Active_or_Inactive?" + key + "=" + value, {
					sus_no : sus_no
				}, function(j) {

				}).done(function(j) {
					var length = j.length - 1;
					var enc = j[length].substring(0, 16);

					$('#' + unit_id).val(dec(enc, j[0]));

				}).fail(function(xhr, textStatus, errorThrown) {
				});
			}
		});

	}

	function search_unit1_from(obj, id) {
		//	 	removeMinDate();
		var unit_name = obj.value;
		var susNoAuto = $("#" + obj.id);
		susNoAuto.autocomplete({
			source : function(request, response) {
				$.ajax({
					type : 'POST',
					url : "UnitsNameList_active_or_inactive?" + key
							+ "=" + value,
					data : {
						unit_name : unit_name
					},
					success : function(data) {
						var susval = [];
						var length = data.length - 1;
						var enc = data[length].substring(0, 16);
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
					$("#" + id).val('');
					susNoAuto.val("");
					susNoAuto.focus();
					return false;
				}
			},
			select : function(event, ui) {
				var unit_name = ui.item.value;
				$.post("SUSFromUNITNAMEActive_or_InactiveList?" + key + "=" + value, {
					target_unit_name : unit_name
				}, function(data) {
				}).done(function(data) {
					var length = data.length - 1;
					var enc = data[length].substring(0, 16);
					$("#" + id).val(dec(enc, data[0]));
				}).fail(function(xhr, textStatus, errorThrown) {
				});
			}
		});

	}
	function validateDate(inputDate, inputElement) {
	    var currentDate = new Date();
	    var parts = inputDate.split('/');
	    var selectedDate = new Date(parts[2], parts[1] - 1, parts[0]);
	    
	    if (selectedDate > currentDate) {
	        alert("Future dates are not allowed.");
	        inputElement.value = "DD/MM/YYYY";
	        inputElement.style.color = "#000000";
	    }
	}
</script>
