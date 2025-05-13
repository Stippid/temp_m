function isAlphaNumeric(evt){
		var charCode = (evt.which) ? evt.which : evt.keyCode;
			if((charCode>=48 && charCode<=57) || (charCode>=65 && charCode<=90) || (charCode>=97 && charCode<=122) || charCode==32 || charCode==46 || charCode == 45  || charCode == 47){
				return true;
			} 
		return false;
}

function isNumeric(evt){
	var charCode = (evt.which) ? evt.which : evt.keyCode;
		if((charCode>=48 && charCode<=57)){
			return true;
		} 
	return false;
}

function nMilCount(a){
	var mths=['','ALPHA','BRAVO','CHARLIE','DELTA','ECHO','FOXTROT','GOLF','HOTEL','JULIET','KILO','LIMA','MIKE','NOVEMBER','OSCAR','PAPA','QUIBIC','ROMEO','SIERRA','TANGO','UNIFORM','VICTOR','WHISKY','X-RAY','YANKEE','ZULU'];
	return mths[a]; 
}
function nFormatDate(data,fmtf,fmt2){
	if (fmtf=="") {
		fmtf=='yyyy-mm-dd';
	}
	var newdata="";
	var mths=['','Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];
	if (!(data=='null' || data==null || data=='undefined' || data=='')) {
		var darr1=data.split(" ");
		var darr2=darr1[0].split("-");
		if (darr1[1]=='undefined') {
			var darr3=darr1[1].split(":");
		}
		//console.log("1",darr1,darr2);
		if (fmt2=='dd-mm-yyyy') {
			if (fmtf=='dd-mm-yyyy') {
				newdata=darr2[0]+"-"+darr2[1]+"-"+darr2[2];
			} else {
				newdata=darr2[2]+"-"+darr2[1]+"-"+darr2[0];
			}
		}
		if (fmt2=='dd-mmm-yyyy') {
			if (fmtf=='dd-mm-yyyy') {
				newdata=darr2[0]+" "+mths[parseInt(darr2[1])]+" "+darr2[2];
			} else {
				newdata=darr2[2]+" "+mths[parseInt(darr2[1])]+" "+darr2[0];
			}
		}
		if (fmtf=='dd mmm yyyy') {
			var darr2=data.split(" ");
			if (mths.indexOf(darr2[1])<10) {
				nmths= '0'+mths.indexOf(darr2[1]);
			} else {
				nmths = mths.indexOf(darr2[1])
			}
			if (fmt2=='dd-mm-yyyy') {
				newdata=darr2[0]+"-"+nmths+"-"+darr2[2];
			} else {
				newdata=darr2[2]+"-"+nmths+"-"+darr2[0];
			}
			if (fmt2=='mm-yyyy') {
				newdata=nmths+"-"+darr2[2];
			}
			if (fmt2=='yyyy-mm') {
				newdata=darr2[2]+"-"+nmths;
			}
		}
		if (fmtf=='mmm yyyy') {
			var darr2=data.split(" ");
			if (mths.indexOf(darr2[1])<10) {
				nmths= '0'+mths.indexOf(darr2[1]);
			} else {
				nmths = mths.indexOf(darr2[1])
			}
			if (fmt2=='mm-yyyy') {
				newdata=nmths+"-"+darr2[2];
			}
			if (fmt2=='yyyy-mm') {
				newdata=darr2[2]+"-"+nmths;
			}
		}
		//console.log("2",darr1,newdata);
	}
	return newdata;
}

function nFormatNull(msgb,data,msga){
	var newdata="";
	if (data == null) {
		newdata="";
	} else {
		newdata=msgb+data+msga;
	}
	return newdata;
}


function nCal_Period(fdt,tdt){
	console.log("1-",fdt,tdt);
	var newpd="";
    var from_d=fdt;
    var from_d1=from_d.substring(0,4);
    var from_d2=from_d.substring(7,5);
    var from_d3=from_d.substring(10,8);
    var frm_d = from_d3+"-"+from_d2+"-"+from_d1;
    frm_d =fdt;
    /* var to_d=$("#dt_of_joining").val();
    var to_d1=to_d.substring(0,4);
    var to_d2=to_d.substring(7,5);
    var to_d3=to_d.substring(10,8); */
    var today=new Date(tdt);
    var to_d3 = today.getDate();
    var to_d2 = today.getMonth() + 1;
    var to_d1 = today.getFullYear();        
    var to_d0 = to_d3+"-"+to_d2+"-"+to_d1;
    to_d0=tdt;
    if(to_d2 > from_d2 && to_d3 > from_d3 || to_d3 == from_d3){
    var year = to_d1 - from_d1        
    var month = to_d2 - from_d2
    }
    if(to_d2 > from_d2 && to_d3 < from_d3){
            var year = to_d1 - from_d1        
            var month = to_d2 - from_d2 - 1
            }
    if(from_d2 > to_d2){
            var year = to_d1 - from_d1 - 1
            var month1 = from_d2 - to_d2
            var month = 12 - month1
    }
    if(from_d2 == to_d2 && from_d3 > to_d3){
            var year = to_d1 - from_d1 - 1
            var days = from_d3 - to_d3
    }
    if(from_d2 == to_d2 && to_d3 > from_d3){
            var year = to_d1 - from_d1 
            var days =  to_d3 - from_d3 
            
    }
    if(from_d2 == to_d2 && to_d3 == from_d3){
            var year = to_d1 - from_d1 
            var days = 0
    }
    //days
    if(from_d2 > to_d2 && from_d3 > to_d3){
            var m = from_d2 - to_d2 
            var n = m * 30
            var m1 = from_d3 - to_d3 
            var m2 = n+m1
            var days =  m2
    }
    if(from_d2 > to_d2 && to_d3 > from_d3){
            var m = from_d2 - to_d2 
            var n = m * 30
            var m1 =  to_d3 - from_d3 
            var m2 = n+m1
            var days =  m2
    }
    if(to_d2 > from_d2   && to_d3 > from_d3){
            var m =  to_d2 - from_d2 
            var n = m * 30
            var m1 =  to_d3 - from_d3 
            var m2 = n+m1        
            var days =  m2 
    
    }
    if(to_d2 >  from_d2 && from_d3 > to_d3){
            var m = to_d2 - from_d2   
            var n = m * 30
            var m1 = from_d3 - to_d3 
            var m2 = n+m1
            var days =  m2
            
    }
    console.log("1-Year-",year);
    newpd =year;
    if (month == undefined) {
       month = 0;
    }
    console.log("1-Month-",month);
    newpd =year +":"+month;
    return newpd;
}
$(function() {
	$.ajaxSetup({
		beforeSend:function(){
			$("#nrWaitLoader").show();
		},
		complete:function(){
			$("#nrWaitLoader").hide();
		}
	});
	
	var num_pattern = /^[0-9]*\\.?[0-9]*$/;
	var injectionKeys = [ "'", "%", "\\","||", "--", "/*", "script", "src", "eval(", ">", "<", "expression","onload", "&gt", "&lt", ";", "~" ,"^","#","iframe","embed","meta","applet","plugin","href"];
	var checkInjection = function(data){
		var hasError = false;
		for(var i=0;i<injectionKeys.length;i++){			
			if(data.toUpperCase().indexOf(injectionKeys[i].toUpperCase()) >= 0 ){
				//console.log(data.toUpperCase(),injectionKeys[i].toUpperCase());
				hasError = true; //injection found
				break;
			}
		}
		return hasError;
	}
	
	/*$(".decimal").on("keypress",function(e){
		var val = $(this).val()+""+String.fromCharCode(e.charCode);
		if(isNaN(val))
			return false;
		else
			return true;
	});
*/
	
	$.fn.nselect=function(opt){
		if (this.find("option").length >5 ) {
			$(this).select2();
		} 
	}
	
	
	
	
	
	$(".decimal").on("keypress",function(e){
		var char = String.fromCharCode(e.charCode);		
		var val = $(this).val()+""+char;
		if($(this).hasClass('negative')){
			if(e.target.selectionStart === 0 && char === '-')
				val = char+""+$(this).val();
			if($(this).val().indexOf('-') === 0 && e.target.selectionStart === 0)
				return false;
		}
		return !isNaN(val);
	});
	
	$(".decimal").on("blur",function(e){
		var val = $(this).val();
		if(!isNaN(val)){
			//var deno = $("#amt_rs").val() == "CR" ? 5 : ($(this).data('decimal-pt')||2);
			var pt = $(this).data('decimal-pt');
			var deno = pt ? pt :  ($("#amt_rs").val() == "CR" ? 5 : 2);
			val = Number(val).toFixed(deno);
			$(this).val(val);
		}
		else
			$(this).val(0);
	});

	$(".decimal").on("paste",function(e){
		e.preventDefault();
	});
	
	
	$("form.fp-form").on("submit",function(e){
		var flag = true,dataEl = e.currentTarget,el;
		$("#WaitLoader, #nrWaitLoader").show();
		for(var i=0;i<dataEl.length;i++){
			el = dataEl[i];
			flag = $(el).hasClass('ignore') ? false : ($(el).hasClass('decimal') ? isNaN($(el).val()) : checkInjection($(el).val()));
			if(flag){
				e.preventDefault();
				flag = false;
				alert("Error - Invalid Input Found Process Failed");
				$("#WaitLoader, #nrWaitLoader").hide();
				break;
			}
		}
		return !flag;
	});
	
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
            limit: $(this).attr("maxlength") || 250
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
	$.fn.Autocomplete = function(a, b, c, d, e, x, z) {
		$.ajax({
			type : 'POST',
			url : b,
			data : d,
			success : function(response) {
				response.sort();
				var a = [];
				if (e != "") {
					for (var i = 0; i < response.length; i++) {
						a[response[i][e]] = response[i][e];
					}
				} else {
					for (var i = 0; i < response.length; i++) {
						a[response[i]] = response[i];
					}
				}
				$(c).autocomplete({
					data : a,
					limit : '10',
				});
			}
		});
	};


	$.fn.Autocomplete2 = function(a, b, c, d, x, z, e) {
		$(c).autocomplete({
			source : function(request, response) {
				$.ajax({
					type : 'POST',
					url : b + '?' + key + "=" + value,
					data : d,
					success : function(data) {
						var a = [];
						var length = data.length - 1;
						if (data.length != 0) {
							var enc = data[length].substring(0, 16);
						}
						for (var i = 0; i < data.length; i++) {
							a[i] = dec(enc, data[i]);
						}
						response(a);
					}
				});
			},
			minLength : 1,
			autoFill : true,
			change : function(event, ui) {
				if (ui.item) {
					return true;
				} else {
					if (e == "" || e == null) {
						alert("Please Input Valid Data...");
						$(c).val('');
						$(z).val('');
						$(c).focus();
						return false;
					}
				}
			},
			select : function(event, ui) {
				var w = ui.item.value;
				
				if(w != "")
				{	
					if (x != "" && x != null) {
						
						if(x == "getMNHDistinctICDList_ID")
						{	
							var a = w.split("-");
							w = a[0];
						}
						
						$.post(x + '?' + key + "=" + value, {
							y : w
						}, function(j) {
							var enc = j[j.length - 1].substring(0, 16);
							var n1 = dec(enc, j[0]);
						
							if (j == "") {
								alert("Please Input Valid Data...");
								$(c).val('');
								$(z).val('');
								$(c).focus();
								return false;
							} else {
								$(z).val(n1);
							}
						});
					}
				}
			}
		});
	};
	$.fn.onchange = function(x, y, z) {
		$.post(x + '?' + key + "=" + value, {
			y : y
		}, function(j) {
			$(z).val(j);
		});
	};
	$.fn.onchange2 = function(x, y, z) {
		$.post(x + '?' + key + "=" + value, {
			y : y
		}, function(j) {
			for (var i = 0; i < z.length; i++) {
				$('#' + z[i] + '').val(j[0][i]);
			}
		});
	};
	$.fn.onSelect = function(x, y, z, w) {
		$.post(x + '?' + key + "=" + value, {
			y : y
		}, function(j) {
			var options = '<option value="' + "-1" + '">' + "--Select--"
					+ '</option>';
			for (var i = 0; i < j.length; i++) {
				options += '<option value="' + j[i][w[0]] + '" name="'
						+ j[i][w[1]] + '" >' + j[i][w[1]] + '</option>';
			}
			$(z).html(options);
		});
	};
	$.fn.JsonGet = function(x, y) {
		$.post(x + '?' + key + "=" + value, {
			y : y
		}, function(j) {
			a = j;
		});
	};
	$.fn.getCurDt = function(a) {
		var d = new Date();
		var Fulldate = d.getFullYear() + "-"
				+ ("0" + (d.getMonth() + 1)).slice(-2) + "-"
				+ ("0" + d.getDate()).slice(-2);
		$(a).val(Fulldate);
	};
	$.fn.getExDt = function(a) {
		var d = new Date();
		var Fulldate = (d.getFullYear() + 1) + "-"
				+ ("0" + (d.getMonth() + 1)).slice(-2) + "-"
				+ ("0" + d.getDate()).slice(-2);
		$(a).val(Fulldate);
	};
	$.fn.getCurDt2 = function(a) {
		var d = new Date();
		var Fulldate = ("0" + d.getDate()).slice(-2) + "-"
				+ ("0" + (d.getMonth() + 1)).slice(-2) + "-" + d.getFullYear();
		$(a).val(Fulldate);
	};
	$.fn.getFirDt = function(a) {
		var d = new Date();
		var Uploaddate = d.getFullYear() + "-"
				+ ("0" + (d.getMonth() + 1)).slice(-2) + "-" + ("01").slice(-2);
		$(a).val(Uploaddate);
	};
	$.fn.getMthYr = function(a) {
		var d = new Date();
		var Fulldate = d.getFullYear() + "-"
				+ ("0" + (d.getMonth() + 1)).slice(-2);
		$(a).val(Fulldate);
	};
	$.fn.getPrintData = function(a) {
		var nra = $(a).html();
		var nrl = '<link rel="stylesheet" href="js/miso/autoComplate/nrcss.css">';
		var nrw = window.open('', 200, 300);
		nrw.document.write(nrl + nra);
		nrw.window.print();
		nrw.document.close();
	};
	$.fn.getPrintDiv1 = function(a) {
		var nra = $(a).html();
		var nrw = window.open('', 200, 300);
		nrw.document.write(nra);
		nrw.window.print();
		nrw.document.close();
	};
	$.fn.getPrintDiv = function(a, b) {
		var pagesize = "L";
		var nrUrl = window.location.protocol + "//" + window.location.hostname + ":" + window.location.port;
		var nra = $(a).html();
		var nrPage = "<html><head>";
		nrPage = nrPage + "<link rel='stylesheet' href='../admin/js/common/nrcssp.css'>";
		nrPage = nrPage + "<style>table td{font-size:12px;} table th{font-size:13px !important;color:black;} </style> </head><body>";
		nrPage = nrPage + "<input type='button' onclick='window.print();' value='Print'>";
		nrPage = nrPage + "<div style='width: 276mm; height: 190mm;'>";
		nrPage = nrPage + "<div width='100%' class='nrTableHeading' style='text-align:center;font-size:18px;color:black;'><b><u>" + b + "</u></b></div>";
		nrPage = nrPage + nra + "</div></body></html>";
		if (pagesize == "L") {
			var nrw = window.open('', 'abc','left=100,top=50,width=1090,height=500');
		} else {
			var nrw = window.open('', 'abc','left=200,top=50,width=750,height=500');
		}
		nrw.document.write(nrPage);
		nrw.document.close();
	};
	$.fn.nrURLPopup = function(url, winName, w, h, scroll) {
		var popupWindow = null;
		LeftPosition = (screen.width) ? (screen.width - w) / 2 : 0;
		TopPosition = (screen.height) ? (screen.height - h) / 2 : 0;
		settings = 'toolbar=no,height=' + h + ',width=' + w + ',top=' + TopPosition + ',left=' + LeftPosition + ',scrollbars=' + scroll + ',noresizable';
		popupWindow = window.open(url, winName, settings)
	};
	$.fn.tbtoExcel = function(b) {
		var a1 = $(b).html();
		var a2 = '<link rel="stylesheet" href="js/miso/autoComplate/nrcss.css">';
		let file = new Blob([ a1 ], {
			type : "application/vnd.ms-excel"
		});
		let url = URL.createObjectURL(file);
		let a = $("<a />", { href : url, download : "exlexpdown.xls" }).appendTo("body").get(0).click();
	};
	$.fn.tbtoWord = function(b) {
		var a1 = $(b).html();
		var a2 = '<link rel="stylesheet" href="js/common/nrcss.css">';
		let file = new Blob([ a1 ], {
			type : "application/vnd.ms-word"
		});
		let url = URL.createObjectURL(file);
		let a = $("<a />", { href : url, download : "wordexpdown.doc" }).appendTo("body").get(0).click();
	};
	
	$.fn.nAlphaNumeric = function (b){
		var a;
		if (b.which) {
			charCode= b.which;
		} else {
			charCode = b.keyCode;
		}
		if((charCode>=48 && charCode<=57) || (charCode>=65 && charCode<=90) || (charCode>=97 && charCode<=122)){
			return true;
		} 
		return false;
	};
	
	$.fn.nAlert = function(b,a) {
		b=b.replaceAll("\n","<BR>");
		if (a=="" || typeof(a)=="undefined"){
			a="Alert";
		}
		var clb="$('#nralert').hide();";
		var a2 = '<div class="nAlertHeader">'+a;
		//a2 +='<span class="nPopClose" onclick="'+clb+'">X</span>
		a2 +='</div>';										
		a2 += '<span class="msg">'+b+'</span>';
		a2 += '<br><div class="nrAlertOk" onclick="'+clb+'"><input type="button" value="Ok"></div>';
		$("#nralertmsg").html(a2);
		$("#nralert").show();
	};
});

//-------------------mnh---------------------

/*age calculation form dob */

function calculate_age(obj){                
    var from_d=$("#"+obj.id).val();
    var from_d1=from_d.substring(0,4);
    var from_d2=from_d.substring(7,5);
    var from_d3=from_d.substring(10,8);
    var frm_d = from_d3+"-"+from_d2+"-"+from_d1;         
    /* var to_d=$("#dt_of_joining").val();
    var to_d1=to_d.substring(0,4);
    var to_d2=to_d.substring(7,5);
    var to_d3=to_d.substring(10,8); */
    var today=new Date();
    var to_d3 = today.getDate();
    var to_d2 = today.getMonth() + 1;
    var to_d1 = today.getFullYear();        
    var to_d0 = to_d3+"-"+to_d2+"-"+to_d1;
    if(to_d2 > from_d2 && to_d3 > from_d3 || to_d3 == from_d3){
    var year = to_d1 - from_d1        
    var month = to_d2 - from_d2
    }
    if(to_d2 > from_d2 && to_d3 < from_d3){
            var year = to_d1 - from_d1        
            var month = to_d2 - from_d2 - 1
            }
    if(from_d2 > to_d2){
            var year = to_d1 - from_d1 - 1
            var month1 = from_d2 - to_d2
            var month = 12 - month1
    }
    if(from_d2 == to_d2 && from_d3 > to_d3){
            var year = to_d1 - from_d1 - 1
            var days = from_d3 - to_d3
    }
    if(from_d2 == to_d2 && to_d3 > from_d3){
            var year = to_d1 - from_d1 
            var days =  to_d3 - from_d3 
            
    }
    if(from_d2 == to_d2 && to_d3 == from_d3){
            var year = to_d1 - from_d1 
            var days = 0
    }
    //days
    if(from_d2 > to_d2 && from_d3 > to_d3){
            var m = from_d2 - to_d2 
            var n = m * 30
            var m1 = from_d3 - to_d3 
            var m2 = n+m1
            var days =  m2
    }
    if(from_d2 > to_d2 && to_d3 > from_d3){
            var m = from_d2 - to_d2 
            var n = m * 30
            var m1 =  to_d3 - from_d3 
            var m2 = n+m1
            var days =  m2
    }
    if(to_d2 > from_d2   && to_d3 > from_d3){
            var m =  to_d2 - from_d2 
            var n = m * 30
            var m1 =  to_d3 - from_d3 
            var m2 = n+m1        
            var days =  m2 
    
    }
    if(to_d2 >  from_d2 && from_d3 > to_d3){
            var m = to_d2 - from_d2   
            var n = m * 30
            var m1 = from_d3 - to_d3 
            var m2 = n+m1
            var days =  m2
            
    }
    $("#age_year").val(year);
//    document.getElementById("age_of_joining").value=year+"-"+month+"-"+days; 
    //document.getElementById('age_year1').value = year;
    
    if (month == undefined)
            {
            month = 0;
            }
    document.getElementById('age_month').value = month;
    
}



function calculate_age_edit2(val){        
    
    if(val != "")
    {
    var from_d=val;
    
    var from_d1=from_d.substring(0,4);
    var from_d2=from_d.substring(7,5);
    var from_d3=from_d.substring(10,8);
    var frm_d = from_d3+"-"+from_d2+"-"+from_d1;         
    /* var to_d=$("#dt_of_joining").val();
    var to_d1=to_d.substring(0,4);
    var to_d2=to_d.substring(7,5);
    var to_d3=to_d.substring(10,8); */
    var today=new Date();
    var to_d3 = today.getDate();
    var to_d2 = today.getMonth() + 1;
    var to_d1 = today.getFullYear();        
    var to_d0 = to_d3+"-"+to_d2+"-"+to_d1;
    if(to_d2 > from_d2 && to_d3 > from_d3 || to_d3 == from_d3){
    var year = to_d1 - from_d1        
    var month = to_d2 - from_d2
    }
    if(to_d2 > from_d2 && to_d3 < from_d3){
            var year = to_d1 - from_d1        
            var month = to_d2 - from_d2 - 1
            }
    if(from_d2 > to_d2){
            var year = to_d1 - from_d1 - 1
            var month1 = from_d2 - to_d2
            var month = 12 - month1
    }
    if(from_d2 == to_d2 && from_d3 > to_d3){
            var year = to_d1 - from_d1 - 1
            var days = from_d3 - to_d3
    }
    if(from_d2 == to_d2 && to_d3 > from_d3){
            var year = to_d1 - from_d1 
            var days =  to_d3 - from_d3 
            
    }
    if(from_d2 == to_d2 && to_d3 == from_d3){
            var year = to_d1 - from_d1 
            var days = 0
    }
    //days
    if(from_d2 > to_d2 && from_d3 > to_d3){
            var m = from_d2 - to_d2 
            var n = m * 30
            var m1 = from_d3 - to_d3 
            var m2 = n+m1
            var days =  m2
    }
    if(from_d2 > to_d2 && to_d3 > from_d3){
            var m = from_d2 - to_d2 
            var n = m * 30
            var m1 =  to_d3 - from_d3 
            var m2 = n+m1
            var days =  m2
    }
    if(to_d2 > from_d2   && to_d3 > from_d3){
            var m =  to_d2 - from_d2 
            var n = m * 30
            var m1 =  to_d3 - from_d3 
            var m2 = n+m1        
            var days =  m2 
    
    }
    if(to_d2 >  from_d2 && from_d3 > to_d3){
            var m = to_d2 - from_d2   
            var n = m * 30
            var m1 = from_d3 - to_d3 
            var m2 = n+m1
            var days =  m2
            
    }
    $("#age_year").val(year);

    document.getElementById('age_month').value = month;
    
    }
    else
    {
            $("#age_year").val("0");

            document.getElementById('age_month').value = "0";
    }
    
}

function quarter_validate(u_q){
	
	var d = new Date();
	var year = d.getFullYear();
	var Month = d.getMonth();
	//var Month = curDate.substring(4,5);
	
	 var quarter="";
		 
		
		 if(Month > 0 && Month <=3)
		 {
			
			 quarter="q1";
		 }
		 else if (Month > 3 && Month <=6)
		 {
			 
			 quarter="q2";
		 }
		 else if (Month > 6 && Month <=9)
		 {
			 
			 quarter="q3";
		 }
		 else
		 {
			 
			 quarter="q4";
		 }
		
		 var user_q = u_q.substr(1, 1); 
	     var current_q = quarter.substr(1, 1); 
	   
		if(parseInt(user_q) >  parseInt(current_q))
		{ 
			
			 return 0;
		}
		return 1;
		
} 
 


function DOB_from_Year_Month()
{
	
	
		
		var cntOfYears =  $("#age_year").val();
		var cntOfMonths =$("#age_month").val();
		if(cntOfMonths == "")
		{
			cntOfMonths =0;
		}
		var cntOfDays = 0;
		var birthDate = moment().subtract(cntOfYears, 'years').subtract(cntOfMonths, 'months').subtract(cntOfDays, 'days');
		document.getElementById("date_of_birth1").value = birthDate.format("YYYY-MM-DD");
}
		
//FP Function Start 
if(typeof String.prototype.replaceAll == "undefined"){
	String.prototype.replaceAll = function(str1,str2){
	    //var reg = new RegExp(str1,'g');
	    //var val = this.valueOf().replace(reg,str2)
		var val = this.valueOf().split(str1).join(str2);
	    return val;
	}
}

		
Number.prototype.toINR = function(curSymbol,rsType,rsFmt,rsType2){
	
	if (!rsType2) {
		rsType2=rsType;
	}	
	if (rsType==rsType2) {
		if (rsType2=='CR') {
			var nRetval=parseFloat(this.valueOf()/1).toFixed(5);
		} else {
			var nRetval=parseFloat(this.valueOf()/1).toFixed(2);	
		}	
	} else {
		if (rsType !='CR' && rsType2=='CR') {
			if (rsType2=='CR') {
				var nRetval=parseFloat(this.valueOf()/10000000).toFixed(5);
			} else {
				var nRetval=parseFloat(this.valueOf()/10000000).toFixed(2);
			}
		} else {
			if (rsType =='CR' && rsType2 !='CR') {
				if (rsType2=='CR') {
					var nRetval=parseFloat(this.valueOf()*10000000).toFixed(5);
				} else {
					var nRetval=parseFloat(this.valueOf()*10000000).toFixed(2);
				}
			}
		}		
	}	
	

	curSymbol = curSymbol || '';
	if (rsFmt=='INR') {
		var nRetValInr=Number(nRetval).toINRC(curSymbol,rsType2);
	} else {
		var nRetValInr=nRetval;
	}
	//console.log(rsType,rsFmt,rsType2,nRetval,nRetValInr);
	var nRetValf=curSymbol+nRetValInr;
	return nRetValf;
}

Number.prototype.toINRR = function(curSymbol,rsType,rsFmt,rsType2){
	if (!rsType2) {
		rsType2=rsType;
}
	curSymbol = curSymbol || '';
	if (rsType2=='CR') {
		var nRetval=parseFloat(this.valueOf()/10000000).toFixed(5);
	} else {
		var nRetval=parseFloat(this.valueOf()/1).toFixed(2);	
	}	
	if (rsFmt=='INR') {
		var nRetValInr=Number(nRetval).toINRC(curSymbol,rsType2);
	} else {
		var nRetValInr=nRetval;
	}
	var nRetValf=curSymbol+nRetValInr;
	return nRetValf;
}


Number.prototype.toINRC = function(curSymbol,rsType){
	curSymbol = curSymbol || '';	
	//var nRetval=this.valueOf().toFixed(5).toLocaleString('en-In',{currency:'INR'});;
	if (rsType=='CR') {
		var nRetval=this.valueOf().toFixed(5).replace(/(\d)(?=(\d{2})+\d\.)/g, '$1,');
	} else {
		var nRetval=this.valueOf().toFixed(2).replace(/(\d)(?=(\d{2})+\d\.)/g, '$1,');
	}
	var nRetValf=curSymbol+nRetval;
	return nRetValf;
}
//FP Function End

//psg
function fire_validate(u_q){
	
	var d = new Date();
	var year = d.getFullYear();	
	var Month = d.getMonth() + 1;
	
	
	  var firing_event_qe="";
	  
		// 
		if(Month >= 1 || Month <= 3)
		{
			firing_event_qe ="3"
		}
		else if(Month >= 4 || Month <= 6)
		{
			firing_event_qe ="6"
		}
		else if(Month >= 7 || Month <= 9)
		{
			firing_event_qe ="9"
		}
		else if(Month >= 10 || Month <= 12)
		{
			firing_event_qe ="12"
		}
		if( firing_event_qe <  u_q ){
			return 0;
		}
		
		return 1;
} 

var other="OTHERS";
function fn_otherShowHide(obj,Divother_id,other_id){
	var thisText=$("#"+obj.id+" option:selected").text();
	if(thisText==other){
		$('#'+Divother_id).show();
	}
	else{
		$('#'+Divother_id).hide();
		$('#'+other_id).val('');
	}
}



/////// MNH ///////////
function calculate_age_new(obj){                
    var from_d=$("#"+obj.id).val();
    var from_d1=from_d.substring(0,4);
    var from_d2=from_d.substring(7,5);
    var from_d3=from_d.substring(10,8);
    var frm_d = from_d3+"-"+from_d2+"-"+from_d1;         
    /* var to_d=$("#dt_of_joining").val();
    var to_d1=to_d.substring(0,4);
    var to_d2=to_d.substring(7,5);
    var to_d3=to_d.substring(10,8); */
    var today=new Date();
    var to_d3 = today.getDate();
    var to_d2 = today.getMonth() + 1;
    var to_d1 = today.getFullYear();        
    var to_d0 = to_d3+"-"+to_d2+"-"+to_d1;
    if(to_d2 > from_d2 && to_d3 > from_d3 || to_d3 == from_d3){
    var year = to_d1 - from_d1        
    var month = to_d2 - from_d2
    }
    if(to_d2 > from_d2 && to_d3 < from_d3){
            var year = to_d1 - from_d1        
            var month = to_d2 - from_d2 - 1
            }
    if(from_d2 > to_d2){
            var year = to_d1 - from_d1 - 1
            var month1 = from_d2 - to_d2
            var month = 12 - month1
    }
    if(from_d2 == to_d2 && from_d3 > to_d3){
            var year = to_d1 - from_d1 - 1
            var days = from_d3 - to_d3
    }
    if(from_d2 == to_d2 && to_d3 > from_d3){
            var year = to_d1 - from_d1 
            var days =  to_d3 - from_d3 
            
    }
    if(from_d2 == to_d2 && to_d3 == from_d3){
            var year = to_d1 - from_d1 
            var days = 0
    }
    //days
    if(from_d2 > to_d2 && from_d3 > to_d3){
            var m = from_d2 - to_d2 
            var n = m * 30
            var m1 = from_d3 - to_d3 
            var m2 = n+m1
            var days =  m2
    }
    if(from_d2 > to_d2 && to_d3 > from_d3){
            var m = from_d2 - to_d2 
            var n = m * 30
            var m1 =  to_d3 - from_d3 
            var m2 = n+m1
            var days =  m2
    }
    if(to_d2 > from_d2   && to_d3 > from_d3){
            var m =  to_d2 - from_d2 
            var n = m * 30
            var m1 =  to_d3 - from_d3 
            var m2 = n+m1        
            var days =  m2 
    
    }
    if(to_d2 >  from_d2 && from_d3 > to_d3){
            var m = to_d2 - from_d2   
            var n = m * 30
            var m1 = from_d3 - to_d3 
            var m2 = n+m1
            var days =  m2
            
    }
    $("#age_year").val(year);
//    document.getElementById("age_of_joining").value=year+"-"+month+"-"+days; 
    //document.getElementById('age_year1').value = year;
    
    if (month == undefined)
            {
            month = 0;
            }
    document.getElementById('age_month').value = month;
    
}


