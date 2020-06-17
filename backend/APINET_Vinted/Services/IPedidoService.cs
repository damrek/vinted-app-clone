using APINET_Vinted.Models;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace APINET_Vinted.Services
{
    public interface IPedidoService
    {
        Task<List<Pedido>> GetPedidos();

        Task<List<Pedido>> GetHistoricoPedidos(int usuario);

        Task<Pedido> AddPedido(int producto, int usuario);

        Task<Pedido> FinalizarPedido(Pedido pedido);
    }
}
