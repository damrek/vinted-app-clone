using APINET_Vinted.Models;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Threading.Tasks;

namespace APINET_Vinted.Repositories
{
    public class UsuarioRepository : IUsuarioRepository
    {
        private readonly VINTEDContext _dbContext;

        public UsuarioRepository(VINTEDContext dbContext)
        {
            _dbContext = dbContext;
        }

        public async Task<Usuario> AddUsuario(Usuario usuario)
        {
            if (_dbContext.Usuarios.Any(e => e.Username == usuario.Username) || 
                _dbContext.Usuarios.Any(e => e.Email == usuario.Email))
            {
                usuario.Username = null;
                usuario.Email = null;
                usuario.Password = null;

                return usuario;
            }
            else
            {

                Comprador comprador = new Comprador();
                comprador.DirecEnvio = "Sin direccion";
                comprador.TarjetaCuenta = "VISA999";
                _dbContext.Compradores.Add(comprador);
                _dbContext.SaveChanges();

                usuario.Comprador = _dbContext.Compradores.Find(comprador.Id);
                usuario.CompradorId = usuario.Comprador.Id;

                Vendedor vendedor = new Vendedor();
                vendedor.Empresa = "Particular";
                vendedor.TarjetaCuenta = "VISA000";
                _dbContext.Vendedores.Add(vendedor);
                _dbContext.SaveChanges();

                usuario.Vendedor = _dbContext.Vendedores.Find(vendedor.Id);
                usuario.VendedorId = usuario.Vendedor.Id;

                _dbContext.Usuarios.Add(usuario);
                await _dbContext.SaveChangesAsync();
                return usuario;
            }

        }

        public async Task<Usuario> FindBy(Expression<Func<Usuario, bool>> predicate)
        {
           return _dbContext.Usuarios.Where(predicate).FirstOrDefault();
        }

        public async Task<Usuario> GetUsuario(int id)
        {
            return await _dbContext.Usuarios.FindAsync(id);
        }

        public async Task<List<Usuario>> GetUsuarios()
        {
            return await _dbContext.Usuarios.Select(x => x).ToListAsync();

        }

        public async Task<List<UsuarioVentas>> GetUsuariosMasVentas()
        {

            List<UsuarioVentas> usuarios = await _dbContext.Set<UsuarioVentas>().FromSqlRaw("SELECT dbo.Usuarios.Id, dbo.Usuarios.Username, dbo.Usuarios.FechaRegistro, count(*) AS TotalVentas from dbo.Usuarios " +
                "INNER JOIN dbo.Vendedores ON dbo.Usuarios.VendedorId = dbo.Vendedores.Id " +
                "INNER JOIN dbo.Ventas ON dbo.Vendedores.Id = dbo.Ventas.VendedorId " +
                "WHERE dbo.Ventas.FechaFin > Convert(datetime,'1975-01-01') AND dbo.Usuarios.Activo=1 " +
                "GROUP BY dbo.Usuarios.Id, dbo.Usuarios.Username, dbo.Usuarios.FechaRegistro").OrderBy(u => u.TotalVentas).OrderByDescending(x => x.TotalVentas).Take(10).ToListAsync();

            return usuarios;
        }
    }
}
