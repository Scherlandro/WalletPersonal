import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {iProduto} from "../../../interfaces/product";
import {ProductService} from "../../../services/product.service";

@Component({
  selector: 'app-dialog-editor',
  templateUrl: './dialog-produto.component.html',
  styleUrls: ['./dialog-produto.component.css']
})
export class DialogProdutoComponent implements OnInit {
  isChange!: boolean;

  constructor(
    @Inject(MAT_DIALOG_DATA)
    public iproduto: iProduto,
    public dialogRef: MatDialogRef<DialogProdutoComponent>,
    public productServices: ProductService
  ) {}


  ngOnInit(): void {
    if (this.iproduto.idProduto != null) {
      this.isChange = true;
    } else {
      this.isChange = false;
    }
  }


  onCancel(): void {
    this.dialogRef.close();
  }

  save():void{
    this.productServices.createElements(this.iproduto);
  }

  formatter(value: number): string {
    //<div>{{ formatter(iprodroduto.valorVenda) }}</div>
    return new Intl.NumberFormat('pt-BR', { style: 'currency', currency: 'BRL' }).format(value);
  }

}

