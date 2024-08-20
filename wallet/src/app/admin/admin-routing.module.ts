import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutComponent } from './layout/layout.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import {ClientesComponent} from "./clientes/clientes.component";
import {UsuariosComponent} from "./usuarios/usuarios.component";
import {ProductsComponent} from "./products/products.component";
import {ContabilidadeComponent} from "./contabilidade/contabilidade.component";
import {VendaComponent} from "./vendas/vendas.component";
import {CAddComponent} from "./vendas/c-add/c-add.component";
import {SalesComponent} from "./vendas/sales/sales.component";

const routes: Routes = [
  {
    path: '', component: LayoutComponent, children: [
      { path: '', redirectTo: 'dashboard', pathMatch: 'full'},

      { path: 'dashboard', component: DashboardComponent },
      {path: 'clientes', component: ClientesComponent},
      {path: 'usuarios', component: UsuariosComponent},
      {path: 'produtos', component: ProductsComponent},
      {path: 'contabilidade', component: ContabilidadeComponent},
      {path: 'vendas', component: VendaComponent},
      {path: 'sales', component: SalesComponent},
      {path: 'addIten',component: CAddComponent},
      /*
       {
        path: 'itenSale', loadChildren:()=>import('./vendas/c-add/c-add.component')
          .then(m => m.CAddComponent)
      }
      */
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
