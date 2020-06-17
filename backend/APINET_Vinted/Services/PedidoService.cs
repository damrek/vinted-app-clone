using APINET_Vinted.Models;
using APINET_Vinted.Repositories;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace APINET_Vinted.Services
{
    public class PedidoService : IPedidoService
    {

        private IPedidoRepository _pedidoRepository;

        public PedidoService(IPedidoRepository pedidoRepo)
        {
            _pedidoRepository = pedidoRepo;
        }

        public async Task<List<Pedido>> GetPedidos()
        {
            return await _pedidoRepository.GetPedidos();
        }

        public async Task<List<Pedido>> GetHistoricoPedidos(int usuario)
        {
            return await _pedidoRepository.GetHistoricoPedidos(usuario);
        }

        public async Task<Pedido> AddPedido(int producto, int usuario)
        {
            return await _pedidoRepository.AddPedido(producto, usuario);
        }

        public async Task<Pedido> FinalizarPedido(Pedido pedido)
        {
            return await _pedidoRepository.FinalizarPedido(pedido);
        }

    }
}
