using APINET_Vinted.Models;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Threading.Tasks;

namespace APINET_Vinted.Repositories
{
    public class ProductoRepository : IProductoRepository
    {

        private readonly VINTEDContext _dbContext;

        public ProductoRepository(VINTEDContext dbContext)
        {
            _dbContext = dbContext;
        }

        public async Task<List<Producto>> GetProductos()
        {
            return await _dbContext.Productos.Select(x => x).ToListAsync();
        }

        public async Task<List<Producto>> GetProductosMasPuntuados()
        {
            return await _dbContext.Productos.Include(p => p).Where(p => p.Activo == true).OrderByDescending(p => p.Puntuacion).Take(10).ToListAsync();
        }

        public async Task<List<Producto>> GetProductosPorCategoria(int categoria)
        {
            return await _dbContext.Productos.FromSqlRaw("SELECT * from dbo.Productos")
               .Where(x => x.Categoria.Id == categoria && x.Activo == true)
               .ToListAsync();
        }

        public async Task<List<Producto>> GetProductosPorCoincidenciaCategoria(string palabras, int categoria)
        {
            var productos = new List<Producto>();
            if (categoria == 0)
            {
                productos = await _dbContext.Productos
                 .FromSqlRaw("SELECT * from dbo.Productos WHERE nombre LIKE '%'+@p0+'%' OR descripcion LIKE '%'+@p0+'%' OR color LIKE '%'+@p0+'%'", palabras) 
                 .Where(p => p.Activo == true)
                 .ToListAsync();
            }
            else
            {
                productos = await _dbContext.Productos
                 .FromSqlRaw("SELECT * from dbo.Productos WHERE (nombre LIKE '%'+@p0+'%' OR descripcion LIKE '%'+@p0+'%' OR color LIKE '%'+@p0+'%') AND categoriaId=@p1", palabras, categoria)
                 .Where(p => p.Activo == true)
                 .ToListAsync();
            }

            return productos;
        }


        public async Task<List<Producto>> GetProductosPorVendedor(int vendedor)
        {
            var productos = new List<Producto>();

            var usuario = _dbContext.Usuarios.Find(vendedor);

            if(usuario != null)
            {
                productos = await _dbContext.Ventas.Where(v => v.VendedorId == usuario.VendedorId)
                    .Select(p => p.Producto)
                    .Where(x => x.Activo == true).ToListAsync();
            }

            return productos;
        }

        public async Task<Producto> GetProducto(int id)
        {
            return await _dbContext.Productos.FindAsync(id);
        }

        public async Task<Producto> AddProducto(Producto producto, int categoria, int usuario)
        {
            var cat = _dbContext.Categorias.Find(categoria);
            var user = _dbContext.Usuarios.Find(usuario);

            if(cat == null || user == null)
            {
                return null;
            }

            producto.Categoria = cat;
            producto.Activo = true;

            _dbContext.Productos.Add(producto);
            _dbContext.SaveChanges();

            var productoAgregado = _dbContext.Productos.Find(producto.Id);
            productoAgregado.Imagen = "producto" + productoAgregado.Id;
            await _dbContext.SaveChangesAsync();

            //Add venta
            Venta venta = new Venta();
            venta.FechaInicio = DateTime.Now;
            venta.Precio = productoAgregado.Precio;
            venta.Producto = productoAgregado;
            venta.VendedorId = user.VendedorId;
            venta.Cantidad = 1;
            _dbContext.Ventas.Add(venta);
            _dbContext.SaveChanges();

            return productoAgregado;
        }

        public async Task<List<Producto>> FindBy(Expression<Func<Producto, bool>> predicate)
        {
            return await _dbContext.Productos.Where(predicate).ToListAsync();
        }

        public async Task<Producto> UpdateProducto(Producto producto)
        {
            var prod = await _dbContext.Productos.FindAsync(producto.Id);

            if (prod != null)
            {
                prod.Puntuacion = producto.Puntuacion;
                prod.Activo = producto.Activo;
                _dbContext.Productos.Update(prod);
                _dbContext.SaveChanges();
            }

            return prod;

        }
    }
}
