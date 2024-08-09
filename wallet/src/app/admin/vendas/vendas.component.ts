import { animate, state, style, transition, trigger } from "@angular/animations";
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl } from "@angular/forms";
import { MatDialog } from "@angular/material/dialog";
import { MatPaginator, PageEvent } from "@angular/material/paginator";
import { MatSort } from "@angular/material/sort";
import { MatTable, MatTableDataSource } from "@angular/material/table";
import {of, timeout} from "rxjs";
import { catchError } from "rxjs/operators";
import { iVendas } from 'src/app/interfaces/vendas';
import { iItensVd } from "../../interfaces/itens-vd";
import { ItensVdService } from "../../services/itens-vd.service";
import { VendasService } from "../../services/vendas.service";
import { DialogOpenSalesComponent } from "../../shared/diolog_components/dialog-open-sales/dialog-open-sales.component";
import { DialogProdutoComponent } from "../../shared/diolog_components/dialog-produto/dialog-produto.component";
import { ErrorDiologComponent } from "../../shared/diolog_components/error-diolog/error-diolog.component";
import {SelectionModel} from "@angular/cdk/collections";
import {co} from "chart.js/dist/chunks/helpers.core";


@Component({
  selector: 'app-vendas',
  templateUrl: './vendas.component.html',
  styleUrls: ['./vendas.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class VendaComponent implements OnInit {

  @ViewChild(MatTable) tableVendas!: MatTable<any>;
  @ViewChild(MatPaginator, {static: true}) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  pageEvent!: PageEvent;
  displayedColumnsVd: string[] = ['nome_cliente','dt_venda','total_geral','opicao'];
  tbSourceVd$: MatTableDataSource<iVendas>;
  displayedColumns: string[] = ['codigo','descricao','preco','qtd','soma','data','imagem','opicoes'];
  //tbSourceItensDaVd$ :MatTableDataSource<iItensVd>;
  tbSourceItensDaVd$ :any;
  vendaControl = new FormControl();
  produtControl = new FormControl();
  listaItensVd: iItensVd[]=[];
  itensVdFiltered: iItensVd[]=[];
  rowClicked: any[]=[];
  selection = new SelectionModel<iVendas>(true, []);
  count = 0;
  $isExpanded= false;
  constructor(
              private vendasService: VendasService,
              public dialog: MatDialog,
              private itensDaVdService: ItensVdService
  ) {
    this.tbSourceVd$ = new MatTableDataSource();
    this.tbSourceItensDaVd$ = new MatTableDataSource();
  }

  ngOnInit(): void {
    this.listarVenda();
   }

  listarVenda(){
    this.vendasService.getAllSales()
      .pipe(catchError(error => {
        this.onError('Erro ao buscar Vendas!')
        return of([])
      }))
      .subscribe((data: iVendas[]) => {
      //  console.log('Vendas ==> ', data);
        this.tbSourceVd$.data = data;
        this.tbSourceVd$.paginator = this.paginator;
      });
  }

  consultarPorCliente(nome: string) {
    if (this.vendaControl.valid) {
      this.vendasService.listarVdPorCliente(nome)
        .pipe(catchError(error => {
          this.onError('Erro ao buscar Cliente!')
          return of([])
        }))
        .subscribe((result: iVendas[]) => {
          this.aplicarFiltro(nome);
          this.tbSourceVd$.data = result;
        })
    }
  }

  changeSales(value: any){
    if (value) {
      this.itensVdFiltered = this.listaItensVd.filter(i => i.descricao.toString()
        .includes(value.toUpperCase()));
    } else {
      this.itensVdFiltered = this.listaItensVd;
    }
  }

  aplicarFiltro(valor: string) {
    valor = valor.trim().toLowerCase();
    this.tbSourceVd$.filter = valor;
  }

  onError(errrorMsg: string) {
    this.dialog.open(ErrorDiologComponent, {
      data: errrorMsg
    });
  }

  openDilogVd(eventVd: any) {

    const dialogRef = this.dialog.open(DialogOpenSalesComponent, {
      width: '300px',
      data: eventVd === null ? {
        idVenda: null,
        nomeCliente: '',
        dtVenda: '',
        cod_vendas: null,
        descricao: '',
        valor_venda: '',
        qtd_vendidas: ''
      } : {
        idVenda: eventVd.idVenda,
        nomeCliente: eventVd.nomeCliente,
        dtVenda: eventVd.dtVenda,
        cod_vendas: eventVd.cod_vendas,
        descricao: eventVd.descricao,
        valor_venda: eventVd.valor_venda,
        qtd_vendidas: eventVd.qtd_vendidas,

      }
    });
  }


  openDilogItenVd(eventVd: any){
    console.log("Dados do elementoDialog", eventVd)
    const dialogRef = this.dialog.open(DialogProdutoComponent, {
      width: '300px',
      data: eventVd === null ? {
        cod_vendas: null,
        descricao: '',
        valor_venda: '',
        qtd_vendidas: ''
      } : {
        cod_vendas: eventVd.cod_vendas,
        descricao: eventVd.descricao,
        valor_venda: eventVd.valor_venda,
        qtd_vendidas: eventVd.qtd_vendidas,

      }
    });

  /*  dialogRef.afterClosed().subscribe(result => {
      if (result !== undefined) {
        if (this.tbSourceItensDaVd$.data
          .map(p => p.id).includes(result.id)) {
          this.itensDaVdService.getItensVdEntreDatas(result)
            .subscribe((data: IProduto) => {
              const index = this.tbSourceItensDaVd$.data
                .findIndex(p => p.id === data.id_produto);
              this.tbSourceItensDaVd$.data[index] = data;
              this.tableVendas.renderRows();
            });
        } else {
          this.itensDaVdService.createVd(result)
            .subscribe((data: IProduto) => {
              this.tbSourceItensDaVd$.data.push(result);
              this.tableVendas.renderRows();
            });
        }
      }
    });*/
  }

  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.tbSourceVd$.data.length;
    var compare = numSelected === numRows;
    console.log('Comparater IsAllSelected', numSelected )
    return numSelected === numRows;
  }

  /** The label for the checkbox on the passed row */
  checkboxLabel(row?: iVendas): string {
    if (!row) {
      return `${this.isAllSelected() ? 'deselect' : 'select'} all`;
    }
    return `${this.selection.isSelected(row) ? 'deselect' : 'select'} row ${row.idVenda + 1}`;
  }


  rowClickHandler(element: any){

   console.log('-------Val isExpanded ',element.isExpanded);
    console.log('count antes if', this.count);
    console.log('Begin $IsExpanded', this.$isExpanded);

    if(this.count === 0 && !this.$isExpanded && element.isExpanded === true){
     // this.count += 1;
      console.log('count no if', this.count, '$isEx..', !this.$isExpanded);
      if(this.count === 0 && !this.$isExpanded) {
        for (var i = 0; i < 2; i++) {
          console.log('valor i', i);
          this.count += i;
          this.tbSourceItensDaVd$.data = element.itensVd;
        }
        this.$isExpanded = true;
        element.isExpanded = null;
      }
    }else {

      this.$isExpanded = element.isExpanded;
    }

  }

  toggleRow(element: any) {

  //  console.log('element before ==> ', element.isExpanded , 'ID vd', element);
   // console.log('ID da venda selecionada ==> ', element.idVenda.toString());

    /*  this.itensDaVdService.listarItensVdPorCodVenda(element.idVenda.toString())
        .pipe(catchError(error => {
          this.onError('Erro ao buscar Itens da Venda!')
          return of([])
        }))
        .subscribe((data: iItensVd[]) => {
       //   console.log('ItensVD ==> ', data);
          this.tbSourceItensDaVd$.data = data;
          var soma = 0;
          for (var i = 0; i < data.length; i++) {
            soma += data.map(i => i.valor_parcial)[i];
          }*/
       //   console.log('ItensVD somados', soma);
     /*     console.log('element half', element.isExpanded);
          if (soma > 0 && element.isExpanded < 2) {
           for(var j = 0; j < 2; j++){
             element.isExpanded += j;
           }
            console.log('element out', element.isExpanded);
          }*/
       //buscando o menor valor
          var matriz = [11, 20, 3, 4, 15, 9, 42, 18, 31];
          var smallest = matriz[0];
          for(var i=1; i<matriz.length ; i++){
            if(matriz[i] < smallest){
              smallest = matriz[i];
            }else {
              smallest = matriz[i];
            }
        //    console.log('Menores',smallest);
          }
          // busca mais simplificado
          var small = matriz.sort((a, b) => a - b);
       //   console.log('Menor RESULT',small[0]);
       //   console.log('RESULT ordenado',small);
       //   console.log('Maior RESULT',small[matriz.length - 1]);

       // });


  }





}
