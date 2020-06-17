using APINET_Vinted.Models;
using Microsoft.EntityFrameworkCore;

namespace APINET_Vinted
{
    public class VINTEDContext : DbContext, IVINTEDContext
    {
        public VINTEDContext(DbContextOptions<VINTEDContext> options) : base(options)
        {

        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            //Declaracion de constraints UNIQUE
            modelBuilder.Entity<Usuario>()
                        .HasIndex(b => b.Username)
                        .IsUnique();

            modelBuilder.Entity<Usuario>()
                     .HasIndex(b => b.Email)
                     .IsUnique();

            modelBuilder.Entity<Categoria>()
                     .HasIndex(b => b.Nombre)
                     .IsUnique();


            modelBuilder.Entity<Pedido>(b =>
                   {
                       b.HasIndex(e => new { e.CompradorId, e.ProductoId }).IsUnique();
                   });

            modelBuilder.Entity<Venta>(b =>
                    {
                        b.HasIndex(e => new { e.VendedorId, e.ProductoId }).IsUnique();
                    });

            modelBuilder.Entity<UsuarioVentas>().HasNoKey();
        }

        public DbSet<Usuario> Usuarios { get; set; }
        public DbSet<Comprador> Compradores { get; set; }
        public DbSet<Vendedor> Vendedores { get; set; }
        public DbSet<Pedido> Pedidos { get; set; }
        public DbSet<Venta> Ventas { get; set; }
        public DbSet<Producto> Productos { get; set; }
        public DbSet<Categoria> Categorias { get; set; }

    }
}
