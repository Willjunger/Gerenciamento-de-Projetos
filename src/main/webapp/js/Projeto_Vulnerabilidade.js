// INICIANDO MODAL
$(".modal").on("shown.bs.modal", function() {
	$("#myInput").trigger("focus");
});

$("#btn-dashboard").on("click", function() {
	$("#div-projetos").addClass("div-projetos");
	$("#div-relatorios").addClass("div-relatorios");
	$("#div-dashboard").removeClass("div-dashboard");
});
$("#btn-projetos").on("click", function() {
	$("#div-dashboard").addClass("div-dashboard");
	$("#div-relatorios").addClass("div-relatorios");
	$("#div-projetos").removeClass("div-projetos");
});
$("#btn-relatorios").on("click", function() {
	$("#div-dashboard").addClass("div-dashboard");
	$("#div-projetos").addClass("div-projetos");
	$("#div-relatorios").removeClass("div-relatorios");
});

// INSERT DE PROJETO NOVO
$("#btn-salvar-novo-projeto").on("click", function() {
	let id = $("#id-projeto").val();
	let nome = $("#nome-projeto").val();
	let tipo_projeto = $("input[name='tipo-projeto']:checked").val().substring(0, 1);
	let dt_inicio = $("#dt-inicio").val();
	$.ajax({
		url: `${url}rest/projetos/`,
		type: "POST",
		dataType: "json",
		contentType: "application/json",
		data: JSON.stringify({
			codigo: id,
			nome: nome.toUpperCase(),
			tipo: tipo_projeto.toUpperCase(),
			dt_inicio: dt_inicio
		}),
		success: function() {
			$("#table-projetos")
				.DataTable()
				.ajax.reload();
            $("#modal-novo-projeto").modal("hide");
            $("#limpando-novo-projeto")[0].reset();
			iziToast.success({
				position: "topCenter",
				timeout: 3000,
				closeOnClick: true,
				title: "OK!",
				message: "Novo projeto criado com sucesso."
			});
		},
		error: function() {
			iziToast.warning({
				position: "topCenter",
				timeout: 3000,
				closeOnClick: true,
				title: "Erro!",
				message: "Não foi possível criar o projeto."
			});
		}
    });
});

var url = window.location.href; //pega a url dinamicamente, sem ter que se preocupar com nome do projeto ou porta
url = url.substring(0, url.lastIndexOf("/") + 1);
let rowId;

// ABRINDO MODAL DE CADA PROJETO
$(document).on("dblclick", "#table-projetos tbody tr", function(e) {
	rowId = $(e.target)
		.parents("tr")
		.prop("id");
	$("#id-nome-projeto").text("Projeto - " + rowId);
    $("#modal-dentro-projeto").modal("show");

});

// CANCELAR NOVA VULNERABILIDADE
$("#cancelar-vulnerabilidade").on("click", function() {
	$("#modal-input-vulnerabilidade").modal("hide");
    $("#modal-dentro-projeto").modal("show");
    $("#formulario-vulnerabilidade")[0].reset();
});

// CRIANDO AS VULNERABILIDADES DO PROJETO
$("#modal-dentro-projeto").on("shown.bs.modal", function() {
	reloadTabelaVulnerabilidade();
});

// DESTRUINDO AS VULNERABILIDADES DO PROJETO
$("#modal-dentro-projeto").on("hidden.bs.modal", function() {
	$("#table-vulnerabilidade tbody tr").remove();
});

//MODAL CHECKLIST
$("#btn-checklist").on("click", function() {
    $("#modal-dentro-projeto").modal("hide");
});


 // CANCELAR CHECKLIST
$("#btn-cancelar-checklist").on("click", function() {
	$("#modal-checklist").modal("hide");
	$("#modal-dentro-projeto").modal("show");
});

//CRIANDO TABELA DE CHECKLIST
$(".modal-checklist").on("shown.bs.modal", function() {
    topicos.itemCheck.forEach(insereChecklist);
    $.ajax({
		url: `${url}rest/projetos/${rowId}`,
		type: "GET",
		dataType: "json",
        contentType: "application/json",
		success: function(data) {
            marcarChecklist(data)
		}
    });
});
$(".modal-checklist").on("hidden.bs.modal", function() {
	$("#table-checklist tbody tr").remove();
});

var colunas = {
	nomeColuna: [{ col: "T" }, { col: "NT" }, { col: "NA" }, { col: "A" }]
};
var topicos = {
	itemCheck: [{ nome: "CAPTCHA" }, { nome: "HTTP" }, { nome: "HTTPS" }, { nome: "SEGURANCA" }, { nome: "HTML" }, { nome: "JAVA" }, { nome: "REST" }, { nome: "TOMCAT" }, { nome: "VUE" }, { nome: "SERVLET" }, { nome: "PYTHON" }]
};

// SALVAR CHECKLIST
$("#btn-salvar-checklist").on("click", function() {

    var json = {"codigo": rowId}
    var rads = $("#table-checklist input[type=radio]");
	for (var i = 0; i < rads.length; i++) {
		if (rads[i].checked) {
            var radios = $(rads[i]).attr("id").split("_");
            var antes  = `fl_${radios[0]}`.toLowerCase();
            var depois = radios[1].toUpperCase();
            json[antes] = depois;
		}
    }
    $.ajax({
        url: `${url}rest/projetos/checklist`,
        type: "PUT",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(json),
        success: function(json) {
        }
    });

    $("#modal-checklist").modal("hide");
    $("#modal-dentro-projeto").modal("show")

});

// GERANDO CADA VULNERABILIDADE NO PROJETO
function insereVulnerabilidade(itemVulnerabilidade, index) {
	var corpoTabelaVulnerabilidade = $("#table-vulnerabilidade").find("tbody");
	var linhaVulnerabilidade = `<tr id=${itemVulnerabilidade.codigo}>
																	<td class='d-flex justify-content-center'>
																			<div class='quadrado-${itemVulnerabilidade.fl_criticidade}'></div>
																	</td>
																	<td class='text-center'>
																			<p class='mb-0'>${itemVulnerabilidade.nome}</p>
																	</td>
																	<td class='text-center'>
																			<p class='mb-0'>${itemVulnerabilidade.codigo}</p>
																	</td>
																	<td class='text-center'>
																			<p class='mb-0'>${itemVulnerabilidade.vl_parametros}</p>
																	</td>
																	<td class='text-center'>
																			<div class='d-flex justify-content-around'>
																			<button class='btn btn-info btn-editar' onclick='editarVulnerabilidade(${itemVulnerabilidade.codigo})'><i class='fas fa-edit'></i> Editar</button>
																			<button class='btn btn-danger btn-lixeira btn-excluir-vulnerabilidade' onclick='deletarVulnerabilidade(${itemVulnerabilidade.codigo})'><i class='fas fa-trash-alt'></i> Excluir</button>
																			</div>
																	</td>
															</tr>`;
	corpoTabelaVulnerabilidade.append(linhaVulnerabilidade);
}

// CRIAÇÃO DO CHECKLIST
function insereChecklist(itemCheck, index) {
	var corpoTabela = $("#table-checklist").find("tbody");
	let coluna;
	colunas.nomeColuna.forEach(function Teste(nomeColuna, index) {
		coluna += `<td>
													<div class='text-center form-check d-flex justify-content-center'>
															<input class='form-check-input position-static' type='radio' name='${itemCheck.nome}' id='${itemCheck.nome}_${nomeColuna.col}'>
															<label for='${itemCheck.nome}_${nomeColuna.col}'></label>
													</div>
											</td>`;
	});
	var linha = `<tr>
									<th>${itemCheck.nome}</th>
											${coluna}
				</tr>`;
	corpoTabela.append(linha);
}

// DELETANDO VULNERABILIDADE
function deletarVulnerabilidade(id) {
	iziToast.show({
		resetOnHover: true,
		messageSize: 23,
		messageColor: "white",
		titleSize: 20,
		closeOnEscape: true,
		theme: "dark",
		icon: "icon-person",
		title: "Atenção!",
		message: "Deseja realmente deletar a vulnerabilidade?",
		position: "center", // bottomRight, bottomLeft, topRight, topLeft, topCenter, bottomCenter
		progressBarColor: "#17a2b8",
		buttons: [
			[
				'<button class="edicao-izi-btn">Sim</button>',
				function(instance, toast) {
					instance.hide(
						{
							transitionOut: "fadeOutUp"
						},
						toast,
						"buttonName"
					);
					$.ajax({
						url: `${url}/rest/vulnerabilidade/${id}`,
						type: "DELETE",
						success: function() {
							iziToast.success({
								timeout: 3000,
								position: "topCenter",
								title: "OK!",
								message: "Vulnerabilidade deletada com sucesso"
							});
							reloadTabelaVulnerabilidade();
						}
					});
				},
				true
			], // true to focus
			[
				'<button class="edicao-izi-btn">Cancelar</button>',
				function(instance, toast) {
					instance.hide(
						{
							transitionOut: "fadeOutUp"
						},
						toast,
						"buttonName"
					);
				}
			]
		]
	});
}

// RELOAD DAS VULNERABILIDADES DE CADA PROJETO
function reloadTabelaVulnerabilidade() {
	$("#table-vulnerabilidade tbody tr").remove();
	$.ajax({
		url: `${url}rest/projetos/${rowId}`,
		type: "GET",
		dataType: "json",
		contentType: "application/json",
		success: function(data) {
			data.vulnerabilidades.forEach(insereVulnerabilidade);
		}
	});
}

// MARCANDO O CHECKLIST A PARTIR DO BANCO
function marcarChecklist(check){
    var nome;
    var tipo;

    for (var i in Object.keys(check.checklist)) { //vai passar por todos os objetos dentro do array
        nome = Object.keys(check.checklist)[i].toUpperCase();
        tipo = Object.values(check.checklist)[i];

        var teste = `#${nome.split("_")[1]}_${tipo}`.trim();
        $(`${teste}`).attr("checked",true);
    }
}

// MODAL NOVA VULNERABILIADDE
$("#btn-nova-vulnerabilidade").on("click", function() {
        $("#modal-dentro-projeto").modal("hide");
	$("#modal-input-vulnerabilidade").modal("show");
        $("#formulario-vulnerabilidade")[0].reset();
	$("#titulo-vulnerabilidade").text("Nova Vulnerabilidade");
	$(".btn-salvar-vulnerabilidade").attr("value", "nova");
});

function salvarVulnerabilidade(btn) {
	var controle = $(btn).attr("value");
	var id = $(btn).attr("id");

	var nome = $("#nome-vulnerabilidade").val();
	var selectCriticidade = $("#criticidade-select option:selected")
		.val()
		.substring(0, 1);
	var nParametros = $("#n-parametros").val();
	var parametrosAfetados = $("#parametros-afetados").val();
	var urlAfetadas = $("#url-afetada").val();
	var img = img;
	var descricaoCriticidade = $("#descricao-criticidade").val();
	var mitigacaoRemediacao = $("#descricao-mitigacao-remediacao").val();
	var descricaoReferencias = $("#descricao-referencias").val();

	var json;
	if (controle == "nova") {
		json = {
			nome: nome,
			fl_criticidade: selectCriticidade,
			vl_parametros: nParametros,
			nm_parametros_afetados: parametrosAfetados,
			nm_url_afetadas: urlAfetadas,
			img: img,
			descricao: descricaoCriticidade,
			referencias: descricaoReferencias,
			remediacao: mitigacaoRemediacao,
			id_projeto: rowId
		};
		$.ajax({
			url: `${url}rest/vulnerabilidade/`,
			type: "POST",
			dataType: "json",
			contentType: "application/json",
			data: JSON.stringify(json),
			success: function() {
                reloadTabelaVulnerabilidade();
                $("#formulario-vulnerabilidade")[0].reset();
			}
		});
		iziToast.success({
			position: "topCenter",
			timeout: 3000,
			closeOnClick: true,
			title: "OK!",
			message: "Nova vulnerabilidade salva com sucesso"
		});
	} else {
		json = {
			nome: nome,
			fl_criticidade: selectCriticidade,
			vl_parametros: nParametros,
			nm_parametros_afetados: parametrosAfetados,
			nm_url_afetadas: urlAfetadas,
			img: img,
			descricao: descricaoCriticidade,
			referencias: descricaoReferencias,
			remediacao: mitigacaoRemediacao,
			codigo: id
		};
		$.ajax({
			url: `${url}/rest/vulnerabilidade`,
			type: "PUT",
			dataType: "json",
			contentType: "application/json",
			data: JSON.stringify(json),
			success: function() {
                reloadTabelaVulnerabilidade();
                $("#formulario-vulnerabilidade")[0].reset();
			}
		});
		iziToast.success({
			position: "topCenter",
			timeout: 3000,
			closeOnClick: true,
			title: "OK!",
			message: "Edição salva com sucesso"
		});
	}
	$("#modal-dentro-projeto").modal("show");
	$("#modal-input-vulnerabilidade").modal("hide");
}

// EDITAR AS VULNERABILIDADES
function editarVulnerabilidade(id) {
	$("#modal-dentro-projeto").modal("hide");
	$("#modal-input-vulnerabilidade").modal("show");
	$(".btn-salvar-vulnerabilidade").attr("id", id);
	$(".btn-salvar-vulnerabilidade").attr("value", "edit");
	$("#titulo-vulnerabilidade").text("Editar Vulnerabilidade");
	$.ajax({
		url: `${url}/rest/vulnerabilidade/${id}`,
		type: "GET",
		dataType: "json",
		contentType: "application/json",
		success: function(data) {
			$("#nome-vulnerabilidade").val(data.nome);
			var criticidade = data.fl_criticidade;
			if (criticidade === "A") {
				$("#criticidade-select option[value=A]").attr("selected", true);
				$("#criticidade-select option[value=B]").attr("selected", false);
				$("#criticidade-select option[value=M]").attr("selected", false);
			} else if (criticidade === "B") {
				$("#criticidade-select option[value=A]").attr("selected", false);
				$("#criticidade-select option[value=B]").attr("selected", true);
				$("#criticidade-select option[value=M]").attr("selected", false);
			} else if (criticidade === "M") {
				$("#criticidade-select option[value=A]").attr("selected", false);
				$("#criticidade-select option[value=B]").attr("selected", false);
				$("#criticidade-select option[value=M]").attr("selected", true);
			}
			$("#n-parametros").val(data.vl_parametros);
			$("#parametros-afetados").val(data.nm_parametros_afetados);
			$("#url-afetada").val(data.nm_url_afetadas);
			// img = img;
			$("#descricao-criticidade").val(data.descricao);
			$("#descricao-mitigacao-remediacao").val(data.remediacao);
			$("#descricao-referencias").val(data.referencias);
		}
	});
}
