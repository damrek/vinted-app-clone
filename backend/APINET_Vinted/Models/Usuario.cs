using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Text.Json.Serialization;

namespace APINET_Vinted.Models
{
    public class Usuario
    {
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        [Key]
        public int Id { get; set; }

        [Required]
        [StringLength(maximumLength:20)]
        public string Username { get; set; }

        [Required]
        [StringLength(maximumLength: 20)]
        public string Password { get; set; }

        [Required]
        [StringLength(maximumLength: 60)]
        public string Email { get; set; }

        [Required]
        [DataType(DataType.Date)]
        public DateTime FechaRegistro { get; set; } = DateTime.Now;

        [Required]
        public bool Activo { get; set; } = true;

        [JsonIgnore]
        public virtual Comprador Comprador { get; set; }
        [JsonIgnore]
        public virtual int CompradorId { get; set; }

        [JsonIgnore]
        public virtual Vendedor Vendedor { get; set; }
        [JsonIgnore]
        public virtual int VendedorId { get; set; }
    }
}
