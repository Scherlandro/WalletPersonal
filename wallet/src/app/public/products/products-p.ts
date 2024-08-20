import {Component, DEFAULT_CURRENCY_CODE, ElementRef, LOCALE_ID, OnInit, ViewChild} from '@angular/core';
import { of} from 'rxjs';
import {ProductService} from "../../services/product.service";
import {ShoppingCartService} from "../../services/shopping-cart.service";
import {catchError} from "rxjs/operators";
import {ErrorDiologComponent} from "../../shared/diolog_components/error-diolog/error-diolog.component";
import {MatDialog} from "@angular/material/dialog";
import { FormControl} from "@angular/forms";
import {MatTable, MatTableDataSource} from "@angular/material/table";
import {iProduto} from "../../interfaces/product";
import {MatPaginator, PageEvent} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {registerLocaleData} from "@angular/common";
import ptBr from "@angular/common/locales/pt";

registerLocaleData(ptBr);

@Component({
  selector: 'products-p',
  templateUrl: './products-p.html',
  styleUrls: ['./products-p.css'],
  providers:    [ { provide: LOCALE_ID, useValue: 'pt' }]
})

export class ProductsPComponent implements OnInit {
  @ViewChild(MatTable) tableProduto!: MatTable<any>;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  pageEvent: PageEvent;
  displayedColumns: string[] = ['descricao','preco','estoque','imagem','opicoes'];
  tbSourceProdutos$: MatTableDataSource<iProduto>;
  produtosFiltered: iProduto[] = [];
  loaded:any;
  products: iProduto[] = [];
  produtoControl = new FormControl();
  searchTerm !:any;
 /*  string;@ViewChild('imgMain') imgMain!: ElementRef;
  imgWidth:any=0;
  imgHeight:any=0;*/

  constructor(private prodService: ProductService,
              private cartService: ShoppingCartService,
              public dialog: MatDialog
  ) {
    this.tbSourceProdutos$= new MatTableDataSource();
    this.pageEvent = new PageEvent();
  }

  ngOnInit(): void {
    this.listarProdutos();
   }

  /*  onImageLoad(e: any){
    this.imgWidth=(this.imgMain.nativeElement as HTMLImageElement ).width;
    this.imgHeight=(this.imgMain.nativeElement as HTMLImageElement).height;
  }*/

  listarProdutos(){
    this.prodService.getTodosProdutos()
      .pipe(catchError(error => {
        this.onError('Erro ao buscar produto.')
        return of([])}))
      .subscribe(  (rest: iProduto[])=>  {
        this.loaded = rest;
          this.tbSourceProdutos$.data = rest;
      } );
  }

  consultarPorCod(codProd: string){
   // event.preventDefault();
    if (this.produtoControl.valid) {
      this.prodService.getProdutoPorCod(codProd)
        .pipe(catchError(error => {
          this.onError('Erro ao buscar produto.')
          return of([]) })).subscribe((result:iProduto[]) => {
      this.tbSourceProdutos$.data = result;
          console.log("Retorno da MatTableDat ", result )
    } )
    }
  }


  consultarPorNome(nomeProd: string){
    if (this.produtoControl.valid) {
      this.prodService.listarProdutoPorNome(nomeProd)
        .pipe(catchError(error => {
          this.onError('Erro ao buscar produto.')
          return of([]) }))
        .subscribe((result:iProduto[]) => {
          this.aplicarFiltro(nomeProd);
          this.tbSourceProdutos$.data = result;
          console.log(result)
        } )
    }
  }


  aplicarFiltro(valor: string) {
    valor = valor.trim().toLowerCase(); // Remove espaços em branco
    this.tbSourceProdutos$.filter = valor;
  }
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.tbSourceProdutos$.filter = filterValue.trim().toLowerCase();

    if (this.tbSourceProdutos$.paginator) {
      this.tbSourceProdutos$.paginator.firstPage();
    }
  }

  loginAndAddProd(){
  //  this.router.navigate(['/auth/login']);
  }

  changeProdutos(value: any){
    if (value) {
      this.produtosFiltered = this.products.filter(products => products.idProduto.toString()
        .includes(value.toUpperCase()));
    } else {
      this.produtosFiltered = this.products;
    }
  }

  onError(errorMsg: string) {
    this.dialog.open(ErrorDiologComponent, {
      data: errorMsg
    });
  }
  onMatSortChange() {
    this.tbSourceProdutos$.sort = this.sort;
  }


  search(event:any){
    this.searchTerm = (event.target as HTMLInputElement).value;
    console.log(this.searchTerm);
    this.cartService.search.next(this.searchTerm);
  }

  onSubmit(valor: string) {
     this.prodService.search(valor).subscribe(
      (result:iProduto[]) => {  this.tbSourceProdutos$.data = result }
    );
  }


}

