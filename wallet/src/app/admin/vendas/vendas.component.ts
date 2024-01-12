import {Component, OnInit, ViewChild} from '@angular/core';
import {iVendas} from "../../interfaces/vendas";
import {VendasService} from "../../services/vendas.service";
import {iItensVd} from "../../interfaces/itens-vd";
import {ItensVdService} from "../../services/itens-vd.service";
import {FormArray, FormControl, NgForm, Validators} from "@angular/forms";
import {catchError} from "rxjs/operators";
import {of} from "rxjs";
import {MatTable, MatTableDataSource} from "@angular/material/table";
import {MatPaginator, PageEvent} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {animate, state, style, transition, trigger} from "@angular/animations";
import {MatDialog} from "@angular/material/dialog";
import {DialogProdutoComponent} from "../../shared/diolog_components/dialog-produto/dialog-produto.component";
import {IProduto} from "../../interfaces/product";


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
  tbSourceItensDaVd$ :MatTableDataSource<iItensVd>;
  vendaControl = new FormControl();
  produtControl = new FormControl();
  listaVendas: iVendas[] = [];
  listaItensVd: iItensVd[]=[];
  itensVdFiltered: iItensVd[]=[];
  panelOpenState = false;
  listCodVd: any;

  constructor(
              private vendasService: VendasService,
              public dialog: MatDialog,
              private itensDaVdService: ItensVdService
  ) {
    this.tbSourceVd$ = new MatTableDataSource();
    this.tbSourceItensDaVd$ = new MatTableDataSource();
  }

  ngOnInit(): void {
    this.listarItensVdEntreDatas();
    this.listarItensPorCodVenda();
    this.listarVenda();
    this.tbSourceItensDaVd$.paginator = this.paginator;
    this.tbSourceVd$.paginator = this.paginator;
   }

  listarVenda(){
    this.vendasService.getAllSales()
      .pipe().subscribe(   (data: iVendas[]) => {
        this.listCodVd = data.map(i=>i.cod_venda);
      this.tbSourceVd$.data = data;
      console.log('Cod de venda-->', this.listCodVd);
   //   this.tbSourceItensDaVd$.paginator = this.paginator;
    });
  }

  listarItensVdEntreDatas(){
    this.itensDaVdService.getItensVdEntreDatas('06/04/2020', '13/04/2020')
      .subscribe(   (data: iItensVd[]) => {
      this.tbSourceItensDaVd$.data = data;
      //this.tbSourceVd$.data = data;
    });
  }
  listarItensPorCodVenda(){
    this.itensDaVdService.listarItensVdPorCodVenda('1')
      .subscribe(   (data: iItensVd[]) => {
     // this.tbSourceItensDaVd$.data = data;
      console.log('Itens por Cod de venda-->', data);
    });
  }

  changeSales(value: any){
    if (value) {
      this.itensVdFiltered = this.listaItensVd.filter(i => i.descricao.toString()
        .includes(value.toUpperCase()));
    } else {
      this.itensVdFiltered = this.listaItensVd;
    }
  }
  openDilogVd(eventVd: any){
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
  toggleRow(element: { expanded: boolean; }) {
    // Uncommnet to open only single row at once
    // ELEMENT_DATA.forEach(row => {
    //   row.expanded = false;
    // })
    element.expanded = !element.expanded
  }

  /*  trash(id:number | undefined){
      console.log(id)
      this.vendasService.trashSales(id).subscribe(
        data => {
          console.log(data)
          this.ngOnInit()
        })
    }

    untrash(id:number | undefined){
      this.vendasService.untrashSales(id).subscribe(
        data => {
          console.log(data)
          this.ngOnInit()
        })
    }*/

  /*
  -------------------------------------
  export interface Cart {
     id: number,
     userId: number,
     date: string,
     products: Array<{productId: number, quantity: number}>,
     __v: number
 }
 -------------------------------------------------
   carts!: Cart[];

   displayedColumns: string[] = ['id','userId', 'date',  'products-id', 'products-quantity'];

   constructor(
     private cartsService: CartsService,
     private route: ActivatedRoute
   ) { }

   ngOnInit(): void {
     this.route.params.subscribe(response => this.cartsService.getCart(response.id)
     .subscribe(response => this.carts = response)
     );

   }
   */


}
