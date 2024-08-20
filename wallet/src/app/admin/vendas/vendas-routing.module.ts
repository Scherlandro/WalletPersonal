import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CAddComponent } from './c-add/c-add.component';
import { CDeleteComponent } from './c-delete/c-delete.component';
import { CEditComponent } from './c-edit/c-edit.component';
import { CIndexComponent } from './c-index/c-index.component';
import {SalesComponent} from "./sales/sales.component";

const routes: Routes = [
  { path: 'sale', component: CIndexComponent },
  { path: 'sales', component: SalesComponent },
  { path: 'edit/:id', component: CEditComponent },
  { path: 'add', component: CAddComponent },
  { path: 'delete/:id', component: CDeleteComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class VendasRoutingModule { }
