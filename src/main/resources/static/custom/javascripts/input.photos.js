	var Cervas = Cervas || {};
	var bar = document.getElementById('js-progressbar');

	Cervas.UploadFoto = ( function () {
		//Declaro Variaveis
		function UploadFoto() {

			this.inputNomeFoto = $('input[name=foto]');
			this.inputContentType =  $('input[name=contentType]');

			// Usando a biblioteca HandleBars.js para 
			// gerar variaveis js e utiliza-las no template
			this.deOndeVemoHtml = $('#foto-cerveja').html();
			this.template = Handlebars.compile(this.deOndeVemoHtml);
			this.containerFotoCerveja = $('.js-container-foto-cerveja');

			this.inputUploud = $('#js-upload');
		}

		//Inicio o método
		UploadFoto.prototype.iniciar = function () {
			var settings = {
				url: 'fotos',
				type:'json',
				allow: '*.(jpg|jpeg|png)',
				multiple: false,

				loadStart: function (e) {
					bar.removeAttribute('hidden');
					bar.max = e.total;
					bar.value = e.loaded;
			   },
	   
			   progress: function (e) {
				   bar.max = e.total;
				   bar.value = e.loaded;
			   },
	   
			   loadEnd: function (e) {
					bar.max = e.total;
					bar.value = e.loaded;
			   },
				// O this permite usar variaveis acima
				complete: onUploadComplete.bind(this)
			}
			UIkit.upload($('.js-upload'), settings);
		}

		//Metodo chamado ao completar o envio
		function onUploadComplete(resposta) {
			this.inputUploud.addClass('hidden');

			this.inputNomeFoto.val(resposta.response.nome);
			this.inputContentType.val(resposta.response.contentType);
			
			//Gera o objeto dentro de um template
			var htmlFotoCerveja = this.template({nomeFoto: resposta.response.nome});
			//Inclui o template HandleBars dentro da class selecionada 
			this.containerFotoCerveja.append(htmlFotoCerveja);

			$('.js-remove-foto').on('click', onRemoveFoto.bind(this));

			setTimeout(function () {
				bar.setAttribute('hidden', 'hidden');
			}, 1000);
		}

		//Método chamado ao excluir
		function onRemoveFoto () {
			$('.js-foto-cerveja').remove();
			this.inputUploud.removeClass('hidden');
			this.inputNomeFoto.val();
			this.inputContentType.val();
		}

		return UploadFoto;

	})();


	$(function () {
		var uploadFoto = new Cervas.UploadFoto();
		uploadFoto.iniciar();
	});
