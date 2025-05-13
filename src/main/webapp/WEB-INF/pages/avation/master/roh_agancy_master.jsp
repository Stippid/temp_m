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
<!--  Created By Mitesh  (28-01-2025) -->
<form:form name="ROHagancy_masterUrl" id="ROHagancy_masterUrl"
	action="ROHagency_masterAction" method="post" class="form-horizontal"
	commandName="add_ROHagencyCMD">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5>ALLOTMENT OF REPAIR & OH AGENCY FOR ROTARY WING</h5>
					<h6 class="enter_by">
						<span style="font-size: 12px; color: red;">(To be entered
							by MISO)</span>
					</h6>
				</div>
				<div class="card-body card-block">
					<div class="col-md-12">
						<div style="width: 100%;" >
							<div class="row form-group">
								<div class="col-md-8">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>Type Of Aircraft</label>
								</div>
								<div class="col-md-8">
									<select id="std_nomen" name="std_nomen" class="form-control">
										<option value="">--Select Aircraft--</option>
										<c:forEach var="item" items="${ml_1}">
											<option value="${item}">${item}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12" >
						<div id="rohLocationContainer" style="width: 100%;">
							<div class="row form-group roh-location-group">
								<div class="col-md-5" style="width: 45%;">
									<label class="form-control-label"><strong
										style="color: red;">* </strong>ROH Agency</label> 
										<select id="roh_agency"
										name="roh_agency[]" class="form-control">
										<option value="">--Select--</option>
										<option value="HAL">HAL</option>
									</select>
								</div>
								<div class="col-md-5" style="width: 45%;">
									<label class="form-control-label"><strong
										style="color: red;">* </strong>Location</label> 
										<select id="location" name="location[]" class="form-control">
										<option value="">--Select--</option>
										<c:forEach var="item" items="${getlocList}">
											<option value="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
								<div class="col-md-2" style="width: 10%; margin-top:4%;">
									<button type="button" class="btn btn-success btn-sm"
										onclick="addRohLocation()">+</button>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="card-footer" align="center" class="form-control">
					<input type="reset" class="btn btn-success btn-sm" value="Clear">
					<input type="submit" class="btn btn-primary btn-sm" value="Save"
						onclick="return validate();">
				</div>
			</div>
		</div>
	</div>

</form:form>

<script>
	function validate() {
		if ($("#std_nomen").val() == "") {
			alert("Please select the Type Of Aircraft.");
			return false;
		}
		if ($("#roh_agency").val() == "") {
			alert("Please select the ROH Agency.");
			return false;
		}
		if ($("#location").val() == "") {
			alert("Please select the Location.");
			return false;
		}
		return true;
	}
</script>

<script>
	$(document).ready(function() {
		document.addEventListener('dragenter', function(event) {
			event.preventDefault();
		});

		document.addEventListener('dragover', function(event) {
			event.preventDefault();
		});

		document.addEventListener('drop', function(event) {
			event.preventDefault();
		});
	});

	function isNumber0_9Only(evt) {
		var charCode = (evt.which) ? evt.which : evt.keyCode;
		if (!(charCode >= 48 && charCode <= 57) && charCode != 8) {
			return false;
		}
		return true;
	}
</script>
<script>
	// Function to block special characters and convert text to uppercase
	function handleInput(input) {
		// Convert the value to uppercase
		input.value = input.value.toUpperCase();

		// Regular expression to match any special characters
		const regex = /[^A-Z0-9\s-]/g; // Allow only uppercase letters, numbers, and spaces

		// Replace special characters with an empty string
		input.value = input.value.replace(regex, '');
	}
</script>
<script>
// Function to add a new set of ROH Agency and Location fields
function addRohLocation() {
   let container = document.getElementById("rohLocationContainer");
    
    let newDiv = document.createElement("div");
    newDiv.classList.add("row", "form-group", "roh-location-group");
    
    newDiv.innerHTML = `
        <div class="col-md-5">
            <select id="roh_agency" name="roh_agency[]" class="form-control">
                <option value="">--Select--</option>
                <option value="HAL">HAL</option>
            </select>
        </div>
        <div class="col-md-5">
        <select id="location" name="location[]" class="form-control">
		<option value="">--Select--</option>
		<c:forEach var="item" items="${getlocList}">
		<option value="${item[1]}">${item[1]}</option>
		</c:forEach>
	</select>
        </div>
        <div class="col-md-2">
        <button type="button" class="btn btn-success btn-sm"
			onclick="addRohLocation()">+</button>
            <button type="button" class="btn btn-danger btn-sm" onclick="removeRohLocation(this)">-</button>
        </div>
    `;
    
    container.appendChild(newDiv);
}

// Function to remove a set of ROH Agency and Location fields
function removeRohLocation(btn) {
    btn.closest(".roh-location-group").remove();
}
</script>