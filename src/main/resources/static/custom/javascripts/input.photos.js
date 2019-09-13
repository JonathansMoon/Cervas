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

	            $('input[name=foto]').val(resposta.response.nome);
	            $('input[name=contentType]').val(resposta.response.contentType);
	
	            setTimeout(function () {
	                bar.setAttribute('hidden', 'hidden');
	            }, 1000);
	
	            alert('Upload Completed');
	        }
    			
    	};
    	
        UIkit.upload($('.js-upload'), settings);

    });
    
