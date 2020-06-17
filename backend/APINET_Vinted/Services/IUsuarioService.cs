using APINET_Vinted.Models;
using System;
using System.Collections.Generic;
using System.Linq.Expressions;
using System.Threading.Tasks;

namespace APINET_Vinted.Services
{
    public interface IUsuarioService
    {
        Task<List<Usuario>> GetUsuarios();

        Task<List<UsuarioVentas>> GetUsuariosMasVentas();

        Task<Usuario> AddUsuario(Usuario usuario);

        Task<Usuario> GetUsuario(int id);

        Task<Usuario> FindBy(Expression<Func<Usuario, bool>> predicate);
    }
}
