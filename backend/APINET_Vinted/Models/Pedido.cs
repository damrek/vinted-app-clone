using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Text.Json.Serialization;

namespace APINET_Vinted.Models
{
    public class Pedido
    {
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        [Key]
        public int Id { get; set; }

        [Required]
        [DataType(DataType.Date)]
        public DateTime Fecha { get; set; } = DateTime.Now;

        [Required]
        public int Cantidad { get; set; } = 1;

        [Required]
        [Range(0, 9999.99)]
        [Column(TypeName = "decimal(5, 2)")]
        public decimal Precio { get; set; }

        [Required]
        public bool Finalizado { get; set; } = false;

        [JsonIgnore]
        public virtual Comprador Comprador { get; set; }
        [JsonIgnore]
        public virtual int CompradorId { get; set; }

        public virtual Producto Producto { get; set; }
        [JsonIgnore]
        public virtual int ProductoId { get; set; }
    }
}
