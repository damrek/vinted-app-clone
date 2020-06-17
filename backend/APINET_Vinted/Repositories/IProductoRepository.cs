using APINET_Vinted.Models;
using System;
using System.Collections.Generic;
using System.Linq.Expressions;
using System.Threading.Tasks;

namespace APINET_Vinted.Repositories
{
    public interface IProductoRepository
    {
        Task<List<Producto>> GetProductos();

        Task<List<Producto>> GetProductosMasPuntuados();

        Task<List<Producto>> GetProductosPorCategoria(int categoria);

        Task<List<Producto>> GetProductosPorCoincidenciaCategoria(string palabras, int categoria);

        Task<List<Producto>> GetProductosPorVendedor(int vendedor);

        Task<Producto> AddProducto(Producto producto, int categoria, int usuario);

        Task<Producto> UpdateProducto(Producto producto);

        Task<Producto> GetProducto(int id);

        Task<List<Producto>> FindBy(Expression<Func<Producto, bool>> predicate);

    }
}
