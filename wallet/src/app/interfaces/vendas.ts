export interface iVendas {
  id_venda: number,
  cod_venda: string,
  id_cliente: number,
  nome_cliente: string,
  id_funcionario: number,
  nome_funcionario: string,
  dt_venda: string,
  subtotal: string,
  desconto: string,
  totalgeral: string,
  forma_de_pagamento: string,
  numero_de_parcelas: number
    updatedAt?: string,
    createdAt?: string,
    deletedAt?: null | string
}

export interface ISingleVendas {
    data: iVendas
}

export interface IDataVendas {
    data: iVendas[]
}
