var Cervas = Cervas || {};

Cervas.MaskCpfCnpj = (function() {

    function MaskCpfCnpj() {
        this.radioTipoPessoa = $('.js-radio-tipo-pessoa');
    }

    MaskCpfCnpj.prototype.iniciar = function() {
        this.radioTipoPessoa.on('change', onTipoPessoaAlterado.bind(this));
    }

    function onTipoPessoaAlterado(event) {
        console.log("ssdsadasdas", event);
        
    }

    return MaskCpfCnpj;

}());

$(function() {
    var maskCpfCnpj = new Cervas.MaskCpfCnpj
    maskCpfCnpj.iniciar();
});