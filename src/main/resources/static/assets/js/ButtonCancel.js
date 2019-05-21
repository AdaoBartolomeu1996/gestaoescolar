
function apagar(x) {
    //alert(x);
    var id = Number(x);
    if(isNaN(id)){
        alert("Nao é um numero");
        return;
    }
    $("#identifacador").val(id);
}


function cancelarButton(x){
    var url = window.location.origin;
    console.log("voltar : " + x);
    window.open(url+x,"_self");
}