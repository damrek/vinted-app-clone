using APINET_Vinted.Models;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;

namespace APINET_Vinted.Repositories
{
    public class PedidoRepository : IPedidoRepository
    {
        private readonly VINTEDContext _dbContext;

        public PedidoRepository(VINTEDContext dbContext)
        {
            _dbContext = dbContext;
        }

        public async Task<List<Pedido>> GetPedidos()
        {
            return await _dbContext.Pedidos.Select(x => x).Include(x => x.Producto).ToListAsync();
        }

        public async Task<List<Pedido>> GetHistoricoPedidos(int usuario)
        {
            var comprador = await _dbContext.Compradores.FindAsync(usuario);

            return await _dbContext.Pedidos.Select(x => x).Where(x => x.CompradorId == comprador.Id).Include(x => x.Producto).ToListAsync();

        }

        public async Task<Pedido> AddPedido(int producto, int usuario)
        {
            var prod = await _dbContext.Productos.FindAsync(producto);
            var user = await _dbContext.Usuarios.Include(u => u.Comprador).FirstOrDefaultAsync(u => u.Id == usuario);

            Pedido pedido = new Pedido();
            pedido.Fecha = DateTime.Now;
            pedido.Comprador = user.Comprador;
            pedido.CompradorId = user.CompradorId;
            pedido.Producto = prod;
            pedido.Precio = prod.Precio;
            pedido.Cantidad = 1;

            Pedido pedidoOriginal = _dbContext.Pedidos.Select(p => p).Where(p => p.Producto == prod).FirstOrDefault();
            if (pedidoOriginal != null)
            {
                pedido = pedidoOriginal;
                return pedido;
            }

            _dbContext.Pedidos.Add(pedido);

            await _dbContext.SaveChangesAsync();

            return pedido;

        }

        public async Task<Pedido> FinalizarPedido(Pedido pedido)
        {
            var ped = await _dbContext.Pedidos.Select(p => p).Where(p => p.Id == pedido.Id).Include(p => p.Producto).FirstOrDefaultAsync();

            if (ped != null)
            {
                ped.Finalizado = true;
                ped.Producto.Activo = false;
                _dbContext.Pedidos.Update(ped);
                await _dbContext.SaveChangesAsync();

                Venta ventaFinalizar = _dbContext.Ventas.Select(v => v).Where(v => v.Producto == ped.Producto).FirstOrDefault();

                if (ventaFinalizar != null)
                {
                    ventaFinalizar.FechaFin = DateTime.Now;
                    _dbContext.Ventas.Update(ventaFinalizar);
                    await _dbContext.SaveChangesAsync();

                    try
                    {
                        var usuarioComprador = _dbContext.Usuarios.Select(u => u).Where(u => u.Comprador == ped.Comprador).FirstOrDefault();
                        //await EnviarEmailComfirmacion(ped, usuarioComprador.Email);
                    }
                    catch (Exception)
                    {
                        throw;
                    }
                }
            }

            return ped;
        }

        public Task<IActionResult> EnviarEmailComfirmacion(Pedido pedido, string emailDest)
        {
            throw new NotImplementedException();
        }

    }
}
