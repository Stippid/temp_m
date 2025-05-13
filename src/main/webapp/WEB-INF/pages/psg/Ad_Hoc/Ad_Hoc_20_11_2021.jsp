<html><head>
    <title>IT ASSET</title>
  	<link rel="shortcut icon" href="../login_file/favicon.png">
  	<link rel="stylesheet" href="js/assets/css/bootstrap.min.css">
  	<link rel="stylesheet" href="js/assets/css/font-awesome.min.css">
	<link rel="stylesheet" href="js/assets/scss/style.css">
	
	<script type="text/javascript" src="js/assets/js/vendor/jquery-2.1.4.min.js"></script> 
	<script src="js/assets/js/plugins.js"></script> 
	<script src="js/assets/js/main.js"></script> 
	

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet">
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<script src="js/InfiniteScroll/js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.min.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/jquery.mockjax.min.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.mockjax.js"></script>

<link rel="stylesheet" href="js/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="js/layout/css/animation.css">
<link rel="stylesheet" href="js/assets/scss/style.css">
<link rel="stylesheet" href="js/assets/collapse/collapse.css">

<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/assets/adjestable_Col/jquery.resizableColumns.css">


	<script type="text/javascript">
	
	
		var roleAccess = 'Unit';
		var role = 'IT_DEO';
		var user_agent = 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36';
		var army_no = 'IC123456';
		var otpKey = '';
		
		
		
		var tbl, div;
     	function resetTimer() {
        	if (jQuery('#div_timeout').length) {  jQuery('#div_timeout').html(timeout());  }
     	}
     	function timeout() { return '600'; }
     	function getsubmodule(id){ localStorage.setItem("subModule", id); }
     	
     	var key = "_csrf";
     	var value = "3b596ad3-3205-4709-835e-4867dcda32af";
     	
     	jQuery(document).on('keypress', function(event) {
     		localStorage.setItem("army_no", army_no);
     		
     		var regex = new RegExp("^[a-zA-Z0-9\\[\\] \\+ \\* \\-.,/ ~!@#$^&%_]+$");
     	    var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
     	    if (!regex.test(key)) {
     	       event.preventDefault();
     	       return false;
     	    } 
     	});
     	
   		jQuery(document).ready(function() {
   			jQuery('body').bind('cut copy paste', function (e) {
	   	        e.preventDefault();
					
				
	   	    });
   			
   			// set current sub module
   			jQuery('ul#Dropdown_'+localStorage.getItem("subModule")).attr("class","dropdown-menu scrollbar show");
   			setInterval(function() {
				var today = new Date();
				var date =("0" + today.getDate()).slice(-2)+'-'+ ("0" + (today.getMonth()+1)).slice(-2)+'-'+today.getFullYear();
				var time = ("0" + today.getHours()).slice(-2) + ":" + ("0" + today.getMinutes()).slice(-2);// + ":" + ("0" + today.getSeconds()).slice(-2);
				var dateTime = date+' '+time;
				jQuery("#datetime").text(dateTime);
				
				if (jQuery('#div_timeout').length) {
	            	 var tt = jQuery('#div_timeout').html();
	                 if (tt === undefined) {
	                     tt = timeout();
	                 }
	                 var ct = parseInt(tt, 10) - 1;
	                 jQuery('#div_timeout').html(ct.toString().padStart(3, '0'));
	                 if (ct === 0) {
	                	 formSubmit();
	                 }
	             } else {
	            	 formSubmit();
	             }
			}, 1000);
			try
			{
				var msg = document.getElementById("msg").value;
				if(msg != null )
				{
					alert(msg);
				}
			}
			catch (e) {
			}
		});
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
		popupWindow = null;
		function parent_disable() {
			if(popupWindow && !popupWindow.closed)
				popupWindow.focus();
		}
	</script>
	<script>
		document.onkeydown = function(e) {
			if(e.keyCode == 123) { return false; }
			if(e.keyCode == 44) {  return false; }
			if(e.ctrlKey && e.keyCode == 'E'.charCodeAt(0)){ return false; } 
			if(e.ctrlKey && e.shiftKey && e.keyCode == 'I'.charCodeAt(0)){ return false; }
			if(e.ctrlKey && e.shiftKey && e.keyCode == 'J'.charCodeAt(0)){ return false; }
			if(e.ctrlKey && e.keyCode == 'U'.charCodeAt(0)){ return false; }
			if(e.ctrlKey && e.keyCode == 'S'.charCodeAt(0)){ return false; }
			if(e.ctrlKey && e.keyCode == 'H'.charCodeAt(0)){ return false; }
			if(e.ctrlKey && e.keyCode == 'A'.charCodeAt(0)){ return false; }
			if(e.ctrlKey && e.keyCode == 'E'.charCodeAt(0)){ return false; }
		}
	</script>
</head>
<body onfocus="parent_disable();" onclick="parent_disable();resetTimer();" oncontextmenu="return false" style="">
		
		
<script>
$(document).ready(function() {
	
	 colAdj("show");
});
	 
	 </script>
<script>
	var username="PARTH_DEO";
	var curDate = "20-09-2021 14:29:22";
	
	//var addiphostna ='0:0:0:0:0:0:0:1';
</script>
	
	<form action="/It_Asset_01_09_2021_v2/j_spring_security_logout" method="post" id="logoutForm">
		<input type="hidden" name="_csrf" value="3b596ad3-3205-4709-835e-4867dcda32af">
	</form>
	<aside id="left-panel" class="left-panel">
		<nav class="navbar navbar-expand-sm navbar-default">
	      	<div align="center">
 				<span style="position: relative; color: white;border-radius: 2px;"> Session timeout in &nbsp; <i style="font-size: 10px;" class="fa fa-hourglass fa-spin"></i>  :  <b style="color: orangered; min-width: 20px" id="div_timeout">213</b></span>
 			</div>
	  	</nav>
</aside>
		<div id="right-panel" class="right-panel"> 
			<header id="header" class="header">
		    	<div class="header-menu">
		    		<div class="col-md-2 col-sm-2"> <a id="menuToggle" class="menutoggle pull-left"><i class="fa fa fa-tasks"></i></a>
		        		<div class="header-left">
		          			<div class="dropdown for-notification"> <img src="js/miso/images/indian-army-logo.png" class="img-fluid" style="height: 55px;"> </div>
		        		</div>
		      		</div>
		      		<div class="col-md-8 col-sm-8">
		      			<div align="center"> <strong style="font-size: 22px;color: #800000;">IT ASSET</strong> <br> <strong style="font-size: 15px;color: #030080;">VERSION 1.0</strong></div>
		      		</div>
		      		<div class="col-md-2 col-sm-2">
		        	<div class="language-select dropdown" id="language-select" align="right"> <img src="js/miso/images/dgis-logo.png" class="img-fluid" style="max-width:130px; height: 50px;"></div>
		      		</div>
		    	</div>
		  	</header>
		  	<div class="ticker dash-tic" align="right">
				<h3></h3>
				<label id="datetime" style="position: relative; color: white;border-radius: 5px;background: #672a2a;">20-09-2021 14:45</label>
				<a href="javascript:formSubmit();" class="btn btn-danger" type="submit" style="border-radius: 5px; position: relative; float: right !important;" onclick="localStorage.clear();">Logout</a>
 			</div>
		  	<p></p>
		  	<div class="content mt-3" style="margin-top:-13px !important;"> 
		  		<div id="WaitLoader" style="display:none;" align="center">
					<span id="">Processing Data.Please Wait ...<i style="font-size: 18px;" class="fa fa-hourglass fa-spin"></i></span>
				</div>
				




<style>
.ui-tooltip {
	position: absolute;
	top: 110px;
	left: 100px;
	
	
}


</style>
<div class="third_2" id="third_2">
<div id="data_1">
  <div id="data" style="overflow-x: auto; max-width: 1600px; border:solid black;">


     

     <table border="1" style="border-color: black;    width: 4000px;" id="show" >

    
        <thead>
         <!---  <tr>
            <th colspan="6" style="text-align: center; background-color: #ffcc00">PERSONAL DETAILS</th>
             <th colspan="4" style="text-align: center; background-color: #99cc00">PRIMARY SKILLS</th>
            <th colspan="10" style="text-align: center;background-color: #ffa366">SECONDARY SKILLS</th> 
            <th colspan="4" style="text-align: center; background-color: #b3d1ff">BODY WEIGHT DETAILS</th>
             <th rowspan="3"  style="text-align: center; background-color: #00cc00">ACTION</th> 
              
          </tr>  --->


       <tr>
                <th align="center" rowspan="2" style="background-color: #264e58;">Ser No</th>
                <th rowspan="2" style="background-color: #264e58"><strong style="color: red;">* </strong>Computing Assets Type</th>
                <th rowspan="2" style="background-color: #264e58"><strong style="color: red;">* </strong>Make/Brand Name</th>
                <th rowspan="2" style="background-color: #264e58"><strong style="color: red;">* </strong>Model Name</th>
                <th rowspan="2" style="background-color: #264e58"><strong style="color: red;">* </strong>Processor Type</th>
                <th rowspan="2" style="background-color: #264e58"><strong style="color: red;">* </strong>RAM Capacity</th>
               <th style="background-color: #264e58"><strong style="color: red;">* </strong>HDD Capacity</th>
                <th style="background-color: #264e58"><strong style="color: red;">* </strong>Operating System</th>
                <th style="background-color: #264e58">Office</th>
                <th style="background-color: #264e58"><strong style="color: red;">* </strong>AntiVirus Installed</th>
                <th style="background-color: #264e58"><strong style="color: red;">* </strong>AntiVirus</th>
                <th style="background-color: #264e58"><strong style="color: red;">* </strong>OS/Firmware Activation and subsequent Patch Updation Mechanism</th>
                <th style="background-color: #264e58"><strong style="color: red;">* </strong>Dply Envt as Applicable</th>
                <th style="background-color: #264e58">RAM Slot Quantity</th>
                <th style="background-color: #264e58">CD Drive</th>
                <th style="background-color: #264e58">Warranty</th>
                <th style="background-color: #264e58">Warranty Upto</th>
                <th style="background-color: #264e58"><strong style="color: red;">* </strong>Model Number</th>
                <th style="background-color: #264e58"><strong style="color: red;">* </strong>Machine No.</th>
                <th style="background-color: #264e58">MAC Address</th>
                <th style="background-color: #264e58">IP Address</th>
                <th style="background-color: #264e58"><strong style="color: red;">* </strong>Serviceable State</th>
                <th style="background-color: #264e58"><strong style="color: red;">* </strong>Un-serviceable State</th> 
				<th style="background-color: #264e58"><strong style="color: red;">* </strong>Un-servicable from</th>
				<th style="background-color: #264e58">Proc Cost</th>
				<th style="background-color: #264e58">Proc Date</th>
				<th style="background-color: #264e58"><strong style="color: red;">* </strong>Budget Head</th>
				<th style="background-color: #264e58"><strong style="color: red;">* </strong>Budget Code</th>
				<th style="background-color: #264e58">Action</th>
            </tr>       
       
       
          <!-- <th colspan="6" ></th> 
           <th style="text-align: center; background-color: #99cc00;width: 2%">120</th>
          <th style="text-align: center; background-color: #99cc00">65</th>
          <th style="text-align: center; background-color: #99cc00">35</th>
          <th style="text-align: center; background-color: #99cc00">10</th>
          <th style="text-align: center; background-color: #ffa366">100</th>
          <th style="text-align: center; background-color: #ffa366">30</th>
          <th style="text-align: center; background-color: #ffa366">30</th>
          <th style="text-align: center; background-color: #ffa366">30</th>
          <th style="text-align: center; background-color: #ffa366">30</th>
          <th style="text-align: center; background-color: #ffa366">30</th>
          <th style="text-align: center; background-color: #ffa366">30</th>
          <th style="text-align: center; background-color: #ffa366">30</th>
          <th style="text-align: center; background-color: #ffa366">30</th>
          <th style="text-align: center; background-color: #ffa366">30</th>
          <th colspan="3" style="text-align: center; background-color: #b3d1ff"></th>  
    <th style="text-align: center; background-color: #0099ff"></th>
          <th style="text-align: center; background-color: #0099ff"></th>
          <th style="text-align: center; background-color: #b3d1ff">30</th>  -->
          
         
          <!-- <th colspan="3" ></th>
          <th style="text-align: center;">30</th> -->
      </thead> 
        <tbody id="tb1">  
            <tr id="tr_id1">
              <td align="center" width='1%;'>1</td>
                <td><select id="asset_type1" name="asset_type1" class="form-control" >
	<option value="0">--Select--</option>
<option value='9' name='AIO'>AIO</option>
<option value='10' name='LAPTOPS'>LAPTOPS</option>
<option value='11' name='SERVERS'>SERVERS</option>
</select>
 </td>  <td ><select id="make_name1" name="make_name1" class="form-control" >	<option value="0">--Select--</option>
<option value='1' name='MICROTEK'>MICROTEK</option>
<option value='2' name='HP'>HP</option>
<option value='3' name='RICHO'>RICHO</option>
<option value='4' name='BARCO'>BARCO</option>
<option value='5' name='ASUS'>ASUS</option>
<option value='6' name='IMAC PRO'>IMAC PRO</option>
<option value='7' name='CISCO'>CISCO</option>
<option value='8' name='NERGEAR'>NERGEAR</option>
<option value='9' name='MICROSOFT'>MICROSOFT</option>
</select>
</td>
<td ><select id="model_name1" name="model_name1" class="form-control">	<option value="0">--Select--</option>
<option value='1' name='CELERON DUAL CORE'>CELERON DUAL CORE</option>
<option value='2' name='HP 1125 VA'>HP 1125 VA</option>
<option value='3' name='HP LASERJET PRO'>HP LASERJET PRO</option>
<option value='4' name='DELL'>DELL</option>
<option value='5' name='HP'>HP</option>
</select> </td>
                <td width='15%;'><select id="processor_type1" name="processor_type1" class="form-control">
										<option value="0" selected="selected">--Select--</option>
<option value='11' name='AMD RYZEN 9'>AMD RYZEN 9</option>
<option value='10' name='INTEL ATOM'>INTEL ATOM</option>
<option value='12' name='M1'>M1</option>
<option value='7' name='AMD RYZEN 7'>AMD RYZEN 7</option>
<option value='5' name='AMD RYZEN 3'>AMD RYZEN 3</option>
<option value='9' name='INTEL CELERON'>INTEL CELERON</option>
<option value='2' name='INTEL I5'>INTEL I5</option>
<option value='1' name='INTEL I3 AND SIMILAR'>INTEL I3 AND SIMILAR</option>
<option value='4' name='INTEL I9'>INTEL I9</option>
<option value='6' name='AMD RYZEN 5'>AMD RYZEN 5</option>
<option value='3' name='INTEL I7'>INTEL I7</option>
</select></td>
                <td ><select id="ram_capi1" name="ram_capi1" class="form-control">
<option value="0" selected="selected">--Select--</option>
<option value='1' name='2GB'>2GB</option>
<option value='2' name='4GB'>4GB</option>
<option value='3' name='8GB'>8GB</option>
<option value='4' name='16GB'>16GB</option>
<option value='5' name='32GB'>32GB</option>
<option value='7' name='64GB'>64GB</option>
<option value='8' name='12435GB'>12435GB</option>
<option value='9' name='20GB'>20GB</option>
<option value='10' name='1212MB'>1212MB</option>
<option value='11' name='40GB'>40GB</option>
<option value='12' name='2400MB'>2400MB</option>
</select>
                </td>
             
                <td ><select id="hdd_capi1" name="hdd_capi1" class="form-control">
<option value="0" selected="selected">--Select--</option>
<option value='1' name='125GB'>125GB</option>
<option value='2' name='250GB'>250GB</option>
<option value='3' name='500GB'>500GB</option>
<option value='4' name='1TB'>1TB</option>
<option value='5' name='2TB'>2TB</option>
<option value='12' name='2 TB'>2 TB</option>
</select></td>
									
                <td  ><select id="operating_system1" name="operating_system1" class="form-control">
<option value="0" selected="selected">--Select--</option>
<option value='1' name='WINDOWS'>WINDOWS</option>
<option value='2' name='MACOS'>MACOS</option>
<option value='3' name='LINUX'>LINUX</option>
<option value='5' name='ANDROID'>ANDROID</option>
<option value='18' name='UBUNTU'>UBUNTU</option>
</select></td>
							
<td  ><select id="office1" name="office1" class="form-control">
<option value="0" selected="selected">--Select--</option>
<option value='2' name='MS OFFICE 2010'>MS OFFICE 2010</option>
<option value='3' name='MS OFFICE 2013'>MS OFFICE 2013</option>
<option value='4' name='MS OFFICE 2016'>MS OFFICE 2016</option>
<option value='5' name='MS OFFICE 2019'>MS OFFICE 2019</option>
<option value='1' name='MS OFFICE 2007 EDITION'>MS OFFICE 2007 EDITION</option>
<option value='6' name='LIBRE OFFICE'>LIBRE OFFICE</option>
</select></td>
 <td ><div class="col-md-8" >
									<input id="antiviruscheckyes1" name="antiviruscheck"  type="radio" value="Yes" checked="checked"  onclick="anti_show(this,1);">
									&nbsp; <label for="yes">Yes</label>&nbsp;
									<input id="antiviruscheckno1" name="antiviruscheck"  type="radio" value="No" onclick="anti_show(this,1);">
									&nbsp; <label for="no">No</label>
								</div></td>
<td ><select id="antivirus1" name="antivirus1" class="form-control">
<option value="0" selected="selected">--Select--</option>
<option value='6' name='AVAST'>AVAST</option>
<option value='1' name='QUICKHEAL'>QUICKHEAL</option>
</select></td>
							
<td ><select id="os_patch1" name="os_patch1" class="form-control">
<option value="0" selected="selected">--Select--</option>
<option value='1' name='ONLINE  ON INTERNET'>ONLINE  ON INTERNET</option>
<option value='2' name='ONLINE ON ADN'>ONLINE ON ADN</option>
<option value='3' name='MANUAL IN OFFLINE MODE'>MANUAL IN OFFLINE MODE</option>
</select></td>
							
<td ><select id="dply_envt1" name="dply_envt1" class="form-control">
<option value="0" selected="selected">--Select--</option>
<option value='6' name='STAND ALONE'>STAND ALONE</option>
<option value='5' name='INTERNET'>INTERNET</option>
<option value='7' name='LAN'>LAN</option>
<option value='8' name='CWN'>CWN</option>
<option value='9' name='ADN'>ADN</option>
<option value='10' name='INDEP NW VIZ CICG'>INDEP NW VIZ CICG</option>
</select></td>
<td ><input id="ram_slot_qty1" name="ram_slot_qty1" min="1" max="1000" type="number" class="form-control" value="0" autocomplete="off"></td>
 <td ><div class="col-md-8">
										<input id="scan1" name="cd_drive1" type="radio" value="Yes">
										&nbsp; <label for="yes">Yes</label>&nbsp;
										<input id="scan1" name="cd_drive1" checked="checked" type="radio" value="No">
										&nbsp; <label for="no">No</label>
									</div></td>
<td ><div class="col-md-8">
									<input id="warrantycheckyes1" name="warrantycheck"  type="radio" value="Yes" checked="checked" onclick="warrenty_show(this,1);">
									&nbsp; <label for="yes">Yes</label>&nbsp;
									<input id="warrantycheckno1" name="warrantycheck" type="radio" value="No" onclick="warrenty_show(this,1);">
									&nbsp; <label for="no">No</label>
								</div></td>
<td ><input type="date" name="warranty_dt1" id="warranty1" maxlength="10"  class="form-control"></td>
<td >	<input type="text" id="model_number1" name="model_number1" class="form-control " autocomplete="off" onkeypress="return isNumber(event);"></td>
<td >	<input type="text" id="machine_number1" name="machine_number1" class="form-control" autocomplete="off" onkeypress="return isNumber(event);"></td>
<td ><input  type="text" id="mac_address1" name="mac_address1" class="form-control " maxlength="17" autocomplete="off" onkeyup="makeMacAddress(this);"></td>
<td ><input  type="text" id="ip_address1" name="ip_address1" maxlength="15" class="form-control " autocomplete="off" onchange="ValidateIPaddress(this,1);"></td>
<td ><select id="s_state1" name="s_state1" class="form-control" onchange="serviceStChange(this,1);">
														<option value="0">--Select--</option>
														
															<option name="Serviceable" value="1">Serviceable</option>
														
															<option name="Un-serviceable" value="2">Un-serviceable</option>
														
													</select></td> 
 <td ><select id="unserviceable_state1" name="unserviceable_state1" class="form-control">
														<option value="0" selected="selected">--Select--</option>
														
															<option name="BER" value="1">BER</option>
														
															<option name="Minor Repair" value="2">Minor Repair</option>
														
															<option name="DOWNGRADED" value="3">DOWNGRADED</option>
														
													</select></td>
<td ><input type="date" name="unsv_from_dt1" id="unsv_from_dt1" maxlength="10"  class="form-control"></td> 
<td ><input type="text" class="form-control" id="b_cost1" name="b_cost1" onkeypress="return isNumber(event);"></td>
<td ><input type="date" name="proc_dt1" id="proc_date1" maxlength="10"></td>
<td ><select id="b_head1" name="b_head1" class="form-control" >
<option value="0" selected="selected">--Select--</option>
<option value='4' name='WSE1'>WSE1</option>
</select></td>
							
<td ><select id="b_code1" name="b_code1" class="form-control"><option value="0" selected="selected">--Select--</option>
<option value='4' name='EW'>EW</option>
</select></td>
<td width='6%' onclick="formopen(1)" id="id_add1"  align="center" /><i class="fa fa-plus" aria-hidden="true" style="color:#17a2b8;font-size:25px;"></i></td>
</tr>
<!-- onkeypress="return validateInt(event)"  -->




         </tbody>
  
    </table>
<div id="bottom_anchor"></div>
      <td class="center" class="avgs"><span id="avg" hidden="true">0</span></td>
            <td class="center" class="avgs"><span id="avg1" hidden="true">0</span></td>

</div>
   <input type="hidden" id="count" name="count" value="1">
</div> 
<!-- export table start -->
<!--
<div class="first_1" id="first_1" style="display: none;" > 
     <table border="4" id="table_show_1" >
   
      <thead>
        <tr>
          <th>Month Validation</th>
          <th>Sus No</th>
          <th>Unit Name</th>
          <th>Sub Unit Tested</th>
          <th>Camp Layout</th>
          <th>Briefing</th>
          <th>Snap Sit</th>
          <th>Op Role</th>
          <th>Debriefing</th>
          <th>Tug Of War</th>
          <th>Social Aspects</th>
          <th>Adm Task</th>
           <!-- <th>Total</th> -->

         
        </tr>
      </thead>
      <tbody>  
      </tbody>
  
    </table> 
  </div>

  <div class="second_2" id="second_2"> 


<!-- style="display: none;" -->
<table border="4" id="table_show_2" style="display: none;" > 
  <thead>
    <tr>
				
                <th >Computing Assets Type</th>
                <th >Make/Brand Name</th>
                <th >Model Name</th>
                <th >Processor Type</th>
                <th >RAM Capacity</th>
                <th >HDD Capacity</th>
                <th >Operating System</th>
                <th >Office</th>
                <th >AntiVirus Installed</th>
                <th >AntiVirus</th>
                <th >OS/Firmware Activation and subsequent Patch Updation Mechanism</th>
                <th >Dply Envt as Applicable</th>
                <th >RAM Slot Quantity</th>
                <th >CD Drive</th>
                <th >Warranty</th>
                <th >Warranty Upto</th>
                <th >Model Number</th>
                <th >Machine No</th>
                <th >MAC Address</th>
                <th >IP Address</th>
                <th >Serviceable State</th>
                <th >Un-serviceable State</th>
				<th >Un-servicable from</th>
				<th >Proc Cost</th>
				<th >Proc Date</th>
				<th >Budget Head</th>
				<th >Budget Code</th>
    </tr>
  </thead>
  <tbody>
  </tbody> 
</table>
   </div>
<br/>
<center><input type="btnExport" class="btn btn-primary btn-sm" onclick="return AddData_second()" value="Export To Excel" ></center>

 <br><br><br>
<!-- export table end -->
  </div>
  


</div>


  </div>
</div>
<div class="digital_india" style="position: fixed;bottom: 20px;">
    <img src="image/digitalindia.png" class="img-fluid">
  </div>
 
</body>

<!-- <script type="text/javascript">
        
    </script> -->
    
<script>

/*function moveScroll(){
    var scroll = $(window).scrollTop();
    var anchor_top = $("#show").offset().top;
    var anchor_bottom = $("#bottom_anchor").offset().top;
    if (scroll>anchor_top && scroll<anchor_bottom) {
    clone_table = $("#clone");
    if(clone_table.length == 0){
        clone_table = $("#show").clone();
        clone_table.attr('id', 'clone');
        clone_table.css({position:'fixed', 
                  'border' : '4',
                 'pointer-events': 'none',
                 top:0});
        clone_table.width($("#show").width());
        $("#addQuantity").append(clone_table);
        $("#clone").css({visibility:'hidden'});
        $("#clone thead").css({visibility:'visible'});
    }
    } else {
    $("#clone").remove();
    }
}
$(window).scroll(moveScroll);*/
 function moveScroll(){
    var scroll = $(window).scrollTop();
    var anchor_top = $("#show").offset().top;
    var anchor_bottom = $("#bottom_anchor").offset().top;
    if (scroll > anchor_top && scroll < anchor_bottom) {
        clone_table = $("#clone");
        if(clone_table.length === 0) {          
            clone_table = $("#show").clone();
            clone_table.attr({id: "clone"})
            .css({
                position: "fixed",
                "pointer-events": "none",
                 top:0
            })
            .width($("#show").width());

            $("#addQuantity").append(clone_table);
            // dont hide the whole table or you lose border style & 
            // actively match the inline width to the #maintable width if the 
            // container holding the table (window, iframe, div) changes width          
            $("#clone").width($("#show").width());
            // only the clone thead remains visible
            $("#clone thead").css({
                visibility:"visible"
            });
            // clone tbody is hidden
            $("#clone tbody").css({
                visibility:"hidden"
            });
            // add support for a tfoot element
            // and hide its cloned version too
            /*var footEl = $("#clone tfoot");
            if(footEl.length){
                footEl.css({
                    visibility:"hidden"
                });
            }*/
        }
    } 
    else {
        $("#clone").remove();
    }
}
$(window).scroll(moveScroll);



 x=1;//declare export variable 
function formopen_re(R){
   $("tr#tr_id"+R).remove();
   R = R-1;
// x = x-1;
   $("input#count").val(R);
   $("#id_add"+R).show();
   $("#id_remove"+R).show();
   getPersCount();
}
function formopen(x){

//document.getElementById('show').style.width="100%";

//endurun(x);


if($("#asset_type"+x).val()==0 || $("#asset_type"+x).val()==""){
		alert("Please Select Computing Assets Type ");
		$("#asset_type"+x).focus();
		return false;
		}
		
		if($("#make_name"+x).val()==0 || $("#make_name"+x).val()==""){
		alert("Please Select Make/Brand Name");
		$("#make_name"+x).focus();
		return false;
		}
		
		
		if($("#model_name"+x).val()==0 || $("#model_name"+x).val()==""){
		alert("Please Select Model Name");
		$("#model_name"+x).focus();
		return false;
		}
		
		if($("#processor_type"+x).val()==0 || $("#processor_type"+x).val()==""){
		alert("Please Select Processor Type");
		$("#processor_type"+x).focus();
		return false;
		}
		
		
		if($("#ram_capi"+x).val()==0 || $("#ram_capi"+x).val()==""){
		alert("Please Select Ram Capacity");
		$("#ram_capi"+x).focus();
		return false;
		}
		
		if($("#hdd_capi"+x).val()==0 || $("#hdd_capi"+x).val()==""){
		alert("Please Select HDD Capacity");
		$("#hdd_capi"+x).focus();
		return false;
		}
		
		if($("#operating_system"+x).val()==0 || $("#operating_system"+x).val()==""){
		alert("Please Select Operating System");
		$("#operating_system"+x).focus();
		return false;
		}
		
		
		
		var antivirusChecked=$('input[name="antiviruscheck"]:checked').val();

		
	if($(antivirusChecked == "Yes")){
		if($("#antivirus"+x).val()==0 || $("#antivirus"+x).val()==""){
			alert("Please Select AntiVirus  ");
			$("#antivirus"+x).focus();
			return false;
			}
		}
		
		if($("#os_patch"+x).val()==0 || $("#os_patch"+x).val()==""){
		alert("Please Select OS/Firmware Activation and subsequent Patch Updation Mechanism");
		$("#os_patch"+x).focus();
		return false;
		}
		
		
		if($("#dply_envt"+x).val()==0 || $("#dply_envt"+x).val()==""){
		alert("Please Select Dply Envt as Applicable");
		$("#dply_envt"+x).focus();
		return false;
		}
		
		if($("#model_number"+x).val()==0 || $("#model_number"+x).val()==""){
		alert("Please Enter Model Number");
		$("#model_number"+x).focus();
		return false;
		}
		
		if($("#machine_number"+x).val()==0 || $("#machine_number"+x).val()==""){
		alert("Please Enter Machine Number");
		$("#machine_number"+x).focus();
		return false;
		}
		
		
		
		if( $("#b_cost"+x).val()=="0" ){
		alert("Proc Cost Must be Greater Than Zero");
		$("#b_cost"+x).focus();
		return false;
		} else if ($("#b_cost"+x).val() > 1000000000) {
		alert("Proc Cost cannot be greater Than 100 Crores");
		$("#b_cost"+x).focus();
		return false;
		}
		
		
		if( $("#s_state"+x).val()=="" || $("#s_state"+x).val()=="0"){
		alert("Please Select Serviceable State");
		$("#s_state"+x).focus();
		return false;
	}
	
	
	if($("#s_state"+x).val()==2){
		if( $("#unserviceable_state"+x).val()=="" || $("#unserviceable_state"+x).val()=="0"){
			alert("Please Select UN-Serviceable State");
			$("#unserviceable_state"+x).focus();
			return false;
		}
	
		if( $("#unsv_from_dt"+x).val()=="" || $("#unsv_from_dt"+x).val()=="0"){
			alert("Please Select UN-Serviceable From Date");
			$("#unsv_from_dt"+x).focus();
			return false;
		}
	
}	if( $("#b_head"+x).val()=="0"){
		alert("Please Select Budget Head");
		$("#b_head"+x).focus();
		return false;
	}
	
	if( $("#b_code"+x).val()=="0"){
		alert("Please Select Budget Code");
		$("#b_code"+x).focus();
		return false;
	}
   $("#id_add"+x).hide();
   $("#id_remove"+x).hide();
  
   x= x+1;

   

     $("input#count").val(x);
     $("table#show").append('<tr id="tr_id'+x+'"><td align="center" width="1%;">'+x+'</td>'
	
	 
        +'<td><select id="asset_type'+x+'" name="asset_type'+x+'" class="form-control" >'+'<option value="0">--Select--</option>'
+'<option value="9" name="AIO">AIO</option>'
+'<option value="10" name="LAPTOPS">LAPTOPS</option>'
+'<option value="11" name="SERVERS">SERVERS</option>'
+'</select>'
+'</td> '+' <td ><select id="make_name'+x+'" name="make_name'+x+'" class="form-control" >'+'<option value="0">--Select--</option>'
+'<option value="1" name="MICROTEK">MICROTEK</option>'
+'<option value="2" name="HP">HP</option>'
+'<option value="3" name="RICHO">RICHO</option>'
+'<option value="4" name="BARCO">BARCO</option>'
+'<option value="5" name="ASUS">ASUS</option>'
+'<option value="6" name="IMAC PRO">IMAC PRO</option>'
+'<option value="7" name="CISCO">CISCO</option>'
+'<option value="8" name="NERGEAR">NERGEAR</option>'
+'<option value="9" name="MICROSOFT">MICROSOFT</option>'
+'</select>'
+'</td>'
+'<td ><select id="model_name'+x+'" name="model_name'+x+'" class="form-control">'+'<option value="0">--Select--</option>'
+'<option value="1" name="CELERON DUAL CORE">CELERON DUAL CORE</option>'
+'<option value="2" name="HP 1125 VA">HP 1125 VA</option>'
+'<option value="3" name="HP LASERJET PRO">HP LASERJET PRO</option>'
+'<option value="4" name="DELL">DELL</option>'
+'<option value="5" name="HP">HP</option>'
+'</select> </td>'
+'<td><select id="processor_type'+x+'" name="processor_type'+x+'" class="form-control">'
	+'<option value="0" selected="selected">--Select--</option>'
+'<option value="11" name="AMD RYZEN 9">AMD RYZEN 9</option>'
+'<option value="10" name="INTEL ATOM">INTEL ATOM</option>'
+'<option value="12" name="M1">M1</option>'
+'<option value="7" name="AMD RYZEN 7">AMD RYZEN 7</option>'
+'<option value="5" name="AMD RYZEN 3">AMD RYZEN 3</option>'
+'<option value="9" name="INTEL CELERON">INTEL CELERON</option>'
+'<option value="2" name="INTEL I5">INTEL I5</option>'
+'<option value="1" name="INTEL I3 AND SIMILAR">INTEL I3 AND SIMILAR</option>'
+'<option value="4" name="INTEL I9">INTEL I9</option>'
+'<option value="6" name="AMD RYZEN 5">AMD RYZEN 5</option>'
+'<option value="3" name="INTEL I7">INTEL I7</option>'
+'</select></td>'
  +'<td ><select id="ram_capi'+x+'" name="ram_capi'+x+'" class="form-control">'
+'<option value="0" selected="selected">--Select--</option>'
+'<option value="1" name="2GB">2GB</option>'
+'<option value="2" name="4GB">4GB</option>'
+'<option value="3" name="8GB">8GB</option>'
+'<option value="4" name="16GB">16GB</option>'
+'<option value="5" name="32GB">32GB</option>'
+'<option value="7" name="64GB">64GB</option>'
+'<option value="8" name="12435GB">12435GB</option>'
+'<option value="9" name="20GB">20GB</option>'
+'<option value="10" name="1212MB">1212MB</option>'
+'<option value="11" name="40GB">40GB</option>'
+'<option value="12" name="2400MB">2400MB</option>'
+'</select></td>'
 +'<td ><select id="hdd_capi'+x+'" name="hdd_capi'+x+'" class="form-control">'
+'<option value="0" selected="selected">--Select--</option>'
+'<option value="1" name="125GB">125GB</option>'
+'<option value="2" name="250GB">250GB</option>'
+'<option value="3" name="500GB">500GB</option>'
+'<option value="4" name="1TB">1TB</option>'
+'<option value="5" name="2TB">2TB</option>'
+'<option value="12" name="2 TB">2 TB</option>'
+'</select></td>'
+'<td  ><select id="operating_system'+x+'" name="operating_system'+x+'" class="form-control">'
+'<option value="0" selected="selected">--Select--</option>'
+'<option value="1" name="WINDOWS">WINDOWS</option>'
+'<option value="2" name="MACOS">MACOS</option>'
+'<option value="3" name="LINUX">LINUX</option>'
+'<option value="5" name="ANDROID">ANDROID</option>'
+'<option value="18" name="UBUNTU">UBUNTU</option>'
+'</select></td>'
+'<td  ><select id="office'+x+'" name="office'+x+'" class="form-control">'
+'<option value="0" selected="selected">--Select--</option>'
+'<option value="2" name="MS OFFICE 2010">MS OFFICE 2010</option>'
+'<option value="3" name="MS OFFICE 2013">MS OFFICE 2013</option>'
+'<option value="4" name="MS OFFICE 2016">MS OFFICE 2016</option>'
+'<option value="5" name="MS OFFICE 2019">MS OFFICE 2019</option>'
+'<option value="1" name="MS OFFICE 2007 EDITION">MS OFFICE 2007 EDITION</option>'
+'<option value="6" name="LIBRE OFFICE">LIBRE OFFICE</option>'
+'</select></td>'
 +'<td ><div class="col-md-8">'
+'<input id="antiviruscheckyes'+x+'" name="antiviruscheck'+x+'" type="radio" value="Yes"  checked="checked" onclick="anti_show(this,'+x+');">'
+'&nbsp; <label for="yes">Yes</label>&nbsp'+'<input id="antiviruscheckno'+x+'" name="antiviruscheck'+x+'"  type="radio" value="No"  onclick="anti_show(this,'+x+');">'
+'&nbsp; <label for="no">No</label>'
+'</div></td>'
+'<td ><select id="antivirus'+x+'" name="antivirus'+x+'" class="form-control">'
+'<option value="0" selected="selected">--Select--</option>'
+'<option value="6" name="AVAST">AVAST</option>'
+'<option value="1" name="QUICKHEAL">QUICKHEAL</option>'
+'</select></td>'
+'<td ><select id="os_patch'+x+'" name="os_patch'+x+'" class="form-control">'
+'<option value="0" selected="selected">--Select--</option>'
+'<option value="1" name="ONLINE  ON INTERNET">ONLINE  ON INTERNET</option>'
+'<option value="2" name="ONLINE ON ADN">ONLINE ON ADN</option>'
+'<option value="3" name="MANUAL IN OFFLINE MODE">MANUAL IN OFFLINE MODE</option>'
+'</select></td>'
+'<td ><select id="dply_envt'+x+'" name="dply_envt'+x+'" class="form-control">'
+'<option value="0" selected="selected">--Select--</option>'
+'<option value="6" name="STAND ALONE">STAND ALONE</option>'
+'<option value="5" name="INTERNET">INTERNET</option>'
+'<option value="7" name="LAN">LAN</option>'
+'<option value="8" name="CWN">CWN</option>'
+'<option value="9" name="ADN">ADN</option>'
+'<option value="10" name="INDEP NW VIZ CICG">INDEP NW VIZ CICG</option>'
+'</select></td>'
+'<td ><input id="ram_slot_qty'+x+'" name="ram_slot_qty'+x+'" min="1" max="1000" type="number" class="form-control" value="0" autocomplete="off"></td>'
+' <td ><div class="col-md-8">'
+'<input id="scan'+x+'" name="cd_drive'+x+'" type="radio" value="Yes">'
+'&nbsp; <label for="yes">Yes</label>&nbsp;'
+'<input id="scan'+x+'" name="cd_drive'+x+'" checked="checked" type="radio" value="No">'
+'&nbsp; <label for="no">No</label>'
+'</div></td>'
+'<td ><div class="col-md-8" >'
+'<input id="warrantycheckyes'+x+'" name="warrantycheck'+x+'"  type="radio" value="Yes" checked="checked" onclick="warrenty_show(this,'+x+');">'
+'&nbsp; <label for="yes">Yes</label>&nbsp;'
+'<input id="warrantycheckno'+x+'" name="warrantycheck'+x+'" type="radio" value="No" onclick="warrenty_show(this,'+x+');">'
+'&nbsp; <label for="no">No</label>'
+'</div></td>'
+'<td ><input type="date" name="warranty_dt'+x+'" id="warranty'+x+'" maxlength="10"  class="form-control"></td>'
+'<td ><input type="text" id="model_number'+x+'" name="model_number'+x+'" class="form-control " autocomplete="off" onkeypress="return isNumber(event);"></td>'
+'<td ><input type="text" id="machine_number'+x+'" name="machine_number'+x+'" class="form-control" autocomplete="off" onkeypress="return isNumber(event);"></td>'
+'<td ><input  type="text" id="mac_address'+x+'" name="mac_address'+x+'" class="form-control " maxlength="17" autocomplete="off" onkeyup="makeMacAddress(this);"></td>'
+'<td ><input  type="text" id="ip_address'+x+'" name="ip_address'+x+'" maxlength="15" class="form-control " autocomplete="off" onchange="ValidateIPaddress(this,'+x+');"></td>'
+'<td ><select id="s_state'+x+'" name="s_state'+x+'" class="form-control" onchange="serviceStChange(this,'+x+');">'
+'<option value="0">--Select--</option>'
+'<option name="Serviceable" value="1">Serviceable</option>'
+'<option name="Un-serviceable" value="2">Un-serviceable</option>'
+'</select></td>' 
+' <td ><select id="unserviceable_state'+x+'" name="unserviceable_state'+x+'" class="form-control">'
+'<option value="0" selected="selected">--Select--</option>'
+'<option name="BER" value="1">BER</option>'
+'<option name="Minor Repair" value="2">Minor Repair</option>'
+'<option name="DOWNGRADED" value="3">DOWNGRADED</option>'
+'</select></td>'
+'<td ><input type="date" name="unsv_from_dt'+x+'" id="unsv_from_dt'+x+'" maxlength="10"  class="form-control"></td> '
+'<td ><input type="text" class="form-control-sm" id="b_cost'+x+'" name="b_cost'+x+'" onkeypress="return isNumber(event);"></td>'
+'<td ><input type="date" name="proc_dt'+x+'" id="proc_date'+x+'" maxlength="10"></td>'
+'<td ><select id="b_head'+x+'" name="b_head'+x+'" class="form-control" >'
+'<option value="0" selected="selected">--Select--</option>'
+'<option value="4" name="WSE1">WSE1</option>'
+'</select></td>'
+'<td ><select id="b_code'+x+'" name="b_code'+x+'" class="form-control">'+'<option value="0" selected="selected">--Select--</option>'
+'<option value="4" name="EW">EW</option>'
+'</select></td>'
+'<td  align="center" ><i class="fa fa-plus" style="color:#17a2b8;font-size:25px;"  id = "id_add'+x+'" onclick="formopen('+x+');"></i>&nbsp;&nbsp;&nbsp;<i class="fa fa-minus"  style="color:red;font-size:25px;" id = "id_remove'+x+'" onclick="formopen_re('+x+');"></i></td>'+'</tr>');
    

    }



/*function validateInt(key) {
  
            var keycode = (key.which) ? key.which : key.keyCode;
           
            if (!(keycode == 8 || keycode == 46) && (keycode < 48 || keycode > 57)) {
                return false;
            }
            else {
                var parts = key.srcElement.value.split('.');
                if (parts.length > 1 && keycode == 46)
                    return false;
                return true;
            }
}*/

function validateFloatKeyPress(el, evt) {
    var charCode = (evt.which) ? evt.which : event.keyCode;
    var number = el.value.split('.');
    if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    //just one dot
    if(number.length>1 && charCode == 46){
         return false;
    }
    //get the carat position
    var caratPos = getSelectionStart(el);
    var dotPos = el.value.indexOf(".");
    if( caratPos > dotPos && dotPos>-1 && (number[1].length > 1)){
        return false;
    }
    return true;
}




//thanks: http://javascript.nwbox.com/cursor_position/
function getSelectionStart(o) {
  if (o.createTextRange) {
    var r = document.selection.createRange().duplicate()
    r.moveEnd('character', o.value.length)
    if (r.text == '') return o.value.length
    return o.value.lastIndexOf(r.text)
  } else return o.selectionStart
}





  
function CheckColors(val,rowid){
/*if(rowid == "0"){
rowid ="";
}*/

document.getElementById("medical_category_remarks"+rowid).style.width='200px';
//alert(document.getElementById("medical_category_remarks"+rowid));
 if(val=='--SELECT--'||val=='LMC'){
  document.getElementById("medical_category_remarks"+rowid).style.display='block';

 }else  {
   document.getElementById("medical_category_remarks"+rowid).style.display='none';
   }
}



 ///////////////////////////////////////////////////////////////////////////////////////export code start



 function AddData_second() {
  $("table#table_show_2  tbody").empty();




  rc=$("#count").val();


  var asset_type ;
  var make_name;
  var model_name;
  var processor_type;
  var ram_capi;
  var hdd_capi;
  var operating_system;
  var office;
  var antiviruscheck;
  var antivirus;
  var os_patch;
  var dply_envt;
  var ram_slot_qty;
  var scan;
  var warrantycheck;
  var warranty;
  var model_number;
  var machine_number;
  var mac_address;
  var ip_address;
  var s_state;
  var unserviceable_state;
  var unsv_from_dt;
   var b_cost;
    var proc_date;
	 var b_head;
	  var b_code;


  for (var colIndex1 = 1; colIndex1 <=parseInt(rc); colIndex1++) {
   

  var rows = "";
 
   asset_type =  document.getElementById("asset_type"+colIndex1).value;
   make_name=  document.getElementById("make_name"+colIndex1).value;
   model_name=  document.getElementById("model_name"+colIndex1).value;
   processor_type =  document.getElementById("processor_type"+colIndex1).value;
   ram_capi=  document.getElementById("ram_capi"+colIndex1).value;
   hdd_capi=  document.getElementById("hdd_capi"+colIndex1).value;
   operating_system=  document.getElementById("operating_system"+colIndex1).value;
   office=  document.getElementById("office"+colIndex1).value;
if(document.getElementById('antiviruscheckyes'+colIndex1).checked){
			antiviruscheck=$('#antiviruscheckyes'+colIndex1).val()
			}
			else if(document.getElementById('antiviruscheckno'+colIndex1).checked){
			antiviruscheck=$('#antiviruscheckno'+colIndex1).val()
			}   antivirus=  document.getElementById("antivirus"+colIndex1).value;
   os_patch=  document.getElementById("os_patch"+colIndex1).value;
   dply_envt=  document.getElementById("dply_envt"+colIndex1).value;
   ram_slot_qty=  document.getElementById("ram_slot_qty"+colIndex1).value;
   scan=  document.getElementById("scan"+colIndex1).value;
  if(document.getElementById('warrantycheckyes'+colIndex1).checked){
			warrantycheck=$('#warrantycheckyes'+colIndex1).val()
			}
			else if(document.getElementById('warrantycheckno'+colIndex1).checked){
			warrantycheck=$('#warrantycheckno'+colIndex1).val()
			}   warranty=  document.getElementById("warranty"+colIndex1).value;
   model_number=  document.getElementById("model_number"+colIndex1).value;
   machine_number=  document.getElementById("machine_number"+colIndex1).value;
   mac_address=  document.getElementById("mac_address"+colIndex1).value;
   ip_address=  document.getElementById("ip_address"+colIndex1).value;
   s_state=  document.getElementById("s_state"+colIndex1).value;
   unserviceable_state=  document.getElementById("unserviceable_state"+colIndex1).value;
   unsv_from_dt=  document.getElementById("unsv_from_dt"+colIndex1).value;
     b_cost=  document.getElementById("b_cost"+colIndex1).value;
	   proc_date=  document.getElementById("proc_date"+colIndex1).value;
	     b_head=  document.getElementById("b_head"+colIndex1).value;
		   b_code=  document.getElementById("b_code"+colIndex1).value;

 rows = "<tr><td>" + asset_type + "</td><td>" + make_name + "</td><td>" + model_name + "</td><td>" + processor_type + "</td><td>" + ram_capi + "</td><td>" 
 + hdd_capi + "</td><td>" + operating_system + "</td><td>" + office + "</td><td>" + antiviruscheck + "</td><td>" + antivirus + "</td><td>" + os_patch + 
 "</td><td>" + dply_envt + "</td><td>" + ram_slot_qty + "</td><td>" + scan + "</td><td>" + warrantycheck + "</td><td>" + warranty + 
"</td><td>" + model_number + "</td><td>" + machine_number + "</td><td>" + mac_address + "</td><td>" + ip_address + "</td><td>" + s_state + "</td><td>" + unserviceable_state + 
 "</td><td>" + unsv_from_dt + "</td><td>" + b_cost + "</td><td>" + proc_date + "</td><td>" + b_head + "</td><td>" + b_code + "</td></tr> ";
  $(rows).appendTo("#table_show_2 tbody");
 
  

    
}

btnExport();
window.location.reload();
}



var btnExport = (function() {
var currentdate = new Date(); 
var datetime = "Computing: " + currentdate.getDate() + "/"
                + (currentdate.getMonth()+1)  + "/" 
                + currentdate.getFullYear() + " Time "  
                + currentdate.getHours() + ":"  
                + currentdate.getMinutes() + ":" 
                + currentdate.getSeconds();
var blobURL = tablesToExcel(['table_show_2'],['UnitDtl'],datetime+'.xls','Excel');
 $(this).attr('href',blobURL);});



var tablesToExcel = (function() {
var uri = 'data:application/vnd.ms-excel;base64,'
, tmplWorkbookXML = '<?xml version="1.0"?><?mso-application progid="Excel.Sheet"?><Workbook xmlns="urn:schemas-microsoft-com:office:spreadsheet" xmlns:ss="urn:schemas-microsoft-com:office:spreadsheet">'
  + '<DocumentProperties xmlns="urn:schemas-microsoft-com:office:office"><Author>Axel Richter</Author><Created>{created}</Created></DocumentProperties>'
  + '<Styles>'
  + '<Style ss:ID="Currency"><NumberFormat ss:Format="Currency"></NumberFormat></Style>'
  + '<Style ss:ID="Date"><NumberFormat ss:Format="Medium Date"></NumberFormat></Style>'
  + '</Styles>' 
  + '{worksheets}</Workbook>'
, tmplWorksheetXML = '<Worksheet ss:Name="{nameWS}"><Table>{rows}</Table></Worksheet>'
, tmplCellXML = '<Cell{attributeStyleID}{attributeFormula}><Data ss:Type="{nameType}">{data}</Data></Cell>'
, base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
, format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }
return function(tables, wsnames, wbname, appname) {
  var ctx = "";
  var workbookXML = "";
  var worksheetsXML = "";
  var rowsXML = "";


  for (var i = 0; i < tables.length; i++) {

    if (!tables[i].nodeType) 
       tables[i] = document.getElementById(tables[i]);


    for (var j = 0; j < tables[i].rows.length; j++) {
      rowsXML += '<Row>'
      for (var k = 0; k < tables[i].rows[j].cells.length; k++) {
        var dataType = tables[i].rows[j].cells[k].getAttribute("data-type");
        var dataStyle = tables[i].rows[j].cells[k].getAttribute("data-style");
        var dataValue = tables[i].rows[j].cells[k].getAttribute("data-value");
        dataValue = (dataValue)?dataValue:tables[i].rows[j].cells[k].innerHTML;
        var dataFormula = tables[i].rows[j].cells[k].getAttribute("data-formula");
        dataFormula = (dataFormula)?dataFormula:(appname=='Calc' && dataType=='DateTime')?dataValue:null;
        ctx = {  attributeStyleID: (dataStyle=='Currency' || dataStyle=='Date')?' ss:StyleID="'+dataStyle+'"':''
               , nameType: (dataType=='Number' || dataType=='DateTime' || dataType=='Boolean' || dataType=='Error')?dataType:'String'
               , data: (dataFormula)?'':dataValue
               , attributeFormula: (dataFormula)?' ss:Formula="'+dataFormula+'"':''
              };
        rowsXML += format(tmplCellXML, ctx);
      }
      rowsXML += '</Row>'
    }
    ctx = {rows: rowsXML, nameWS: wsnames[i] || 'Sheet' + i};
    worksheetsXML += format(tmplWorksheetXML, ctx);
    rowsXML = "";
  }

  ctx = {created: (new Date()).getTime(), worksheets: worksheetsXML};
  workbookXML = format(tmplWorkbookXML, ctx);
  


  var link = document.createElement("A");
  link.href = uri + base64(workbookXML);
  console.log(typeof ctx);
  link.download = wbname || 'Workbook.xls';
  link.target = '_blank';
  document.body.appendChild(link);
  link.click();
  document.body.removeChild(link);
}

 })();

function serviceStChange(obj,x)
 {
	 var a =$("select#s_state"+x).val();
	 if(a == '1')
		 {
		    
			 $('select#unserviceable_state'+x).attr('readonly', true); $('#unsv_from_dt'+x).attr('readonly', true);
		   
		 }
	 else
		 {
		 $('select#unserviceable_state'+x).attr('readonly', false); $('#unsv_from_dt'+x).attr('readonly', false);
		 
		 }
 } function anti_show(obj,x)
 {
	 if ($("#antiviruscheckyes"+x).prop("checked"))
	 {
	     
		  $('select#antivirus'+x).attr('readonly', false);
		
	}
		else{
			 $('select#antivirus'+x).attr('readonly', true);
			
	 } }function warrenty_show(obj,x)
	{
	
		 if ($("#warrantycheckyes"+x).prop("checked")) {
		 
			
		  $('#warranty'+x).attr('readonly', false);
				}
			else{
			 $('#warranty'+x).attr('readonly', true);
			
	 }
		
	}function makeMacAddress(obj){
	
	 if(obj.value!=''){
	 var val=obj.value.split('-').join('');
	 var v = val.match(/.{1,2}/g).join("-");
	 obj.value=v;
	 }
}


function ValidateIPaddress(ipaddress,x) {  
	var ip = new RegExp(/^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/)
	  if (ip.test(ipaddress.value)) {  
	    return true;  
	   
	  }  
	  else{
	 	alert("You have entered an invalid IP Address!")  
		$("#ip_address"+x).focus();
	
		  return false;
	  }
	} 
	
	
	function isNumber(evt) {
		evt = (evt) ? evt : window.event;
		var charCode = (evt.which) ? evt.which : evt.keyCode;
		if (charCode > 31 && (charCode < 48 || charCode > 57)) {
		return false;
		}
		return true;
		}	
///////////////////////////////////////////////////////////////////////////////////////export code end
</script>

</html>

	

<div id="ui-datepicker-div" class="ui-datepicker ui-widget ui-widget-content ui-helper-clearfix ui-corner-all"></div></body></html>