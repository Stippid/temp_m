<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>


	<div class="animated fadeIn">
		<div class="">
			<div class="container" align="center">
				<div class="card">
					<div class="card-header"><h3>Deletion Of BA No<br></h3></div>
					<!-- <div class="card-header"><strong>Basic Details</strong></div> -->
					<div class="card-body card-block">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>BA No </label>
									</div>
									<div class="col-md-8">
										<input type="text" id="ba_no" name="ba_no" placeholder="" autocomplete="off"
											class="form-control" oninput="isalphanumeric();"
											maxlength="10" /> <!-- <input type="hidden" id="parent_request_id"
											name="parent_request_id" placeholder="" class="form-control autocomplete" autocomplete="off" /> -->
									</div>
								</div>
							</div>
						</div>
						
						
					</div>
				 	
				<div class="form-control card-footer" align="center">
					<!-- <a href="addition_of_ba_no" class="btn btn-success btn-sm" >Delete</a>  -->
					<input id="submit_id" type="submit" class="btn btn-primary btn-sm" value="Delete" onclick="return validate();">
					
				</div>
			</div>
		</div>
	</div>
</div>


<script>
	function validate() {
		if ($("#ba_no").val() == "") {
			alert("Please Enter the BA No.");
			return false;
		}
		
		var ba_no = $("#ba_no").val();
		  $.post("deletebano?"+key+"="+value, {
              ba_no : ba_no
      }, function(data) {
            
      }).done(function(data) {
              alert(data);
      }).fail(function(xhr, textStatus, errorThrown) {
      });
		
		
	}

	
	function isalphanumeric() {
		var ba_no = document.getElementById("ba_no").value;
		var n_ba_no = ba_no.replace(/[^a-z0-9]/gi, '').toUpperCase();
		document.getElementById("ba_no").value = n_ba_no;
	}
	
</script>

