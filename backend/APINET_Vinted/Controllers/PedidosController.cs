using APINET_Vinted.Models;
using APINET_Vinted.Services;
using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace APINET_Vinted.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class PedidosController : ControllerBase
    {
        private readonly IPedidoService _pedidoService;

        public PedidosController(IPedidoService pedidoService)
        {
            _pedidoService = pedidoService;
        }

        /// <summary>
        /// Obtiene todos los pedidos generados
        /// </summary>
        /// <returns></returns>
        [HttpGet]
        [Produces("application/json", Type = typeof(Task<List<Pedido>>))]
        public async Task<IActionResult> GetPedidos()
        {
            var pedidos = await _pedidoService.GetPedidos();

            if (pedidos == null)
            {
                return NotFound();
            }

            return Ok(pedidos);
        }

        [HttpGet("historico/{usuario}")]
        public async Task<IActionResult> GetHistoricoPedidos(int usuario)
        {
            var pedidos = await _pedidoService.GetHistoricoPedidos(usuario);

            if (pedidos == null)
            {
                return NotFound();
            }

            return Ok(pedidos);
        }

        [HttpPost("nuevo/{producto}/usuario/{usuario}")]
        public async Task<IActionResult> AddPedido(int producto, int usuario)
        {
            var pedido = await _pedidoService.AddPedido(producto, usuario);

            if (pedido == null)
            {
                return NotFound();
            }

            return Ok(pedido);
        }

        [HttpPut("confirmarpedido")]
        public async Task<IActionResult> FinalizarPedido(Pedido pedido)
        {
            var pedidoFin = await _pedidoService.FinalizarPedido(pedido);

            if (pedidoFin == null)
            {
                return NotFound();
            }

            return Ok(pedidoFin);
        }

    }
}
