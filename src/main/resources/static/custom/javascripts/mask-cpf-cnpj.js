var Cervas = Cervas || {};

Cervas.MaskCpfCnpj = (function() {

    function MaskCpfCnpj() {
        this.radioTipoPessoa = $('.js-radio-tipo-pessoa');
        this.labelCpfCnpj = $('[for=cpfOuCnpj]');
        this.inputCpfCnpj = $('#cpfOuCnpj');
    }

    MaskCpfCnpj.prototype.iniciar = function() {
        this.radioTipoPessoa.on('change', onTipoPessoaAlterado.bind(this));
       	var tipoPessoaSelecionada = this.radioTipoPessoa.filter(':checked')[0];
       	if (tipoPessoaSelecionada) {
       		aplicarMascara.call(this, $(tipoPessoaSelecionada));
       	}
    }

    function onTipoPessoaAlterado(event) {
        var tipoPessoaSelecionada = $(event.currentTarget);
    	aplicarMascara.call(this, tipoPessoaSelecionada)
        this.inputCpfCnpj.val('');
    }
    
    function aplicarMascara(tipoPessoaSelecionada) {
        this.labelCpfCnpj.text(tipoPessoaSelecionada.data('documento'));
        this.inputCpfCnpj.mask(tipoPessoaSelecionada.data('mascara'));
        this.inputCpfCnpj.removeAttr('disabled')
    }

    return MaskCpfCnpj;

}());

$(function() {
    var maskCpfCnpj = new Cervas.MaskCpfCnpj
    maskCpfCnpj.iniciar();
});