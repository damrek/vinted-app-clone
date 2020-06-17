using APINET_Vinted.Models;
using APINET_Vinted.Repositories;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Threading.Tasks;

namespace APINET_Vinted.Services
{
    public class UsuarioService : IUsuarioService
    {
        private IUsuarioRepository _usuarioRepository;

        public UsuarioService(IUsuarioRepository usuarioRepo) {
            _usuarioRepository = usuarioRepo;
        }

        public async Task<List<Usuario>> GetUsuarios()
        {
            return await _usuarioRepository.GetUsuarios();
        }

        public async Task<Usuario> AddUsuario(Usuario usuario)
        {
            return await _usuarioRepository.AddUsuario(usuario);
        }

        public async Task<Usuario> GetUsuario(int id)
        {
            return await _usuarioRepository.GetUsuario(id);
        }

        public async Task<Usuario> FindBy(Expression<Func<Usuario, bool>> predicate)
        {
            return await _usuarioRepository.FindBy(predicate);
        }

        public Task<List<UsuarioVentas>> GetUsuariosMasVentas()
        {
            return _usuarioRepository.GetUsuariosMasVentas();
        }
    }
}
