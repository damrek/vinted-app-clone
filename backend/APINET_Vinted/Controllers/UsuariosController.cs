using APINET_Vinted.Models;
using APINET_Vinted.Services;
using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace APINET_Vinted.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class UsuariosController : ControllerBase
    {
        private readonly IUsuarioService _usuarioService;

        public UsuariosController(IUsuarioService usuarioService)
        {
            _usuarioService = usuarioService;
        }

        // GET: api/Usuarios
        /// <summary>
        /// Obtiene todos los usuarios de la aplicación
        /// </summary>
        /// <returns></returns>
        [HttpGet]
        [Produces("application/json",Type = typeof(Task<List<Usuario>>))]
        public async Task<IActionResult> GetUsuarios()
        {
            var usuarios = await _usuarioService.GetUsuarios();

            if (usuarios == null)
            {
                return NotFound();
            }

            return Ok(usuarios);
        }

        // GET: api/Usuarios/masventas
        [HttpGet("masventas")]
        public async Task<IActionResult> GetUsuariosMasVentas()
        {
            var usuarios = await _usuarioService.GetUsuariosMasVentas();

            if(usuarios == null) {
                return NotFound();
            }

            return Ok(usuarios);
        }


        //// GET: api/Usuarios/5
        [HttpGet("{id}")]
        public async Task<IActionResult> GetUsuario(int id)
        {
            var usuario = await _usuarioService.GetUsuario(id);

            if (usuario == null)
            {
                return NotFound();
            }

            return Ok(usuario);
        }


        // POST: api/Usuarios
        [HttpPost]
        public async Task<IActionResult> PostUsuario(Usuario usuario)
        {
            var usuarioCreado = await _usuarioService.AddUsuario(usuario);

            if (usuarioCreado == null)
            {
                return NotFound();
            }

            return Ok(usuarioCreado);
        }

        // POST: api/Usuarios
        [HttpPost("login")]
        public async Task<IActionResult> LoginUsuario([FromBody] Usuario usuario)
        {
            var usuarioLogin = await _usuarioService.FindBy(u => u.Username == usuario.Username 
                                                        && u.Password == usuario.Password
                                                        && u.Activo == true);

            if (usuarioLogin == null)
            {
                return NotFound();
            }

            return Ok(usuarioLogin);
        }
    }
}
