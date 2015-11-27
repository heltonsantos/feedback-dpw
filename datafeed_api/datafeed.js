/*DataFeed URL*/
$urlBase = "http://localhost:8080/datafeed/";

/*Carrega a Datafeed API*/
function loadDatafeed(identifier){
	loadDataset(encodeURIComponent(identifier));
}

/*Carrega os dados do Dataset na pagina*/
function loadDataset(identifier){
	var url = $urlBase + "rest/dataset/buscar?identifier=" + identifier;
	var data = ajaxGet(url);

	if(data!="fail"){					
		console.log(data.id + " " + data.identifier + " " + data.hasRating.toFixed(2));
							
		$("#datafeed").empty();
		$("#datafeed").append("<div id='df_dataset' class='reset_datafeed'></div>");
		$("#df_dataset").append("<h4>Classificação do Dataset:</h4>");
		$("#df_dataset").append("<div id='df_starRating'></div>");
		$("#df_dataset").append("<br></br>");
		$("#df_dataset").append("<h5>Forneça-nos seu feedback, ele contribuirá para melhorar a qualidade dos dados publicados.</h5>");
		$("#df_dataset").append("<button id='dt_button_feedback_form' class='reset_datafeed btn btn-primary white'>Adicionar Feedback</button>");
		$("#df_dataset").append("<br></br>");

		$("#df_starRating").rateYo({
		  	readOnly: true,
		  	numStars: 5,
		    rating: data.hasRating.toFixed(2),
		    starWidth: "40px",
			halfStar: true

		});

		loadFeedback(identifier);

		$("#dt_button_feedback_form").click(function() {
    		loadFeedbackForm(identifier);		
		});
	}
	else{
		alert("Ocorreu um erro no carregamento do dataset!");
	}
}

/*Carrega os dados do Feedback na pagina*/
function loadFeedback(identifier){
	var offset = 0;
	var limit = 5;
	var url = $urlBase + "rest/feedback/buscar?identifier=" + identifier + "&offset=" + offset + "&limit=" + limit;
	var data = ajaxGet(url);
	
	if(data!="fail"){
		$("#datafeed").append("<div id='df_feedback' class='reset_datafeed form-group'></div>");
		$("#df_feedback").append("<br></br>");
		$("#df_feedback").append("<h4>Últimos feedbacks:</h4>");
		$("#df_feedback").append("<ul id='df_feedback_list' class='reset_datafeed list-group df_feedback_list pull-left'></ul>");

		for (var prop in data) { 
			
			$("#df_feedback_list").append("<li id='df_feedback_list" + prop + "' class='list-group-item'></li>");

			if(data[prop].motivatedBy == "RATING"){	
				$("#df_feedback_list" + prop).append("<div id='df_starRatingDataset" + data[prop].id +"'></div>");
				
				$("#df_starRatingDataset" + data[prop].id).rateYo({
				  	readOnly: true,
				  	numStars: 5,
				    rating: parseFloat(data[prop].hasBody).toFixed(2),
				    starWidth: "20px",
					halfStar: true

				});

				$("#df_feedback_list" + prop).append("<p>"+ "<strong>Data de subimissão: </strong>" + data[prop].dateSubmitted +"</p>");
				$("#df_feedback_list" + prop).append("<p>"+ "<strong>Motivação: </strong>" + "Classificação" +"</p>");

				if(data[prop].annotatedBy != null){
					$("#df_feedback_list" + prop).append("<p>"+ "<strong>Nome: </strong>" + data[prop].annotatedBy.giveName +"</p>");
					$("#df_feedback_list" + prop).append("<p>"+ "<strong>Email: </strong>" + data[prop].annotatedBy.mbox +"</p>");
				}

				//$("#df_feedback_list").append("<br></br>");

			}
			else if(data[prop].motivatedBy == "CORRECTION"){
				$("#df_feedback_list" + prop).append("<p>"+ "<strong>Data de subimissão: </strong>" + data[prop].dateSubmitted +"</p>");
				$("#df_feedback_list" + prop).append("<p>"+ "<strong>Comentario: </strong>" + data[prop].hasBody +"</p>");
				$("#df_feedback_list" + prop).append("<p>"+ "<strong>Motivação: </strong>" + "Correção" +"</p>");

				if(data[prop].annotatedBy != null){
					$("#df_feedback_list" + prop).append("<p>"+ "<strong>Nome: " + data[prop].annotatedBy.giveName +"</p>");
					$("#df_feedback_list" + prop).append("<p>"+ "<strong>Email: " + data[prop].annotatedBy.mbox +"</p>");
				}

				//$("#df_feedback_list").append("<br></br>");

			}	
			
		}

	}
	else{
		alert("Ocorreu um erro no carregamento do feedback!");
	}
}

/*Carrega o Formulario de Feedback*/
function loadFeedbackForm(identifier){
	var selectVal;

	if ($("#df_feedbackForm").length){
		$("#df_feedbackForm").remove();
	}

	$("#df_dataset").append("<div id='df_feedbackForm' class='reset_datafeed df_feedbackForm_div'></div>");

	$("#df_feedbackForm").append("<h5>Escolha abaixo a motivação para o seu feedback:</h5>");

	$("#df_feedbackForm").append("<div class='reset_datafeed'><div class='reset_datafeed radio'><label class='reset_datafeed'><input id='df_feedbackForm_radio_rating' class='reset_datafeed' type='radio' name='optradio' value='RATING'>Classificação</label></div><div class='reset_datafeed radio'><label class='reset_datafeed'><input id='df_feedbackForm_radio_correction' class='reset_datafeed' type='radio' name='optradio' value='CORRECTION'>Correção</label></div></div>");

	$("#df_feedbackForm").append("<br></br>");

	$("#df_feedbackForm_radio_rating").change(function() {
		selectVal = $(this).val();
		
		if ($("#df_feedbackForm_content").length == 1){
				$("#df_feedbackForm_content").remove();
		}
		
		$("#df_feedbackForm").append("<div id='df_feedbackForm_content'></div>");

		$("#df_feedbackForm_content").append("<label class='reset_datafeed'>Classificação: </label>");
		$("#df_feedbackForm_content").append("<div id='df_starRatingFeedbackForm' class='reset_datafeed'></div>");

		$("#df_feedbackForm_content").append("<br></br>");

		$("#df_feedbackForm_content").append("<h5>Se você deseja registrar seus dados pessoais insira-os abaixo:</h5>");

  		$("#df_feedbackForm_content").append("<label class='reset_datafeed' for='df_feedbackForm_giveName'>Nome: </label>");
		$("#df_feedbackForm_content").append("<input type='text' id='df_feedbackForm_giveName' class='reset_datafeed' size='35' maxlength='45'/><br>");	

		$("#df_feedbackForm_content").append("<label class='reset_datafeed' for='df_feedbackForm_mbox'>Email: </label>");
		$("#df_feedbackForm_content").append("<input type='text' id='df_feedbackForm_mbox' class='reset_datafeed' size='35' maxlength='45'/><br>");	
		
		$("#df_feedbackForm_content").append("<button id='df_feedbackForm_button' class='reset_datafeed btn btn-success white'>Adicionar</button>");

		$("#df_starRatingFeedbackForm").rateYo({
		  	readOnly: false,
		  	numStars: 5,
		    rating: 0,
		    starWidth: "35px",
			halfStar: true

		});

		$("#df_feedbackForm_button").click(function() {

			if($("#df_starRatingFeedbackForm").rateYo("rating") == ""){
				alert("Insira uma classifiação para o dataset!");
			}
			else{
				if($("#df_feedbackForm_giveName").val() == "" && $("#df_feedbackForm_mbox").val() == ""){
					var rating = $("#df_starRatingFeedbackForm").rateYo("rating");
					var json = {hasBody:rating,motivatedBy:selectVal};
				
					enviarFeedbackForm(identifier, json);
				}
				else{
					var rating = $("#df_starRatingFeedbackForm").rateYo("rating");
					var personJson = {giveName:$("#df_feedbackForm_giveName").val(), mbox:$("#df_feedbackForm_mbox").val()};
					var feedbackJson = {hasBody:rating,motivatedBy:selectVal};
					var json = {feedback:feedbackJson, person:personJson};
				
					enviarFeedbackFormAnnotated(identifier, json);	

				}
			}
			
		});
	});
	$("#df_feedbackForm_radio_correction").change(function() {
		selectVal = $(this).val();

		if ($("#df_feedbackForm_content").length == 1){
				$("#df_feedbackForm_content").remove();
		}
		
		$("#df_feedbackForm").append("<div id='df_feedbackForm_content'></div>");

		$("#df_feedbackForm_content").append("<label class='reset_datafeed' for='df_feedbackForm_hasBody'>Comentário: </label>");
		$("#df_feedbackForm_content").append("<input type='text' id='df_feedbackForm_hasBody' class='reset_datafeed' size='67' maxlength='255'/><br>");	

		$("#df_feedbackForm_content").append("<h5>Se você deseja registrar seus dados pessoais insira-os abaixo:</h5>");

		$("#df_feedbackForm_content").append("<label class='reset_datafeed' for='df_feedbackForm_giveName'>Nome: </label>");
		$("#df_feedbackForm_content").append("<input type='text' id='df_feedbackForm_giveName' class='reset_datafeed' size='35' maxlength='45'/><br>");	

		$("#df_feedbackForm_content").append("<label class='reset_datafeed' for='df_feedbackForm_mbox'>Email: </label>");
		$("#df_feedbackForm_content").append("<input type='text' id='df_feedbackForm_mbox' class='reset_datafeed' size='35' maxlength='45'/><br>");	

		$("#df_feedbackForm_content").append("<button id='df_feedbackForm_button' class='reset_datafeed btn btn-success white'>Adicionar</button>");

		$("#df_feedbackForm_button").click(function() {

			if($("#df_feedbackForm_hasBody").val() == ""){

				$("#df_feedbackForm_hasBody").css("border","1px solid red");
				alert("Insira um comentário para a correção!");
				
				return;
			}

			else{
			
				if($("#df_feedbackForm_giveName").val() == "" && $("#df_feedbackForm_mbox").val() == ""){
					var json = {hasBody:$("#df_feedbackForm_hasBody").val(), motivatedBy:selectVal};
				
					enviarFeedbackForm(identifier, json);
				}
				else{
					var personJson = {giveName:$("#df_feedbackForm_giveName").val(), mbox:$("#df_feedbackForm_mbox").val()};
					var feedbackJson = {hasBody:$("#df_feedbackForm_hasBody").val(), motivatedBy:selectVal};
					var json = {feedback:feedbackJson, person:personJson};
					
					enviarFeedbackFormAnnotated(identifier, json);	
				}
			}	

		});

		$( "#df_feedbackForm_hasBody" ).focus(function() {

 			$("#df_feedbackForm_hasBody").css("border","");
		});

	});
	 
}

/*Envia o Formulario de Feedback*/
function enviarFeedbackForm(identifier, json){
	var url = $urlBase + "rest/feedback/adicionar?identifier=" + identifier;
	var verificacao = ajaxPost(url, json);

	if(verificacao == true){
		alert("Avaliação cadastrada com sucesso!");
		$("#df_avaliarForm").remove();
		location.reload();
	}
	else{
		alert("A avaliação não pode ser cadastrada!")
		$("#df_avaliarForm").remove();
		location.reload();
	}
}

/*Envia o Formulario de Feedback*/
function enviarFeedbackFormAnnotated(identifier, json){
	var url = $urlBase + "rest/feedback/adicionarAnotado?identifier=" + identifier;
	var verificacao = ajaxPost(url, json);

	if(verificacao == true){
		alert("Avaliação cadastrada com sucesso!");
		$("#df_avaliarForm").remove();
		location.reload();
	}
	else{
		alert("A avaliação não pode ser cadastrada!")
		$("#df_avaliarForm").remove();
		location.reload();
	}
}

/*Ajax Get
	Se for sucesso retorna o dado
	Se for falha retorna nulo*/
function ajaxGet(url) {
	var data_return;
	
	$.ajax({
	    async: false,
	    type: "GET",
	    url: url
	}).done(function( data, textStatus, jqXHR ) {
    	console.log(textStatus);
    	console.log(jqXHR.responseText );

    	data_return = data;
  	})
  	.fail(function( jqXHR, textStatus, errorThrown ) {
  		console.log(jqXHR.responseText);
    	console.log(textStatus);
    	console.log(errorThrown);

    	data_return = "fail";
  	});
	
	console.log(data_return);
	return data_return;
}

/*Ajax Post Json
	Se for sucesso retorna true
	Se for falha retorna false */
function ajaxPost(url, json) {
	var data_return;
	$.ajax({
		async: false,
	   	headers: {"Content-Type": "application/json"},
	   	type: "POST",
	   	url: url,
	    data: JSON.stringify(json)
	    
	}).done(function( data, textStatus, jqXHR ) {
		 
    	console.log(textStatus);
    	console.log(jqXHR.responseText );

    	data_return = true;
    	
	}).fail(function( jqXHR, textStatus, errorThrown ) {
		 
    	console.log(jqXHR.responseText);
    	console.log(textStatus);
    	console.log(errorThrown);
    	
    	data_return = false;
    	
	});

	console.log(data_return);
	return data_return;
}

/*rateYo V2.0.1, A simple and flexible star rating plugin
prashanth pamidi (https://github.com/prrashi)*/
!function(a){"use strict";function b(a,b,c){return a===b?a=b:a===c&&(a=c),a}function c(a,b,c){var d=a>=b&&c>=a;if(!d)throw Error("Invalid Rating, expected value between "+b+" and "+c);return a}function d(b,c){var d;return a.each(c,function(){return b===this.node?(d=this,!1):void 0}),d}function e(b,c){return a.each(c,function(a){if(b===this.node){var d=c.slice(0,a),e=c.slice(a+1,c.length);return c=d.concat(e),!1}}),c}function f(a){return"undefined"!=typeof a}function g(d,h){function i(a){f(a)||(a=h.rating);var b=a/I,c=b*K;b>1&&(c+=(Math.ceil(b)-1)*M),Q.css("width",c+"%")}function k(){N=J*h.numStars,N+=L*(h.numStars-1),K=J/N*100,M=L/N*100,d.width(N),i()}function l(a){return f(a)?(h.starWidth=h.starHeight=a,J=parseFloat(h.starWidth.replace("px","")),P.find("svg").attr({width:h.starWidth,height:h.starHeight}),Q.find("svg").attr({width:h.starWidth,height:h.starHeight}),k(),d):h.starWidth}function m(a){return f(a)?(h.spacing=a,L=parseFloat(h.spacing.replace("px","")),P.find("svg:not(:first-child)").css({"margin-left":a}),Q.find("svg:not(:first-child)").css({"margin-left":a}),k(),d):h.spacing}function n(a){return f(a)?(h.normalFill=a,P.find("svg").attr({fill:h.normalFill}),d):h.normalFill}function o(a){return f(a)?(h.ratedFill=a,Q.find("svg").attr({fill:h.ratedFill}),d):h.ratedFill}function p(b){if(!f(b))return h.numStars;h.numStars=b,I=h.maxValue/h.numStars,P.empty(),Q.empty();for(var c=0;c<h.numStars;c++)P.append(a(j)),Q.append(a(j));return l(h.starWidth),o(h.ratedFill),n(h.normalFill),m(h.spacing),i(),d}function q(a){return f(a)?(h.maxValue=a,I=h.maxValue/h.numStars,h.rating>a&&E(a),i(),d):h.maxValue}function r(a){return f(a)?(h.precision=a,i(),d):h.precision}function s(a){return f(a)?(h.halfStar=a,d):h.halfStar}function t(a){return f(a)?(h.fullStar=a,d):h.fullStar}function u(a){var b=a%I,c=I/2,d=h.halfStar,e=h.fullStar;return e||d?(e||d&&b>c?a+=I-b:(a-=b,b>0&&(a+=c)),a):a}function v(a){var b=P.offset(),c=b.left,d=c+P.width(),e=h.maxValue,f=a.pageX,g=0;if(c>f)g=R;else if(f>d)g=e;else{var i=(f-c)/(d-c);if(L>0){i*=100;for(var j=i;j>0;)j>K?(g+=I,j-=K+M):(g+=j/K*I,j=0)}else g=i*h.maxValue;g=u(g)}return g}function w(a){var c=v(a).toFixed(h.precision),e=h.maxValue;c=b(parseFloat(c),R,e),i(c),d.trigger("rateyo.change",{rating:c})}function x(){i(),d.trigger("rateyo.change",{rating:h.rating})}function y(a){var b=v(a).toFixed(h.precision);b=parseFloat(b),H.rating(b)}function z(a,b){h.onChange&&"function"==typeof h.onChange&&h.onChange.apply(this,[b.rating,H])}function A(a,b){h.onSet&&"function"==typeof h.onSet&&h.onSet.apply(this,[b.rating,H])}function B(){d.on("mousemove",w).on("mouseenter",w).on("mouseleave",x).on("click",y).on("rateyo.change",z).on("rateyo.set",A)}function C(){d.off("mousemove",w).off("mouseenter",w).off("mouseleave",x).off("click",y).off("rateyo.change",z).off("rateyo.set",A)}function D(a){return f(a)?(h.readOnly=a,d.attr("readonly",!0),C(),a||(d.removeAttr("readonly"),B()),d):h.readOnly}function E(a){if(!f(a))return h.rating;var e=a,g=h.maxValue;return"string"==typeof e&&("%"===e[e.length-1]&&(e=e.substr(0,e.length-1),g=100,q(g)),e=parseFloat(e)),c(e,R,g),e=parseFloat(e.toFixed(h.precision)),b(parseFloat(e),R,g),h.rating=e,i(),d.trigger("rateyo.set",{rating:e}),d}function F(a){return f(a)?(h.onSet=a,d):h.onSet}function G(a){return f(a)?(h.onChange=a,d):h.onChange}this.$node=d,this.node=d.get(0);var H=this;d.addClass("jq-ry-container");var I,J,K,L,M,N,O=a("<div/>").addClass("jq-ry-group-wrapper").appendTo(d),P=a("<div/>").addClass("jq-ry-normal-group").addClass("jq-ry-group").appendTo(O),Q=a("<div/>").addClass("jq-ry-rated-group").addClass("jq-ry-group").appendTo(O),R=0;this.rating=function(a){return f(a)?(E(a),d):h.rating},this.destroy=function(){return h.readOnly||C(),g.prototype.collection=e(d.get(0),this.collection),d.removeClass("jq-ry-container").children().remove(),d},this.method=function(a){if(!a)throw Error("Method name not specified!");if(!f(this[a]))throw Error("Method "+a+" doesn't exist!");var b=Array.prototype.slice.apply(arguments,[]),c=b.slice(1),d=this[a];return d.apply(this,c)},this.option=function(a,b){if(!f(a))return h;var c;switch(a){case"starWidth":c=l;break;case"numStars":c=p;break;case"normalFill":c=n;break;case"ratedFill":c=o;break;case"maxValue":c=q;break;case"precision":c=r;break;case"rating":c=E;break;case"halfStar":c=s;break;case"fullStar":c=t;break;case"readOnly":c=D;break;case"spacing":c=m;break;case"onSet":c=F;break;case"onChange":c=G;break;default:throw Error("No such option as "+a)}return c(b)},p(h.numStars),D(h.readOnly),this.collection.push(this),this.rating(h.rating)}function h(b){var c=g.prototype.collection,e=a(this);if(0===e.length)return e;var f=Array.prototype.slice.apply(arguments,[]);if(0===f.length)b=f[0]={};else{if(1!==f.length||"object"!=typeof f[0]){if(f.length>=1&&"string"==typeof f[0]){var h=f[0],i=f.slice(1),j=[];return a.each(e,function(a,b){var e=d(b,c);if(!e)throw Error("Trying to set options before even initialization");var f=e[h];if(!f)throw Error("Method "+h+" does not exist!");var g=f.apply(e,i);j.push(g)}),j=1===j.length?j[0]:a(j)}throw Error("Invalid Arguments")}b=f[0]}return b=a.extend(JSON.parse(JSON.stringify(k)),b),a.each(e,function(){var e=d(this,c);return e?void 0:new g(a(this),a.extend({},b))})}function i(){return h.apply(this,Array.prototype.slice.apply(arguments,[]))}var j='<?xml version="1.0" encoding="utf-8"?><svg version="1.1" id="Layer_1"xmlns="http://www.w3.org/2000/svg"viewBox="0 12.705 512 486.59"x="0px" y="0px"xml:space="preserve"><polygon id="star-icon"points="256.814,12.705 317.205,198.566 512.631,198.566 354.529,313.435 414.918,499.295 256.814,384.427 98.713,499.295 159.102,313.435 1,198.566 196.426,198.566 "/></svg>',k={starWidth:"32px",normalFill:"gray",ratedFill:"#f39c12",numStars:5,maxValue:5,precision:1,rating:0,fullStar:!1,halfStar:!1,readOnly:!1,spacing:"0px",onChange:null,onSet:null};g.prototype.collection=[],window.RateYo=g,a.fn.rateYo=i}(jQuery);
