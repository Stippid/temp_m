<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<script type="text/javascript" src="js/printWatermark/common.js"></script>
<script src="js/printWatermark/printAllPages.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/printWatermark/cueWatermark.css">
<script src="js/printWatermark/cueWatermark.js" type="text/javascript"></script>

<form:form name="Search_tail_no" id="Search_tail_no" method="post" class="form-horizontal">
	<div class="animated fadeIn">
		<div class="">
			<div class="container" align="center">
				<div class="card">
					<div class="card-header"><h5><strong>TAIL NO : SEARCH</strong></h5></div>
					<div class="card-body card-block" id="mainViewSelection" style="display: block;">
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
										maxlength="10" oninput="handleInput(this)">
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
							
						</div>
					</div>
					</div>
					<input type="hidden" id="testId_1" name="testId_1" class="form-control">
						<div class="form-control card-footer" align="center" id="btnhide" style="display: block;">
							<a href="Search_tail_no" type="reset" class="btn btn-primary btn-sm"> Clear </a>							
							<button type="button" class="btn btn-primary btn-sm" onclick="Search();">Search</button>
							
						</div>
					</div>
				</div>
			</div>
		</div>
	<c:if test="${getTailNoCurrentStatus.size() != 0}">
    <div class="col s12" id="tableshow">
        <div id="divShow" style="display: block;"></div>
        <div class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
            <span id="ip"></span>
            <table id="getSearchReport" class="table no-margin table-striped table-hover table-bordered">
                <thead style="text-align: center;">
                    <tr>
                        <th style="width: 10%;">Tail No</th>
                        <th style="width: 10%;">ACT</th>
                        <th style="width: 10%;">SUS No</th> <!-- Always show the SUS No column -->
                        <th style="width: 50%;">Unit / Agency Name</th>
                        <th style="width: 20%;">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${getTailNoCurrentStatus}">
                        <tr>
                            <!-- Tail No -->
                            <td style="width: 10%; text-align: center;">${item[0]}</td>

                            <!-- ACT -->
                            <td style="width: 10%; text-align: center;">${item[1]}</td>

                            <!-- SUS No: Show only if classification is not '3' -->
                            <td style="width: 10%; text-align: center;">
                                <c:choose>
                                    <c:when test="${item[2] == '3'}">
                                        - <!-- Display dash for SUS No if classification is '3' -->
                                    </c:when>
                                    <c:otherwise>
                                        ${item[3]} <!-- Display SUS No for other classifications -->
                                    </c:otherwise>
                                </c:choose>
                            </td>

                            <!-- Unit Name or Agency Name based on classification -->
                            <td style="width: 50%; text-align: center;">
                                <c:choose>
                                    <c:when test="${item[2] == '3'}">
                                        ${item[3]} <!-- Show Agency Name if classification is '3' -->
                                    </c:when>
                                    <c:otherwise>
                                        ${item[4]} <!-- Show Unit Name for other classifications -->
                                    </c:otherwise>
                                </c:choose>
                            </td>

                            <!-- Action Button -->
                            <td style="width: 20%; text-align: center;"> ${item[5]}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</c:if>
<c:if test="${getCHTLTailNoCurrentStatus.size() != 0}">
    <div class="col s12" id="tableshow">
        <div id="divShow" style="display: block;"></div>
        <div class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
            <span id="ip"></span>
            <table id="getSearchReport" class="table no-margin table-striped table-hover table-bordered">
                <thead style="text-align: center;">
                    <tr>
                        <th style="width: 10%;">Tail No</th>
                        <th style="width: 10%;">ACT</th>
                        <th style="width: 10%;">SUS No</th> <!-- Always show the SUS No column -->
                        <th style="width: 50%;">Unit / Agency Name</th>
                        <th style="width: 20%;">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${getCHTLTailNoCurrentStatus}">
                        <tr>
                            <!-- Tail No -->
                            <td style="width: 10%; text-align: center;">${item[0]}</td>

                            <!-- ACT -->
                            <td style="width: 10%; text-align: center;">${item[1]}</td>

                            <!-- SUS No: Show only if classification is not '3' -->
                            <td style="width: 10%; text-align: center;">
                                <c:choose>
                                    <c:when test="${item[2] == '3'}">
                                        - <!-- Display dash for SUS No if classification is '3' -->
                                    </c:when>
                                    <c:otherwise>
                                        ${item[3]} <!-- Display SUS No for other classifications -->
                                    </c:otherwise>
                                </c:choose>
                            </td>

                            <!-- Unit Name or Agency Name based on classification -->
                            <td style="width: 50%; text-align: center;">
                                <c:choose>
                                    <c:when test="${item[2] == '3'}">
                                        ${item[3]} <!-- Show Agency Name if classification is '3' -->
                                    </c:when>
                                    <c:otherwise>
                                        ${item[4]} <!-- Show Unit Name for other classifications -->
                                    </c:otherwise>
                                </c:choose>
                            </td>

                            <!-- Action Button -->
                            <td style="width: 20%; text-align: center;"> ${item[5]}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</c:if>
<c:if test="${getRPASTailNoCurrentStatus.size() != 0}">
    <div class="col s12" id="tableshow">
        <div id="divShow" style="display: block;"></div>
        <div class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
            <span id="ip"></span>
            <table id="getSearchReport" class="table no-margin table-striped table-hover table-bordered">
                <thead style="text-align: center;">
                    <tr>
                        <th style="width: 10%;">Tail No</th>
                        <th style="width: 10%;">ACT</th>
                        <th style="width: 10%;">SUS No</th> <!-- Always show the SUS No column -->
                        <th style="width: 50%;">Unit / Agency Name</th>
                        <th style="width: 20%;">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${getRPASTailNoCurrentStatus}">
                        <tr>
                            <!-- Tail No -->
                            <td style="width: 10%; text-align: center;">${item[0]}</td>

                            <!-- ACT -->
                            <td style="width: 10%; text-align: center;">${item[1]}</td>

                            <!-- SUS No: Show only if classification is not '3' -->
                            <td style="width: 10%; text-align: center;">
                                <c:choose>
                                    <c:when test="${item[2] == '3'}">
                                        - <!-- Display dash for SUS No if classification is '3' -->
                                    </c:when>
                                    <c:otherwise>
                                        ${item[3]} <!-- Display SUS No for other classifications -->
                                    </c:otherwise>
                                </c:choose>
                            </td>

                            <!-- Unit Name or Agency Name based on classification -->
                            <td style="width: 50%; text-align: center;">
                                <c:choose>
                                    <c:when test="${item[2] == '3'}">
                                        ${item[3]} <!-- Show Agency Name if classification is '3' -->
                                    </c:when>
                                    <c:otherwise>
                                        ${item[4]} <!-- Show Unit Name for other classifications -->
                                    </c:otherwise>
                                </c:choose>
                            </td>

                            <!-- Action Button -->
                            <td style="width: 20%; text-align: center;"> ${item[5]}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</c:if>

</form:form>
<c:url value="Search_tailno_details" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="code_value1">
		<input type="hidden" name="tail_no1" id="tail_no1" value="0"/>
	</form:form> 
	
<c:url value="tail_noUpatation" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid">
		<input type="hidden" name="updatetail_no" id="updatetail_no" value="0"/>
</form:form>
<c:url value="CHTLtail_noUpatation" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm1" name="updateForm1" modelAttribute="updateid">
		<input type="hidden" name="updatetail_no1" id="updatetail_no1" value="0"/>
</form:form>
<c:url value="RPAStail_noUpatation" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm2" name="updateForm1" modelAttribute="updateid">
		<input type="hidden" name="updatetail_no2" id="updatetail_no2" value="0"/>
</form:form>
<script>
$(document).ready(function() {
	$("div#divwatermark").val('').addClass('watermarked'); 
	watermarkreport();
	 $("#lhser").hide();
     $("#lhhrs").hide();
     
     $("#tail_no").val('${tail_no}');
     var tail_no =  $("#tail_no").val();
     if(tail_no != "")
     {
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

</script>
	
<script type="text/javascript">
jQuery(function() {
	// Source TAIL No
	jQuery("#tail_no").keypress(function() {
		var tail_no = this.value;
		var tailNoAuto = jQuery("#tail_no");
		tailNoAuto.autocomplete({
			source : function(request, response) {
				jQuery.ajax({
					type : 'POST',
					url : "gettailNo?" + key + "=" + value,
					data : {
						tail_no : tail_no,
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
</script>
<script>
	//---------------------------- Print View --------------------------------------------------------
	function getView() {
		if (document.getElementById('PrintViewSelection').style.display == 'none') {
			document.getElementById('PrintViewSelection').style.display = 'block';
			document.getElementById('mainViewSelection').style.display = 'none';
			document.getElementById('btnhide').style.display = 'none';

		} else {
			document.getElementById('mainViewSelection').style.display = 'block';
		}

		window.print();
	}
</script>
<script>

function Search(){
	if($("#tail_no").val() == "")
	{
		alert("Please Enter Tail No.");
	}
	else
	{
		$("#tail_no1").val($("#tail_no").val()) ;
	    jQuery("#WaitLoader").show();
		document.getElementById('searchForm').submit();
	}
}
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
function Update(tail_no){
	   document.getElementById('updatetail_no').value=tail_no;
	   document.getElementById('updateForm').submit();
}
function Edit(tail_no){
	   document.getElementById('updatetail_no1').value=tail_no;
	   document.getElementById('updateForm1').submit();
}
function Modify(tail_no){
	   document.getElementById('updatetail_no2').value=tail_no;
	   document.getElementById('updateForm2').submit();
}
</script>