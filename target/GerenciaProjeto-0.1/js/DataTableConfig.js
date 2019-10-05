// CONFIGURAÇÕES DO DATATABLE
$(document).ready(function() {
    let url = window.location.href; //pega a url dinamicamente, sem ter que se preocupar com nome do projeto ou porta
    url = url.substring(0, url.lastIndexOf("/") + 1);

    // TABELA DE PROJETOS
    $("#table-projetos").DataTable({
        processing: true,
        ajax: { url: `${url}rest/projetos/`,
                dataSrc:'data'},
        columns: [
                    {data: "codigo" },
                    {data: "nome" },
                    {data: "tipo" }
                ],
        rowId: 'codigo',
        language: {
            paginate: {
                previous: "Anterior",
                next: "Próximo"
            },
            sEmptyTable: "Nenhum registro encontrado",
            sInfo: "Mostrando de _START_ até _END_ de _TOTAL_ registros",
            sInfoEmpty: "Mostrando 0 até 0 de 0 registros",
            sInfoFiltered: "(Filtrados de _MAX_ registros)",
            sInfoPostFix: "",
            sInfoThousands: ".",
            sLengthMenu: "_MENU_ resultados por página",
            sLoadingRecords: "Carregando...",
            sProcessing: "Processando...",
            sZeroRecords: "Nenhum registro encontrado",
            sSearch: "Pesquisar",
            oPaginate: {
                sNext: "Próximo",
                sPrevious: "Anterior",
                sFirst: "Primeiro",
                sLast: "Último"
            },
            oAria: {
                sSortAscending: ": Ordenar colunas de forma ascendente",
                sSortDescending: ": Ordenar colunas de forma descendente"
            },
            select: {
                rows: {
                    _: "Selecionado %d linhas",
                    "0": "Nenhuma linha selecionada",
                    "1": "Selecionado 1 linha"
                }
            }
        }
    }).ajax.reload();
});
