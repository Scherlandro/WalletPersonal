import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/index";
import {ICliente} from "../interfaces/cliente";
import {delay, first, tap} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  private baseUrl: string = environment.API_PATH +'api/clientes';

  constructor(private _http: HttpClient) { }

  public getTodosClientes(): Observable<ICliente[]>{
    return this._http.get<ICliente[]>(this.baseUrl+'/all')
      .pipe(
        first(),
        delay(3000),
        tap(clients=>console.log(clients))
      ) ;
  }

  public getClientePorID(id: number): Observable<ICliente[]>{
    return this._http.get<ICliente[]>(this.baseUrl + id);
  }

  /*  getClientePorID(id: string): Observable<any> {
    return this._http.get(this.baseUrl + id)
      .pipe(map(response => response));
}  */

  createCliente(element: ICliente): Observable<ICliente> {
    return this._http.post<ICliente>(this.baseUrl+'/salvar', element);
  }

  editElement(element: ICliente): Observable<ICliente> {
    return this._http.put<ICliente>(this.baseUrl+'/editar', element);
  }

  deleteElement(id: number): Observable<any> {
    return this._http.delete<any>(`${this.baseUrl}/delete/${id}`);
  }

}
