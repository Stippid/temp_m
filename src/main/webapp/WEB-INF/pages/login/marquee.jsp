<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script src="js/amin_module/webmaster/update.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>
<script src="js/amin_module/webmaster/jquery-2.2.3.min.js"></script>
<style type="text/css">
.btn-group-sm > .btn, .btn-sm {
	font-size: 12px;
	line-height: 1.5;
}
.glyphicon-refresh::before {
	content: "\e031";
}
.glyphicon {
    font-family: 'Glyphicons Halflings';
	font-style: normal;
	font-weight: 400;
	line-height: 1;
}
.cue_text .col-md-12{
  justify-content: center;
}
</style>
<style>
.multiselect {
  width: 200px;
}
.selectBox {
  position: relative;
}
.selectBox select {
  width: 100%;
}
.overSelect {
  position: absolute;
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;
}
#checkboxes {
  display: none;
  border: 1px #dadada solid;
}
#checkboxes label {
  display: block;
  text-align:left;
  padding-left: 30px;
}
#checkboxes label:hover {
  background-color: #1e90ff;
}
#checkboxes label input[type="checkbox"]{
  margin-right: 10px;
}
</style>
<style>
textarea {
   text-transform: capitalize;
}
input[type="text"]{
  text-transform: capitalize;
}
</style>
	<script type="text/javascript">
		window.history.forward();
		function noBack() {
			window.history.forward();
		}
		$(document).ready(function (){
	   		$("textarea#test_msg").val('${msg12}');
	   		$("select#valid_upto").val('${valid_upto1}');
		    try{
	    		   if(window.location.href.includes("msg1="))
	    			{
	    				var url = window.location.href.split("?msg1")[0];
	    			} 	
	    		}
	    		catch (e) {
	    		} 
			});
</script>
<form:form name="marcuries" id="marcuries" action="marcuriesAction" method='POST' commandName="marcuriescmd">
<div >
<div class="container" align="center">
	<div class="card">
		<div class="card-header"> <h5>MANAGE MARQUEE</h5></div>
		<div class="card-body card-block cue_text">
			<div class="col-md-12">				  
				<div class="col-md-6">
	             	<div class="row form-group"> 
	             		<div class="col col-md-6">
		                	<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Insert Marquee </label>
		                </div>
		                <div class="col-12 col-md-6">		              
		                  <textarea rows="2" cols="250" id="test_msg" id="test_msg" name="test_msg" style="font-family: 'FontAwesome',Arial; width:300px;" maxlength="200" class="form-control char-counter" autocomplete="off"></textarea>
						<input type="hidden"  id="msg_old_name" name="msg_old_name"  class="form-control " autocomplete="off" > 
						</div>
	             	</div>
	            </div> 	                         	 	
             	</div>  
			 	<div class="col-md-12">	
             	<div class="col-md-6">
	             	<div class="row form-group"> 
	             		<div class="col col-md-6">
		                	<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> To Date </label>
		                </div>		                
	             		<div class="col-12 col-md-6">	             		
               				<input type="date" id="valid_upto" name="valid_upto" class="form-control">               			
               				<input type="hidden" id="valid_upto_old" name="valid_upto_old" class="form-control" > 
               				<input type="hidden" id="id_hid" name="id_hid" class="form-control" > 
						</div>
	             	</div>
             	</div>              	           	            
			</div>
		</div>
				<div class="card-footer" align="center">
      	            <input type="reset" class="btn btn-success btn-sm" value="Clear">    	
            		<input type="submit"  id="save_btn" class="btn btn-primary btn-sm" value="Save" onclick="return isValid();">
            		<input type="submit" id="update_btn" class="btn btn-primary btn-sm" value="Update" style="display: none">
      			</div>
        		<div class="card-body"  id="divPrint">
			 	<div id="divShow" style="display: block;"></div>
				<div  class="watermarked" data-watermark="" id="divwatermark">
					<span id="ip"></span>
					<table id="getMsgSearchReport" class="table no-margin table-striped  table-hover  table-bordered report_print" >
						<thead style="background-color: #9c27b0; color: white;">
							<tr style="font-size: 15px;">
								<th width="10%">Ser No</th>
								<th width="65%">Insert Area </th>
								<th width="15%">To Date </th>
								<th width="10%" style="text-align: center;">Action</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach var="item" items="${list}" varStatus="num" >
							<tr style="font-size: 12px;">
								<td width="10%">${num.index+1}</td>
								<td width="65%">${item.msg}</td>
								<td width="15%">${item.valid_upto}</td>
								<td width="10%" id="thAction1" align="center" >${item.id}</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>	
			</div> 
	</div>
</div>
</div>  
</form:form>
<script>
function isValid()
{
	if($("textarea#test_msg").val() == ""){
		alert("Please Enter Marquee Text");
		$("textarea#test_msg").focus();
		return false;
   	}
	if($("#valid_upto").val()==""){
		alert("Please select valid upto date");
		$("#valid_upto").focus();
		return false;
	} 
	return true;
}
var dateControler = {
        currentDate : null
    }
         $(document).on( "change", "#valid_upto",function( event, ui ) {
             var now = new Date();
             var selectedDate = new Date($(this).val());
             if(selectedDate < now) {
                 $(this).val(dateControler.currentDate)
             } else {
                 dateControler.currentDate = $(this).val();
             }
         }); 
</script>
<script>
	function editData(id,name,date){	
		var dd = date.substring(0,2);
		var d = dd.includes("-");
		if(d == true){
			date = '0' + date;
		}
		var newdate = date.split("-").reverse().join("-");
	
		document.getElementById('id_hid').value=id;
		document.getElementById('test_msg').value=name;
		document.getElementById('msg_old_name').value=name;
		document.getElementById('valid_upto_old').value=newdate;
		document.getElementById('valid_upto').value=newdate;
		document.getElementById('update_btn').style.display='inline-block'; 
		document.getElementById('save_btn').style.display='none';  
	}
</script>
<script>
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
            limit: 200
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
$(".char-counter").charCounter();
</script>