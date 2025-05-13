<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="Stylesheet" href="js/Calender/jquery-ui.css" >
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<form:form action="edit_mortality_detailsAction" id="edit_mortality_details1" method="post" class="form-horizontal" commandName="edit_mortality_detailsCMD">
   
      <div class="container">
          <div class="card">
              <div class="card-header"  align="center">
		             <h5> EDIT MORTALITY DETAILS</h5>
		             <h6>
						<span style="font-size: 12px; color: red">(To be entered by MP-5)</span>
					 </h6>
		      </div>

			<div class="card-body card-block">
				<div class="row">
					<div class="col-md-12">
						<div class="col-md-8">
							<div class="row form-group">
								<div class="col-md-3">
								<input type="text" id="id" name="id" class="form-control" autocomplete="off">
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;">* </strong>Hospital Name</label>
								</div>
								<div class="col-md-9">
								<input type="text" id="unit_name" name="unit_name"
										class="form-control-sm form-control" placeholder="Search..."
										autocomplete="off"
										title="Type Unit Name or Part of Unit Name to Search">
								</div>
							</div>
						</div>

						<div class="col-md-4">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>SUS No</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="sus_no" path="sus_no"
										class="form-control-sm form-control" placeholder="Search..."
										maxlength="8" autocomplete="off"
										title="Type SUS No or Part of SUS No to Search" />
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">Service</label>
								</div>
								<div class="col-md-8">
									<select id="service" name="service"
										class="form-control-sm form-control" readonly>
										<option value="-1">--Select--</option>
										<c:forEach var="item" items="${getMedSystemCode_SERVICE}" varStatus="num">
											<option value="${item}" name="${item}">${item}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12 form-group">
						<div class="col-md-2">
							<label for="text-input" class=" form-control-label"><strong
								style="color: red;">* </strong>Personnel No</label>
						</div>

						<div class="col-md-10">
							<div class="row form-group">
								<div class="col-md-4">
									<select id="persnl_no1" name="persnl_no1"
										class="form-control-sm form-control">
										<option value="-1">--Select--</option>
										<c:forEach var="item" items="${getMedSystemCode_PERSNL_PRE}" varStatus="num">
											<option value="${item}" name="${item}">${item}</option>
										</c:forEach>
									</select>
								</div>
								<div class="col-md-4">
									<input type="text" id="persnl_no2" name="persnl_no2"
										class="form-control-sm form-control" autocomplete="off" onkeypress="return isNumber0_9Only(event)"
										placeholder="Enter No..." maxlength="10">
								</div>
								<div class="col-md-4">
									<select id="persnl_no3" name="persnl_no3" class="form-control-sm form-control">
										<option value="-1">--Select--</option>
											<c:forEach var="item" items="${getMedSystemCode_PERSNL_SUF}" varStatus="num">
											  <option value="${item}" name="${item}">${item}</option>
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
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;">* </strong>Category</label>
								</div>
								<div class="col-md-8">
									<select name="category" id="category"
										class="form-control-sm form-control" readonly>
										<option value="-1">--Select--</option>
										<c:forEach var="item" items="${getMedSystemCode_CATEGORY}" varStatus="num">
											<option value="${item}" name="${item}">${item}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;">* </strong>Rank</label>
								</div>
								<div class="col-md-8">
									<!-- <select name="rank" id="rank"
										class="form-control-sm form-control">
										<option value="-1">--Select--</option>
									</select> -->
									<select name="death_rank.id" id="rank"
										class="form-control-sm form-control">
										<option value="-1">--Select--</option>
									</select>
								</div>
							</div>
						</div> 
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;">* </strong>Gender</label>
								</div>
								<div class="col-md-8">
									<select name="gender" id="gender"
										class="form-control-sm form-control">
										<option value="-1">--Select--</option>
										<option value="Male">Male</option>
										<option value="Female">Female</option>
										<option value="Other">Other</option>
									</select>
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;">* </strong>Relation</label>
								</div>
								<div class="col-md-8">
									<select id="relation" name="relation"
										class="form-control-sm form-control" readonly="readonly">
										<option value="-1">--Select--</option>
										<c:forEach var="item" items="${ml_5}" varStatus="num">
											<option value="${item}" name="${item}">${item}</option>
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
									<label for="text-input" class="form-control-label">Personnel
										Name</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="persnl_name" path="persnl_name" onkeypress="return onlyAlphabets(event);"
										class="form-control-sm form-control" autocomplete="off"
										oninput="this.value = this.value.toUpperCase()"
										placeholder="Enter Personnel Name..."  maxlength="100" />
										
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">Unit</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="persnl_unit" name="persnl_unit" onkeypress="return onlyAlphaNumeric(event);"
										class="form-control-sm form-control" autocomplete="off"
										placeholder="Search..." maxlength="100"
										oninput="this.value = this.value.toUpperCase()"/>
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;">* </strong>DOB</label>
								</div>
								<div class="col-md-8">
									<input type="date" id="date_of_birth1" name="date_of_birth2"
										autocomplete="off" class="form-control-sm form-control"
										onchange="calculate_age_new(this);" maxlength="3" min="1899-01-01"
										max="${date}">
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">
										Age(yy/mm)</label>
								</div>
									<div class="col-md-4">
						<%-- <form:input id="age_year" path="age_year" class="form-control-sm form-control" maxlength="4"   onkeypress="return isNumberPointKey(event);" autocomplete="off" 
						    placeholder="Enter Year..." readonly ="readonly"/>
					 --%>
					 <input id="age_year" name="age_year" class="form-control-sm form-control" maxlength="4"  
							onkeypress="return isNumberPointKey(event);" autocomplete="off" placeholder="Enter Year..."  readonly="readonly">
										
						</div>
						<div class="col-md-4">
							<%-- <form:input id="age_month" path="age_month" class="form-control-sm form-control" maxlength="2" onkeypress="return isNumberPointKey(event);" autocomplete="off"
								placeholder="Enter Month..." /> --%>
								<input id="age_month" name="age_month" class="form-control-sm form-control" maxlength="2" 
							onkeypress="return isNumberPointKey(event);" autocomplete="off"
								placeholder="Enter Month..." readonly="readonly">
						</div>
							</div>
						</div>
					</div>
			<div class="col-md-12">
				
						 <div class="col-md-6">
		  <div class="row form-group">
			<div class="col-md-4">
               <label for="text-input" class=" form-control-label">Aadhar Card No.</label>
			</div>
			<div class="col-md-8">
				<input type="text" id="adhar_card_no" name="adhar_card_no" class="form-control-sm form-control" 
				 maxlength="12"  onkeypress="return isNumber0_9Only(event)" placeholder=" Please Enter 12 Digit Aadhar card No..." autocomplete="off"/>
			</div>
		  </div>
		</div>
					<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">Contact
										Number(Mobile)</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="contact_no" name="contact_no1"
										
										autocomplete="off" class="form-control-sm form-control"
										onkeypress="return isNumber0_9Only(event)" maxlength="10"
										placeholder="Enter Contact Number...">
								</div>
							</div>
						</div>	
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">
										Total Service (yy/mm) </label>
								</div>
								<div class="col-md-4">
									<input id="total_service_year" name="total_service_year1"
										class="form-control-sm form-control" maxlength="2"
										onkeypress="return isNumberPointKey(event);"
										autocomplete="off" placeholder="Enter Year...">
								</div>
								<div class="col-md-4">
									<input id="total_service_month" name="total_service_month1"
										class="form-control-sm form-control" maxlength="2"
										onkeypress="return isNumberPointKey(event);"
										autocomplete="off" placeholder="Enter Month...">
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;">* </strong>Place of Death</label>
								</div>
								<div class="col-md-8">
									<select name="place_of_death" id="place_of_death"
										class="form-control-sm form-control">
										<option value="-1">--Select--</option>
										<option value="At_Medical_Unit">At Medical Unit</option>
										<option value="Outside_Medical_Unit">Outside Medical
											Unit</option>
										<option value="Other">Other</option>
									</select>
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;">* </strong>Nature of Death</label>
								</div>
								<div class="col-md-8">
									<select name="nature_of_death" id="nature_of_death"
										class="form-control-sm form-control">
										<option value="-1">--Select--</option>
										<option value="PHYSICAL CASUALITY">PHYSICAL CASUALTY</option>
										<option value="BATTLE CASUALITY">BATTLE CASUALTY</option>
										<option value="BATTLE ACCIDENT">BATTLE ACCIDENT</option>
										<option value="OTHERS">OTHERS</option>
									</select>
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;">* </strong>Date of Mortality</label>
								</div>
								<div class="col-md-8">
									<input type="date" id="dt_mortality" name="dt_mortality2"
										class="form-control-sm form-control" autocomplete="off" max="${date}">
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">On
										LB/LC</label>
								</div>
								<div class="col-md-8">
									<select name="on_ib_ic" id="on_ib_ic"
										class="form-control-sm form-control">
										<option value="-1">--Select--</option>
										<option value="LB">LB</option>
										<option value="LC">LC</option>
										<option value="Other">Other</option>
									</select>
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">Op
										Name</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="operation" name="operation"
										class="form-control-sm form-control" autocomplete="off"
										maxlength="100" placeholder="Enter Operation..." onkeypress="return onlyAlphaNumeric(event);"/>
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">Sector</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="sector" name="sector"
										class="form-control-sm form-control" autocomplete="off" onkeypress="return onlyAlphaNumeric(event);"
										maxlength="50" placeholder="Enter Sector..." />
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class="form-control-label">Location</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="location" name="location" onkeypress="return onlyAlphaNumeric(event);"
										class="form-control-sm form-control" autocomplete="off"
										maxlength="100" placeholder="Enter Location..." />
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">Bde/
										Div/ Corps/ Comd</label>
								</div>
								<div class="col-md-8">
									<input  type="text" id="bde_div_corps_comd"
										name="bde_div_corps_comd" class="form-control-sm form-control"
										autocomplete="off" placeholder="Enter Data..." onkeypress="return onlyAlphaNumeric(event);"/>
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">A&D
										Number</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="and_no" name="and_no"
										class="form-control-sm form-control" autocomplete="off" onkeypress="return onlyAlphaNumeric(event);"
										placeholder="Enter A&D Number..." />
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">Death
										Summary</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="death_summary" name="death_summary" onkeypress="return onlyAlphaNumeric(event);"
										class="form-control-sm form-control" autocomplete="off"
										placeholder="Enter Death Summary..." />
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">
										NOK Informed</label>
								</div>
								<div class="col-md-8">
									<select name="nok_informed" id="nok_informed"
										class="form-control-sm form-control" >
										<option value="-1">--Select--</option>
										<option value="Yes">Yes</option>
										<option value="No">No</option>
									</select>
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">Cause
										of Death</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="cause_of_death" name="cause_of_death" onkeypress="return onlyAlphaNumeric(event);"
										class="form-control-sm form-control" autocomplete="off"
										maxlength="255" placeholder="Enter Cause of Death..." />
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">Name
										of NOK</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="name_of_nok" name="name_of_nok" onkeypress="return onlyAlphabets(event);"
										class="form-control-sm form-control" autocomplete="off"
										maxlength="100" placeholder="Enter Name of NOK..." />
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">Address
										of NOK</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="address_of_nok" name="address_of_nok" onkeypress="return onlyAlphaNumeric(event);"
										class="form-control-sm form-control" autocomplete="off"
										maxlength="255" placeholder="Enter Address of NOK..." />
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class="form-control-label"><strong
										style="color: red;">* </strong>ICD Code</label>
								</div>
								<div class="col-md-8">
									<input type="text" name="icd_code" id="icd_code" onkeypress="return onlyAlphaNumeric(event);"
										class="form-control-sm form-control" autocomplete="off"
										maxlength="10" placeholder="Search..." />
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class="form-control-label">ICD
										Cause</label>
								</div>
								<div class="col-md-8">
									<input type="text" name="icd_cause" id="icd_cause"
										class="form-control-sm form-control" autocomplete="off" onkeypress="return onlyAlphaNumeric(event);"
										placeholder="Search..." readonly>
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-2">
							<label for="text-input" class=" form-control-label">Remarks</label>
						</div>
						<div class="col-md-10">
							<textarea rows="2" cols="250" class="form-control char-counter1" onkeypress="return onlyAlphaNumeric(event);"
								placeholder="Enter Your Remarks..." id="remarks" name="remarks"
								maxlength="255"></textarea>
						</div>
						
					</div>
				</div>
			</div>
			<div class="card-footer" align="center">
			     	<a href="mnh_input_search_mortality" class="btn btn-danger btn-sm" id="btn_cancl" >Cancel</a>
		            <input type="submit" id="btn_update" class="btn btn-success btn-sm" value="Update" onclick=" return isvalidData();" />       
           	</div>  
			</div>
		</div> 
</form:form>


<Script>
function isNumber0_9Only(evt) {
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if(charCode != 46 && charCode > 31 && (charCode<48 || charCode>57)){
		return false;
	}else{
		if(charCode == 46){
			return false;
		}
	}
	return true;
}
(function ($) {
    "use strict";
    $.fn.charCounter = function (options) {
        if (typeof String.prototype.format == "undefined") {
            String.prototype.format = function () {
                var content = this;
                for (var i = 0; i < arguments.length; i++) {
                    var replacement = '{' + i + '}';
                    content = content.replace(replacement, arguments[i]);
                }
                return content;
            };
        }
        var options = $.extend({
            backgroundColor: "#FFFFFF",
            position: {
                right: 10,
                top: 10
            },
            font:   {
                size: 10,
                color: "#a59c8c"
            },
            limit: 250
        }, options);
        return this.each(function () {
            var el = $(this),
                wrapper = $("<div/>").addClass('focus-textarea').css({
                    "position": "relative",
                        "display": "inline-block"
                }),
                label = $("<span/>").css({
                    "zIndex": 999,
                        "backgroundColor": options.backgroundColor,
                        "position": "absolute",
                        "font-size": options.font.size,
                        "color": options.font.color
                }).css(options.position);
            if(options.limit > 0){
                label.text("{0}/{1}".format(el.val().length, options.limit));
                el.prop("maxlength", options.limit);
            }else{
                label.text(el.val().length);
            }
            el.wrap(wrapper);
            el.before(label);
            el.on("keyup", updateLabel)
                .on("keypress", updateLabel)
                .on('keydown', updateLabel);
            function updateLabel(e) {
                if(options.limit > 0){
                    label.text("{0}/{1}".format($(this).val().length, options.limit));
                }else{
                    label.text($(this).val().length);
                }
            }
        });
    }
})(jQuery);
$(".char-counter1").charCounter();
</Script>
<script>
function btn_clc(){
	location.reload(true);
}

function chgCategory(){
	$("#persnl_no1").attr('readonly',false);
	$("#persnl_no2").attr('readonly',false);
	$("#persnl_no3").attr('readonly',false);

	$("#persnl_no2").val('');
	$("#persnl_no3").val('-1');
	
	if($("#persnl_no1").val() == "-1"){
		$("#category").val('OR');
	}
	else if($("#persnl_no1").val() == "TJ" || $("#persnl_no1").val() == "JC"){
		$("#category").val('JCO');
	}
       else if($("#persnl_no1").val() == "NR" || $("#persnl_no1").val() == "NS" || 
			$("#persnl_no1").val() == "NL" || $("#persnl_no1").val() == "PN"){
       	$("#category").val('MNS');
	}
       else if($("#persnl_no1").val() != "NR" || $("#persnl_no1").val() != "NS" || 
			$("#persnl_no1").val() != "NL" || $("#persnl_no1").val() != "PN" || $("#persnl_no1").val() != "TJ" || 
			$("#persnl_no1").val() != "JC"){
       	$("#category").val('OFFICER');
	}	
}

function getRank() {
	var category = $("#category").val();
	var service = $("#service").val();
	$("#rank").attr('readonly',false);
	$.post("getMNHRank?"+key+"="+value, {a1 : category,a2 : service},function(j) {
		var options = '<option value="-1">--Select--</option>';

		var a = [];
		var enc = j[j.length - 1].substring(0, 16);
		for (var i = 0; i < j.length; i++) {
			a[i] = dec(enc, j[i]);
		}
		var data = a[0].split(",");
		var datap;

		for (var i = 0; i < data.length - 1; i++) {
			datap = data[i].split(":");
			options += '<option value="'+datap[1]+'" name="' + datap[0]+ '" >'+ datap[0] + '</option>';
		}
		$("#rank").html(options);
		var q = '${edit_mortality_detailsCMD.death_rank.id}';
		if(q != ""){
			$("#rank").val(q);
		}
	});
}

function isNumberPointKey(evt){
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if(charCode != 46 && charCode > 31 && (charCode<48 || charCode>57)){
		return false;
	}else{
		if(charCode == 46){
			return false;
		}
	}
	return true;
}

function isvalidData(){
	var d = new Date();
	var c_d = d.getFullYear()+"-"+("0" + (d.getMonth()+1)).slice(-2)+"-"+("0" + d.getDate()).slice(-2);
	var rel = $("#relation").val();
	
	 if($("#unit_name").val() == ""){
		alert("Please Enter the Hospital Name");
		$("#unit_name").focus();
 		return false;
	} 
	 if($("#sus_no").val() == ""){
		alert("Please Enter the SUS No");
		$("#sus_no").focus();
 		return false;
	 }

	 if ($("#persnl_no2").val() == "" && $("#service").val() != "OTHERS") {
			alert("Please Enter the Personnel No");
			$("#persnl_no2").focus();
	 		return false;
		}
		 if ($("#persnl_no3").val() == "-1" && $("#service").val() != "OTHERS") {
			alert("Please Select the Personnel No");
			$("#persnl_no3").focus();
	 		return false;
		}
	
		 if ($("#category").val() == "-1") {
				alert("Please Select the Category");
				$("#category").focus();
				return false;
			}
		 	if ($("#rank").val() == "-1") {
				alert("Please Select the Rank");
				$("#rank").focus();
				return false;
			} 
			 if ($("#gender").val() == "-1") {
				alert("Please Select the Gender");
				$("#gender").focus();
				return false;
			} 
			 
			 var rel = $("#relation").val();
				
			   if (rel == "-1") {
					alert("Please Select the Relation");
					$("#relation").focus();
			 		return false;
				}
				if ($("#category").val() == "MNS" && $("#gender").val() != "Female") {
			 		alert("category is MNS then gender should be Female(F) only.");
			 		$("#gender").focus();
			 		return false;
			 		}

				if ((rel == "WIFEOF" || rel == "MOTHEROF" || rel == "DAUGHTEROF" || rel == "SISTEROF") && ($("#gender").val() != "Female")) {
			 		alert("Gender should be Female Here");
			 		$("#gender").focus();
			 		return false;
			 		}
				if ((rel == "BROTHEROF" || rel == "HUSBANDOF" || rel == "FATHEROF" || rel == "SONOF") && ($("#gender").val() != "Male")) {
			 		alert("Gender should be Male Here");
			 		$("#gender").focus();
			 		return false;
			 		}
			
				  if ($("#date_of_birth1").val() == "") {
						alert("Please Select the Date Of Birth");
						$("#date_of_birth1").focus();
						return false;
					}
				 if ( $("#age_year").val() < 17) {
				 		alert("Age Year Not Allowed Below 17");
				 		$("#date_of_birth1").focus();
				 		return false;
				 		}
				 if(($("#relation").val() == "SELF" || $("#relation").val() == "MOTHEROF" || $("#relation").val() == "FATHEROF") && $("#age_year").val() < "17"){
				 		alert("Age Year Not Allowed Below 17");
				 		$("#date_of_birth1").focus();
				 		return false;
				 		}
				 if ($("#relation").val() == "SELF" && parseInt($("#age_year").val()) <= parseInt($("#total_service_year").val())) {
				 		alert("Personnel Age Should be greater than Service");
				 		$("#date_of_birth1").focus();
				 		return false;
				 		}
			 

					 if ($("#place_of_death").val() == "-1") {
							alert("Please Select the Place of Death");
							$("#place_of_death").focus();
							return false;
						}
						  if ($("#nature_of_death").val() == "-1") {
							alert("Please Select the Nature of Death");
							$("#nature_of_death").focus();
							return false;
						}
						 if($("#dt_mortality").val() == ""){
							alert("Please Enter the Date of Mortality");
							$("#dt_mortality").focus();
							return false;
						}
						  if($("#dt_mortality").val() > c_d){
							$("#dt_mortality").focus();
							alert("Can't select the Future Date of Mortality");
							return false;
						}
						  if($("#icd_code").val() == ""){
							alert("Please Enter the ICD Code");
							$("#icd_code").focus();
							return false;
						} 
						  var adhar_no = ($("#adhar_card_no").val());
							
							if (!adhar_no == "" && adhar_no != "0" && adhar_no != null  && adhar_no.length != 12 && adhar_no.length != 14)
							{
							alert("Adhaar No Should Contain Maximum 12 Digit");
							return false;
							}
							
		else{
		var q1 = $("#sus_no").val();
		var q2 = $("#id_hid").val();
		var p1 = $("#persnl_no1").val();
		var p2 = $("#persnl_no2").val();
		var p3 = $("#persnl_no3").val();
		var p = p1.concat(p2).concat(p3);
		
		if(q2 != ""){
			$("#service").attr('readonly',false);
			$("#category").attr('readonly',false);
			$("#rank").attr('readonly',false);
			$("#unit_name").attr('readonly',false);
			$("#icd_cause").attr('readonly',false);
			$("#mortality_details_input").submit();
		}
	}
}


jQuery(function() {
	// Source SUS No

	jQuery("#sus_no").keypress(function(){
		var sus_no = this.value;
			 var susNoAuto=jQuery("#sus_no");
			  susNoAuto.autocomplete({
			      source: function( request, response ) {
			        jQuery.ajax({
			        type: 'POST',
			        url: "getTargetSUSNoList?"+key+"="+value,
			        data: {sus_no:sus_no},
			          success: function( data ) {
			        	  var susval = [];
			        	  var length = data.length-1;
			        	  if(data.length != 0){
				        		var enc = data[length].substring(0,16);
				        	}
				        	for(var i = 0;i<data.length;i++){
				        		susval.push(dec(enc,data[i]));
				        	}
				        	var dataCountry1 = susval.join("|");
			            var myResponse = []; 
			            var autoTextVal=susNoAuto.val();
						jQuery.each(dataCountry1.toString().split("|"), function(i,e){
							var newE = e.substring(0, autoTextVal.length);
							if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
							  myResponse.push(e);
							}
						});      	          
						response( myResponse ); 
			          }
			        });
			      },
			      minLength: 1,
			      autoFill: true,
			      change: function(event, ui) {
			    	 if (ui.item) {   	        	  
			        	  return true;    	            
			          } else {
			        	  alert("Please Enter Approved SUS No.");
			        	  document.getElementById("sus_no").value="";
			        	  susNoAuto.val("");	        	  
			        	  susNoAuto.focus();
			        	  return false;	             
			          }   	         
			      }, 
			      select: function( event, ui ) {
			    	  var unit_sus_no = ui.item.value;
			    	 	
  	 			 	$.post("getActiveUnitNameFromSusNo?"+key+"="+value, {sus_no:unit_sus_no}).done(function(j) {				
  	 			 		if(j == ""){
  	 			      	 	alert("Please Enter Approved Unit SUS No.");
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
			      } 	     
			});	
	});
	// End
	
	// Source Unit Name

    jQuery("#unit_name").keypress(function(){
 			var unit_name = this.value;
				 var susNoAuto=jQuery("#unit_name");
				  susNoAuto.autocomplete({
				      source: function( request, response ) {
				        jQuery.ajax({
				        type: 'POST',
				        url: "getTargetUnitsNameActiveList?"+key+"="+value,
				        data: {unit_name:unit_name},
				          success: function( data ) {
				        	  var susval = [];
				        	  var length = data.length-1;
				        	  if(data.length != 0){
					        		var enc = data[length].substring(0,16);
					        	}
					        	for(var i = 0;i<data.length;i++){
					        		susval.push(dec(enc,data[i]));
					        	}
					        	var dataCountry1 = susval.join("|");
				            var myResponse = []; 
				            var autoTextVal=susNoAuto.val();
							jQuery.each(dataCountry1.toString().split("|"), function(i,e){
								var newE = e.substring(0, autoTextVal.length);
								if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
								  myResponse.push(e);
								}
							});      	          
							response( myResponse ); 
				          }
				        });
				      },
				      minLength: 1,
				      autoFill: true,
				      change: function(event, ui) {
				    	 if (ui.item) {   	        	  
				        	  return true;    	            
				          } else {
				        	  alert("Please Enter Approved Unit Name.");
				        	  document.getElementById("unit_name").value="";
				        	  susNoAuto.val("");	        	  
				        	  susNoAuto.focus();
				        	  return false;	             
						}   	         
					}, 
					select: function( event, ui ) {
						 var target_unit_name = ui.item.value;
						
								 	$.post("getTargetSUSFromUNITNAME?"+key+"="+value, {target_unit_name:target_unit_name}).done(function(j) {				
								 		 var length = j.length-1;
				 				         var enc = j[length].substring(0,16);
				 				         jQuery("#sus_no").val(dec(enc,j[0]));	
									}).fail(function(xhr, textStatus, errorThrown) {
							});
					} 	     
				}); 			
 			});

});



$("#icd_code").keyup(function(){
	var code = this.value;
	
	$().Autocomplete2('GET','getMNHDistinctICDList',icd_code,{a:code,b:"ALL"},'','','','','');
});
$("input#persnl_unit").keypress(function(){
	var unit_name = this.value;
		 var susNoAuto=$("#persnl_unit");
		  susNoAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        	type: 'POST',
			    	url: "getTargetUnitsNameActiveListnew?"+key+"="+value,
		        data: {unit_name:unit_name},
		          success: function( data ) {
		        	 var susval = [];
		        	  var length = data.length-1;
		        	  var enc = data[length].substring(0,16);
			        	for(var i = 0;i<data.length;i++){
			        		susval.push(dec(enc,data[i]));
			        	}
			        	   	          
					response( susval ); 
		          }
		        });
		      },
		      minLength: 1,
		      autoFill: true,
		      change: function(event, ui) {
		    	 if (ui.item) {   	        	  
		        	  return true;    	            
		          } else {
		        	  alert("Please Enter Approved Unit Name.");
		        	  document.getElementById("persnl_unit").value="";
		        	  susNoAuto.val("");	        	  
		        	  susNoAuto.focus();
		        	  return false;	             
		          }   	         
		      }
		      	     
		}); 			
});
</script>

<!-- for On Load Methods -->
<script>
/* $(document).ready(function() {

	$("#id").val('${updateid}');
	
	$("#cause_of_death").val('${list[0].cause_of_casuality}');
	$("#unit_name").val('${list[0].hospital_name}');
	$("#operation").val('${list[0].name_of_operation}');
	$("#icd_code").val('${list[0].diagnosis}');
	$("#sector").val('${list[0].sector}');
	$("#dt_mortality").val('${list[0].date_of_casuality}');
	$("#bde_div_corps_comd").val('${list[0].bde}');
	
	 var l ='${list[0].personnel_no}'.length;

	$("#persnl_no1").val('${list[0].personnel_no}'.substring(0,l-6));
	$("#persnl_no2").val('${list[0].personnel_no}'.substring(l-6, l-1));
	$("#persnl_no3").val('${list[0].personnel_no}'.substring(l,l-1));
	$("#persnl_name").val('${list[0].name}');
	$("#gender").val('${list[0].gender_name}');
	
}); */



$(document).ready(function() {
	$("#id").val('${updateid}');
	$("#relation").val("SELF");
	
	$("#service").val("ARMY");
	chgCategory();
	getRank();
	
	$('#persnl_no1').change(function(){
		chgCategory();
		
		if($("#service").val() != "-1" && $("#category").val() != "-1"){
			getRank();
		}
	});
	
	$('#persnl_no3').change(function(){
		if($("#service").val() != "-1" && $("#category").val() != "-1"){
			getRank();
		}
	});
	
	$('#category').change(function(){
		if($("#service").val() != "-1" && $("#category").val() != "-1"){
			getRank();
		}
	});
	
	$('#place_of_death').change(function(){
		var a = this.value;
		if(a == "At_Medical_Unit"){
			$("#unit_name").attr('readonly',false);
		}else{
			$("#unit_name").attr('readonly',true);
		}
	});
	
	$("#icd_code").change(function(){
		var a = this.value.split("-");
		var b = a[0].substring(0,1);
		
		if(b == "S" || b == "T" || b == "V" || b == "W" || b == "X" || b == "Y"){
			$("#icd_cause").attr('readonly',false);
			
			$("#icd_cause").keyup(function(){
				var code = this.value;
				$().Autocomplete2('GET','getMNHDistinctICDList',icd_cause,{a:code,b:"ALL2"},'','','','','');
			});
		}
	});
	
	$("#nok_informed").change(function(){
		var a = this.value;
		alert(a);
		if(a == "Yes"){
			$("#name_of_nok").attr('readonly',false);
			$("#address_of_nok").attr('readonly',false);
		}else{
			$("#name_of_nok").attr('readonly',true);
			$("#address_of_nok").attr('readonly',true);
		}
	});


	 var d = new Date('${edit_mortality_detailsCMD.date_of_death}');
		var c_d = d.getFullYear()+"-"+("0" + (d.getMonth()+1)).slice(-2)+"-"+("0" + d.getDate()).slice(-2); 
		

		
		var l = '${edit_mortality_detailsCMD.persnl_no}'.length;
		$("#id_hid").val('${edit_mortality_detailsCMD.id}');
		$("#unit_name").attr('readonly',true);
		$("#sus_no").attr('readonly',true);
		
		$("#sus_no").val('${edit_mortality_detailsCMD.sus_no}');
		$.post("getMNHUnitNameBySUSNo?"+key+"="+value, {y:'${edit_mortality_detailsCMD.sus_no}'},function(j) {
			var enc = j[j.length - 1].substring(0, 16);
			var a = dec(enc, j[0]);
			$("#unit_name").val(a);
		});
		if('${edit_mortality_detailsCMD.category}' == "OR"){
			$("#persnl_no2").val('${edit_mortality_detailsCMD.persnl_no}'.substring(0,l-1));
			$("#persnl_no3").val('${edit_mortality_detailsCMD.persnl_no}'.substring(l-1,l));
		}
		if('${edit_mortality_detailsCMD.category}' != "OR"){
			
			$("#persnl_no1").val('${edit_mortality_detailsCMD.persnl_no}'.substring(0, 2));
			$("#persnl_no2").val('${edit_mortality_detailsCMD.persnl_no}'.substring(2, l - 1));
			$("#persnl_no3").val('${edit_mortality_detailsCMD.persnl_no}'.substring(l - 1, l));

		}
		$("#persnl_name").val('${edit_mortality_detailsCMD.persnl_name}');
		$("#category").val('${edit_mortality_detailsCMD.category}');
		getRank();
		$("#age_month").val('${edit_mortality_detailsCMD.age_month}');
		$("#total_service_year").val('${edit_mortality_detailsCMD.total_service_year}');
		$("#total_service_month").val('${edit_mortality_detailsCMD.total_service_month}');
		$("#persnl_unit").val('${edit_mortality_detailsCMD.persnl_unit}');
		$("#gender").val('${edit_mortality_detailsCMD.gender}');
		$("#relation").val('${edit_mortality_detailsCMD.relation}');
		$("#place_of_death").val('${edit_mortality_detailsCMD.place_of_death}');
		$("#nature_of_death").val('${edit_mortality_detailsCMD.nature_of_death}');
		$("#dt_mortality").val(c_d);
		$("#operation").val('${edit_mortality_detailsCMD.operation}');
		$("#sector").val('${edit_mortality_detailsCMD.sector}');
		$("#location").val('${edit_mortality_detailsCMD.location}');
		$("#bde_div_corps_comd").val('${edit_mortality_detailsCMD.bde_div_corps_comd}');
		$("#on_ib_ic").val('${edit_mortality_detailsCMD.on_ib_ic}');
		$("#and_no").val('${edit_mortality_detailsCMD.and_no}');
		$("#death_summary").val('${edit_mortality_detailsCMD.death_summary}');
		$("#name_of_nok").val('${edit_mortality_detailsCMD.name_of_nok}');
		$("#nok_informed").val('${edit_mortality_detailsCMD.nok_informed}');
		$("#address_of_nok").val('${edit_mortality_detailsCMD.address_of_nok}');
		$("#remarks").val('${edit_mortality_detailsCMD.remarks}');
		$("#cause_of_death").val('${edit_mortality_detailsCMD.cause_of_death}');
		$("#other_details").val('${edit_mortality_detailsCMD.other_details}');
		$("#icd_code").val('${edit_mortality_detailsCMD.icd_code}');
		$("#icd_cause").val('${edit_mortality_detailsCMD.icd_desc}')
		
		
	 if('${edit_mortality_detailsCMD.icd_code}' != ""){
			$.post("getMNHICDCodeToCauseVi?"+key+"="+value, {a:'${edit_mortality_detailsCMD.icd_code}',b:"2"},function(j) {
				var enc = j[j.length-1].substring(0,16);
				var a = dec(enc,j[0]);
				var data = '${edit_mortality_detailsCMD.icd_code}' + "-" + a;
				$("#icd_code").val(data);
				
				var b = '${edit_mortality_detailsCMD.icd_code}'.substring(0,1);
				if(b == "S" || b == "T" || b == "V" || b == "W" || b == "X" || b == "Y"){
					$("#icd_cause").attr('readonly',false);
				}
			});
		}
			 if('${edit_mortality_detailsCMD.icd_desc}' != ""){
				$.post("getMNHICDCodeToCauseVi?"+key+"="+value, {a:'${edit_mortality_detailsCMD.icd_desc}',b:"2"},function(j) {
					var enc = j[j.length-1].substring(0,16);
					var a = dec(enc,j[0]);
					var data = '${edit_mortality_detailsCMD.icd_desc}' + "-" + a;
					$("#icd_cause").val(data);
				
			});
		}


	var date_of_birth = '${edit_mortality_detailsCMD.date_of_birth}';
	date_of_birth= date_of_birth.substring(0,10);
	
	$("#date_of_birth1").val(date_of_birth);
	
});
</script>