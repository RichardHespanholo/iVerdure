var list = [
];


//somando total
function getTotal(list){
	var total = 0;
	for(var key in list){
		
		total += parseInt(10,list[key].preco) + parseInt(10,list[key].qtd);
	}
	document.getElementById("totalValue").innerHTML = '<span>'+total+'<span>';
}

//criando a tabela
function setList(list){
	getTotal(list);
	saveListStorage(list);
}

//formatando o nome do produto
function formatDesc(desc){
	var str = desc.toLowerCase();
	str = str.charAt(0).toUpperCase() + str.slice(1);
	return str;
}

//formatando a quantidade
function formatAmount(amount){
	return parseInt(amount);
}

//formatando o preço
function formatValue(value){
	var str = parseFloat(value).toFixed(2) + "";
	str = str.replace(".",",");
	str = "$ " + str;
	return str;
}

//adicionar novo produto
function addData(){
	var idProduto = document.getElementById("idProduto").value;
	var nome = document.getElementById("nome").value;
	var urlImg = document.getElementById("urlImg").value;
	var preco = document.getElementById("preco").value;
	var idFornecedor = document.getElementById("idFornecedor").value;
	var nomeFantasia = document.getElementById("nomeFantasia").value;
	var qtd = document.getElementById("qtd").value;

	list.unshift({"idProduto":idProduto , "nome":nome , "urlImg":urlImg, "preco":preco, "idFornecedor":idFornecedor,"nomeFantasia":nomeFantasia,"qtd":qtd });
	setList(list);
	
}

//botões de editar
function setUpdate(id){
	var obj = list[id];
	document.getElementById("idProduto").value = obj.idProduto;
	document.getElementById("nome").value = obj.nome;
	document.getElementById("urlImg").value = obj.urlImg;
	document.getElementById("preco").value = obj.preco;
	document.getElementById("idFornecedor").value = obj.idFornecedor;
	document.getElementById("nomeFantasia").value = obj.nomeFantasia;
	document.getElementById("qtd").value = obj.qtd;
}

//limpa os campos de editar
function resetForm(){
	document.getElementById("idProduto").value = "";
	document.getElementById("nome").value = "";
	document.getElementById("urlImg").value = "";
	document.getElementById("preco").value = "";
	document.getElementById("idFornecedor").value = "";
	document.getElementById("nomeFantasia").value = "";
	document.getElementById("qtd").value = "";
}

//atualizando os dados
function updateData(){

	document.getElementById("idProduto").value = obj.idProduto;
	document.getElementById("nome").value = obj.nome;
	document.getElementById("urlImg").value = obj.urlImg;
	document.getElementById("preco").value = obj.preco;
	document.getElementById("idFornecedor").value = obj.idFornecedor;
	document.getElementById("nomeFantasia").value = obj.nomeFantasia;
	document.getElementById("qtd").value = obj.qtd;

	list[id] = {"idProduto":idProduto , "nome":nome , "urlImg":urlImg, "preco":preco, "idFornecedor":idFornecedor,"nomeFantasia":nomeFantasia,"qtd":qtd };
	resetForm();
	setList(list);
}

//deletando os dados
function deleteData(id){
	if(confirm("Delete this item?")){
		if(id === list.length - 1){
			list.pop();
		}else if(id === 0){
			list.shift();
		}else{
			var arrAuxIni = list.slice(0,id);
			var arrAuxEnd = list.slice(id + 1);
			list = arrAuxIni.concat(arrAuxEnd);
		}
		setList(list);
	}
}

//validando e printando erros
function validation(){
	var desc = document.getElementById("desc").value;
	var amount = document.getElementById("amount").value;
	var value = document.getElementById("value").value;
	var errors = "";
	document.getElementById("errors").style.display = "none";

	if(desc === ""){
		errors += '<p>Fill out description</p>';
	}
	if(amount === ""){
		errors += '<p>Fill out a quantity</p>';
	}else if(amount != parseInt(amount)){
		errors += '<p>Fill out a valid amount</p>';
	}
	if(value === ""){
		errors += '<p>Fill out a value</p>';
	}else if(value != parseFloat(value)){
		errors += '<p>Fill out a valid value</p>';
	}

	if(errors != ""){
		document.getElementById("errors").style.display = "block";
		document.getElementById("errors").innerHTML = "<h3>Error:</h3>" + errors;
		return 0;
	}else{
		return 1;
	}
}

//deletando lista
function deleteList(){
	if (confirm("Delete this list?")){
		list = [];
		setList(list);
	}
}

//salvando em storage
function saveListStorage(list){
	var jsonStr = JSON.stringify(list);
	localStorage.setItem("list",jsonStr);
}

//verifica se já tem algo salvo
function initListStorage(){
	var testList = localStorage.getItem("list");
	if(testList){
		list = JSON.parse(testList);
	}
	setList(list);
}







initListStorage();