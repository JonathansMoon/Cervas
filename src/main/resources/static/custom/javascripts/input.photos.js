var bar = document.getElementById('js-progressbar');

    $(function() {
    	var settings = {
    			
			url: 'fotos',
	        type:'json',
	        allow: '*.(jpg|jpeg|png)',
	        multiple: false,
	
	        beforeSend: function () {
	            console.log('beforeSend', arguments);
	        },
	        beforeAll: function () {
	            console.log('beforeAll', arguments);
	        },
	        load: function () {
	            console.log('load', arguments);
	        },
	        error: function () {
	            console.log('error', arguments);
	        },
	        complete: function () {
	            console.log('complete', arguments);
	        },
	
	        loadStart: function (e) {
	            console.log('loadStart', arguments);
	
	            bar.removeAttribute('hidden');
	            bar.max = e.total;
	            bar.value = e.loaded;
	        },
	
	        progress: function (e) {
	            console.log('progress', arguments);
	
	            bar.max = e.total;
	            bar.value = e.loaded;
	        },
	
	        loadEnd: function (e) {
	            console.log('loadEnd', arguments);
	
	            bar.max = e.total;
	            bar.value = e.loaded;
	        },
	
	        completeAll: function (resposta) {

				var inputNomeFoto = $('input[name=foto]');
				var inputContentType =  $('input[name=contentType]');

				// Usando a biblioteca HandleBars.js para 
				// gerar variaveis js e utiliza-las no template
				var deOndeVemoHtml = $('#foto-cerveja').html();
				var template = Handlebars.compile(deOndeVemoHtml);
				//Gera o objeto dentro de um template
				var htmlFotoCerveja = template({nomeFoto: resposta.response.nome});

				var containerFotoCerveja = $('.js-container-foto-cerveja');

				var inputUploud = $('#js-upload');

				inputUploud.addClass('hidden');

				inputNomeFoto.val(resposta.response.nome);
				inputContentType.val(resposta.response.contentType);
				
				//Inclui o template HandleBars dentro da class selecionada 
				containerFotoCerveja.append(htmlFotoCerveja);

				$('.js-remove-foto').on('click', function() {
					$('.js-foto-cerveja').remove();
					inputUploud.removeClass('hidden');
					inputNomeFoto.val();
					inputContentType.val();
				});

				setTimeout(function () {
	                bar.setAttribute('hidden', 'hidden');
				}, 1000);		
	        }
    			
    	};
    	
        UIkit.upload($('.js-upload'), settings);

    });
    
