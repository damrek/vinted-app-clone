using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Text.Json.Serialization;

namespace APINET_Vinted.Models
{
    public class Producto
    {
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        [Key]
        public int Id { get; set; }

        [Required]
        [StringLength(maximumLength: 50)]
        public String Nombre { get; set; }

        [StringLength(maximumLength: 300)]
        public String Descripcion { get; set; }

        [Required]
        [Range(0, 9999.99)]
        [Column(TypeName = "decimal(5, 2)")]
        public decimal Precio { get; set; }

        [StringLength(maximumLength: 200)]
        public String Imagen { get; set; }

        [StringLength(maximumLength: 30)]
        public String Color { get; set; }

        [StringLength(maximumLength: 30)]
        public String Talla { get; set; }

        [Required]
        public int Puntuacion { get; set; } = 0;

        [Required]
        public bool Activo { get; set; } = true;

        [JsonIgnore]
        public virtual Categoria Categoria { get; set; }

    }
}
