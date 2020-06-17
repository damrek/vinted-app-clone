using APINET_Vinted.Models;
using APINET_Vinted.Services;
using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace APINET_Vinted.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ProductosController : ControllerBase
    {
        private readonly IProductoService _productoService;

        public ProductosController(IProductoService productoService)
        {
            _productoService = productoService;
        }

        [HttpGet]
        public async Task<IActionResult> GetProductos()
        {
            var productos = await _productoService.GetProductos();

            if (productos == null)
            {
                return NotFound();
            }

            return Ok(productos);
        }

        [HttpGet("maspuntuacion")]
        public async Task<IActionResult> GetProductosMasPuntuados()
        {
            var productos = await _productoService.GetProductosMasPuntuados();

            if (productos == null)
            {
                return NotFound();
            }

            return Ok(productos);
        }

        [HttpGet("categoria/{categoria}")]
        public async Task<IActionResult> GetProductosPorCategoria(int categoria)
        {
            var productos = await _productoService.GetProductosPorCategoria(categoria);

            if (productos == null)
            {
                return NotFound();
            }

            return Ok(productos);
        }

        [HttpGet("contiene/{palabras}/categoria/{categoria}")]
        public async Task<IActionResult> GetProductosPorCoincidenciaCategoria(string palabras, int categoria)
        {
            var productos = await _productoService.GetProductosPorCoincidenciaCategoria(palabras, categoria);

            if (productos == null)
            {
                return NotFound();
            }

            return Ok(productos);
        }

        [HttpGet("enventa/{vendedor}")]
        public async Task<IActionResult> GetProductosPorVendedor(int vendedor)
        {
            var productos = await _productoService.GetProductosPorVendedor(vendedor);

            if (productos == null)
            {
                return NotFound();
            }

            return Ok(productos);
        }

        [HttpPost("categoria/{categoria}/usuario/{usuario}")]
        public async Task<IActionResult> AddProducto(int categoria, int usuario,[FromBody] Producto producto)
        {
            var productoAdd = await _productoService.AddProducto(producto, categoria, usuario);

            if (productoAdd == null)
            {
                return NotFound();
            }

            return Ok(productoAdd);
        }

        [HttpGet("{id}")]
        public async Task<IActionResult> GetProducto(int id)
        {

            var producto = await _productoService.GetProducto(id);

            if (producto == null)
            {
                return NotFound();
            }

            return Ok(producto);
        }


        [HttpGet("activos")]
        public async Task<IActionResult> GetProductosActivos()
        {

            var productos = await _productoService.FindBy(p => p.Activo == true);

            if (productos == null)
            {
                return NotFound();
            }

            return Ok(productos);
        }

        [HttpPut("modificar")]
        public async Task<IActionResult> UpdateProducto(Producto producto)
        {

            var prod = await _productoService.UpdateProducto(producto);

            if (prod == null)
            {
                return NotFound();
            }

            return Ok(prod);
        }

    }
}
