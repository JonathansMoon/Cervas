var Cervas = Cervas || {};

Cervas.MaskCep = (function() {

    function MaskCep() {
        this.inputCep = $('[name=cep]');
    }

    MaskCep.prototype.enable = function() {
        this.inputCep.mask('00000-000');
    }

    return MaskCep

}());

$(function() {
    var maskCep = new Cervas.MaskCep
    maskCep.enable();
});