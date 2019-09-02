$(function(){
	
	var meuModal 	= 	$('#modalCadastroRapidoEstilo');
	var botaoSalvar =	meuModal.find('.js-modal-cadastro-estilo-salvar-btn');
	var meuForm		=	meuModal.find('form');
	meuForm.on('submit', function(event) {event.preventDefault()});
	var meuUrl		=	meuForm.attr('action');
	var inputNomeEstilo	=	$('#nomeEstilo');
	var containerMensagemErro = meuModal.find('.js-mensagem-cadastro-rapido-estilo');
	
	//Ao carregar o modal chama a função onModalShow
	meuModal.on('shown.bs.modal', onModalShow);
	meuModal.on('hide.bs.modal', onModalClose);
	botaoSalvar.on('click', onBotaoSalvarClick);
	
	function onModalShow() {
		inputNomeEstilo.focus();
	}
	
	function onModalClose() {
		inputNomeEstilo.val('');
	}
	
	function onBotaoSalvarClick() {
		var nomeEstilo	=	inputNomeEstilo.val().trim();
		$.ajax({
			url			:	meuUrl,
			method		:	'POST',
			contentType	: 	'application/json',
			data		:	JSON.stringify({'nome': nomeEstilo}),
			error		:	onErroSalvandoEstilo,
			success		:	onEstiloSalvo
		});
	}
	
	function onErroSalvandoEstilo(obj) {
		var mensagemErro = obj.responseText;
		containerMensagemErro.removeClass('hidden');
		containerMensagemErro.html('<span>' + mensagemErro + '</span>')
		console.log(mensagemErro);
		meuForm.find('.form-group').addClass('has-error');
	}
	
	// Salva e adiciona um novo estilo ao select combo
	function onEstiloSalvo(estilo) {
		var comboEstilo	=	$('#estilo');
		comboEstilo.append('<option value=' + estilo.codigo + '>' + estilo.nome + '</option>');
		comboEstilo.val(estilo.codigo);
		meuModal.modal('hide');
	}
	
});