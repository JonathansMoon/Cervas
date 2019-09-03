var Cervas = Cervas || {};

Cervas.EstiloCadastroRapido = (function(){

	// Método construtor, para as inicializações ficarem visíveis pra todo projeto
	function EstiloCadastroRapido(){
		this.meuModal 				= 	$('#modalCadastroRapidoEstilo');
		this.botaoSalvar 			=	this.meuModal.find('.js-modal-cadastro-estilo-salvar-btn');
		this.meuForm				=	this.meuModal.find('form');
		this.meuUrl					=	this.meuForm.attr('action');
		this.inputNomeEstilo		=	$('#nomeEstilo');
		this.containerMensagemErro 	= 	this.meuModal.find('.js-mensagem-cadastro-rapido-estilo');
	}

	EstiloCadastroRapido.prototype.iniciar = function() {
		this.meuForm.on('submit', function(event) {event.preventDefault()});
		//Ao carregar o modal chama a função onModalShow
		this.meuModal.on('shown.bs.modal', onModalShow.bind(this));
		this.meuModal.on('hide.bs.modal', onModalClose.bind(this));
		this.botaoSalvar.on('click', onBotaoSalvarClick.bind(this));

	}

	function onModalShow() {
		this.inputNomeEstilo.focus();
	}

	function onModalClose() {
		this.inputNomeEstilo.val('');
		this.containerMensagemErro.addClass('hidden');
		this.meuForm.find('.form-group').removeClass('has-error');
	}

	function onBotaoSalvarClick() {
		var nomeEstilo	=	this.inputNomeEstilo.val().trim();
		$.ajax({
			url			:	this.meuUrl,
			method		:	'POST',
			contentType	: 	'application/json',
			data		:	JSON.stringify({'nome': nomeEstilo}),
			error		:	onErroSalvandoEstilo.bind(this),
			success		:	onEstiloSalvo.bind(this)
		});
	}
	
	function onErroSalvandoEstilo(obj) {
		var mensagemErro = obj.responseText;
		this.containerMensagemErro.removeClass('hidden');
		this.containerMensagemErro.html('<span>' + mensagemErro + '</span>')
		console.log(mensagemErro);
		this.meuForm.find('.form-group').addClass('has-error');
	}
	
	// Salva e adiciona um novo estilo ao select combo
	function onEstiloSalvo(estilo) {
		var comboEstilo	=	$('#estilo');
		comboEstilo.append('<option value=' + estilo.codigo + '>' + estilo.nome + '</option>');
		comboEstilo.val(estilo.codigo);
		this.meuModal.modal('hide');
	}

	return EstiloCadastroRapido;

}());

$(function() {
	var estiloCadastroRapido = new Cervas.EstiloCadastroRapido();
	estiloCadastroRapido.iniciar();
});


$(function(){
	
	
	
	
	
	
	
	
	
	
});