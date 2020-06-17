using APINET_Vinted.Models;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace APINET_Vinted
{
    public interface IVINTEDContext
    {
        DbSet<Usuario> Usuarios { get; set; }
        DbSet<Comprador> Compradores { get; set; }
        DbSet<Vendedor> Vendedores { get; set; }
        DbSet<Pedido> Pedidos { get; set; }
        DbSet<Venta> Ventas { get; set; }
        DbSet<Producto> Productos { get; set; }
        DbSet<Categoria> Categorias { get; set; }
    }
}
