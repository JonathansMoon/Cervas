//Recebe o objeto Cervas se existir, senão cria o objeto 
var Cervas = Cervas || {};

//Cria a função MaskMoney
Cervas.MaskMoney = (function(){

  function MaskMoney() {
    this.decimal = $('.js-decimal');
    this.plain = $('.js-plain');
  }

  MaskMoney.prototype.enable = function() {
    this.decimal.maskMoney({ decimal: ',' , thousands: '.'});
    this.plain.maskMoney({ precision: 0, thousands: '.'});
  }  
  
  return MaskMoney;

}());

$(function() {
    var maskMoney = new Cervas.MaskMoney();
    maskMoney.enable();
  })