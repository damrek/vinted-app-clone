using APINET_Vinted.Models;
using APINET_Vinted.Repositories;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Threading.Tasks;

namespace APINET_Vinted.Services
{
    public class ProductoService : IProductoService
    {

        private IProductoRepository _productoRepository;

        public ProductoService(IProductoRepository productoRepo)
        {
            _productoRepository = productoRepo;
        }

        public async Task<Producto> AddProducto(Producto producto, int categoria, int usuario)
        {
            return await _productoRepository.AddProducto(producto, categoria, usuario);
        }

        public async Task<List<Producto>> FindBy(Expression<Func<Producto, bool>> predicate)
        {
            return await _productoRepository.FindBy(predicate);
        }

        public async Task<Producto> GetProducto(int id)
        {
            return await _productoRepository.GetProducto(id);
        }

        public async Task<List<Producto>> GetProductos()
        {
            return await _productoRepository.GetProductos();
        }

        public async Task<List<Producto>> GetProductosMasPuntuados()
        {
            return await _productoRepository.GetProductosMasPuntuados();
        }

        public async Task<List<Producto>> GetProductosPorCategoria(int categoria)
        {
            return await _productoRepository.GetProductosPorCategoria(categoria);
        }

        public async Task<List<Producto>> GetProductosPorCoincidenciaCategoria(string palabras, int categoria)
        {
            return await _productoRepository.GetProductosPorCoincidenciaCategoria(palabras, categoria);
        }

        public async Task<List<Producto>> GetProductosPorVendedor(int vendedor)
        {
            return await _productoRepository.GetProductosPorVendedor(vendedor);
        }

        public async Task<Producto> UpdateProducto(Producto producto)
        {
            return await _productoRepository.UpdateProducto(producto);
        }
    }
}
