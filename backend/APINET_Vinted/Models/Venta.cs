using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace APINET_Vinted.Models
{
    public class Venta
    {
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        [Key]
        public int Id { get; set; }

        [Required]
        public int Cantidad { get; set; } = 1;

        [Required]
        [Range(0, 9999.99)]
        [Column(TypeName = "decimal(5, 2)")]
        public decimal Precio { get; set; }

        [DataType(DataType.Date)]
        public DateTime FechaInicio { get; set; }

        [DataType(DataType.Date)]
        public DateTime FechaFin { get; set; }

        public virtual Vendedor Vendedor { get; set; }
        public virtual int VendedorId { get; set; }

        public virtual Producto Producto { get; set; }
        public virtual int ProductoId { get; set; }
    }
}
